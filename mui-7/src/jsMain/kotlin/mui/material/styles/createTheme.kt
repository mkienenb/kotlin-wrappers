@file:JsModule("@mui/material/styles/createTheme")

package mui.material.styles

external interface ThemeOptions : mui.system.ThemeOptions {
    override var components: dynamic
    override var palette: dynamic
    override var typography: dynamic
    var cssVariables: dynamic
    var modularCssLayers: Boolean?
    var unstable_strictMode: Boolean?
}

external interface Theme : mui.system.Theme {
    override var components: dynamic
    override var palette: dynamic
    override var typography: dynamic
    var cssVariables: dynamic
    var unstable_strictMode: Boolean?
}

@JsName("default")
external fun createTheme(
    options: ThemeOptions? = definedExternally,
    vararg args: Any,
): Theme
