package proggroup.advprogmt;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    @FXML
    AnchorPane anchorPane;
    @FXML
    HBox hbox;
    @FXML
    ImageView imgView;
    @FXML
    Label usernameDisplay;
    @FXML
    Label typeDisplay;
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
    Librarian librarian = new Librarian();
    User user = new User();
    @FXML
    Button settingsBtn;
    @FXML
    ImageView settingsIco;
    @FXML
    Line line1;
    @FXML
    Button logoutBtn;
    Scene loginScene;
    static boolean inBooks = true;
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
        settingsBtn.setVisible(!User.type.equals("Reader"));
        settingsIco.setVisible(!User.type.equals("Reader"));
        line1.setVisible(!User.type.equals("Reader"));
        usersBtn.setVisible(!User.type.equals("Reader"));
        usersIco.setVisible(!User.type.equals("Reader"));
    }
    public void displayBooks(){
        searchField.setVisible(true);
        searchField.clear();
        searchField.setPromptText("Search For Books");
        searchBtn.setVisible(true);
        bookListview.setVisible(true);
        userListview.setVisible(false);
        inBooks = true;
        search.viewallBooks();
//        if (User.type.equals("Librarian")){
        bookListview.setItems(user.searchBooks());
//        }else {
//            bookListview.setItems(user.searchBooks());
//        }
        Rectangle clip = new Rectangle();
        clip.setWidth(957);
        clip.setHeight(548);
        clip.setArcHeight(20);
        clip.setArcWidth(20);
        bookListview.setClip(clip);
    }
    public void displayUsers(){
        searchField.setVisible(true);
        searchField.clear();
        searchField.setPromptText("Search For Users");
        searchBtn.setVisible(true);
        userListview.setVisible(true);
        bookListview.setVisible(false);
        inBooks = false;
        search.viewallUsers();
        userListview.setItems(librarian.searchUsers());
        Rectangle clip = new Rectangle();
        clip.setWidth(957);
        clip.setHeight(548);
        clip.setArcHeight(20);
        clip.setArcWidth(20);
        userListview.setClip(clip);
    }
    public void search(){
        if (inBooks){
            search.searchforBooks(searchField.getText());
            if (search.i==0) {
                bookListview.setItems(user.searchBooks());
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
            }else{
                ArrayList<HomeController.HBoxCell> list = new ArrayList<>();
                list.add(new HomeController.HBoxCell(search.result));
                ObservableList<HomeController.HBoxCell> myObservableList = FXCollections.observableList(list);
                userListview.setItems(myObservableList);
            }
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
//                if (!User.type.equals("Reader")){
                btn2 = new Button();
                btnColor(btn2,color2);
                btn2.setText(buttonText2);
                btn2.setPadding(new Insets(4, 46, 4, 46));
                btn2.setOnAction(e -> {
                    System.out.println(labelText + "Rent pressed");
//                        librarian.removeUser(labelText);
                });
                this.getChildren().add(btn2);
                HBox.setMargin(btn2, new Insets(0, 10, 0, 0));
//                }
                btnColor(btn1,color1);
                this.getChildren().add(btn1);
                btn1.setText(buttonText1);
                btn1.setPadding(new Insets(4, 30, 4, 30));
                btn1.setOnAction(e -> {
                    System.out.println(labelText + " removed");
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
    public void initialize(){
        if (usernameDisplay != null) {
            usernameDisplay.setText(User.userName);
            typeDisplay.setText(User.type);
            checkType();
        }
        searchField.setVisible(false);
        searchBtn.setVisible(false);
        bookListview.setVisible(false);
        userListview.setVisible(false);
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
