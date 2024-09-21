package com.mkornaszewska.helpers;

import java.util.Random;

public class AdditionalNeedsHelper {

    private String[] additionalNeeds = {"Pets", "Smoking", "Parking Spot"};
    private Random random = new Random();


    public int getAdditionalNeedsIndex() {
        return random.nextInt(additionalNeeds.length);
    }

    public String getAdditionalNeed() {
        int index = getAdditionalNeedsIndex();
        return additionalNeeds[index];
    }
}
