@file:JsModule("@mui/material/CircularProgress")

package mui.material

import mui.system.PropsWithSx
import mui.system.StandardProps

external interface CircularProgressProps :
    StandardProps,
    react.dom.html.HTMLAttributes<web.html.HTMLSpanElement>,
    PropsWithSx {
    var color: CircularProgressColor?
    var disableShrink: Boolean?
    var size: dynamic
    var thickness: Number?
    var value: Number?
    var variant: CircularProgressVariant?
}

@JsName("default")
external val CircularProgress: react.FC<CircularProgressProps>
