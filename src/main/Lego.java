package main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Lego {
    private Image image;
    private String name, desc;
    private Long price;
    private Integer stock;
    private TextField nametf, pricetf, stocktf, desctf;
    private Button update;
    private ImageView imageView;
    

    public TextField getNametf() {
		return nametf;
	}

	public void setNametf(TextField nametf) {
		this.nametf = nametf;
	}

	public TextField getPricetf() {
		return pricetf;
	}

	public void setPricetf(TextField pricetf) {
		this.pricetf = pricetf;
	}

	public TextField getStocktf() {
		return stocktf;
	}

	public void setStocktf(TextField stocktf) {
		this.stocktf = stocktf;
	}

	public TextField getDesctf() {
		return desctf;
	}

	public void setDesctf(TextField desctf) {
		this.desctf = desctf;
	}

	public Button getUpdate() {
		return update;
	}

	public void setUpdate(Button update) {
		this.update = update;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Lego(Image image, String name, String desc, long price, int stock) {
		super();
		this.image = image;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.stock = stock;
	}

    public GridPane createLegoView() {
    	GridPane legoPane = new GridPane();
        HBox legohb;
        Label nameLabel, priceLabel, stockLabel, descLabel;
        
        
        
        nameLabel = new Label("Name: ");
        priceLabel = new Label("Price: ");
        stockLabel = new Label("Stock: ");
        descLabel = new Label("Description: ");
        
        nametf = new TextField(this.getName());
        nametf.setDisable(true);;
        
        pricetf = new TextField(this.getPrice().toString());
        stocktf = new TextField(this.getStock().toString());
        desctf = new TextField(this.getDesc());
        desctf.setMaxWidth(600);
        
        legohb = new HBox();
        
        update = new Button("Update");
        
        imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);
        
        
        legohb.getChildren().addAll(nametf, priceLabel, pricetf, stockLabel);
        legohb.setSpacing(10);
        legohb.setAlignment(Pos.CENTER);
        
        
        
        legoPane.add(imageView, 0, 0);
        legoPane.add(nameLabel, 1, 0);
        legoPane.add(legohb, 2, 0);
        legoPane.add(stocktf, 3, 0);
        legoPane.add(descLabel, 1, 1);
        legoPane.add(desctf, 2, 1);
        legoPane.add(update, 2, 2);
        
        legoPane.setAlignment(Pos.CENTER);
        return legoPane;
    }
    
    public VBox createCatalogueView() {
    	VBox vb = new VBox();
    	Label nameLabel, priceLabel, stockLabel, descLabel;
    	
    	nameLabel = new Label("Name: " + this.getName().toString());
    	priceLabel = new Label("Price: " + this.getPrice().toString());
    	stockLabel = new Label("Stock: " + this.getStock().toString());
    	descLabel = new Label("Description: " + this.getDesc().toString());
    	descLabel.setWrapText(true);
    	
    	imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);
        
        vb.setMaxWidth(250);
        vb.getChildren().addAll(imageView, nameLabel, priceLabel, stockLabel, descLabel);
        
    	return vb;
    }
}