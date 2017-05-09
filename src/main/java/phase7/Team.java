package phase7;


import java.util.LinkedList;

/**
 * Created by keaton on 5/3/2017.
 * Keaton & Nida
 * IT 306-001
 * Spring 2017
 */
public class Team {
    private String teamName;
    private Coach teamCoach;
    private int playerCount;
    private String teamColor;
    private LinkedList<Player> players = new LinkedList<Player>();

    public Team() {

    }

    public Team(String name, Coach coach) {
        this();
        teamName = name;
        teamCoach = coach;
    }

    public String getTName() {return teamName;}
    public int getPlayerCount() {return players.size();}
    public String getTColor() {return teamColor;}
    public Coach getTCoach() {return teamCoach;}
    public LinkedList<Player> getPlayers() {return players;}

    public void setTName(String name) {
        if(name.length() > 0)
            teamName = name;
    }

    //The user should only be able to set the number of players to an appropriate team size
    //which is between 6 and 15, inclusive. There is a separate counter for the array.
    public void setPlayerCount(int count) {
        if(count >= 6)
            if(count <= 15)
                playerCount = count;
    }

    public void setTColor(String color) {
        if(color.length() > 0)
            teamColor = color;
    }

    public void setTCoach(Coach newCoach) {
        teamCoach = newCoach;
    }

    public void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }

    public String toString() {
        String output = "";
        output += "Team name: " + teamName + "\n";
        output += "Coach: " + teamCoach.toString() + "\n";
        output += "Team size: " + playerCount + "\n";
        output += "No. players currently on roster: " + players.size() + "\n";
        output += "Team color: " + teamColor;

        return output;
    }

    public String toString(int num){
        String output = "";

        output = teamName + "," + teamCoach.getCoachName() +
                "," + teamCoach.getCoachEmail() + "," + teamCoach.getCoachPhone() +
                "," + teamCoach.getPassword() + "," + this.getPlayerCount() + "," +
                teamColor;

        return output;
    }
}
