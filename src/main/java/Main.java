import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // how to get the file path
//        Image image = new Image("z-p-15.jpg");
//        System.out.println("image = " + image);
//        System.out.println("image path = " + image.getUrl());
//
//        ImageView iv = new ImageView(image);
//
//        String pathToImageGetFile = getClass().getResource("z-p-32.jpg").getFile();
//        String pathToImageToString = getClass().getResource("z-p-32.jpg").toString();
//        System.out.println("pathToImage-GetFile() = " + pathToImageGetFile);
//        System.out.println("pathToImage-ToString() = " + pathToImageToString);
//
//        Image image2 = new Image(pathToImageToString);
//        ImageView iv2 = new ImageView(image2);

        // how to make stage size equals image size ?
        Image image1 = new Image("full-tech-1-1.png");
        ImageView iv1 = new ImageView(image1);
        iv1.setPreserveRatio(true);
        iv1.setFitWidth(500);

        System.out.println("------------------------------------------------");

        // default fxml
        AnchorPane root = new AnchorPane();
        VBox vbox = new VBox();
        vbox.getChildren().add(iv1);

        root.getChildren().add(vbox);

        // add Close Button
        Button closeBtn = new Button("EXIT");
        Image imageBtn = new Image("gnome_panel_force_quit.png");
        ImageView ivBtn = new ImageView(imageBtn);
        ivBtn.setPreserveRatio(true);
        ivBtn.setFitWidth(40);

        closeBtn.setGraphic(ivBtn);
        closeBtn.setContentDisplay(ContentDisplay.TOP);
        closeBtn.setCancelButton(true);
        closeBtn.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: white");

        root.getChildren().add(closeBtn);
//        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setTopAnchor(closeBtn, 10.0);
//        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(closeBtn, 10.0);

        closeBtn.setOnAction(e -> Platform.exit());

        iv1.fitWidthProperty().bind(primaryStage.widthProperty());
        iv1.fitHeightProperty().bind(primaryStage.heightProperty());

        Rectangle2D rectangleScreen = Screen.getPrimary().getVisualBounds();
        primaryStage.setHeight(rectangleScreen.getHeight() * 3/4);
        primaryStage.setWidth(primaryStage.getHeight() * image1.getWidth()/image1.getHeight());

        // Video Player FXML
//        Pane rootVideoPlayer = FXMLLoader.load(getClass().getResource("VideoPlayer.fxml"));


        // add Open New-Window Button
        Button newWindowBtn = new Button("New Window");
        root.getChildren().add(newWindowBtn);
        AnchorPane.setTopAnchor(newWindowBtn, 10.0);
        AnchorPane.setLeftAnchor(newWindowBtn, 10.0);
        newWindowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("open new window button clicked");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VideoPlayer.fxml"));

                    Parent root = fxmlLoader.load();
                    Stage videoStage = new Stage();
                    Scene videoScene = new Scene(root, 1280, 720);

                    videoStage.setScene(videoScene);
                    videoStage.setTitle("Video Stage");
                    videoStage.show();

                } catch (Exception e) {
                    System.out.println("error loading fxml file");
                }
            }
        });

        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Hello");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
