"use strict";

const chai = require('chai');
const Game = require('../core/Game.js');

chai.should();

describe('Main Cycle', function() {
    it('should be properly initialized', function() {
        let mainCycle = new Game([{
            type:"location"
        }]);
        mainCycle.gameState.isOver.should.equal(false);
        (mainCycle.plugins["location"] !== undefined).should.equal(true);
    });
});