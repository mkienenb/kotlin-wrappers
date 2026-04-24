@file:JsModule("@mui/system/ThemeProvider")

package mui.system

external interface ThemeProviderProps : react.PropsWithChildren {
    override var children: react.ReactNode?
    var theme: dynamic
}

@JsName("default")
external val ThemeProvider: react.FC<ThemeProviderProps>
