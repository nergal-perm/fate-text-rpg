"use strict";

class LocationsPlugin {
    constructor() {
        this.type = "location";
        this.locations = this.getLocations();
    }

    static getLocations() {
        return {
            1: {
                description: "Весь день вы потратили на бесплодные поиски. Вечером пришли на базар",
                possibleMoves: [{
                    toLocation: 2,
                    action: "move",
                    description: "Подойти к кабаку"
                }]
            }
        };
    }

    update(worldState) {

    }
}

module.exports = LocationsPlugin;