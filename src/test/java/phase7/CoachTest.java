package phase7;

import static org.junit.Assert.*;

/**
 * Created by nidas on 5/8/17.
 */
public class CoachTest {

    @org.junit.Test
    public void getCoachName() throws Exception {
        Coach coach = new Coach();
        coach.setCoachName("John Saunders");
        assertEquals(coach.getCoachName(), "John Saunders");
    }

    @org.junit.Test
    public void getCoachEmail() throws Exception {
        Coach coach = new Coach();
        coach.setCoachEmail("jsaunders@coaches.com");
        assertEquals(coach.getCoachEmail(), "jsaunders@coaches.com");
    }

    @org.junit.Test
    public void getCoachPhone() throws Exception {
        Coach coach = new Coach();
        coach.setCoachPhone("703-777-7777");
        assertEquals(coach.getCoachPhone(), "703-777-7777");
    }

    @org.junit.Test
    public void setCoachName() throws Exception {
        this.getCoachName();
    }

    @org.junit.Test
    public void setCoachEmail() throws Exception {
        this.getCoachEmail();
    }

    @org.junit.Test
    public void setCoachPhone() throws Exception {
        this.getCoachPhone();
    }

    @org.junit.Test
    public void setPassword() throws Exception {
        Coach coach = new Coach();
        coach.setPassword("password");
        assertEquals(coach.getPassword(), "password");
    }

    @org.junit.Test
    public void getPassword() throws Exception {
        this.setPassword();
    }

}