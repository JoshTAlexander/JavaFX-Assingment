/**
 * @author jta787
 * @version 28/11/19
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.paint.Color; 

public class Pie extends Application{
	
	/**
	 * @param xoffset set centre of pie chart
	 * @param yoffset set centre of pie chart
	 * @param radius set radius of pie chart
	 * @param label radius set radius of labels
	 */
	public static final int X_OFFSET =500;
	public static final int Y_OFFSET=300;
	public static final int RADIUS=200;
	public static final int LABEL_RADIUS =250;
	
	/**
	 * @param expenditures
	 * @param maxLength
	 * @param startLength
	 */
	public static Expenditure[] expenditures;
	public static int maxLength;
	public static int startLength;
	
	/**
	 * setCirlce method to create the circle
	 */
	public static Circle setCircle() {
		Circle circle = new Circle(X_OFFSET, Y_OFFSET, RADIUS);
		circle.setFill(null);
		circle.setStroke(Color.BLACK);
		return circle;
	}
	/**
	 * sumValues method to find total of expenditures
	 */
	public static double sumValues(double Values) {
	    double sum = 0;
	        for (int i=0; i < expenditures.length; i++) {
		        sum += expenditures[i].getValue();
		}
	    return sum;
	}
	
	/**
	 * setWedge method to set the angles of the sections relative to total expenditure
	 */
	public static Line[] setWedge() {
		Line[] wedge = new Line[expenditures.length];
				
		
		double totalExpenditure = sumValues(0);
		double[] expenditureRadians = new double[expenditures.length];
		
		for (int i =0; i<expenditures.length; i++) {
			expenditureRadians[i] = expenditures[i].getValue() / totalExpenditure *2 * Math.PI;
	}
		double totalRadians=0;
		
		for (int i =0; i<expenditureRadians.length; i++) {
			wedge[i] = new Line(X_OFFSET,Y_OFFSET,X_OFFSET +(RADIUS *Math.cos(totalRadians)), Y_OFFSET - (RADIUS * Math.sin(totalRadians)));
			wedge[i].setStroke(Color.BLACK);
			totalRadians += expenditureRadians[i];
		}
		return wedge;
	
		
	}
	/**
	 * setText method to set the descriptions of the sections next to the section
	 */
	public static Text[] setLabels() {
		Text[] labels = new Text[expenditures.length];
		
		String[] labelDescriptions = new String[expenditures.length];
		
		for (int i=0; i <expenditures.length; i++) {
			labelDescriptions[i] = expenditures[i].getDescription();
		}
		
		double totalExpenditure = sumValues(0);
		double[] expenditureRadians = new double[expenditures.length];
		
		for (int i =0; i<expenditures.length; i++) {
			expenditureRadians[i] = expenditures[i].getValue() / totalExpenditure *2 * Math.PI;
	}
		double totalRadians= 0;
		double textAngle = 0;
		
		for (int i=0; i<labelDescriptions.length; i++) {
			
			textAngle = totalRadians + expenditureRadians[i]/2;
			labels[i] = new Text(X_OFFSET +(LABEL_RADIUS *Math.cos(textAngle)), Y_OFFSET - (LABEL_RADIUS * Math.sin(textAngle)), labelDescriptions[i]);
			totalRadians += expenditureRadians[i];
			
			if (labels[i].getLayoutBounds().getWidth() > 50 && (textAngle < (Math.PI/2) && textAngle > (3*Math.PI/2))) {
				textAngle = totalRadians + expenditureRadians[i]/2;
				labels[i] = new Text((X_OFFSET - labels[i].getLayoutBounds().getWidth() +50 )  +(LABEL_RADIUS  *Math.cos(textAngle)), Y_OFFSET - (LABEL_RADIUS * Math.sin(textAngle)), labelDescriptions[i]);
				totalRadians += expenditureRadians[i];
			}	
		}
		return labels;
	}
	
	@Override
	public void start(Stage stage) throws Exception{	
		/**
		 * creates the objects in the scene
		 */
		Circle circle = setCircle();
		Line[] wedge = setWedge();
		Text[] labels = setLabels();
		
		Group root = new Group();
		root.getChildren().add(circle);
		
		if(wedge.length > 1) {
			for (Line l: wedge) {
				root.getChildren().add(l);
			}
		}
		
		for (Text t: labels) {
			root.getChildren().add(t);
		}
		/**
		 * sets the scene
		 */
	        Scene scene = new Scene(root, 1000, 700);
	        stage.setTitle("Pie");
	        stage.setScene(scene);
	        stage.show();
		
	}
	 public static void main(String[] args) { 
		 
		 expenditures = 
				 new Expenditure[] {
						 new Expenditure("Salaries", 11000),
						 new Expenditure("Paper", 2000),
						 new Expenditure("Rent", 5000),
						 new Expenditure("Most popular books on Java ect.", 10000),
						 new Expenditure("Heating", 3000),
						 new Expenditure("Coffee/Tea", 7000),
						 new Expenditure("Buiscuits", 8000),
						 new Expenditure("Travel", 18000),
						 new Expenditure("Electricity", 1000),
						 new Expenditure("Pencils", 3000),
				 };
		 maxLength = 8;
		 startLength = expenditures.length;
	        launch(args);
	    }

}
