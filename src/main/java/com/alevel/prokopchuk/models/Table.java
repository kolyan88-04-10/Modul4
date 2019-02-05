package com.alevel.prokopchuk.models;

import com.alevel.prokopchuk.dao.NodeDao;
import com.alevel.prokopchuk.dao.TableDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table extends Model {
    private TableDao tableDao;
    private NodeDao nodeDao;
    private List<Column> columns = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    private int nodeCounter;


    public Table(String name, TableDao tableDao, NodeDao nodeDao) {
        super(name);
        this.tableDao = tableDao;
        this.nodeDao = nodeDao;
    }

    public Table addColumn(Column column){
        tableDao.addColumn(this, column);
        columns.add(column);
        for (Node node : nodes) {
            node.getValues().put(column, null);
        }
        return this;
    }

    public Table removeColumn(Column column){
        tableDao.removeColumn(this, column);
        columns.remove(column);
        for (Node node : nodes) {
            node.getValues().remove(column);
        }
        return this;
    }

    public Table renameColumn(Column column, String newName){
        tableDao.renameColumn(this, column, newName);
        column.setName(newName);
        return this;
    }

    public Node newNode(){
        Node node = new Node();
        tableDao.addNode(this, node);
        return node;
    }

    public Table removeNode(Node node){
        tableDao.removeNode(this, node);
        nodes.remove(node);
        return this;
    }

    public void load() {
        tableDao.loadTable(this);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }



    public class Node {

        private Map<Column, String> values = new HashMap<>();
        private int id;

        public Node(Map<Column, String> values, int nodeId) {
            Table.this.nodeCounter++;
            this.values = values;
            Table.this.nodes.add(this);
        }

        public Node() {
            id = ++Table.this.nodeCounter;
            for (Column column : Table.this.columns) {
                values.put(column, "");
                Table.this.nodes.add(this);
            }
        }

        public Map<Column, String> getValues() {
            return values;
        }

        public void setValues(Map<Column, String> values) {
            this.values = values;
        }

        public void insert(Column column, String value) {
            values.put(column, value);
            Table.this.nodeDao.insert(this, column, value);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Table getTable() {
            return Table.this;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "values=" + values +
                    ", id=" + id +
                    '}';
        }
    }
}
