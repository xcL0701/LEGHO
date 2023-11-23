package main;

import java.io.File;
import java.util.Vector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Catalogue {

	Stage stage;
	Scene scene;
	BorderPane bp;
	GridPane gpLeft;
	VBox vbLeft, vbRight, vb1, vb2, vb3, vb4;
	ScrollPane spl, spr;
	HBox hb, hbBtn;
	
	Menu menu;
	MenuBar mb;
	MenuItem logout;
	
	Label title;
	
	Button buyBtn, clearCartBtn;
	
	File audioFile;
	Media media;
	MediaPlayer mediaplayer;
	
	Alert a;
	
	int idxUser;
	boolean aman;
	
	private void initialize(Vector<Lego> legos, Vector<Users> users, int idx) {
		bp = new BorderPane();
		mb = new MenuBar();
		menu = new Menu("Menu");
		logout = new MenuItem("Logout");
		
		vb1 = new VBox();
		vb2 = new VBox();
		vb3 = new VBox();
		vb4 = new VBox();
		
		gpLeft = new GridPane();
		vbLeft = new VBox();
		vbRight = new VBox();
		
		title = new Label("Your Cart");
		title.setStyle("-fx-font-size: 56px;");
		title.setAlignment(Pos.CENTER);
		
		mb.getMenus().add(menu);
		menu.getItems().addAll(logout);
		bp.setTop(mb);
		
		hbBtn = new HBox();
		
		buyBtn = new Button("Buy");
		clearCartBtn = new Button("Clear Cart");
		buyBtn.setPrefWidth(100);
		clearCartBtn.setPrefWidth(100);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().addAll(buyBtn, clearCartBtn);
		
		if(!users.get(idx).getCart().isEmpty()) {
			for(int i=0; i < users.get(idx).getCart().size(); i++) {
				vbRight.getChildren().add(i, users.get(idx).getCart().get(i).createCatalogueView());
			}
		}
		
		vbRight.getChildren().addAll(title, hbBtn);
		vbRight.setAlignment(Pos.CENTER);
		
		//Scroll Pane kiri kanan
		spl = new ScrollPane(vbLeft);
		spr = new ScrollPane(vbRight);
		
		spl.setPrefWidth(550);
		spr.setPrefWidth(450);
		
		spl.setPadding(new Insets(20,20,20,20));
		spr.setPadding(new Insets(20,20,20,20));
		spr.setFitToWidth(true);
		
		bp.setLeft(spl);
		bp.setRight(spr);
		
		scene = new Scene(bp, 1000, 700);
	}
	
	private void initAudio() {
		// TODO Auto-generated method stub
		audioFile = new File("lofi2.mp3");
		media = new Media(audioFile.toURI().toString());
		mediaplayer = new MediaPlayer(media);
		mediaplayer.setAutoPlay(true);
	}
	
	private void leftComp(Vector<Lego> legos) {
		vb1 = legos.get(0).createCatalogueView();
		vb2 = legos.get(1).createCatalogueView();
		vb3 = legos.get(2).createCatalogueView();
		vb4 = legos.get(3).createCatalogueView();
		
		gpLeft.add(vb1, 0, 0);
		gpLeft.add(vb2, 1, 0);
		gpLeft.add(vb3, 0, 1);
		gpLeft.add(vb4, 1, 1);
		
		vbLeft.getChildren().add(gpLeft);
	}
	
	private void dragItem(Vector<Users> users, Vector<Lego> legos, int idx) {
		vb1.setOnDragDetected(e ->{
			Dragboard db = vb1.startDragAndDrop(TransferMode.ANY);
			ClipboardContent cc = new ClipboardContent();
			cc.putString(legos.get(0).getName());
			db.setContent(cc);
		});
		
		vb2.setOnDragDetected(e ->{
			Dragboard db = vb1.startDragAndDrop(TransferMode.ANY);
			ClipboardContent cc = new ClipboardContent();
			cc.putString(legos.get(1).getName());
			db.setContent(cc);
		});
		
		vb3.setOnDragDetected(e ->{
			Dragboard db = vb1.startDragAndDrop(TransferMode.ANY);
			ClipboardContent cc = new ClipboardContent();
			cc.putString(legos.get(2).getName());
			db.setContent(cc);
		});
		
		vb4.setOnDragDetected(e ->{
			Dragboard db = vb1.startDragAndDrop(TransferMode.ANY);
			ClipboardContent cc = new ClipboardContent();
			cc.putString(legos.get(3).getName());
			db.setContent(cc);
		});
		
		spr.setOnDragOver(e ->{
			if(e.getDragboard().toString() != null) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			e.consume();
		});
		spr.setOnDragDropped(e ->{
			Dragboard db = e.getDragboard();
			aman = false;
			if(db.hasString()) {
				for(int i = 0; i < legos.size(); i++) {
					if(db.getString().equals(legos.get(i).getName()) &
						users.get(idx).getQuantity().get(i) < legos.get(i).getStock()) {
					
						users.get(idx).getQuantity().set(i, users.get(idx).getQuantity().get(i)+1);
						System.out.println("add " + i);
						aman = true;
					}
//					if(db.getString().equals(legos.get(i).getName()) &
//						users.get(idx).getQuantity().get(i) + 1 > legos.get(i).getStock()) {
//
//						a = new Alert(AlertType.ERROR);
//						a.setContentText("Sorry, we are out of stock for this item");
//						a.show();
//					}
				}
				if(!aman) {
					a = new Alert(AlertType.ERROR);
					a.setContentText("Sorry, we are out of stock for this item");
					a.show();
				}
				if(aman) {
					for(int i=0; i < legos.size(); i++) {
						if(legos.get(i).getName().equals(db.getString())) {
//							System.out.println("Step1 ini i-" + i);
							users.get(idx).getCart().add(legos.get(i));
						}
					}
//					System.out.println("123");
					vbRight.getChildren().clear();
					
					
					for(int i=0; i < users.get(idx).getCart().size(); i++) {
//						System.out.println("step2");
						vbRight.getChildren().add(i,users.get(idx).getCart().get(i).createCatalogueView());
					}
					vbRight.getChildren().add(hbBtn);
					e.setDropCompleted(true);
				}
				e.consume();
					
				}
		});
	}
	
	public void clickBtn(Vector<Users> users, Vector<Lego> legos, int idx) {
		logout.setOnAction(e -> {
			new Login(stage, users, legos);
			mediaplayer.stop();
		});
		buyBtn.setOnMouseClicked(e ->{
			vbRight.getChildren().clear();
			vbRight.getChildren().addAll(title, hbBtn);
			
			for(int i = 0;i < legos.size(); i++) {
				legos.get(i).setStock(legos.get(i).getStock() - users.get(idx).getQuantity().get(i));
//				System.out.println(legos.get(i).getStock()+ "-<stock -> user quantity"+ users.get(idx).getQuantity().get(i));
			}
			
			vbLeft.getChildren().clear();
			gpLeft.getChildren().clear();
			leftComp(legos);
			dragItem(users, legos, idx);
			
			users.get(idx).getCart().removeAllElements();
			users.get(idx).getQuantity().set(0, 0);
			users.get(idx).getQuantity().set(1, 0);
			users.get(idx).getQuantity().set(2, 0);
			users.get(idx).getQuantity().set(3, 0);
		});
		clearCartBtn.setOnMouseClicked(e ->{
			vbRight.getChildren().clear();
			vbRight.getChildren().addAll(title, hbBtn);
			users.get(idx).getCart().removeAllElements();
			users.get(idx).getQuantity().set(0, 0);
			users.get(idx).getQuantity().set(1, 0);
			users.get(idx).getQuantity().set(2, 0);
			users.get(idx).getQuantity().set(3, 0);
		});
	}

	
	//VIEW IMAGE
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
	

	public Catalogue(Stage stage, Vector<Users> users, Vector<Lego> legos, int idx) {
		// TODO Auto-generated constructor stub
		System.out.println(idx + "INDEX USER ACTIVE");
		initialize(legos, users, idx);
		leftComp(legos);
		initAudio();
		clickBtn(users, legos, idx);
		dragItem(users, legos, idx);
		clickImg(users, legos);
		this.stage = stage;
		this.stage.setTitle("USER");
		this.stage.setScene(scene);
		this.stage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
		this.stage.show();
	}

}
