package ru.terekhov.fate.core

import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.UserAction
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

    override fun handleAction(action: UserAction) {
        when (action.type) {
            "move" -> loadLocation(action.locationId)
        }
    }

}