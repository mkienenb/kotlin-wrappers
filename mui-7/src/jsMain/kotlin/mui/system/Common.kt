package mui.system

import react.Props
import react.PropsWithClassName
import react.PropsWithStyle

@Suppress("UNUSED_TYPEALIAS_PARAMETER")
typealias SxProps<T> = react.CSSProperties

external interface PropsWithSx : Props {
    var sx: SxProps<Theme>?
}

external interface StandardProps :
    PropsWithStyle,
    PropsWithClassName
