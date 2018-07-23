package ru.terekhov.fate.core.actions

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.Game
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.model.ActionModel
import ru.terekhov.fate.core.model.GenericDescription
import ru.terekhov.fate.core.model.LocationModel
import ru.terekhov.fate.core.model.ActionType.*

internal class MoveActionTest {
    companion object {
        val game = mockkClass(Game::class)
    }


    @BeforeEach
    fun setUpAllTests() {
        clearMocks(game)
        every {
            game.loadLocation(any())
        } just Runs
    }

    @Test
    fun `should be properly initialized`() {
        // Given
        val target = MoveAction("id01", "someDesc", "someCall", "city02")

        // Then
        assert(target.type == ActionType.MOVE)
        assert(target.id == "id01")
        assert(target.description == "someDesc")
        assert(target.callToAction == "someCall")
    }

    @Test
    fun `should validate right MoveAction against Game`() {
        // Mocking interaction
        every {
            game.currentLocation
        } answers {
            LocationModel("1", GenericDescription("short description", null), null,
                    arrayOf(
                            ActionModel("id01", MOVE, GenericDescription("someDesc", "someCall"),
                                    null,null,"city02"),
                            ActionModel("id02", MOVE, GenericDescription("someDesc", "someCall"),
                                    null, null, "city03"),
                            ActionModel("id03", MOVE, GenericDescription("someDesc", "someCall"),
                                    null, null,"city04"),
                            ActionModel("id04", MOVE, GenericDescription("someDesc", "someCall"),
                                    null, null,"city05")))
        }

        // Given
        val target = MoveAction("id01", "someDesc", "someCall", "city02")

        // When
        val actual = target.validate(game)

        // Then
        assert(actual)

    }

    @Test
    fun `should not validate wrong MoveAction against Game`() {
        // Mocking interaction
        every {
            game.currentLocation
        } answers {
            LocationModel("1", GenericDescription("short description", null), null,
                    arrayOf(
                            ActionModel("id01", MOVE, GenericDescription("someDesc", "someCall"),
                                    null,null,"city02"),
                            ActionModel("id02", MOVE, GenericDescription("someDesc", "someCall"),
                                    null, null, "city03"),
                            ActionModel("id03", MOVE, GenericDescription("someDesc", "someCall"),
                                    null, null,"city04"),
                            ActionModel("id04", MOVE, GenericDescription("someDesc", "someCall"),
                                    null, null,"city05")))
        }

        // Given
        val target = MoveAction("wrongId", "someDesc", "someCall", "city02")

        // When
        val actual = target.validate(game)

        // Then
        assert(!actual)

    }

    @Test
    fun `should handle user movement`() {
        // Given
        val target = MoveAction("moveToId", "someDesc", "someCall", "city02")
        val expectedDestination = "game.currentLocation = city02"

        // When
        val actual = target.act(game)

        // Then
        assertThat(expectedDestination).isIn(actual)
        assertThat(actual.size).isEqualTo(1)
    }
}