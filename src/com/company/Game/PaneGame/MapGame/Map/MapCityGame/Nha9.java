package com.company.Game.PaneGame.MapGame.Map.MapCityGame;

import com.company.LoadImage;
import com.company.Game.PaneGame.MapGame.Chung.OVuong;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;

public class Nha9 extends OVuong {
    private static Image image = LoadImage.load("image/MapGame/Mapcity/nha9.png", libarary.WidthOVuong,libarary.WidthOVuong);
    public Nha9(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(Graphics2D g, JPanel panel) {
        g.drawImage(image, ToaDoX(),ToaDoY(),panel);
    }
}
