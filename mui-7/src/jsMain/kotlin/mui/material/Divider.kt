@file:JsModule("@mui/material/Divider")

package mui.material

import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface DividerProps :
    react.PropsWithChildren,
    react.dom.html.HTMLAttributes<web.html.HTMLHRElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
    var absolute: Boolean?
    var flexItem: Boolean?
    var light: Boolean?
    var orientation: Orientation?
    var textAlign: DividerTextAlign?
    var variant: DividerVariant?
}

@JsName("default")
external val Divider: react.FC<DividerProps>
