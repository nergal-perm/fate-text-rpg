package ru.terekhov.fate.core.states

/**
 * Used to access and update persistent game state
 * (current phase, location etc.)
 *
 */
interface GameStateRepository {
    fun getValue(key: String): String

}

/**
 * Used to access and update persistent world state
 * (time of day, choice consequences and so on)
 */
interface WorldStateRepository {

}

/**
 * Used to access and update location state
 * (visited or not, discovered hidden Aspects, taken
 * paths requiring Overcome action and so on)
 */
interface LocationStateRepository {

}

/**
 * Used to access and update limbo state
 * (objects and NPCs that should have just one instance
 * across the game and can appear in different places must
 * be handled with limbo to ensure they are unique)
 */
interface LimboStateRepository {

}

/**
 * Used to access and update characters state
 * (FATE points, stress tracks, Aspects, Consequences etc.)
 */
interface CharacterStateRepository {

}