import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Transaction {
  public static void main() {
    Connection conn = null;
    Statement st = null;

    try {
      conn = DB.getConnection();
      // não confirma as operações automáticamente
      conn.setAutoCommit(false);
      st = conn.createStatement();


      int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

      // int x = 1;
      // if (x < 2) {
      //   throw new SQLException("Fake error");
      // }

      int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

      // confirmando que a transação terminou
      conn.commit();

      System.out.println("rows1 " + rows1);
      System.out.println("rows2 " + rows2);
    } catch (SQLException e) {
      try {
        conn.rollback();
        throw new DbException("Transaction rolled backd! Caused by: " + e.getMessage());
      } catch (SQLException e2) {
        throw new DbException("Error trying to rollback: cause by: " + e2.getMessage());
      }
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}