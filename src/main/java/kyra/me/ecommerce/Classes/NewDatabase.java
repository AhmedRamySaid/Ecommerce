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
        String sql =
            """
            SELECT * FROM [User] AS u
            LEFT JOIN Admin AS a ON a.Username = u.Username
            LEFT JOIN Customer AS c on c.Username = u.Username
            WHERE u.username = ?
            """;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Verify password first
                String storedHash = rs.getString("Password");
                if (!PasswordService.verifyPassword(password, storedHash)) return null;

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
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Authentication error: " + e.getMessage());
            alert.show();
        }
        return null; // User not found or error occurred
    }

    public User registerCustomer(String username, String password, java.util.Date dateOfBirth,
                                 String gender) {
        String registerSQL =
                """
                INSERT INTO [User] (Username, Password, DateOfBirth) VALUES (?, ?, ?);
                INSERT INTO Customer (Username, Gender, Address) VALUES (?, ?, ?);
                """;
        try {
            PreparedStatement stmt = connection.prepareStatement(registerSQL);
            // Prevents AutoCommit to prevent unexpected behavior
            connection.setAutoCommit(false);

            try {
                // Hash password before storing
                String hashedPassword = PasswordService.hashPassword(password);

                // Set parameters for User table
                stmt.setString(1, username);
                stmt.setString(2, hashedPassword);
                stmt.setDate(3, new java.sql.Date(dateOfBirth.getTime()));

                // Set parameters for Customer table
                stmt.setString(4, username);
                stmt.setString(5, gender);
                stmt.setString(6, "");

                // Execute both inserts as a batch
                stmt.executeUpdate();
                connection.commit();
                return new Customer(username, dateOfBirth, Customer.StringToGender(gender), "");

            } catch (SQLException e) {
                connection.rollback();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error. Failed to register. Please try again later");
                alert.show();
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Authentication error. Please restart your application");
            alert.show();
            return null;
        }
        return null;
    }

    public boolean isUnique(String username){
        String searchSQL =
                """
                SELECT Username
                FROM [User]
                WHERE Username = ?
                """;

        try {
            PreparedStatement stmt = connection.prepareStatement(searchSQL);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Username");
                return name == null;
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Authentication error. Please restart your application");
            alert.show();
            return false;
        }
        return false;
    }
}
