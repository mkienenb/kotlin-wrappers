@file:JsModule("@mui/material/AppBar")

package mui.material

import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface AppBarProps :
    react.PropsWithChildren,
    react.dom.html.HTMLAttributes<web.html.HTMLDivElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
    var color: AppBarColor?
    var enableColorOnDark: Boolean?
    var position: AppBarPosition?
}

@JsName("default")
external val AppBar: react.FC<AppBarProps>
