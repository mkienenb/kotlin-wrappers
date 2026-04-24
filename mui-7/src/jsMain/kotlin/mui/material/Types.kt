package mui.material

import js.reflect.unsafeCast

private fun <T : Any> literal(value: String): T =
    unsafeCast(value)

sealed external interface ButtonColor {
    companion object
}

val ButtonColor.Companion.inherit: ButtonColor
    get() = literal("inherit")

val ButtonColor.Companion.primary: ButtonColor
    get() = literal("primary")

val ButtonColor.Companion.secondary: ButtonColor
    get() = literal("secondary")

val ButtonColor.Companion.success: ButtonColor
    get() = literal("success")

val ButtonColor.Companion.error: ButtonColor
    get() = literal("error")

val ButtonColor.Companion.info: ButtonColor
    get() = literal("info")

val ButtonColor.Companion.warning: ButtonColor
    get() = literal("warning")

sealed external interface ButtonVariant {
    companion object
}

val ButtonVariant.Companion.text: ButtonVariant
    get() = literal("text")

val ButtonVariant.Companion.outlined: ButtonVariant
    get() = literal("outlined")

val ButtonVariant.Companion.contained: ButtonVariant
    get() = literal("contained")

sealed external interface AppBarColor {
    companion object
}

val AppBarColor.Companion.inherit: AppBarColor
    get() = literal("inherit")

val AppBarColor.Companion.primary: AppBarColor
    get() = literal("primary")

val AppBarColor.Companion.secondary: AppBarColor
    get() = literal("secondary")

val AppBarColor.Companion.default: AppBarColor
    get() = literal("default")

val AppBarColor.Companion.transparent: AppBarColor
    get() = literal("transparent")

val AppBarColor.Companion.error: AppBarColor
    get() = literal("error")

val AppBarColor.Companion.info: AppBarColor
    get() = literal("info")

val AppBarColor.Companion.success: AppBarColor
    get() = literal("success")

val AppBarColor.Companion.warning: AppBarColor
    get() = literal("warning")

sealed external interface AppBarPosition {
    companion object
}

val AppBarPosition.Companion.fixed: AppBarPosition
    get() = literal("fixed")

val AppBarPosition.Companion.absolute: AppBarPosition
    get() = literal("absolute")

val AppBarPosition.Companion.sticky: AppBarPosition
    get() = literal("sticky")

val AppBarPosition.Companion.static: AppBarPosition
    get() = literal("static")

val AppBarPosition.Companion.relative: AppBarPosition
    get() = literal("relative")

sealed external interface ToolbarVariant {
    companion object
}

val ToolbarVariant.Companion.regular: ToolbarVariant
    get() = literal("regular")

val ToolbarVariant.Companion.dense: ToolbarVariant
    get() = literal("dense")

sealed external interface DividerVariant {
    companion object
}

val DividerVariant.Companion.fullWidth: DividerVariant
    get() = literal("fullWidth")

val DividerVariant.Companion.inset: DividerVariant
    get() = literal("inset")

val DividerVariant.Companion.middle: DividerVariant
    get() = literal("middle")

sealed external interface Orientation {
    companion object
}

val Orientation.Companion.horizontal: Orientation
    get() = literal("horizontal")

val Orientation.Companion.vertical: Orientation
    get() = literal("vertical")

sealed external interface DividerTextAlign {
    companion object
}

val DividerTextAlign.Companion.center: DividerTextAlign
    get() = literal("center")

val DividerTextAlign.Companion.left: DividerTextAlign
    get() = literal("left")

val DividerTextAlign.Companion.right: DividerTextAlign
    get() = literal("right")

sealed external interface CircularProgressColor {
    companion object
}

val CircularProgressColor.Companion.inherit: CircularProgressColor
    get() = literal("inherit")

val CircularProgressColor.Companion.primary: CircularProgressColor
    get() = literal("primary")

val CircularProgressColor.Companion.secondary: CircularProgressColor
    get() = literal("secondary")

val CircularProgressColor.Companion.success: CircularProgressColor
    get() = literal("success")

val CircularProgressColor.Companion.error: CircularProgressColor
    get() = literal("error")

val CircularProgressColor.Companion.info: CircularProgressColor
    get() = literal("info")

val CircularProgressColor.Companion.warning: CircularProgressColor
    get() = literal("warning")

sealed external interface CircularProgressVariant {
    companion object
}

val CircularProgressVariant.Companion.determinate: CircularProgressVariant
    get() = literal("determinate")

val CircularProgressVariant.Companion.indeterminate: CircularProgressVariant
    get() = literal("indeterminate")
