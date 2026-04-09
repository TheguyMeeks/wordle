package wordle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class WordleApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApp.class.getResource("GameSetupView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        GameSetupController controller = fxmlLoader.getController();

        /*
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.print(keyEvent.getText() + " keycode: ");
                System.out.print(keyEvent.getCode() + "\n");
                controller.handleKeyTyped(keyEvent);
                System.out.println(Arrays.deepToString(controller.labels));

            }
        });
        */

        stage.setTitle("Wordle—remake!");
        stage.setScene(scene);
        stage.show();
    }
}
