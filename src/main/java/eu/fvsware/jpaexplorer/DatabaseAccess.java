/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fvsware.jpaexplorer;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author fvasi
 */
public class DatabaseAccess {

    public static void startDB() throws SQLException {
        Server server = Server.createTcpServer().start();

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:./test");
        ds.setUser("sa");
        ds.setPassword("sa");
        Connection conn = ds.getConnection();
    }
}
