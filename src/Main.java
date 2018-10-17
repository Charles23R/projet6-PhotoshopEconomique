import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(true);
        primaryStage.setTitle("Jojo's Bizarre Adventure : Diamond is Unbreakable");
        primaryStage.setMaximized(true);


        //MENUS
        MenuItem image1 = new MenuItem("Image #1");
        MenuItem image2 = new MenuItem("Image #2");
        MenuItem image3 = new MenuItem("Image #3");
        MenuItem reinitialiser = new MenuItem("Réinitialiser");

        Menu charger = new Menu("Charger une image");
        charger.getItems().addAll(image1, image2, image3);

        Menu fichiers = new Menu("Fichiers");
        fichiers.getItems().add(charger);

        Menu actions = new Menu("Actions");
        actions.getItems().add(reinitialiser);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fichiers, actions);

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(fichiers, actions);




        //SLIDERS ET IMAGES
        Label lumi = new Label("Luminosité");
        Label contraste = new Label("Contraste");
        Label teinte = new Label("Teinte");
        Label saturation = new Label("Saturation");

        Slider lumiSlide = new Slider(-1, 1, 0);
        Slider contrasteSlide = new Slider(-1, 1, 0);
        Slider teinteSlide = new Slider(-1, 1, 0);
        Slider saturationSlide = new Slider(-1, 1, 0);

        Tooltip tooltip1= new Tooltip("Rend l'image plus claire ou plus sombre");
        Tooltip tooltip2= new Tooltip("Diminue ou augmente la différence entre les couleurs");
        Tooltip tooltip3= new Tooltip("Change la teinte (couleur) de l'image");
        Tooltip tooltip4= new Tooltip("Diminue ou augmente l'intensité des couleurs");

        lumiSlide.setTooltip(tooltip1);
        contrasteSlide.setTooltip(tooltip2);
        teinteSlide.setTooltip(tooltip3);
        saturationSlide.setTooltip(tooltip4);

        ImageView imageView = new ImageView("file:default.jpg");
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        Image imageView1 = new Image("file:image1.jpg");
        Image imageView2 = new Image("file:image2.jpg");
        Image imageView3 = new Image("file:image3.jpg");
        ColorAdjust modifs = new ColorAdjust();

        Label label = new Label("Bienvenue dans le modificateur d'images!");
        label.setPadding(new Insets(5));


        //DISPOSITION
        VBox vBox = new VBox(lumi, lumiSlide, contraste, contrasteSlide, teinte, teinteSlide, saturation, saturationSlide);
        HBox hBox = new HBox(imageView, vBox);
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        hBox.setSpacing(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(hBox);
        borderPane.setBottom(label);

        //EVENTS
        reinitialiser.setOnAction((event) -> {
            lumiSlide.setValue(0);
            contrasteSlide.setValue(0);
            saturationSlide.setValue(0);
            teinteSlide.setValue(0);
            label.setText("Réinitialisation des ajustements de couleur");
        });

        image1.setOnAction((event -> {
            imageView.setImage(imageView1);
            label.setText("Image 1 chargée");
            reinitialiser.fire();
        }));

        image2.setOnAction((event -> {
            imageView.setImage(imageView2);
            label.setText("Image 2 chargée");
            reinitialiser.fire();
        }));

        image3.setOnAction((event -> {
            imageView.setImage(imageView3);
            label.setText("Image 3 chargée");
            reinitialiser.fire();
        }));

        lumiSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
            modifs.setBrightness((double)newValue);
            imageView.setEffect(modifs);
        });

        contrasteSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
            modifs.setContrast((double)newValue);
            imageView.setEffect(modifs);
        });

        teinteSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
            modifs.setHue((double)newValue);
            imageView.setEffect(modifs);
        });

        saturationSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
            modifs.setSaturation((double)newValue);
            imageView.setEffect(modifs);
        });

        borderPane.setOnContextMenuRequested(event -> {
            contextMenu.show(borderPane, event.getScreenX(), event.getScreenY());
        });


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
