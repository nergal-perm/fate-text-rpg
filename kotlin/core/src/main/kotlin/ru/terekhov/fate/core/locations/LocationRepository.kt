package ru.terekhov.fate.core.locations

import ru.terekhov.fate.core.model.LocationModel

interface LocationRepository {
    fun loadLocation(locationId: String): LocationModel
}