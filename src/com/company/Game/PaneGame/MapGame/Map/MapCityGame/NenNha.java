package com.company.Game.PaneGame.MapGame.Map.MapCityGame;

import com.company.LoadImage;
import com.company.Game.PaneGame.MapGame.Chung.OVuong;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;

public class NenNha extends OVuong {
    private static Image image = LoadImage.load("Image/mapgame/mapcity/nennha.png", libarary.WidthOVuong,libarary.WidthOVuong);
    public NenNha(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(Graphics2D g, JPanel panel) {
        g.drawImage(image,ToaDoX(),ToaDoY(),panel);
    }
}
