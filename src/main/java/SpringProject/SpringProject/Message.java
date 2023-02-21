package SpringProject.SpringProject;


import java.util.Date;

public class Message {
    private String message;
    private Date Date;


    public Message(String message , Date date) {

        this.message = message;
        Date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }
}
