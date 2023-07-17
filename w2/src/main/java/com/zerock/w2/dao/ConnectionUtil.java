package com.zerock.w2.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Enum 타입을 통해 싱글턴 패턴 구현 -> Connection풀이 여러개일 필요 없으니까
 *
 * Connection 획득 할 때 마다 구현체 생성하는건 번거로우니까
 * DataSource를 통해 (특정)커넥션풀에 접근 -> connection 획득 하도록 만들자!
 *
 * ConnectionUtil을 통해 DataSource에 접근하도록 구현
 *
 * Connection 사용하는쪽은 DAO라 DAO패키지에 생성( 캐바캐 )
 */

public enum ConnectionUtil { //enum도 클래스임, 컴파일시 타입체크가 되기때문에 안정성이 높음. 일반클래스,enum,컬렉션쓰는거 선택은 선택의 문제<설계>
    INSTANCE;

    private HikariDataSource ds; //책은 HikariDataSource , 좀 더 추상화해서 반환해도 되지 않을까? DataSource로 해봤는데 -> DataSource는 인터페이스라 안됨

    ConnectionUtil() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config); //생성자는 return문 생략 가능
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
