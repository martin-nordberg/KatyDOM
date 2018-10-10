//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package jvm.katydid.css.styles.builders

import o.katydid.css.styles.Style
import o.katydid.css.styles.builders.visibility
import o.katydid.css.styles.style
import o.katydid.css.types.EVisibility
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

//---------------------------------------------------------------------------------------------------------------------

@Suppress("RemoveRedundantBackticks")
class VisibilityStylePropertyTests {

    private fun checkStyle(
        expectedCss: String,
        build: Style.() -> Unit
    ) {
        assertEquals(expectedCss, style(build).toString())
    }

    @Test
    fun `Visibility properties convert to correct CSS`() {

        checkStyle("visibility: collapse;") { visibility(EVisibility.collapse) }
        checkStyle("visibility: hidden;") { visibility(EVisibility.hidden) }
        checkStyle("visibility: visible;") { visibility(EVisibility.visible) }

    }

}

//---------------------------------------------------------------------------------------------------------------------
