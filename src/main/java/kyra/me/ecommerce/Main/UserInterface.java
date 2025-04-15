package kyra.me.ecommerce.Main;

import kyra.me.ecommerce.Classes.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

public class UserInterface extends Application {
    static User currentUser = null;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login/Signup Interface");
        Image logo = new Image(getClass().getResource("/kyra/me/ecommerce/Assets/bag_icon.png").toExternalForm());
        primaryStage.getIcons().add(logo);

        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(300); // Set width
        logoView.setPreserveRatio(true);
        // Buttons for Login and Signup
        Button loginButton = new Button("Login");

        Button signupButton = new Button("Signup");

        // Main.Main Layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(logoView,loginButton, signupButton);

        // Scene
        Scene mainScene = new Scene(root, 500, 400);
        mainScene.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Login.css").toExternalForm());

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
        Scene loginScene = new Scene(loginForm, 500, 400);
        primaryStage.setScene(loginScene);
    }

    // Method to Show Signup Form
    private void showSignupForm(Stage primaryStage, Scene mainScene) {
        GridPane signupForm = createSignupForm(primaryStage, mainScene);
        Scene signupScene = new Scene(signupForm, 500, 400);
        primaryStage.setScene(signupScene);
    }

    private void showCustomerHomePage(Stage primaryStage, Scene mainScene) {
        GridPane CustomerHomepage = createCustomerHomepage(primaryStage, mainScene);
        Scene homepageScene = new Scene(CustomerHomepage, 600, 400);
        primaryStage.setScene(homepageScene);
    }

    private void showAdminHomePage(Stage primaryStage, Scene mainScene){
        GridPane AdminHomepage = createAdminHomepage(primaryStage, mainScene);
        Scene homepageScene = new Scene(AdminHomepage, 400, 300);
        primaryStage.setScene(homepageScene);
    }
    private void showProductList(Stage primaryStage, Scene mainScene){
        ScrollPane productListPage = showProductPage(primaryStage, mainScene);
        Scene productScene = new Scene(productListPage, 700, 400);
        primaryStage.setScene(productScene);
    }

    private void showAllOrdersPage(Stage primaryStage, Scene mainScene){
        ScrollPane orderListPage = showAllOrders(primaryStage, mainScene);
        Scene orderScene = new Scene(orderListPage, 700, 500);
        primaryStage.setScene(orderScene);
    }
    private void showOrderProductList(Stage primaryStage, Scene mainScene){
        ScrollPane productListPage = showCartPage(primaryStage,mainScene);
        Scene productScene = new Scene(productListPage, 700, 500);
        primaryStage.setScene(productScene);
    }
    private void showAllPage(Stage primayStage, Scene mainScene){
        GridPane grid = viewAll(primayStage, mainScene);
        Scene scene = new Scene(grid, 700, 500);
        primayStage.setScene(scene);
    }
    private void showAdminCreationPage(Stage primayStage, Scene mainScene){
        GridPane grid = CreateAdminCreationPage(primayStage, mainScene);
        Scene scene = new Scene(grid, 700, 500);
        primayStage.setScene(scene);
    }
    private void showAdminProductPage(Stage primaryStage, Scene mainScene){
        GridPane grid = CreateProductCreationPage(primaryStage, mainScene);
        Scene scene = new Scene(grid, 700, 500);
        primaryStage.setScene(scene);
    }
    private void showAdminCategoryPage(Stage primaryStage, Scene mainScene){
        GridPane grid = CreateCategoryCreationPage(primaryStage, mainScene);
        Scene scene = new Scene(grid, 700, 500);
        primaryStage.setScene(scene);
    }

    private GridPane createCustomerHomepage(Stage primaryStage, Scene mainScene) {
        primaryStage.setTitle("Customer Homepage");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Homepage content
        Label welcomeLabel = new Label("Welcome," + currentUser.getUsername());
        GridPane.setConstraints(welcomeLabel, 0, 0);

        // Create the first VBox for View Products Button
        VBox viewProductsBox = createButtonWithImage("View Products", "/kyra/me/ecommerce/Assets/products_icon.png");
        GridPane.setConstraints(viewProductsBox, 0, 2);

        // Create the second VBox for View Cart Button
        VBox viewCartBox = createButtonWithImage("View Cart", "/kyra/me/ecommerce/Assets/basket_icon.png");
        GridPane.setConstraints(viewCartBox, 1, 2);

        // Create the third VBox for Logout Button
        VBox logoutBox = createButtonWithImage("Logout", "/kyra/me/ecommerce/Assets/logout_icon.png");
        GridPane.setConstraints(logoutBox, 2, 2);

        // Add event handlers
        Button viewProductsButton = (Button) viewProductsBox.getChildren().get(1); // Get the button from VBox
        viewProductsButton.setOnAction(e -> {
            showProductList(primaryStage, mainScene);
        });

        Button viewOrderProductsButton = (Button) viewCartBox.getChildren().get(1); // Get the button from VBox
        viewOrderProductsButton.setOnAction(e -> {
            showOrderProductList(primaryStage, mainScene);
        });

        Button logoutButton = (Button) logoutBox.getChildren().get(1); // Get the button from VBox
        logoutButton.setOnAction(e -> {
            currentUser = null;
            start(primaryStage);
        });
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Customer.css").toExternalForm());


        // Add components to the grid
        grid.getChildren().addAll(welcomeLabel, viewProductsBox, viewCartBox, logoutBox);
        return grid;
    }
    private VBox createButtonWithImage(String buttonText, String imagePath) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER); // Centers the content (image and button)

        // Add image
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(70);  // Set width of image
        imageView.setFitHeight(70); // Set height of image

        // Add button
        Button button = new Button(buttonText);
        button.setMinWidth(150);

        // Add image and button to the VBox
        vbox.getChildren().addAll(imageView, button);
        return vbox;
    }

    private GridPane createAdminHomepage(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Homepage content
        Label welcomeLabel = new Label("Welcome, Admin " + currentUser.getUsername());
        GridPane.setConstraints(welcomeLabel, 0, 0);

        Button viewAllButton = new Button("View all");
        GridPane.setConstraints(viewAllButton, 0, 3);

        Button creationPageButton = new Button("Create new item");
        creationPageButton.getStyleClass().add("creationPageButton");

        GridPane.setConstraints(creationPageButton, 0, 4);


        Button logoutButton = new Button("Logout");
        GridPane.setConstraints(logoutButton, 0, 5);

        viewAllButton.setOnAction(e->{
            showAllPage(primaryStage, mainScene);
        });
        creationPageButton.setOnAction(e->{
            showAdminCreationPage(primaryStage, mainScene);
        });
        logoutButton.setOnAction(e->{
            currentUser = null;
            start(primaryStage);
        });

        grid.getChildren().addAll(welcomeLabel, viewAllButton, creationPageButton, logoutButton);
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Admin.css").toExternalForm());
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
                if (currentUser.isAdmin()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Logging in as: " + username);
                    alert.show();
                    showAdminHomePage(primaryStage, mainScene);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Logging in as: " + username);
                    alert.show();
                    showCustomerHomePage(primaryStage, mainScene);
                }
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
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Login.css").toExternalForm());

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
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Login.css").toExternalForm());

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
        genderComboBox.getItems().addAll("Male", "Female");
        genderComboBox.setPromptText("Select Gender");
        GridPane.setConstraints(genderComboBox, 1, 3);

        // DOB section
        Label dateLabel = new Label("Date of birth:");
        GridPane.setConstraints(dateLabel, 0, 4);
        TextField birthyearField = new TextField();
        TextField birthmonthField = new TextField();
        TextField birthdayField = new TextField();

        birthyearField.setPromptText("Year");
        birthmonthField.setPromptText("M");
        birthdayField.setPromptText("D");

        birthyearField.setPrefWidth(60);
        birthmonthField.setPrefWidth(40);
        birthdayField.setPrefWidth(40);

        HBox dateBox = new HBox(birthyearField, birthmonthField, birthdayField);
        GridPane.setConstraints(dateBox, 1, 4);

        // Signup Button
        Button signupButton = new Button("Signup");
        GridPane.setConstraints(signupButton, 1, 5);
        signupButton.setOnAction(_ -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            String confirmPassword = confirmPasswordInput.getText();
            String gender = genderComboBox.getValue();
            LocalDate dob;
            try {
                int year = Integer.parseInt(birthyearField.getText().trim()); //removes extra spaces with .trim()
                int month = Integer.parseInt(birthmonthField.getText().trim());
                int day = Integer.parseInt(birthdayField.getText().trim());

                if(year < Year.now().getValue() - 120 || year > Year.now().getValue() - 16){
                    throw new DateTimeException("Invalid birth year");
                }

                dob = LocalDate.of(year, month, day);
            } catch (DateTimeException e) { // If an exception is thrown, the date is invalid
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid birth year");
                alert.show();
                return;
            }
            if (username == null || password == null || confirmPassword == null || gender == null) { //make sure all fields are filled in
                Alert alert = new Alert(Alert.AlertType.ERROR, "Make sure to fill in all fields");
                alert.show();
            }
            else if (password.length() < 8){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords must be at least 8 characters long");
                alert.show();
            }
            else if (!password.equals(confirmPassword)) { //make sure the passwords match
                Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords do not match!");
                alert.show();
            }
            else if(!NewDatabase.instance.isUnique(username)){ //make sure the username is unique
                Alert alert = new Alert(Alert.AlertType.ERROR, "Username is already taken");
                alert.show();
            }
            else {
                //if all conditions are met, create an account
                Date date = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
                currentUser = NewDatabase.instance.registerCustomer(username,password,date,gender);
                if (currentUser != null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account created successfully");
                    alert.show();
                    showCustomerHomePage(primaryStage, mainScene);
                }
            }
        });

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 5);
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));

        // Add nodes to GridPane
        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, confirmPasswordLabel, confirmPasswordInput,
                genderLabel, genderComboBox, signupButton, backButton, dateLabel, dateBox);
        return grid;
    }

    private ScrollPane showProductPage(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);

        Product[] productList = NewDatabase.instance.getProductList();

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 0);
        if (currentUser.isAdmin()){
            backButton.setOnAction(e -> showAdminHomePage(primaryStage, mainScene));
        }
        else {
            backButton.setOnAction(e -> showCustomerHomePage(primaryStage, mainScene));
        }
        grid.getChildren().add(backButton);

        for(int i = 0; i < productList.length; i++){
            if (productList[i] == null){
                continue;
            }
            Label usernameLabel = new Label(productList[i].toString());
            GridPane.setConstraints(usernameLabel, 0, i+1);
            grid.getChildren().add(usernameLabel);
            if (!currentUser.isAdmin()){
                Button addToCart = new Button("Add to cart");
                addToCart.getStyleClass().add("addtocart");

                GridPane.setConstraints(addToCart, 1, i+1);
                String str = productList[i].getID();
                addToCart.setOnAction(e -> {
                    ((Customer)currentUser).AddToCart(str);
                });
                grid.getChildren().add(addToCart);
            }
            else {
                Button deleteProduct = new Button("Delete product");
                GridPane.setConstraints(deleteProduct, 1, i+1);
                String str = productList[i].getID();
                deleteProduct.setOnAction(e -> {
                    ((Admin)currentUser).DeleteProduct(str);
                    showAdminHomePage(primaryStage, mainScene);
                });
                grid.getChildren().add(deleteProduct);
            }
        }
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Customer.css").toExternalForm());
        return scrollPane;
    }

    private GridPane CreateProductCreationPage(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 0);
        backButton.setOnAction(e -> showAdminHomePage(primaryStage, mainScene));

        // Name Field
        Label nameLabel = new Label("Product name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        // Description Field
        Label descLabel = new Label("Product description:");
        GridPane.setConstraints(descLabel, 0, 2);
        TextField descInput = new TextField();
        GridPane.setConstraints(descInput, 1, 2);

        // Price Field
        Label priceLabel = new Label("Product price:");
        GridPane.setConstraints(priceLabel, 0, 3);
        TextField priceInput = new TextField();
        GridPane.setConstraints(priceInput, 1, 3);

        // Category ComboBox
        Label categoryLabel = new Label("Category:");
        GridPane.setConstraints(categoryLabel, 0, 4);
        ComboBox<Category> categoryComboBox = new ComboBox<>();

        for(Category category: NewDatabase.instance.getCategoryList()){
            categoryComboBox.getItems().add(category);
        }
        categoryComboBox.setPromptText("Select Category");
        GridPane.setConstraints(categoryComboBox, 1, 4);

        Button createProductButton = new Button("Confirm");
        GridPane.setConstraints(createProductButton, 1, 5);
        createProductButton.setOnAction(e -> {
            String productName = nameInput.getText();
            String description = descInput.getText();
            double price;

            try {
                // Removes extra spaces
                price = Double.parseDouble(priceInput.getText().trim());
            } catch (NumberFormatException ex) {
                // If an exception is thrown, the price is invalid
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid price");
                alert.show();
                return;
            }

            if (productName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Name space can't be left empty");
                alert.show();
            }
            else if (productName.length() > 100) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Product name must be less than 100 characters long");
                alert.show();
            }
            else if (categoryComboBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Category can't be left empty");
                alert.show();
            }
            else {
                // If all conditions are met, create a product
                NewDatabase.instance.createProduct(productName, price, categoryComboBox.getValue(), description);
                showAdminHomePage(primaryStage, mainScene);
            }
        });

        grid.getChildren().addAll(backButton, nameLabel, nameInput, descLabel, descInput, priceLabel,
                priceInput,categoryLabel, categoryComboBox, createProductButton);
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Admin.css").toExternalForm());

        return grid;
    }

    private GridPane CreateCategoryCreationPage(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 0);
        backButton.setOnAction(e -> showAdminHomePage(primaryStage, mainScene));

        // Name Field
        Label nameLabel = new Label("Category name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Button createCategoryButton = new Button("Confirm");
        GridPane.setConstraints(createCategoryButton, 1, 5);
        createCategoryButton.setOnAction(e -> {
            String productName = nameInput.getText();

            if (productName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Name space can't be left empty");
                alert.show();
            }
            else if (productName.length() > 50) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Category name must be less than 50 characters long");
                alert.show();
            }
            else {
                // If all conditions are met, create a product
                NewDatabase.instance.createCategory(productName);
                showAdminHomePage(primaryStage, mainScene);
            }
        });

        grid.getChildren().addAll(backButton, nameLabel, nameInput, createCategoryButton);
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Admin.css").toExternalForm());

        return grid;
    }

    private GridPane CreateAdminCreationPage(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 0);
        backButton.setOnAction(e -> showAdminHomePage(primaryStage, mainScene));
        grid.getChildren().add(backButton);

        Button createAProductButton = new Button("Create a product");
        GridPane.setConstraints(createAProductButton, 0, 2);
        createAProductButton.setOnAction(e->showAdminProductPage(primaryStage, mainScene));
        grid.getChildren().add(createAProductButton);

        Button createACategoryButton = new Button("Create a category");
        GridPane.setConstraints(createACategoryButton, 0, 3);
        createACategoryButton.setOnAction(e->showAdminCategoryPage(primaryStage, mainScene));
        grid.getChildren().add(createACategoryButton);
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Admin.css").toExternalForm());

        return grid;
    }

    private ScrollPane showCartPage(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);

        Product[] productlist = ((Customer)currentUser).getCart().getProducts();

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 0);
        backButton.setOnAction(e -> showCustomerHomePage(primaryStage, mainScene));
        grid.getChildren().add(backButton);

        // Order Button
        Button orderButton = new Button("Order");
        GridPane.setConstraints(orderButton, 1, 0);
        orderButton.setOnAction(e ->{
            if (((Customer)currentUser).getCart().getCount() > 0)
            {
                ((Customer)currentUser).Checkout(1,"");
            }
        });
        grid.getChildren().add(orderButton);

        for(int i = 0; i < productlist.length; i++){
            if (productlist[i] == null){
                continue;
            }
            Label usernameLabel = new Label(productlist[i].toString());
            GridPane.setConstraints(usernameLabel, 0, i+1);
            grid.getChildren().add(usernameLabel);
            Button removeFromCart = new Button("Remove from the cart");
            removeFromCart.getStyleClass().add("remove");


            GridPane.setConstraints(removeFromCart, 1, i+1);
            int num = i;
            removeFromCart.setOnAction(e -> {
                ((Customer)currentUser).getCart().removeProduct(num);
                showCustomerHomePage(primaryStage, mainScene);
            });
            grid.getChildren().add(removeFromCart);
        }
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Customer.css").toExternalForm());

        return scrollPane;
    }
    private GridPane viewAll(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 0);
        backButton.setOnAction(e -> showAdminHomePage(primaryStage, mainScene));
        grid.getChildren().add(backButton);

        Button viewAllProductsButton = new Button("View all products");
        GridPane.setConstraints(viewAllProductsButton, 0, 2);
        viewAllProductsButton.setOnAction( e->showProductList(primaryStage, mainScene));
        grid.getChildren().add(viewAllProductsButton);

        Button viewAllCategoriesButton = new Button("View all orders");
        GridPane.setConstraints(viewAllCategoriesButton, 0, 3);
        viewAllCategoriesButton.setOnAction( e->showAllOrdersPage(primaryStage, mainScene));
        grid.getChildren().add(viewAllCategoriesButton);
        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Admin.css").toExternalForm());

        return grid;
    }

    private ScrollPane showAllOrders(Stage primaryStage, Scene mainScene) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);

        Order[] orderList = Database.getOrderList();

        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 0);
        backButton.setOnAction(e -> showAdminHomePage(primaryStage, mainScene));
        grid.getChildren().add(backButton);

        for(int i = 0; i < orderList.length; i++){
            if (orderList[i] == null){
                continue;
            }
            Label orderLabel = new Label(orderList[i].toString());
            GridPane.setConstraints(orderLabel, 0, i+1);
            grid.getChildren().add(orderLabel);
        }

        grid.getStylesheets().add(getClass().getResource("/kyra/me/ecommerce/Assets/Customer.css").toExternalForm());

        return scrollPane;
    }
}
