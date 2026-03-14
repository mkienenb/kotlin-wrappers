package mui.material.karakum.plugins

import io.github.sgrishchenko.karakum.extension.Context
import io.github.sgrishchenko.karakum.extension.GeneratedFile
import io.github.sgrishchenko.karakum.extension.Plugin
import io.github.sgrishchenko.karakum.extension.Render
import io.github.sgrishchenko.karakum.structure.derived.DerivedDeclaration
import io.github.sgrishchenko.karakum.structure.derived.generateDerivedDeclarations
import js.array.ReadonlyArray
import typescript.Node

class TypographyAlignPlugin : Plugin {
    override fun setup(context: Context) = Unit

    override fun traverse(node: Node, context: Context) = Unit

    override fun render(node: Node, context: Context, next: Render<Node>): String? = null

    override fun generate(context: Context, render: Render<Node>): ReadonlyArray<GeneratedFile> {
        return generateDerivedDeclarations(
            arrayOf(
                DerivedDeclaration(
                    sourceFileName = "Typography/Typography.d.ts",
                    namespace = null,
                    fileName = "Typography.ext.kt",
                    body = """
                        import seskar.js.JsValue

                        sealed external interface TypographyAlign {
                            companion object {
                                @JsValue("inherit")
                                val inherit: TypographyAlign
                                @JsValue("left")
                                val left: TypographyAlign
                                @JsValue("center")
                                val center: TypographyAlign
                                @JsValue("right")
                                val right: TypographyAlign
                                @JsValue("justify")
                                val justify: TypographyAlign
                            }
                        }
                    """.trimIndent(),
                )
            ),
            context
        )
    }
}
