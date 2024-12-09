package Classes;

import java.util.Date;

public class Customer extends User {
    private double balance;
    private String address;
    private Gender gender;
    private Category[] interests;
    private Cart cart;

    private Customer(String username, String password, Date dateOfBirth, Gender gender){
        super(username,password,dateOfBirth);
        this.gender = gender;
        cart = new Cart();
    }

    public static Customer Register(String username, String password, Date dateOfBirth, Gender gender){
        User user = Database.getUser(username);
        if (user != null) { System.out.println("This username is unavailable"); return null; }
        return new Customer(username, password, dateOfBirth, gender);
    }
    @Override
    public boolean IsAdmin(){
        return false;
    }
    @Override
    public String toString(){
        return "customer: "+super.toString();
    }
    public String getAddress(){
        return address;
    }
    public Cart getCart() {
        return cart;
    }
    public static Gender GetGender(String gender){
        if (gender.equals("1")){
            return Gender.Man;
        }
        else if (gender.equals("2")){
            return Gender.Woman;
        }
        else return null;
    }
}
