package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HotellitubaDAO extends DataAccessObject<Hotellituba> {

    // Teeb INSERT query mis lisab andmed faili
    private static final String INSERT = "INSERT INTO hotellitoad (toa_number, voodikohtade_arv, " +
            "hind) VALUES (?, ?, ?)";

    // Teeb SELECT query mis leiab kliendi id järgi kliendi ja valib selle info

    private static final String GET_ONE = "SELECT toa_id, toa_number, voodikohtade_arv, " +
            "hind FROM hotellitoad WHERE toa_id = ?";

    // Teeb UPDATE query mis leiab kliendi id järgi kliendi ja uuendab infot
    private static final String UPDATE = "UPDATE hotellitoad SET toa_number = ?, voodikohtade_arv = ?, " +
            "hind = ? WHERE toa_id = ?";

    // Teeb DELETE query-s mis leiab kasutaja id järgi kliendi ja kustutab selle
    private static final String DELETE = "DELETE FROM hotellitoad WHERE toa_id = ?";

    public HotellitubaDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Hotellituba findById(long id) {
        Hotellituba hotellituba = new Hotellituba();

        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                hotellituba.setId(rs.getLong("toa_id"));
                hotellituba.setToa_number(rs.getString("toa_number"));
                hotellituba.setVoodikohtade_arv(rs.getString("voodikohtade_arv"));
                hotellituba.setHind(rs.getString("hind"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return hotellituba;
    }

    @Override
    public List<Hotellituba> findAll() {
        return null;
    }

    @Override
    public Hotellituba update(Hotellituba dto) {
        Hotellituba hotellituba = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            statement.setString(1, dto.getToa_number());
            statement.setString(2, dto.getVoodikohtade_arv());
            statement.setString(3, dto.getHind());
            statement.setLong(4, dto.getId());

            statement.execute();
            hotellituba = this.findById(dto.getId());
            return hotellituba;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hotellituba create(Hotellituba dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
            statement.setString(1, dto.getToa_number());
            statement.setString(2, dto.getVoodikohtade_arv());
            statement.setString(3, dto.getHind());

            statement.execute();

            int id = this.getLastVal(HOTELLITUBA_SEQUENCE);
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
