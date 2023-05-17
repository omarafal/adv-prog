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
    public Librarian(String Username,String Password,String Type,String FirstName,String LastName,String Address,String CellPhone,String Email, boolean isBlocked){
        super(Username,Password,Type,FirstName,LastName,Address,CellPhone,Email, isBlocked);
    }
    public Librarian() {
    }
    public void addUser(String data) throws IOException {
        setFile("Users.txt");
        bw = new BufferedWriter(new FileWriter(path, true));
        bw.write(data);
        bw.newLine();
        bw.flush();
    }
    public void editUser(String userName, String password, String type, String firstName, String lastName, String address, String cellPhone, String email, boolean isBLocked) throws IOException {
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
//    public void addBook(String title) throws IOException {
//        setFile("Books.txt");
//        bw = new BufferedWriter(new FileWriter(path, true));
//        bw.write(title);
//        bw.newLine();
//        bw.flush();
//    }
    public static void acceptRequest(String name, String book){
//        file = "Loans.txt";
        new Database("Loans.txt", 'w').saveP1P2(name, book);
        new Database("Requests.txt", 'm').removeUser(name);

    }

    public static void denyRequest(String name){
        new Database("Requests.txt", 'm').removeUser(name);
    }

    public ObservableList<HomeController.HBoxCell> searchUsers(){
        ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
        if (Search.usersArr != null) {
            for (String i: Search.usersArr) {
                if (i!= null) {
                    System.out.println("user "+ i + " added");
                    list.add(new HomeController.HBoxCell(i, "Remove","crimson","Edit","#FFC107",User.getType()));
                }
            }
        }
        ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
        return myObservableList;
    }

    private static void setFile(String path){
        file = path;
    }
}
