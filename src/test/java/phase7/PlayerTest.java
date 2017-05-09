package phase7;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nidas on 5/8/17.
 */
public class PlayerTest {

    @Test
    public void getPName() throws Exception {
        Player player = new Player();
        player.setPName("Deron Williams");
        assertEquals(player.getPName(), "Deron Williams");
    }

    @Test
    public void getGamesPlayed() throws Exception {
        Player player = new Player();
        player.setGamesPlayed(4);
        assertEquals(player.getGamesPlayed(), 4);
    }

    @Test
    public void getPoints() throws Exception {
        Player player = new Player();
        player.setPoints(13);
        assertEquals(player.getPoints(), 13);
    }

    @Test
    public void getAssists() throws Exception {
        Player player = new Player();
        player.setAssists(7);
        assertEquals(player.getAssists(), 7);
    }

    @Test
    public void getRebounds() throws Exception {
        Player player = new Player();
        player.setRebounds(3);
        assertEquals(player.getRebounds(), 3);
    }

    @Test
    public void getSteals() throws Exception {
        Player player = new Player();
        player.setSteals(13);
        assertEquals(player.getSteals(), 13);
    }

    @Test
    public void setPlayerEmail() throws Exception {
        Player player = new Player();
        player.setPlayerEmail("dwilliams@players.com");
        assertEquals(player.getPlayerEmail(), "dwilliams@players.com");
    }

    @Test
    public void getPlayerEmail() throws Exception {
        this.setPlayerEmail();
    }

    @Test
    public void setPassword() throws Exception {
        Player player = new Player();
        player.setPassword("password");
        assertEquals(player.getPassword(), "password");
    }

    @Test
    public void getPassword() throws Exception {
        this.setPassword();
    }

    @Test
    public void setPName() throws Exception {
        this.setPName();
    }

    @Test
    public void setGamesPlayed() throws Exception {
        this.getGamesPlayed();
    }

    @Test
    public void setAssists() throws Exception {
        this.getAssists();
    }

    @Test
    public void setPoints() throws Exception {
        this.getPoints();
    }

    @Test
    public void setRebounds() throws Exception {
        this.getRebounds();
    }

    @Test
    public void setSteals() throws Exception {
        this.getSteals();
    }

}