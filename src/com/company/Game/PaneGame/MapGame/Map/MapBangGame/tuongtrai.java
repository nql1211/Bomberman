package com.company.Game.PaneGame.MapGame.Map.MapBangGame;

import com.company.LoadImage;
import com.company.Game.PaneGame.MapGame.Chung.OVuong;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;

public class tuongtrai extends OVuong {
    private static Image image = LoadImage.load("Image/mapGame/MapBang/canhtrai.png",libarary.WidthOVuong,libarary.WidthOVuong);
    public tuongtrai(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(Graphics2D g, JPanel panel) {
        g.drawImage(image, ToaDoX(),ToaDoY(),panel);
    }
}
