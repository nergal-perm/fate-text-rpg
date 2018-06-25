// @flow
export default class UserAction {
    id: string;
    type: string;
    description: string;
    callToAction: string;
    destination: string;
};

/*
export type UserAction = {
    "dice": {
        "opposition": {"skill": "Athletics", "level": "+2"},
        "failure": {
            "description":"Lorem ipsum",
            "outcome": {"???"},
            "nextAction": {"action"}
        },
        "tie": { "" },
        "succeed": { "" },
        "style": { "" },
    }
};
*/