
@file:JsModule("@mui/material/styles/ThemeProvider")

package mui.material.styles

external interface ThemeProviderProps : react.PropsWithChildren {
    override var children: react.ReactNode?
    var theme: dynamic
}

@JsName("default")
external val ThemeProvider: react.FC<ThemeProviderProps>
