import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private AnchorPane anchorpane_video;

    @FXML
    private Button btn_play;

    @FXML
    private Button btn_stop;

    @FXML
    private MediaView mv1;

    @FXML
    private Button btn_getWindow;

    @FXML
    private Button btn_disposeVideo;

    MediaPlayer mediaPlayer;
    AudioClip clickSFX;
    AudioClip overlaySFX;


    public void initialize() {

        // get SoundFX
        clickSFX = new AudioClip(getClass().getResource("MouseClick.mp3").toString());
//        overlaySFX = new AudioClip(getClass().getResource("MouseOver.mp3").toString());

        // get Media a path
        String mediaPath = getClass().getResource("Splittek-Intro-Electric_compressed.mp4").toString();
//        System.out.println(mediaPath);

        // setup Media and MediaPlayer
        Media media = new Media(mediaPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // setup MediaView
        mv1.setMediaPlayer(mediaPlayer);
        mv1.setPreserveRatio(false);
//        mv1.setFitWidth(500);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getStageAndFitVideoSize();
                handleCloseRequest();
            }
        });

    }

    @FXML
    void handleButtonPlay(ActionEvent event) {
//        System.out.println("play button clicked");
        clickSFX.play();
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }
    }

    @FXML
    void handleButtonStop(ActionEvent event) {
//        System.out.println("stop button clicked");
        clickSFX.play();
        mediaPlayer.stop();
    }

    @FXML
    void handleMouseOver(MouseEvent event) {
        System.out.println("mouse entered");
//        overlaySFX.play();
    }

    @FXML
    void handleGetWindow(ActionEvent event) {
        System.out.println("handle get Window");
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        System.out.println(thisStage.getHeight());
        System.out.println(thisStage.getWidth());

//        Stage currentStage = (Stage) anchorpane_video.getScene().getWindow();
//        System.out.println(currentStage.getHeight());
    }

    void getStageAndFitVideoSize() {
        Stage currentStage = (Stage) anchorpane_video.getScene().getWindow();
        System.out.println("this is from run later");

        mv1.fitWidthProperty().bind(currentStage.widthProperty());
        mv1.fitHeightProperty().bind(currentStage.heightProperty());

//        Rectangle2D rectScreen = Screen.getPrimary().getVisualBounds();
//
//        currentStage.setHeight(rectScreen.getHeight() * 3/4);
//        currentStage.setWidth(currentStage.getHeight() * 1280/720);

    }

    @FXML
    void handleDisposeVideo(ActionEvent event) {
        System.out.println("dispose media");
        mediaPlayer.dispose();
    }

//    void handleCloseRequest() {
//        Stage currentStage = (Stage) anchorpane_video.getScene().getWindow();
//        currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                System.out.println("Closed, media disposed");
//                mediaPlayer.dispose();
//            }
//        });
//    }

    void handleCloseRequest() {
        Stage currentStage = (Stage) anchorpane_video.getScene().getWindow();
        currentStage.setOnCloseRequest(e -> {
            System.out.println("Closed, media disposed");
            mediaPlayer.dispose();
        });
    }

}