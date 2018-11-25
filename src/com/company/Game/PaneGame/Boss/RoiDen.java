package com.company.Game.PaneGame.Boss;

import com.company.LoadImage;
import com.company.libarary;

public class RoiDen extends BossGame {
    public RoiDen(int x, int y){
        super(x,y);
        image[0] = LoadImage.load("image/boss/RoiDen/len1.png", libarary.WidthOVuong,libarary.WidthOVuong);
        image[1] = LoadImage.load("image/boss/RoiDen/len2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[2] = LoadImage.load("image/boss/RoiDen/len3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[3] = LoadImage.load("image/boss/RoiDen/xuong1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[4] = LoadImage.load("image/boss/RoiDen/xuong2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[5] = LoadImage.load("image/boss/RoiDen/xuong3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[6] = LoadImage.load("image/boss/RoiDen/trai1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[7] = LoadImage.load("image/boss/RoiDen/trai2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[8] = LoadImage.load("image/boss/RoiDen/trai3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[9] = LoadImage.load("image/boss/RoiDen/phai1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[10] = LoadImage.load("image/boss/RoiDen/phai2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[11] = LoadImage.load("image/boss/RoiDen/phai3.png",libarary.WidthOVuong,libarary.WidthOVuong);
    }
}
