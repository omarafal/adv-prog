package proggroup.advprogmt;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    HBox hbox;
    @FXML
    ImageView imgView;
    @FXML
    Pane booksPane;
    @FXML
    Pane usersPane;
    @FXML
    Label usernameDisplay;
    @FXML
    Label typeDisplay;
    @FXML
    TextField searchField1;
    @FXML
    TextField searchField2;
    @FXML
    JFXButton searchBtn1;
    @FXML
    JFXButton usersBtn;
    @FXML
    ImageView usersIco;
    Search search = new Search();
    @FXML
    ListView<HomeController.HBoxCell> bookListview;
    Librarian librarian = new Librarian();
    Reader reader = new Reader();
    @FXML
    Button settingsBtn;
    @FXML
    ImageView settingsIco;
    @FXML
    Line line1;
    @FXML
    Button logoutBtn;
    Scene loginScene;

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
            searchBtn1.fire();
    }
    public void checkType(){
        settingsBtn.setVisible(!User.type.equals("Reader"));
        settingsIco.setVisible(!User.type.equals("Reader"));
        line1.setVisible(!User.type.equals("Reader"));
        usersBtn.setVisible(!User.type.equals("Reader"));
        usersIco.setVisible(!User.type.equals("Reader"));

//        if (!User.type.equals("Reader"))
//            type.getItems().add("Users");
    }
    public void displayBooks(){
        booksPane.setVisible(true);
        usersPane.setVisible(false);
    }
    public void displayUsers(){
        usersPane.setVisible(true);
        booksPane.setVisible(false);
    }
    public void search(){
        search.searchforBooks(searchField1.getText());
        System.out.println("search button pressed");
//        bp.setCenter(null);
        if (search.i==0) {
            if (User.type.equals("Librarian")){
                bookListview.setItems(librarian.searchBooks());
                System.out.println("User is Librarian");
//                    bp.setCenter(librarian.searchUsers());

//                    else bp.setCenter(librarian.searchBooks());
            }else {
                bookListview.setItems(reader.searchBooks());
                System.out.println("User is Reader");
            }

        }
//        else {
//            bp.setCenter(null);
//            System.out.println(search.result +"from home");
//            text.setText(search.result);
//            bp.setCenter(body);
//        }
    }
    public static class HBoxCell extends HBox {
        Label text = new Label();
        Button btn1 = new Button();
        Button btn2;
        Librarian librarian = new Librarian();
        Reader reader = new Reader();
        HBoxCell(String labelText, String buttonText1, String color1, String buttonText2, String color2, String type) {
            super();
            text.setStyle("-fx-font-size:20;-fx-font-weight:bold;");
            text.setText(labelText);
            text.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(text, Priority.ALWAYS);

            this.getChildren().add(text);
            if (type.equals("Librarian")){
                if (!User.type.equals("Reader")){
                    btn2 = new Button();
                    btn2.setText(buttonText2);
                    btn2.setStyle("-fx-background-radius:7;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-background-color:" + color2 + ";-fx-cursor:hand;");
                    btn2.setPadding(new Insets(4, 38, 4, 38));
                    btn2.setOnAction(e -> {
                        System.out.println(labelText + " pressed");
                        librarian.removeUser(labelText);
                    });
                    this.getChildren().add(btn2);
                    HBox.setMargin(btn2, new Insets(0, 10, 0, 0));
                }
                this.getChildren().add(btn1);
                btn1.setText(buttonText1);
                btn1.setStyle("-fx-background-radius:7;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-background-color:" + color1 + ";-fx-cursor:hand;-fx-font-size:20");
                btn1.setPadding(new Insets(4, 30, 4, 30));
                btn1.setOnAction(e -> {
                    System.out.println(labelText + " removed");
                });
            }else {
                this.getChildren().add(btn1);
                btn1.setText(buttonText1);
                btn1.setStyle("-fx-background-radius:7;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-background-color:"+ color1 +";-fx-cursor:hand;-fx-font-size:20");
                btn1.setPadding(new Insets(4,38,4,38));
                btn1.setOnAction(e->{
                    System.out.println(labelText + " rent pressed");
                });
            }
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
        booksPane.setVisible(false);
        usersPane.setVisible(false);
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
            Image im = new Image("file:src/main/resources/proggroup/advprogmt/loginBG.jpg",false);
            imgView.setImage(im);
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
