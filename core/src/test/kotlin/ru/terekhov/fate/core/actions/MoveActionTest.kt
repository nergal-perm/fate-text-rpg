package ru.terekhov.fate.core.actions

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.Game
import ru.terekhov.fate.core.model.ActionModel
import ru.terekhov.fate.core.model.ActionType.MOVE
import ru.terekhov.fate.core.model.GenericOutcome
import ru.terekhov.fate.core.model.LocationModel

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
            LocationModel("1", "short description", null,
                    arrayOf(
                            ActionModel("id01", MOVE, "someDesc",
                                    null,null, GenericOutcome("Some description", "game.location=city02")),
                            ActionModel("id02", MOVE, "someDesc",
                                    null, null, GenericOutcome("Some description", "game.location=city03")),
                            ActionModel("id03", MOVE, "someDesc",
                                    null, null,GenericOutcome("Some description", "game.location=city04")),
                            ActionModel("id04", MOVE, "someDesc",
                                    null, null,GenericOutcome("Some description", "game.location=city05"))
                    )
            )
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
            LocationModel("1", "short description", null,
                    arrayOf(
                            ActionModel("id01", MOVE, "someDesc",
                                    null,null, GenericOutcome("Some description", "game.location=city02")),
                            ActionModel("id02", MOVE, "someDesc",
                                    null, null, GenericOutcome("Some description", "game.location=city03")),
                            ActionModel("id03", MOVE, "someDesc",
                                    null, null,GenericOutcome("Some description", "game.location=city04")),
                            ActionModel("id04", MOVE, "someDesc",
                                    null, null,GenericOutcome("Some description", "game.location=city05"))
                    )
            )
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