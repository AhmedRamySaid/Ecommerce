package kyra.me.ecommerce.Classes;

import javafx.scene.control.Alert;

import java.sql.*;

public class NewDatabase {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Ecommerce;trustServerCertificate=true;";
    private static final String USER = "Admin";
    private static final String PASS = "1610";

    public Connection connection;
    public static NewDatabase instance = new NewDatabase();

    private NewDatabase() {
        try {
            connection = getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Connection to database failed: " + e.getMessage());
            alert.show();
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public User authenticate(String username, String password) {
        String sql = """
            SELECT * FROM [User] AS u
            LEFT JOIN Admin AS a ON a.Username = u.Username
            LEFT JOIN Customer AS c on c.Username = u.Username
            WHERE u.username = ?
            """;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Verify password first
                    String storedHash = rs.getString("Password");
                    if (!storedHash.equals(password)) return null;

                    // Check user type and create appropriate object
                    Date dob = rs.getDate("DateOfBirth");
                    String role = rs.getString("Role");

                    if (role != null) {
                        return new Admin(username, dob, role);
                    } else {
                        String gender = rs.getString("Gender");
                        String address = rs.getString("Address");
                        return new Customer(username, dob, Customer.StringToGender(gender), address);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Authentication error: " + e.getMessage());
        }
        return null; // User not found or error occurred
    }
}
