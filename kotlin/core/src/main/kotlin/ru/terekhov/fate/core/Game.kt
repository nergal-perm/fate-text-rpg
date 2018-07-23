package ru.terekhov.fate.core

import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.ActionResultListener
import ru.terekhov.fate.core.descriptions.Representation
import ru.terekhov.fate.core.locations.LocationRepository
import ru.terekhov.fate.core.model.ActionModel
import ru.terekhov.fate.core.model.LocationModel
import ru.terekhov.fate.core.states.*

class Game : ActionHandler {

    lateinit var locations: LocationRepository
    lateinit var gameState: GameStateRepository
    lateinit var worldState: WorldStateRepository
    lateinit var limboState: LimboStateRepository
    lateinit var charactersState: CharacterStateRepository
    lateinit var locationState: LocationStateRepository
    lateinit var currentLocation: LocationModel
    private lateinit var listener: ActionResultListener


    fun loadLocation(locationId: String) {
        currentLocation = locations.loadLocation(locationId)
    }

    override fun handleAction(actionId: String) {
        // Validate action
        val action: ActionModel? = currentLocation.actions.filter { it.id == actionId }[0]
        if (action != null) {
            // Choose and apply reaction
            if (action.destination != null) {
                currentLocation = locations.loadLocation(action.destination)
            }
            // Generate description
        } else {
            // Generate error description

        }

        // Make ActionResultListener render the description
        presentDescription()
    }

    override fun setListener(listener: ActionResultListener) {
        this.listener = listener
    }

    override fun startGame() {
        presentDescription()
    }

    private fun presentDescription() {
        if (!::currentLocation.isInitialized) {
            currentLocation = locations.loadLocation("default")
        }
        listener.showDescription(
                Representation(
                        currentLocation.description.longDesc ?: currentLocation.description.shortDesc,
                        currentLocation.actions
                )
        )
    }
}