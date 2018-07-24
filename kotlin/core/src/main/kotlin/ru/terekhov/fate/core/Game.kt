package ru.terekhov.fate.core

import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.ActionResultListener
import ru.terekhov.fate.core.descriptions.Representation
import ru.terekhov.fate.core.locations.LocationRepository
import ru.terekhov.fate.core.model.ActionModel
import ru.terekhov.fate.core.model.LocationModel
import ru.terekhov.fate.core.states.*
import ru.terekhov.fate.core.utils.ConditionEvaluator
import ru.terekhov.fate.core.utils.OutcomeHandler

class Game : ActionHandler {

    lateinit var locations: LocationRepository
    lateinit var gameState: GameStateRepository
    lateinit var worldState: WorldStateRepository
    lateinit var limboState: LimboStateRepository
    lateinit var charactersState: CharacterStateRepository
    lateinit var locationState: LocationStateRepository
    lateinit var currentLocation: LocationModel
    lateinit var outcomeHandler: OutcomeHandler
    lateinit var conditionEvaluator: ConditionEvaluator
    private lateinit var listener: ActionResultListener


    fun loadLocation(locationId: String) {
        currentLocation = locations.loadLocation(locationId)
    }

    override fun handleAction(actionId: String) {
        // Validate action
        val action: ActionModel? = currentLocation.actions.filter { it.id == actionId }[0]
        var actionResult = ""
        if (action != null) {
            if (action.outcome != null) {
                outcomeHandler.eval(action.outcome.outcome)
                actionResult = action.outcome.description ?: ""
            }
            // Generate description
        } else {
            // Generate error description

        }


        currentLocation = locations.loadLocation(gameState.getValue("location"))
        // Make ActionResultListener render the description
        presentDescription(actionResult)
    }

    override fun setListener(listener: ActionResultListener) {
        this.listener = listener
    }

    override fun startGame() {
        currentLocation = locations.loadLocation("default")
        presentDescription("")
    }

    private fun presentDescription(actionResult: String) {
        listener.showDescription(
                Representation(
                        actionResult + "\n" + currentLocation.description,
                        currentLocation.actions.filter { conditionEvaluator.eval(it.condition).result }.toTypedArray()
                )
        )
    }
}