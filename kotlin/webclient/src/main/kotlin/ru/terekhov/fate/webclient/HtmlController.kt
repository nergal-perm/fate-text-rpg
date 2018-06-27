package ru.terekhov.fate.webclient


import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import ru.terekhov.fate.core.actions.BaseAction

@Controller
class HtmlController(gameConfiguration: GameConfiguration, val presenter: SimpleDescriptionPresenter) {
    val game = gameConfiguration.createGame()

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Cool text RPG!"
        model["description"] = presenter.description.description
        model["actions"] = presenter.description.actions.map { it.render() }
        return "home"
    }

}

private fun BaseAction.render() = RenderedAction(
    id, description, callToAction
)

data class RenderedAction (
        val id: String,
        val description: String,
        val callToAction: String
)
