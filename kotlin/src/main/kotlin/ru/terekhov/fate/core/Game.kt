package ru.terekhov.fate.core

import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationLoader

class Game {
    var currentLocation: Location? = null
    lateinit var locationLoader: LocationLoader


    fun loadLocation(location: Int) {
        currentLocation = locationLoader.loadLocation(location)
    }

}