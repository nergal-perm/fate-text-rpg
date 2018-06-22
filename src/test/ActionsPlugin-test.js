"use strict";

const ActionsPlugin = require('../plugins/ActionsPlugin');
const EventEmitter = require('events');
const sinon = require('sinon');

const chai = require('chai');
chai.should();

describe('Actions Plugin', function() {
    let actionsPlugin;
    let eventEmitterSpy;

    beforeEach(function() {
        actionsPlugin = new ActionsPlugin();
        eventEmitterSpy = sinon.spy();
    });

    afterEach(function() {
        actionsPlugin.removeListener('move', eventEmitterSpy);
    });

    it('should extend EventEmitter', function() {
        (actionsPlugin instanceof EventEmitter).should.equal(true);
    });

    it('should handle movement actions', function() {
        actionsPlugin.on('move', eventEmitterSpy);

        actionsPlugin.handle({
            "type": "move",
            "destination":"65"
        });
        actionsPlugin.handle({
            "type": "move",
            "destination":"125"
        });

        eventEmitterSpy.callCount.should.equal(2);
        eventEmitterSpy.getCall(0).args[0].should.equal("65");
        eventEmitterSpy.getCall(1).args[0].should.equal("125");
    });
});