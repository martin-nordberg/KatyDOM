//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package o.katydid.kotlgen.model.expressions

import o.katydid.kotlgen.model.core.KgComposite

//---------------------------------------------------------------------------------------------------------------------

interface KgCollectionLiteral
    : KgExpression, KgComposite {

    ////

    operator fun invoke(revise: KgCollectionLiteral.() -> Unit) =
        this.revise()

}

//---------------------------------------------------------------------------------------------------------------------

