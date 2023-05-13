package proggroup.advprogmt;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home{
    ControlPanel controlPanel = new ControlPanel();
    Stage cp = new Stage();
    Alert alert = new Alert();
    Scene homeScene;
    BorderPane bp = new BorderPane();
    HBox topMenu = new HBox();
    HBox body = new HBox();
    TextField searchbar = new TextField();
    Button searchBtn = new Button("Search");
    Button ctrlPanel = new Button();
    Tooltip toolTip1 = new Tooltip("Control Panel");
    Button logOut = new Button();
    Tooltip toolTip2 = new Tooltip("LogOut");
    static ComboBox<String> type;
    ImageView icon1 = new ImageView(new Image(Home.class.getResourceAsStream("settings.png")));
    ImageView icon2 = new ImageView(new Image(Home.class.getResourceAsStream("exit.png")));
    Label text = new Label();
    static boolean ctrlPanel_opened = true;
    Search search = new Search();
    Librarian librarian = new Librarian();
    Reader reader = new Reader();
    public void home(){
        searchbar.setPromptText("Search");
        searchbar.setMinWidth(240);
        searchbar.setStyle("-fx-focus-color:grey;");

        searchBtn.setStyle("-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-cursor:hand;");
        searchBtn.setOnAction(e -> {
//            search.searchfor(searchbar.getText(), type.getValue());
            bp.setCenter(null);
            if (search.i==0) {
                if (User.type.equals("Librarian")){
                    if(type.getValue().equals("Users")){
                        bp.setCenter(librarian.searchUsers());
                    }
//                    else bp.setCenter(librarian.searchBooks());
                }
//                else bp.setCenter(reader.searchBooks());
            }else {
                bp.setCenter(null);
                System.out.println(search.result +"from home");
                text.setText(search.result);
                bp.setCenter(body);
            }
        });
        searchbar.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                searchBtn.fire();
                searchBtn.requestFocus();
            }
        });

        type = new ComboBox<>();
        type.getItems().addAll("Books");
        type.setValue("Books");
        type.setTranslateX(12);
        type.setStyle("-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-cursor:hand");

        icon1.setFitHeight(20);
        icon1.setPreserveRatio(true);
        ctrlPanel.setGraphic(icon1);
        ctrlPanel.setStyle("-fx-background-color:transparent;-fx-cursor:hand");
        ctrlPanel.setTranslateX(28);
        ctrlPanel.setTooltip(toolTip1);
        toolTip1.setStyle("-fx-show-delay:1ms");
        ctrlPanel.setOnAction(e -> {
            if (!ctrlPanel_opened){
                ctrlStage();
                Home.ctrlPanel_opened = true;
            }else alert.display("Error","Control Panel is already open!","red");
        });

        cp.setOnCloseRequest(event -> {
            controlPanel.root.getChildren().clear();
            controlPanel.root = new VBox();
            controlPanel.root.getChildren().clear();
            Home.ctrlPanel_opened = false;
        });

        icon2.setFitHeight(20);
        icon2.setPreserveRatio(true);
        logOut.setGraphic(icon2);
        logOut.setStyle("-fx-background-color:transparent;-fx-cursor:hand");
        logOut.setTranslateX(36);
        logOut.setTooltip(toolTip2);
        toolTip2.setStyle("-fx-show-delay:1ms");

        topMenu.setPadding(new Insets(20,20,20,20));
        topMenu.setSpacing(0);
        topMenu.setStyle("-fx-background-color:lightgrey");
        topMenu.getChildren().addAll(searchbar,searchBtn,type,ctrlPanel,logOut);

        text.setStyle("-fx-font-size:16");
        body.getChildren().addAll(text);
        body.setAlignment(Pos.TOP_CENTER);

        bp.setTop(topMenu);

        homeScene = new Scene(bp,500,300);
    }
    public void checkType(){
        ctrlPanel.setVisible(!User.type.equals("Reader"));
        if (!User.type.equals("Reader"))
            type.getItems().add("Users");
    }
    public void ctrlStage(){
        controlPanel.start(cp);
        ctrlPanel_opened = true;
    }
    public static class HBoxCell extends HBox {
        Label text = new Label();
        Button btn1 = new Button();
        Button btn2;
        Librarian librarian = new Librarian();
        Reader reader = new Reader();
        HBoxCell(String labelText, String buttonText1, String color1, String buttonText2, String color2, String type) {
            super();
            text.setStyle("-fx-font-size:16");
            text.setText(labelText);
            text.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(text, Priority.ALWAYS);
            this.getChildren().add(text);
            if (type.equals("Librarian")){
                if (!Home.type.getValue().equals("Users")){
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
                btn1.setStyle("-fx-background-radius:7;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-background-color:" + color1 + ";-fx-cursor:hand;");
                btn1.setPadding(new Insets(4, 30, 4, 30));
                btn1.setOnAction(e -> {
                    System.out.println(labelText + " removed");
                });
            }else {
                this.getChildren().add(btn1);
                btn1.setText(buttonText1);
                btn1.setStyle("-fx-background-radius:7;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;-fx-background-color:"+ color1 +";-fx-cursor:hand;");
                btn1.setPadding(new Insets(4,38,4,38));
                btn1.setOnAction(e->{
                    System.out.println(labelText + " rent pressed");
                });
            }

        }


    }
}
