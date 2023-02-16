package com.stebolt;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Java Cricket!");
        Match match = new Match(20, squadBuilder("eng"), squadBuilder("aus"));
        match.playTheMatch();
    }

    static Team squadBuilder(String team) {
        switch (team) {
            case "eng":
                Player[] ENG_SQUAD = new Player[11];
                ENG_SQUAD[0] = new Player("Jos", "Buttler", "Opening batsman");
                ENG_SQUAD[1] = new Player("Jason", "Roy", "Opening batsman");
                ENG_SQUAD[2] = new Player("Joe", "Root", "Top order batsman");
                ENG_SQUAD[3] = new Player("Johnny", "Bairstow", "Middle order batsman");
                ENG_SQUAD[4] = new Player("Ben", "Stokes", "All rounder");
                ENG_SQUAD[5] = new Player("Harry", "Brook", "Middle order batsman");
                ENG_SQUAD[6] = new Player("Moeen", "Ali", "All rounder");
                ENG_SQUAD[7] = new Player("Chris", "Woakes", "Pace bowler");
                ENG_SQUAD[8] = new Player("Adil", "Rashid", "Spin bowler");
                ENG_SQUAD[9] = new Player("Jofra", "Archer", "Pace bowler");
                ENG_SQUAD[10] = new Player("Reece", "Topley", "Pace bowler");
                return new Team("England", ENG_SQUAD);
            case "aus":
                Player[] AUS_SQUAD = new Player[11];
                AUS_SQUAD[0] = new Player("David", "Warner", "Opening batsman");
                AUS_SQUAD[1] = new Player("Alex", "Cameron", "Opening batsman");
                AUS_SQUAD[2] = new Player("Steve", "Smith", "Top order batsman");
                AUS_SQUAD[3] = new Player("Marnus", "Labuschagne", "Middle order batsman");
                AUS_SQUAD[4] = new Player("Glenn", "Maxwell", "All rounder");
                AUS_SQUAD[5] = new Player("Travis", "Head", "Middle order batsman");
                AUS_SQUAD[6] = new Player("Alex", "Carey", "Middle order batsman");
                AUS_SQUAD[7] = new Player("Mitchell", "Starc", "Pace bowler");
                AUS_SQUAD[8] = new Player("Pat", "Cummins", "Pace bowler");
                AUS_SQUAD[9] = new Player("Adam", "Zampa", "Spin bowler");
                AUS_SQUAD[10] = new Player("Josh", "Hazlewood", "Pace bowler");
                return new Team("Australia", AUS_SQUAD);
        }
        return null;
    }
}
