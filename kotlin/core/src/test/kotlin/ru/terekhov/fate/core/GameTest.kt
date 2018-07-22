package ru.terekhov.fate.core

import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.actions.ActionResultListener
import ru.terekhov.fate.core.actions.MoveAction
import ru.terekhov.fate.core.descriptions.Representation
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationRepository

internal class GameTest {
    companion object {
        val moveToCity01Action = MoveAction("moveToCity01", "Неподалёку виднеется лавка торговца", "Перейти к лавке", "city01")
        val moveToDefaultAction = MoveAction("moveToDefault", "Можно пройти обратно на базар", "Вернуться на базар", "default")
        val descriptionPresenter = mockkClass(ActionResultListener::class)
    }

    @BeforeEach
    fun setUp() {
        clearMocks(descriptionPresenter)
        every {
            descriptionPresenter.showDescription(any())
        } just Runs
    }

    @Test
    fun `should pass proper description and actions to UI`() {
        // TODO: Rewrite, cause now constructor has side effect

        // Given
        val game = Game()
        game.locations = StubLocationRepository()
        game.setListener(descriptionPresenter)
        game.startGame()

        // Then
        val expectedDescription = Representation("Вы пришли на базар", listOf(moveToCity01Action))
        verify(exactly = 1) { descriptionPresenter.showDescription(expectedDescription) }
    }
}

class StubLocationRepository: LocationRepository {
    var locations = mapOf(
            "default" to Location(1, "Вы пришли на базар", listOf(GameTest.moveToCity01Action)),
            "city01" to Location(5, "Всем привет", listOf(GameTest.moveToDefaultAction)))
    override fun loadLocation(locationId: String): Location {
        return if (locationId in locations)
            locations[locationId]!!
        else Location(-1, "Несуществующая локация", listOf())
    }

}