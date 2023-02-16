package com.stebolt;

import java.util.Random;

public class ScoringEngine {
    Random r = new Random();

    public int getShotOutcome() {
        int chance = r.nextInt(100);
        if (chance < 6)
            return 99;   // 6 in 100 chance of a wicket
        else if (chance < 11 )
            return 6;  // + 5 chance of a 6
        else if (chance < 29 )
            return 4;
        else if (chance < 33)
            return 3;
        else if (chance < 45)
            return 2;
        else if (chance < 60)
            return 1;
        else
            return 0;
    }

}
