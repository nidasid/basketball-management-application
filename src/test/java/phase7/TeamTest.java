package phase7;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nidas on 5/8/17.
 */
public class TeamTest {
    @Test
    public void getTName() throws Exception {
        Team team = new Team();
        team.setTName("Basketball Team");
        assertEquals(team.getTName(), "Basketball Team");
    }

    @Test
    public void getPlayerCount() throws Exception {
        Team team = new Team();
        assertEquals(team.getPlayerCount(), 1);
    }

    @Test
    public void getTColor() throws Exception {
        Team team = new Team();
        team.setTColor("blue");
        assertEquals(team.getTColor(), "blue");
    }

    @Test
    public void getTCoach() throws Exception {
        Coach coach = new Coach();
        Team team = new Team();
        coach.setCoachName("Bob");
        team.setTCoach(coach);
        assertEquals(team.getTCoach(), "Bob");
    }

    @Test
    public void getPlayers() throws Exception {
        Player player = new Player();
        player.setPassword("password");
        player.setSteals(1);
        player.setPlayerEmail("email@email.com");
        player.setRebounds(4);
        player.setPoints(4);
        player.setPName("Test User");
        player.setGamesPlayed(4);
        player.setAssists(4);

        Team team = new Team();

        assertEquals(team.getPlayers(), player);
    }

    @Test
    public void setTName() throws Exception {
        this.getTName();
    }

    @Test
    public void setPlayerCount() throws Exception {
        this.getPlayerCount();
    }

    @Test
    public void setTColor() throws Exception {
        this.getTColor();
    }

    @Test
    public void setTCoach() throws Exception {
        this.getTCoach();
    }

}