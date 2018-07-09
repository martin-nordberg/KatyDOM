//
// (C) Copyright 2018 Martin E. Nordberg III
// Apache 2.0 License
//

package org.katydom.samples.sudokusolver


//---------------------------------------------------------------------------------------------------------------------

/**
 * Creates a new application state modified from given [applicationState] by the given [message].
 */
fun updateSudokuSolver(
    applicationState: SudokuSolverAppState,
    message: SudokuSolverMsg
): SudokuSolverAppState {

    return when (message) {

        is PlaceValueMsg     ->
            applicationState.withCellValueSet(
                message.rowIndex, message.columnIndex, message.newValue
            )

        is RemoveValueMsg    ->
            applicationState.withCellValueRemoved(
                message.rowIndex, message.columnIndex
            )

        is ChangeSettingsMsg ->
            when (message.settingsChange) {

                is ChangeIsSolvedAutomatically ->
                    applicationState.withSettingsChanged(
                        applicationState.settings.isXSudoku,
                        message.settingsChange.newIsSolvedAutomatically,
                        false
                    )

                is ChangeIsUserSolving         ->
                    applicationState.withSettingsChanged(
                        applicationState.settings.isXSudoku,
                        applicationState.settings.isSolvedAutomatically,
                        message.settingsChange.newIsUserSolving
                    )

                is ChangeIsXSudoku             ->
                    applicationState.withSettingsChanged(
                        message.settingsChange.newIsXSudoku,
                        applicationState.settings.isSolvedAutomatically,
                        applicationState.settings.isUserSolving
                    )

            }

    }

}

//---------------------------------------------------------------------------------------------------------------------

/**
 * Clones this board but removes one previously placed value.
 */
fun SudokuSolverAppState.withCellValueRemoved(rowIndex: Int, colIndex: Int): SudokuSolverAppState {

    val newBoard = Board(settings.isXSudoku)
    val newChanges = mutableListOf<BoardChange>()

    for (i in 0..8) {

        for (j in 0..8) {

            val cell = board.rows[i].cells[j]

            if (cell.row.index == rowIndex && cell.column.index == colIndex) {
                continue
            }

            val newCell = newBoard.rows[i].cells[j]

            val oldValue = cell.value
            lateinit var candidatesRemoved: List<String>
            if (oldValue != null && cell.state != Cell.State.SOLVED) {
                candidatesRemoved = newCell.setValue(oldValue, cell.state)
            }
            else {
                continue
            }

            newChanges.add(BoardChange("Cell Value Set", newCell.name, candidatesRemoved))

        }

    }

    if (settings.isSolvedAutomatically) {
        newChanges.addAll(solve(newBoard))
    }

    return SudokuSolverAppState(newBoard, newChanges, settings)

}

//---------------------------------------------------------------------------------------------------------------------

/**
 * Clones this board but with one additional cell value set.
 */
fun SudokuSolverAppState.withCellValueSet(rowIndex: Int, colIndex: Int, value: Int): SudokuSolverAppState {

    val newBoard = Board(settings.isXSudoku)
    val newChanges = mutableListOf<BoardChange>()

    for (i in 0..8) {

        for (j in 0..8) {

            val cell = board.rows[i].cells[j]
            val newCell = newBoard.rows[i].cells[j]

            val oldValue = cell.value
            val candidatesRemoved =
                if (oldValue != null && (cell.state == Cell.State.DEFINED || settings.isUserSolving)) {
                    newCell.setValue(oldValue, cell.state)
                }
                else if (cell.row.index == rowIndex && cell.column.index == colIndex) {
                    val newState = if (settings.isUserSolving) Cell.State.GUESSED else Cell.State.DEFINED
                    newCell.setValue(value, newState)
                }
                else {
                    continue
                }

            newChanges.add(BoardChange("Cell Value Set", newCell.name, candidatesRemoved))

        }

    }

    if (settings.isSolvedAutomatically) {
        newChanges.addAll(solve(newBoard))
    }

    return SudokuSolverAppState(newBoard, newChanges, settings)

}

//---------------------------------------------------------------------------------------------------------------------

/**
 * Clones this board but changes its settings.
 */
fun SudokuSolverAppState.withSettingsChanged(
    newIsXSudoku: Boolean,
    newIsSolvedAutomatically: Boolean,
    newIsUserSolving: Boolean
): SudokuSolverAppState {

    val newSettings = SudokuSolverSettings(newIsXSudoku, newIsSolvedAutomatically, newIsUserSolving)
    val newBoard = Board(newSettings.isXSudoku)
    val newChanges = mutableListOf<BoardChange>()

    for (i in 0..8) {

        for (j in 0..8) {

            val cell = board.rows[i].cells[j]
            val newCell = newBoard.rows[i].cells[j]

            val oldValue = cell.value
            if (oldValue != null && newCell.candidates.contains(
                    oldValue) && (cell.state == Cell.State.DEFINED || newSettings.isUserSolving)) {
                val candidatesRemoved = newCell.setValue(oldValue, cell.state)
                newChanges.add(BoardChange("Cell Value Set", newCell.name, candidatesRemoved))
            }

        }

    }

    if (newIsSolvedAutomatically) {
        newChanges.addAll(solve(newBoard))
    }

    return SudokuSolverAppState(newBoard, newChanges, newSettings)

}

//---------------------------------------------------------------------------------------------------------------------

private fun solve(board: Board): List<BoardChange> {

    val result = mutableListOf<BoardChange>()

    while (true) {

        var changes = solveNakedSingles(board)

        if (changes.isNotEmpty()) {
            result.addAll(changes)
            continue
        }

        changes = solveHiddenSingles(board)

        if (changes.isNotEmpty()) {
            result.addAll(changes)
            continue
        }

        changes = solveNakedPairs(board)

        if (changes.isNotEmpty()) {
            result.addAll(changes)
            continue
        }

        break

    }

    return result

}

//---------------------------------------------------------------------------------------------------------------------

private fun solveNakedSingles(board: Board): List<BoardChange> {

    val result = mutableListOf<BoardChange>()

    for (row in board.rows) {

        for (cell in row.cells) {

            if (cell.candidates.size == 1) {
                val c = cell.candidates.elementAt(0)
                val candidatesRemoved = cell.setValue(c, Cell.State.SOLVED)
                result.add(BoardChange("Naked Single", cell.name, candidatesRemoved))
            }

        }

    }

    return result

}

//---------------------------------------------------------------------------------------------------------------------

private fun solveHiddenSingles(board: Board): List<BoardChange> {

    val result = mutableListOf<BoardChange>()

    for (unit in board.units) {

        for (c in 0..8) {

            if (unit.cellsWithCandidate[c].size == 1) {
                val cell = unit.cellsWithCandidate[c].elementAt(0)
                val candidatesRemoved = cell.setValue(c, Cell.State.SOLVED)
                result.add(BoardChange("Hidden Single in ${unit.name}", cell.name, candidatesRemoved))
            }

        }

    }

    return result

}

//---------------------------------------------------------------------------------------------------------------------

private fun solveNakedPairs(board: Board): List<BoardChange> {

    val result = mutableListOf<BoardChange>()

    for (unit in board.units) {

        for (u1 in 0..8) {

            val cell1 = unit.cells[u1]

            for (u2 in (u1 + 1)..8) {

                val cell2 = unit.cells[u2]

                if (cell1.candidates.size == 2 && cell1.candidates == cell2.candidates) {

                    val c1 = cell1.candidates.elementAt(0)
                    val c2 = cell1.candidates.elementAt(1)

                    val candidatesRemoved = mutableListOf<String>()

                    for (u in 0..8) {

                        if (u != u1 && u != u2) {

                            val cell = unit.cells[u]

                            if (cell.removeCandidate(c1)) {
                                candidatesRemoved.add("${cell.name}#${c1 + 1}")
                            }

                            if (cell.removeCandidate(c2)) {
                                candidatesRemoved.add("${cell.name}#${c2 + 1}")
                            }

                        }

                    }

                    if (candidatesRemoved.isNotEmpty()) {
                        result.add(
                            BoardChange("Naked Pair: ${cell1.name}/${cell2.name} in ${unit.name}", "",
                                        candidatesRemoved))
                    }

                }

            }

        }

    }

    return result

}

//---------------------------------------------------------------------------------------------------------------------

