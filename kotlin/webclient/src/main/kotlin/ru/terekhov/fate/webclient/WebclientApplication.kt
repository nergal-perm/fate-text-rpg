package ru.terekhov.fate.webclient

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.terekhov.fate.core.actions.ActionHandler
import ru.terekhov.fate.core.actions.ActionResultListener
import ru.terekhov.fate.core.descriptions.Representation
import ru.terekhov.fate.core.locations.LocationRepository
import ru.terekhov.fate.core.model.LocationModel
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
    fun locationEntityGateway() = LocationJsonLoader()

    @Bean
    fun descriptionPresenter() = SimpleDescriptionPresenter()
}

class LocationJsonLoader: LocationRepository {
    override fun loadLocation(locationId: String): LocationModel {
        val mapper = jacksonObjectMapper()
        val locationFile = javaClass.classLoader.getResource("content/$locationId.json")
        return mapper.readValue(locationFile)
    }
}

class SimpleDescriptionPresenter: ActionResultListener {
    var representation: Representation? = null

    override fun showDescription(desc: Representation) {
        this.representation = desc
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
    private val state = HashMap<String, String>()
    override fun setValue(key: String, value: String) {
        state[key] = value
    }

    override fun getValue(key: String): String =
        when (state.containsKey(key)) {
            true -> state[key].toString()
            false -> ""
        }

}

class SpringWorldStateRepository: WorldStateRepository
class SpringLocationStateRepository: LocationStateRepository
class SpringLimboStateRepository: LimboStateRepository
class SpringCharacterStateRepository: CharacterStateRepository