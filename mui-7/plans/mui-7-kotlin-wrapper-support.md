# Plan: MUI 7 Kotlin Wrapper Support

> Source PRD: `./mui-7/prds/mui-7-kotlin-wrapper-support.md`

## Architectural decisions

Durable decisions that apply across all phases:

- **Project shape**: `./mui-7` is a standalone Kotlin/JS wrapper project that builds as a normal dependency rather than extending the existing daily-generated wrapper modules.
- **Public API**: The exposed Kotlin namespaces remain `mui.types`, `mui.system`, `mui.material`, `mui.material.styles`, and `mui.icons.material`.
- **Third-party boundary**: The wrapper binds the published MUI 7 npm packages needed for the scoped surface, centered on `@mui/system`, `@mui/material`, `@mui/material/styles`, and `@mui/icons-material`.
- **Package surface source**: Implementation should use the local published-package snapshot in `./mui7-surface` as the source of truth for package exports, `.d.ts` entrypoints, and dependency metadata.
- **Pinned versions**: The implementation target keeps `@mui/material@7.3.10`, `@mui/system@7.3.10`, and `@mui/icons-material@7.3.10` pinned to the verified MUI 7 surface, while `@emotion/react`, `@emotion/styled`, `react`, and `react-dom` use compatible ranges so consuming builds can reuse their existing installs.
- **Typing model**: The wrapper follows the existing pragmatic MUI wrapper style: preserve the small unions that materially help Kotlin call sites and use loose types for difficult MUI TypeScript constructs.
- **Theming boundary**: `mui7` owns first-class `mui.material.styles` bindings so consumers can delete local styles shims; consumer-owned routing extensions layered onto `PropsWithComponent` remain outside the dependency.
- **Verification strategy**: Each phase ends with a buildable, consumer-style proof that exercises the phase surface as it would be used from a Kotlin/JS application. "Buildable" means both (a) `compileKotlinJs` green AND (b) the emitted klib contains real IR bodies and runtime-resolvable bindings for every public symbol the phase adds — not just metadata headers. Compile-clean is necessary but not sufficient; a string-union pseudo-enum must resolve at JS module load without a `ReferenceError`.
- **String-union pseudo-enums**: Value unions exposed as `sealed external interface X { companion object { ... } }` MUST use Seskar's `@JsValue("…")` annotation on abstract companion vals. Hand-rolled `literal()` / `unsafeCast` extension properties compile cleanly but emit bare-global references (`get_caption(TypographyVariant)`) that fail at runtime because nothing defines `TypographyVariant` on the JS side. The Seskar compiler plugin (`io.github.turansky.seskar`) must remain applied to `mui-7/build.gradle.kts` for this pattern to produce runtime-correct IR.

---

## Phase 1: Standalone Baseline

**User stories**: 1, 2, 3, 5, 7, 8, 15, 20, 23, 24, 25

### What to build

Create the standalone `./mui-7` dependency skeleton and prove the lowest-level wrapper surface end-to-end. This slice establishes the package namespaces, the project build and dependency model, and the foundational `mui.system` and `mui.types` APIs needed by later phases. The slice should already demonstrate a small consumer-style layout using system `Box`, `sx`, and `PropsWithComponent` compatibility.

### Acceptance criteria

- [x] `./mui-7` builds as a standalone Kotlin/JS dependency with the required MUI 7 npm package boundary defined.
- [x] The standalone dependency is wired against the approved published package set represented by `./mui7-surface`.
- [x] The dependency exposes the baseline namespaces `mui.types` and `mui.system` with the shared helper types needed by later slices.
- [x] A consumer-style proof compiles using `mui.system.Box` and `mui.system.sx` in the same broad style as the existing app.
- [x] `PropsWithComponent` exists with the minimal compatibility required for consumer-owned extensions to remain viable.

---

## Phase 2: Theme and Typography Slice

**User stories**: 6, 11, 16, 17, 18, 19, 20, 23

### What to build

Add the first themed material screen slice. This phase introduces direct `mui.material.styles` bindings, material `Box`, and the text primitives needed to replace the consumer’s local `@mui/material/styles` shim. The slice should prove that a themed screen can be built using direct dependency imports and the existing `unsafeJso`-style theme construction approach.

### Acceptance criteria

- [x] The dependency provides first-class `mui.material.styles.createTheme` and `mui.material.styles.ThemeProvider` bindings.
- [x] The styles bindings are derived from the published MUI 7.3.10 surface available in `./mui7-surface`, not inferred from older wrapper output.
- [x] A consumer-style proof compiles using direct `mui.material.styles` imports instead of a local styles shim.
- [x] `createTheme` accepts the currently used loose theme configuration pattern, including `palette`, `typography`, and `components` overrides.
- [x] `mui.material.Box`, `mui.material.Typography`, and the required `TypographyVariant` members support a themed screen representative of the current app.

---

## Phase 3: Navigation and Action Slice

**User stories**: 9, 10, 15, 18, 19, 21, 22, 23

### What to build

Add the application-shell interaction slice used by the current top navigation. This phase introduces the action and navigation components, the required small unions, and the scoped icons needed to express the current toolbar pattern. The slice should prove that buttons and icons work in consumer-style navigation code while leaving consumer routing fields outside the library.

### Acceptance criteria

- [x] `Button`, `ButtonColor`, `ButtonVariant`, `AppBar`, `AppBarPosition`, and `Toolbar` support the prop patterns already used by the consumer.
- [x] The minimum confirmed literal values compile and behave through a consumer-style navigation example.
- [x] The named icons `AdminPanelSettings`, `Help`, `Person`, and `RateReview` are available in `mui.icons.material`.
- [x] The navigation and icon bindings are verified against the installed published icon and component declarations in `./mui7-surface`.
- [x] Icon usage is verified both as React components and through the existing `create()`-style invocation pattern.

---

## Phase 4: Cards, Detail Content, and Loading Slice

**User stories**: 12, 13, 14, 18, 19, 20, 23

### What to build

Add the content components used by the current detail and list screens. This phase completes the scoped non-navigation surface by covering cards, content containers, separators, and loading feedback. The slice should prove that the documented detail-view patterns can render end-to-end using only the standalone dependency.

### Acceptance criteria

- [x] `Card`, `CardContent`, `Divider`, `DividerVariant`, and `CircularProgress` are available and compile in consumer-style screen code.
- [x] The confirmed divider and typography value patterns used by the consumer repo are supported.
- [x] A representative detail-content proof compiles using cards, text, layout, and divider composition together.
- [x] A representative loading proof compiles with the scoped loading indicator usage.

---

## Phase 5: Consumer Migration Proof Slice

**User stories**: 2, 4, 16, 23, 24, 25

### What to build

Prove the full scoped surface together as a migration-ready dependency. This phase validates that the standalone package can replace the current MUI 5 usage boundary without relying on a local styles shim and without silently expanding scope beyond the approved surface. The outcome is a releasable wrapper snapshot with a durable proof of the intended consumer experience.

### Acceptance criteria

- [x] A single end-to-end proof compiles and the emitted klib links at runtime against the full scoped surface described in the PRD, exercising every string-union value listed in the PRD's literal set.
- [x] The proof imports `mui.material.styles` directly and does not require a local `@mui/material/styles` shim.
- [x] The dependency boundary is verified as intentionally narrow, with no requirement for undocumented MUI surface area.
- [x] The released dependency shape remains aligned with the approved `./mui7-surface` package snapshot and exact version set.
- [x] The project is ready to publish or consume as the initial MUI 7 wrapper snapshot for the target app.

---

## Post-mortem: the `@JsValue` / Seskar runtime-linkage fix

The first delivery of Phases 1–5 passed every acceptance criterion above, yet the consumer app saw ~178 runtime test failures on mount because two string-union values (`AppBarPosition.static` in the root navigation, `TypographyVariant.caption` in a detail view) crashed with `ReferenceError` at JS load time.

### Root cause

Every string-union pseudo-enum was authored with the hand-rolled pattern:

```kotlin
sealed external interface TypographyVariant { companion object }

val TypographyVariant.Companion.caption: TypographyVariant
    get() = literal("caption")   // unsafeCast("caption")
```

The Kotlin/JS backend emitted each call site as `get_caption(TypographyVariant)` — a bare-global reference to the external interface's name. Nothing on the JS side defines a `TypographyVariant` object (no `@JsModule`, no runtime shim, and the external interface itself has no JS identity), so the reference resolves to `undefined` and the module fails at first dereference. The main library's `jsJar` was ~empty of runtime IR because none of the abstract externals produced linkable code.

### Fix

- `mui-7/build.gradle.kts` now applies `id("io.github.turansky.seskar") version "4.40.0"` directly, keeping the project self-contained instead of depending on monorepo conventions.
- `mui-7/src/jsMain/kotlin/mui/material/Types.kt` and `mui-7/src/jsMain/kotlin/mui/material/styles/TypographyVariant.kt` moved every value **inside** the companion object, annotated with `@JsValue("…")`. Seskar rewrites each getter body to return the string literal directly, so the companion object no longer needs any JS-side identity.

### Verification receipt

- Fresh `mui-7-js-7.3.10-pre.1.klib` grew from a metadata-only shell to ~38 KB with real IR bodies.
- IR strings table contains `seskarjsJsValue` annotations and per-member `PROPERTY_BACKING_FIELD` entries; the old `literal` / `unsafeCast` / `jsreflect` references are gone.
- Every string-union value listed in `Implementation Decisions` line 57 of the PRD is exercised by `proof/src/jsMain/kotlin/mui7/proof/*.kt`.

### Lesson folded back into acceptance criteria

The "Verification strategy" architectural decision above was tightened to require runtime-resolvable bindings, not just compile-clean output. The "String-union pseudo-enums" decision explicitly mandates the `@JsValue` / Seskar pattern for any future additions to this wrapper.
