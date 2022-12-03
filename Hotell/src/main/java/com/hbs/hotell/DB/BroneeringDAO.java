package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BroneeringDAO extends DataAccessObject<Broneering> {

    // Teeb INSERT query mis lisab andmed faili
    private static final String INSERT = "INSERT INTO broneeringud (külastaja, tuba, " +
            "aeg) VALUES (?, ?, ?)";

    // Teeb SELECT query mis leiab kliendi id järgi kliendi ja valib selle info

    private static final String GET_ONE = "SELECT bron_id, külastaja, tuba, " +
            "aeg FROM broneeringud WHERE bron_id = ?";

    // Teeb UPDATE query mis leiab kliendi id järgi kliendi ja uuendab infot
    private static final String UPDATE = "UPDATE broneeringud SET külastaja = ?, tuba = ?, " +
            "aeg = ? WHERE bron_id = ?";

    // Teeb DELETE query-s mis leiab kasutaja id järgi kliendi ja kustutab selle
    private static final String DELETE = "DELETE FROM broneeringud WHERE bron_id = ?";

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
                broneering.setId(rs.getLong("bron_id"));
                broneering.setKulastaja(rs.getString("külastaja"));
                broneering.setTuba(rs.getString("tuba"));
                broneering.setAeg(rs.getString("aeg"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return broneering;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Broneering update(Broneering dto) {
        Broneering broneering = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            statement.setString(1, dto.getKulastaja());
            statement.setString(2, dto.getTuba());
            statement.setString(3, dto.getAeg());
            statement.setLong(4, dto.getId());

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
            statement.setString(1, dto.getKulastaja());
            statement.setString(2, dto.getTuba());
            statement.setString(3, dto.getAeg());

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
