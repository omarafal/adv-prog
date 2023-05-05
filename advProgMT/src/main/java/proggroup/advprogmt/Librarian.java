package proggroup.advprogmt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class Librarian extends User{
    public Librarian(String Username,String Password,String Type,String FirstName,String LastName,String Address,int CellPhone,String Email){
        super(Username,Password,Type,FirstName,LastName,Address,CellPhone,Email);
    }

    public Librarian() {

    }

    public void addUser(TextField username, TextField password, ChoiceBox type, TextField firstname, TextField lastname,TextField address , TextField cellphone, TextField email){
        BufferedWriter bw;
        {
            try {
                bw = new BufferedWriter(new FileWriter("src/main/resources/proggroup/advprogmt/Users.txt",true));
                bw.write(username + "," + password + "," + type + "," + firstname + "," + lastname + "," + address+ "," + cellphone + "," + email + "," + false);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void removeUser(String nameToRemove){
        BufferedReader br;
        BufferedWriter bw;
        try {
            br = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Users.txt"));
            bw = new BufferedWriter(new FileWriter("src/main/resources/proggroup/advprogmt/Users.txt",true));
        }catch (IOException e){

        }
    }
    public ListView<Home.HBoxCell> searchUsers(){
        ArrayList<Home.HBoxCell> list = new ArrayList<>();
        if (Search.usersArr != null) {
            for (String i: Search.usersArr) {
                if (i!= null) {
                    System.out.println("user "+ i + " added");
                    list.add(new Home.HBoxCell(i, "Remove","crimson","","transparent","Librarian"));
                }
            }
        }

        ListView<Home.HBoxCell> listView = new ListView<>();
        ObservableList<Home.HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        listView.setStyle("-fx-border-color:black;-fx-selection-bar:grey");
        return listView;
    }
    public ListView<Home.HBoxCell> searchBooks(){
        ArrayList<Home.HBoxCell> list = new ArrayList<>();
        if (Search.booksArr != null){
            for (String i: Search.booksArr) {
                if (i!= null) {
                    System.out.println("element "+ i + " added");
                    list.add(new Home.HBoxCell(i, "Remove","crimson","Rent","limegreen","Librarian"));
                }
            }
        }

        ListView<Home.HBoxCell> listView = new ListView<>();
        ObservableList<Home.HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
//          -fx-control-inner-background-alt: grey;-fx-control-inner-background: red;-fx-selection-bar:green;
        listView.setStyle("-fx-border-color:black;-fx-selection-bar:grey");
        return listView;
    }
}
