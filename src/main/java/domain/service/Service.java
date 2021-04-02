package domain.service;


import domain.db.AgendaDB;
import domain.db.AgendaDBSQL;
import domain.model.Appointment;
import domain.model.MyTimeStamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final AgendaDB agendaDB = new AgendaDBSQL();

    public Service() {
    }

    public Appointment getAppointmentWithID(int id) {
        return agendaDB.getAppointmentWithID(id);
    }

    public List<Appointment> getAllAppointments() {
        return agendaDB.getAllAppointments();
    }

    public void addAppointment(Appointment appointment) {
        agendaDB.addAppointment(appointment);
    }

    public void deleteAppointment(int id) {
        agendaDB.deleteAppointment(id);
    }

    private AgendaDB getAgendaDB() {
        return agendaDB;
    }

    public List<Appointment> getAllAppointmnentsFromUntilJava(Timestamp from, Timestamp until) {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : getAllAppointments()
        ) {
            if (appointment.getDate().after(from) && appointment.getDate().before(until))
                appointments.add(appointment);
        }
        return appointments;
    }

    /**
     * Find all appointments between from and until
     * @param from: starting date; if from is not set, starting date is set to 1970-01-01
     * @param until: ending date: if until is not set, ending date is set to 3000-12-31
     * @param method: method used to generate list.
     *              method == java: list with all appointments is generated by SQL, filtering is done in java
     *              method == sql: list with filtered data is generated by SQL
     * @return appointments filtered by date
     */
    public List<Appointment> getAllAppointmnentsFromUntil(String from, String until, String method) {
        Timestamp fromTimestamp = new Timestamp(0), untilTimestamp = MyTimeStamp.getTimestampFromDateAndTimeAsString("3000-12-31","00:00");
        if (!from.isBlank())
            fromTimestamp = MyTimeStamp.getTimestampFromDateAndTimeAsString(from, "00:00");
        if (!until.isBlank())
            untilTimestamp = MyTimeStamp.getTimestampFromDateAndTimeAsString(until, "00:00");
        if (method.equals("java"))
            return getAllAppointmnentsFromUntilJava(fromTimestamp, untilTimestamp);
        else
            return agendaDB.getAllAppointmentsFromUntil(fromTimestamp,untilTimestamp);
    }


}
