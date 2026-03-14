package mui.material.karakum.plugins

import arrow.core.raise.nullable
import io.github.sgrishchenko.karakum.extension.createPlugin
import typescript.*

val convertBox = createPlugin { node, _, _ ->
    nullable {
        ensure(isVariableDeclaration(node))
        val name = node.name
        ensure(isIdentifier(name))
        ensure(name.text == "Box")

        val type = node.type
        ensureNotNull(type)
        ensure(isTypeReferenceNode(type))
        val typeName = type.typeName
        ensure(isIdentifier(typeName))
        ensure(typeName.text == "OverridableComponent")

        val typeArguments = type.typeArguments?.asArray()
        ensureNotNull(typeArguments)
        ensure(typeArguments.size == 1)
        val typeArgument = typeArguments.first()
        ensure(isTypeReferenceNode(typeArgument))
        val typeArgumentName = typeArgument.typeName
        ensure(isIdentifier(typeArgumentName))
        ensure(typeArgumentName.text == "BoxTypeMap")

        """
        /**
         *
         * Demos:
         *
         * - [Box](https://mui.com/material-ui/react-box/)
         *
         * API:
         *
         * - [Box API](https://mui.com/material-ui/api/box/)
         */
        @JsModule("@mui/material/Box")
        @JsName("default")
        external val Box: react.FC<mui.system.BoxProps>
        """.trimIndent()
    }
}
