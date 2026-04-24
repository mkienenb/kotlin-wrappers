package mui7.proof

import mui.system.Box
import mui.system.ThemeProvider
import mui.system.ThemeOptions
import mui.system.createTheme
import mui.system.sx
import react.FC
import react.Props
import js.objects.unsafeJso
import web.cssom.Display
import web.cssom.px

private val baselineTheme = createTheme(
    unsafeJso<ThemeOptions> {
        palette = unsafeJso<dynamic> {
            mode = "light"
        }
    }
)

val BaselineProof = FC<Props> {
    ThemeProvider {
        theme = baselineTheme

        Box {
            component = ProofLink
            to = "/baseline"

            sx {
                display = Display.flex
                gap = 12.px
                padding = 16.px
            }

            +"System Box, sx, and PropsWithComponent compatibility"
        }
    }
}
