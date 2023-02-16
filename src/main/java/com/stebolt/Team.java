package com.stebolt;

public class Team {
    String name;
    Player[] squad;

    public Team(String name, Player[] squad){
        this.name = name;
        this.squad = squad;
    }

    public int getWickets() {
        int wickets = 0;
        for (Player player : this.squad) {
            if (player.dismissed) {
                wickets++;
            }
        }
        return wickets;
    }

    public int getTeamScore() {
        int score = 0;
        for (Player player : this.squad) {
            score+= player.score;
        }
        return score;
    }

    public void printScorecard(){
        // TODO - Nicer formatted scorecard
        System.out.print("-----" + this.name + "\t");
        System.out.println(getTeamScore() + "/" + getWickets() + "------");
        for (Player player : this.squad) {
            System.out.println(player.fname.charAt(0) + ". "
                                + player.sname
                                + ": \t\t"
                                + player.score
                                + "\t dismissed: "
                                + player.dismissed
                                + "\t balls faced: "
                                + player.ballsFaced
                                + "\t batting average: "
                                + (int) player.calculateAverage()
                                ) ;
        }
    }
}
