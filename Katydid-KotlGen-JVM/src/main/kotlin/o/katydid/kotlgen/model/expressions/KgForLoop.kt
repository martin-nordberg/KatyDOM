//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package o.katydid.kotlgen.model.expressions

//---------------------------------------------------------------------------------------------------------------------

interface KgForLoop
    : KgLoop {

    ////

    operator fun invoke(revise: KgForLoop.() -> Unit) =
        this.revise()

}

//---------------------------------------------------------------------------------------------------------------------

