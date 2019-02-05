package com.alevel.prokopchuk.dao;

import com.alevel.prokopchuk.ConnectorDB;
import com.alevel.prokopchuk.models.Column;
import com.alevel.prokopchuk.models.Table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NodeDao {

    private static final String SQL_UPDATE_TABLE = "UPDATE %s SET %s = \'%s\' WHERE ID = %d";

    public boolean insert(Table.Node node, Column column, String value) {
        String tableName = node.getTable().getName();
        String query = String.format(SQL_UPDATE_TABLE, tableName, column.getName(),
                value, node.getId());
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println(value + " was inserted to table: " + tableName
            + " in column: " + column.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
