package ru.terekhov.fate.core.actions

import io.mockk.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.Game
import ru.terekhov.fate.core.GameTest
import ru.terekhov.fate.core.StubLocationRepository
import ru.terekhov.fate.core.locations.Location

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
            Location(1, "short description",
                    listOf(
                            MoveAction("id01", "someDesc", "someCall", "city02"),
                            MoveAction("id02", "someDesc", "someCall", "city03"),
                            MoveAction("id03", "someDesc", "someCall", "city04"),
                            MoveAction("id04", "someDesc", "someCall", "city05")))
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
            Location(1, "short description",
                    listOf(
                            MoveAction("id01", "someDesc", "someCall", "city02"),
                            MoveAction("id02", "someDesc", "someCall", "city03"),
                            MoveAction("id03", "someDesc", "someCall", "city04"),
                            MoveAction("id04", "someDesc", "someCall", "city05")))
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

        // When
        target.act(game)

        // Then
        verify { game.loadLocation("city02") }

    }
}