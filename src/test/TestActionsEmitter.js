"use strict";

const EventEmitter = require("events");

class TestActionsEmitter extends EventEmitter {
    moveTo(locationId) {
        this.emit('move', {newLocation: locationId});
    }
}

module.exports = TestActionsEmitter;