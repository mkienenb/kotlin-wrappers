@file:JsModule("@mui/system/Box")

package mui.system

import mui.types.PropsWithComponent

external interface BoxProps :
    react.PropsWithChildren,
    react.dom.html.HTMLAttributes<web.html.HTMLDivElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
}

@JsName("default")
external val Box: react.FC<BoxProps>
