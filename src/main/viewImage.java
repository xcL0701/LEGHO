package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class viewImage {
	Stage stage1;
	Scene scene1;
	BorderPane bp1;
	GridPane gp1;
	VBox vb1;
	Label desc;
	ImageView iv;
	Button lRotate, rRotate, zoomIn, zoomOut;
	
	public void initialize(Lego lego) {
		// TODO Auto-generated method stub
		desc = new Label(lego.getDesc());
		
		iv = new ImageView(lego.getImage());
		iv.setFitWidth(300);
		iv.setFitHeight(250);
		
		bp1 = new BorderPane();
		vb1 = new VBox();
		
		lRotate = new Button("Left Rotate");
		rRotate = new Button("Right Rotate");
		zoomIn = new Button("Zoom In");
		zoomOut = new Button("Zoom Out");
		
		gp1 = new GridPane();
		gp1.add(lRotate, 0, 0);
		gp1.add(rRotate, 0, 1);
		gp1.add(zoomIn, 1, 0);
		gp1.add(zoomOut, 1, 1);
		gp1.setAlignment(Pos.CENTER);
		
		vb1.getChildren().addAll(iv, desc, gp1);
		vb1.setAlignment(Pos.CENTER);
		
		bp1.setCenter(vb1);
		
		scene1 = new Scene(bp1, 600, 600);
	}
	
	private void clickBtn() {
		lRotate.setOnMouseClicked(e ->{
			iv.setRotate(iv.getRotate()-90);
		});
		rRotate.setOnMouseClicked(e ->{
			iv.setRotate(iv.getRotate()+90);
		});
		zoomIn.setOnMouseClicked(e ->{
			if(iv.getScaleX() <= 2.5) {
				iv.setScaleX(iv.getScaleX()*1.2);
				iv.setScaleY(iv.getScaleY()*1.2);
			}
		});
		zoomOut.setOnMouseClicked(e ->{
			if(iv.getScaleX() >= 0.4) {
				iv.setScaleX(iv.getScaleX()*0.8);
				iv.setScaleY(iv.getScaleY()*0.8);
			}
		});
	}
	
	public viewImage(Stage stage1, Lego lego) {
		// TODO Auto-generated constructor stub
		initialize(lego);
		clickBtn();
		this.stage1 = stage1;
		this.stage1.setTitle("View Image");
		this.stage1.setScene(scene1);
		this.stage1.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
		this.stage1.show();
	}

}
