package com.stebolt;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Match {
    public boolean inProgress = true;
    public boolean secondInnings = false;
    final private int overLimits;
    Team battingTeam;
    Team fieldingTeam;

    Player playerAtBat;
    Player playerAtBowlersEnd;

    Logger logger;

    ScoringEngine scoringEngine = new ScoringEngine();

    public Match(int overLimits, Team home, Team away, Logger logger) {
        this.overLimits = overLimits;
        this.logger = logger;
        tossTheCoin(home, away);
//        System.out.println(battingTeam.name + " to bat first.\nGame on!");
        logger.info(battingTeam.name + " to bat first. Game on!");
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

    void playAShot(int currentOver, int currentDelivery, int deliveryScore) {
        if (deliveryScore != 99)
            playerAtBat.addToScore(deliveryScore);
        switch (deliveryScore) {
            case 0:
                logger.info("{}.{}) {}: No score for {}", currentOver, currentDelivery, deliveryScore, playerAtBat);
                break;
            case 1:
                logger.info("{}.{}) {}: a single of the ball for {}", currentOver, currentDelivery, deliveryScore, playerAtBat);
                rotateTheStrike();
                break;
            case 2:
                logger.info("{}.{}) {}: {} makes a double", currentOver, currentDelivery, deliveryScore, playerAtBat);
                break;
            case 3:
                logger.info("{}.{}) : the fielders stop the boundary, but that's {} for {}", currentOver, currentDelivery, deliveryScore, playerAtBat);
                rotateTheStrike();
                break;
            case 4:
                logger.info("{}.{}) It carries to the rope for a boundary! {} for {}", currentOver, currentDelivery, deliveryScore, playerAtBat);
                break;
            case 6:
                logger.info("{}.{}) : and that's a massive {} from {}", currentOver, currentDelivery, deliveryScore, playerAtBat);
                break;
            default:
                // TODO - a better dismissal engine
                playerAtBat.setDismissal("Bowled!");
                logger.info("{}.{}) : Howzat! {} is out for {}", currentOver, currentDelivery, playerAtBat, playerAtBat.score);
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
        playAShot(currentOver, currentDelivery, scoringEngine.getShotOutcome());
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
        logger.debug("Rotate the strike");
    }

    public void bringInTheNextBatter() {
        if (battingTeam.getWickets() < battingTeam.squad.length - 1) {
            playerAtBat = battingTeam.squad[battingTeam.getWickets() + 1];
            logger.info("{} comes in to bat", playerAtBat);
        } else {
            logger.info("{} are all out for {}", battingTeam.name, battingTeam.getTeamScore());
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
        if (battingTeam.getTeamScore() == fieldingTeam.getTeamScore()) {
            logger.info("The match is tied!");
        } else if (battingTeam.getTeamScore() > fieldingTeam.getTeamScore()) {
            logger.info("The winners are {} by {} wickets"
                    , battingTeam.name, (battingTeam.squad.length -1 - battingTeam.getWickets()));
        } else
            logger.info("The winners are {} by {} runs"
                    ,  fieldingTeam.name, (fieldingTeam.getTeamScore() - battingTeam.getTeamScore()) );
    }
}

// TODO - A better commentary Engine

