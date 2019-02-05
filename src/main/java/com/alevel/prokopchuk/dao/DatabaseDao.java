package com.alevel.prokopchuk.dao;


import com.alevel.prokopchuk.ConnectorDB;
import com.alevel.prokopchuk.models.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseDao extends AbstractDao<Table> {

    private static final String SQL_CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS %s (ID INT)";
    private static final String SQL_DROP_TABLE_QUERY = "DROP TABLE IF EXISTS %s";
    private static final String SQL_LOAD_TABLES_NAMES_QUERY = "SELECT TABLE_NAME \n" +
            "FROM INFORMATION_SCHEMA.TABLES\n" +
            "WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='Tables';";


    public boolean create(Table table) {
        String query = String.format(SQL_CREATE_TABLE_QUERY, table.getName());
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println(table.getName() + " was added to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//    public boolean create(Table table) {
//        StringBuilder queryBuilder = new StringBuilder(SQL_CREATE_TABLE_START_QUERY);
//        queryBuilder.append(table.getName()).append(" (").append(SQL_CREATE_TABLE_ID_DECLARATION);
//
//        for (Column column : table.getColumns()) {
//            queryBuilder.append(column.getName()).append(' ').append(column.getTypeName());
//            queryBuilder.append(',').append(System.lineSeparator());
//        }
//        queryBuilder.setLength(queryBuilder.length() - 3);
//        queryBuilder.append(SQL_CREATE_TABLE_END_QUERY);
//        try (Connection connection = ConnectorDB.getConnection();
//             Statement statement = connection.createStatement()){
//            statement.executeUpdate(queryBuilder.toString());
//            System.out.println(table.getName() + " was added to database");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    @Override
    public boolean delete(Table table) {
        String query = String.format(SQL_DROP_TABLE_QUERY, table.getName());
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println(table.getName() + " was removed from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> loadTablesNames() {
        List<String> tablesNames = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_LOAD_TABLES_NAMES_QUERY)){
            while (resultSet.next()) {
                tablesNames.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tablesNames;
    }
}
