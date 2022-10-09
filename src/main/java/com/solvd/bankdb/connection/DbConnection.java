package com.solvd.bankdb.connection;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

        private static Connection object;

       private String resource = "src/main/resources/mybatis-config.xml";
        private String file = "src/main/resources/database/db.properties";

        private String connectionURL;

        private String userName;

        private String psswd;

    public DbConnection() {
    }

    public Connection createConnection() throws SQLException, IOException {
            this.readUserInfo();
            return DriverManager.getConnection(connectionURL, userName, psswd);
        }


        public BasicDataSource pollConnection() throws IOException {
            BasicDataSource ds = new BasicDataSource();
            this.readUserInfo();
            ds.setUrl(this.connectionURL);
            ds.setUsername(userName);
            ds.setPassword(psswd);
            ds.setMaxTotal(10);
            return ds;
        }
        private void readUserInfo() throws IOException {
            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get(file)));
            this.connectionURL = properties.getProperty("db.url");
            this.userName = properties.getProperty("db.user");
            this.psswd = properties.getProperty("db.password");
        }
        public SqlSessionFactory myBatisConnection() throws IOException {
            Reader reader = new FileReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return sqlSessionFactory;
        }
    }
