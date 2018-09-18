//
// (C) Copyright 2017-2018 Martin E. Nordberg III
// Apache 2.0 License
//

package o.katydid.css.types

//---------------------------------------------------------------------------------------------------------------------

/**
 * Enumeration of the option 'auto' when other options for a property are not enumerated.
 */
@Suppress("EnumEntryName")
enum class EAutoOption(
    private val css: String
) {

    /** The "auto" option. */
    auto("auto");

    ////

    /** @return the CSS attribute text for this option. */
    fun toCssString() =
        css

    ////

    companion object {

        fun fromString(option: String?) =
            when (option) {
                null                 -> null
                "auto"               -> auto
                else                 -> throw IllegalArgumentException("Unknown 'auto' option: '$option'.")
            }

    }

}

//---------------------------------------------------------------------------------------------------------------------

