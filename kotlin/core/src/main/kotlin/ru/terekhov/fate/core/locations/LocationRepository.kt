package ru.terekhov.fate.core.locations

interface LocationRepository {
    fun loadLocation(locationId: String): Location
}