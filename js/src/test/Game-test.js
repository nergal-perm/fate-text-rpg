"use strict";

const chai = require('chai');
const Game = require('../core/Game.js');
const TestActionsEmitter = require("./TestActionsEmitter");
const LocationsPlugin = require("../plugins/LocationsPlugin");

chai.should();

describe('Main Cycle', function() {
    const actionsEmitter = new TestActionsEmitter();
    const stubLocationLoader = {
        getLocation: function(locationId) {
            return { id: locationId };
        }
    };

    before(function() {
        this.mainCycle = new Game([new LocationsPlugin(stubLocationLoader)], actionsEmitter);
    });

    it('should be properly initialized', function() {
        this.mainCycle.gameState.isOver.should.equal(false);
        (this.mainCycle.plugins["location"] !== undefined).should.equal(true);
    });

    it('should properly handle movement actions', function() {
        this.mainCycle.worldState.location.id.should.equal(1);
        actionsEmitter.moveTo(65);
        this.mainCycle.worldState.location.id.should.equal(65);
    });

});