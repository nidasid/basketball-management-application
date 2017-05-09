package phase7;

import javax.swing.*;
import java.awt.*;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.io.*;
import java.util.Scanner;

/**
 * Created by keaton on 5/3/2017.
 * Keaton & Nida
 * IT 306-001
 * Spring 2017
 */
public class FCYBA {
    public static void main (String[] args) {
        JOptionPane.showMessageDialog(null, "*TESTING INSTRUCTIONS*  \n\n" +
                "If you are logging in as a coach: \n" +
                "Coach Username: jsaunders@coaches.com \n" +
                "Coach Password: password \n\n" +
                "If you are logging in as a player: \n" +
                "Player Username: jbond@players.com \n" +
                "Player Password: password");

        JOptionPane.showMessageDialog(null,"Welcome to the Fairfax County Youth Basketball Association (FCYBA) Team" +
                "Management software. \n\nPlease login with your credentials.");

        loadUserData();
    }

    private static void loadUserData(){
        Team team = new Team();
        LinkedList<Player> players = new LinkedList<Player>();

        String playerData = "../../test/java/phase7/player_data.txt";
        String teamData = "../../test/java/phase7/team_data.txt";
        Coach coach = new Coach();

        try {

            if(playerData == null || teamData == null)
            {
                throw new FileNotFoundException();
            }


            File file1 = new File(playerData);

            Scanner playerScan = new Scanner(file1);

            while(playerScan.hasNextLine()){
                Player newPlayer = new Player();
                String player = playerScan.nextLine();
                String[] playerFields =  player.split(",");

                newPlayer.setPName(playerFields[0]);
                newPlayer.setPlayerEmail(playerFields[1]);
                newPlayer.setPassword(playerFields[2]);
                newPlayer.setGamesPlayed(Integer.parseInt(playerFields[3]));
                newPlayer.setAssists(Integer.parseInt(playerFields[4]));
                newPlayer.setPoints(Integer.parseInt(playerFields[5]));
                newPlayer.setRebounds(Integer.parseInt(playerFields[6]));
                newPlayer.setSteals(Integer.parseInt(playerFields[7]));

                players.add(newPlayer);

            }

            playerScan.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "File was not found. System will now terminate.");
            System.exit(-1);
        }

        try{
            Scanner teamScan = new Scanner(new File(teamData));

            String fileTeam = teamScan.nextLine();
            String[] teamFields = fileTeam.split(",");

            coach.setCoachName(teamFields[1]);
            coach.setCoachEmail(teamFields[2]);
            coach.setCoachPhone(teamFields[3]);
            coach.setPassword(teamFields[4]);

            team.setTName(teamFields[0]);
            team.setTCoach(coach);
            team.setPlayerCount(Integer.parseInt(teamFields[5]));
            team.setTColor(teamFields[6]);

            for(int i = 0; i < players.size(); i++){
                team.addPlayer(players.get(i));
            }

            teamScan.close();
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Team data file could not be found. System will now terminate.");
            System.exit(-1);
        }

        login(team, players);

    }

    private static void saveUserData(Team team) {
        String playerData = "./src//main/resources/player_data_save.txt";
        String teamData = "./src/main/resources/team_data_save.txt";

        /**
         * To write to the player_data.txt file.
         * Does not save if there is an error.
         * Overwrites whole file with new data.
         */
        try {
            PrintWriter writer = new PrintWriter(playerData, "UTF-8");
            LinkedList<Player> teamPlayers = team.getPlayers();

            for(int i = 0; i < team.getPlayerCount(); i++){
                writer.println(teamPlayers.get(i).toString(1));
            }

            writer.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error writing to file. " +
                    "\nSystem will terminate without saving.");
            System.exit(-1);
        }

        JOptionPane.showMessageDialog(null, "Players saved successfully.");

        /**
         * To write to the team_data.txt file.
         * Does not save if there is an error.
         * Overwrites whole file with new data.
         * Updated coach information written here.
         */
        try{
            PrintWriter writer = new PrintWriter(teamData, "UTF-8");
            writer.println(team.toString(1));
            writer.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error writing to file. " +
                    "\nSystem will terminate without saving.");
            System.exit(-1);
        }

        JOptionPane.showMessageDialog(null, "Team saved successfully.");
    }

    private static void login(Team team, LinkedList<Player> players){
        int reply = JOptionPane.showConfirmDialog(null, "Are you the coach?");

        if(reply == JOptionPane.YES_OPTION)
        {

            JFrame frame = new JFrame();
            JPanel panel= new JPanel(new BorderLayout(15, 15));
            JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
            JTextField usernameField = new JTextField(10);
            JTextField passwordField = new JPasswordField(10);

            panel.add(label, BorderLayout.WEST);

            label.add(new JLabel("Username:"));
            label.add(usernameField);
            label.add(new JLabel("\nPassword:"));
            label.add(passwordField);

            String username;// = usernameField.getText();
            String password;// = passwordField.getText();

            try
            {
                boolean bool = false;
                while(bool == false)
                {
                    JOptionPane.showMessageDialog(frame, panel, "Coach Login", JOptionPane.QUESTION_MESSAGE);
                    username = usernameField.getText();
                    password = passwordField.getText();
                    if (username.equals("")) {
                        JOptionPane.showMessageDialog(null, "Your username field cannot be blank.");
                        bool = false;
                    }
                    else if (password.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Your password field cannot be blank.");
                        bool = false;
                    }
                    if(team.getTCoach().getCoachEmail().equals(username) && team.getTCoach().getPassword().equals(password))
                    {
                        coachMenu(team, players);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Your credentials did not match the records - please try again.");
                        bool = false;
                    }
                }

            }
            catch (IllegalFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Error occurred.");
            }
        }
        else if(reply == JOptionPane.NO_OPTION)
        {

            JFrame frame = new JFrame();
            JPanel panel= new JPanel(new BorderLayout(15, 15));
            JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
            JTextField usernameField = new JTextField(10);
            JTextField passwordField = new JPasswordField(10);

            panel.add(label, BorderLayout.WEST);

            label.add(new JLabel("Username:"));
            label.add(usernameField);
            label.add(new JLabel("\nPassword:"));
            label.add(passwordField);

            String username;// = usernameField.getText();
            String password;// = passwordField.getText();

            try
            {
                boolean bool = false;
                boolean goodOnce;
                while(bool == false)
                {
                    JOptionPane.showMessageDialog(frame, panel, "Player Login", JOptionPane.QUESTION_MESSAGE);
                    goodOnce = false;

                    username = usernameField.getText();
                    password = passwordField.getText();

                    if (username.equals("")) {
                        JOptionPane.showMessageDialog(null, "Your username field cannot be blank.");
                        bool = false;
                    }
                    else if (password.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Your password field cannot be blank.");
                        bool = false;
                    }
                    else {

                        for (int i = 0; i < players.size(); i++){
                            if (players.get(i).getPlayerEmail().equals(username) && players.get(i).getPassword().equals(password)) {
                                playerMenu(players, i, team);
                                goodOnce = true;
                            }
                        }

                        if(!goodOnce) {
                            JOptionPane.showMessageDialog(null, "Your credentials did not match any " +
                                    "of the players' - please try again.");
                            bool = false;
                        }
                    }
                }

            }
            catch (IllegalFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Error occurred.");
            }
        }

    }

    private static void reports(Team team){
        int menuChoice = -1;
        String output = "";
        LinkedList<Player> playerList = team.getPlayers();

        boolean bool = false;
        Player temp;

        menuChoice = Integer.parseInt(JOptionPane.showInputDialog("Please select the report you want to view:\n\n"
                + "1. Alphabetically\n"
                + "2. Points scored\n"
                + "3. Games played\n"));

        if(menuChoice == 1){
            /**
             * Insertion sort alphabetical based on name.
             */
            for(int i = 0; i < playerList.size(); i++){
                for(int j = 0; j < (playerList.size()-1); j++){
                    if(playerList.get(j).getPName().toLowerCase().charAt(0) > playerList.get(j+1).getPName().toLowerCase().charAt(0)){
                        temp = playerList.get(j);
                        playerList.set(j, playerList.get(j+1));
                        playerList.set(j+1, temp);
                    }
                    else if(playerList.get(j).getPName().toLowerCase().charAt(0) == playerList.get(j+1).getPName().toLowerCase().charAt(0)){
                        for(int k = 1; k < playerList.get(j).getPName().length() && k < playerList.get(j+1).getPName().length(); k++){
                            if(playerList.get(j).getPName().toLowerCase().charAt(k) > playerList.get(j+1).getPName().toLowerCase().charAt(k)){
                                temp = playerList.get(j);
                                playerList.set(j, playerList.get(j+1));
                                playerList.set(j+1, temp);
                                bool = true;
                            }
                            if(bool)
                                break;
                        }
                    }
                }
            }

            output += "Sorted on player name:\n\n";
            output += "Player name | Games played | Points scored | Assists | Rebounds | Steals\n";

            /*for(int i = 0; i < playerList.size(); i++){
                output += (i+1) + ". " + playerList.get(i).getPName() + " | " + playerList.get(i).getGamesPlayed() + " | "
                        + playerList.get(i).getPoints() + " | "  + playerList.get(i).getAssists() + " | "
                        + playerList.get(i).getRebounds() + " | " + playerList.get(i).getSteals() + "\n";
            }*/
        }
        else if(menuChoice == 2){
            /**
             * Insertion sort based on points scored.
             */
            for(int i = 0; i < playerList.size(); i++){
                for(int j = 0; j < (playerList.size()-1); j++){
                    if(playerList.get(j).getPoints() > playerList.get(j+1).getPoints()){
                        temp = playerList.get(j);
                        playerList.set(j, playerList.get(j+1));
                        playerList.set(j+1, temp);
                    }
                }
            }

            output += "Sorted on player's points scored:\n\n";
            output += "Player name | Games played | Points scored | Assists | Rebounds | Steals\n";

            /*for(int i = 0; i < playerList.size(); i++){
                output += (i+1) + ". " + playerList.get(i).getPName() + " | " + playerList.get(i).getGamesPlayed() + " | "
                        + playerList.get(i).getPoints() + " | "  + playerList.get(i).getAssists() + " | "
                        + playerList.get(i).getRebounds() + " | " + playerList.get(i).getSteals() + "\n";
            }*/
        }
        else if(menuChoice == 3){
            /**
             * Insertion sort based on number of games played.
             */
            for(int i = 0; i < playerList.size(); i++){
                for(int j = 0; j < (playerList.size()-1); j++){
                    if(playerList.get(j).getGamesPlayed() > playerList.get(j+1).getGamesPlayed()){
                        temp = playerList.get(j);
                        playerList.set(j, playerList.get(j+1));
                        playerList.set(j+1, temp);
                    }
                }
            }

            output += "Sorted on player's number of games played:\n\n";
            output += "Player name | Games played | Points scored | Assists | Rebounds | Steals\n";

            /*for(int i = 0; i < playerList.size(); i++){
                output += (i+1) + ". " + playerList.get(i).getPName() + " | " + playerList.get(i).getGamesPlayed() + " | "
                        + playerList.get(i).getPoints() + " | "  + playerList.get(i).getAssists() + " | "
                        + playerList.get(i).getRebounds() + " | " + playerList.get(i).getSteals() + "\n";
            }*/
        }

        for(int i = 0; i < playerList.size(); i++){
            output += (i+1) + ". " + playerList.get(i).getPName() + " | " + playerList.get(i).getGamesPlayed() + " | "
                    + playerList.get(i).getPoints() + " | "  + playerList.get(i).getAssists() + " | "
                    + playerList.get(i).getRebounds() + " | " + playerList.get(i).getSteals() + "\n";
        }

        JOptionPane.showMessageDialog(null, output);
    }

    private static void coachMenu(Team team, LinkedList<Player> players){
        int userChoice = 0;
        try
        {
            boolean bool = false;
            while (bool == false)
            {
                userChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Welcome " + team.getTCoach().getCoachName() +
                        "! \n Team Management Software \n" +
                        "Please select from the options below: \n" +
                        "1. View and edit profile \n" +
                        "2. View and edit team info \n" +
                        "3. View and edit player info \n" +
                        "4. run reports \n" +
                        "5. Exit. "));
                if (userChoice > 5 || userChoice < 1) {
                    bool = false;
                    JOptionPane.showMessageDialog(null, "Please choose from the choices given.");
                } else {
                    bool = true;
                }
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Error occurred. Please enter a valid number.");
        }

        if(userChoice == 1)
        {
            profile(team);
        }
        else if(userChoice == 2)
        {
            teamInfo(team);
        }
        else if(userChoice == 3)
        {
            coachPlayerProfile(team);
        }
        else if(userChoice == 4)
        {
            reports(team);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You have been successfully logged out. \n Have a great day.");
            saveUserData(team);
            System.exit(0);
        }
    }

    private static void profile(Team team){
        int menuChoice = -1;
        String input = "";
        boolean hasAt = false;
        boolean hasDot = false;

        try{
            do{
                menuChoice = Integer.parseInt(JOptionPane.showInputDialog("Welcome " + team.getTCoach().getCoachName() + ", \n\n"
                        + "Please select an option: \n"
                        + "1. View your profile. \n"
                        + "2. Edit your profile."));
                if(menuChoice != 1 && menuChoice != 2)
                    JOptionPane.showMessageDialog(null, "Please enter either a '1' or a '2'");
            }while(menuChoice != 1 && menuChoice != 2);

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter either a '1' or a '2'");
        }

        if(menuChoice == 1){
            JOptionPane.showMessageDialog(null, team.getTCoach().toString());
        }
        else if(menuChoice == 2){
            menuChoice = -1;

            try{
                do{
                    menuChoice = Integer.parseInt(JOptionPane.showInputDialog("Please select the information you would like to update: \n"
                            + "1. Name \n"
                            + "2. Phone\n"
                            + "3. Email"));
                    if(menuChoice != 1 && menuChoice != 2 && menuChoice != 3)
                        JOptionPane.showMessageDialog(null, "Please enter a number 1 through 3.");
                }while(menuChoice != 1 && menuChoice != 2 && menuChoice != 3);

            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a number 1 through 3.");
            }

            if(menuChoice == 1){
                do{
                    input = JOptionPane.showInputDialog("Please enter the new name you would like to use:");

                    if(input.length() <= 0)
                        JOptionPane.showMessageDialog(null, "Please enter a name at least one character long.");
                }while(input.length() <= 0);

                team.getTCoach().setCoachName(input);

                //Needs to set email too, so log on changes. Or do we?
                /*
                String email = Character.toLowerCase(input.charAt(0)) + "";
                if(input.length() > 1 && input.indexOf(' ') != 0)
                    email += input.trim().substring(input.indexOf(' ') + 1);
                email += "@players.com";

                player.setCoachEmail(email);
                */
            }
            else if(menuChoice == 2){
                do{
                    input = JOptionPane.showInputDialog("Please enter the new phone you would like to use (just numbers, or with just two dashes: - ):");

                    if(input.length() != 10 && input.length() != 12)
                        JOptionPane.showMessageDialog(null, "Please enter a phone of just numeric digits, or in the \nformat of: XXX-XXX-XXXX." +
                                "\n\nThank you!");
                }while(input.length() != 10 && input.length() != 12);

                team.getTCoach().setCoachPhone(input);
            }
            else if(menuChoice == 3){
                do{
                    input = JOptionPane.showInputDialog("Please enter the new email you would like to use:");

                    for(int i = 0; i < input.length(); i++){
                        if(i > 1 && input.charAt(i) == '@')
                            hasAt = true;

                        if(i > 3 && input.charAt(i) == '.')
                            hasDot = true;

                        if(i == (input.length() - 1) && input.charAt(i) == '.')
                            hasDot = false;
                    }

                    if(!hasAt || !hasDot)
                        JOptionPane.showMessageDialog(null, "Please enter a valid email: '___@___.___'");
                }while(!hasAt || !hasDot);

                team.getTCoach().setCoachEmail(input);
            }

            JOptionPane.showMessageDialog(null, "Here is your new information: \n\n" + team.getTCoach().toString());
        }
    }

    private static void coachPlayerProfile(Team team){
        LinkedList<Player> playerList = team.getPlayers();
        int choice = -1;

        String output = "Please select the player you want to edit:\n\n";
        for(int i = 0; i < playerList.size(); i++){
            output += (i+1) + ". " + playerList.get(i).getPName() + " | " + playerList.get(i).getPlayerEmail() + "\n";
        }

        while(!(choice >= 1 && choice <= playerList.size())) {
            try {
                choice = Integer.parseInt(JOptionPane.showInputDialog(output));

                if (!(choice >= 1 && choice <= playerList.size())) {
                    choice = -1;
                    JOptionPane.showMessageDialog(null, "Please enter the number of a player on the list.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }

        playerProfile(playerList.get(choice-1));

    }

    private static void teamInfo(Team team){
        int choice = 0;
        do{
            try{
                choice = Integer.parseInt(JOptionPane.showInputDialog("Would you like to: \n\n1. View Team Info?\n2. Edit Team Info?"));
            }
            catch(NumberFormatException e){
                choice = 0;
            }
        }while(choice != 1 && choice != 2);

        if(choice == 1){
            String output = "Team Info:\n\n"
                    + "Name: " + team.getTName() + "\n"
                    + "Coach: " + team.getTCoach().getCoachName() + "\n"
                    + "Email: " + team.getTCoach().getCoachEmail() + "\n"
                    + "Number of Players: " + team.getPlayerCount() + "\n"
                    + "Color: " + team.getTColor() + "\n\n"
                    + "To view more information on the players, please select the report option from the main menu.";

            JOptionPane.showMessageDialog(null, output);
        }
        else if(choice == 2){
            choice = 0;
            do{
                try{
                    choice = Integer.parseInt(JOptionPane.showInputDialog("Update which field? \n\n1. Team name\n"
                            + "2. Team color\n"
                            + "3. Player count\n\n"));
                }
                catch(NumberFormatException e){
                    choice = 0;
                }

                if(!(choice >= 1 && choice <= 3))
                    JOptionPane.showMessageDialog(null, "Please select an option between 1 and 3.");

            }while(!(choice >= 1 && choice <= 3));

            if(choice == 1){
                String input = "";

                while(input.equals("")){
                    input = JOptionPane.showInputDialog("Please enter the new name:");

                    if(input.equals(""))
                        JOptionPane.showMessageDialog(null, "Please don't leave team name blank!");
                }

                team.setTName(input);
            }
            else if(choice == 2){
                String input = "";

                while(input.equals("")){
                    input = JOptionPane.showInputDialog("Please enter the new color:");

                    if(input.equals(""))
                        JOptionPane.showMessageDialog(null, "Please don't leave team color blank!");
                }

                team.setTColor(input);
            }
            else if(choice == 3){
                int input = 0;

                while(!(input >= 6 && input <= 15)){
                    try{
                        input = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new name:"));
                    }
                    catch(NumberFormatException e){
                        input = 0;
                    }
                    if(!(input >= 6 && input <= 15))
                        JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 15.");
                }

                team.setPlayerCount(input);
            }
        }
    }

    private static void playerMenu(LinkedList<Player> players, int selected, Team team){
        int userChoice = 0;
        try
        {
            boolean bool = false;
            while (bool == false)
            {
                userChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Welcome " + players.get(selected).getPName() +
                        "! \n Team Management Software \n" +
                        "Please select from the options below: \n" +
                        "1. View and edit profile \n" +
                        "2. Exit. "));
                if (userChoice > 2 || userChoice < 1) {
                    bool = false;
                    JOptionPane.showMessageDialog(null, "Please choose from the choices given.");
                } else {
                    bool = true;
                }
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Error occurred. Please enter a valid number.");
        }

        if(userChoice == 1) {
            playerProfile(players.get(selected));
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You have been successfully logged out. \n Have a great day.");
            saveUserData(team);
            System.exit(0);
        }
    }

    private static void playerProfile(Player player){
        int menuChoice = -1;
        String input;
        int num;

        try{
            do{
                menuChoice = Integer.parseInt(JOptionPane.showInputDialog("Welcome to " + player.getPName() + "'s profile, \n\n"
                        + "Please select an option: \n"
                        + "1. View profile. \n"
                        + "2. Edit profile."));
                if(menuChoice != 1 && menuChoice != 2)
                    JOptionPane.showMessageDialog(null, "Please enter either a '1' or a '2'");
            }while(menuChoice != 1 && menuChoice != 2);

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter either a '1' or a '2'");
        }

        if(menuChoice == 1){
            JOptionPane.showMessageDialog(null, player.toString());
        }
        else if(menuChoice == 2){
            menuChoice = -1;

            try{
                do{
                    menuChoice = Integer.parseInt(JOptionPane.showInputDialog("Please select the information you would like to update: \n"
                            + "1. Name \n"
                            + "2. Number of games played\n"
                            + "3. Points earned \n"
                            + "4. Assists\n"
                            + "5. Rebounds\n"
                            + "6. Steals"));
                    if(!(menuChoice >= 1 && menuChoice <= 6))
                        JOptionPane.showMessageDialog(null, "Please enter a number 1 through 6.");
                }while(!(menuChoice >= 1 && menuChoice <= 6));

            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a number 1 through 6.");
            }

            if(menuChoice == 1){
                do{
                    input = JOptionPane.showInputDialog("Please enter the new name you would like to use:\n\n");

                    if(input.length() <= 0)
                        JOptionPane.showMessageDialog(null, "Please enter a name at least one character long.");
                }while(input.length() <= 0);

                player.setPName(input);

                //Needs to set email too, so log on changes. Or do we?
                /*
                String email = Character.toLowerCase(input.charAt(0)) + "";
                if(input.length() > 1 && input.indexOf(' ') != 0)
                    email += input.trim().substring(input.indexOf(' ') + 1);
                email += "@players.com";

                player.setPlayerEmail(email);
                */
            }
            else if(menuChoice == 2){
                do{
                    num = Integer.parseInt(JOptionPane.showInputDialog("Please enter new number of games played:"));

                    if(num < 0 || num < player.getGamesPlayed())
                        JOptionPane.showMessageDialog(null, "Please enter a number greater than the last number of games played (" + player.getGamesPlayed() +").");
                }while(num < 0 || num < player.getGamesPlayed());

                player.setGamesPlayed(num);
            }
            else if(menuChoice == 3){
                do{
                    num = Integer.parseInt(JOptionPane.showInputDialog("Please enter new number of points scored:"));

                    if(num < 0 || num < player.getPoints())
                        JOptionPane.showMessageDialog(null, "Please enter a number greater than the last number of points earned (" + player.getPoints() + ").");
                }while(num < 0 || num < player.getPoints());

                player.setPoints(num);
            }
            else if(menuChoice == 4){
                do{
                    num = Integer.parseInt(JOptionPane.showInputDialog("Please enter new number of assists:"));

                    if(num < 0 || num < player.getAssists())
                        JOptionPane.showMessageDialog(null, "Please enter a number greater than the last number of assists (" + player.getAssists() + ").");
                }while(num < 0 || num < player.getAssists());

                player.setAssists(num);
            }
            else if(menuChoice == 5){
                do{
                    num = Integer.parseInt(JOptionPane.showInputDialog("Please enter new number of rebounds:"));

                    if(num < 0 || num < player.getRebounds())
                        JOptionPane.showMessageDialog(null, "Please enter a number greater than the last number of rebound (" + player.getRebounds() + ").");
                }while(num < 0 || num < player.getRebounds());

                player.setRebounds(num);
            }
            else if(menuChoice == 6){
                do{
                    num = Integer.parseInt(JOptionPane.showInputDialog("Please enter new number of steals:"));

                    if(num < 0 || num < player.getSteals())
                        JOptionPane.showMessageDialog(null, "Please enter a number greater than the last number of steals (" + player.getSteals() + ").");
                }while(num < 0 || num < player.getSteals());

                player.setSteals(num);
            }

            JOptionPane.showMessageDialog(null, "Here is the new information: \n\n" + player.toString());
        }
    }

}
