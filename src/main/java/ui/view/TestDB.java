package ui.view;

import domain.db.DbException;
import domain.model.Appointment;
import domain.model.MyTimeStamp;
import util.Secret;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        // set properties for the db connection
        Properties properties = new Properties();
        // in what follows: replace "webontwerp" by the name of your database
        // replace "web3" by the name of your schema
        String url = "jdbc:postgresql://databanken.ucll.be:62021/webontwerp";
        String schema = "grjon";
        try {
            Class.forName("util.Secret"); // implementation of abstract class Credentials
            Secret.setPass(properties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class ui.view.Secret with credentials not found!");
        }
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode", "prefer");

        // open the db connection
        Connection connection = DriverManager.getConnection(url, properties);

        // first an example for execute: set the search_path for this connection
        String querySetSearchPath = "SET search_path = agenda;";
        Statement statement = connection.createStatement();
        statement.execute(querySetSearchPath);

        // set the schema for your query
        // get all the countries
        // notice that web3 is the schema name
        String query = String.format("SELECT * from %s.agenda;", schema);
        statement.executeQuery(query);
        ResultSet result = statement.getResultSet();

        System.out.println("Insert appointment");
        String name = "Pol";
        Timestamp date = MyTimeStamp.getTimestampFromDateAndTimeAsString("2021-02-16","04:56");
        String sql = String.format("INSERT INTO %s.agenda (name, date) VALUES (?, ?)", schema);

/*        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);

            statementSQL.setString(1, name);
            statementSQL.setTimestamp(2, date);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }*/


        System.out.println("All appointments");
        List<Appointment> agenda = new ArrayList<Appointment>();
        sql = String.format("SELECT * FROM %s.agenda", schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            result = statementSql.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                name = result.getString("name");
                Timestamp timestamp = result.getTimestamp("date");
                agenda.add(new Appointment(id, name, timestamp));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(agenda);

        // close the db connection
        connection.close();
    }
}