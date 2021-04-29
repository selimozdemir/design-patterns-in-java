package com.company.behavioral.state;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

class ImageLoader implements Icon {
    private Icon icon;
    private URL imageUrl;
    private boolean loading;

    public ImageLoader(String url) throws MalformedURLException {
        this.icon = new ImageLoading(this);
        this.imageUrl = new URL(url);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        this.icon.paintIcon(c,g,x,y);
    }

    @Override
    public int getIconWidth() {
        return this.icon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return this.icon.getIconHeight();
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public boolean isLoading() {
        return loading;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    void loadImage(final Component c, Graphics g, int x, int y){
        if(!this.isLoading()){
            Thread t = new Thread(() -> {

               this.icon = new ImageIcon(this.imageUrl,"XYZ");
                c.repaint();
            });
            t.start();
        }
    }
}

class ImageLoading implements Icon {

    ImageLoader loader;

    public ImageLoading(ImageLoader loader) {
        this.loader = loader;

    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawString("Image is loading...", x+300, y+300);
        this.loader.loadImage(c,g,x,y);
    }

    @Override
    public int getIconWidth() {
        return 800;
    }

    @Override
    public int getIconHeight() {
        return 600;
    }
}

class ImageLoaded implements Icon {

    ImageLoader loader;

    public ImageLoaded(ImageLoader loader) {
        this.loader = loader;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        this.loader.setIcon();
    }

    @Override
    public int getIconWidth() {
        return this.loader.getIcon().getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return this.loader.getIcon().getIconHeight();
    }
}

public class ProxyExample {

    public static void main(String[] args) {

    }
}
