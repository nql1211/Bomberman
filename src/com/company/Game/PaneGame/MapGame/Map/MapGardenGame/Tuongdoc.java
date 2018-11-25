package com.company.Game.PaneGame.MapGame.Map.MapGardenGame;

import com.company.LoadImage;
import com.company.Game.PaneGame.MapGame.Chung.OVuong;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;

public class Tuongdoc extends OVuong {
    private static Image image = LoadImage.load("Image/mapGame/MapGarden/canh.png", libarary.WidthOVuong,libarary.WidthOVuong);
    public Tuongdoc(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(Graphics2D g, JPanel panel) {
        g.drawImage(image, ToaDoX(),ToaDoY(),panel);
    }
}
