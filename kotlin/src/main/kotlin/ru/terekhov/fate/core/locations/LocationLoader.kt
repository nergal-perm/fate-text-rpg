package ru.terekhov.fate.core.locations

interface LocationLoader {
    fun loadLocation(locationId: Int): Location?
}