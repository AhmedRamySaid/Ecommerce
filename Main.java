import Classes.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Admin("Ramy", "1610", new Date(), "Engineer", 40);
        new Admin("Kareem", "1610", new Date(), "Engineer", 40);
        new Admin("Hager", "1610", new Date(), "Engineer", 40);
        User currentUser = null;
        Scanner sc = new Scanner(System.in);

        currentUser = LoginAndRegister(sc);
        if(currentUser.IsAdmin()){
            System.out.println("Press ");
        }
        else{
            while (true) {
                System.out.println("Welcome to the Shopping Application!");
                System.out.println("1. View Products");
                System.out.println("2. Add Product to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Exit");
                System.out.print("Please select an option: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch(choice){
                    case 1:
                        currentUser.ShowAllProduct();
                        break;
                    case 2:
                        System.out.println("Enter product code: ");
                        String str = sc.nextLine();
                        ((Customer)currentUser).AddToCart(str);
                }
            }
        }
    }

    public static User LoginAndRegister(Scanner sc){
        User currentUser = null;

        while(currentUser == null) {
            System.out.println("Press 1 to login. Press 2 to register");
            String st = sc.nextLine();
            if (st.equals("1")) {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                currentUser = User.Login(username, password);
            } else if (st.equals("2")) {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                System.out.print("Select your gender (1 for man 2 for woman): ");
                Gender gender = Customer.GetGender(sc.nextLine());
                currentUser = Customer.Register(username, password, new Date(), gender);
            }
        }
        return currentUser;
    }
}
