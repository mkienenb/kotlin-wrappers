@file:JsModule("@mui/material/Button")

package mui.material

import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface ButtonProps :
    react.PropsWithChildren,
    react.dom.html.ButtonHTMLAttributes<web.html.HTMLButtonElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
    var color: ButtonColor?
    var disableElevation: Boolean?
    var disableFocusRipple: Boolean?
    var endIcon: react.ReactNode?
    var fullWidth: Boolean?
    var href: String?
    var size: String?
    var startIcon: react.ReactNode?
    var variant: ButtonVariant?
}

@JsName("default")
external val Button: react.FC<ButtonProps>
