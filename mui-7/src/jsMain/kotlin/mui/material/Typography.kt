@file:JsModule("@mui/material/Typography")

package mui.material

import mui.material.styles.TypographyVariant
import mui.system.PropsWithSx
import mui.types.PropsWithComponent

external interface TypographyProps :
    react.PropsWithChildren,
    react.dom.html.HTMLAttributes<web.html.HTMLSpanElement>,
    PropsWithComponent,
    PropsWithSx {
    override var children: react.ReactNode?
    var align: String?
    var color: String?
    var gutterBottom: Boolean?
    var noWrap: Boolean?
    var paragraph: Boolean?
    var variant: TypographyVariant?
    var variantMapping: dynamic
}

@JsName("default")
external val Typography: react.FC<TypographyProps>
