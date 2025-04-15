package kyra.me.ecommerce.Classes;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class NewDatabase {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");

    public Map<String, Product> productList;
    public Map<String, Category> categoryList;

    public Connection connection;
    public static NewDatabase instance = new NewDatabase();

    private NewDatabase() {
        try {
            connection = getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Connection to database failed: " + e.getMessage());
            alert.show();
        }
        this.initialize();
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

    public void createProduct(String name, double price, Category category, String description) {
        String registerSQL =
                """
                INSERT INTO Product (ProductID, ProductName, Description, Price, CategoryID)
                VALUES (?,?,?,?,?);
                """;
        try {
            PreparedStatement stmt = connection.prepareStatement(registerSQL);
            // Prevents AutoCommit to prevent unexpected behavior
            connection.setAutoCommit(false);

            try {
                // Create Product Object
                Product product = new Product(name, price, category, description);

                // Set parameters for Product table
                stmt.setString(1, product.getID());
                stmt.setString(2, name);
                stmt.setString(3, description);
                stmt.setDouble(4, price);
                stmt.setString(5, category.getID());

                // Execute both inserts as a batch
                stmt.executeUpdate();
                connection.commit();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully created new product");
                alert.show();

            } catch (SQLException e) {
                connection.rollback();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error. Failed to create product. Please try again later");
                alert.show();
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Authentication error. Please restart your application");
            alert.show();
        }
    }

    public void createCategory(String name) {
        String registerSQL =
                """
                INSERT INTO CATEGORY (categoryid, categoryname)
                VALUES (?,?);
                """;
        try {
            PreparedStatement stmt = connection.prepareStatement(registerSQL);
            // Prevents AutoCommit to prevent unexpected behavior
            connection.setAutoCommit(false);

            try {
                // Create Category Object
                Category category = new Category(name);

                // Set parameters for Category table
                stmt.setString(1, category.getID());
                stmt.setString(2, name);

                // Execute both inserts as a batch
                stmt.executeUpdate();
                connection.commit();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully created new category");
                alert.show();

            } catch (SQLException e) {
                connection.rollback();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error. Failed to create Category. Please try again later");
                alert.show();
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Authentication error. Please restart your application");
            alert.show();
        }
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

    public void initialize() {
        String countSQL =
                """
                SELECT count(ProductID) AS PRODUCTNUMBER
                FROM Product
                SELECT count(CategoryID) AS CATEGORYNUMBER
                FROM Category
                """;
        String fetchProducts =
                """
                SELECT * FROM Product
                """;
        String fetchCategories =
                """
                SELECT * FROM Category
                """;

        try {
            PreparedStatement stmt = connection.prepareStatement(countSQL);

            ResultSet rs = stmt.executeQuery();
            // Executes and gets the result from each part of the query

            int productCount = 0;
            int categoryCount = 0;
            if (rs.next()) {
                productCount = rs.getInt("PRODUCTNUMBER");
            }
            if (rs.next()) {
                categoryCount += rs.getInt("CATEGORYNUMBER");
            }

            Product.IDCounter += productCount;
            Category.IDCounter += categoryCount;

            productList = new HashMap<>(productCount);
            categoryList = new HashMap<>(categoryCount);

            stmt = connection.prepareStatement(fetchCategories);

            rs = stmt.executeQuery();

            // Fetch every Category
            while (rs.next()) {
                Category c = new Category(rs.getString("CategoryName"), rs.getString("CategoryID"));
                categoryList.put(c.getID(), c);
            }

            stmt = connection.prepareStatement(fetchProducts);

            rs = stmt.executeQuery();

            // Fetch every Product
            while (rs.next()) {
                Product p = new Product(
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        categoryList.get(rs.getString("CategoryID")),
                        rs.getString("Description"),
                        rs.getString("ProductID"));
                productList.put(p.getID(), p);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Authentication error. Please restart your application");
            alert.show();
        }
    }

    public Product getProduct(String id) {
        return productList.get(id);
    }
    public Category getCategory(String id) {
        return categoryList.get(id);
    }

    public Product[] getProductList(){
        return productList.values().toArray(new Product[0]);
    }
    public Category[] getCategoryList(){
        return categoryList.values().toArray(new Category[0]);
    }
}
