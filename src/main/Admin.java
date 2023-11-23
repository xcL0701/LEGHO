package main;

import java.util.Vector;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin {
	Stage stage;
	Scene scene;
	BorderPane bp;
	ScrollPane sp;
	Pane pane;
	VBox vb;
	
	Menu menu;
	MenuBar mb;
	MenuItem logout;
	
	Alert a;
	
	private void initialize(Vector<Lego> legos) {
		bp = new BorderPane();
		bp.setPrefSize(1000, 700);
		vb = new VBox();
		mb = new MenuBar();
		menu = new Menu("Menu");
		logout = new MenuItem("Logout");
		
		mb.getMenus().add(menu);
		menu.getItems().addAll(logout);
		bp.setTop(mb);
		
		for (Lego i : legos) {
			if(i != null) {
				vb.getChildren().addAll(i.createLegoView());
			}else
			{
				break;
			}
		}
		
		vb.setSpacing(20);
		
		sp = new ScrollPane(vb);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        

		bp.setCenter(sp);
		scene = new Scene(bp, 1000, 700);
	}
	
	public void clickBtn(Vector<Users> users, Vector<Lego> legos) {
		logout.setOnAction(e -> {
			new Login(stage, users, legos);
		});
		
		legos.get(0).getUpdate().setOnMouseClicked(e ->{
			update(legos, 0);
		});
		
		legos.get(1).getUpdate().setOnMouseClicked(e ->{
			update(legos, 1);
		});
		
		legos.get(2).getUpdate().setOnMouseClicked(e ->{
			update(legos, 2);
		});
		
		legos.get(3).getUpdate().setOnMouseClicked(e ->{
			update(legos, 3);
		});
		
	}
	
	public void update(Vector<Lego> legos, int idx) {
		// TODO Auto-generated method stub
		if(
		legos.get(idx).getPricetf().getText().isEmpty() ||
		legos.get(idx).getStocktf().getText().isEmpty() ||
		!legos.get(idx).getPricetf().getText().matches(("[0-9.]+")) ||
		!legos.get(idx).getStocktf().getText().matches(("[0-9.]+")) ||
		legos.get(idx).getDesctf().getText().isEmpty() ||
		Long.parseLong(legos.get(idx).getPricetf().getText().toString()) <= 0 ||
		Integer.parseInt(legos.get(idx).getStocktf().getText().toString()) < 0 ){
			a = new Alert(AlertType.ERROR);
			a.setContentText("Please make sure field are correct");
			a.show();
		}else {
			legos.get(idx).setPrice(Long.parseLong(legos.get(idx).getPricetf().getText().toString()));
			legos.get(idx).setStock(Integer.parseInt(legos.get(idx).getStocktf().getText().toString()));
			legos.get(idx).setDesc(legos.get(idx).getDesctf().getText().toString());
			a = new Alert(AlertType.INFORMATION);
			a.setContentText("Update Success");
			a.show();
		}
	}
	
	public void clickImg(Vector<Users> users, Vector<Lego> legos) {
		Stage newstage = new Stage();
		
		legos.get(0).getImageView().setOnMouseClicked(e ->{
			new viewImage(newstage, legos.get(0));
		});
		legos.get(1).getImageView().setOnMouseClicked(e ->{
			new viewImage(newstage, legos.get(1));
		});
		legos.get(2).getImageView().setOnMouseClicked(e ->{
			new viewImage(newstage, legos.get(2));
		});
		legos.get(3).getImageView().setOnMouseClicked(e ->{
			new viewImage(newstage, legos.get(3));
		});
	}
	
	public Admin(Stage stage, Vector<Users> users, Vector<Lego> legos) {
		// TODO Auto-generated constructor stub
		initialize(legos);
		clickBtn(users, legos);
		clickImg(users, legos);
		
		this.stage = stage;
		this.stage.setTitle("ADMIN");
		this.stage.setScene(scene);
		this.stage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
		this.stage.show();
	}

}
