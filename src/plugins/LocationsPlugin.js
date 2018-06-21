"use strict";

class LocationsPlugin {

    constructor(locationLoader) {
        this.type = "location";
        this.locationLoader = locationLoader;
    }

    getLocation(currentLocationId) {
        return this.locationLoader.getLocation(currentLocationId);
    }

    update(worldState) {
        worldState.location = this.getLocation(worldState.location.id);
    }
}

module.exports = LocationsPlugin;