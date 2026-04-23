# Plan: MUI 7 Kotlin Wrapper Support

> Source PRD: `./mui-7/prds/mui-7-kotlin-wrapper-support.md`

## Architectural decisions

Durable decisions that apply across all phases:

- **Project shape**: `./mui-7` is a standalone Kotlin/JS wrapper project that builds as a normal dependency rather than extending the existing daily-generated wrapper modules.
- **Public API**: The exposed Kotlin namespaces remain `mui.types`, `mui.system`, `mui.material`, `mui.material.styles`, and `mui.icons.material`.
- **Third-party boundary**: The wrapper binds the published MUI 7 npm packages needed for the scoped surface, centered on `@mui/system`, `@mui/material`, `@mui/material/styles`, and `@mui/icons-material`.
- **Package surface source**: Implementation should use the local published-package snapshot in `./mui7-surface` as the source of truth for package exports, `.d.ts` entrypoints, and dependency metadata.
- **Pinned versions**: The implementation target is `@mui/material@7.3.10`, `@mui/system@7.3.10`, `@mui/icons-material@7.3.10`, `@emotion/react@11.14.0`, `@emotion/styled@11.14.1`, `react@19.2.5`, and `react-dom@19.2.5`.
- **Typing model**: The wrapper follows the existing pragmatic MUI wrapper style: preserve the small unions that materially help Kotlin call sites and use loose types for difficult MUI TypeScript constructs.
- **Theming boundary**: `mui7` owns first-class `mui.material.styles` bindings so consumers can delete local styles shims; consumer-owned routing extensions layered onto `PropsWithComponent` remain outside the dependency.
- **Verification strategy**: Each phase ends with a buildable, consumer-style proof that exercises the phase surface as it would be used from a Kotlin/JS application.

---

## Phase 1: Standalone Baseline

**User stories**: 1, 2, 3, 5, 7, 8, 15, 20, 23, 24, 25

### What to build

Create the standalone `./mui-7` dependency skeleton and prove the lowest-level wrapper surface end-to-end. This slice establishes the package namespaces, the project build and dependency model, and the foundational `mui.system` and `mui.types` APIs needed by later phases. The slice should already demonstrate a small consumer-style layout using system `Box`, `sx`, and `PropsWithComponent` compatibility.

### Acceptance criteria

- [ ] `./mui-7` builds as a standalone Kotlin/JS dependency with the required MUI 7 npm package boundary defined.
- [ ] The standalone dependency is wired against the approved published package set represented by `./mui7-surface`.
- [ ] The dependency exposes the baseline namespaces `mui.types` and `mui.system` with the shared helper types needed by later slices.
- [ ] A consumer-style proof compiles using `mui.system.Box` and `mui.system.sx` in the same broad style as the existing app.
- [ ] `PropsWithComponent` exists with the minimal compatibility required for consumer-owned extensions to remain viable.

---

## Phase 2: Theme and Typography Slice

**User stories**: 6, 11, 16, 17, 18, 19, 20, 23

### What to build

Add the first themed material screen slice. This phase introduces direct `mui.material.styles` bindings, material `Box`, and the text primitives needed to replace the consumer’s local `@mui/material/styles` shim. The slice should prove that a themed screen can be built using direct dependency imports and the existing `unsafeJso`-style theme construction approach.

### Acceptance criteria

- [ ] The dependency provides first-class `mui.material.styles.createTheme` and `mui.material.styles.ThemeProvider` bindings.
- [ ] The styles bindings are derived from the published MUI 7.3.10 surface available in `./mui7-surface`, not inferred from older wrapper output.
- [ ] A consumer-style proof compiles using direct `mui.material.styles` imports instead of a local styles shim.
- [ ] `createTheme` accepts the currently used loose theme configuration pattern, including `palette`, `typography`, and `components` overrides.
- [ ] `mui.material.Box`, `mui.material.Typography`, and the required `TypographyVariant` members support a themed screen representative of the current app.

---

## Phase 3: Navigation and Action Slice

**User stories**: 9, 10, 15, 18, 19, 21, 22, 23

### What to build

Add the application-shell interaction slice used by the current top navigation. This phase introduces the action and navigation components, the required small unions, and the scoped icons needed to express the current toolbar pattern. The slice should prove that buttons and icons work in consumer-style navigation code while leaving consumer routing fields outside the library.

### Acceptance criteria

- [ ] `Button`, `ButtonColor`, `ButtonVariant`, `AppBar`, `AppBarPosition`, and `Toolbar` support the prop patterns already used by the consumer.
- [ ] The minimum confirmed literal values compile and behave through a consumer-style navigation example.
- [ ] The named icons `AdminPanelSettings`, `Help`, `Person`, and `RateReview` are available in `mui.icons.material`.
- [ ] The navigation and icon bindings are verified against the installed published icon and component declarations in `./mui7-surface`.
- [ ] Icon usage is verified both as React components and through the existing `create()`-style invocation pattern.

---

## Phase 4: Cards, Detail Content, and Loading Slice

**User stories**: 12, 13, 14, 18, 19, 20, 23

### What to build

Add the content components used by the current detail and list screens. This phase completes the scoped non-navigation surface by covering cards, content containers, separators, and loading feedback. The slice should prove that the documented detail-view patterns can render end-to-end using only the standalone dependency.

### Acceptance criteria

- [ ] `Card`, `CardContent`, `Divider`, `DividerVariant`, and `CircularProgress` are available and compile in consumer-style screen code.
- [ ] The confirmed divider and typography value patterns used by the consumer repo are supported.
- [ ] A representative detail-content proof compiles using cards, text, layout, and divider composition together.
- [ ] A representative loading proof compiles with the scoped loading indicator usage.

---

## Phase 5: Consumer Migration Proof Slice

**User stories**: 2, 4, 16, 23, 24, 25

### What to build

Prove the full scoped surface together as a migration-ready dependency. This phase validates that the standalone package can replace the current MUI 5 usage boundary without relying on a local styles shim and without silently expanding scope beyond the approved surface. The outcome is a releasable wrapper snapshot with a durable proof of the intended consumer experience.

### Acceptance criteria

- [ ] A single end-to-end proof compiles or runs against the full scoped surface described in the PRD.
- [ ] The proof imports `mui.material.styles` directly and does not require a local `@mui/material/styles` shim.
- [ ] The dependency boundary is verified as intentionally narrow, with no requirement for undocumented MUI surface area.
- [ ] The released dependency shape remains aligned with the approved `./mui7-surface` package snapshot and exact version set.
- [ ] The project is ready to publish or consume as the initial MUI 7 wrapper snapshot for the target app.
