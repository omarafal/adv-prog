package proggroup.advprogmt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
//    (Username(ID),Password,Type,FirstName,LastName,Address,CellPhone,Email,isBlocked).
    private static String userName;
    private String password;
    private static String type;
    private String firstName;
    private String lastName;
    private String address;
    private int cellPhone;
    private String email;
    static BufferedReader brv;
    static String temp;
    static boolean isBlocked = false;
    Search search = new Search();
    public User(){

    };
    public User(String Username,String Password,String type,String FirstName,String LastName,String Address,int CellPhone,String Email, boolean isBLocked){
        this.userName=Username;
        this.password=Password;
        this.type=type;
        this.firstName=FirstName;
        this.lastName=LastName;
        this.address=Address;
        this.cellPhone=CellPhone;
        this.email=Email;
        this.isBlocked = isBLocked;
    }

    public static boolean validate(TextField username, PasswordField password,TextField passShown , Label msg){
        if (!checkFields(username,password,passShown,msg)){
            return false;
        }
        boolean matches = false;
        try {
            brv = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
            temp = brv.readLine();
            while ((temp = brv.readLine())!= null){
                if (username.getText().equals(temp.split(",")[0])){
                    if (password.getText().equals(temp.split(",")[1]) || passShown.getText().equals(temp.split(",")[1])) {
                        isBlocked = temp.split(",")[8].equals("true");
                        matches = true;
                        type = temp.split(",")[2];
                        userName = temp.split(",")[0];
                        msg.setText("");
                        break;
                    }else {
                        msg.setText("Wrong Credentials");
                        msg.setStyle("-fx-text-fill:red");
                    }
                    username.clear();
                    passShown.clear();
                    password.clear();
                } else {
                    msg.setText("Wrong Credentials");
                    msg.setStyle("-fx-text-fill:red");
                }
            }
            brv.close();
        } catch (IOException e) {
        }
        return matches;
    }

    public static boolean checkFields(TextField username, PasswordField password,TextField passShown , Label msg) {
        if (password.getText().isEmpty())
            password.setText(passShown.getText());
        if (username.getText().isEmpty() && password.getText().isEmpty()) {
            msg.setText("Please enter your Username & Password");
            msg.setStyle("-fx-text-fill:red");
            return false;
        } else if (username.getText().isEmpty()) {
            msg.setText("Please enter your Username");
            msg.setStyle("-fx-text-fill:red");
            return false;
        } else if (password.getText().isEmpty() && passShown.getText().isEmpty()) {
            msg.setText("Please enter your Password");
            msg.setStyle("-fx-text-fill:red");
            return false;
        }
        return true;
    }
    public ObservableList<HomeController.HBoxCell> searchBooks(){
        ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
        if (Search.booksArr != null) {
            for (String i: Search.booksArr) {
                if (i!= null) {
//                    System.out.println("element "+ i + " added");
                    if (type.equals("Librarian")) {
                        list.add(new HomeController.HBoxCell(i, "Remove","crimson","Rent","limegreen",type));
                    }else {
                        list.add(new HomeController.HBoxCell(i, "Rent","limegreen","","transparent",type));
                    }
                }
            }
        }
//        for(Node entity: list.get(0).getChildren()){
//            ((Button)entity).setOnAction(e -> {
//                System.out.println("Button detected");
//            });
//        }
        ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
        return myObservableList;
    }
    public String getUserData() {
        return userName + "," + password + "," + type + "," + firstName + "," + lastName + "," + address + "," + cellPhone + "," + email + "," + isBlocked;
    }

    public static String getUserName(){
        return userName;
    }

    public static String getType(){
        return type;
    }
}
