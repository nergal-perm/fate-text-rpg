"use strict";

const EventEmitter = require('events');


class Game extends EventEmitter {
    plugins: Object;
    gameState: Object;
    worldState: Object;

    constructor(plugins: Array<Object>, actionEmitter: Object) {
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

    addPlugin(plugin: Object) {
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
        }
    }

    move(movementAction: string) {
        this.worldState.location = {
            id: movementAction
        };
        this.updateDescription();
    }
}

module.exports = Game;