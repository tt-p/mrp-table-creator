package Utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResourceOp {

    public static Image setImage(String path) {
        return new Image(ClassLoader.getSystemClassLoader().getResource(path).toString());
    }

    public static ImageView setImageView(String path, double width, double height) {

        ImageView imgView = new ImageView(new Image(ClassLoader.getSystemClassLoader().getResource(path).toString()));

        imgView.setFitWidth(width);
        imgView.setFitHeight(height);
        return imgView;
    }

}