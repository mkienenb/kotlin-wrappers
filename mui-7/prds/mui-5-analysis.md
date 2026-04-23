# MUI 5 Kotlin Wrapper Analysis

## Scope

Analyzed these subprojects:

- `kotlin-mui-base`
- `kotlin-mui-icons-material`
- `kotlin-mui-material`
- `kotlin-mui-system`

Goal: determine the minimum rules needed to reproduce the final Kotlin wrapper result for MUI 5, with the intent of applying the same approach to a one-time MUI 7 snapshot, without depending on the original daily generation pipeline.

## Executive Summary

- These subprojects already contain the end product: Kotlin/JS `external` declarations plus a very small amount of helper glue.
- The subprojects themselves do not contain the generation pipeline. They mostly contain `src/jsMain/generated` output and simple Gradle dependencies.
- The wrapper style is intentionally lossy. It does **not** try to preserve full TypeScript fidelity. It regularly collapses difficult types to `dynamic`, `Any`, `react.Props`, or plain string aliases.
- That is the key finding: a one-time MUI 7 snapshot does **not** need the original generator. It only needs a one-time emitter that writes Kotlin files in the same broad style.
- `kotlin-mui-system` is the foundation. `kotlin-mui-base` depends on it, `kotlin-mui-material` depends on base, and `kotlin-mui-icons-material` only depends on material.
- If your goal is "usable Kotlin wrappers", the current MUI 5 output proves you can get there with a relatively small set of translation rules and a lot of deliberate simplification.

## What Is Actually In Each Subproject

| Subproject | Gradle dependencies | Generated `.kt` files | Unique `@JsModule` entrypoints | Manual Kotlin source |
| --- | --- | ---: | ---: | ---: |
| `kotlin-mui-system` | `kotlin-react-dom`, npm `@mui/system` | 28 | 9 | 0 |
| `kotlin-mui-base` | `kotlin-mui-system`, `kotlin-popperjs-core`, npm `@mui/base` | 118 | 42 | 0 |
| `kotlin-mui-material` | `kotlin-mui-base`, npm `@mui/material` | 334 | 139 | 2 |
| `kotlin-mui-icons-material` | `kotlin-mui-material`, npm `@mui/icons-material` | 2 | 1 | 0 |

Generated file breakdown:

| Subproject | `*.classes.kt` | `*.ext.kt` | `*.types.kt` | other |
| --- | ---: | ---: | ---: | ---: |
| `kotlin-mui-system` | 3 | 3 | 0 | 22 |
| `kotlin-mui-base` | 20 | 9 | 43 | 46 |
| `kotlin-mui-material` | 116 | 68 | 3 | 147 |
| `kotlin-mui-icons-material` | 0 | 0 | 0 | 2 |

Important consequence:

- A large part of the file count is support structure, not core runtime bindings.
- The icons module is structurally trivial but huge in raw symbol count: `Icons.kt` alone is 10,617 lines and exposes 10,611 `external val`s.

## Build-Level Conclusions

The subprojects are simple at build time:

- `kotlin-mui-system/build.gradle.kts` only adds npm `@mui/system` and depends on `kotlin-react-dom`.
- `kotlin-mui-base/build.gradle.kts` only adds npm `@mui/base` and depends on `kotlin-mui-system` and `kotlin-popperjs-core`.
- `kotlin-mui-material/build.gradle.kts` only adds npm `@mui/material` and depends on `kotlin-mui-base`.
- `kotlin-mui-icons-material/build.gradle.kts` only adds npm `@mui/icons-material` and depends on `kotlin-mui-material`.

The only special build convention relevant here is in `build-logic/wrappersbuild/src/main/kotlin/wrappersbuild.kotlin-conventions.gradle.kts`, which adds:

- `src/jsMain/generated`
- `src/webMain/generated`

as Kotlin source directories.

For a one-time MUI 7 snapshot, you do **not** need to preserve that layout. You can write directly to `src/jsMain/kotlin` and skip the "generated source dir" convention if you want a simpler setup.

## The Stable Wrapper Patterns

These are the recurring patterns that matter.

### 1. One Kotlin binding per JS module entrypoint

Every real export is rooted in an npm subpath, for example:

- `@mui/system/Box`
- `@mui/material/Button`
- `@mui/material/styles/createTheme`
- `@mui/material/usePagination`
- `@mui/icons-material`

The Kotlin file uses `@file:JsModule("...")` and then declares `external val` or `external fun`.

Typical component pattern:

```kotlin
@file:JsModule("@mui/material/Button")

external interface ButtonProps : ButtonOwnProps, ButtonHTMLAttributes<HTMLButtonElement>, PropsWithComponent

external interface ButtonOwnProps : PropsWithChildren, PropsWithSx {
    var color: ButtonColor?
    var variant: ButtonVariant?
}

@JsName("default")
external val Button: FC<ButtonProps>
```

Typical hook pattern:

```kotlin
@file:JsModule("@mui/material/usePagination")

@JsName("default")
external fun usePagination(
    props: UsePaginationProps,
): UsePaginationResult
```

## 2. React DOM inheritance is used instead of reproducing HTML prop types

The wrappers heavily reuse existing wrapper types:

- `react.dom.html.HTMLAttributes<...>`
- `react.dom.html.ButtonHTMLAttributes<...>`
- `react.dom.svg.SVGAttributes<...>`
- `react.PropsWithChildren`
- `react.PropsWithClassName`
- `react.PropsWithStyle`

This is one of the biggest simplifications. Instead of translating all DOM-facing TypeScript utility types, the wrappers piggyback on existing Kotlin React/Web wrappers.

## 3. Shared helper interfaces do a lot of work

`kotlin-mui-system` provides tiny shared helpers that many other files lean on:

- `mui.types.PropsWithComponent`
- `mui.system.PropsWithSx`
- `mui.system.StandardProps`
- `mui.system.ResponsiveStyleValue`

Examples:

- `PropsWithComponent` is just `var component: react.ElementType<*>?`
- `StandardProps` is just `PropsWithStyle + PropsWithClassName`
- `SxProps<T>` is only `typealias SxProps<T> = react.CSSProperties`

That `SxProps<T>` definition is especially important: the current wrappers do **not** model the real MUI `SxProps` type. They intentionally reduce it to `react.CSSProperties`.

## 4. Literal string unions become `sealed external interface` only when it is useful

Example:

```kotlin
sealed external interface ButtonVariant {
    companion object {
        @JsValue("text")
        val text: ButtonVariant

        @JsValue("outlined")
        val outlined: ButtonVariant

        @JsValue("contained")
        val contained: ButtonVariant
    }
}
```

This pattern is used widely for:

- variants
- colors
- positions
- placements
- sizes
- directions
- class name constants

But it is not universal. Sometimes the wrappers simplify to:

- `typealias Union = String`
- existing web/react aliases such as `InputType` or `TdAlign`
- `dynamic`

So the real rule is:

- preserve small public string unions when they improve Kotlin ergonomics;
- collapse the rest.

## 5. Complex TypeScript constructs are routinely collapsed

This is the central simplification rule.

The current wrappers often reduce:

- conditional types
- mapped types
- overloaded slot prop types
- generic utility types
- React override types
- polymorphic MUI helper types

to one of:

- `dynamic`
- `Any`
- `react.Props`
- `react.ElementType<*>?`
- `Function<*>`

Examples:

- `mui.system.Aliases.kt` defines `typealias Union = String`
- `mui.material.styles.ThemeOptions` uses `dynamic` for `components`, `typography`, `shadows`, `zIndex`, and more
- `mui.base/useAutocomplete.kt` uses many `Any` and `dynamic` escape hatches
- `slotProps` are often modeled as nested interfaces with `react.Props?` fields instead of fully preserving the original TypeScript generic structure

This is exactly why a one-time snapshot is feasible.

## 6. Slot APIs are modeled broadly, not precisely

For `slots` and `slotProps`, the wrappers generally use:

- `react.ElementType<*>?` for slot components
- `react.Props?` for per-slot props

Example shape:

```kotlin
interface SlotProps {
    var root: react.Props?
    var badge: react.Props?
}

var slots: BadgeSlots?
```

This is a very useful rule for MUI 7. It avoids getting stuck on MUI's most complex generic APIs.

## 7. Theme and styling APIs are only partially modeled

The theme-related wrappers are real but still selective:

- some pieces are strongly typed enough to be useful (`Palette`, `Breakpoints`, `ZIndex`, `Transitions`)
- many other pieces remain `dynamic`
- helper ergonomics are minimal

Examples:

- `mui.system.createTheme.kt` leaves many fields as `dynamic`
- `mui.material.styles.createTheme.kt` refines a few system types but still leaves a lot as `dynamic`
- `mui.material/src/jsMain/kotlin/mui/material/styles/createTransitions.ext.kt` is just a small convenience overload
- `TypographyOptions.temp.kt` is a handwritten helper/builder, not part of the core generated structure

Conclusion:

- for a minimum viable MUI 7 snapshot, you only need enough theme typing to support common use;
- you do **not** need to model the full theme type graph perfectly.

## 8. `*.classes.kt` files are separate and optional from a "usable snapshot" perspective

The current wrappers spend a lot of files on class key APIs:

- `ButtonClasses`
- `AutocompleteClasses`
- `badgeClasses`
- `Mui.active`, `Mui.disabled`, etc.

These are valuable for API parity, but they are not structurally necessary to get components working.

For a minimal MUI 7 snapshot you can choose one of two strategies:

- parity-oriented: keep `*.classes.kt` files and `classes: SomeClasses?`
- minimum viable: type `classes` more loosely, or omit exported class-key objects until needed

## 9. Reexports and cross-module sharing are done with `typealias`

The wrappers avoid duplication when an existing type already exists elsewhere.

Examples:

- `mui.material.Box` reuses `mui.system.BoxProps`
- `mui.material.Orientation` is a typealias to `mui.base.Orientation`
- `mui.material.ContainerClasses` is a typealias to `mui.system.ContainerClasses`
- icons use `typealias SvgIconComponent = react.FC<mui.material.SvgIconProps>`

This is an important rule for MUI 7:

- put shared types in the lowest sensible layer;
- alias upward instead of cloning.

## 10. The emitted code is not perfectly clean or exhaustive

There are clear signs that the current output is a pragmatic artifact, not a hand-curated canonical API:

- `kotlin-mui-base/src/jsMain/generated/mui/base/MultiSelect.kt` is empty except for the package declaration
- `kotlin-mui-base/src/jsMain/generated/mui/base/common.types.kt` is also empty
- several `use*` files are type-only and contain no `@JsModule` binding
- some files need suppressions such as `VIRTUAL_MEMBER_HIDDEN`, `VAR_TYPE_MISMATCH_ON_OVERRIDE`, and `NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE`

This matters because it shows you do **not** need to reproduce the current file layout byte-for-byte. The important part is the final Kotlin API surface, not the generator's exact quirks.

## Bare Minimum Rules For A One-Time MUI 7 Snapshot

If the goal is "usable wrappers" rather than "rebuild the original generator", the minimum rule set is:

1. Start from actual published MUI 7 package exports, not the MUI 5 module list.
   The package topology can change across MUI versions. The current `kotlin-mui-base` already shows drift: npm module is `@mui/base`, while comments/docs reference "Base UI".

2. Build the layers in this order:
   `system` -> `base` -> `material` -> `icons`.

3. In `system`, define the shared escape hatches first:
   `PropsWithComponent`, `PropsWithSx`, `StandardProps`, `SxProps<T>`, `ResponsiveStyleValue<T>`, `Breakpoint`, `Theme`, `ThemeOptions`.

4. For each component or hook export, generate one Kotlin `@JsModule` file with:
   a props/result interface,
   React DOM attribute inheritance,
   broad `dynamic`/`Any` fallbacks where TS gets difficult,
   and the final `external val` or `external fun`.

5. Preserve only the small literal unions that materially help call sites.
   Everything else can be `String`, `Union`, `dynamic`, or an existing wrapper alias.

6. Model `slots` and `slotProps` loosely.
   `ElementType<*>?` and `react.Props?` are sufficient for the style already used here.

7. Reuse existing wrapper types wherever possible.
   Do not re-model DOM, CSS, or Popper types if `web`, `react`, `cssom`, or `popper.core` already cover them.

8. Add class-key APIs only if you want parity with the MUI 5 wrapper style.
   They are not required for an initial working snapshot.

9. Add ergonomic helpers last.
   Builder helpers such as `sx { ... }` are nice, but they are not the core wrapper translation problem.

10. Write the snapshot as normal source.
    If you are not regenerating, `src/jsMain/kotlin` is simpler than `src/jsMain/generated`.

## What I Would Treat As The True Minimum

For MUI 7, the smallest sensible target would be:

- keep the four-module dependency layering
- keep `system` shared types and theming entrypoints
- wrap actual default exports and hook exports
- keep prop names and broad React attribute inheritance
- keep a subset of public string unions
- collapse advanced TS to `dynamic`/`Any`
- defer `*.classes.kt`, many helper files, docs, and builder sugar

That would still be recognizably the same wrapper family, while avoiding most of the complexity that motivated the original generator.

## Practical Takeaway

The MUI 5 wrappers show that the "final result" is not a deep semantic translation of MUI's TypeScript model. It is a Kotlin-friendly external facade with selective typing and many escape hatches.

That is good news for your MUI 7 plan:

- you do not need the daily generator
- you do not need full TypeScript fidelity
- you mainly need a one-time export-to-Kotlin translation pass that emits the stable patterns listed above

If you later want MUI 7 parity closer to the current MUI 5 wrappers, the main extra work is not core components. It is the optional surface area:

- `*.classes.kt`
- extra literal-union files
- theme/style helper sugar
- icon symbol expansion
