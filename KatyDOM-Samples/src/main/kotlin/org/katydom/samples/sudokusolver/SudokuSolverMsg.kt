//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package org.katydom.samples.sudokusolver

//---------------------------------------------------------------------------------------------------------------------

/** Simple message for user events (clicked candidate). */
sealed class SudokuSolverMsg

/** Message when a candidate has been clicked. */
data class PlaceValueMsg(
    val rowIndex: Int,
    val columnIndex: Int,
    val newValue: Int
) : SudokuSolverMsg()

/** Message when a value has been clicked. */
data class RemoveValueMsg(
    val rowIndex: Int,
    val columnIndex: Int
) : SudokuSolverMsg()

/** Message for a change in settings. */
data class ChangeSettingsMsg(
    val settingsChange: SettingsChange
) : SudokuSolverMsg()

//---------------------------------------------------------------------------------------------------------------------

sealed class SettingsChange

data class ChangeIsXSudoku(
    val newIsXSudoku: Boolean
) : SettingsChange()

data class ChangeIsSolvedAutomatically(
    val newIsSolvedAutomatically: Boolean
) : SettingsChange()

data class ChangeIsUserSolving(
    val newIsUserSolving: Boolean
) : SettingsChange()

//---------------------------------------------------------------------------------------------------------------------
