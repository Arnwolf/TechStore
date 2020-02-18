package com.techstore.jdbc;

import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static HikariDataSource pool = new HikariDataSource();
    private final static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

    public static void setConfiguration(final String pathToConfig) {
        try(InputStream confStream = new FileInputStream(pathToConfig)) {
            Properties dbConfig = new Properties();
            dbConfig.load(confStream);

            pool.setDriverClassName(dbConfig.getProperty("driver-name"));
            pool.setJdbcUrl(dbConfig.getProperty("database-url"));
            pool.setUsername(dbConfig.getProperty("database-username"));
            pool.setPassword(dbConfig.getProperty("database-pass"));
            pool.addDataSourceProperty("cachePrepStmts", dbConfig.getProperty("pool-cache-statements"));
            pool.addDataSourceProperty("prepStmtCacheSize", dbConfig.getProperty("pool-statements-cache-size"));
            pool.addDataSourceProperty("prepStmtCacheSqlLimit", dbConfig.getProperty("pool-cache-statements-max-size"));
            pool.setMaximumPoolSize(Integer.parseInt(dbConfig.getProperty("pool-max-size")));
        } catch (final Exception exc) {
            LOG.log(Level.ALL, exc.toString());
            exc.printStackTrace();
        }
    }

    private ConnectionPool() {}

    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public static void close() {
        if (pool != null)
            pool.close();
    }
}
