"use strict";
const fs = require("fs");

class JsonLocationLoader {
    constructor(folder) {
        this.locationsPath = folder;
    }

    getLocation(locationId) {
        let locationJson = fs.readFileSync(this.locationsPath + '\\' + locationId + '.json');
        return JSON.parse(locationJson);
    }
}

module.exports = JsonLocationLoader;