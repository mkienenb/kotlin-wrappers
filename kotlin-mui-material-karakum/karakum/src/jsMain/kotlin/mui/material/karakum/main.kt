package mui.material.karakum

import io.github.sgrishchenko.karakum.generate
import io.github.sgrishchenko.karakum.util.manyOf
import io.github.sgrishchenko.karakum.util.ruleOf
import js.array.ReadonlyArray
import js.objects.recordOf
import js.objects.unsafeJso
import mui.material.karakum.plugins.*

suspend fun main(args: ReadonlyArray<String>) {
    generate(args) {
        libraryName = "@mui/material"

        plugins = manyOf(
            renameConflictingNames,
            convertExoticReactTypeReferences,
            convertReactQualifiedName,
            convertUtilityTypes,
            convertBox,
            ignoreBoxProps,
            convertDivider,
            DividerVariantPlugin(),
            convertTypography,
            TypographyClassesPlugin(),
            TypographyAlignPlugin(),
        )

        input = manyOf(
            "index.d.ts",
            "*/index.d.ts",
            "*/Box.d.ts",
            "*/Divider.d.ts",
            "*/Typography.d.ts",
            "*/typographyClasses.d.ts",
            "ButtonBase/TouchRipple.d.ts",
        )
        ignoreInput = manyOf(
            "esm/**/*.d.ts",
            "node_modules/**/*.d.ts",
        )
        ignoreOutput = manyOf(
            "**/index.kt",
            "**/module.kt",
            "**/dividerClasses.kt",
            "**/typographyClasses.kt",
            "**/TypographyOwnProps.kt",
            "**/TypographyProps.kt",
            "**/TypographyPropsVariantOverrides.kt",
            "**/TypographyTypeMap.kt",
            "**/TypographyTypeMapProps.kt",
            "**/TypographyClasses.kt",
            "**/TypographyClassKey.kt",
        )
        isolatedOutputPackage = true
        moduleNameMapper = recordOf(
            "Box/Box" to "Box",
            "Divider/Divider" to "Divider",
            "Divider/dividerClasses" to "Divider.classes",
            "Typography/Typography" to "Typography",
            "Typography/typographyClasses" to "Typography.classes",
        )
        packageNameMapper = recordOf(
            "index/" to "/",
            "Box/" to "/",
            "Divider/Divider/" to "/",
            "DividerVariant/" to "/",
            "Divider/dividerClasses/" to "/",
            "Divider.classes/" to "/",
            "Typography/Typography/" to "/",
            "Typography/typographyClasses/" to "/",
            "Typography.classes/" to "/",

            "experimental_extendTheme/" to "experimental_extend_theme/",

            "accordionActionsClasses/" to "accordionActions_classes/",
            "accordionClasses/" to "accordion_classes/",
            "accordionDetailsClasses/" to "accordionDetails_classes/",
            "accordionSummaryClasses/" to "accordionSummary_classes/",
            "alertClasses/" to "alert_classes/",
            "alertTitleClasses/" to "alertTitle_classes/",
            "appBarClasses/" to "appBar_classes/",
            "autocompleteClasses/" to "autocomplete_classes/",
            "avatarClasses/" to "avatar_classes/",
            "avatarGroupClasses/" to "avatarGroup_classes/",
            "backdropClasses/" to "backdrop_classes/",
            "badgeClasses/" to "badge_classes/",
            "bottomNavigationActionClasses/" to "bottomNavigationAction_classes/",
            "bottomNavigationClasses/" to "bottomNavigation_classes/",
            "boxClasses/" to "box_classes/",
            "breadcrumbsClasses/" to "breadcrumbs_classes/",
            "buttonBaseClasses/" to "buttonBase_classes/",
            "buttonClasses/" to "button_classes/",
            "buttonGroupClasses/" to "buttonGroup_classes/",
            "cardActionAreaClasses/" to "cardActionArea_classes/",
            "cardActionsClasses/" to "cardActions_classes/",
            "cardClasses/" to "card_classes/",
            "cardContentClasses/" to "cardContent_classes/",
            "cardHeaderClasses/" to "cardHeader_classes/",
            "cardMediaClasses/" to "cardMedia_classes/",
            "checkboxClasses/" to "checkbox_classes/",
            "chipClasses/" to "chip_classes/",
            "circularProgressClasses/" to "circularProgress_classes/",
            "collapseClasses/" to "collapse_classes/",
            "containerClasses/" to "container_classes/",
            "dialogActionsClasses/" to "dialogActions_classes/",
            "dialogClasses/" to "dialog_classes/",
            "dialogContentClasses/" to "dialogContent_classes/",
            "dialogContentTextClasses/" to "dialogContentText_classes/",
            "dialogTitleClasses/" to "dialogTitle_classes/",
            "drawerClasses/" to "drawer_classes/",
            "fabClasses/" to "fab_classes/",
            "filledInputClasses/" to "filledInput_classes/",
            "formControlClasses/" to "formControl_classes/",
            "formControlLabelClasses/" to "formControlLabel_classes/",
            "formGroupClasses/" to "formGroup_classes/",
            "formHelperTextClasses/" to "formHelperText_classes/",
            "formLabelClasses/" to "formLabel_classes/",
            "grid2Classes/" to "grid2_classes/",
            "gridClasses/" to "grid_classes/",
            "iconButtonClasses/" to "iconButton_classes/",
            "iconClasses/" to "icon_classes/",
            "imageListClasses/" to "imageList_classes/",
            "imageListItemBarClasses/" to "imageListItemBar_classes/",
            "imageListItemClasses/" to "imageListItem_classes/",
            "inputAdornmentClasses/" to "inputAdornment_classes/",
            "inputBaseClasses/" to "inputBase_classes/",
            "inputClasses/" to "input_classes/",
            "inputLabelClasses/" to "inputLabel_classes/",
            "linearProgressClasses/" to "linearProgress_classes/",
            "linkClasses/" to "link_classes/",
            "listClasses/" to "list_classes/",
            "listItemAvatarClasses/" to "listItemAvatar_classes/",
            "listItemButtonClasses/" to "listItemButton_classes/",
            "listItemClasses/" to "listItem_classes/",
            "listItemIconClasses/" to "listItemIcon_classes/",
            "listItemSecondaryActionClasses/" to "listItemSecondaryAction_classes/",
            "listItemTextClasses/" to "listItemText_classes/",
            "listSubheaderClasses/" to "listSubheader_classes/",
            "menuClasses/" to "menu_classes/",
            "menuItemClasses/" to "menuItem_classes/",
            "mobileStepperClasses/" to "mobileStepper_classes/",
            "modalClasses/" to "modal_classes/",
            "nativeSelectClasses/" to "nativeSelect_classes/",
            "outlinedInputClasses/" to "outlinedInput_classes/",
            "paginationClasses/" to "pagination_classes/",
            "paginationItemClasses/" to "paginationItem_classes/",
            "paperClasses/" to "paper_classes/",
            "popoverClasses/" to "popover_classes/",
            "popperClasses/" to "popper_classes/",
            "radioClasses/" to "radio_classes/",
            "radioGroupClasses/" to "radioGroup_classes/",
            "ratingClasses/" to "rating_classes/",
            "scopedCssBaselineClasses/" to "scopedCssBaseline_classes/",
            "selectClasses/" to "select_classes/",
            "skeletonClasses/" to "skeleton_classes/",
            "sliderClasses/" to "slider_classes/",
            "snackbarClasses/" to "snackbar_classes/",
            "snackbarContentClasses/" to "snackbarContent_classes/",
            "speedDialActionClasses/" to "speedDialAction_classes/",
            "speedDialClasses/" to "speedDial_classes/",
            "speedDialIconClasses/" to "speedDialIcon_classes/",
            "stackClasses/" to "stack_classes/",
            "stepButtonClasses/" to "stepButton_classes/",
            "stepClasses/" to "step_classes/",
            "stepConnectorClasses/" to "stepConnector_classes/",
            "stepContentClasses/" to "stepContent_classes/",
            "stepIconClasses/" to "stepIcon_classes/",
            "stepLabelClasses/" to "stepLabel_classes/",
            "stepperClasses/" to "stepper_classes/",
            "svgIconClasses/" to "svgIcon_classes/",
            "switchBaseClasses/" to "switchBase_classes/",
            "switchClasses/" to "switch_classes/",
            "tabClasses/" to "tab_classes/",
            "tableBodyClasses/" to "tableBody_classes/",
            "tableCellClasses/" to "tableCell_classes/",
            "tableClasses/" to "table_classes/",
            "tableContainerClasses/" to "tableContainer_classes/",
            "tableFooterClasses/" to "tableFooter_classes/",
            "tableHeadClasses/" to "tableHead_classes/",
            "tablePaginationClasses/" to "tablePagination_classes/",
            "tableRowClasses/" to "tableRow_classes/",
            "tableSortLabelClasses/" to "tableSortLabel_classes/",
            "tabsClasses/" to "tabs_classes/",
            "tabScrollButtonClasses/" to "tabScrollButton_classes/",
            "textFieldClasses/" to "textField_classes/",
            "toggleButtonClasses/" to "toggleButton_classes/",
            "toggleButtonGroupClasses/" to "toggleButtonGroup_classes/",
            "toolbarClasses/" to "toolbar_classes/",
            "tooltipClasses/" to "tooltip_classes/",
            "touchRippleClasses/" to "touchRipple_classes/",
            "typographyClasses/" to "typography_classes/",
        )
        importMapper = recordOf(
            "react" to ruleOf(
                ".+" to "react."
            ),
            "@mui/material" to ruleOf(
                ".+" to "mui.material."
            ),
            "@mui/system" to ruleOf(
                ".+" to "mui.system."
            ),
            "@mui/base" to ruleOf(
                ".+" to "mui.base."
            ),
            "csstype" to ruleOf(
                ".+" to "web.cssom."
            ),
            "@mui/types" to ruleOf(
                ".+" to ""
            ),
            "../styles/Theme" to ruleOf(
                "default" to "mui.material.styles.Theme"
            ),
            "../styles" to ruleOf(
                "Theme" to "mui.material.styles.Theme"
            ),
            ".." to ruleOf(
                "Theme" to "mui.material.styles.Theme",
                "OverridableComponent" to "",
                "OverrideProps" to "",
                "Orientation" to "Orientation",
            ),
            "./dividerClasses" to ruleOf(
                "default" to "",
                "DividerClasses" to "",
            ),
            "../dividerClasses" to ruleOf(
                "default" to "",
                "DividerClasses" to "",
            ),
            "./typographyClasses" to ruleOf(
                "default" to "",
                "TypographyClasses" to "",
            ),
            "../typographyClasses" to ruleOf(
                "default" to "",
                "TypographyClasses" to "",
            ),
            "../../styles" to ruleOf(
                "Theme" to "mui.material.styles.Theme"
            ),
            "../../system" to ruleOf(
                "SxProps" to "mui.system.SxProps"
            ),
            "../types" to ruleOf(
                "Orientation" to "Orientation",
            ),
            "web.cssom" to ruleOf(
                "ClassName" to "web.cssom.ClassName"
            ),
            "../styles" to ruleOf(
                ".+" to ""
            ),
        )
        compilerOptions = unsafeJso {
            lib = arrayOf(
                "lib.esnext.d.ts",
                "lib.dom.d.ts",
            )
        }
    }
}
