package main;

import java.util.Vector;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Register {
	Stage stage;
	Scene scene;
	GridPane gp;
	VBox vbUtama;
	Label email, password, confirmPassword, register;
	TextField emailtf;
	PasswordField passwordpf, confirmPasswordpf;
	HBox btnHBox;
	Button registerBtn, loginBtn, resetBtn;
	Alert a;
	boolean search;
	
	private void initialize() {
		register = new Label("Register");
		email = new Label("Email");
		password = new Label("Password");
		confirmPassword = new Label("Confirm Password");
		
		emailtf = new TextField();
		passwordpf = new PasswordField();
		confirmPasswordpf = new PasswordField();
		
		emailtf.setPrefHeight(40);
		passwordpf.setPrefHeight(40);
		confirmPasswordpf.setPrefHeight(40);
		
		registerBtn = new Button("Register");
		loginBtn = new Button("Login");
		resetBtn = new Button("Reset");
		
		
		btnHBox = new HBox();
		btnHBox.getChildren().addAll(loginBtn, registerBtn, resetBtn);
		btnHBox.setSpacing(10);
		
		
		
		gp = new GridPane();
		gp.add(register, 0, 0);
		gp.add(email, 0, 1);
		gp.add(password, 0, 2);
		gp.add(confirmPassword, 0, 3);
		gp.add(emailtf, 1, 1);
		gp.add(passwordpf, 1, 2);
		gp.add(confirmPasswordpf, 1, 3);
		gp.add(btnHBox, 1, 4);
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		
		
		register.setStyle("-fx-font-size: 42px;"
				+ "-fx-font-weight: bold;");
		
		email.setStyle("-fx-font-size: 24px");
		password.setStyle("-fx-font-size: 24px");
		confirmPassword.setStyle("-fx-font-size: 24px");
		
		loginBtn.setStyle("-fx-background-color: #555555;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 18px;"
				+ "-fx-min-width: 150px;");
		registerBtn.setStyle("-fx-background-color: #555555;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 18px;"
				+ "-fx-min-width: 150px;");
		resetBtn.setStyle("-fx-background-color: #555555;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-size: 18px;"
				+ "-fx-min-width: 150px;");
		
		vbUtama = new VBox();
		vbUtama.setAlignment(Pos.CENTER);
		vbUtama.getChildren().addAll(register, gp);
		
		scene = new Scene(vbUtama, 700, 500);
	}
	
	private void clickBtn(Vector<Users> users, Vector<Lego> legos) {
		loginBtn.setOnMouseClicked(e->{
			new Login(stage, users, legos);
		});
		
		registerBtn.setOnMouseClicked(e->{
			String EMAIL_REGEX = new String("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
			for (Users i : users) {
				if(i.getEmail().equals(emailtf.getText().toString())) {
//					System.out.println(i.getEmail() + i.getPassword());
					search = true;
				}else {
					search = false;
				}
			}
			if(emailtf.getText().toString().isBlank() 
				|| !(emailtf.getText().toString().endsWith("@email.com"))
				|| !emailtf.getText().toString().matches(EMAIL_REGEX)
				|| emailtf.getText().toString().contains(" ")
				|| emailtf.getText().toString().startsWith("@")
				|| search == true
				|| passwordpf.getText().toString().isBlank()
				|| confirmPasswordpf.getText().toString().isBlank()
				|| !(passwordpf.getText().toString().equals(confirmPasswordpf.getText().toString()))) {
				
				a = new Alert(AlertType.ERROR);
				a.setContentText("Please fill username, password, or confirm password");
				a.show();
			}else {
				users.add(new Users(emailtf.getText().toString(), passwordpf.getText().toString()));
				System.out.println(users.get(0).getEmail());
				System.out.println(users.get(1).getEmail());
				System.out.println(users.get(users.size()-1).getEmail());
				new Login(stage, users, legos);
			}
		});
		
		resetBtn.setOnMouseClicked(e->{
			emailtf.clear();
			passwordpf.clear();
			confirmPasswordpf.clear();
		});
	}
	
	public Register(Stage stage, Vector<Users> users, Vector<Lego> legos) {
		// TODO Auto-generated constructor stub
		initialize();
		clickBtn(users, legos);
		this.stage = stage;
		this.stage.setTitle("Register");
		this.stage.setScene(scene);
		this.stage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
		this.stage.show();
	}

}
