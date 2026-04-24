@file:JsModule("@mui/material/SvgIcon")

package mui.material

import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface SvgIconProps :
    react.PropsWithChildren,
    react.dom.svg.SVGAttributes<web.svg.SVGSVGElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
    var htmlColor: String?
    var inheritViewBox: Boolean?
    var titleAccess: String?
}

@JsName("default")
external val SvgIcon: react.FC<SvgIconProps>
