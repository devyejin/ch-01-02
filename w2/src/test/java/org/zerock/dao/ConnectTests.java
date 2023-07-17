package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

//@Slf4j
public class ConnectTests {

//    @Test
//    public void testConnection() throws ClassNotFoundException, SQLException {
//
//        Class.forName("org.mariadb.jdbc.Driver"); //JDBC 드라이버 클래스를 메모리상 로딩
//
//        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/webdb",
//                "webuser",
//                "webuser"); //정보들을 통해 커넥션을 맺고 커넥션 객체를 가
//
//        Assertions.assertNotNull(connection);
//
//        connection.close();
//
//    }

    @Test
    public void testHikariCP() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        HikariDataSource ds = new HikariDataSource(config); //dataSource
        Connection connection = ds.getConnection();

        System.out.println("connection= " +  connection);
        //connection= HikariProxyConnection@1207231495 wrapping org.mariadb.jdbc.Connection@7cbc3762


        connection.close();

    }


}
