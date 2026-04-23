# MUI 5 Wrapper References

Search basis:

- `rg -n "mui\\.|com\\.gvea\\.par\\.common\\.mui|@mui/material/styles|kotlinWrappers\\.mui" build.gradle.kts src/jsMain/kotlin`
- No additional `mui.` references were found outside the files listed below.

## Dependency References

- `build.gradle.kts:88` `implementation(kotlinWrappers.mui.iconsMaterial)`
- `build.gradle.kts:89` `implementation(kotlinWrappers.mui.material)`

## Local Shim Over `@mui/material/styles`

- `src/jsMain/kotlin/com/gvea/par/common/mui/ThemeWrapper.kt:2` `@file:JsModule("@mui/material/styles")`
- `src/jsMain/kotlin/com/gvea/par/common/mui/ThemeWrapper.kt:11` `external fun createTheme(...)`
- `src/jsMain/kotlin/com/gvea/par/common/mui/ThemeWrapper.kt:13` `external interface ThemeProviderProps`
- `src/jsMain/kotlin/com/gvea/par/common/mui/ThemeWrapper.kt:17` `external val ThemeProvider`
- `src/jsMain/kotlin/com/gvea/par/common/components/ParTheme.kt:3` `import com.gvea.par.common.mui.createTheme`
- `src/jsMain/kotlin/com/gvea/par/common/components/ParTheme.kt:6` `createTheme(...)`
- `src/jsMain/kotlin/com/gvea/par/common/components/App.kt:7` `import com.gvea.par.common.mui.ThemeProvider`
- `src/jsMain/kotlin/com/gvea/par/common/components/App.kt:20` `ThemeProvider { ... }`

## Direct Wrapper Import References

### `mui.icons.material.AdminPanelSettings`

- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:14`

### `mui.icons.material.Help`

- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:15`

### `mui.icons.material.Person`

- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:16`

### `mui.icons.material.RateReview`

- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:17`

### `mui.material.*`

- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:18`

Expanded symbol references from that wildcard import:

- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:36` `AppBar`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:38` `AppBarPosition`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:46` `Toolbar`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:57` `Box`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:63` `Typography`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:84` `Box`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:94` `Button`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:95` `ButtonColor`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:96` `ButtonVariant`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:124` `Button`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:125` `ButtonColor`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:126` `ButtonVariant`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:159` `Button`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:160` `ButtonColor`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:161` `ButtonVariant`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:186` `Button`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:187` `ButtonColor`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:188` `ButtonVariant`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:214` `Box`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:224` `Box`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:237` `Typography`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:247` `Typography`

### `mui.material.Box`

- `src/jsMain/kotlin/com/gvea/par/common/components/EmployeeDetails.kt:5`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanContents.kt:5`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanDetails.kt:5`
- `src/jsMain/kotlin/com/gvea/par/myparview/MyParView.kt:4`
- `src/jsMain/kotlin/com/gvea/par/myparview/ObjectiveYearSelector.kt:15`
- `src/jsMain/kotlin/com/gvea/par/myparview/ParListSelector.kt:5`
- `src/jsMain/kotlin/com/gvea/par/myparview/ReviewListContent.kt:9`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCard.kt:7`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCardRow.kt:3`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsContents.kt:7`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsInputCardRow.kt:12`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectivesDetails.kt:9`
- `src/jsMain/kotlin/com/gvea/par/pardetailsview/ReviewDetailsContents.kt:17`

### `mui.material.Button`

- `src/jsMain/kotlin/com/gvea/par/myparview/DevelopmentPlanCard.kt:9`
- `src/jsMain/kotlin/com/gvea/par/myparview/ObjectiveCard.kt:9`
- `src/jsMain/kotlin/com/gvea/par/myparview/ReviewListContent.kt:10`

### `mui.material.ButtonVariant`

- `src/jsMain/kotlin/com/gvea/par/myparview/DevelopmentPlanCard.kt:10`
- `src/jsMain/kotlin/com/gvea/par/myparview/ObjectiveCard.kt:10`

### `mui.material.Card`

- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanDetails.kt:6`
- `src/jsMain/kotlin/com/gvea/par/myparview/DevelopmentPlanCard.kt:11`
- `src/jsMain/kotlin/com/gvea/par/myparview/ObjectiveCard.kt:11`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCard.kt:8`

### `mui.material.CardContent`

- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanDetails.kt:7`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCard.kt:9`

### `mui.material.CircularProgress`

- `src/jsMain/kotlin/com/gvea/par/common/components/LoadingIndicator.kt:3`

### `mui.material.Divider`

- `src/jsMain/kotlin/com/gvea/par/common/components/EmployeeDetails.kt:6`

### `mui.material.DividerVariant`

- `src/jsMain/kotlin/com/gvea/par/common/components/EmployeeDetails.kt:7`

### `mui.material.Typography`

- `src/jsMain/kotlin/com/gvea/par/common/components/EmployeeDetails.kt:8`
- `src/jsMain/kotlin/com/gvea/par/common/components/SectionHeader.kt:4`
- `src/jsMain/kotlin/com/gvea/par/common/components/TitleComponent.kt:3`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanContents.kt:6`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanDetails.kt:8`
- `src/jsMain/kotlin/com/gvea/par/myparview/DevelopmentPlanCard.kt:12`
- `src/jsMain/kotlin/com/gvea/par/myparview/MyParView.kt:5`
- `src/jsMain/kotlin/com/gvea/par/myparview/ObjectiveCard.kt:12`
- `src/jsMain/kotlin/com/gvea/par/myparview/ReviewListContent.kt:11`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCard.kt:10`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCardRow.kt:4`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsInputCardRow.kt:13`
- `src/jsMain/kotlin/com/gvea/par/pardetailsview/PerformanceAppraisalOverallRatingDetails.kt:12`

### `mui.material.styles.TypographyVariant`

- `src/jsMain/kotlin/com/gvea/par/common/components/EmployeeDetails.kt:9`
- `src/jsMain/kotlin/com/gvea/par/common/components/SectionHeader.kt:5`
- `src/jsMain/kotlin/com/gvea/par/common/components/TitleComponent.kt:4`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanContents.kt:7`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanDetails.kt:9`
- `src/jsMain/kotlin/com/gvea/par/myparview/DevelopmentPlanCard.kt:13`
- `src/jsMain/kotlin/com/gvea/par/myparview/MyParView.kt:6`
- `src/jsMain/kotlin/com/gvea/par/myparview/ObjectiveCard.kt:13`
- `src/jsMain/kotlin/com/gvea/par/myparview/ReviewListContent.kt:12`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCard.kt:11`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCardRow.kt:5`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsInputCardRow.kt:14`
- `src/jsMain/kotlin/com/gvea/par/pardetailsview/PerformanceAppraisalOverallRatingDetails.kt:13`

### `mui.system.Box`

- `src/jsMain/kotlin/com/gvea/par/common/components/StandardPageLayout.kt:3`

### `mui.system.sx`

- `src/jsMain/kotlin/com/gvea/par/common/components/EmployeeDetails.kt:10`
- `src/jsMain/kotlin/com/gvea/par/common/components/StandardPageLayout.kt:4`
- `src/jsMain/kotlin/com/gvea/par/common/components/TitleComponent.kt:5`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:19`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanContents.kt:8`
- `src/jsMain/kotlin/com/gvea/par/developmentplanview/DevelopmentPlanDetails.kt:10`
- `src/jsMain/kotlin/com/gvea/par/myparview/DevelopmentPlanCard.kt:14`
- `src/jsMain/kotlin/com/gvea/par/myparview/MyParView.kt:7`
- `src/jsMain/kotlin/com/gvea/par/myparview/ObjectiveCard.kt:14`
- `src/jsMain/kotlin/com/gvea/par/myparview/ParListSelector.kt:6`
- `src/jsMain/kotlin/com/gvea/par/myparview/ReviewListContent.kt:13`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCard.kt:12`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsCardRow.kt:6`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsContents.kt:8`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectiveDetailsInputCardRow.kt:15`
- `src/jsMain/kotlin/com/gvea/par/objectivesdetailsview/ObjectivesDetails.kt:10`
- `src/jsMain/kotlin/com/gvea/par/pardetailsview/PerformanceAppraisalOverallRatingDetails.kt:14`

### `mui.types.PropsWithComponent`

- `src/jsMain/kotlin/com/gvea/par/common/components/PropsWithComponentLinkSupport.kt:6` `import mui.types.PropsWithComponent`
- `src/jsMain/kotlin/com/gvea/par/common/components/PropsWithComponentLinkSupport.kt:10` `var PropsWithComponent.to`
- `src/jsMain/kotlin/com/gvea/par/common/components/PropsWithComponentLinkSupport.kt:13` `var PropsWithComponent.params`

## Icon Call Sites From `TopNavBar.kt`

- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:97` `Person.create()`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:127` `RateReview.create()`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:162` `AdminPanelSettings.create()`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:189` `Help.create()`
- `src/jsMain/kotlin/com/gvea/par/common/components/TopNavBar.kt:231` `Person { ... }`

## Boundary Notes

- No `mui.base` imports were found.
- No `mui.lab` imports were found.
- No direct `@mui/*` string reference was found outside `src/jsMain/kotlin/com/gvea/par/common/mui/ThemeWrapper.kt:2`.
