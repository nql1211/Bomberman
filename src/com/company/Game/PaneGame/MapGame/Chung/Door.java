package com.company.Game.PaneGame.MapGame.Chung;

import com.company.LoadImage;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;

public class Door extends OVuong {
    private static Image image = LoadImage.load("image/MapGame/cua1.png", libarary.WidthOVuong,libarary.WidthOVuong);
    private static Image image2 = LoadImage.load("image/MapGame/cua2.png", libarary.WidthOVuong,libarary.WidthOVuong);
    public Door(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(Graphics2D g, JPanel panel) {
        g.drawImage(image,ToaDoX(),ToaDoY(),panel);
    }
}
