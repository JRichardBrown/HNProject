package GooeyBits;

//Handles Images for the GooeyBits API

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GooeyImage extends JLabel{
    private BufferedImage image;

    public GooeyImage () {
        image = null;
    }

    public GooeyImage (String filepath) throws IOException  {
        //read file into BufferedImage
        try {
            image = ImageIO.read(new File(filepath));
        } 
        catch (IOException e) {
            throw e;
        }

        //create a resized-version of BufferedImage that fits the GooeyImage area
        Image temp = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);

        //Set the GooeyImage icon
        this.setIcon(new ImageIcon(temp));
    }

    public void setImage(String filepath) throws IOException {
         //read file into BufferedImage
         try {
            image = ImageIO.read(new File(filepath));
        } 
        catch (IOException e) {
            throw e;
        }

        //create a resized-version of BufferedImage that fits the GooeyImage area
        Image temp = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);

        //Set the GooeyImage icon
        this.setIcon(new ImageIcon(temp));
    }
}
