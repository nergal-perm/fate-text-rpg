package ru.terekhov.fate.core.locations

interface LocationEntityGateway {
    fun loadLocation(locationId: Int): Location
}