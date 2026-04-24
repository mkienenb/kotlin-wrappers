package mui7.proof

import mui.material.Box
import mui.material.styles.ThemeProvider
import mui.system.sx
import react.FC
import react.Props
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.px

val ProofApp = FC<Props> {
    ThemeProvider {
        theme = appTheme

        Box {
            sx {
                display = Display.flex
                flexDirection = FlexDirection.column
                gap = 24.px
                padding = 24.px
            }

            BaselineProof {}
            ThemeAndTypographyProof {}
            NavigationProof {}
            DetailContentProof {}
        }
    }
}
