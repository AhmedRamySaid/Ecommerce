import Classes.Customer;
import Classes.Database;
import Classes.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface extends Application {
    static User currentUser = null;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login/Signup Interface");

        // Buttons for Login and Signup
        Button loginButton = new Button("Login");
        Button signupButton = new Button("Signup");

        // Main Layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(loginButton, signupButton);

        // Scene
        Scene mainScene = new Scene(root, 400, 300);

        // Set Scene and Show Stage
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // Button Actions
        loginButton.setOnAction(e -> showLoginForm(primaryStage, mainScene));
        signupButton.setOnAction(e -> showSignupForm(primaryStage, mainScene));
    }

    // Method to Show Login Form
    private void showLoginForm(Stage primaryStage, Scene mainScene) {
        GridPane loginForm = createLoginForm(primaryStage, mainScene);
        Scene loginScene = new Scene(loginForm, 400, 300);
        primaryStage.setScene(loginScene);
    }

    // Method to Show Signup Form
    private void showSignupForm(Stage primaryStage, Scene mainScene) {
        GridPane signupForm = createSignupForm(primaryStage, mainScene);
        Scene signupScene = new Scene(signupForm, 400, 300);
        primaryStage.setScene(signupScene);
    }

    private void showCustomerHomePage(Stage primaryStage, Scene mainScene) {
        GridPane CustomerHomepage = createCustomerHomepage(primaryStage, mainScene);
        Scene homepageScene = new Scene(CustomerHomepage, 400, 300);
        primaryStage.setScene(homepageScene);
    }

    private GridPane createCustomerHomepage(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Homepage content
        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername() + "!");
        GridPane.setConstraints(welcomeLabel, 0, 0);
        Button viewProductsButton = new Button("View Products");
        GridPane.setConstraints(viewProductsButton, 0, 2);
        Button logoutButton = new Button("Logout");
        GridPane.setConstraints(logoutButton, 0, 3);

        viewProductsButton.setOnAction(e->{

        });
        logoutButton.setOnAction(e->{
           currentUser = null;
           start(primaryStage);
        });

        grid.getChildren().addAll(welcomeLabel, viewProductsButton, logoutButton);
        return grid;
    }
    // Method to create the Login Form
    private GridPane createLoginForm(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Username Field
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 0);

        // Password Field
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        // Login Button
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            currentUser = User.Login(username, password);
            if (currentUser != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Logging in as: " + username);
                alert.show();
                showCustomerHomePage(primaryStage, mainScene);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password");
                alert.show();
            }
        });

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 2);
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));

        // Add nodes to GridPane
        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton, backButton);
        return grid;
    }

    // Method to create the Signup Form
    private GridPane createSignupForm(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Username Field
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 0);

        // Password Field
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        // Confirm Password Field
        Label confirmPasswordLabel = new Label("Confirm Password:");
        GridPane.setConstraints(confirmPasswordLabel, 0, 2);
        PasswordField confirmPasswordInput = new PasswordField();
        GridPane.setConstraints(confirmPasswordInput, 1, 2);

        // Gender ComboBox
        Label genderLabel = new Label("Gender:");
        GridPane.setConstraints(genderLabel, 0, 3);
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Man", "Woman");
        genderComboBox.setPromptText("Select Gender");
        GridPane.setConstraints(genderComboBox, 1, 3);

        // Signup Button
        Button signupButton = new Button("Signup");
        GridPane.setConstraints(signupButton, 1, 4);
        signupButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            String confirmPassword = confirmPasswordInput.getText();
            String gender = genderComboBox.getValue();
            if (username == null || password == null || confirmPassword == null || gender == null) { //make sure all fields are filled in
                Alert alert = new Alert(Alert.AlertType.ERROR, "Make sure to fill in all fields");
                alert.show();
            }
            else if (!password.equals(confirmPassword)) { //make sure the passwords match
                Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords do not match!");
                alert.show();
            }
            else if(Database.getUser(username) != null){ //make sure the username is unique
                Alert alert = new Alert(Alert.AlertType.ERROR, "Unavailable username");
                alert.show();
            }
            else { //if all conditions are met, create an account
                currentUser = Customer.Register(username,password,"",gender);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account created successfully");
                alert.show();
                showCustomerHomePage(primaryStage, mainScene);
            }
        });

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 4);
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));

        // Add nodes to GridPane
        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, confirmPasswordLabel, confirmPasswordInput, genderLabel, genderComboBox, signupButton, backButton);
        return grid;
    }
}
