package ru.terekhov.fate.core

import org.assertj.core.api.Assertions.*

import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.actions.UserAction
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationEntityGateway

class GameTest {
    @Test
    fun `should handle user movement`() {
        // Given
        val game = Game(StubLocationEntityGateway())
        var expectedLocation = Location(1, "Вы пришли на базар")
        assertThat(game.currentLocation).isEqualTo(expectedLocation)

        // When
        val movementAction = UserAction("move", "city01")
        game.handleAction(movementAction)

        // Then
        expectedLocation = Location(5, "Всем привет")
        assertThat(game.currentLocation).isEqualTo(expectedLocation)
    }

    class StubLocationEntityGateway: LocationEntityGateway {
        var locations = mapOf(
                "default" to Location(1, "Вы пришли на базар"),
                "city01" to Location(5, "Всем привет"))
        override fun loadLocation(locationId: String): Location {
            val loc= locations[locationId]
            return loc ?: Location(-1, "Несуществующая локация")
        }

    }
}