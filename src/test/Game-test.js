"use strict";

const chai = require('chai');
const Game = require('../core/Game.js');

chai.should();

describe('Main Cycle', function() {
    it('should be properly initialized', function() {
        let mainCycle = new Game();
        mainCycle.gameState.isOver.should.equal(false);
    });

    it('should run for three steps and then quit', function() {
        let mainCycle = new Game();
        const locationPlugin = {
            update: function () {
                return null;
            },
            type: "location"
        };
        mainCycle.addPlugin(locationPlugin);
        mainCycle.run();
        mainCycle.gameState.stepCounter.should.equal(3);
        mainCycle.description.should.equal("Game is over");
    });
});