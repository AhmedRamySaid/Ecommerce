import Classes.*;

import java.util.Scanner;

public class Main {
    public static User currentUser = null;
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //dummy data
        Customer.Register("Ramy", "1610", "16/10/2006", Customer.GetGender("1"));
        new Admin("Kareem", "1610", "10/5/2004", "Engineer", 40);
        new Admin("Hager", "1610", "21/7/2004", "Engineer", 40);
        new Category("Electronics");
        new Category("Pet supplies");
        new Product("IPhone", 200, Database.getCategory("CTG100"),"This is an IPhone 15");
        new Product("Samsung TV", 2000, Database.getCategory("CTG100"),"This is a big Samsung TV");
        new Product("Cat food", 10, Database.getCategory("CTG101"),"The perfect meal for your cat!");

        boolean UsingApplication = true;

        while (UsingApplication) {
            currentUser = LoginAndRegister();
            if (currentUser == null) { UsingApplication = false; }
            while (currentUser != null) {
                if(currentUser.isAdmin()){
                    UsingApplication = AdminInteraction();
                }
                else if (currentUser != null){
                    UsingApplication = UserInteraction();
                }
            }
        }
    }

    public static User LoginAndRegister(){
        User currentUser = null;

        while(currentUser == null) {
            System.out.println("\nPress 1 to login. Press 2 to register. Press 3 to exit");
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
                    System.out.print("Date of birth: ");
                    String dob = sc.nextLine();
                    currentUser = Customer.Register(username2, password2, dob, Customer.GetGender(gender));
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return null;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
        System.out.println("Welcome, " + currentUser.getUsername());
        return currentUser;
    }

    public static boolean UserInteraction(){
        Customer customer = (Customer)currentUser;
        System.out.println("\n1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. Remove from Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Make an order");
        System.out.println("6. View orders");
        System.out.println("7. Cancel an order");
        System.out.println("8. logout");
        System.out.println("9. Exit");
        System.out.print("Please select an option: ");

        String choice = sc.nextLine();

        switch(choice) {
            case "1":
                currentUser.ShowAllProducts();
                break;
            case "2":
                System.out.print("Please enter product code: ");
                String str = sc.nextLine();
                customer.AddToCart(str);
                break;
            case "3":
                System.out.print("Please enter the number of the item you want to remove: ");
                try {
                    int num = sc.nextInt();
                    sc.nextLine();
                    customer.getCart().removeProduct(num);
                } catch (Exception e) {
                    System.out.println("Invalid number");
                }
                break;
            case "4":
                customer.ViewCart();
                break;
            case "5":
                String address = null;
                if (customer.getAddress() != null) {
                    System.out.print("Press 1 if you'd like to use the address associated with your account: ");
                    if(sc.nextLine().equals("1")){
                        address = customer.getAddress();
                    }
                }
                if (address == null) {
                    System.out.println("Please enter address for delivery");
                    address = sc.nextLine();
                }
                System.out.print("Press 1 to pay with a credit card. Press 2 to pay with a debit card. " +
                        "Press 3 to pay using a cash app. Press 4 to pay on arrival");
                try{
                    customer.MakeAnOrder(sc.nextInt(),address);
                }
                catch(Exception e){
                    System.out.println("Invalid input");
                }
                break;
            case "6":
                customer.ViewOrders();
                break;
            case "7":
                System.out.print("Please select which order you want to cancel: ");
                customer.ViewOrders();
                try{
                    int i = sc.nextInt();
                    customer.RemoveOrder(i,2);
                    sc.nextLine();
                }
                catch(Exception e){
                    System.out.println("Invalid number. Please enter the number of your order");
                }
                break;
            case "8":
                currentUser = null;
                break;
            case "9":
                System.out.println("Goodbye!");
                currentUser = null;
                return false;
            default:
                System.out.println("Invalid option! Try again.");
                break;
        }
        return true;
    }

    private static boolean AdminInteraction() {
        Admin admin = (Admin)currentUser;

        System.out.println("\n1. Create a product");
        System.out.println("2. Edit a product");
        System.out.println("3. Delete a product");
        System.out.println("4. Create a category");
        System.out.println("5. Delete a category");
        System.out.println("6. Show all");
        System.out.println("7. logout");
        System.out.println("8. Exit");
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
                admin.CreateProduct(name, price, category, description); }
                catch (Exception e){
                    System.out.println("Error. Try again");
                }
                break;
            case "2":
                System.out.print("Enter product code: ");
                String code = sc.nextLine();
                System.out.println("Press 1 to edit price. Press 2 to edit description. Press 3 to change the category");
                int i;
                try{
                    i = sc.nextInt();
                }
                catch(Exception e){
                    System.out.println("Invalid input");
                    break;
                }
                System.out.println("Enter the new price/new description/new category ID");
                String line = sc.nextLine();
                admin.EditProduct(code,line,i);
                break;
            case "3":
                System.out.print("Enter product code: ");
                admin.DeleteProduct(sc.nextLine());
                break;
            case "4":
                System.out.print("Enter category name: ");
                admin.CreateCategory(sc.nextLine());
                break;
            case "5":
                System.out.print("Enter category code: ");
                admin.DeleteCategory(sc.nextLine());
            case "6":
                System.out.println("1. Show all users");
                System.out.println("2. Show all products");
                System.out.println("3. Show all orders");
                String st = sc.nextLine();
                switch (st){
                    case "1":
                        admin.ShowAllUsers();
                        break;
                    case "2":
                        admin.ShowAllProducts();
                        break;
                    case "3":
                        admin.ShowAllOrders();
                        break;
                }
                break;
            case "7":
                currentUser = null;
                break;
            case "8":
                System.out.println("Goodbye!");
                currentUser = null;
                return false;
            default:
                System.out.println("Invalid option");
                break;
        }
        return true;
    }
}
