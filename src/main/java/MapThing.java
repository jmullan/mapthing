import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class MapThing extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage myStage) {
        int width = 1000;
        int height = 1000;
        int x;
        int y;
        Random random = new Random();
        myStage.setTitle("Making a map");
        FlowPane rootNode = new FlowPane();
        Scene myScene = new Scene(rootNode, width, height);
        myStage.setScene(myScene);

        WritableImage dest = new WritableImage(width, height);
        PixelWriter writer = dest.getPixelWriter();
        for (x = 0; x < width; x++) {
            for (y = 0; y < height; y++) {
                writer.setColor(x, y, Color.WHITE);
                Color color = new Color(
                    random.nextDouble(),
                    random.nextDouble(),
                    random.nextDouble(),
                    1.0
                );
                writer.setColor(x, y, color);
            }
        }
        ImageView destView = new ImageView(dest);
        rootNode.getChildren().add(destView);


        myStage.show();
    }
}
