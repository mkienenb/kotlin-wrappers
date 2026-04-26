package example.csstype

import emotion.react.css
import react.FC
import react.dom.html.ReactHTML.span

val View = FC {
    span {
        css {}
        +"Hello from csstype only"
    }
}
