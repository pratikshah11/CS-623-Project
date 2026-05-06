/*
CS 623 - Final Project
Name - Moldir Nurmakhan
Transactions 1 and 2
*/

import java.sql.*;

public class Main {
    static final String URL = "jdbc:postgresql://localhost:5432/cs623project";
    static final String USER = "postgres";
    static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);
            System.out.println("Connected to PostgreSQL!");

            System.out.println("\n--- Transaction 1 ---");
            resetData(conn);
            deleteProduct(conn);

            System.out.println("\n--- Transaction 2 ---");
            resetData(conn);
            deleteDepot(conn);

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    static void resetData(Connection conn) throws SQLException {
        try {
            conn.prepareStatement("DELETE FROM stock").executeUpdate();
            conn.prepareStatement("DELETE FROM product").executeUpdate();
            conn.prepareStatement("DELETE FROM depot").executeUpdate();

            conn.prepareStatement("INSERT INTO product VALUES " +
                    "('p1','tape',2.50),('p2','tv',250.00)," +
                    "('p3','vcr',80.00),('p4','player',600.00),('p5','dvd',120.00)").executeUpdate();

            conn.prepareStatement("INSERT INTO depot VALUES " +
                    "('d1','New York',9000),('d2','Syracuse',6000),('d4','New York',2000)").executeUpdate();

            conn.prepareStatement("INSERT INTO stock VALUES " +
                    "('p1','d1',1000),('p1','d2',-1000),('p1','d4',1200)," +
                    "('p3','d1',3000),('p3','d4',2000),('p2','d2',1500)," +
                    "('p2','d1',-400),('p4','d2',700),('p5','d1',400),('p5','d4',200)").executeUpdate();

            conn.commit();
            System.out.println("Data reset successfully!");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Reset failed: " + e.getMessage());
        }
    }

    static void deleteProduct(Connection conn) throws SQLException {
        try {
            PreparedStatement st1 = conn.prepareStatement("DELETE FROM stock WHERE prodid = ?");
            st1.setString(1, "p1");
            st1.executeUpdate();

            PreparedStatement st2 = conn.prepareStatement("DELETE FROM product WHERE prodid = ?");
            st2.setString(1, "p1");
            st2.executeUpdate();

            conn.commit();
            System.out.println("Transaction 1 done: p1 deleted from product and stock");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transaction 1 failed: " + e.getMessage());
        }
    }

    static void deleteDepot(Connection conn) throws SQLException {
        try {
            PreparedStatement st1 = conn.prepareStatement("DELETE FROM stock WHERE depid = ?");
            st1.setString(1, "d1");
            st1.executeUpdate();

            PreparedStatement st2 = conn.prepareStatement("DELETE FROM depot WHERE depid = ?");
            st2.setString(1, "d1");
            st2.executeUpdate();

            conn.commit();
            System.out.println("Transaction 2 done: d1 deleted from depot and stock");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transaction 2 failed: " + e.getMessage());
        }
    }
}