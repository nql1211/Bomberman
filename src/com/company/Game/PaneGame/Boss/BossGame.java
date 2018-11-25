package com.company.Game.PaneGame.Boss;

import com.company.Animation;
import com.company.Game.PaneGame.MapGame.InputMap;
import com.company.Game.SoundGame;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BossGame extends Animation {
    private libarary.ToaDo ViTri = new libarary.ToaDo();
    protected  Image[] image = new Image[12];
    private int numberframe = 0;
    private libarary.TrangThai TT;
    protected int Speed = 1; //cang lon cang cham
    private  int soNhip = 0;
    private boolean TimDuong = false;
    public BossGame(int x, int y){
        ViTri.x = x;
        ViTri.y = y;
        TT = libarary.TrangThai.xuong;
    }
    public void drawBoss(Graphics2D g, JPanel panel) {
        if (TT == libarary.TrangThai.len) {
            AnimationUp(g, panel);
        }else
        if (TT == libarary.TrangThai.xuong) {
            AnimationDown(g, panel);
        }else
        if (TT == libarary.TrangThai.trai) {
            AnimationLeft(g, panel);
        }else
        if (TT == libarary.TrangThai.phai) {
            AnimationRight(g, panel);
        }
        soNhip++;
        if(soNhip == 10000){
            soNhip = 0;
        }
    }
    public boolean CheckDie(){
        for(int i = ViTri.y; i <= ViTri.y + 2; i++)
            for(int j = ViTri.x ; j <= ViTri.x + 2 ; j++){
                if(InputMap.Map[i][j] == libarary.BomNo){
                    XoaViTriCu();
                    new SoundGame("die").start();
                    return true;
                }
            }
        return false;
    }
    protected void TimDuong(int soLan){
        if(soLan == 5)  return;
        Random random = new Random();
        int a = random.nextInt(100);
        numberframe = 0;
        if(a < 25){ //len
            if (ViTri.y > 2 && CheckVitri(InputMap.Map[ViTri.y - 1][ViTri.x + 1])
                    && CheckVitri(InputMap.Map[ViTri.y - 1][ViTri.x])
                    && CheckVitri(InputMap.Map[ViTri.y - 1][ViTri.x + 2])) {
                TT = libarary.TrangThai.len;
            }
            else{
                TimDuong(soLan+1);
            }
        }else if(a < 50){//xuong
            if (ViTri.y < libarary.soHang - 6 && CheckVitri(InputMap.Map[ViTri.y + 3][ViTri.x + 1])
                    && CheckVitri(InputMap.Map[ViTri.y + 3][ViTri.x])
                    && CheckVitri(InputMap.Map[ViTri.y + 3][ViTri.x + 2])) {
                TT = libarary.TrangThai.xuong;
            }
            else {
                TimDuong(soLan+1);
            }
        }else if(a < 75){// phai
            if (ViTri.x < libarary.soCot - 6 && CheckVitri(InputMap.Map[ViTri.y ][ViTri.x + 3])
                    && CheckVitri(InputMap.Map[ViTri.y + 1][ViTri.x + 3])
                    && CheckVitri(InputMap.Map[ViTri.y + 2][ViTri.x + 3])) {
                TT = libarary.TrangThai.phai;
            }
            else {
                TimDuong(soLan+1);
            }
        }else if(a < 100){ //trai
            if (ViTri.x > 3 &&  CheckVitri(InputMap.Map[ViTri.y ][ViTri.x - 1])
                    &&  CheckVitri(InputMap.Map[ViTri.y + 1][ViTri.x - 1])
                    &&  CheckVitri(InputMap.Map[ViTri.y + 2][ViTri.x - 1])) {
                TT = libarary.TrangThai.trai;
            }
            else {
                TimDuong(soLan+1);
            }
        }
    }
    @Override
    public void AnimationUp(Graphics2D g, JPanel panel) {
        if(soNhip % Speed == 0) {
            if (ViTri.y > 3 && CheckVitri(InputMap.Map[ViTri.y - 1][ViTri.x + 1])
                            && CheckVitri(InputMap.Map[ViTri.y - 1][ViTri.x])
                            && CheckVitri(InputMap.Map[ViTri.y - 1][ViTri.x + 2])) {
                XoaViTriCu();
                ViTri.y--;
                Random random = new Random();
                int a = random.nextInt(100);
                if (a < 15 && ViTri.x % 3 == 0 && ViTri.y % 3 == 0) {
                    TimDuong = true;
                }
            } else {
                TimDuong = true;
            }
        }
        DatViTriMoi();
        g.drawImage(image[numberframe % 3], ToaDoX(), ToaDoY(), panel);
        if(soNhip % Speed == Speed - 1) {
            numberframe++;
            if (TimDuong) {
                TimDuong(1);
                TimDuong = false;
            }
        }
    }

    @Override
    public void AnimationDown(Graphics2D g, JPanel panel) {
        if(soNhip % Speed == 0) {
            if (ViTri.y < libarary.soHang - 6 && CheckVitri(InputMap.Map[ViTri.y + 3][ViTri.x + 1])
                                                && CheckVitri(InputMap.Map[ViTri.y + 3][ViTri.x])
                                                && CheckVitri(InputMap.Map[ViTri.y + 3][ViTri.x + 2])) {
                XoaViTriCu();
                ViTri.y++;
                Random random = new Random();
                int a = random.nextInt(100);
                if (a < 15 && ViTri.x % 3 == 0 && ViTri.y % 3 == 0) {
                    TimDuong = true;
                }
            } else {
                TimDuong = true;
            }
        }
        DatViTriMoi();
        g.drawImage(image[numberframe % 3 + 3], ToaDoX(), ToaDoY(), panel);
        if(soNhip % Speed == Speed - 1) {
            numberframe++;
            if (TimDuong) {
                TimDuong(1);
                TimDuong = false;
            }
        }
    }

    @Override
    public void AnimationLeft(Graphics2D g, JPanel panel) {
        if(soNhip % Speed == 0) {
            if (ViTri.x > 3 &&  CheckVitri(InputMap.Map[ViTri.y ][ViTri.x - 1])
                            &&  CheckVitri(InputMap.Map[ViTri.y + 1][ViTri.x - 1])
                            &&  CheckVitri(InputMap.Map[ViTri.y + 2][ViTri.x - 1])) {
                XoaViTriCu();
                ViTri.x--;
                Random random = new Random();
                int a = random.nextInt(100);
                if (a < 15 && ViTri.x % 3 == 0 && ViTri.y % 3 == 0) {
                    TimDuong = true;
                }
            } else {
                TimDuong = true;
            }
        }
        DatViTriMoi();
        g.drawImage(image[numberframe % 3 + 6], ToaDoX(), ToaDoY(), panel);
        if(soNhip % Speed == Speed - 1) {
            numberframe++;
            if (TimDuong) {
                TimDuong(1);
                TimDuong = false;
            }
        }
    }

    @Override
    public void AnimationRight(Graphics2D g, JPanel panel) {
        if(soNhip % Speed == 0) {
            if (ViTri.x < libarary.soCot - 6 && CheckVitri(InputMap.Map[ViTri.y ][ViTri.x + 3])
                                            && CheckVitri(InputMap.Map[ViTri.y + 1][ViTri.x + 3])
                                            && CheckVitri(InputMap.Map[ViTri.y + 2][ViTri.x + 3])) {
                XoaViTriCu();
                ViTri.x++;
                Random random = new Random();
                int a = random.nextInt(100);
                if (a < 15 && ViTri.x % 3 == 0 && ViTri.y % 3 == 0) {
                    TimDuong = true;
                }
            } else {
                TimDuong = true;
            }

        }
        DatViTriMoi();
        g.drawImage(image[numberframe % 3 + 9], ToaDoX(), ToaDoY(), panel);
        if(soNhip % Speed == Speed - 1) {
            numberframe++;
            if (TimDuong) {
                TimDuong(1);
                TimDuong = false;
            }
        }
    }
    @Override
    public void AnimationDie(Graphics2D g, JPanel panel) {

    }
    public boolean CheckVitri(char key){
        if(key != libarary.Tuong && key != libarary.Gach && key != libarary.Bom){
            return true;
        }
        return false;
    }
    public int ToaDoX() {
        return ViTri.x * libarary.WidthOVuong/3;
    }

    public int ToaDoY() {
        return ViTri.y * libarary.WidthOVuong/3;
    }

    public void XoaViTriCu() { // xoa vi tri cu tren mang;
        com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y][ViTri.x] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y][ViTri.x + 1] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y][ViTri.x + 2] = libarary.DiChuyen;
        com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 1][ViTri.x] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 1][ViTri.x + 1] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 1][ViTri.x + 2] = libarary.DiChuyen;
        com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 2][ViTri.x] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 2][ViTri.x + 1] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 2][ViTri.x + 2] = libarary.DiChuyen;
    }

    public void DatViTriMoi() { // dat vi tri moi len mang
        com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y][ViTri.x] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y][ViTri.x + 1] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y][ViTri.x + 2] = libarary.Boss;
        com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 1][ViTri.x] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 1][ViTri.x + 1] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 1][ViTri.x + 2] = libarary.Boss;
        com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 2][ViTri.x] = com.company.Game.PaneGame.MapGame.InputMap.Map[ViTri.y + 2][ViTri.x + 1] = InputMap.Map[ViTri.y + 2][ViTri.x + 2] = libarary.Boss;
    }
}
