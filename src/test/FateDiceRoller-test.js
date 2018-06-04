"use strict";

const chai = require('chai');
const DiceRoller = require('../core/FateDiceRoller');

chai.should();


describe('DiceRoller', function () {
    it('should be properly initialized', function () {
        let diceRoller = new DiceRoller;
        diceRoller.outcomes.length.should.equal(3);
        diceRoller.diceQty.should.equal(4);
    });

    it('should roll 4 dice', function() {
        let diceRoller = new DiceRoller();
        for (let i = 0; i < 1000; i++) {
            let actualRoll = diceRoller.roll();
            actualRoll.length.should.equal(4);
        }
    })
});
