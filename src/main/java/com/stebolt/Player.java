package com.stebolt;

public class Player {
    String fname;
    String sname;
    String description;
    String dismissal;
    boolean dismissed = false;
    int ballsFaced = 0;
    int score = 0;
//    double average = 0;


    public Player(String fname, String sname, String description) {
        this.fname = fname;
        this.sname = sname;
        this.description = description;
    }

    public Player(){}

    public String toString() {
        return fname + " " + sname;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
        this.dismissed = true;
    }

    public void addToScore(int score){
        this.score += score;
//        calculateAverage();
    }

    public void addToBallsFaced(){
        this.ballsFaced += 1;
    }

    public double calculateAverage() {
        return this.ballsFaced > 0 ? ((double) this.score / (double) this.ballsFaced * 100) : 0.0d;

    }


}