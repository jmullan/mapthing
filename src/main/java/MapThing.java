import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MapThing extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage myStage) {
        myStage.setTitle("Making a map");
        FlowPane rootNode = new FlowPane();
        Scene myScene = new Scene(rootNode, 300, 200);
        myStage.setScene(myScene);
        Label myLabel = new Label("This is a label.");
        rootNode.getChildren().add(myLabel);
        myStage.show();
    }
}
