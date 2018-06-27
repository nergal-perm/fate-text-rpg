package ru.terekhov.fate.core

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.actions.MoveAction
import ru.terekhov.fate.core.descriptions.Description
import ru.terekhov.fate.core.descriptions.DescriptionPresenter
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationEntityGateway

class GameTest {
    companion object {
        val moveToCity01Action = MoveAction("moveToCity01", "Неподалёку виднеется лавка торговца", "Перейти к лавке", "city01")
        val moveToDefaultAction = MoveAction("moveToDefault", "Можно пройти обратно на базар", "Вернуться на базар", "default")
        val descriptionPresenter = mockkClass(DescriptionPresenter::class)
    }

    @BeforeEach
    fun setUp() {
        clearMocks(descriptionPresenter)
        every {
            descriptionPresenter.showDescription(any())
        } answers {
            println("Hey!")
        }
    }

    @Test
    fun `should handle user movement`() {
        // Given
        val game = Game(StubLocationEntityGateway(), descriptionPresenter)
        var expectedLocation = Location(1, "Вы пришли на базар", listOf(moveToCity01Action))
        assertThat(game.currentLocation).isEqualTo(expectedLocation)

        // When
        game.handleAction(game.currentLocation.actions[0])

        // Then
        expectedLocation = Location(5, "Всем привет", listOf(moveToDefaultAction))
        assertThat(game.currentLocation).isEqualTo(expectedLocation)

        // When
        game.handleAction(game.currentLocation.actions[0])

        // Then
        expectedLocation = Location(1, "Вы пришли на базар", listOf(moveToCity01Action))
        assertThat(game.currentLocation).isEqualTo(expectedLocation)
    }

    @Test
    fun `should pass proper description and actions to UI`() {
        // Given
        val game = Game(StubLocationEntityGateway(), descriptionPresenter)

        // Then
        val expectedDescription = Description("Вы пришли на базар", listOf(moveToCity01Action))
        verify(exactly = 1) { descriptionPresenter.showDescription(expectedDescription) }
    }
}

class StubLocationEntityGateway: LocationEntityGateway {
    var locations = mapOf(
            "default" to Location(1, "Вы пришли на базар", listOf(GameTest.moveToCity01Action)),
            "city01" to Location(5, "Всем привет", listOf(GameTest.moveToDefaultAction)))
    override fun loadLocation(locationId: String): Location {
        return if (locationId in locations)
            locations[locationId]!!
        else Location(-1, "Несуществующая локация", listOf())
    }

}