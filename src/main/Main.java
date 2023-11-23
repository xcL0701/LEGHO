package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	Vector<Users> users;
	Vector<Lego> legos;
	
	Users admin;
	Lego lego1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		users = new Vector<Users>();
		admin = new Users("admin", "admin");
		users.add(admin);
		
		FileInputStream  file1 = null;
		FileInputStream  file2 = null;
		FileInputStream  file3 = null;
		FileInputStream  file4 = null;
		try {
			file1 = new FileInputStream("lego5.png");
			file2 = new FileInputStream("lego2.png");
			file3 = new FileInputStream("lego3.png");
			file4 = new FileInputStream("lego4.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		legos = new Vector<Lego>();
		legos.add(new Lego(new Image(file1), "Ironman Hulkbuster",
				"Capture the scale and power of the Hulkbuster MK44 with this 4,049-piece, authentically detailed movable model, standing over 52 cm (20.5 in.) tall",
				2330000, 100));
		legos.add(new Lego(new Image(file2), "Hogwarts Icons - Collectors' Edition",
				"Bring the magical personality and elegant movement of Hedwig, the celebrated snowy owl from the Harry Potter™ movies, to life",
				4550000, 80));
		legos.add(new Lego(new Image(file3), "Mickey Mouse",
				"LEGHO feature the iconic Mickey Mouse",
				1520000, 500));
		legos.add(new Lego(new Image(file4), "Eiffel Tower",
				"Captivate fans of travel, history or architecture with the new LEGHO Eiffel tower",
				8200000, 70));
		
		new Login(stage, users, legos);
	}

}
