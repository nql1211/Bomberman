package com.company.Game;

import com.company.Game.PaneGame.ShowGame;
import com.company.GameBoom;
import com.company.libarary;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PanePlay extends JPanel {
    private int SpeedGame = 100;
    private int soLanLap = 0;
    private ShowGame showGame;
    private PaneOptionGame paneOptionGame;
    private PaneNotification paneNotification;
    boolean RunGame = true;

    public PanePlay() {
        paneOptionGame = new PaneOptionGame();// pane chọn map va boomber
        paneNotification = new PaneNotification(); // pane thông báo
        add(paneOptionGame);
        setLayout(null);
    }
    // trả về thao tác của người dùng cho vòng lặp lớn ở GameBoom xử lí
    public libarary.Click PlayGame() {
        try {
            //bắt sự kiện nhấn dấu X
            GameBoom.getMainFrame().addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    RunGame = false;
                    paneOptionGame.click = libarary.Click.Exit;
                }
            });
            // màn hình lụa chọn map và boomber
            paneOptionGame.setVisible(true);
            while (paneOptionGame.click == libarary.Click.KhongClick){
                paneOptionGame.ShowAllMenu(); // tạo hiệu ứng xoay quanh đối tượng đã chọn
                try{
                    Thread.sleep(150);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            if(paneOptionGame.click == libarary.Click.Play) {
                // tạo game với map và boomber đã chọn
                showGame = new ShowGame(paneOptionGame.getMapGame(), paneOptionGame.getBoomber());
                showGame.Camera(getWidth(), getHeight());//camera
                showGame.setVisible(RunGame);
                add(showGame);
                paneOptionGame.setVisible(false);
                paneNotification.setVisible(false);
                add(paneNotification);
            }
            if(paneOptionGame.click == libarary.Click.Back){
                paneOptionGame.click = libarary.Click.KhongClick;
                return libarary.Click.Back;
            }
            if(paneOptionGame.click == libarary.Click.Exit){
                return libarary.Click.Exit;
            }
            // Game
            while (RunGame) {
                if(showGame.isVisible()) {// neu game đang được hiên thị
                    showGame.setThoiGian(soLanLap/10); // cài đăt thời gian
                    showGame.Camera(getWidth(),getHeight());// hien thi theo nhân vật
                    showGame.repaint(); // vẽ lại game
                }
                //kiem tra
                if(showGame.checkGame() == libarary.Game.lose){// kiem tra thua thi hien thong bao thua
                    paneNotification.Lose();
                    paneNotification.setVisible(true); //hien thong bao
                    showGame.setVisible(false);// tắt hiển thị game
                    System.out.println("thông báo");
                }else {
                    if(showGame.checkGame() == libarary.Game.win){// thang thì hiện thong bao
                        paneNotification.Win();
                        paneNotification.setVisible(true);//hien thi thong bao
                        showGame.setVisible(false); // tắt hiển thi game
                    }
                }
                if(showGame.checkGame() != libarary.Game.play) {
                    //kiem tra khi hien thông báo thì người chơi click vào đâu
                    if (paneNotification.Click() == libarary.Click.Menu) {// trở lại màn hình bắt đầu
                        paneNotification.setVisible(false);
                        return libarary.Click.Menu;
                    } else {
                        if (paneNotification.Click() == libarary.Click.PlayAgain) { // chơi lại
                            showGame.setVisible(true);// hiên thị game và reset lại
                            showGame.Camera(getWidth(),getHeight());// hiển thị map thep nhân vật
                            paneNotification.setVisible(false);
                            soLanLap = -1; // tính thời gian chơi game
                        }
                    }
                }
                soLanLap++; //tính thời gian choi game
                Thread.sleep(SpeedGame);
            }
        } catch (Exception eq) {
            System.out.println(eq);
        }
        return libarary.Click.Exit;
    }
}
