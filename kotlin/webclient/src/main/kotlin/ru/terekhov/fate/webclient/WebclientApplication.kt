package ru.terekhov.fate.webclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.terekhov.fate.core.Game
import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.ActionResultListener
import ru.terekhov.fate.core.actions.MoveAction
import ru.terekhov.fate.core.descriptions.Description
import ru.terekhov.fate.core.locations.Location
import ru.terekhov.fate.core.locations.LocationRepository
import ru.terekhov.fate.core.states.*
import ru.terekhov.fate.core.utils.AbstractEngineFactory

@SpringBootApplication
class WebclientApplication

fun main(args: Array<String>) {
    runApplication<WebclientApplication>(*args)
}

@Configuration
class GameConfiguration {
    @Bean
    fun createGame(): ActionHandler {
        val factory = SpringEngineFactory(SpringGameStateRepository(), SpringWorldStateRepository(),
                SpringLocationStateRepository(), SpringLimboStateRepository(), SpringCharacterStateRepository(),
                locationEntityGateway())
        val game = factory.createGameEngine()
        game.setListener(descriptionPresenter())
        game.startGame()
        return game
    }

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

    private var locations = mapOf(
            "default" to Location(1, "Вы пришли на базар", listOf(moveToCity01Action)),
            "city01" to Location(5, "Всем привет", listOf(moveToDefaultAction)))
    override fun loadLocation(locationId: String): Location {
        return if (locationId in locations)
            locations[locationId]!!
        else Location(-1, "Несуществующая локация", listOf())
    }

}

class SimpleDescriptionPresenter: ActionResultListener {
    lateinit var description: Description

    override fun showDescription(desc: Description) {
        this.description = desc
    }
}

class SpringEngineFactory(
        gameStateRepository: GameStateRepository,
        worldStateRepository: WorldStateRepository,
        locationStateRepository: LocationStateRepository,
        limboStateRepository: LimboStateRepository,
        characterStateRepository: CharacterStateRepository,
        locationRepository: LocationRepository) :
            AbstractEngineFactory(gameStateRepository, worldStateRepository,locationStateRepository,
                    limboStateRepository, characterStateRepository, locationRepository)

class SpringGameStateRepository: GameStateRepository {
    override fun getValue(key: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class SpringWorldStateRepository: WorldStateRepository
class SpringLocationStateRepository: LocationStateRepository
class SpringLimboStateRepository: LimboStateRepository
class SpringCharacterStateRepository: CharacterStateRepository