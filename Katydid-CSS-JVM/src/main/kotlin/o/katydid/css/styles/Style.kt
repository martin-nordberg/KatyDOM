//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package o.katydid.css.styles

import o.katydid.css.colors.Color
import o.katydid.css.measurements.Length
import o.katydid.css.measurements.Percentage
import o.katydid.css.types.*
import o.katydid.css.types.EBackgroundPositionOption.center

//---------------------------------------------------------------------------------------------------------------------

class Style {

    private val properties = mutableListOf<String>()

    ////

    // TODO: background

    fun backgroundAttachment(value: EBackgroundAttachmentOption) =
        setProperty("background-attachment", value.toCssString())

    fun backgroundColor(value: Color) =
        setProperty("background-color", "$value")

    fun backgroundImage(url: String) =
        setProperty("background-image", "url(\"$url\")")

    fun backgroundImage(value:ENoneOption) =
        setProperty("background-image", value.toCssString())

    fun backgroundPosition(x: EBackgroundPositionOption, y: EBackgroundPositionOption = center) =
        setProperty("background-position", x.toCssString() + " " + y.toCssString())

    fun backgroundPosition(x: Length) =
        setProperty("background-position", "$x center")

    fun backgroundPosition(x: Length, y: Length) =
        setProperty("background-position", "$x $y")

    fun backgroundPosition(x: Percentage) =
        setProperty("background-position", "$x center")

    fun backgroundPosition(x: Percentage, y: Percentage) =
        setProperty("background-position", "$x $y")

    fun backgroundRepeat(value: EBackgroundRepeatOption) =
        setProperty("background-repeat", value.toCssString())

    // TODO: border

    fun borderBottom(width: EBorderWidthOption, style: EBorderStyleOption, color: Color) =
        setProperty("border-bottom", width.toCssString() + " " + style.toCssString() + " $color")

    fun borderBottomColor(value: Color) =
        setProperty("border-bottom-color", "$value")

    fun borderBottomStyle(value: EBorderStyleOption) =
        setProperty("border-bottom-style", value.toCssString())

    fun borderBottomWidth(value: EBorderWidthOption) =
        setProperty("border-bottom-width", value.toCssString())

    fun borderBottomWidth(value: Length) =
        setProperty("border-bottom-width", "$value")

    fun borderCollapse(value: EBorderCollapseOption) =
        setProperty("border-collapse", value.toCssString())

    fun borderColor(top: Color, right: Color = top, bottom: Color = top, left: Color = right) {
        var css = "$top"
        if ( right != top || bottom != top || left != right ) {
            css += " $right"
        }
        if ( bottom != top || left != right ) {
            css += " $bottom"
        }
        if ( left != right ) {
            css += " $left"
        }
        setProperty("border-color", css)
    }

    fun borderLeft(width: EBorderWidthOption, style: EBorderStyleOption, color: Color) =
        setProperty("border-left", width.toCssString() + " " + style.toCssString() + " $color")

    fun borderLeftColor(value: Color) =
        setProperty("border-left-color", "$value")

    fun borderLeftStyle(value: EBorderStyleOption) =
        setProperty("border-left-style", value.toCssString())

    fun borderLeftWidth(value: EBorderWidthOption) =
        setProperty("border-left-width", value.toCssString())

    fun borderLeftWidth(value: Length) =
        setProperty("border-left-width", "$value")

    fun borderRight(width: EBorderWidthOption, style: EBorderStyleOption, color: Color) =
        setProperty("border-right", width.toCssString() + " " + style.toCssString() + " $color")

    fun borderRightColor(value: Color) =
        setProperty("border-right-color", "$value")

    fun borderRightStyle(value: EBorderStyleOption) =
        setProperty("border-right-style", value.toCssString())

    fun borderRightWidth(value: EBorderWidthOption) =
        setProperty("border-right-width", value.toCssString())

    fun borderRightWidth(value: Length) =
        setProperty("border-right-width", "$value")

    fun borderSpacing(horizontal: Length, vertical: Length = horizontal) {
        var css = "$horizontal"
        if ( vertical != horizontal ) {
            css += " $vertical"
        }
        setProperty("border-spacing", css)
    }

    fun borderStyle(top: EBorderStyleOption, right: EBorderStyleOption = top,
                    bottom: EBorderStyleOption = top, left: EBorderStyleOption = right) {
        var css = top.toCssString()
        if ( right != top || bottom != top || left != right ) {
            css += " " + right.toCssString()
        }
        if ( bottom != top || left != right ) {
            css += " " + bottom.toCssString()
        }
        if ( left != right ) {
            css += " " + left.toCssString()
        }
        setProperty("border-style",css)
    }

    fun borderTop(width: EBorderWidthOption, style: EBorderStyleOption, color: Color) =
        setProperty("border-top", width.toCssString() + " " + style.toCssString() + " $color")

    fun borderTopColor(value: Color) =
        setProperty("border-top-color", "$value")

    fun borderTopStyle(value: EBorderStyleOption) =
        setProperty("border-top-style", value.toCssString())

    fun borderTopWidth(value: EBorderWidthOption) =
        setProperty("border-top-width", value.toCssString())

    fun borderTopWidth(value: Length) =
        setProperty("border-top-width", "$value")

    fun bottom(value:Length) =
        setProperty("bottom", "$value")

    fun bottom(value:Percentage) =
        setProperty("bottom", "$value")

    fun bottom(value:EAutoOption) =
        setProperty("bottom", value.toCssString())

    fun boxSizing(value:EBoxSizingOption) =
        setProperty("box-sizing", value.toCssString())

    fun captionSide(value:ECaptionSideOption) =
        setProperty("caption-side", value.toCssString())

    fun caretColor(value: Color) =
        setProperty("caret-color", "$value")

    fun caretColor(value: EAutoOption) =
        setProperty("caret-color", value.toCssString())

    // TODO?: chains

    fun clear(value: EClearOption) =
        setProperty("clear", value.toCssString())

    // TODO: clip

    fun color(value: Color) =
        setProperty("color", "$value")

    fun content(value: EContentOption) =
        setProperty("content", value.toCssString())

    fun content(value: String) =
        setStringProperty("content", value)

    fun contentAttr(value: String) =
        setProperty("content", "attr($value)")

    fun contentUrl(value: String) =
        setProperty("content", "url(\"$value\")")

    fun display(value: EDisplayOption) =
        setProperty("display", value.toCssString())

    fun height(value: Length) =
        setProperty("height", "$value")

    /** Funky getter actually sets the last used key to be !important. */
    val important: Unit
        get() {
            require(properties.size > 0) { "Set a property before making it important." }
            properties[properties.size - 1] += " !important"
        }

    fun width(value: Length) =
        setProperty("width", "$value")

    ////

    fun inherit(key: String) {
        properties.add("$key: inherit")
    }

    fun setProperty(key: String, value: String) {
        properties.add("$key: $value")
    }

    private fun setStringProperty(key: String, value: String) {
        var cssValue = value.replace("\"","\\\"")
        cssValue = cssValue.replace( "\n", "\\A")
        // TODO: probably more characters worth escaping
        setProperty( key, "\"$cssValue\"")
    }

    fun toCssString(whitespace: String = "\n") =
        properties.joinToString(";" + whitespace) + ";"

    override fun toString() =
        toCssString(" ")

}

//---------------------------------------------------------------------------------------------------------------------

/**
 * Builds a new style object.
 * @param build the callback that fills in the CSS properties.
 */
fun style(
    build: Style.() -> Unit
): Style {

    val result = Style()
    result.build()
    return result

}

//---------------------------------------------------------------------------------------------------------------------

