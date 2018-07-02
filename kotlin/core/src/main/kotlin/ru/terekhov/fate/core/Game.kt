package ru.terekhov.fate.core

import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.ActionResultListener
import ru.terekhov.fate.core.actions.BaseAction
import ru.terekhov.fate.core.descriptions.Description
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationRepository
import ru.terekhov.fate.core.states.*

class Game : ActionHandler {

    lateinit var locations: LocationRepository
    lateinit var gameState: GameStateRepository
    lateinit var worldState: WorldStateRepository
    lateinit var limboState: LimboStateRepository
    lateinit var charactersState: CharacterStateRepository
    lateinit var locationState: LocationStateRepository
    lateinit var currentLocation: Location
    private lateinit var listener: ActionResultListener


    fun loadLocation(locationId: String) {
        currentLocation = locations.loadLocation(locationId)
    }

    override fun handleAction(action: BaseAction) {
        // Validate action
        if (action.validate(this)) {
            action.act(this)
            // Choose reaction
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

    fun presentDescription() {
        if (!::currentLocation.isInitialized) {
            currentLocation = locations.loadLocation("default")
        }
        listener.showDescription(
                Description(
                        currentLocation.description,
                        currentLocation.actions
                )
        )
    }
}