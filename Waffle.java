import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author jta787
 * @version 29/11/19
 */
public class Waffle extends Application {
	/**
	 * @param size size of the squares
	 * @param xoffset where to start the table
	 * @param yoffset where to start the table
	 * @param gap offset how big the gap is
	 * @param key xoffset where the key is
	 * @param key yoffset where the key is
	 */
    public static final int SIZE = 30; 
    public static final int X_OFFSET = 40;
    public static final int Y_OFFSET = 40;
    public static final int GAP_OFFSET = 10;
    public static final int KEY_X_OFFSET = 600;
    public static final int KEY_Y_OFFSET = 40;
	
    /**
    * @param expenditures
    * @param maxlength
    * @param startlength
    * @param colours to use
    */
	public static Expenditure[] expenditures;
	public static int maxLength;
	public static int startLength;
	public static Color[] color = new Color[]{Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK, Color.GRAY};
	
	/**
	 * setSquares method
	 */
	public static Rectangle[][] setSquares() {

        int[] expenditureLengths = round();
        Rectangle[][] squares = new Rectangle[10][10]; // 10 by 10
        int count = 0;
        int index = 0; 

        for (int j=0; j<10; j++){ // loop creating the squares
            for (int i=0; i<10; i++){
		        squares[i][j] =
                    new Rectangle(X_OFFSET + GAP_OFFSET * i + i*SIZE, Y_OFFSET + GAP_OFFSET * j + j*SIZE,
                                  SIZE, SIZE);
                    count++;
              
                if (count > expenditureLengths[index]){ // if enough squares have been coloured
                    index++;
                    count = 1;
                }
                squares[i][j].setFill(color[index]);
            }
    	}
        return squares;
    } 
	/**
	 *setKey method
	 */
	public static Rectangle[] setKey(){

        Rectangle[] key = new Rectangle[expenditures.length];
        
        for (int i=0; i<expenditures.length; i++){
            key[i] =
                new Rectangle(KEY_X_OFFSET, KEY_Y_OFFSET + GAP_OFFSET * i + (SIZE / 2 * (startLength - expenditures.length) + GAP_OFFSET) + i * SIZE,
                                SIZE, SIZE); //draws the rectangles for the key
                key[i].setFill(color[i]);
    	}
        return key;
    }
	/**
	 * setLabels method for the key
	 */
	 public static Text[] setLabels(){
	        Text[] labels = new Text[expenditures.length];

	        for( int i = 0; i < expenditures.length; i++){
	            labels[i] =
	                new Text(KEY_X_OFFSET + 55, KEY_Y_OFFSET + GAP_OFFSET * i + (SIZE / 2 * (startLength - expenditures.length) + GAP_OFFSET) + (SIZE / 2) + i * SIZE,
	                expenditures[i].getDescription());
	        }
	        return labels;
	    }
	 /**
	  * sumValues method that returns the sum of all the values in the array
	  */
	 public static double sumValues(int value){
	        double sumValues = 0;
	        for (int i = value; i < expenditures.length; i++){
	            sumValues += expenditures[i].getValue();
	        }
	        return sumValues;
	    }
	 /**
	  * maxExpenditure method and sorts smallest values into Other
	  */
	 public static Expenditure[] maxExpenditure (){
	        double other = sumValues(maxLength - 1);
            Expenditure[] newExpenditure = new Expenditure[maxLength];

	        for (int i = 0; i < maxLength; i++){
	            if (i == maxLength - 1){
	                newExpenditure[i] = new Expenditure("Other", (int) other);
	                continue;
	            }
	            newExpenditure[i] = expenditures[i];
	        }
	        return newExpenditure;
	    }
	 /**
	  * formatting method to sort the array
	  */
	 public static Expenditure[] formatting(){
	        
	        Arrays.sort(expenditures, (Expenditure exp1, Expenditure exp2) ->
	            exp2.getValue() - exp1.getValue());

	        if (expenditures.length > maxLength){
	            expenditures = maxExpenditure();
	        }

	        return expenditures;
	    }
	 /**
	  * finds max value in double array
	  */
	 public static int maxDouble(double[] d){
	        int max = 0;
	        for (int i = 0; i < d.length; i++){
	            if (d[i] > d[max]){ max = i;}
	        }
	        return max;
	    }
	 /**
	  * method round that creates array for number of sqaures for each expenditure and checks losses due to rounding
	  */
	 public static int[] round() {

	        double totalExpenditure = sumValues(0);
            int[] expenditureLengths = new int[expenditures.length];
            double[] Array = new double[expenditures.length];
	        double rounded = 0;

	        for (int i = 0; i < expenditures.length; i++){
	            double realValue = expenditures[i].getValue() / totalExpenditure * 100.0;
	            
	            rounded += realValue % 1;
	            expenditureLengths[i] = (int) Math.floor(realValue);
	            Array[i] = realValue % 1;
	        }
	        
	        while (rounded >= 0.9999){
	            int maxRoundIndex = maxDouble(Array);
	            expenditureLengths[maxRoundIndex]++;
	            Array[maxRoundIndex]--;
	            rounded--;
	        }

	        return expenditureLengths;
	    } 
	 
	 
	
	
	
	@Override
    public void start(Stage stage) throws Exception {
    	
        expenditures = formatting();
        /**
         * creates the elements of the scene
         */
        Rectangle[][] waffle = setSquares();
        Rectangle[] key = setKey();
        Text[] labels = setLabels();

        Group root = new Group();

        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                root.getChildren().add(waffle[i][j]);
            }
        }
        
        for (int i=0; i<expenditures.length; i++){
            root.getChildren().add(key[i]);
        }
 
        for (int i=0; i<expenditures.length; i++){
            root.getChildren().add(labels[i]);
        }
        /**
         * sets the scene
         */
        Scene scene = new Scene(root,1000,700);

        stage.setTitle("Expenditure - Waffle Chart");
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
