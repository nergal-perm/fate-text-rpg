"use strict";

class CharacterCreator {
    constructor() {
        this.name = "Reth of the Andrali";
        this.highConcept = {
            title: "Suncaller of the Andral Desert"
        };
        this.trouble = {
            title: "Steel Assassins Want Me Dead"
        };
        this.aspects = [{
            title: "My Kung Fu Is The Strongest"
        }, {
            title: "Crush On Avasa"
        }, {
            title: "I Can Learn from Serio’s Experience"
        }];
        this.approaches = {
            careful: 2,
            clever: 1,
            flashy: 0,
            forceful: 3,
            quick: 2,
            sneaky: 1
        };
        this.stunts = [
            {
                title: "Stance of the Defiant Sun",
                conditionTags: ["combat", "hand-to-hand"],
                action: {
                    type: "defense",
                    approach: "forceful"
                },
                bonus: 2
            }
        ]
    }
}

module.exports = CharacterCreator;