package domain.db;

import domain.model.Appointment;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaDBSQL implements AgendaDB {
    private Connection connection;
    private final String schema;

    public AgendaDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new DbException("Nothing to add.");
        }

        String sql = String.format("INSERT INTO %s.agenda (name, date) VALUES (?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = getConnection().prepareStatement(sql);

            statementSQL.setString(1, appointment.getName());
            statementSQL.setTimestamp(2, appointment.getDate());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void deleteAppointment(int id) {
        String sql = String.format("DELETE FROM %s.agenda WHERE id=?", this.schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throw new DbException(throwables);
        }

    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> agenda = new ArrayList<Appointment>();
        String sql = String.format("SELECT * FROM %s.agenda ORDER BY date", this.schema);
        try {
            PreparedStatement statementSql = getConnection().prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                Timestamp timestamp = result.getTimestamp("date");
                agenda.add(new Appointment(id, name, timestamp));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return agenda;
    }

    @Override
    public List<Appointment> getAllAppointmentsFromUntil(Timestamp from, Timestamp until) {
        List<Appointment> agenda = new ArrayList<Appointment>();
        String sql = String.format("SELECT * FROM %s.agenda WHERE date > ? AND date < ? ORDER BY date", this.schema);
        try {
            PreparedStatement statementSql = getConnection().prepareStatement(sql);
            statementSql.setTimestamp(1, from);
            statementSql.setTimestamp(2, until);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                Timestamp timestamp = result.getTimestamp("date");
                agenda.add(new Appointment(id, name, timestamp));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return agenda;
    }

    @Override
    public Appointment getAppointmentWithID(int id) {
        String sql = String.format("SELECT * FROM %s.agenda WHERE id = ? ", this.schema);
        try {
            PreparedStatement statementSql = getConnection().prepareStatement(sql);
            statementSql.setInt(1, id);
            ResultSet result = statementSql.executeQuery();
            if (!result.next())
                return null;
            // result.next() is done in line 96
            String name = result.getString("name");
            Timestamp timestamp = result.getTimestamp("date");
            return new Appointment(id, name, timestamp);
        } catch (SQLException c) {
            throw new DbException(c.getMessage());
        }
    }

    /**
     * Check the connection and reconnect when necessery
     *
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        checkConnection();
        return this.connection;
    }

    /**
     * Check if the connection is still open
     * When connection has been closed: reconnect
     */
    private void checkConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection has been closed");
                this.reConnect();
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }

    /**
     * Reconnects application to db
     */
    private void reConnect() {
        DbConnectionService.disconnect();   // close connection with db properly
        DbConnectionService.reconnect();      // reconnect application to db server
        this.connection = DbConnectionService.getDbConnection();    // assign connection to DBSQL
    }


}
