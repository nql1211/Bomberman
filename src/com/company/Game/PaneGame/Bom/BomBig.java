package com.company.Game.PaneGame.Bom;

import com.company.Game.PaneGame.MapGame.InputMap;
import com.company.Game.SoundGame;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;

public class BomBig extends Bom{
    private int widthTrai,widthPhai,widthTren,widthDuoi;

    public BomBig(int x, int y) {
        super(x, y);
    }
    // kiem tra xem bom nổ xong chưa để remove khỏi list bom trong show game
    public boolean BomNo(Graphics2D g, JPanel panel){
        if(frameBom == 11){
            //vẽ trung tam
            g.drawImage(imageBomNo[0],ToaDoX(),ToaDoY(),panel);
            new SoundGame("Bom").start(); //  âm thanh
        }else {
            int a;
            // vẽ ảnh bé khi frame < 13
            if(frameBom <=13){
                a = 0;
            }else {
                a = 1;
            }
            g.drawImage(imageBomNo[a], ToaDoX(), ToaDoY(), panel);
            //trai
            if(com.company.Game.PaneGame.MapGame.InputMap.Map[Vitri.y+1][Vitri.x - 1] != libarary.Tuong ) {
                // tính độ dài của bom bên trai
                widthTrai = 1;
                if(InputMap.Map[Vitri.y+1][Vitri.x - 1] == libarary.DiChuyen && InputMap.Map[Vitri.y+1][Vitri.x-4] != libarary.Tuong){
                    widthTrai = 2;
                }
                g.drawImage(imageBomNo[a+2].getScaledInstance(libarary.WidthOVuong * widthTrai, libarary.WidthOVuong, Image.SCALE_SMOOTH), ToaDoX() - libarary.WidthOVuong * widthTrai, ToaDoY(), panel); // no trai
            }
            //phai
            if(com.company.Game.PaneGame.MapGame.InputMap.Map[Vitri.y+1][Vitri.x + 3] != libarary.Tuong ) {
                //tinh do dai bom ben phai
                widthPhai = 1;
                if(InputMap.Map[Vitri.y+1][Vitri.x + 3] == libarary.DiChuyen && InputMap.Map[Vitri.y+1][Vitri.x+6] != libarary.Tuong){
                    widthPhai = 2;
                }
                g.drawImage(imageBomNo[a+4].getScaledInstance(libarary.WidthOVuong * widthPhai, libarary.WidthOVuong, Image.SCALE_SMOOTH), ToaDoX() + libarary.WidthOVuong, ToaDoY(), panel);//no phai
            }
            //tren
            if(com.company.Game.PaneGame.MapGame.InputMap.Map[Vitri.y-1][Vitri.x+1] != libarary.Tuong ) {
                widthTren = 1;
                if(InputMap.Map[Vitri.y-1][Vitri.x+1] == libarary.DiChuyen && InputMap.Map[Vitri.y-4][Vitri.x+1] != libarary.Tuong){
                    widthTren = 2;
                }
                g.drawImage(imageBomNo[a+6].getScaledInstance(libarary.WidthOVuong, libarary.WidthOVuong * widthTren, Image.SCALE_SMOOTH), ToaDoX(), ToaDoY() - libarary.WidthOVuong * widthTren, panel); // no tren
            }
            //duoi
            if(InputMap.Map[Vitri.y + 3][Vitri.x+1] != libarary.Tuong ) {
                widthDuoi = 1;
                if(InputMap.Map[Vitri.y+3][Vitri.x+1] == libarary.DiChuyen && InputMap.Map[Vitri.y+6][Vitri.x+1] != libarary.Tuong){
                    widthDuoi = 2;
                }
                g.drawImage(imageBomNo[a+8].getScaledInstance(libarary.WidthOVuong, libarary.WidthOVuong * widthDuoi, Image.SCALE_SMOOTH), ToaDoX(), ToaDoY() + libarary.WidthOVuong, panel); // no duoi
            }
        }
        if(frameBom == 14){
            QuetPhamViBomNo(2);// tiêu diệt mục tiêu xung quanh
            return true;
        }
        return false;
    }
}