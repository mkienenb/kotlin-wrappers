@file:JsModule("@mui/material/Toolbar")

package mui.material

import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface ToolbarProps :
    react.PropsWithChildren,
    react.dom.html.HTMLAttributes<web.html.HTMLDivElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
    var disableGutters: Boolean?
    var variant: ToolbarVariant?
}

@JsName("default")
external val Toolbar: react.FC<ToolbarProps>
