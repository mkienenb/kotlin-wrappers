## Problem Statement

The consuming application currently depends on Kotlin wrappers around MUI 5 and a small local shim over `@mui/material/styles`. That shim exists because the dependency in use does not provide the first-class MUI styles bindings the application wants to consume directly. At the same time, there is no standalone MUI 7 Kotlin wrapper project that reproduces the specific APIs the application actually uses. Requiring full MUI 7 parity or the original daily generation pipeline would make the migration unnecessarily expensive and slow.

The immediate problem is to provide a Kotlin dependency that exposes only the MUI surface proven to be in use today, while remaining practical to build, publish, and maintain as a one-time MUI 7 snapshot. The solution must live in `./mui-7`, remain isolated from the existing monorepo generation flow, and be usable by a consuming Kotlin/JS application as a normal dependency.

## Solution

Create a standalone Kotlin/JS wrapper project in `./mui-7` that packages a narrow MUI 7 snapshot as a consumable dependency. The project will preserve the established Kotlin package vocabulary used by consumers, including `mui.material`, `mui.material.styles`, `mui.system`, `mui.types`, and `mui.icons.material`, but it will only implement the exports required by the documented usage boundary.

The wrapper style will follow the proven MUI 5 patterns described in `./mui-7/prds/mui-5-analysis.md`: one Kotlin binding per JS entrypoint, React DOM attribute inheritance instead of re-modeling HTML types, selective literal unions where they help ergonomics, and broad `dynamic` or `Any` escape hatches where MUI 7 TypeScript types become too complex. The MUI 7 dependency will provide first-class `mui.material.styles` bindings so the consumer can delete its local styles shim. Consumer-owned extensions layered onto wrapper interfaces will remain outside the dependency. The resulting dependency should be easy to consume, easy to rebuild, and intentionally limited in scope.

## User Stories

1. As a Kotlin/JS application maintainer, I want a standalone MUI 7 wrapper project in `./mui-7`, so that wrapper work is isolated from the existing monorepo build and generation pipeline.
2. As a Kotlin/JS application maintainer, I want the `./mui-7` project to build into a dependency I can consume normally, so that I do not need ad hoc local file copies or raw JS interop in the app.
3. As a consumer of the wrapper, I want the familiar package names `mui.material`, `mui.material.styles`, `mui.system`, `mui.types`, and `mui.icons.material`, so that migration from the current wrapper usage stays small.
4. As a consumer of the wrapper, I want the wrapper scope to match the documented MUI 5 usage boundary, so that implementation effort stays focused on the APIs the application truly uses.
5. As a maintainer, I want the project to avoid dependence on the original daily wrapper generator, so that MUI 7 support can be delivered as a one-time pragmatic snapshot.
6. As a consumer, I want `mui.material.Box` support, so that existing layout code can continue to compile with minimal change.
7. As a consumer, I want `mui.system.Box` support, so that layout code that imports system-level Box continues to work.
8. As a consumer, I want the `mui.system.sx` helper, so that existing styling blocks can continue to use the same Kotlin ergonomics.
9. As a consumer, I want `mui.material.Button` with the color and variant values already used by the app, so that action controls can migrate without rewriting component usage.
10. As a consumer, I want `mui.material.AppBar`, `mui.material.Toolbar`, and `mui.material.AppBarPosition`, so that navigation layouts keep their existing structure and typed positioning options.
11. As a consumer, I want `mui.material.Typography` and `mui.material.styles.TypographyVariant`, so that text styling remains typed at the usage points already present in the app.
12. As a consumer, I want `mui.material.Card` and `mui.material.CardContent`, so that the existing card-based detail views remain supported.
13. As a consumer, I want `mui.material.Divider` and `mui.material.DividerVariant`, so that the current section separators continue to compile and render.
14. As a consumer, I want `mui.material.CircularProgress`, so that loading indicators remain available after the migration.
15. As a consumer, I want `mui.types.PropsWithComponent`, so that the wrapper stays compatible with application-level extensions layered on top of the `component` prop without owning those extensions itself.
16. As a consumer, I want `mui.material.styles.createTheme` and `mui.material.styles.ThemeProvider`, so that the application can import them directly from the dependency and delete its local `@mui/material/styles` shim.
17. As a consumer, I want `createTheme` to accept the current `unsafeJso`-style theme configuration pattern, so that existing palette, typography, and component override setup can migrate without a type-modeling project.
18. As a maintainer, I want the wrapper to keep complex MUI 7 TypeScript constructs loosely typed where necessary, so that difficult generic APIs do not block delivery of the required surface area.
19. As a maintainer, I want small public unions such as button variants, button colors, typography variants, divider variants, and app bar positions preserved where they materially improve Kotlin call sites, so that the wrapper remains pleasant to use.
20. As a maintainer, I want the wrapper to reuse existing React, DOM, CSS, and wrapper types wherever possible, so that the project does not duplicate or re-model platform types unnecessarily.
21. As a consumer, I want the specific icons `AdminPanelSettings`, `Help`, `Person`, and `RateReview`, so that the existing navigation UI continues to compile.
22. As a consumer, I want icon bindings to work both as React components and with the existing `create()`-style usage, so that current call sites do not need custom interop workarounds.
23. As a maintainer, I want the standalone dependency to carry the required npm MUI 7 packages and styling peers, so that consuming applications do not need to guess the JS dependency set.
24. As a maintainer, I want a smoke-test or sample usage inside the standalone project that reflects the documented usage boundary, so that regressions against the required API surface are caught quickly.
25. As a maintainer, I want the project to be intentionally incomplete outside the documented usage boundary, so that future expansion can be driven by real consumer needs instead of speculative parity work.

## Implementation Decisions

- The deliverable will be a standalone Kotlin/JS project rooted at `./mui-7`, with its own build and publication flow, rather than a change to the existing daily-generated MUI wrapper modules.
- The standalone project will expose the established wrapper package namespaces used by consumers: `mui.types`, `mui.system`, `mui.material`, `mui.material.styles`, and `mui.icons.material`.
- The project will optimize for a simple consumer experience and may publish as a single Kotlin dependency even though the code preserves logical separation between system, material, styles, and icons packages.
- Source should be authored as normal Kotlin source in the standalone project rather than relying on the existing `src/jsMain/generated` convention.
- The required wrapper surface is limited to the APIs documented in `./mui-7/prds/mui-5-usage.md` and verified against the actual consumer call sites in the `performance-appraisal-frontend` repository.
- The minimum component and type surface includes `mui.material.Box`, `mui.system.Box`, `Button`, `ButtonColor`, `ButtonVariant`, `AppBar`, `AppBarPosition`, `Toolbar`, `Typography`, `mui.material.styles.TypographyVariant`, `Card`, `CardContent`, `CircularProgress`, `Divider`, `DividerVariant`, `PropsWithComponent`, `sx`, `mui.material.styles.createTheme`, `mui.material.styles.ThemeProvider`, and the four named icons in documented use.
- The standalone dependency must provide first-class MUI styles bindings so the consumer can replace `com.gvea.par.common.mui.ThemeWrapper` with direct imports from `mui.material.styles`.
- The wrapper should bind actual MUI 7 npm entrypoints and adapt Kotlin declarations to those exports instead of copying the MUI 5 file list mechanically.
- Each exported component or function should map to a Kotlin `external` declaration rooted in the corresponding JS module entrypoint.
- React DOM attribute inheritance should be reused for component props rather than attempting a full translation of TypeScript utility types for HTML elements.
- Shared helpers such as `PropsWithComponent`, `PropsWithSx`, `StandardProps`, `SxProps<T>`, `Theme`, and `ThemeOptions` should be defined early because other bindings depend on them.
- `SxProps<T>` should remain pragmatically simplified in the style of the MUI 5 wrappers instead of attempting full TypeScript fidelity.
- `createTheme` and `ThemeProvider` should be typed loosely enough to support the consumer's existing `unsafeJso` theme construction pattern, including `palette`, `typography`, and `components` overrides, without requiring exhaustive theme type modeling.
- Small, high-value string unions should be preserved as Kotlin sealed external interfaces when that improves call-site ergonomics; more complex unions may collapse to `String`, `dynamic`, `Any`, or existing aliases.
- The minimum literal unions confirmed from the consumer repo are `ButtonColor.inherit`, `ButtonVariant.text`, `ButtonVariant.outlined`, `AppBarPosition.static`, `DividerVariant.middle`, and the `TypographyVariant` members `h2`, `h3`, `h4`, `h6`, `subtitle1`, `body1`, `body2`, and `caption`.
- `Button` must support the currently used prop patterns: `disabled`, `color`, `variant`, `startIcon`, `component`, children, and `sx`.
- Icon bindings must support both `SomeIcon.create()` usage and component invocation such as `SomeIcon { ... }`.
- Complex MUI 7 generic constructs, slot typing, override typing, and advanced theme graph details should be modeled loosely with deliberate escape hatches instead of blocking on perfect typing.
- The standalone dependency must declare the underlying MUI 7 npm packages and any required styling peers needed for the wrapped components to build and run.
- A project-local verification layer should compile or exercise the documented usage boundary so the wrapper can be validated against the actual required consumer patterns, including direct `mui.material.styles` imports and representative `component = Link` usage.
- Consumer-owned extensions on top of wrapper types, such as extra properties layered onto `PropsWithComponent`, are not part of `mui7`; the wrapper only needs to remain compatible with those local additions.

## Out of Scope

Full MUI 7 parity is out of scope. The project does not need to reproduce the complete MUI Material, Base UI, Lab, X, or icons surface. It does not need to rebuild the original generator, maintain daily upstream synchronization, or preserve the exact file layout of the MUI 5 wrappers.

Also out of scope are exhaustive class-key APIs, broad `*.classes.kt` coverage, perfect TypeScript fidelity for theming and slot APIs, ownership of consumer-specific routing helpers layered on top of `PropsWithComponent`, non-JS targets, and undocumented MUI usages that are not present in `./mui-7/prds/mui-5-usage.md`.

## Further Notes

The current repository state supports a clean standalone effort because the supporting planning documents now live under `./mui-7/prds` and `./mui-7/plans`, keeping the standalone effort scoped under a single directory.

This PRD assumes the documented usage boundary, as confirmed against the current `performance-appraisal-frontend` consumer repo, is the source of truth for the first delivery. If implementation reveals additional consumer code paths or MUI 7 export differences, those should be handled as follow-up scope decisions rather than silently expanding the initial target.

If needed during implementation, source from the consuming repository and the exact published MUI 7 exports can be used to tighten acceptance tests and verify entrypoint names, but they are not required to define this initial product scope.
