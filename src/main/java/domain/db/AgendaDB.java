package domain.db;

import domain.model.Appointment;

import java.sql.Timestamp;
import java.util.List;

public interface AgendaDB {
    List<Appointment> getAllAppointments();
    List<Appointment> getAllAppointmentsFromUntil(Timestamp from, Timestamp until);
    Appointment getAppointmentWithID(int id);
    void addAppointment(Appointment appointment);
    void deleteAppointment(int id);
}
