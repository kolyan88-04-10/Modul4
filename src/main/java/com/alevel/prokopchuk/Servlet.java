package com.alevel.prokopchuk;

import com.alevel.prokopchuk.dao.DatabaseDao;
import com.alevel.prokopchuk.dao.NodeDao;
import com.alevel.prokopchuk.dao.TableDao;
import com.alevel.prokopchuk.models.Database;
import com.alevel.prokopchuk.models.Table;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DatabaseDao databaseDao = new DatabaseDao();
        TableDao tableDao = new TableDao();
        NodeDao nodeDao = new NodeDao();
        Database database = new Database("Database1", databaseDao, tableDao, nodeDao);

    }
}
