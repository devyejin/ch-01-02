package com.zerock.jdbcex.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTests {

    @Test
    public void testConnection() throws ClassNotFoundException, SQLException {
        //given
            Class.forName("org.mariadb.jdbc.Driver"); //(대략) static 블럭내에 객체를 생성하도록 구현해놓음 -> 클래스 로더될 때 static 블럭 먼저 초기화되며 Driver 객체가 생성됨

        Connection con = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "webuser"
                );

        //then
        Assertions.assertNotNull(con);

        con.close();
    }

    /**기존방식은 DirverManager통해서 바로 connection 객체 얻는 방법
    HikariCP는 커넥션풀에 미리 conenction 생성해놓고 갖다쓰는 방법
    커넥션풀 구현 체 중 하나가 HikariCP임, 어떤 커넥션풀을 쓸건지, DriverManager를 통해 바로 갖다 쓸건지
    변경될 때 마다 로직이 변경되기 때문에 의존성이 큼
     의존성을 끊어주기 위해 DataSource인터페이스 이용!
     */
    @Test
    @DisplayName("HikariCP 커넥션풀을 이용")
    public void testHikariCP() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config); //DataSource이용
        //ds만 바라보도록!
        Connection con = ds.getConnection();

        Assertions.assertNotNull(con);
        System.out.println(con);
        con.close();
    }


}
