package ru.terekhov.fate.core.actions

import ru.terekhov.fate.core.Game

class MoveAction(
        override val id: String,
        override val description: String,
        override val callToAction: String,
        private val destinationId: String) : BaseAction() {

    override val type: ActionType
        get() = ActionType.MOVE

    override fun validate(game: Game) = game.currentLocation.actions.any { (it.id == this.id ) }

    override fun act(game: Game) = listOf("game.currentLocation = " + this.destinationId)
}