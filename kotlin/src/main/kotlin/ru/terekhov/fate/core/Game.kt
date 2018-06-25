package ru.terekhov.fate.core

import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationEntityGateway

class Game {
    var currentLocation: Location
    private var locationEntityGateway: LocationEntityGateway

    constructor(locationEntityGateway: LocationEntityGateway) {
        this.locationEntityGateway = locationEntityGateway
        currentLocation = locationEntityGateway.loadLocation(1)
    }


    fun loadLocation(location: Int) {
        currentLocation = locationEntityGateway.loadLocation(location)
    }

}