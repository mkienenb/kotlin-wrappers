package mui.material.karakum.plugins

import io.github.sgrishchenko.karakum.extension.Context
import io.github.sgrishchenko.karakum.extension.GeneratedFile
import io.github.sgrishchenko.karakum.extension.Plugin
import io.github.sgrishchenko.karakum.extension.Render
import io.github.sgrishchenko.karakum.structure.derived.DerivedDeclaration
import io.github.sgrishchenko.karakum.structure.derived.generateDerivedDeclarations
import js.array.ReadonlyArray
import typescript.Node

class DividerVariantPlugin : Plugin {
    override fun setup(context: Context) = Unit

    override fun traverse(node: Node, context: Context) = Unit

    override fun render(node: Node, context: Context, next: Render<Node>): String? = null

    override fun generate(context: Context, render: Render<Node>): ReadonlyArray<GeneratedFile> {
        return generateDerivedDeclarations(
            arrayOf(
                DerivedDeclaration(
                    sourceFileName = "DividerVariant.d.ts",
                    namespace = null,
                    fileName = "DividerVariant.kt",
                    body = """
                        import seskar.js.JsValue

                        sealed external interface DividerVariant {
                            companion object {
                                @JsValue("fullWidth")
                                val fullWidth: DividerVariant
                                @JsValue("inset")
                                val inset: DividerVariant
                                @JsValue("middle")
                                val middle: DividerVariant
                            }
                        }
                    """.trimIndent(),
                )
            ),
            context
        )
    }
}
