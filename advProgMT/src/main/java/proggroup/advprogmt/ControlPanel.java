package proggroup.advprogmt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlPanel extends Application {
    VBox root = new VBox(); // the main pane that contains all the panes
    TopBar topBar = new TopBar();
    HBox mainSection = new HBox();
    @Override
    public void start(Stage mainStage){
        root.getChildren().add(topBar.getTopBar());
        topBar.btn1.setOnAction(event -> addUser());
        mainStage.setTitle("Library System - Control Panel");
        mainStage.setResizable(false);
        mainStage.setScene(new Scene(root, 500, 300));
        mainStage.show();
    }
    public void addUser(){
    }

}
