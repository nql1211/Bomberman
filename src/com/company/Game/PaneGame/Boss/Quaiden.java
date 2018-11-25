package com.company.Game.PaneGame.Boss;

import com.company.LoadImage;
import com.company.libarary;

public class Quaiden extends BossGame {
    public Quaiden(int x, int y){
        super(x,y);
        Speed = 2;
        image[0] = LoadImage.load("image/boss/Quaiden/len1.png", libarary.WidthOVuong,libarary.WidthOVuong);
        image[1] = LoadImage.load("image/boss/QuaiDen/len2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[2] = LoadImage.load("image/boss/QuaiDen/len3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[3] = LoadImage.load("image/boss/QuaiDen/xuong1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[4] = LoadImage.load("image/boss/QuaiDen/xuong2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[5] = LoadImage.load("image/boss/QuaiDen/xuong3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[6] = LoadImage.load("image/boss/QuaiDen/trai1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[7] = LoadImage.load("image/boss/QuaiDen/trai2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[8] = LoadImage.load("image/boss/QuaiDen/trai3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[9] = LoadImage.load("image/boss/QuaiDen/phai1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[10] = LoadImage.load("image/boss/QuaiDen/phai2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[11] = LoadImage.load("image/boss/QuaiDen/phai3.png",libarary.WidthOVuong,libarary.WidthOVuong);
    }
}
