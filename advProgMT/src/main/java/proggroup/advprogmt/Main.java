package proggroup.advprogmt;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    Stage MainStage;
//    Login LoginWindow = new Login();
    Scene loginScene;
    Home HomeWindow = new Home();

    @Override
    public void start(Stage mainStage){
        MainStage=mainStage;
        mainSetup();
        mainStage.show();
    }
    public void mainSetup(){
        MainStage.setTitle("Library System - Login");

        MainStage.setResizable(false);
        MainStage.getIcons().add(new Image(Login.class.getResourceAsStream("online-library.png")));

        loginWindow();
        MainStage.setScene(loginScene);
        HomeWindow.logOut.setOnAction(e -> logOut());
//        LoginWindow.showPass.setGraphic(LoginWindow.hideIco);
        MainStage.initStyle(StageStyle.TRANSPARENT);
    }

    public void loginWindow(){
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
//        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("Home.fxml"));
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            loginScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
            loginScene.getStylesheets().add(this.getClass().getResource("styling.css").toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    protected void logIn()  {
//        System.out.println("test");
//        if(User.validate(LoginWindow.textField1, LoginWindow.passField, LoginWindow.passShown, LoginWindow.label3)) {
//            if(User.type.equals("Reader")){
//                if (!Reader.validate(LoginWindow.label3)){
//                    logOut();
//                }else homeScreen();
//            } else if (User.type.equals("Librarian")) {
//                homeScreen();
//                HomeWindow.ctrlStage();
//            };
//        }
//        MainStage.setOnCloseRequest(event -> HomeWindow.cp.close());
//    }
    public void homeScreen(){
//        try {
//            Parent root1 = FXMLLoader.load(getClass().getResource("Home.fxml"));
//            loginScene.setRoot(root1);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        MainStage.setTitle("Library System - Home - " + User.type + ": " + User.userName);
//        LoginWindow.label3.setText("");
//        new Alert().display("Welcome "+User.type,"Login Successful!","green");

        HomeWindow.checkType();
    }

    public void logOut(){
        mainSetup();
//        LoginWindow.textField1.clear();
//        LoginWindow.passField.clear();
        HomeWindow.searchbar.clear();
        HomeWindow.controlPanel.root.getChildren().clear();
        HomeWindow.controlPanel.root = new VBox();
        HomeWindow.cp.close();
        HomeWindow.type.getItems().remove("Users");
        HomeWindow.bp.setCenter(null);
        HomeWindow.type.setValue("Books");
        HomeWindow.search.booksArr=null;
        HomeWindow.search.usersArr=null;
        HomeWindow.search.result=null;
    }
}