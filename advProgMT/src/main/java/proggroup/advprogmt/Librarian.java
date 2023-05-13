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
    private BufferedWriter bw;
    private BufferedReader br;
    private static String file = "Users.txt";
    private static String path = "src/main/java/proggroup/advprogmt/Database/" + file;
    public Librarian(String Username,String Password,String Type,String FirstName,String LastName,String Address,int CellPhone,String Email, boolean isBlocked){
        super(Username,Password,Type,FirstName,LastName,Address,CellPhone,Email, isBlocked);
    }

    public void addUser(String data) throws IOException {
        bw = new BufferedWriter(new FileWriter(path, true));
        bw.write(data);
        bw.newLine();
        bw.flush();
    }
    public void editUser(String userName, String password, String type, String firstName, String lastName, String address, int cellPhone, String email, boolean isBLocked) throws IOException {
        removeUser(userName);
        addUser(new User(userName, password, type, firstName, lastName, address, cellPhone, email, isBLocked).getUserData());
    }
    public void removeUser(String nameToRemove){
        String[] users;
        int size = 0;

        try {
            br = new BufferedReader(new FileReader(path));

            String line = br.readLine();
            while(line != null){
                line = br.readLine();
                size++;
            }
            System.out.println(size);
            users = new String[size-1];
            br = new BufferedReader(new FileReader(path));

            line = br.readLine();
            for(int i = 0; line != null;){
                if(!(nameToRemove.equals(line.split(",")[0]))){
                    users[i] = line;
                    i++;
                }
                line = br.readLine();
            }
            br.close();
            bw = new BufferedWriter(new FileWriter(path));

            for(int i = 0; i < size-1; i++){
                bw.write(users[i]);
                bw.newLine();
            }
            bw.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void acceptRequest(String name, String book){

//        file = "Loans.txt";
        new Database("Loans.txt", 'w').saveP1P2(name, book);
        new Database("Requests.txt", 'm').removeUser(name);

    }

    public static void denyRequest(String name){
        new Database("Requests.txt", 'm').removeUser(name);
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

    public static void setFile(String path){
        file = path;
    }
}
