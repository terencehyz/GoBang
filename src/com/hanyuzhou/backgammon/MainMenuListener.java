package com.hanyuzhou.backgammon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuListener implements ActionListener, GoBangInterface {
    MainMenu mm;
    MainMenuListener(MainMenu mm){
        this.mm = mm;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(MainMenu.jb[3])){
            System.exit(0);
        }
        else if(e.getSource().equals(MainMenu.jb[2])){
            int res = JOptionPane.showConfirmDialog(null,"是否为服务器？","服务器客户端选择",JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_OPTION){
                String input = JOptionPane.showInputDialog( "输入端口号" );
                int port = Integer.parseInt(input);
                GoBangSocket goBangSocketS = new GoBangSocket();
                goBangSocketS.s(port);
                GoBangPanel chesspanel = new GoBangPanel();
                chesspanel.gobang(1,2,true,goBangSocketS);
                goBangSocketS.setGoBangPanel(chesspanel);
                mm.dispose();
            }else{
                String input = JOptionPane.showInputDialog( "输入对方ip和端口号" );
                GoBangSocket goBangSocketC = new GoBangSocket();
                String[] inputData = input.split(":");
                String host = inputData[0];
                int port = Integer.parseInt(inputData[1]);
                System.out.println(host+" "+port);
                goBangSocketC.c(host,port);
                GoBangPanel chesspanel = new GoBangPanel();
                chesspanel.gobang(1,2,false,goBangSocketC);
                chesspanel.setPlay();
                goBangSocketC.setGoBangPanel(chesspanel);
                mm.dispose();
            }
        }
        else if (e.getSource().equals(MainMenu.jb[0])){
            int intitate;
            int res= JOptionPane.showConfirmDialog(null, "是否先手？", "先后选择", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_OPTION){
                intitate = 1;
            }else{
                intitate = 0;
            }
            GoBangPanel chesspanel = new GoBangPanel();
            chesspanel.gobang(intitate,0,true,null);
            mm.dispose();
        }
        else{
            GoBangPanel chesspanel = new GoBangPanel();
            chesspanel.gobang(1,1,true,null);
            mm.dispose();
        }
    }
}
