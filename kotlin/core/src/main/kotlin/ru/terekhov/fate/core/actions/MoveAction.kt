package ru.terekhov.fate.core.actions

import ru.terekhov.fate.core.Game

class MoveAction(
        override val description: String,
        override val callToAction: String,
        private val destinationId: String) : BaseAction() {

    override val type: ActionType
        get() = ActionType.MOVE

    override fun act(game: Game) {
        game.loadLocation(destinationId)
    }

    override fun toString(): String {
        return "MOVE: $callToAction ($description) to $destinationId"
    }

    // Testing purposes only!!!
    override fun equals(other: Any?): Boolean {
        return super.equals(other) || this.toString() == other.toString()
    }
}