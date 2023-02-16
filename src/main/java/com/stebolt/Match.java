package com.stebolt;

import java.util.Random;

public class Match {
    public boolean inProgress = true;
    public boolean secondInnings = false;
    final private int overLimits;
    Team battingTeam;
    Team fieldingTeam;

    Player playerAtBat;
    Player playerAtBowlersEnd;


    ScoringEngine scoringEngine = new ScoringEngine();

    public Match(int overLimits, Team home, Team away) {
        this.overLimits = overLimits;
        tossTheCoin(home, away);
        System.out.println(battingTeam.name + " to bat first.\nGame on!");
    }

    public void tossTheCoin(Team home, Team away) {
        Random r = new Random();
        int chance = r.nextInt(2);
        if (chance == 1) {
            this.battingTeam = home;
            this.fieldingTeam = away;
        } else {
            this.battingTeam = away;
            this.fieldingTeam = home;
        }
    }

    void playAShot(int deliveryScore) {
        if (deliveryScore != 99)
            playerAtBat.addToScore(deliveryScore);
        switch (deliveryScore) {
            case 0:
                System.out.println(deliveryScore + ": No score for " + playerAtBat);
                break;
            case 1:
                System.out.println(deliveryScore + ": a single of the ball for " + playerAtBat);
                rotateTheStrike();
                break;
            case 2:
                System.out.println(deliveryScore + ": " + playerAtBat + " makes a double");
                break;
            case 3:
                System.out.println(deliveryScore + ": the fielders stop the boundary, but that's 3 for " + playerAtBat);
                rotateTheStrike();
                break;
            case 4:
                System.out.println(deliveryScore + ": carries to the rope for a boundary! Four runs for " + playerAtBat);
                break;
            case 6:
                System.out.println(deliveryScore + ": and that's a massive shot from " + playerAtBat);
                break;
            default:
                // TODO - a better dismissal engine
                playerAtBat.setDismissal("Bowled!");
                System.out.println("Howzat?! " + playerAtBat + " is out for " + playerAtBat.score + "!");
                bringInTheNextBatter();
                break;
        }
    }

    void playTheMatch() {
        playAnInnings();
        switchTeams();
        this.secondInnings = true;
        this.inProgress = true;
        playAnInnings();
        decideTheWinner();
    }

    void playAnInnings() {
        int currentOver = 1;
        setBatters();
        while (currentOver <= this.overLimits & this.inProgress) {
            playAnOver(currentOver);
            rotateTheStrike();
            currentOver++;
        }
        this.inProgress = false;
        battingTeam.printScorecard();
    }

    void playAnOver(int currentOver) {
        int currentDelivery = 1;
        while (currentDelivery <= 6 & this.inProgress) {
            playADelivery(currentOver, currentDelivery);
            currentDelivery++;
        }
    }

    void playADelivery(int currentOver, int currentDelivery) {
        System.out.print("Over: " + currentOver + " | Delivery: " + currentDelivery + ")\t\t");
        playAShot(scoringEngine.getShotOutcome());
        wasTheChaseSuccessfulYet();
    }

    void wasTheChaseSuccessfulYet(){
        if(secondInnings & this.battingTeam.getTeamScore() > this.fieldingTeam.getTeamScore())
            this.inProgress = false;
    }

    public void rotateTheStrike() {
        Player x = playerAtBat;
        playerAtBat = playerAtBowlersEnd;
        playerAtBowlersEnd = x;
        System.out.println("Rotate the strike");
    }

    public void bringInTheNextBatter() {
        if (battingTeam.getWickets() < battingTeam.squad.length - 1) {
            playerAtBat = battingTeam.squad[battingTeam.getWickets() + 1];
            System.out.println(playerAtBat + " comes in to bat");
        } else {
            System.out.println("All out!");
            this.inProgress = false;
        }
    }

    public void setBatters() {
        this.playerAtBat = battingTeam.squad[0];
        this.playerAtBowlersEnd = battingTeam.squad[1];
    }

    public void switchTeams() {
        Team x = battingTeam;
        this.battingTeam = this.fieldingTeam;
        this.fieldingTeam = x;
    }

    public void decideTheWinner() {
        // TODO - can I make this comparison cleaner ?
        System.out.println();
        if (battingTeam.getTeamScore() == fieldingTeam.getTeamScore()) {
            System.out.println("The match is tied!");
        } else if (battingTeam.getTeamScore() > fieldingTeam.getTeamScore()) {
            System.out.println("The winners are " + battingTeam.name + " by "
                    + (battingTeam.squad.length -1 - battingTeam.getWickets()) + " wickets");
        } else
            System.out.println("The winners are " + fieldingTeam.name + " by "
                    + (fieldingTeam.getTeamScore() - battingTeam.getTeamScore()) + " runs");
    }
}

// TODO - A better commentary Engine

