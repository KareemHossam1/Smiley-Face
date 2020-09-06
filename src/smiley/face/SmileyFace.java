package smiley.face;
// imporing packages 
import java.util.Random;
import javafx.animation.KeyFrame; 
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import javafx.util.Duration;
public class SmileyFace extends Application {
    Circle yellowCircle = new Circle (200,120,70);  // create the face circle and its position and radius
    Circle blueCircle1 = new Circle (180,100,10); // create the first eye circle and its position and radius
    Circle blueCircle2 = new Circle (220,100,10);  // create the second eye circle and its position and radius
    QuadCurve curve = new QuadCurve();   // create the smile curve
    Button btn = new Button("Change color");
    Pane panFace = new Pane();
    StackPane buttonPan = new StackPane();
    VBox v = new VBox(40);
    Random rand = new Random();
    double moveDistance=18.5; // variable to control the face movement
    int eyeColorR,eyeColorG,eyeColorB,faceColorR,faceColorG,faceColorB;
    public void start(Stage primaryStage) {
        yellowCircle.setFill(Color.RED); // make the face circle color yellow
        yellowCircle.setStroke(Color.BLACK); // make stroke for the circle
        blueCircle1.setFill(Color.GREEN);   // make the first eye circle color blue
        blueCircle2.setFill(Color.GREEN);    // make the second eye circle color blue
        curve.setStartX(180);   // x starting point
        curve.setStartY(150);   // y starting point
        curve.setEndX(220);     // x end point
        curve.setEndY(150);     // y end point
        curve.setControlX(200);   // making the curve shape
        curve.setControlY(170);
        curve.setStroke(Color.WHITE); // to make the curve color red
        curve.setFill(Color.RED);  // to be like the face color
        moveFace();
        buttonPan.getChildren().add(btn);
        buttonPan.setPadding(new Insets (5,5,5,5));
        panFace.getChildren().addAll(yellowCircle,blueCircle1,blueCircle2,curve);
        v.getChildren().addAll(panFace,buttonPan);
        // Handling the button
        btn.setOnAction(e->{
            faceColorR=rand.nextInt(255);
            faceColorG=rand.nextInt(255);
            faceColorB=rand.nextInt(255);
            yellowCircle.setFill(Color.rgb(faceColorR,faceColorG,faceColorB));  // change face color with a random color
            curve.setFill(Color.rgb(faceColorR,faceColorG,faceColorB));  // to be like the face color
            curve.setStroke(Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255))); // change smile color randomly
            eyeColorR=rand.nextInt(255);
            eyeColorG=rand.nextInt(255);
            eyeColorB=rand.nextInt(255);
            blueCircle1.setFill(Color.rgb(eyeColorR,eyeColorG,eyeColorB));  // change eyes color
            blueCircle2.setFill(Color.rgb(eyeColorR,eyeColorG,eyeColorB));
        });
        Scene scene = new Scene(v,407,300);  // create the scene
        primaryStage.setResizable(false);   // to make it not resizable
        primaryStage.setTitle("Smiley Face");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void moveFace(){
            Timeline move = new Timeline(new KeyFrame(Duration.millis(500),e ->{   // face movement event handler
                if (yellowCircle.getCenterX()>330|| yellowCircle.getCenterX() < 80)   
                moveDistance*=-1;         // to reverse its movement direction when it hits the border
                yellowCircle.setCenterX(yellowCircle.getCenterX()+moveDistance);  // move the face
                blueCircle1.setCenterX(blueCircle1.getCenterX()+moveDistance);  // move first eye
                blueCircle2.setCenterX(blueCircle2.getCenterX()+moveDistance);  // move second eye
                curve.setStartX(curve.getStartX()+moveDistance);
                curve.setEndX(curve.getEndX()+moveDistance);   // move smile
                curve.setControlX(curve.getControlX()+moveDistance);
            }));
            move.setCycleCount(Timeline.INDEFINITE);
            move.play();
    }}