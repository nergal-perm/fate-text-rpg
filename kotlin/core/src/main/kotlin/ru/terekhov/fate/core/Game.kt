package ru.terekhov.fate.core

import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.BaseAction
import ru.terekhov.fate.core.actions.MoveAction
import ru.terekhov.fate.core.descriptions.Description
import ru.terekhov.fate.core.descriptions.DescriptionPresenter
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationEntityGateway

class Game(private val locationEntityGateway: LocationEntityGateway,
           private val descriptionPresenter: DescriptionPresenter) : ActionHandler {

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

    fun presentDescription() {
        descriptionPresenter.showDescription(
                Description(
                        currentLocation.description,
                        currentLocation.actions
                )
        )
    }

}