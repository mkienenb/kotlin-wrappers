package mui.material.karakum.plugins

import arrow.core.raise.nullable
import io.github.sgrishchenko.karakum.extension.createPlugin
import typescript.isIdentifier

val renameConflictingNames = createPlugin { node, _, _ ->
    nullable {
        ensure(isIdentifier(node))
        val text = node.text
        ensure(text.endsWith("Classes") && text[0].isLowerCase())

        "${text}_"
    }
}
