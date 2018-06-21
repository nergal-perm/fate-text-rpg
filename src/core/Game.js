"use strict";

const EventEmitter = require('events');


class Game extends EventEmitter {
    constructor(plugins, actionEmitter) {
        super();
        this.gameState = {
            isOver: false,
            stepCounter: 0
        };
        this.worldState = {
            location: {
                id: 1
            }
        };
        this.plugins = {};
        for (let index = 0, len = plugins.length; index < len; index++) {
            this.addPlugin(plugins[index]);
        }
        actionEmitter.on("move", this.move.bind(this));
    }

    addPlugin(plugin) {
        if (!this.plugins[plugin.type]) {
            this.plugins[plugin.type] = plugin;
        }
    }

    updateDescription() {
        this.plugins["location"].update(this.worldState);
    }

    changeWorldState() {
        this.gameState.stepCounter++;
        if (this.gameState.stepCounter === 3) {
            this.gameState.isOver = true;
            this.description = "Game is over";
        }
    }

    move(movementAction) {
        this.worldState.location = {
            id: movementAction.newLocation
        };
        this.updateDescription();
    }
}

module.exports = Game;