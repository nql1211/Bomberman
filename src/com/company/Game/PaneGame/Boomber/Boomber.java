package com.company.Game.PaneGame.Boomber;

import com.company.Animation;
import com.company.Game.SoundGame;
import com.company.GameBoom;
import com.company.Game.PaneGame.MapGame.InputMap;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Boomber extends Animation implements KeyListener {
    private libarary.ToaDo ViTri = new libarary.ToaDo();
    protected Image[] image = new Image[12];
    protected Image dungIM;
    protected Image Die;
    private boolean DatBom = false;
    private int numberframe = 0;
    private libarary.TrangThai TT = libarary.TrangThai.dungIm;
    private int soBom = 1;
    private boolean flames = false; // do rong bom no;
    private static int SpeedBoomber = 50;
    private boolean BoomberDie = false;
    private int loadAfterDie = 0;
    public Boomber(int x, int y) {
        ViTri.x = x;
        ViTri.y = y;
        DatViTriMoi();
    }
    public void drawBoomber(Graphics2D g, JPanel panel) {
        checkItem();
        if(CheckDie()){
            AnimationDie(g,panel);
            new SoundGame("die").start();
            loadAfterDie++;
            if(loadAfterDie == 20) {
                BoomberDie = true;
                loadAfterDie = 0;
            }
        }else {
            if (TT == libarary.TrangThai.dungIm) {
                DatViTriMoi();
                g.drawImage(dungIM, ToaDoX(), ToaDoY(), panel);
            }
            if (TT == libarary.TrangThai.len) {
                AnimationUp(g, panel);
            }
            if (TT == libarary.TrangThai.xuong) {
                AnimationDown(g, panel);
            }
            if (TT == libarary.TrangThai.trai) {
                AnimationLeft(g, panel);
            }
            if (TT == libarary.TrangThai.phai) {
                AnimationRight(g, panel);
            }
        }
    }

    public void checkItem(){
        for(int i = ViTri.y; i <= ViTri.y + 2; i++)
            for(int j = ViTri.x ; j <= ViTri.x + 2 ; j++){
                if(InputMap.Map[i][j] == libarary.powerup_bombs){
                    soBom = 2;
                }
                if(InputMap.Map[i][j] == libarary.powerup_flames){
                    flames = true;
                }
                if(InputMap.Map[i][j] == libarary.powerup_speed){
                    if(SpeedBoomber < 100)
                        SpeedBoomber +=25;
                }
            }
    }

    public void XoaViTriCu() { // xoa vi tri cu tren mang;
        InputMap.Map[ViTri.y][ViTri.x] = InputMap.Map[ViTri.y][ViTri.x + 1] = InputMap.Map[ViTri.y][ViTri.x + 2] = libarary.DiChuyen;
        InputMap.Map[ViTri.y + 1][ViTri.x] = InputMap.Map[ViTri.y + 1][ViTri.x + 1] = InputMap.Map[ViTri.y + 1][ViTri.x + 2] = libarary.DiChuyen;
        InputMap.Map[ViTri.y + 2][ViTri.x] = InputMap.Map[ViTri.y + 2][ViTri.x + 1] = InputMap.Map[ViTri.y + 2][ViTri.x + 2] = libarary.DiChuyen;
    }

    public void DatViTriMoi() { // dat vi tri moi len mang
        InputMap.Map[ViTri.y][ViTri.x] = InputMap.Map[ViTri.y][ViTri.x + 1] = InputMap.Map[ViTri.y][ViTri.x + 2] = libarary.Boomber;
        InputMap.Map[ViTri.y + 1][ViTri.x] = InputMap.Map[ViTri.y + 1][ViTri.x + 1] = InputMap.Map[ViTri.y + 1][ViTri.x + 2] = libarary.Boomber;
        InputMap.Map[ViTri.y + 2][ViTri.x] = InputMap.Map[ViTri.y + 2][ViTri.x + 1] = InputMap.Map[ViTri.y + 2][ViTri.x + 2] = libarary.Boomber;
    }
    public void ReloadBoomber(int x,int y){
        soBom = 1;
        flames = false;
        SpeedBoomber = 50;
        ViTri.x = x; ViTri.y = y;
        DatViTriMoi();
        GameBoom.getMainFrame().addKeyListener(this);
        TT = libarary.TrangThai.dungIm;
        numberframe = 0;
        DatBom = false;
        BoomberDie = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37) { // sang trai
            TT = libarary.TrangThai.trai;
        }else
        if (e.getKeyCode() == 38) { // len tren
            TT = libarary.TrangThai.len;
        }else
        if (e.getKeyCode() == 39) {// sang phai
            TT = libarary.TrangThai.phai;
        }else
        if (e.getKeyCode() == 40) {//xuong duoi
            TT = libarary.TrangThai.xuong;
        }
        if (e.getKeyCode() == 32) {
            DatBom = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 40) {
            dungIM = image[0];
            TT = libarary.TrangThai.dungIm;
            numberframe = 0;
        }else
        if (e.getKeyCode() == 38) {
            dungIM = image[3];
            TT = libarary.TrangThai.dungIm;
            numberframe = 0;
        }else
        if (e.getKeyCode() == 37) {
            dungIM = image[6];
            TT = libarary.TrangThai.dungIm;
            numberframe = 0;
        }else
        if (e.getKeyCode() == 39) {
            dungIM = image[9];
            TT = libarary.TrangThai.dungIm;
            numberframe = 0;
        }
    }

    @Override
    public void AnimationUp(Graphics2D g, JPanel panel) {
        if (ViTri.y > 3 && CheckVitri(InputMap.Map[ViTri.y - 1][ViTri.x + 1])) {
            if(CheckVitri2(InputMap.Map[ViTri.y - 1][ViTri.x + 1],InputMap.Map[ViTri.y - 2][ViTri.x + 1])) {
                XoaViTriCu();
                if (!CheckVitri(InputMap.Map[ViTri.y-1][ViTri.x + 2])|| (InputMap.Map[ViTri.y - 1][ViTri.x + 2] == libarary.Bom && InputMap.Map[ViTri.y-1][ViTri.x + 1] != libarary.Bom)) {
                    ViTri.x--;
                } else {
                    if (!CheckVitri(InputMap.Map[ViTri.y-1][ViTri.x]) || (InputMap.Map[ViTri.y - 1][ViTri.x] == libarary.Bom && InputMap.Map[ViTri.y-1][ViTri.x + 1] != libarary.Bom)) {
                        ViTri.x++;
                    }
                }
                ViTri.y--;
                DatViTriMoi();
            }
        }
        g.drawImage(image[numberframe % 2 + 4], ToaDoX(), ToaDoY(), panel);
        numberframe++;
    }

    @Override
    public void AnimationDown(Graphics2D g, JPanel panel) {
        if (ViTri.y < libarary.soHang - 6 && CheckVitri(InputMap.Map[ViTri.y + 3][ViTri.x + 1])) {
            if(CheckVitri2(InputMap.Map[ViTri.y + 3][ViTri.x + 1],InputMap.Map[ViTri.y + 4][ViTri.x + 1])) {
                XoaViTriCu();
                if (!CheckVitri(InputMap.Map[ViTri.y+3][ViTri.x+2]) || (InputMap.Map[ViTri.y+3][ViTri.x+2] == libarary.Bom && InputMap.Map[ViTri.y+3][ViTri.x + 1] != libarary.Bom)) {
                    ViTri.x--;
                } else {
                    if (!CheckVitri(InputMap.Map[ViTri.y+3][ViTri.x]) || (InputMap.Map[ViTri.y +3][ViTri.x] == libarary.Bom && InputMap.Map[ViTri.y+3][ViTri.x + 1] != libarary.Bom)) {
                        ViTri.x++;
                    }
                }
                ViTri.y++;
                DatViTriMoi();
            }
        }
        g.drawImage(image[numberframe % 2 + 1], ToaDoX(), ToaDoY(), panel);
        numberframe++;
    }

    @Override
    public void AnimationLeft(Graphics2D g, JPanel panel) {
        if (ViTri.x > 3 && CheckVitri(InputMap.Map[ViTri.y + 1][ViTri.x - 1])) {
            if(CheckVitri2(InputMap.Map[ViTri.y + 1][ViTri.x - 1],InputMap.Map[ViTri.y + 1][ViTri.x - 2])) {
                XoaViTriCu();
                if (!CheckVitri(InputMap.Map[ViTri.y][ViTri.x - 1]) || (InputMap.Map[ViTri.y][ViTri.x - 1] == libarary.Bom && InputMap.Map[ViTri.y+1][ViTri.x - 1] != libarary.Bom)) {
                    ViTri.y++;
                } else {
                    if (!CheckVitri(InputMap.Map[ViTri.y + 2][ViTri.x - 1]) || (InputMap.Map[ViTri.y + 2][ViTri.x - 1] == libarary.Bom && InputMap.Map[ViTri.y + 1][ViTri.x - 1]!= libarary.Bom)) {
                        ViTri.y--;
                    }
                }
                ViTri.x--;
                DatViTriMoi();
            }
        }
        g.drawImage(image[numberframe % 2 + 7], ToaDoX(), ToaDoY(), panel);
        numberframe++;

    }

    @Override
    public void AnimationRight(Graphics2D g, JPanel panel) {
        if (ViTri.x < libarary.soCot - 6 && CheckVitri(InputMap.Map[ViTri.y + 1][ViTri.x + 3])) {
            if(CheckVitri2(InputMap.Map[ViTri.y + 1][ViTri.x + 3],InputMap.Map[ViTri.y + 1][ViTri.x + 4])) {
                XoaViTriCu();
                if (!CheckVitri(InputMap.Map[ViTri.y][ViTri.x + 3]) || (InputMap.Map[ViTri.y][ViTri.x + 3] == libarary.Bom && InputMap.Map[ViTri.y+1][ViTri.x + 3] != libarary.Bom)) {
                    ViTri.y++;
                } else {
                    if (!CheckVitri(InputMap.Map[ViTri.y+2][ViTri.x + 3]) || (InputMap.Map[ViTri.y + 2][ViTri.x + 3] == libarary.Bom && InputMap.Map[ViTri.y+1][ViTri.x + 3] != libarary.Bom)) {
                        ViTri.y--;
                    }
                }
                ViTri.x++;
                DatViTriMoi();
            }
        }
        g.drawImage(image[numberframe % 2 + 10], ToaDoX(), ToaDoY(), panel);
        numberframe++;
    }
    @Override
    public void AnimationDie(Graphics2D g, JPanel panel) {
        g.drawImage(Die, ToaDoX(),ToaDoY(),panel);
    }

    private boolean CheckDie(){
        for(int i = ViTri.y; i <= ViTri.y + 2; i++)
            for(int j = ViTri.x ; j <= ViTri.x + 2 ; j++){
                if(InputMap.Map[i][j] == libarary.BomNo || InputMap.Map[i][j] == libarary.Boss || InputMap.Map[i][j] == libarary.DiChuyen){
                    GameBoom.getMainFrame().removeKeyListener(this);
                    return true;
                }
            }
        return false;
    }
    public boolean CheckVitri(char key){
        if(key != libarary.Tuong && key != libarary.Gach){
            return true;
        }
        return false;
    }
    public boolean CheckVitri2(char key,char key2){// neu o dang xét là bom nhung vi tri tiep theo khac boom thì di dc
        if(key != libarary.Bom || (key == libarary.Bom && CheckVitri(key2) && key2 != libarary.Bom) ){
            return true;
        }
        return false;
    }
    public boolean getBoomberDie(){ return  BoomberDie;}
    public libarary.ToaDo getViTri() {
        return ViTri;
    }
    public int getSoBom() { return soBom; }
    public boolean isFlames() { return flames; }
    public boolean getDatBom() {
        return DatBom;
    }
    public void ResetDatBom() {
        DatBom = false;
    }
    public KeyListener GetKeyListener(){ return (KeyListener) this; }
    public int ToaDoX() {
        return ViTri.x * libarary.WidthOVuong/3;
    }
    public int ToaDoY() {
        return ViTri.y * libarary.WidthOVuong/3;
    }
    public static int getSpeedBoomber(){
        return SpeedBoomber;
    }
}
