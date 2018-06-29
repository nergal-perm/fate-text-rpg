package ru.terekhov.fate.core

import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.BaseAction
import ru.terekhov.fate.core.descriptions.Description
import ru.terekhov.fate.core.descriptions.DescriptionPresenter
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationRepository

class Game(private val locationRepository: LocationRepository,
           private val descriptionPresenter: DescriptionPresenter) : ActionHandler {

    var currentLocation: Location

    init {
        currentLocation = locationRepository.loadLocation("default")
        presentDescription()
    }

    fun loadLocation(locationId: String) {
        currentLocation = locationRepository.loadLocation(locationId)
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