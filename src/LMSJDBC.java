import java.sql.*;
public class LMSJDBC {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/lmsbooks";
        String uname = "root";
        String password = ")8X5i5E%crl%Rl@R,tz)";
        String query = "select * from book";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String lmsBookData = "";
                for (int i = 1; i <= 6; i++) {
                    lmsBookData += result.getString(i) + ", ";
                }
                System.out.println(lmsBookData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
