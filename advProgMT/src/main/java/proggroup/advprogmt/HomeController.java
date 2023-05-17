package proggroup.advprogmt;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    Button tempBtnIgnore = new Button();
    Label tempLabelIgnore = new Label();
    Button tempBtnIgnoreRent = new Button();
    Button tempBtnIgnoreRemove = new Button();
    String tempStrIgnore;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button focusBtn;
    @FXML
    HBox hbox;
    @FXML
    ImageView imgView;
    @FXML
    Label usernameDisplay;
    @FXML
    Label typeDisplay;
    @FXML
    Pane homePane;
    @FXML
    Pane mainPane;
    @FXML
    Pane settingsPane;
    @FXML
    GridPane gridPane;
    @FXML
    HBox settingsBtns;
    @FXML
    Pane adduserPane;
    @FXML
    Pane addbookPane;
    @FXML
    Pane editPane;
    @FXML
    TextField searchField;
    @FXML
    JFXButton searchBtn;
    @FXML
    JFXButton usersBtn;
    @FXML
    ImageView usersIco;
    Search search = new Search();
    @FXML
    ListView<HomeController.HBoxCell> bookListview;
    @FXML
    ListView<HomeController.HBoxCell> userListview;
    User user = new User();
    @FXML
    Button settingsBtn;
    @FXML
    ImageView settingsIco;
    @FXML
    JFXButton addUserBtn;
    @FXML
    TextField userNameField;
    @FXML
    PasswordField passField;
    @FXML
    JFXComboBox type;
    String Type;
    @FXML
    TextField firstnameField;
    @FXML
    Label usernameErr;
    @FXML
    Label passErr;
    @FXML
    Label typeErr;
    @FXML
    Label firstnameErr;
    @FXML
    Label lastnameErr;
    @FXML
    Label emailErr;
    @FXML
    Label cellphoneErr;
    @FXML
    Label addressErr;
    @FXML
    TextField lastnameField;
    @FXML
    TextField emailField;
    @FXML
    TextField cellphoneField;
    @FXML
    TextField addressField;
    @FXML
    JFXCheckBox blockedCB;
    @FXML
    TextField booktitleField;
    @FXML
    Label bookfieldErr;
    @FXML
    JFXButton addBtn1;
    @FXML
    Label userAdded;
    @FXML
    Label bookAdded;
    @FXML
    Button saveBtn;
    @FXML
    Button backBtn;
    @FXML
    Label userEdited;
    @FXML
    Line line1;
    @FXML
    Button logoutBtn;
    Scene loginScene;
    Librarian librarian = new Librarian();
    Book book = new Book();
    static boolean inBooks = true;
    static boolean userExist = false;
    static boolean bookExist = false;
    @FXML
    public void closeWindow(){
        System.exit(0);
    }
    @FXML
    public void minimizeWindow(){
        ((Stage) anchorPane.getScene().getWindow()).setIconified(true);
    }
    private double xOffset = 0;
    private double yOffset = 0;
    public void enter(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER))
            searchBtn.fire();
    }
    public void checkType(){
        settingsBtn.setVisible(!User.getType().equals("Reader"));
        settingsIco.setVisible(!User.getType().equals("Reader"));
        line1.setVisible(!User.getType().equals("Reader"));
        usersBtn.setVisible(!User.getType().equals("Reader"));
        usersIco.setVisible(!User.getType().equals("Reader"));
    }
    public void displayBooks(){
        searchField.setVisible(true);
        setvisible("main");
        clearFields();
        searchField.setPromptText("Search For Books");
        searchBtn.setVisible(true);
        bookListview.setVisible(true);
        userListview.setVisible(false);
        inBooks = true;
        search.viewallBooks();
        bookListview.setItems(user.searchBooks());
        hboxCell(bookListview,"Rent");
        bookListview.setClip(roundedListview());
    }

    public void displayUsers(){
        setvisible("main");
        clearFields();
        searchField.setPromptText("Search For Users");
        userListview.setVisible(true);
        inBooks = false;
        search.viewallUsers();
        userListview.setItems(librarian.searchUsers());
        hboxCell(userListview,"Edit");
        userListview.setClip(roundedListview());
    }
    public void back(){
        settingsPane.setVisible(false);
        displayUsers();
    }
    public Rectangle roundedListview(){
        Rectangle clip = new Rectangle();
        clip.setWidth(957);
        clip.setHeight(548);
        clip.setArcHeight(20);
        clip.setArcWidth(20);
        return clip;
    }
    public void search(){
        if (inBooks){
            search.searchforBooks(searchField.getText());
            if (search.i==0) {
                bookListview.setItems(user.searchBooks());
                hboxCell(bookListview,"Rent");
            }else{
                ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
                list.add(new HomeController.HBoxCell(search.result));
                ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
                bookListview.setItems(myObservableList);
            }
        }else {
            search.searchforUsers(searchField.getText());
            if (search.i==0) {
                userListview.setItems(librarian.searchUsers());
                hboxCell(userListview,"Edit");
            }else{
                ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
                list.add(new HomeController.HBoxCell(search.result));
                ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
                userListview.setItems(myObservableList);
            }
        }
    }
    public void settings(){
        setvisible("settings");
    }
    public void newUser(){
        clearFields();
        setvisible("settings");
        adduserPane.setVisible(true);
        gridPane.setVisible(true);
        userAdded.setVisible(true);
        addBtn1.setVisible(true);
    }
    public void saveUser(){
        userAdded.setText("");
        if (User.validate(userNameField,usernameErr,passField,passErr,type.getValue(),typeErr,firstnameField,firstnameErr,lastnameField,lastnameErr,emailField,emailErr,cellphoneField,cellphoneErr,addressField,addressErr)){
            try {
                type.getValue().toString();
                Type = type.getValue().toString();
            } catch (NullPointerException | NumberFormatException e) {
            }
            try {
                search.viewallUsers();
                for (String i : Search.usersArr) {
                    if (userNameField.getText().equals(i)) {
                        userExist = true;
                        break;
                    } else userExist = false;
                }
                if (!userExist) {
                    librarian.addUser(new User(userNameField.getText(), passField.getText(), Type, firstnameField.getText(), lastnameField.getText(), addressField.getText(), cellphoneField.getText(), emailField.getText(), false).getUserData());
                    clearFields();
                    userAdded.setText("User added");
                    userAdded.setStyle("-fx-text-fill:limegreen");
                    userExist = false;
                } else {
                    userAdded.setText("Username already exists");
                    userAdded.setStyle("-fx-text-fill:red");
                }
            } catch (IOException | NumberFormatException e) {
            }
        }
    }
    public void newBook(){
        clearFields();
        setvisible("settings");
        addbookPane.setVisible(true);
    }
    public void saveBook(){
        bookAdded.setText("");
        if (!User.checkEmpty(booktitleField,bookfieldErr)){
            search.viewallBooks();
            for (String i : Search.booksArr) {
                if (booktitleField.getText().equals(i)) {
                    bookExist = true;
                    break;
                } else bookExist = false;
            }if(bookExist){
                bookAdded.setText("Book already exists");
                bookAdded.setStyle("-fx-text-fill:red");
            }else {
                try {
                    book.addBook(new Book(booktitleField.getText()).getTitle());
                    clearFields();
                    bookAdded.setText("Book Added");
                    bookAdded.setStyle("-fx-text-fill:limegreen");
                } catch (NullPointerException e) {
                }
            }
        }
    }
    public void orderList(){
        clearFields();
        setvisible("settings");
    }
    private void clearFields() {
        searchField.clear();
        userNameField.clear();
        type.getSelectionModel().clearSelection();
        passField.clear();
        firstnameField.clear();
        lastnameField.clear();
        emailField.clear();
        cellphoneField.clear();
        addressField.clear();
        booktitleField.clear();
        blockedCB.setSelected(false);
        typeErr.setText("");
        usernameErr.setText("");
        defaultStyle(userNameField);
        passErr.setText("");
        defaultStyle(passField);
        firstnameErr.setText("");
        defaultStyle(firstnameField);
        lastnameErr.setText("");
        defaultStyle(lastnameField);
        emailErr.setText("");
        defaultStyle(emailField);
        cellphoneErr.setText("");
        defaultStyle(cellphoneField);
        addressErr.setText("");
        defaultStyle(addressField);
        userAdded.setText("");
        bookfieldErr.setText("");
        defaultStyle(booktitleField);
        bookAdded.setText("");
    }
    private void defaultStyle(TextField field){
        field.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;");
    }
    public void hboxCell(ListView<HomeController.HBoxCell> listView,String btnlbl){
        for(Node entity: listView.getItems()){
            for(Node nested: ((HBoxCell)entity).getChildren()){
                if(nested.getClass() == tempLabelIgnore.getClass()){
                    tempStrIgnore = ((Label) nested).getText();
                }
                else if(nested.getClass() == tempBtnIgnore.getClass()){
                    if(((Button) nested).getText().equals(btnlbl)){
                        tempBtnIgnoreRent = ((Button) nested);
                        setEvent(tempStrIgnore, tempBtnIgnoreRent, tempBtnIgnoreRemove);
                    }
                }
            }
        }
        for(Node entity: listView.getItems() ){
            for(Node nested: ((HBoxCell)entity).getChildren()){
                if(nested.getClass() == tempLabelIgnore.getClass()){
                    tempStrIgnore = ((Label) nested).getText();
                } else if(nested.getClass() == tempBtnIgnore.getClass()) {
                    if (((Button) nested).getText().equals("Remove")) {
                        tempBtnIgnoreRemove = ((Button) nested);
                        setEvent(tempStrIgnore, tempBtnIgnoreRent, tempBtnIgnoreRemove);
                    }
                }
            }
        }
    }
    public void setEvent(String lbl, Button btn1, Button btn2){
        if (btn1.getText().equals("Edit")){
            btn1.setOnAction(e -> {
                System.out.println(lbl+" EDIT BUTTON PRESSED");
                setvisible("none");
                clearFields();////////////
                setvisible("edit");
                focusBtn.requestFocus();
                userNameField.setText(lbl);
            });
            btn2.setOnAction(e ->{
                librarian.removeUser(lbl);
                System.out.println(lbl + " deleted");
                if (!searchField.getText().equals("") ) {
                    searchBtn.fire();
                }else{
                    bookListview.setItems(null);
                    displayUsers();
                }
            });
        }else if(btn1.getText().equals("Rent")){
            btn1.setOnAction(e->{
                System.out.println(lbl +" RENT PRESSED");
                Book.rentBook(lbl);
            });
            btn2.setOnAction(e -> {
                Book.removeBook(lbl);
                System.out.println(lbl+" book removed!!!");
                if (!searchField.getText().equals("") ) {
                    searchBtn.fire();
                }else{
                    bookListview.setItems(null);
                    displayBooks();
                }
            });
        }
    }
    public static class HBoxCell extends HBox {
        Label text = new Label();
        Button btn1 = new Button();
        Button btn2;
        HBoxCell(String labelText, String buttonText1, String color1, String buttonText2, String color2, String type) {
            super();
            text.setStyle("-fx-font-size:21;-fx-font-weight:bold;");
            text.setText(labelText);
            text.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(text, Priority.ALWAYS);
            getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
            btn1.getStyleClass().add("Button");
            this.getChildren().add(text);
            if (type.equals("Librarian")){
                btn2 = new Button();
                btnColor(btn2,color2);
                btn2.setText(buttonText2);
                btn2.setPadding(new Insets(4, 46, 4, 46));
                btn2.setOnAction(e -> {
//                    System.out.println(labelText + "Rent pressed");
                });
                this.getChildren().add(btn2);
                HBox.setMargin(btn2, new Insets(0, 10, 0, 0));
                btnColor(btn1,color1);
                this.getChildren().add(btn1);
                btn1.setText(buttonText1);
                btn1.setPadding(new Insets(4, 30, 4, 30));
                btn1.setOnAction(e -> {
//                    librarian.removeUser(labelText);
//                    home.displayUsers();
                });
            }else {
                this.getChildren().add(btn1);
                btnColor(btn1,color1);
                btn1.setText(buttonText1);
                btn1.setPadding(new Insets(4,46,4,46));
                btn1.setOnAction(e->{
                    System.out.println(labelText + " rent pressed");
                });
            }
        }
        public void btnColor(Button btn,String color){
            if (color.equals("crimson")) {
                btn.getStyleClass().add("ButtonR");
            } else if (color.equals("limegreen")) {
                btn.getStyleClass().add("ButtonG");
            } else if (color.equals("#FFC107")) {
                btn.getStyleClass().add("ButtonY");
            }
        }
        HBoxCell(String labelText){
            super();
            text.setText(labelText);
            text.setStyle("-fx-font-size:21;-fx-font-weight:bold;-fx-alignment:center");
            text.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(text, Priority.ALWAYS);
            this.getChildren().add(text);
        }
    }
    public void logut(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            loginScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
//            FadeTransition transition = new FadeTransition(Duration.seconds(0.1), root);
//            transition.setFromValue(0);
//            transition.setToValue(1);
//            transition.play();
            Stage window = (Stage)(logoutBtn.getScene().getWindow());
            window.setTitle("Library System - Login");
            window.setScene(loginScene);
            window.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setvisible(String window){
        if (window.equals("settings")) {
            settingsPane.setVisible(true);
            settingsBtns.setVisible(true);
            adduserPane.setVisible(false);
            gridPane.setVisible(false);
            addbookPane.setVisible(false);
            userAdded.setVisible(false);
            addBtn1.setVisible(false);
            saveBtn.setVisible(false);
            blockedCB.setVisible(false);
            backBtn.setVisible(false);
            userEdited.setVisible(false);
            mainPane.setVisible(false);
            bookListview.setVisible(false);
            userListview.setVisible(false);
        } else if (window.equals("main")) {
            mainPane.setVisible(true);
            settingsPane.setVisible(false);
            settingsBtns.setVisible(false);
            adduserPane.setVisible(false);
            addbookPane.setVisible(false);
            gridPane.setVisible(false);
            saveBtn.setVisible(false);
            backBtn.setVisible(false);
            userEdited.setVisible(false);
            bookListview.setVisible(false);
            userListview.setVisible(false);
        }else if (window.equals("edit")){
            mainPane.setVisible(false);
            settingsPane.setVisible(true);
            settingsBtns.setVisible(false);
            adduserPane.setVisible(true);
            addbookPane.setVisible(false);
            gridPane.setVisible(true);
            userAdded.setVisible(false);
            addBtn1.setVisible(false);
            saveBtn.setVisible(true);
            blockedCB.setVisible(true);
            backBtn.setVisible(true);
            userEdited.setVisible(true);
            bookListview.setVisible(false);
            userListview.setVisible(false);
        } else if (window.equals("none")){
            mainPane.setVisible(false);
            settingsPane.setVisible(false);
            settingsBtns.setVisible(false);
            adduserPane.setVisible(false);
            gridPane.setVisible(false);
            addbookPane.setVisible(false);
            saveBtn.setVisible(false);
            backBtn.setVisible(false);
            userEdited.setVisible(false);
            bookListview.setVisible(false);
            userListview.setVisible(false);
        }
    }
    public void initialize(){
        if (usernameDisplay != null) {
            usernameDisplay.setText(User.getUserName());
            typeDisplay.setText(User.getType());
            checkType();
        }
        mainPane.setVisible(false);
        settingsPane.setVisible(false);
        settingsBtns.setVisible(false);
        adduserPane.setVisible(false);
        bookListview.setVisible(false);
        userListview.setVisible(false);
        type.getItems().addAll("Librarian","Reader");
        anchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        hbox.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        if (imgView != null){
            imgView.setImage(new Image("file:src/main/resources/proggroup/advprogmt/loginBG.jpg",false));
            Rectangle clip = new Rectangle();
            clip.setWidth(1280.0);
            clip.setHeight(720);
            clip.setArcHeight(40);
            clip.setArcWidth(40);
            clip.setStroke(Color.BLACK);
            imgView.setClip(clip);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = imgView.snapshot(parameters, null);
            imgView.setClip(null);
            imgView.setImage(image);
        }
    }
}
