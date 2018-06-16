//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package o.org.katydom.internal.concretenodes.text

import o.org.katydom.abstractnodes.KatyDomHtmlElement
import o.org.katydom.builders.KatyDomPhrasingContentBuilder
import o.org.katydom.builders.miscellaneous.KatyDomTextContentBuilder
import o.org.katydom.internal.builders.KatyDomPhrasingContentBuilderImpl
import o.org.katydom.types.EDirection
import x.org.katydom.types.KatyDateTime

//---------------------------------------------------------------------------------------------------------------------

/**
 * Virtual node for a <time> element.
 */
internal class KatyDomTime<Msg> : KatyDomHtmlElement<Msg> {

    constructor(
        phrasingContent: KatyDomPhrasingContentBuilderImpl<Msg>,
        selector: String?,
        key: Any?,
        accesskey: Char?,
        contenteditable: Boolean?,
        dir: EDirection?,
        hidden: Boolean?,
        lang: String?,
        spellcheck: Boolean?,
        style: String?,
        tabindex: Int?,
        title: String?,
        translate: Boolean?,
        defineContent: KatyDomTextContentBuilder<Msg>.() -> Unit
    ) : super(selector, key, accesskey, contenteditable, dir,
              hidden, lang, spellcheck, style, tabindex, title, translate) {

        phrasingContent.textContent(this).defineContent()
        this.freeze()

    }

    constructor(
        phrasingContent: KatyDomPhrasingContentBuilderImpl<Msg>,
        selector: String?,
        key: Any?,
        accesskey: Char?,
        contenteditable: Boolean?,
        datetime: KatyDateTime,
        dir: EDirection?,
        hidden: Boolean?,
        lang: String?,
        spellcheck: Boolean?,
        style: String?,
        tabindex: Int?,
        title: String?,
        translate: Boolean?,
        defineContent: KatyDomPhrasingContentBuilder<Msg>.() -> Unit
    ) : super(selector, key, accesskey, contenteditable, dir,
              hidden, lang, spellcheck, style, tabindex, title, translate) {

        setDateTimeAttribute("datetime", datetime)

        phrasingContent.withNoAddedRestrictions(this).defineContent()
        this.freeze()

    }

    ////

    override val nodeName = "TIME"

}

//---------------------------------------------------------------------------------------------------------------------