package phase7;

/**
 * Created by keaton on 5/3/2017.
 * Keaton & Nida
 * IT 306-001
 * Spring 2017
 */
public class Coach {
    private String coachName;
    private String coachEmail;
    private String coachPhone;
    private String password;

    //Constructors
    public Coach(){}
    public Coach(String name){
        coachName = name;
    }
    public Coach (String coachEmail, String password)
    {
        boolean verify = false;
        verify = this.setCoachEmail(coachEmail);
        if (verify == false)
        {
            this.setCoachEmail(null);
        }
        else
        {
            this.setCoachEmail(coachEmail);
        }

        verify = this.setPassword(password);
        if (verify == false){
            this.setPassword(null);
        } else {
            this.setPassword(password);
        }
    }

    //Accessors
    public String getCoachName() {return coachName;}
    public String getCoachEmail() {return coachEmail;}
    public String getCoachPhone() {return coachPhone;}

    //Mutators
    public void setCoachName(String name) {
        if(name.length() > 0)
            coachName = name;
    }

    public boolean setCoachEmail(String email) {
        if(email.length() > 0) {
            coachEmail = email;
            return true;
        }
        else{
            return false;
        }
    }

    public void setCoachPhone(String phone) {
        if(phone.length() > 0)
            coachPhone = phone;
    }

    //Special Methods
    public boolean setPassword(String pass) {
        if(pass.length() > 6) {
            password = pass;
            return true;
        } else
        {
            return false;
        }
    }

    public String getPassword(){
        return this.password;
    }

    public String toString() {
        String output = "";

        output += "Coach Name: " + coachName + "\n";
        output += "Phone: " + coachPhone + "\n";
        output += "Email: " + coachEmail;

        return output;
    }
}
