//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package jvm.katydid.css.styles

import o.katydid.css.colors.*
import o.katydid.css.measurements.em
import o.katydid.css.measurements.percent
import o.katydid.css.measurements.pt
import o.katydid.css.measurements.px
import o.katydid.css.styles.Style
import o.katydid.css.styles.style
import o.katydid.css.types.*
import o.katydid.css.types.EAuto.auto
import o.katydid.css.types.EBackgroundPosition.*
import o.katydid.css.types.EBorderCollapse.collapse
import o.katydid.css.types.EBorderCollapse.separate
import o.katydid.css.types.EDisplay.*
import o.katydid.css.types.ELineStyle.*
import o.katydid.css.types.ELineWidth.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

//---------------------------------------------------------------------------------------------------------------------

@Suppress("RemoveRedundantBackticks")
class StylePropertyTests {

    private fun checkStyle(
        expectedCss: String,
        build: Style.() -> Unit
    ) {
        assertEquals(expectedCss, style(build).toString())
    }

    @Test
    fun `Background style properties convert to correct CSS`() {

        // TODO: background

        checkStyle("background-attachment: fixed;") { backgroundAttachment(EAttachment.fixed) }
        checkStyle("background-attachment: scroll, local;") { backgroundAttachment(EAttachment.scroll, EAttachment.local) }

        checkStyle("background-color: aquamarine;") { backgroundColor(aquamarine) }

        checkStyle("background-image: none;") { backgroundImage(ENone.none) }
        checkStyle("background-image: url(\"http://myimage.jpg\");") { backgroundImage("http://myimage.jpg") }
        checkStyle("background-image: url(\"images/1.jpg\"), url(\"images/2.jpg\");") { backgroundImage("images/1.jpg", "images/2.jpg") }
        checkStyle("background-image: url(\"images/1.jpg\"), none;") { backgroundImage("images/1.jpg", "none") }

        checkStyle("background-position: center center;") { backgroundPosition(center) }
        checkStyle("background-position: left top;") { backgroundPosition(left, top) }
        checkStyle("background-position: right bottom;") { backgroundPosition(right, bottom) }
        checkStyle("background-position: 50% center;") { backgroundPosition(50.percent) }
        checkStyle("background-position: 50% 75%;") { backgroundPosition(50.percent, 75.percent) }
        checkStyle("background-position: 100px center;") { backgroundPosition(100.px) }
        checkStyle("background-position: 10px 20px;") { backgroundPosition(10.px, 20.px) }
        checkStyle("background-position: left 50% bottom 75%;") { backgroundPosition(left, 50.percent, bottom, 75.percent) }
        checkStyle("background-position: left 2px bottom -5px;") { backgroundPosition(left, 2.px, bottom, (-5).px) }

        checkStyle("background-repeat: repeat;") { backgroundRepeat(ERepeatStyle.repeat) }
        checkStyle("background-repeat: repeat-x;") { backgroundRepeat(ERepeatStyle.repeatX) }
        checkStyle("background-repeat: repeat-y;") { backgroundRepeat(ERepeatStyle.repeatY) }
        checkStyle("background-repeat: no-repeat;") { backgroundRepeat(ERepeatStyle.noRepeat) }
        checkStyle("background-repeat: round space;") { backgroundRepeat(ERepeatStyle.round, ERepeatStyle.space) }
        checkStyle("background-repeat: round space, repeat no-repeat;") {
            backgroundRepeat(Pair(ERepeatStyle.round, ERepeatStyle.space), Pair(ERepeatStyle.repeat, ERepeatStyle.noRepeat))
        }
        assertFailsWith<IllegalArgumentException> { checkStyle("throws") { backgroundRepeat(ERepeatStyle.repeatX, ERepeatStyle.repeat) } }
        assertFailsWith<IllegalArgumentException> { checkStyle("throws") { backgroundRepeat(ERepeatStyle.repeatY, ERepeatStyle.repeat) } }
        assertFailsWith<IllegalArgumentException> { checkStyle("throws") { backgroundRepeat(Pair(ERepeatStyle.repeatX, ERepeatStyle.repeat)) } }
    }

    @Test
    fun `Border style properties convert to correct CSS`() {

        checkStyle("border: thin;") { border(thin) }
        checkStyle("border: 4px dashed green;") { border(4.px, ELineStyle.dashed, green) }
        checkStyle("border: solid;") { border(ELineStyle.solid) }

        checkStyle("border-bottom: thin dotted mediumslateblue;") { borderBottom(thin, dotted, mediumslateblue) }
        checkStyle("border-bottom: thin;") { borderBottom(thin) }
        checkStyle("border-bottom: 4px dashed green;") { borderBottom(4.px, ELineStyle.dashed, green) }
        checkStyle("border-bottom: solid;") { borderBottom(ELineStyle.solid) }

        checkStyle("border-bottom-color: navajowhite;") { borderBottomColor(navajowhite) }

        checkStyle("border-bottom-style: none;") { borderBottomStyle(ELineStyle.none) }
        checkStyle("border-bottom-style: hidden;") { borderBottomStyle(hidden) }
        checkStyle("border-bottom-style: dotted;") { borderBottomStyle(dotted) }
        checkStyle("border-bottom-style: dashed;") { borderBottomStyle(dashed) }
        checkStyle("border-bottom-style: solid;") { borderBottomStyle(solid) }
        checkStyle("border-bottom-style: double;") { borderBottomStyle(double) }
        checkStyle("border-bottom-style: groove;") { borderBottomStyle(groove) }
        checkStyle("border-bottom-style: ridge;") { borderBottomStyle(ridge) }
        checkStyle("border-bottom-style: inset;") { borderBottomStyle(inset) }
        checkStyle("border-bottom-style: outset;") { borderBottomStyle(outset) }

        checkStyle("border-bottom-width: thin;") { borderBottomWidth(thin) }
        checkStyle("border-bottom-width: medium;") { borderBottomWidth(medium) }
        checkStyle("border-bottom-width: thick;") { borderBottomWidth(thick) }
        checkStyle("border-bottom-width: 4px;") { borderBottomWidth(4.px) }

        checkStyle("border-collapse: separate;") { borderCollapse(separate) }
        checkStyle("border-collapse: collapse;") { borderCollapse(collapse) }

        checkStyle("border-color: purple;") { borderColor(purple) }
        checkStyle("border-color: purple orange;") { borderColor(purple, orange) }
        checkStyle("border-color: purple orange;") { borderColor(purple, orange, purple, orange) }
        checkStyle("border-color: purple orange olivedrab;") { borderColor(purple, orange, olivedrab) }
        checkStyle("border-color: purple orange olivedrab salmon;") { borderColor(purple, orange, olivedrab, salmon) }

        checkStyle("border-left: thin dotted mediumslateblue;") { borderLeft(thin, dotted, mediumslateblue) }
        checkStyle("border-left: thin;") { borderLeft(thin) }
        checkStyle("border-left: 4px dashed green;") { borderLeft(4.px, ELineStyle.dashed, green) }
        checkStyle("border-left: solid;") { borderLeft(ELineStyle.solid) }

        checkStyle("border-left-color: navajowhite;") { borderLeftColor(navajowhite) }

        checkStyle("border-left-style: solid;") { borderLeftStyle(solid) }

        checkStyle("border-left-width: thick;") { borderLeftWidth(thick) }
        checkStyle("border-left-width: 4px;") { borderLeftWidth(4.px) }

        checkStyle("border-right: thin dotted mediumslateblue;") { borderRight(thin, dotted, mediumslateblue) }
        checkStyle("border-right: thin;") { borderRight(thin) }
        checkStyle("border-right: 4px dashed green;") { borderRight(4.px, ELineStyle.dashed, green) }
        checkStyle("border-right: solid;") { borderRight(ELineStyle.solid) }

        checkStyle("border-right-color: navajowhite;") { borderRightColor(navajowhite) }

        checkStyle("border-right-style: solid;") { borderRightStyle(solid) }

        checkStyle("border-right-width: thick;") { borderRightWidth(thick) }
        checkStyle("border-right-width: 4px;") { borderRightWidth(4.px) }

        checkStyle("border-spacing: 2px;") { borderSpacing(2.px) }
        checkStyle("border-spacing: 2px;") { borderSpacing(2.px, 2.px) }
        checkStyle("border-spacing: 2px 1px;") { borderSpacing(2.px, 1.px) }

        checkStyle("border-style: solid;") { borderStyle(solid) }
        checkStyle("border-style: solid dotted;") { borderStyle(solid, dotted) }
        checkStyle("border-style: solid dotted ridge;") { borderStyle(solid, dotted, ridge) }
        checkStyle("border-style: solid dotted ridge dashed;") { borderStyle(solid, dotted, ridge, dashed) }

        checkStyle("border-top: thin dotted mediumslateblue;") { borderTop(thin, dotted, mediumslateblue) }
        checkStyle("border-top: thin;") { borderTop(thin) }
        checkStyle("border-top: 4px dashed green;") { borderTop(4.px, ELineStyle.dashed, green) }
        checkStyle("border-top: solid;") { borderTop(ELineStyle.solid) }

        checkStyle("border-top-color: navajowhite;") { borderTopColor(navajowhite) }

        checkStyle("border-top-style: solid;") { borderTopStyle(solid) }

        checkStyle("border-top-width: thick;") { borderTopWidth(thick) }
        checkStyle("border-top-width: 4px;") { borderTopWidth(4.px) }

        checkStyle("border-width: thick;") { borderWidth(thick) }
        checkStyle("border-width: thick thin thick medium;") { borderWidth(thick, thin, thick, medium) }
        checkStyle("border-width: 4px;") { borderWidth(4.px) }
        checkStyle("border-width: 4px 2px;") { borderWidth(4.px, 2.px) }

    }

    @Test
    fun `Caption side style properties convert to correct CSS`() {

        checkStyle("caption-side: top;") { captionSide(ECaptionSide.top) }
        checkStyle("caption-side: bottom;") { captionSide(ECaptionSide.bottom) }

    }

    @Test
    fun `Caret color style properties convert to correct CSS`() {

        checkStyle("caret-color: slateblue;") { caretColor(slateblue) }
        checkStyle("caret-color: auto;") { caretColor(auto) }

    }

    @Test
    fun `Clear and float style properties convert to correct CSS`() {

        checkStyle("clear: block-end;") { clear(EClear.blockEnd) }
        checkStyle("clear: block-start;") { clear(EClear.blockStart) }
        checkStyle("clear: both;") { clear(EClear.both) }
        checkStyle("clear: bottom;") { clear(EClear.bottom) }
        checkStyle("clear: inline-end;") { clear(EClear.inlineEnd) }
        checkStyle("clear: inline-start;") { clear(EClear.inlineStart) }
        checkStyle("clear: left;") { clear(EClear.left) }
        checkStyle("clear: none;") { clear(EClear.none) }
        checkStyle("clear: right;") { clear(EClear.right) }
        checkStyle("clear: top;") { clear(EClear.top) }

        checkStyle("float: block-end;") { float(EFloat.blockEnd) }
        checkStyle("float: block-start;") { float(EFloat.blockStart) }
        checkStyle("float: bottom;") { float(EFloat.bottom) }
        checkStyle("float: inline-end;") { float(EFloat.inlineEnd) }
        checkStyle("float: inline-start;") { float(EFloat.inlineStart) }
        checkStyle("float: left;") { float(EFloat.left) }
        checkStyle("float: none;") { float(EFloat.none) }
        checkStyle("float: right;") { float(EFloat.right) }
        checkStyle("float: snap-block;") { float(EFloat.snapBlock) }
        checkStyle("float: snap-inline;") { float(EFloat.snapInline) }

    }

    @Test
    fun `Cursor style properties convert to correct CSS`() {

        checkStyle("cursor: alias;") { cursor(ECursor.alias) }
        checkStyle("cursor: all-scroll;") { cursor(ECursor.allScroll) }
        checkStyle("cursor: auto;") { cursor(ECursor.auto) }
        checkStyle("cursor: cell;") { cursor(ECursor.cell) }
        checkStyle("cursor: col-resize;") { cursor(ECursor.colResize) }
        checkStyle("cursor: context-menu;") { cursor(ECursor.contextMenu) }
        checkStyle("cursor: copy;") { cursor(ECursor.copy) }
        checkStyle("cursor: crosshair;") { cursor(ECursor.crosshair) }
        checkStyle("cursor: default;") { cursor(ECursor.default) }
        checkStyle("cursor: e-resize;") { cursor(ECursor.eResize) }
        checkStyle("cursor: ew-resize;") { cursor(ECursor.ewResize) }
        checkStyle("cursor: grab;") { cursor(ECursor.grab) }
        checkStyle("cursor: grabbing;") { cursor(ECursor.grabbing) }
        checkStyle("cursor: help;") { cursor(ECursor.help) }
        checkStyle("cursor: move;") { cursor(ECursor.move) }
        checkStyle("cursor: n-resize;") { cursor(ECursor.nResize) }
        checkStyle("cursor: ne-resize;") { cursor(ECursor.neResize) }
        checkStyle("cursor: nesw-resize;") { cursor(ECursor.neswResize) }
        checkStyle("cursor: no-drop;") { cursor(ECursor.noDrop) }
        checkStyle("cursor: none;") { cursor(ECursor.none) }
        checkStyle("cursor: not-allowed;") { cursor(ECursor.notAlowed) }
        checkStyle("cursor: ns-resize;") { cursor(ECursor.nsResize) }
        checkStyle("cursor: nw-resize;") { cursor(ECursor.nwResize) }
        checkStyle("cursor: nwse-resize;") { cursor(ECursor.nwseResize) }
        checkStyle("cursor: pointer;") { cursor(ECursor.pointer) }
        checkStyle("cursor: progress;") { cursor(ECursor.progress) }
        checkStyle("cursor: row-resize;") { cursor(ECursor.rowResize) }
        checkStyle("cursor: s-resize;") { cursor(ECursor.sResize) }
        checkStyle("cursor: se-resize;") { cursor(ECursor.seResize) }
        checkStyle("cursor: sw-resize;") { cursor(ECursor.swResize) }
        checkStyle("cursor: text;") { cursor(ECursor.text) }
        checkStyle("cursor: vertical-text;") { cursor(ECursor.verticalText) }
        checkStyle("cursor: w-resize;") { cursor(ECursor.wResize) }
        checkStyle("cursor: wait;") { cursor(ECursor.wait) }
        checkStyle("cursor: zoom-in;") { cursor(ECursor.zoomIn) }
        checkStyle("cursor: zoom-out;") { cursor(ECursor.zoomOut) }

    }

    @Test
    fun `Display style properties convert to correct CSS`() {

        checkStyle("display: inline;") { display(inline) }
        checkStyle("display: block;") { display(block) }
        checkStyle("display: list-item;") { display(listItem) }
        checkStyle("display: inline-block;") { display(inlineBlock) }
        checkStyle("display: table;") { display(table) }
        checkStyle("display: inline-table;") { display(inlineTable) }
        checkStyle("display: table-row-group;") { display(tableRowGroup) }
        checkStyle("display: table-header-group;") { display(tableHeaderGroup) }
        checkStyle("display: table-footer-group;") { display(tableFooterGroup) }
        checkStyle("display: table-row;") { display(tableRow) }
        checkStyle("display: table-column-group;") { display(tableColumnGroup) }
        checkStyle("display: table-column;") { display(tableColumn) }
        checkStyle("display: table-cell;") { display(tableCell) }
        checkStyle("display: table-caption;") { display(tableCaption) }
        checkStyle("display: none;") { display(EDisplay.none) }

    }

    @Test
    fun `Empty cells style properties convert to correct CSS`() {

        checkStyle("empty-cells: show;") { emptyCells(EEmptyCells.show) }
        checkStyle("empty-cells: hide;") { emptyCells(EEmptyCells.hide) }

    }

    @Test
    fun `Font style properties convert to correct CSS`() {

        checkStyle("font-family: arial, helvetica, sans-serif;") { fontFamily("arial", "helvetica", "sans-serif") }
        checkStyle("font-family: \"Courier New\", serif;") { fontFamily("Courier New", "serif") }

        checkStyle("font-size: large;") { fontSize(EFontSize.large) }
        checkStyle("font-size: larger;") { fontSize(EFontSize.larger) }
        checkStyle("font-size: medium;") { fontSize(EFontSize.medium) }
        checkStyle("font-size: small;") { fontSize(EFontSize.small) }
        checkStyle("font-size: smaller;") { fontSize(EFontSize.smaller) }
        checkStyle("font-size: x-large;") { fontSize(EFontSize.xLarge) }
        checkStyle("font-size: x-small;") { fontSize(EFontSize.xSmall) }
        checkStyle("font-size: xx-large;") { fontSize(EFontSize.xxLarge) }
        checkStyle("font-size: xx-small;") { fontSize(EFontSize.xxSmall) }
        checkStyle("font-size: 14pt;") { fontSize(14.pt) }
        checkStyle("font-size: 90%;") { fontSize(90.percent) }

        checkStyle("font-style: normal;") { fontStyle(EFontStyle.normal) }
        checkStyle("font-style: italic;") { fontStyle(EFontStyle.italic) }
        checkStyle("font-style: oblique;") { fontStyle(EFontStyle.oblique) }

        checkStyle("font-variant: all-petite-caps;") { fontVariant(EFontVariant.allPetiteCaps) }
        checkStyle("font-variant: all-small-caps;") { fontVariant(EFontVariant.allSmallCaps) }
        checkStyle("font-variant: normal;") { fontVariant(EFontVariant.normal) }
        checkStyle("font-variant: petite-caps;") { fontVariant(EFontVariant.petiteCaps) }
        checkStyle("font-variant: small-caps;") { fontVariant(EFontVariant.smallCaps) }
        checkStyle("font-variant: titling-caps;") { fontVariant(EFontVariant.titlingCaps) }
        checkStyle("font-variant: unicase;") { fontVariant(EFontVariant.unicase) }

        checkStyle("font-weight: bold;") { fontWeight(EFontWeight.bold) }
        checkStyle("font-weight: bolder;") { fontWeight(EFontWeight.bolder) }
        checkStyle("font-weight: lighter;") { fontWeight(EFontWeight.lighter) }
        checkStyle("font-weight: normal;") { fontWeight(EFontWeight.normal) }
        checkStyle("font-weight: 100;") { fontWeight(EFontWeight.weight100) }
        checkStyle("font-weight: 200;") { fontWeight(EFontWeight.weight200) }
        checkStyle("font-weight: 300;") { fontWeight(EFontWeight.weight300) }
        checkStyle("font-weight: 400;") { fontWeight(EFontWeight.weight400) }
        checkStyle("font-weight: 500;") { fontWeight(EFontWeight.weight500) }
        checkStyle("font-weight: 600;") { fontWeight(EFontWeight.weight600) }
        checkStyle("font-weight: 700;") { fontWeight(EFontWeight.weight700) }
        checkStyle("font-weight: 800;") { fontWeight(EFontWeight.weight800) }
        checkStyle("font-weight: 900;") { fontWeight(EFontWeight.weight900) }

    }

    @Test
    fun `Letter spacing and line height style properties convert to correct CSS`() {

        checkStyle("letter-spacing: 0.05em;") { letterSpacing(0.05.em) }
        checkStyle("letter-spacing: normal;") { letterSpacing(ENormal.normal) }

        checkStyle("line-height: normal;") { lineHeight(ENormal.normal) }
        checkStyle("line-height: 1.2em;") { lineHeight(1.2.em) }
        checkStyle("line-height: 120%;") { lineHeight(120.percent) }
        checkStyle("line-height: 1.2;") { lineHeight(1.2f) }

    }

    @Test
    fun `List style properties convert to correct CSS`() {

        checkStyle("list-style-image: none;") { listStyleImage(ENone.none) }
        checkStyle("list-style-image: url(\"http://myimage.jpg\");") { listStyleImage("http://myimage.jpg") }

        checkStyle("list-style-position: inside;") { listStylePosition(EListStylePosition.inside) }
        checkStyle("list-style-position: outside;") { listStylePosition(EListStylePosition.outside) }

        checkStyle("list-style-type: armenian;") { listStyleType(EListStyleType.armenian) }
        checkStyle("list-style-type: circle;") { listStyleType(EListStyleType.circle) }
        checkStyle("list-style-type: decimal;") { listStyleType(EListStyleType.decimal) }
        checkStyle("list-style-type: decimal-leading-zero;") { listStyleType(EListStyleType.decimalLeadingZero) }
        checkStyle("list-style-type: disc;") { listStyleType(EListStyleType.disc) }
        checkStyle("list-style-type: georgian;") { listStyleType(EListStyleType.georgian) }
        checkStyle("list-style-type: lower-greek;") { listStyleType(EListStyleType.lowerGreek) }
        checkStyle("list-style-type: lower-latin;") { listStyleType(EListStyleType.lowerLatin) }
        checkStyle("list-style-type: lower-roman;") { listStyleType(EListStyleType.lowerRoman) }
        checkStyle("list-style-type: square;") { listStyleType(EListStyleType.square) }
        checkStyle("list-style-type: upper-latin;") { listStyleType(EListStyleType.upperLatin) }
        checkStyle("list-style-type: upper-roman;") { listStyleType(EListStyleType.upperRoman) }
        checkStyle("list-style-type: lower-latin;") { listStyleType(EListStyleType.lowerAlpha) }
        checkStyle("list-style-type: upper-latin;") { listStyleType(EListStyleType.upperAlpha) }

        checkStyle("list-style: square;") { listStyle(EListStyleType.square) }
        checkStyle("list-style: square outside;") { listStyle(EListStyleType.square, EListStylePosition.outside) }
        checkStyle("list-style: square outside url(\"images/image.jpg\");") { listStyle(EListStyleType.square, EListStylePosition.outside, "images/image.jpg") }

    }

    @Test
    fun `Resize properties convert to correct CSS`() {

        checkStyle("resize: both;") { resize(EResize.both) }
        checkStyle("resize: horizontal;") { resize(EResize.horizontal) }
        checkStyle("resize: none;") { resize(EResize.none) }
        checkStyle("resize: vertical;") { resize(EResize.vertical) }

    }

    @Test
    fun `Arbitrary properties convert to correct CSS`() {

        checkStyle("not-yet-invented: 7px;") { setProperty("not-yet-invented", 7.px.toString()) }

    }

    @Test
    fun `Table layout properties convert to correct CSS`() {

        checkStyle("table-layout: auto;") { tableLayout(ETableLayout.auto) }
        checkStyle("table-layout: fixed;") { tableLayout(ETableLayout.fixed) }

    }

    @Test
    fun `Widows and orphans properties convert to correct CSS`() {

        checkStyle("orphans: 3;") { orphans(3) }
        checkStyle("widows: 2;") { widows(2) }

        assertFailsWith<IllegalArgumentException> { checkStyle("throws") { orphans(0) } }
        assertFailsWith<IllegalArgumentException> { checkStyle("throws") { orphans(-1) } }
        assertFailsWith<IllegalArgumentException> { checkStyle("throws") { widows(0) } }
        assertFailsWith<IllegalArgumentException> { checkStyle("throws") { widows(-1) } }

    }

}

//---------------------------------------------------------------------------------------------------------------------

