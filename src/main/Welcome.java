package main;

import java.io.File;
import java.util.Vector;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Welcome {
	Scene scene;
	Stage stage;
	Label welcome;
	BorderPane bp;
	VBox vb;
	Button continueBtn;
	File file;
	Media media;
	MediaPlayer mediaplayer;
	MediaView mediaview;
	
	Menu menu;
	MenuBar mb;
	MenuItem viewlogo, logout;
	
	private void initializeVideo() {
		file = new File("lego.mp4");
		media = new Media(file.toURI().toString());
		mediaplayer = new MediaPlayer(media);
		mediaview = new MediaView(mediaplayer);
		mediaplayer.setAutoPlay(true);
		
		mediaview.setFitWidth(300);
		mediaview.setFitHeight(200);
	}
	
	private void initialize() {
		welcome = new Label("Welcome To LEGHO!");
		continueBtn = new Button("CONTINUE >>");
		bp = new BorderPane();
		vb = new VBox();
		mb = new MenuBar();
		menu = new Menu("Menu");
		logout = new MenuItem("Logout");
		viewlogo = new MenuItem("View Logo");
		
		mb.getMenus().add(menu);
		menu.getItems().addAll(viewlogo, logout);
		bp.setTop(mb);
		
		vb.getChildren().addAll(welcome, mediaview, continueBtn);
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(vb);
		vb.setSpacing(15);
		
		scene = new Scene(bp, 1000, 700);
		
		continueBtn.setStyle("-fx-background-color: #555555;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 18px;"
				+ "-fx-min-width: 150px;");
	}
	
	public void clickBtn(Vector<Users> users, Vector<Lego> legos, int idx) {
		continueBtn.setOnMouseClicked(e -> {
			new Catalogue(stage, users, legos, idx);
			mediaplayer.stop();
		});
		logout.setOnAction(e -> {
			new Login(stage, users, legos);
			mediaplayer.stop();
		});
		viewlogo.setOnAction(e -> {
			new Catalogue(stage, users, legos, idx);
			mediaplayer.stop();
		});
		
	}
	
	public Welcome(Stage stage, Vector<Users> users, Vector<Lego> legos, int idx) {
		// TODO Auto-generated constructor stub
		System.out.println("Welcome: user ke-" + idx);
		initializeVideo();
		initialize();
		clickBtn(users, legos, idx);
		this.stage = stage;
		this.stage.setTitle("USER");
		this.stage.setScene(scene);
		this.stage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
		this.stage.show();
	}

}
