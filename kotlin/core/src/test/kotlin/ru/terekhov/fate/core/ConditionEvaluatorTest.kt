package ru.terekhov.fate.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.terekhov.fate.core.states.GameStateRepository
import ru.terekhov.fate.core.utils.ConditionEvaluator
import ru.terekhov.fate.core.utils.ConditionResult

class ConditionEvaluatorTest {

    @Test
    fun `should distinct negative and positive`() {
        // Given
        val target = ConditionEvaluator(StubGameStateRepository())

        // When
        target.eval("!game.phase eq combat")

        // Then
        assertThat(target.negative).isEqualTo(true)
        assertThat(target.argCount).isEqualTo(4)

        // When
        target.eval("game.phase eq combat")

        // Then
        assertThat(target.negative).isEqualTo(false)
        assertThat(target.argCount).isEqualTo(4)
    }

    @Test
    fun `should evaluate conditions against gameState`() {
        // Given
        val stateRep = StubGameStateRepository()
        val state = HashMap<String, String>()
        state["phase"] = "combat"
        stateRep.setState(state)
        val target = ConditionEvaluator(stateRep)

        assertThat(target.eval("game.phase eq combat")).isEqualTo(ConditionResult("game.phase eq combat", true))
        assertThat(target.eval("!game.phase eq combat")).isEqualTo(ConditionResult("!game.phase eq combat", false))
    }
}

class StubGameStateRepository: GameStateRepository {
    var state: Map<String, String> = HashMap()

    override fun getValue(key: String): String {
        return state[key] ?: "false"
    }

}

fun StubGameStateRepository.setState(state: Map<String, String>) {
    this.state = state
}