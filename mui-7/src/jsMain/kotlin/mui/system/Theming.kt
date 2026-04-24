@file:JsModule("@mui/system/createTheme/createTheme")

package mui.system

external interface ThemeOptions {
    var breakpoints: dynamic
    var components: dynamic
    var direction: String?
    var mixins: dynamic
    var palette: dynamic
    var shadows: dynamic
    var shape: dynamic
    var spacing: dynamic
    var transitions: dynamic
    var typography: dynamic
    var unstable_sxConfig: dynamic
    var zIndex: dynamic
}

external interface Theme {
    var breakpoints: dynamic
    var components: dynamic
    var direction: String?
    var mixins: dynamic
    var palette: dynamic
    var shadows: dynamic
    var shape: dynamic
    var spacing: dynamic
    var transitions: dynamic
    var typography: dynamic
    var unstable_sxConfig: dynamic
    var zIndex: dynamic
}

@JsName("default")
external fun createTheme(
    options: ThemeOptions? = definedExternally,
    vararg args: Any,
): Theme
