package proggroup.advprogmt;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TopBar {
    Button btn1 = new Button("Add User");
    Button btn2 = new Button("Add Book");
    Button btn3 = new Button("Book Order List");
    static HBox pane = new HBox();

    public TopBar() {
        pane.setSpacing(50);
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.getChildren().addAll(btn1, btn2, btn3);
    }

    public static Pane getTopBar(){
        return pane;
    }

    @Override
    public String toString(){
        return ("Top nevigavtion bar: Add User - Add Book - Book Order List");
    }
}
