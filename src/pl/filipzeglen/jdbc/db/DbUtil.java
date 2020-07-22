package pl.filipzeglen.jdbc.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DbUtil {
    private static DbUtil dbUtil;
    private HikariDataSource dataSource;

    private DbUtil() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=UTC");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("admin");
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        if(dataSource != null)
            dataSource.close();
    }

    public static DbUtil getInstance() {
        if (dbUtil == null) {
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }
}