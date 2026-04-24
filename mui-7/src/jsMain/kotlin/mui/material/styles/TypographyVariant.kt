package mui.material.styles

import js.reflect.unsafeCast

private fun <T : Any> literal(value: String): T =
    unsafeCast(value)

sealed external interface TypographyVariant {
    companion object
}

val TypographyVariant.Companion.h1: TypographyVariant
    get() = literal("h1")

val TypographyVariant.Companion.h2: TypographyVariant
    get() = literal("h2")

val TypographyVariant.Companion.h3: TypographyVariant
    get() = literal("h3")

val TypographyVariant.Companion.h4: TypographyVariant
    get() = literal("h4")

val TypographyVariant.Companion.h5: TypographyVariant
    get() = literal("h5")

val TypographyVariant.Companion.h6: TypographyVariant
    get() = literal("h6")

val TypographyVariant.Companion.subtitle1: TypographyVariant
    get() = literal("subtitle1")

val TypographyVariant.Companion.subtitle2: TypographyVariant
    get() = literal("subtitle2")

val TypographyVariant.Companion.body1: TypographyVariant
    get() = literal("body1")

val TypographyVariant.Companion.body2: TypographyVariant
    get() = literal("body2")

val TypographyVariant.Companion.caption: TypographyVariant
    get() = literal("caption")

val TypographyVariant.Companion.button: TypographyVariant
    get() = literal("button")

val TypographyVariant.Companion.overline: TypographyVariant
    get() = literal("overline")
