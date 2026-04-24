package mui7.proof

import mui.material.*
import mui.system.sx
import react.FC
import react.Props
import mui.material.styles.*
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.px

val DetailContentProof = FC<Props> {
    Box {
        sx {
            display = Display.flex
            flexDirection = FlexDirection.column
            gap = 16.px
            padding = 20.px
        }

        Card {
            CardContent {
                Typography {
                    variant = TypographyVariant.h4
                    +"Detail content proof"
                }

                Divider {
                    variant = DividerVariant.middle

                    sx {
                        marginBottom = 12.px
                        marginTop = 12.px
                    }
                }

                Typography {
                    variant = TypographyVariant.body1
                    +"Cards, typography, and divider composition compile together in the standalone dependency."
                }

                Typography {
                    variant = TypographyVariant.caption
                    +"The scope stays intentionally narrow and consumer-focused."
                }
            }
        }

        Box {
            sx {
                alignItems = AlignItems.center
                display = Display.flex
                gap = 12.px
            }

            CircularProgress {
                size = 28
            }

            Typography {
                variant = TypographyVariant.body2
                +"Loading proof"
            }
        }
    }
}
