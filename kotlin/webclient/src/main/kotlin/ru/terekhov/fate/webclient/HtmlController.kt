package ru.terekhov.fate.webclient


import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.terekhov.fate.core.model.ActionModel

@Controller
class HtmlController(gameConfiguration: GameConfiguration, val presenter: SimpleDescriptionPresenter) {
    val game = gameConfiguration.createGame()

    @GetMapping("/")
    fun renderHomePage(model: Model): String {
        model["representation"] = presenter.representation!!.description
        model["actions"] = presenter.representation!!.actions.map { it.render() }
        return "home"
    }

    @GetMapping("/action/{id}")
    fun renderActionResult(@PathVariable id:String, model: Model): String {
        game.handleAction(id)
        model["representation"] = presenter.representation!!.description
        model["actions"] = presenter.representation!!.actions.map { it.render() }
        return "home"
    }

}

private fun ActionModel.render() = RenderedAction(
    id,  description
)

data class RenderedAction (
        val id: String,
        val callToAction: String
)
