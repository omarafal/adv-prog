package proggroup.advprogmt;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.Reader;

public class ControlPanel extends Application {
    VBox root = new VBox(); // the main pane that contains all the panes
    TopBar topBar = new TopBar();
    VBox mainSection = new VBox();
    VBox subSection = new VBox();
    Label winTitle = new Label();
    ScrollPane scrollPane = new ScrollPane();
    @Override
    public void start(Stage mainStage){
        root.getChildren().addAll(topBar.getTopBar(), mainSection);
        topBar.btn1.setOnAction(event -> addUser());
        topBar.btn2.setOnAction(event -> addBook());
        topBar.btn3.setOnAction(event -> bookList());
        mainStage.setTitle("Library System - Control Panel");
        mainStage.setResizable(false);
        mainStage.setScene(new Scene(root, 500, 300));
        mainStage.setX(100);
        mainStage.setY(150);
        mainStage.show();

    }
    public void addUser(){
        newStart("Add a new user");
        mainSection.getChildren().add(new Button("Hello"));
    }
    public void addBook(){
//        winTitle.setText("Add a new book");
        newStart("Add a new book");
        HBox buttonPane =new HBox();

        Label bookTitle = new Label("Book Title:");
        bookTitle.setTextAlignment(TextAlignment.LEFT);

        TextField bookField = new TextField();

        subSection.getChildren().addAll(new Label("Book Title:"), bookField);
        subSection.setPadding(new Insets(-25, 100, 0, 100));
        subSection.setSpacing(25);

        Button addButton = new Button("Add");

        buttonPane.setAlignment(Pos.BOTTOM_CENTER);
        buttonPane.getChildren().add(addButton);
        buttonPane.setPadding(new Insets(80, 0, 0, 0));

        mainSection.getChildren().addAll(subSection, buttonPane);
    }
    public void bookList(){
        newStart("Book Order List");
        alignMainCenter(scrollPane);

        String[] users = new Database("test-requests.txt", 'r').getPart1();
        String[] books = new Database("test-requests.txt", 'r').getPart2();

        VBox tempBox = new VBox();
        tempBox.setSpacing(50);
        String color = "";

        for(int i = 0; i < users.length; i++){
            HBox temp = new HBox();
            HBox buttonsTemp = new HBox();

            buttonsTemp.getChildren().addAll(new Button("✔"), new Button("X"));

            temp.getChildren().addAll(new Label(users[i]), new Label(books[i]), buttonsTemp);
            HBox.setMargin(temp,new Insets(0,0,0,80));
            alignMainCenter(temp);
            alignMainCenter(buttonsTemp);

            temp.setSpacing(50);
            buttonsTemp.setSpacing(25);

            for(Node button: buttonsTemp.getChildren()){
                int finalI = i;
                if(((Button)button).getText() == "✔"){
                    color = "99ff33";
                    ((Button) button).setOnAction(event -> {
                        Librarian.acceptRequest(users[finalI], books[finalI]);
                        bookList(); // call back the same function to set new scene
                    });
                }
                else{
                    color = "ff4c33";
                    ((Button) button).setOnAction(event -> {
                        Librarian.denyRequest(users[finalI]);
                        bookList(); // call back the same function to set new scene
                    });
                }
                ((Button) button).setStyle("-fx-background-radius: 150;" +
                        "-fx-pref-width: 15;" +
                        "-fx-pref-height: 15;" +
                        "-fx-font-weight: bold;"+
                        "-fx-background-color: #" + color + ";");

                ((Button) button).setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouse) {
                        root.setCursor(Cursor.HAND); // Change cursor to hand
                    }
                });

                ((Button) button).setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouse) {
                        root.setCursor(Cursor.DEFAULT); // Change cursor back to default
                    }
                });
            }
            tempBox.getChildren().add(temp);
        }
//        scrollPane.setStyle("-fx-width: 200;");
        scrollPane.setContent(tempBox);
        subSection.getChildren().add(scrollPane);
        mainSection.getChildren().add(subSection);
    }

    public void newStart(String winText){
        winTitle.setText(winText);
        mainSection = new VBox();
        subSection = new VBox();
        scrollPane = new ScrollPane();
        mainSection.getChildren().addAll(winTitle);
        alignMainCenter(mainSection);
        root.getChildren().clear();
        root.getChildren().addAll(topBar.getTopBar(),mainSection);
    }

    public void alignMainCenter(VBox pane){
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setSpacing(20);
    }

    public void alignMainCenter(HBox pane){
        pane.setAlignment(Pos.BASELINE_CENTER);
    }

    public void alignMainCenter(ScrollPane pane){
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);
    }
}
