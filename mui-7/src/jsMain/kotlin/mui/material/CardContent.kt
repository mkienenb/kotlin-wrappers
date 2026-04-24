@file:JsModule("@mui/material/CardContent")

package mui.material

import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface CardContentProps :
    react.PropsWithChildren,
    react.dom.html.HTMLAttributes<web.html.HTMLDivElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
}

@JsName("default")
external val CardContent: react.FC<CardContentProps>
