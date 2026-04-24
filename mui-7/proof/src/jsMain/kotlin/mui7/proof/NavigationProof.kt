package mui7.proof

import mui.icons.material.AdminPanelSettings
import mui.icons.material.Help
import mui.icons.material.Person
import mui.icons.material.RateReview
import mui.material.*
import mui.system.sx
import react.FC
import react.Props
import react.create
import mui.material.styles.*
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.px

val NavigationProof = FC<Props> {
    AppBar {
        position = AppBarPosition.static

        Toolbar {
            sx {
                alignItems = AlignItems.center
                display = Display.flex
                gap = 12.px
                padding = 12.px
            }

            Typography {
                variant = TypographyVariant.h6
                +"Navigation proof"
            }

            Button {
                color = ButtonColor.inherit
                component = ProofLink
                startIcon = Person.create()
                to = "/profile"
                variant = ButtonVariant.text

                +"Profile"
            }

            Button {
                color = ButtonColor.inherit
                component = ProofLink
                startIcon = RateReview.create()
                to = "/reviews"
                variant = ButtonVariant.outlined

                +"Reviews"
            }

            Button {
                color = ButtonColor.inherit
                component = ProofLink
                startIcon = AdminPanelSettings.create()
                to = "/admin"
                variant = ButtonVariant.text

                +"Admin"
            }

            Button {
                color = ButtonColor.inherit
                component = ProofLink
                startIcon = Help.create()
                to = "/help"
                variant = ButtonVariant.text

                +"Help"
            }

            Person {
                titleAccess = "Signed in user"
            }
        }
    }
}
