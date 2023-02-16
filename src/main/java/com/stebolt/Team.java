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

    public String printScorecard(){
        StringBuilder sb = new StringBuilder(2000);
        sb.append("-----" + this.name + "\t");
        sb.append(getTeamScore() + "/" + getWickets() + "------\n");
        for (Player player : this.squad) {
            sb.append(player.fname.charAt(0) + ". "
                    + player.sname
                    + ": \t\t"
                    + player.score
                    + "\t dismissed: "
                    + player.dismissed
                    + "\t balls faced: "
                    + player.ballsFaced
                    + "\t batting average: "
                    + (int) player.calculateAverage()
                    +"\n"
            );
        }
        return sb.toString();
    }
}
