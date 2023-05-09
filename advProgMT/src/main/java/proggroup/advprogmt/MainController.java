package proggroup.advprogmt;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.effect.DropShadow;
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
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    Button focus;
    @FXML
    JFXTextField usernameField;
    @FXML
    JFXPasswordField passwordField;
    @FXML
    JFXTextField passwordShown;
    @FXML
    Button showPassBtn;
    @FXML
    Label errmsgLable;
    @FXML
    JFXButton loginBtn;
    @FXML
    ImageView hideIco;
    @FXML
    ImageView showIco;
    @FXML
    ImageView imgView;
    @FXML
    Label usernameDisplay;
    @FXML
    Label typeDisplay;
    boolean visible = false;
    @FXML
    AnchorPane anchorPane;
    @FXML
    HBox hbox;
    @FXML
    Button logoutBtn;
    Scene loginScene;
    @FXML
    Button settingsBtn;
    @FXML
    ImageView settingsIco;
    @FXML
    Line line1;
    @FXML
    public void closeWindow(){
        System.exit(0);
    }
    @FXML
    public void minimizeWindow(){
        ((Stage) anchorPane.getScene().getWindow()).setIconified(true);
    }
    @FXML
    public void enter1(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER))
            passwordField.requestFocus();
    }
    @FXML
    public void enter2(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER)) {
            loginBtn.fire();
            loginBtn.requestFocus();
        }
    }
    private double xOffset = 0;
    private double yOffset = 0;


    @FXML
    protected void logIn()  {
        if(User.validate(usernameField, passwordField, passwordShown, errmsgLable)) {
            if(User.type.equals("Reader")){
                if (!Reader.validate(errmsgLable)){
//                    logOut();
                }else homeScreen();
            } else if (User.type.equals("Librarian")) {
                homeScreen();
//                HomeWindow.ctrlStage();
            }
        }
    }
    public void homeScreen(){
        try {
            new Alert().display("Welcome "+ User.userName,"Login Successful!","green");
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene homeScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
            Stage window = (Stage)(loginBtn.getScene().getWindow());
            window.setTitle("Library System - Home - " + User.type + ": " + User.userName);
            window.setScene(homeScene);
            window.show();
//            FadeTransition transition = new FadeTransition(Duration.seconds(0.1), root);
//            transition.setFromValue(0);
//            transition.setToValue(1);
//            transition.play();
//            TranslateTransition inTransition = new TranslateTransition(Duration.seconds(0.2), homeScene.getRoot());
//            inTransition.setFromX(-homeScene.getWidth());
//            inTransition.setToX(0);
//            inTransition.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        usernameDisplay.setText(User.userName);
//        typeDisplay.setText(User.type);
    }
    public void checkType(){
        settingsBtn.setVisible(!User.type.equals("Reader"));
        settingsIco.setVisible(!User.type.equals("Reader"));
        line1.setVisible(!User.type.equals("Reader"));
//        if (!User.type.equals("Reader"))
//            type.getItems().add("Users");
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
    public void showPass(){
        if(!visible){
            hideIco.setVisible(false);
            showIco.setVisible(true);
            passwordShown.setText(passwordField.getText());
            passwordShown.setVisible(true);
            passwordField.setVisible(false);
            visible=true;
        }else {
            hideIco.setVisible(true);
            showIco.setVisible(false);
            passwordField.setText(passwordShown.getText());
            passwordField.setVisible(true);
            passwordShown.setVisible(false);
            visible=false;
        }
    }
    public void initialize(){
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
        if (usernameDisplay != null) {
            usernameDisplay.setText(User.userName);
            typeDisplay.setText(User.type);
            checkType();
        }
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