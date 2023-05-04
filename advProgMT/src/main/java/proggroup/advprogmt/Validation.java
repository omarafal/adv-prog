package proggroup.advprogmt;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Validation {
    BufferedReader brv;
    String temp;
    boolean isBlocked = false;
    static String Type = "none";
    static String Username = "none";
    public boolean validation(TextField username, PasswordField password){

        boolean matches = false;
        try {
            brv = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
            while ((temp = brv.readLine())!= null){
                if (username.getText().toString().equals(temp.split(",")[0]) && password.getText().toString().equals(temp.split(",")[1])){
                    isBlocked = temp.split(",")[8].equals("true");
                    matches = true;
                    Type = temp.split(",")[2];
                    Username = temp.split(",")[0];
                    break;
                }
            }
            brv.close();
        } catch (IOException e) {
        }
        return matches;
    }
}

