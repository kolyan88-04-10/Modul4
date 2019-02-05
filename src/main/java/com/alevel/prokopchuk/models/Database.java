package com.alevel.prokopchuk.models;

import com.alevel.prokopchuk.dao.DatabaseDao;
import com.alevel.prokopchuk.dao.NodeDao;
import com.alevel.prokopchuk.dao.TableDao;

import java.util.ArrayList;
import java.util.List;

public class Database extends Model {
    private List<Table> tables = new ArrayList<>();
    private DatabaseDao databaseDao;
    private TableDao tableDao;
    private NodeDao nodeDao;

    public Database(String name, DatabaseDao databaseDao,
                    TableDao tableDao, NodeDao nodeDao) {
        super(name);
        this.databaseDao = databaseDao;
        this.tableDao = tableDao;
        this.nodeDao = nodeDao;
        loadTables();
    }

    public void addTable(Table table){
        tables.add(table);
        databaseDao.create(table);
    }

    public void removeTable(Table table){
        databaseDao.delete(table);
        tables.remove(table);
    }

    public List<Table> getTables() {

        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    private void loadTables() {
        List<String> tablesNames = databaseDao.loadTablesNames();
        for (String tableName : tablesNames) {
            Table table = new Table(tableName, tableDao, nodeDao);
            table.load();
            tables.add(table);
            System.out.println("Table " + tableName + " was loaded from database");
        }
        System.out.println(tablesNames.size() + " tables was loaded from database");
    }
}
