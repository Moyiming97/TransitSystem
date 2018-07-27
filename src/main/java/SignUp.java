
public class SignUp {

    public static boolean authenticate(String email) {
        // hardcoded username and password
        if (email.equals("secret")) {
            return true;
        }
        return false;
    }
}
