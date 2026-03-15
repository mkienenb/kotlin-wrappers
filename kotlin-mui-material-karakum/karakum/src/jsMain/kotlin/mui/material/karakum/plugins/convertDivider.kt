package mui.material.karakum.plugins

import arrow.core.raise.nullable
import io.github.sgrishchenko.karakum.extension.createPlugin
import typescript.*

val convertDivider = createPlugin { node, _, render ->
    nullable {
        if (isVariableDeclaration(node)) {
            val name = node.name
            ensure(isIdentifier(name))
            if (name.text == "Divider") {
                val type = node.type
                ensureNotNull(type)
                ensure(isTypeReferenceNode(type))
                val typeName = type.typeName
                ensure(isIdentifier(typeName))
                ensure(typeName.text == "OverridableComponent")

                """
                typealias Orientation = mui.base.Orientation

                sealed external interface DividerTextAlign {
                    companion object {
                        @JsValue("center")
                        val center: DividerTextAlign
                        @JsValue("right")
                        val right: DividerTextAlign
                        @JsValue("left")
                        val left: DividerTextAlign
                    }
                }

                sealed external interface DividerClasses {
                    /** Styles applied to the root element. */
                    val root: web.cssom.ClassName

                    /** Styles applied to the root element if `absolute={true}`. */
                    val absolute: web.cssom.ClassName

                    /** Styles applied to the root element if `variant="inset"`. */
                    val inset: web.cssom.ClassName

                    /** Styles applied to the root element if `variant="fullWidth"`. */
                    val fullWidth: web.cssom.ClassName

                    /** Styles applied to the root element if `light={true}`.
                     * @deprecated
                     */
                    val light: web.cssom.ClassName

                    /** Styles applied to the root element if `variant="middle"`. */
                    val middle: web.cssom.ClassName

                    /** Styles applied to the root element if `orientation="vertical"`. */
                    val vertical: web.cssom.ClassName

                    /** Styles applied to the root element if `flexItem={true}`. */
                    val flexItem: web.cssom.ClassName

                    /** Styles applied to the root element if divider have text. */
                    val withChildren: web.cssom.ClassName

                    /** Styles applied to the root element if divider have text and `orientation="vertical"`.
                     * @deprecated Combine the [.MuiDivider-withChildren](/material-ui/api/divider/#divider-classes-withChildren) and [.MuiDivider-vertical](/material-ui/api/divider/#divider-classes-vertical) classes instead.
                     */
                    val withChildrenVertical: web.cssom.ClassName

                    /** Styles applied to the root element if `textAlign="right" orientation="horizontal"`. */
                    val textAlignRight: web.cssom.ClassName

                    /** Styles applied to the root element if `textAlign="left" orientation="horizontal"`. */
                    val textAlignLeft: web.cssom.ClassName

                    /** Styles applied to the span children element if `orientation="horizontal"`. */
                    val wrapper: web.cssom.ClassName

                    /** Styles applied to the span children element if `orientation="vertical"`. */
                    val wrapperVertical: web.cssom.ClassName
                }

                external val dividerClasses: DividerClasses

                external interface DividerProps :
                    DividerOwnProps,
                    react.dom.html.HTMLAttributes<web.html.HTMLHRElement>,
                    mui.types.PropsWithComponent


                external interface DividerOwnProps :
                    react.PropsWithChildren,
                    mui.system.PropsWithSx {
                    /**
                     * The content of the component.
                     */
                    override var children: react.ReactNode?

                    /**
                     * Override or extend the styles applied to the component.
                     */
                    var classes: DividerClasses?

                    /**
                     * Absolutely position the element.
                     * @default false
                     */
                    var absolute: Boolean?

                    /**
                     * If `true`, a vertical divider will have the correct height specified in px.
                     * @default false
                     */
                    var flexItem: Boolean?

                    /**
                     * If `true`, the divider will have a lighter color.
                     * @default false
                     */
                    var light: Boolean?

                    /**
                     * The component orientation.
                     * @default 'horizontal'
                     */
                    var orientation: Orientation?

                    /**
                     * The system prop that allows defining system overrides as well as additional CSS styles.
                     */
                    override var sx: mui.system.SxProps<mui.material.styles.Theme>?

                    /**
                     * The text alignment.
                     * @default 'center'
                     */
                    var textAlign: DividerTextAlign?

                    /**
                     * The variant to use.
                     * @default 'fullWidth'
                     */
                    var variant: DividerVariant?
                }

                /**
                 *
                 * Demos:
                 *
                 * - [Dividers](https://mui.com/material-ui/react-divider/)
                 *
                 * API:
                 *
                 * - [Divider API](https://mui.com/material-ui/api/divider/)
                 */
                @JsName("default")
                external val Divider: react.FC<DividerProps>
                """.trimIndent()
            } else {
                null
            }
        } else if (isInterfaceDeclaration(node)) {
            val name = node.name
            ensure(isIdentifier(name))
            if (name.text == "DividerProps") {
                ""
            } else if (name.text == "DividerOwnProps") {
                ""
            } else if (name.text == "DividerPropsVariantOverrides") {
                "external interface DividerPropsVariantOverrides"
            } else {
                null
            }
        } else if (isTypeAliasDeclaration(node)) {
            val name = node.name
            ensure(isIdentifier(name))
            if (name.text == "DividerProps") {
                ""
            } else {
                null
            }
        } else {
            null
        }
    }
}
