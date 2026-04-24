package mui7.proof

import react.FC
import react.PropsWithChildren

external interface ProofLinkProps : PropsWithChildren {
    override var children: react.ReactNode?
    var to: String?
}

val ProofLink = FC<ProofLinkProps> { props ->
    +("link:${props.to ?: "/"}")
}
