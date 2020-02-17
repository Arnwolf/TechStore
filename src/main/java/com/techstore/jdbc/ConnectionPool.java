package com.techstore.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static HikariConfig poolConfig = new HikariConfig();
    private static HikariDataSource pool;
    private final static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

    public static void init(final String pathToConfig) {
        try(InputStream confStream = new FileInputStream(pathToConfig + File.separator + "application.properties")) {
            Properties dbConfig = new Properties();
            dbConfig.load(confStream);

            poolConfig.setDriverClassName(dbConfig.getProperty("driver-name"));
            poolConfig.setJdbcUrl(dbConfig.getProperty("database-url"));
            poolConfig.setUsername(dbConfig.getProperty("database-username"));
            poolConfig.setPassword(dbConfig.getProperty("database-pass"));
            poolConfig.addDataSourceProperty("cachePrepStmts", dbConfig.getProperty("pool-cache-statements"));
            poolConfig.addDataSourceProperty("prepStmtCacheSize", dbConfig.getProperty("pool-statements-cache-size"));
            poolConfig.addDataSourceProperty("prepStmtCacheSqlLimit", dbConfig.getProperty("pool-cache-statements-max-size"));
            poolConfig.setMaximumPoolSize(Integer.parseInt(dbConfig.getProperty("pool-max-size")));

            pool = new HikariDataSource(poolConfig);
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
