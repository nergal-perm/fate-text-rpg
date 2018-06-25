"use strict";

const chai = require('chai');
const CharacterCreator = require('../plugins/CharacterPlugin');

chai.should();

describe('character creation plugin', function () {
    it('should be properly initialized', function () {
        let reth = new CharacterCreator();
        reth.should.not.equal(undefined);
        reth.name.should.equal("Reth of the Andrali");
    });
});