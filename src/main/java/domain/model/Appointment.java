package domain.model;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private Timestamp date;
    private int id;
    private String name;


    public Appointment(int id, String name, Timestamp date) {
        setId(id);
        setName(name);
        setDate(date);
    }

    public Appointment() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new DomainException("No valid name");
        this.name = name;
    }

    /**
     * Formate datum to string (YYYY-MM-DD)
     */
    public String getLocalDateAsString() {
        return MyTimeStamp.formatDate(getDate());
    }

    /**
     * Format time as string (HH:MM)
     */
    public String getLocalTimeAsString() {
        return MyTimeStamp.formatTime(getDate());
    }

    /**
     * Format timestamp as string (YYYY-MM-DD HH:MM)
     */
    public String getDateAsString() {
        return MyTimeStamp.formatTimeStamp(getDate());
    }

    @Override
    public String toString() {
        return "id: " + getId() + "; name: " + getName() + "; date: " + this.getDateAsString();
    }
}
