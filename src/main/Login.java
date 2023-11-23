package main;

import java.util.Vector;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Login {

	Stage stage;
	Scene scene;
	GridPane gp;
	VBox vbUtama;
	Label email, password, legho;
	TextField emailtf;
	PasswordField passwordpf;
	HBox btnHBox, titleHBox;
	Button registerBtn, loginBtn;
	Alert a;
	int idx = 0;
	boolean search;
	
	private void initialize(Vector<Lego> legos) {
		email = new Label("Email");
		password = new Label("Password");
		legho = new Label("Legho");
		legho.setTextAlignment(TextAlignment.CENTER);
		
		emailtf = new TextField();
		passwordpf = new PasswordField();
		
		emailtf.setPrefHeight(40);
		passwordpf.setPrefHeight(40);
		
		registerBtn = new Button("Register");
		loginBtn = new Button("Login");
		
		
		btnHBox = new HBox();
		btnHBox.getChildren().addAll(registerBtn, loginBtn);
		btnHBox.setSpacing(10);
		
		titleHBox = new HBox();
		titleHBox.getChildren().add(legho);
		titleHBox.setAlignment(Pos.CENTER);
		
		gp = new GridPane();
		gp.add(email, 0, 1);
		gp.add(password, 0, 2);
		gp.add(emailtf, 1, 1);
		gp.add(passwordpf, 1, 2);
		gp.add(btnHBox, 1, 3);
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		
		vbUtama = new VBox();
		vbUtama.setSpacing(20);
		vbUtama.setAlignment(Pos.CENTER);
		vbUtama.getChildren().addAll(titleHBox, gp);
		
		legho.setStyle("-fx-font-size: 42px;"
				+ "-fx-font-weight: bold;");
		
		email.setStyle("-fx-font-size: 24px");
		password.setStyle("-fx-font-size: 24px");
		
		loginBtn.setStyle("-fx-background-color: #555555;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 18px;"
				+ "-fx-min-width: 150px;");
		registerBtn.setStyle("-fx-background-color: #555555;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 18px;"
				+ "-fx-min-width: 150px;");
		
		scene = new Scene(vbUtama, 700, 500);
	}
	

	private void clickBtn(Vector<Users> users, Vector<Lego> legos) {
		loginBtn.setOnMouseClicked(e->{
			for (Users i : users) {
				if(i.getEmail().equals(emailtf.getText().toString()) & i.getPassword().equals(passwordpf.getText().toString())) {
					if(!emailtf.getText().toString().equals("admin")) {
						users.get(idx).setActive(true);
					}
					search = true;
					break;
				}else {
					search = false;
				}

				idx++;
			}
			if(emailtf.getText().toString().isBlank() || passwordpf.getText().toString().isBlank() || search == false) {
				a = new Alert(AlertType.ERROR);
				a.setContentText("Please make sure that your email and password are correct");
				a.show();
			}else if(emailtf.getText().toString().equals("admin")
					& passwordpf.getText().toString().equals("admin")){
				new Admin(stage, users, legos);
			}else {
				new Welcome(stage, users, legos, idx);
			}
		});
		
		registerBtn.setOnMouseClicked(e->{
			new Register(stage, users, legos);
		});
	}


	public Login(Stage stage, Vector<Users> users, Vector<Lego> legos) {
		// TODO Auto-generated method stub
		idx = 0;
		initialize(legos);
		clickBtn(users, legos);
		this.stage = stage;
		this.stage.setTitle("Login");
		this.stage.setScene(scene);
		this.stage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
		this.stage.show();
	}
}
