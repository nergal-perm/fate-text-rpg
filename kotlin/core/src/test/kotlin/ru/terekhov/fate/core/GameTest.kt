package ru.terekhov.fate.core

import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.actions.ActionResultListener
import ru.terekhov.fate.core.descriptions.Representation
import ru.terekhov.fate.core.locations.LocationRepository
import ru.terekhov.fate.core.model.ActionModel
import ru.terekhov.fate.core.model.ActionType
import ru.terekhov.fate.core.model.GenericDescription
import ru.terekhov.fate.core.model.LocationModel

internal class GameTest {
    companion object {
        val moveToCity01Action = ActionModel("moveToCity01", ActionType.MOVE,
                GenericDescription("Неподалёку виднеется лавка торговца", "Перейти к лавке"),
                null, null,"city01")
        val moveToDefaultAction = ActionModel("moveToDefault", ActionType.MOVE,
                GenericDescription("Можно пройти обратно на базар", "Вернуться на базар"),
                null, null, "default")
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
        val expectedDescription = Representation("Вы пришли на базар", arrayOf(moveToCity01Action))
        verify(exactly = 1) { descriptionPresenter.showDescription(expectedDescription) }
    }
}

class StubLocationRepository: LocationRepository {
    var locations = mapOf(
            "default" to LocationModel("1", GenericDescription("Вы пришли на базар", null),
                    null, arrayOf(GameTest.moveToCity01Action)),
            "city01" to LocationModel("5", GenericDescription("Всем привет", null),
                    null, arrayOf(GameTest.moveToDefaultAction)))
    override fun loadLocation(locationId: String): LocationModel {
        return if (locationId in locations)
            locations[locationId]!!
        else LocationModel("-1", GenericDescription("Несуществующая локация", null),
                null, arrayOf())
    }

}