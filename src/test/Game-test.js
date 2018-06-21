"use strict";

const chai = require('chai');
const Game = require('../core/Game.js');

chai.should();

describe('Main Cycle', function() {
    it('should be properly initialized', function() {
        let mainCycle = new Game();
        mainCycle.gameState.isOver.should.equal(false);
    });
});