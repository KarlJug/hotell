package com.hbs.hotell.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataAccessObject <T> {

    protected final Connection connection;
    protected final static String LAST_VAL = "SELECT last_value FROM ";
    protected final static String CUSTOMER_SEQUENCE = "hotell_kliendi_seq";
    protected final static String BRONEERINGU_SEQUENCE = "hotell_broneeringud_seq";
    protected final static String HOTELLITUBA_SEQUENCE = "hotell_toad_seq";



    protected DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }


    public abstract T findById(long id);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(long id);

    protected int getLastVal(String sequence) {
        int key = 0;
        String sql = LAST_VAL + sequence;

        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                key = rs.getInt(1);
            }
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
