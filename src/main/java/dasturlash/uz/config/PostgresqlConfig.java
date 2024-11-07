package dasturlash.uz.config;


import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresqlConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/Jakarta_lesson_1";
    private static final String USER = "Sardorbek";
    private static final String PASSWORD = "sardor0304";

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }




}
