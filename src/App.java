import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            // st = conn.prepareStatement(
            //   "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)",
            //   Statement.RETURN_GENERATED_KEYS
            // );

            // st.setString(1, "Carl Purple");
            // st.setString(2, "carl@gmail.com");
            // st.setDate(3, Date.valueOf(LocalDate.parse("1985-04-22")));
            // st.setDouble(4, 3000);
            // st.setInt(5, 4);

            st = conn.prepareStatement(
                "insert into department (Name) values ('D1'), ('D2')",
                Statement.RETURN_GENERATED_KEYS
            );

            int rowsAffected = st.executeUpdate(); 
            if (rowsAffected > 0)  {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = " + id);
                }
            } else {
                System.out.println("no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
