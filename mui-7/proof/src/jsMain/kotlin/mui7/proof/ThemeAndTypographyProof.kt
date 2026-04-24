package mui7.proof

import mui.material.Box
import mui.material.Typography
import mui.material.styles.ThemeOptions
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import mui.system.sx
import react.FC
import react.Props
import js.objects.unsafeJso
import mui.material.styles.*
import web.cssom.px

internal val appTheme = createTheme(
    unsafeJso<ThemeOptions> {
        palette = unsafeJso<dynamic> {
            primary = unsafeJso<dynamic> {
                main = "#0f766e"
            }
            secondary = unsafeJso<dynamic> {
                main = "#c2410c"
            }
        }
        typography = unsafeJso<dynamic> {
            h3 = unsafeJso<dynamic> {
                fontWeight = 700
            }
            subtitle1 = unsafeJso<dynamic> {
                letterSpacing = "0.08em"
            }
        }
        components = unsafeJso<dynamic> {
            MuiButton = unsafeJso<dynamic> {
                defaultProps = unsafeJso<dynamic> {
                    variant = "outlined"
                }
            }
        }
    }
)

val ThemeAndTypographyProof = FC<Props> {
    ThemeProvider {
        theme = appTheme

        Box {
            sx {
                padding = 20.px
            }

            Typography {
                variant = TypographyVariant.h3
                +"Direct mui.material.styles imports"
            }

            Typography {
                variant = TypographyVariant.subtitle1
                +"createTheme accepts palette, typography, and components overrides via unsafeJso."
            }
        }
    }
}
