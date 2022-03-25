package Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage1) throws Exception {
        FXMLLoader fxmlLoader1 = new FXMLLoader(MainApp.class.getResource("MainWindow.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 800, 800);
        MainWindow mainWindow = fxmlLoader1.getController();

        FXMLLoader fxmlLoader2 = new FXMLLoader(MainApp.class.getResource("SelectWindow.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 800, 600);
        SelectWindow selectWindow = fxmlLoader2.getController();

        mainWindow.setSelectWindow(selectWindow);

        stage1.setScene(scene1);
        stage1.setTitle("Main Window");
        stage1.show();

        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.setTitle("Select Window");
        stage2.show();
    }

    public static void main(String[] args){
        launch();
    }
}
