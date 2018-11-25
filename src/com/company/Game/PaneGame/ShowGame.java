package com.company.Game.PaneGame;

import com.company.Game.PaneGame.Bom.Bom;
import com.company.Game.PaneGame.Bom.BomBig;
import com.company.Game.PaneGame.Boomber.*;
import com.company.Game.PaneGame.Boss.BossGame;
import com.company.Game.PaneGame.Boss.Quaiden;
import com.company.Game.PaneGame.Boss.RoiDen;
import com.company.Game.PaneGame.Boss.RoiDo;
import com.company.Game.PaneGame.MapGame.InputMap;
import com.company.Game.PaneGame.MapGame.MapGame;
import com.company.GameBoom;
import com.company.LoadImage;
import com.company.Game.SoundGame;
import com.company.libarary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ShowGame extends JPanel {
    private Boomber boomber;
    private MapGame mapGame;
    private ArrayList<Bom> listBom = new ArrayList<>();
    private ArrayList<BossGame> bossGames = new ArrayList<>();
    private int WidthCamera,HeightCamera;
    private Image KhungTG;
    String ThoiGian = "00:00";
    private SoundGame sound;
    public ShowGame(MapGame mapGame,Boomber boomber) {
        this.mapGame = mapGame;
        this.boomber = boomber;
        BuildBoss();
        setBounds(0, 0, libarary.soCot * libarary.WidthOVuong/3, libarary.soHang * libarary.WidthOVuong/3);
        setLayout(null);
        KhungTG = LoadImage.load("image/KhungTG.png",120,50);
        setForeground(new Color(0xF6DF31));
        GameBoom.getMainFrame().addKeyListener(boomber.GetKeyListener());
    }
    @Override
    public void setVisible(boolean check){
        if(check) {
            RestoreGame();
        }
        super.setVisible(check);
        if(check){
            sound = new SoundGame("Nền");
            sound.Remuse();
            sound.start();
        }else {
            sound.Stop();
        }
    }
    // reset lại game
    public void RestoreGame(){
        InputMap.ReloadMap();
        mapGame.RandomVitri();
        mapGame.buildMap();
        listBom.clear();
        BuildBoss();
        boomber.ReloadBoomber(3,3);
        this.setBackground(new Color(0x4D3E3E));
        Camera(WidthCamera,HeightCamera);
    }
    // tính thời gian
    public void setThoiGian(int thoiGian) {//don vi giay
        int soPhut = thoiGian/60;
        int soGiay = thoiGian%60;
        if(soPhut < 10){
            ThoiGian = "0" + soPhut + ":";
        }else {
            ThoiGian = soPhut + ":";
        }
        if(soGiay < 10){
            ThoiGian = ThoiGian + "0" + soGiay;
        }else {
            ThoiGian = ThoiGian + soGiay;
        }
    }
    // kiem tra game thang hay thua
    public libarary.Game checkGame(){
        if(bossGames.size() == 0 && boomber.getViTri().x == mapGame.getVitriDoor().x
                && boomber.getViTri().y == mapGame.getVitriDoor().y){
            return libarary.Game.win;
        }else{
            if(boomber.getBoomberDie()){
                return libarary.Game.lose;//game thua
            }else {
                return libarary.Game.play;// game vân đang chơi
            }
        }
    }
    public void setBoomber(Boomber boomber){
        this.boomber = boomber;
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D gd2 = (Graphics2D) g;
        super.paint(gd2);
        //vẽ map
        mapGame.drawMap(gd2,this);
        //vẽ bom trong trạng thái chưa nổ
        for(Bom bom: listBom){
            bom.drawBom(gd2,this);
        }
        // dat bom
        if(boomber.getDatBom()){
            if(listBom.size() < boomber.getSoBom()) {
                if(boomber.isFlames()) {// kiem tra xem bom của người chơi đang là loại nào để thêm vào list
                    listBom.add(new BomBig(boomber.getViTri().x, boomber.getViTri().y));
                }else {
                    listBom.add(new Bom(boomber.getViTri().x, boomber.getViTri().y));
                }
                if(listBom.size() >= 2) {// kiem tra xem bom dat có trùng nhau
                    for(int i = 0; i < listBom.size() - 1 ; i++) {
                        if (listBom.get(listBom.size() -1).getVitri().x == listBom.get(i).getVitri().x && listBom.get(listBom.size() - 1).getVitri().y == listBom.get(i).getVitri().y) {
                            listBom.remove(listBom.size() - 1);
                        }
                    }
                }
            }
            boomber.ResetDatBom();
        }
        // ve bomber
        boomber.drawBoomber(gd2, this);

        //vẽ quái game
        for(BossGame bossGame: bossGames){
            bossGame.drawBoss(gd2,this);
        }
        // hien thi khi bom nổ
        for(int i = 0; i < listBom.size() ; i++){
            if(listBom.get(i).BomKichHoat()){
                if(listBom.get(i).BomNo(gd2,this)){
                    listBom.remove(i);
                    i--;
                }
            }
        }
        // kierm tra xem boss có bị die không
        for(int i = 0 ; i < bossGames.size() ; i++){
            if(bossGames.get(i).CheckDie()){
                bossGames.remove(i);
                i--;
            }
        }
        // vẽ khung thoi gian
        gd2.drawImage(KhungTG,-getX() +(WidthCamera-120)/2,-getY()+ 3,this);
        gd2.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        gd2.drawString(ThoiGian,-getX() +(WidthCamera-120)/2+20,-getY()+ 38);
    }
    // tạo quái trong game
    public void BuildBoss(){
        Random random = new Random();
        bossGames.clear();// xóa
        for (int y = 0; y < libarary.soHang; y += 3)
            for (int x = 0; x < libarary.soCot; x += 3) {
                if(InputMap.Map[y][x] == libarary.Boss){
                    int a = random.nextInt(100);
                    if( a < 30) {
                        bossGames.add(new RoiDo(x, y));
                    }
                    else if( a < 60){
                        bossGames.add(new RoiDen(x, y));
                    } else {
                        bossGames.add(new Quaiden(x,y));
                    }
                }
            }
    }
    // hiển thị map theo boomber
    public  void Camera(int widthCamera,int heightCamera) {
        WidthCamera = widthCamera;
        HeightCamera = heightCamera;
        int w  = widthCamera /2;
        int h = heightCamera /2;
        if((boomber.ToaDoX() - w) >= 0 && (boomber.ToaDoX() + w) <= getWidth()){// o giau man hinh
            setLocation(-(boomber.ToaDoX() - w),getY());
        }else {
            if(boomber.ToaDoX() - w >= 0){// lệch phai
                setLocation(-(getWidth() - WidthCamera), getY());
            }else {
                setLocation(0,getY());// lệch trái
            }
        }
        if((boomber.ToaDoY() - h) >= 0 && (boomber.ToaDoY() + h) <= getHeight()){ // o giữa man hình
            setLocation(getX(),-(boomber.ToaDoY() - h));
        }else {
            if(boomber.ToaDoY() - h >= 0){// lệch dưới
                setLocation(getX(), -(getHeight() - HeightCamera));
            }else {
                setLocation(getX(),0);// lệch trên
            }
        }
    }
}
