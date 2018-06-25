"use strict";
const fs = require("fs");

class JsonLocationLoader {
    locationsPath: string;

    constructor(folder: string) {
        this.locationsPath = folder;
    }

    getLocation(locationId: string) {
        let locationJson: string = (fs.readFileSync(this.locationsPath + '\\' + locationId + '.json')).toString('utf8');
        return JSON.parse(locationJson);
    }
}

module.exports = JsonLocationLoader;