package proggroup.advprogmt;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    Stage MainStage = new Stage();
    Login LoginWindow = new Login();
    Home HomeWindow = new Home();
    @Override
    public void start(Stage mainStage){
        MainStage=mainStage;
        mainStage.setTitle("Library System - Login");

        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image(Login.class.getResourceAsStream("online-library.png")));

        LoginWindow.login();

        mainStage.setScene(LoginWindow.loginScene);

        LoginWindow.loginBtn.setOnAction(e -> logIn());
        HomeWindow.logOut.setOnAction(e -> logOut());

        mainStage.show();
    }
    public void logIn(){
        LoginWindow.onClick(LoginWindow.textField1, LoginWindow.passField, LoginWindow.passShown, LoginWindow.label3);
        if(Login.UserIsAuth) {
            MainStage.setTitle("Library System - Home - " + Validation.Type + ": " + Validation.Username);
            LoginWindow.label3.setText("");
            new Alert().display("Welcome "+Validation.Type,"Login Successful!","green");
            HomeWindow.home();
            MainStage.setScene(HomeWindow.homeScene);
            HomeWindow.checkType();
        }
    }
    public void logOut(){
        MainStage.setTitle("Library System - Login");
        MainStage.setScene(LoginWindow.loginScene);
        Login.UserIsAuth =false;
        LoginWindow.loginBtn.setOnAction(event -> logIn());
        LoginWindow.showPass.setGraphic(LoginWindow.hideIco);
        HomeWindow.searchbar.clear();
        HomeWindow.bp.setCenter(null);
        HomeWindow.type.setValue("Book");
    }
}