package ru.terekhov.fate.core

import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.BaseAction
import ru.terekhov.fate.core.actions.MoveAction
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationEntityGateway

class Game(private var locationEntityGateway: LocationEntityGateway) : ActionHandler {
    var currentLocation: Location

    init {
        currentLocation = locationEntityGateway.loadLocation("default")
    }


    fun loadLocation(locationId: String) {
        currentLocation = locationEntityGateway.loadLocation(locationId)
    }

    override fun handleAction(action: BaseAction) {
        action.act(this)
    }

}