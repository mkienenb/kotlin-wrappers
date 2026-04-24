@file:JsModule("@mui/material/Card")

package mui.material

import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface CardProps :
    react.PropsWithChildren,
    react.dom.html.HTMLAttributes<web.html.HTMLDivElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
    var raised: Boolean?
}

@JsName("default")
external val Card: react.FC<CardProps>
