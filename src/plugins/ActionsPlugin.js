"use strict";

const EventEmitter = require('events');

class ActionsPlugin extends EventEmitter {
    constructor() {
        super();
    }

    handle(action) {
        switch(action.type) {
            case 'move':
                this.emit('move', action.destination);
                break;
        }
    }
}

module.exports = ActionsPlugin;