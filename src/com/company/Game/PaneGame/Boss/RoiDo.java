package com.company.Game.PaneGame.Boss;

import com.company.LoadImage;
import com.company.libarary;

public class RoiDo extends BossGame {
    public RoiDo(int x, int y){
        super(x,y);
        image[0] = LoadImage.load("image/boss/RoiDo/len1.png", libarary.WidthOVuong,libarary.WidthOVuong);
        image[1] = LoadImage.load("image/boss/RoiDo/len2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[2] = LoadImage.load("image/boss/RoiDo/len3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[3] = LoadImage.load("image/boss/RoiDo/xuong1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[4] = LoadImage.load("image/boss/RoiDo/xuong2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[5] = LoadImage.load("image/boss/RoiDo/xuong3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[6] = LoadImage.load("image/boss/RoiDo/trai1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[7] = LoadImage.load("image/boss/RoiDo/trai2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[8] = LoadImage.load("image/boss/RoiDo/trai3.png",libarary.WidthOVuong,libarary.WidthOVuong);

        image[9] = LoadImage.load("image/boss/RoiDo/phai1.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[10] = LoadImage.load("image/boss/RoiDo/phai2.png",libarary.WidthOVuong,libarary.WidthOVuong);
        image[11] = LoadImage.load("image/boss/RoiDo/phai3.png",libarary.WidthOVuong,libarary.WidthOVuong);
    }
}
