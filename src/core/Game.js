"use strict";

class Game {
    constructor() {
        this.gameState = {
            isOver: false,
            stepCounter: 0
        };
        this.worldState = {
            currentLocationId: 1
        };
        this.plugins = {};
    }

    addPlugin(plugin) {
        if (!this.plugins[plugin.type]) {
            this.plugins[plugin.type] = plugin;
        }
    }

    run() {
        while (!this.gameState.isOver) {
            let stateChange = {};
            // Update user visible description based on worldState
            this.updateDescription();
            // Ask user for action and handle it
            stateChange = this.handleAction('user', {type: "move", direction: "S"});
            // Change worldState accordingly (and check for end game conditions?)
            // Choose AI action and handle it
            // Change worldState accordingly (and check for end game conditions?)
            this.changeWorldState();

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

    handleAction(user, param2) {

    }
}

module.exports = Game;