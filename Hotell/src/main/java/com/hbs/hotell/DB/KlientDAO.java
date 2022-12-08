package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataAccessObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KlientDAO extends DataAccessObject<Klient> {

    // Teeb INSERT query mis lisab andmed faili
    private static final String INSERT = "INSERT INTO kliendid (eesnimi, pere_nimi, " +
            "isikukood, email) VALUES (?, ?, ?, ?)";

    // Teeb SELECT query mis leiab kliendi id järgi kliendi ja valib selle info
    private static final String GET_ONE = "SELECT kliendi_id, eesnimi, pere_nimi, " +
            "isikukood, email FROM kliendid WHERE kliendi_id = ?";

    private static final String GET_ONE_BY_CODE = "SELECT kliendi_id, eesnimi, pere_nimi, " +
            "isikukood, email FROM kliendid WHERE isikukood = ?";

    // Teeb UPDATE query mis leiab kliendi id järgi kliendi ja uuendab infot
    private static final String UPDATE = "UPDATE kliendid SET eesnimi = ?, pere_nimi = ?, " +
            "isikukood = ?, email = ? WHERE kliendi_id = ?";

    // Teeb DELETE query-s mis leiab kasutaja id järgi kliendi ja kustutab selle
    private static final String DELETE = "DELETE FROM kliendid WHERE kliendi_id = ?";

    // query mis valib kõik
    private static final String SELECT = "SELECT * FROM kliendid";


    public KlientDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Klient findById(long id) {
        Klient klient = new Klient();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                klient.setId(rs.getLong("kliendi_id"));
                klient.setEesnimi(rs.getString("eesnimi"));
                klient.setPere_nimi(rs.getString("pere_nimi"));
                klient.setIsikukood(rs.getString("isikukood"));
                klient.setEmail(rs.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return klient;
    }

    public boolean findByPersonalCode(String personalCode) {
        Klient klient = new Klient();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE_BY_CODE)) {
            statement.setString(1, personalCode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                klient.setId(rs.getLong("kliendi_id"));
                klient.setEesnimi(rs.getString("eesnimi"));
                klient.setPere_nimi(rs.getString("pere_nimi"));
                klient.setIsikukood(rs.getString("isikukood"));
                klient.setEmail(rs.getString("email"));

                if (personalCode == klient.getIsikukood()) {
                    return true;
                }
                else {
                    return false;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ObservableList<Klient> findAll() {
        ObservableList<Klient> list = FXCollections.observableArrayList();

        try (PreparedStatement statement = this.connection.prepareStatement(SELECT)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Klient klient = new Klient();

                klient.setId(rs.getLong("kliendi_id"));
                klient.setEesnimi(rs.getString("eesnimi"));
                klient.setPere_nimi(rs.getString("pere_nimi"));
                klient.setIsikukood(rs.getString("isikukood"));
                klient.setEmail(rs.getString("email"));

                list.add(klient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Klient update(Klient dto) {
        Klient klient = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            statement.setString(1, dto.getEesnimi());
            statement.setString(2, dto.getPere_nimi());
            statement.setString(3, dto.getIsikukood());
            statement.setString(4, dto.getEmail());
            statement.setLong(5, dto.getId());

            statement.execute();
            klient = this.findById(dto.getId());
            return klient;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Klient create(Klient dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
            statement.setString(1, dto.getEesnimi());
            statement.setString(2, dto.getPere_nimi());
            statement.setString(3, dto.getIsikukood());
            statement.setString(4, dto.getEmail());

            statement.execute();

            int id = this.getLastVal(CUSTOMER_SEQUENCE);
            return this.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


            /*Customer customer = new Customer();
            customer.setFirstName("Jhon");
            customer.setLastName("Adams");
            customer.setEmail("jadams@wh.gov");
            customer.setPhone("(555) 389-2412");
            customer.setAddress("444 Main St");
            customer.setCity("Arlington");
            customer.setState("VA");
            customer.setZipCode("01234");

            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);
            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);
            dbCustomer.setEmail("jhon.adams@wh.gov");
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);
            customerDAO.delete(dbCustomer.getId());*/

            /*Customer customer = customerDAO.findById(10000);

            System.out.println(customer.getFirstName() + " " + customer.getLastName() + "" + customer.getEmail());
            customer.setEmail("gman@wh.gov");

            customer = customerDAO.update(customer);
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + "" + customer.getEmail());*/

            /*Customer customer = customerDAO.findById(10004);
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getId());*/


            /*Customer customer = new Customer();

            customer.setFirstName("Puur");
            customer.setLastName("Washer");
            customer.setEmail("ssss@wh.gov");
            customer.setPhone("55555155555");
            customer.setAddress("133 Main St");
            customer.setCity("Everest");
            customer.setState("Vv");
            customer.setZipCode("231111");
            customer = customerDAO.create(customer);
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getId());*/

