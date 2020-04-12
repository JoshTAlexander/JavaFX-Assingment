import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color; 
/**
 * @author jta787
 * @version 28/11/19
 */
public class Upload extends Application{

	@Override
    public void start(Stage stage) throws Exception {
		/**
		 * creates the bottom shape colour black
		 */
        Polygon polygon1 = new Polygon(200,80,200,210,400,210,400,80,370,80,370,180,230,180,230,80); 
        polygon1.setFill(Color.BLACK);
        polygon1.setStroke(Color.BLACK);
        /**
		 * creates the arrow shape colour black
		 */
        Polygon polygon2 = new Polygon(300,30,260,60,285,60,285,150,315,150,315,60,340,60); 
        polygon2.setFill(Color.BLACK);
        polygon2.setStroke(Color.BLACK);
        /**
		 * creates the group of both shapes
		 */
        Group root = new Group(polygon1,polygon2);
        /**
		 * creates the scene
		 */
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Upload");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { 
        launch(args);
    }
}