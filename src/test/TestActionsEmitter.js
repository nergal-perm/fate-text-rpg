"use strict";

const EventEmitter = require("events");

class TestActionsEmitter extends EventEmitter {
    moveTo(locationId) {
        this.emit('move', locationId);
    }
}

module.exports = TestActionsEmitter;