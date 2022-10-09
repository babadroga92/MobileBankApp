package com.solvd.bankdb;
import com.solvd.bankdb.connection.DbConnection;
import com.solvd.bankdb.dao.BankDao;
import com.solvd.bankdb.service.BankServiceImpl;
import com.solvd.bankdb.service.MobileApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;


public class Main {
    private static Logger log = LogManager.getLogger(Main.class.getName());


    public static void main(String[] args) {

        DbConnection dbConnection = new DbConnection();

        try {
            Connection connection = dbConnection.createConnection();
            BankDao radnik = new BankDao(connection);
            BankServiceImpl menadzer = new BankServiceImpl(radnik);
            MobileApp mobileApp = new MobileApp(connection,radnik,menadzer);
            mobileApp.login();

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
