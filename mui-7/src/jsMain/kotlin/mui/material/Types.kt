package mui.material

import seskar.js.JsValue

sealed external interface ButtonColor {
    companion object {
        @JsValue("inherit")
        val inherit: ButtonColor

        @JsValue("primary")
        val primary: ButtonColor

        @JsValue("secondary")
        val secondary: ButtonColor

        @JsValue("success")
        val success: ButtonColor

        @JsValue("error")
        val error: ButtonColor

        @JsValue("info")
        val info: ButtonColor

        @JsValue("warning")
        val warning: ButtonColor
    }
}

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

sealed external interface AppBarColor {
    companion object {
        @JsValue("inherit")
        val inherit: AppBarColor

        @JsValue("primary")
        val primary: AppBarColor

        @JsValue("secondary")
        val secondary: AppBarColor

        @JsValue("default")
        val default: AppBarColor

        @JsValue("transparent")
        val transparent: AppBarColor

        @JsValue("error")
        val error: AppBarColor

        @JsValue("info")
        val info: AppBarColor

        @JsValue("success")
        val success: AppBarColor

        @JsValue("warning")
        val warning: AppBarColor
    }
}

sealed external interface AppBarPosition {
    companion object {
        @JsValue("fixed")
        val fixed: AppBarPosition

        @JsValue("absolute")
        val absolute: AppBarPosition

        @JsValue("sticky")
        val sticky: AppBarPosition

        @JsValue("static")
        val static: AppBarPosition

        @JsValue("relative")
        val relative: AppBarPosition
    }
}

sealed external interface ToolbarVariant {
    companion object {
        @JsValue("regular")
        val regular: ToolbarVariant

        @JsValue("dense")
        val dense: ToolbarVariant
    }
}

sealed external interface DividerVariant {
    companion object {
        @JsValue("fullWidth")
        val fullWidth: DividerVariant

        @JsValue("inset")
        val inset: DividerVariant

        @JsValue("middle")
        val middle: DividerVariant
    }
}

sealed external interface Orientation {
    companion object {
        @JsValue("horizontal")
        val horizontal: Orientation

        @JsValue("vertical")
        val vertical: Orientation
    }
}

sealed external interface DividerTextAlign {
    companion object {
        @JsValue("center")
        val center: DividerTextAlign

        @JsValue("left")
        val left: DividerTextAlign

        @JsValue("right")
        val right: DividerTextAlign
    }
}

sealed external interface CircularProgressColor {
    companion object {
        @JsValue("inherit")
        val inherit: CircularProgressColor

        @JsValue("primary")
        val primary: CircularProgressColor

        @JsValue("secondary")
        val secondary: CircularProgressColor

        @JsValue("success")
        val success: CircularProgressColor

        @JsValue("error")
        val error: CircularProgressColor

        @JsValue("info")
        val info: CircularProgressColor

        @JsValue("warning")
        val warning: CircularProgressColor
    }
}

sealed external interface CircularProgressVariant {
    companion object {
        @JsValue("determinate")
        val determinate: CircularProgressVariant

        @JsValue("indeterminate")
        val indeterminate: CircularProgressVariant
    }
}
