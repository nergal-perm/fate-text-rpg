"use strict";

class LocationsPlugin {
    type: string;
    locationLoader: Object;


    constructor(locationLoader: Object) {
        this.type = "location";
        this.locationLoader = locationLoader;
    }

    getLocation(currentLocationId: string) {
        return this.locationLoader.getLocation(currentLocationId);
    }

    update(worldState: Object) {
        worldState.location = this.getLocation(worldState.location.id);
    }
}

module.exports = LocationsPlugin;