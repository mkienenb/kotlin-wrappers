package mui.material.karakum.plugins

import io.github.sgrishchenko.karakum.extension.Context
import io.github.sgrishchenko.karakum.extension.GeneratedFile
import io.github.sgrishchenko.karakum.extension.Plugin
import io.github.sgrishchenko.karakum.extension.Render
import io.github.sgrishchenko.karakum.structure.derived.DerivedDeclaration
import io.github.sgrishchenko.karakum.structure.derived.generateDerivedDeclarations
import js.array.ReadonlyArray
import typescript.Node

class TypographyVariantPlugin : Plugin {
    override fun setup(context: Context) = Unit

    override fun traverse(node: Node, context: Context) = Unit

    override fun render(node: Node, context: Context, next: Render<Node>): String? = null

    override fun generate(context: Context, render: Render<Node>): ReadonlyArray<GeneratedFile> {
        return generateDerivedDeclarations(
            arrayOf(
                DerivedDeclaration(
                    sourceFileName = "styles/TypographyVariant.d.ts",
                    namespace = null,
                    fileName = "TypographyVariant.kt",
                    body = """
                        import seskar.js.JsValue

                        sealed external interface TypographyVariant {
                            companion object {
                                @JsValue("h1")
                                val h1: TypographyVariant
                                @JsValue("h2")
                                val h2: TypographyVariant
                                @JsValue("h3")
                                val h3: TypographyVariant
                                @JsValue("h4")
                                val h4: TypographyVariant
                                @JsValue("h5")
                                val h5: TypographyVariant
                                @JsValue("h6")
                                val h6: TypographyVariant
                                @JsValue("subtitle1")
                                val subtitle1: TypographyVariant
                                @JsValue("subtitle2")
                                val subtitle2: TypographyVariant
                                @JsValue("body1")
                                val body1: TypographyVariant
                                @JsValue("body2")
                                val body2: TypographyVariant
                                @JsValue("caption")
                                val caption: TypographyVariant
                                @JsValue("button")
                                val button: TypographyVariant
                                @JsValue("overline")
                                val overline: TypographyVariant
                            }
                        }
                    """.trimIndent(),
                )
            ),
            context
        )
    }
}
