package ru.terekhov.fate.core

import org.assertj.core.api.Assertions.*

import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationEntityGateway

class GameTest {
    @Test
    fun `should handle user movement`() {
        // Given
        val game = Game(StubLocationEntityGateway())

        game.loadLocation(1)

        var expectedLocation = Location(1, "Вы пришли на базар")
        assertThat(game.currentLocation).isEqualTo(expectedLocation)

        game.loadLocation(5)
        expectedLocation = Location(5, "Всем привет")
        assertThat(game.currentLocation).isEqualTo(expectedLocation)
    }

    class StubLocationEntityGateway: LocationEntityGateway {
        var locations = mapOf(
                1 to Location(1, "Вы пришли на базар"),
                5 to Location(5, "Всем привет"))
        override fun loadLocation(locationId: Int): Location {
            val loc= locations[locationId]
            return loc ?: Location(-1, "Несуществующая локация")
        }

    }
}