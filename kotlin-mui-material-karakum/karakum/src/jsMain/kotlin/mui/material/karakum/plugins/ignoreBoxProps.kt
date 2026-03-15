package mui.material.karakum.plugins

import arrow.core.raise.nullable
import io.github.sgrishchenko.karakum.extension.createPlugin
import typescript.*

val ignoreBoxProps = createPlugin { node, _, _ ->
    nullable {
        ensure(isTypeAliasDeclaration(node))
        val name = node.name
        ensure(isIdentifier(name))
        ensure(name.text == "BoxProps")

        ""
    }
}
