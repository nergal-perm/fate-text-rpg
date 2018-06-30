package ru.terekhov.fate.core.utils

import ru.terekhov.fate.core.Game
import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.locations.LocationRepository
import ru.terekhov.fate.core.states.*

abstract class AbstractEngineFactory(
        val gameStateRepository: GameStateRepository,
        val worldStateRepository: WorldStateRepository,
        val locationStateRepository: LocationStateRepository,
        val limboStateRepository: LimboStateRepository,
        val characterStateRepository: CharacterStateRepository,
        val locationRepository: LocationRepository
) {
    fun createGameEngine(): ActionHandler {
        val game = Game()
        game.locations = locationRepository
        game.gameState = gameStateRepository
        game.worldState = worldStateRepository
        game.locationState = locationStateRepository
        game.limboState = limboStateRepository
        game.charactersState = characterStateRepository
        return game
    }
}