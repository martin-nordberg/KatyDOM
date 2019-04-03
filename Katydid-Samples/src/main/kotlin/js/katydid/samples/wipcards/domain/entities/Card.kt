//
// (C) Copyright 2019 Martin E. Nordberg III
// Apache 2.0 License
//

package js.katydid.samples.wipcards.domain.entities

import js.katydid.samples.wipcards.infrastructure.addIf


//---------------------------------------------------------------------------------------------------------------------

data class Card(

    val details: String,

    val title: String,

    val uuid: String

) {

    val problems = listOf<String>()
        .addIf(title.isEmpty()) {
            "Card title name must not be empty."
        }
        .addIf(title.isBlank()) {
            "Card title must not be blank."
        }
        .addIf(title.length > 100) {
            "Card title limited to 100 characters."
        }

}

//---------------------------------------------------------------------------------------------------------------------


