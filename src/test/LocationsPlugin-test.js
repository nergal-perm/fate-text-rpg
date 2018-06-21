"use strict";
const LocationPlugin = require('../plugins/LocationsPlugin');
const LocationLoader = require('../core/JsonLocationLoader');

const chai = require('chai');
chai.should();

describe("Locations Plugin & Loader", function() {
    before(function() {
        const locationLoader = new LocationLoader(__dirname + "/../../resources/test/locations");
        this.locationPlugin = new LocationPlugin(locationLoader);
    });

    it('should be created with LocationLoader', function() {
        (this.locationPlugin.locationLoader !== undefined).should.equal(true);
    });

    it('should load test location with id 1', function() {
        let location = this.locationPlugin.getLocation("testLocation1");
        location.id.should.equal("01");
    });

    it('should present accessible actions', function() {
        let location = this.locationPlugin.getLocation("testLocation1");
        location.actions.length.should.equal(2);
    });
});