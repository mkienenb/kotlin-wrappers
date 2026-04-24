package mui7.proof

import mui.types.PropsWithComponent

var PropsWithComponent.to: String?
    get() = asDynamic().to as String?
    set(value) {
        asDynamic().to = value
    }
