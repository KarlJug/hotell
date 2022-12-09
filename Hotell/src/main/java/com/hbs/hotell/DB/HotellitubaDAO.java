package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataAccessObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotellitubaDAO extends DataAccessObject<Hotellituba> {

    // Teeb INSERT query mis lisab andmed faili
    private static final String INSERT = "INSERT INTO toad (toa_num, toa_type, voodikohtade_arv, " +
            "hind, broneeritud, broneeria_eesnimi, broneeria_perekonnanimi) VALUES (?, ?, ?, ?, ?, ?, ?)";


    // Teeb SELECT query mis leiab kliendi id järgi kliendi ja valib selle info

    private static final String GET_ONE = "SELECT id, toa_num, voodikohtade_arv, " +
            "hind FROM toad WHERE toa_id = ?";

    // Otsib kõik vabad toad
    private static final String AVAILABLE = "SELECT toa_type, broneeritud FROM toad WHERE broneeritud = FALSE AND toa_type = ?";
    private static final String AVAILABLE_ROOM = "SELECT toa_num FROM toad WHERE broneeritud = FALSE AND toa_type = ?";

    // Teeb UPDATE query mis leiab kliendi id järgi kliendi ja uuendab infot
    private static final String UPDATE = "UPDATE toad SET toa_number = ?, toa_type = ?, voodikohtade_arv = ?, " +
            "hind = ?, broneeria_eesnimi = ?, broneeria_perekonnanimi = ? WHERE id = ?";

    private static final String BOOK = "UPDATE toad SET broneeritud = ?, broneeria_eesnimi = ?, broneeria_perekonnanimi = ? WHERE toa_num = ?";

    // Teeb DELETE query-s mis leiab kasutaja id järgi kliendi ja kustutab selle
    private static final String DELETE = "DELETE FROM toad WHERE id = ?";

    // query mis valib kõik
    private static final String SELECT = "SELECT * FROM toad";

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
                hotellituba.setToa_num(rs.getInt("toa_num"));
                hotellituba.setVoodikohtade_arv(rs.getInt("voodikohtade_arv"));
                hotellituba.setHind(rs.getInt("hind"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return hotellituba;
    }

    @Override
    public ObservableList<Hotellituba> findAll() {
        ObservableList<Hotellituba> list = FXCollections.observableArrayList();

        try (PreparedStatement statement = this.connection.prepareStatement(SELECT)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Hotellituba hotellituba = new Hotellituba();

                hotellituba.setId(rs.getLong("id"));
                hotellituba.setToa_num(rs.getInt("toa_num"));
                hotellituba.setToa_type(rs.getInt("toa_type"));
                hotellituba.setVoodikohtade_arv(rs.getInt("voodikohtade_arv"));
                hotellituba.setHind(rs.getInt("hind"));
                hotellituba.setBroneeritud(rs.getBoolean("broneeritud"));
                hotellituba.setBroneeria_eesnimi(rs.getString("broneeria_eesnimi"));
                hotellituba.setBroneeria_perekonnanimi(rs.getString("broneeria_perekonnanimi"));

                list.add(hotellituba);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }


    public ObservableList<Hotellituba> findAllAvailable(int type) {
        ObservableList<Hotellituba> list = FXCollections.observableArrayList();
        Hotellituba hotellituba = new Hotellituba();

        try (PreparedStatement statement = this.connection.prepareStatement(AVAILABLE)) {
            statement.setInt(1, type);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                hotellituba.setToa_type(rs.getInt("toa_type"));
                hotellituba.setBroneeritud(rs.getBoolean("broneeritud"));

                list.add(hotellituba);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public int checkIfRoomAvailable(int type) {
        ObservableList<Hotellituba> list = FXCollections.observableArrayList();
        Hotellituba hotellituba = new Hotellituba();
        int tuba;

        // Leiab vaba toa ja annab edasi
        try (PreparedStatement statement = this.connection.prepareStatement(AVAILABLE_ROOM)) {
            statement.setInt(1, type);
            ResultSet rs = statement.executeQuery();
            rs.next();
            hotellituba.setToa_num(rs.getInt("toa_num"));
            tuba = hotellituba.getToa_num();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tuba;
    }
    public void bookARoom(int tuba, Hotellituba dto) {
        // lisab kasutaja selle toa broneeringu tabelisse
        try (PreparedStatement statement = this.connection.prepareStatement(BOOK)) {
            statement.setBoolean(1, true);
            statement.setString(2, dto.getBroneeria_eesnimi());
            statement.setString(3, dto.getBroneeria_perekonnanimi());

            statement.setInt(4, tuba);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hotellituba update(Hotellituba dto) {
        Hotellituba hotellituba = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            statement.setInt(1, dto.getToa_num());
            statement.setInt(4, dto.getToa_type());
            statement.setInt(2, dto.getVoodikohtade_arv());
            statement.setInt(3, dto.getHind());
            statement.setString(4, dto.getBroneeria_eesnimi());
            statement.setString(4, dto.getBroneeria_perekonnanimi());
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
            statement.setInt(1, dto.getToa_num());
            statement.setInt(2, dto.getToa_type());
            statement.setInt(3, dto.getVoodikohtade_arv());
            statement.setInt(4, dto.getHind());
            statement.setString(5, dto.getBroneeria_eesnimi());
            statement.setString(6, dto.getBroneeria_perekonnanimi());


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
