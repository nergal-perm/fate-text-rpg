// @flow
"use strict";

import type UserAction from '../core/flow-types';
const EventEmitter = require('events');

class ActionsPlugin extends EventEmitter {
    constructor() {
        super();
    }

    handle(action: UserAction) {
        switch(action.type) {
            case 'move':
                this.emit('move', action.destination);
                break;
        }
    }
}

module.exports = ActionsPlugin;