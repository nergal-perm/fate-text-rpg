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

    }
}

module.exports = LocationsPlugin;