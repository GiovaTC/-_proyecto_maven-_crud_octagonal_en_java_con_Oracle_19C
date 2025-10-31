package com.example.registro.config;

import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.SQLException;

public final class DataSourceConfig {
    private DataSourceConfig() {}

    public static DataSource build(String url, String user, String password) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(url);
            ods.setUser(user);
            ods.setPassword(password);
            return ods;
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo crear DataSource", e);
        }
    }
}
