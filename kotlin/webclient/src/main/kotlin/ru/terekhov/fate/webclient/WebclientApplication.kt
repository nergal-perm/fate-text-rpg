package ru.terekhov.fate.webclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.terekhov.fate.core.Game
import ru.terekhov.fate.core.actions.MoveAction
import ru.terekhov.fate.core.descriptions.Description
import ru.terekhov.fate.core.descriptions.DescriptionPresenter
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationRepository

@SpringBootApplication
class WebclientApplication

fun main(args: Array<String>) {
    runApplication<WebclientApplication>(*args)
}

@Configuration
class GameConfiguration {
    @Bean
    fun createGame() = Game(locationEntityGateway(), descriptionPresenter())

    @Bean
    fun locationEntityGateway() = StubLocationEntityGateway()

    @Bean
    fun descriptionPresenter() = SimpleDescriptionPresenter()
}

class StubLocationEntityGateway: LocationRepository {
    companion object {
        val moveToCity01Action = MoveAction("moveToCity01", "Неподалёку виднеется лавка торговца", "Перейти к лавке", "city01")
        val moveToDefaultAction = MoveAction("moveToDefault", "Можно пройти обратно на базар", "Вернуться на базар", "default")
    }

    var locations = mapOf(
            "default" to Location(1, "Вы пришли на базар", listOf(moveToCity01Action)),
            "city01" to Location(5, "Всем привет", listOf(moveToDefaultAction)))
    override fun loadLocation(locationId: String): Location {
        return if (locationId in locations)
            locations[locationId]!!
        else Location(-1, "Несуществующая локация", listOf())
    }

}

class SimpleDescriptionPresenter: DescriptionPresenter {
    lateinit var description: Description

    override fun showDescription(description: Description) {
        this.description = description
    }
}