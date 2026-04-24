package mui.types

external interface PropsWithComponent : react.Props {
    var component: react.ElementType<*>?
}
