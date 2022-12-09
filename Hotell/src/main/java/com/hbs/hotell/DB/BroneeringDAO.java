package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataAccessObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class BroneeringDAO extends DataAccessObject<Broneering> {

    // Teeb INSERT query mis lisab andmed faili
    private static final String INSERT = "INSERT INTO broneeringud (kulastaja_eesnimi, kulastaja_perekonnanimi, tuba, " +
            "alg_aeg, lopp_aeg) VALUES (?, ?, ?, ?, ?)";

    // Teeb SELECT query mis leiab kliendi id järgi kliendi ja valib selle info

    private static final String GET_ONE = "SELECT id, kulastaja_eesnimi, kulastaja_perekonnanimi, tuba, " +
            "alg_aeg, lopp_aeg FROM broneeringud WHERE id = ?";

    // Teeb UPDATE query mis leiab kliendi id järgi kliendi ja uuendab infot
    private static final String UPDATE = "UPDATE broneeringud SET kulastaja_eesnimi = ?, kulastaja_perekonnanimi = ?, tuba = ?, " +
            "alg_aeg = ?, lopp_aeg = ? WHERE id = ?";

    // Teeb DELETE query-s mis leiab kasutaja id järgi kliendi ja kustutab selle
    private static final String DELETE = "DELETE FROM broneeringud WHERE id = ?";

    private static final String SELECT = "SELECT * FROM broneeringud";


    public BroneeringDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Broneering findById(long id) {
        Broneering broneering = new Broneering();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                broneering.setId(rs.getLong("id"));
                broneering.setKulastaja_eesnimi(rs.getString("kulastaja_eesnimi"));
                broneering.setKulastaja_perekonnanimi(rs.getString("kulastaja_perekonnanimi"));
                broneering.setTuba(rs.getInt("tuba"));
                broneering.setAlg_aeg(rs.getDate("alg_aeg").toLocalDate());
                broneering.setLopp_aeg(rs.getDate("lopp_aeg").toLocalDate());

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return broneering;
    }



    @Override
    public ObservableList<Broneering> findAll() {
        ObservableList<Broneering> list = FXCollections.observableArrayList();

        try (PreparedStatement statement = this.connection.prepareStatement(SELECT)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Broneering broneering = new Broneering();

                broneering.setId(rs.getLong("id"));
                broneering.setKulastaja_eesnimi(rs.getString("kulastaja_eesnimi"));
                broneering.setKulastaja_perekonnanimi(rs.getString("kulastaja_perekonnanimi"));
                broneering.setTuba(rs.getInt("tuba"));
                broneering.setAlg_aeg(rs.getDate("alg_aeg").toLocalDate());
                broneering.setLopp_aeg(rs.getDate("lopp_aeg").toLocalDate());

                list.add(broneering);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public Broneering update(Broneering dto) {
        Broneering broneering = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            statement.setString(1, dto.getKulastaja_eesnimi());
            statement.setString(2, dto.getKulastaja_perekonnanimi());
            statement.setInt(3, dto.getTuba());
            statement.setObject(4, dto.getAlg_aeg());
            statement.setObject(5, dto.getLopp_aeg());
            statement.setLong(6, dto.getId());

            statement.execute();
            broneering = this.findById(dto.getId());
            return broneering;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Broneering create(Broneering dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
            statement.setString(1, dto.getKulastaja_eesnimi());
            statement.setString(2, dto.getKulastaja_perekonnanimi());
            statement.setInt(3, dto.getTuba());
            statement.setObject(4, dto.getAlg_aeg());
            statement.setObject(5, dto.getLopp_aeg());

            statement.execute();

            int id = this.getLastVal(BRONEERINGU_SEQUENCE);
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
