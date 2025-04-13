package kyra.me.ecommerce.Main;

import kyra.me.ecommerce.Classes.*;
import javafx.application.Application;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //dummy data
//        new Admin("Admin", "1610", "16/10/2006", "Engineer");
//        Customer.Register("Ramy", "1610", "16/10/2006", Customer.GetGender("1"));
//        new Admin("Kareem", "1610", "10/5/2004", "Engineer", 40);
//        new Admin("Hager", "1610", "21/7/2004", "Engineer", 40);
//        new Category("Electronics");
//        new Category("Pet supplies");
//        new Product("IPhone", 200, Database.getCategory("CTG100"),"This is an IPhone 15");
//        new Product("Samsung TV", 2000, Database.getCategory("CTG100"),"This is a big Samsung TV");
//        new Product("Cat food", 10, Database.getCategory("CTG101"),"The perfect meal for your cat!");
//        new Product("Dog food", 200, Database.getCategory("CTG101"),"The perfect meal for your dog!");

        Application.launch(UserInterface.class, args);
    }
}
