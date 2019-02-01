package com.sots.api.util.data;

import java.util.UUID;

public class Owner {
    private final UUID pipeID;
    private final UUID augmentID;

    public Owner(UUID pipeID, UUID augmentID) {
        this.pipeID = pipeID;
        this.augmentID = augmentID;
    }

    public UUID getAugmentID() {
        return augmentID;
    }

    public UUID getPipeID() {
        return pipeID;
    }
}
