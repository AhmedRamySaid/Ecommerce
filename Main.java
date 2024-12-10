import Classes.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static User currentUser = null;
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Customer.Register("Ramy", "1610", new Date(), Customer.GetGender("1"));
        new Admin("Kareem", "1610", new Date(), "Engineer", 40);
        new Admin("Hager", "1610", new Date(), "Engineer", 40);

        while (true) {
            currentUser = LoginAndRegister();
            while (currentUser != null) {
                if(currentUser.isAdmin()){
                    AdminInteraction();
                }
                else if (currentUser != null){
                    UserInteraction();
                }
            }
        }
    }

    public static User LoginAndRegister(){
        User currentUser = null;

        while(currentUser == null) {
            System.out.println("Press 1 to login. Press 2 to register. Press 3 to exit");
            String st = sc.nextLine();
            switch(st) {
                case "1":
                    System.out.print("Username: ");
                    String username1 = sc.nextLine();
                    System.out.print("Password: ");
                    String password1 = sc.nextLine();
                    currentUser = User.Login(username1, password1);
                    break;
                case "2":
                    System.out.print("Username: ");
                    String username2 = sc.nextLine();
                    System.out.print("Password: ");
                    String password2 = sc.nextLine();
                    System.out.print("Select your gender (1 for man 2 for woman): ");
                    String gender = sc.nextLine();
                    while (!(gender.equals("1") || gender.equals("2"))) {
                        System.out.print("Please enter a valid number: ");
                        gender = sc.nextLine();
                    }
                    currentUser = Customer.Register(username2, password2, new Date(), Customer.GetGender(gender));
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
        System.out.println("Welcome, " + currentUser.getUsername());
        return currentUser;
    }

    public static void UserInteraction(){
        Customer customer = (Customer)currentUser;
        System.out.println("1. View Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. logout");
        System.out.println("6. Exit");
        System.out.print("Please select an option: ");

        String choice = sc.nextLine();
        sc.nextLine();

        switch(choice){
            case "1":
                currentUser.ShowAllProduct();
                break;
            case "2":
                System.out.print("Enter product code: ");
                String str = sc.nextLine();
                customer.AddToCart(str);
                break;
            case "3":
                customer.ViewCart();
                break;
            case "4":

                break;
            case "5":
                currentUser = null;
                break;
            case "6":
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid option! Try again.");
                break;
        }
    }

    private static void AdminInteraction() {
        Admin admin = (Admin)currentUser;

        System.out.println("1. Create a product");
        System.out.println("2. Delete a product");
        System.out.println("3. Create a category");
        System.out.println("5. logout");
        System.out.println("6. Exit");
        System.out.print("Please select an option: ");

        String choice = sc.nextLine();

        switch(choice){
            case "1":
                try{
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                System.out.print("Enter category code: ");
                String category = sc.nextLine();
                System.out.print("Enter product price: ");
                double price = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter product description: ");
                String description = sc.nextLine();
                System.out.println("Product code: " + admin.CreateProduct(name, price, category, description)); }
                catch (Exception e){
                    System.out.println("Error. Try again");
                }
                break;
            case "2":
                System.out.print("Enter product code: ");
                admin.DeleteProduct(sc.nextLine());
                break;
            case "3":
                System.out.print("Enter category name: ");
                System.out.println("Category ID: " + admin.CreateCategory(sc.nextLine()));
                break;
            case "5":
                currentUser = null;
                break;
            case "6":
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

}
