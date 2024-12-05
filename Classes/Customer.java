package Classes;

public class Customer extends User {
    private Gender gender;
    private Category[] interests;

    public boolean IsAdmin(){
        return false;
    }
}
enum Gender{
    MAN, WOMAN
}
