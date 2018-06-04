"use strict";

/**
 * Class responsibility is to represent FATE dice rolls
 */
class FateDiceRoller {
    constructor() {
        // possible outcomes aka dice sides
        this.outcomes = [-1, 0, 1];
        // how many dice are rolled simultaneously
        this.diceQty = 4;
    }

    /**
     * Returns an array, each value corresponds to individual dice result
     * @returns {Array} individual dice results
     */
    roll() {
        let result = [];
        for (let i = 0; i < this.diceQty; i++) {
            result.push(this.outcomes[Math.floor(Math.random() * this.outcomes.length)]);
        }
        return result;
    }
}

module.exports = FateDiceRoller;