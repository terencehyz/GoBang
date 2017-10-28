package com.hanyuzhou.backgammon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu extends JFrame{

    static JButton[] jb = new JButton[4];

    public void initMenu() throws Exception{
        this.setSize(320,568);
        this.setTitle("欢乐五子棋");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

        // 设置图标
        BufferedImage myIcon = null;
        myIcon = ImageIO.read(getClass().getResource("/ic_launcher.png"));
        this.setIconImage(myIcon);

        // 设置背景
        ImageIcon icon = new ImageIcon(getClass().getResource("/duizhan_bg.jpg"));
        Image img = icon.getImage().getScaledInstance(320, 568, Image.SCALE_FAST); // 图像缩放为适合Frame大小
        JLabel jlabel = new JLabel(new ImageIcon(img));
        jlabel.setBounds(0, 0, 320, 568);
        this.getLayeredPane().add(jlabel,new Integer(Integer.MIN_VALUE));
        JPanel jp = (JPanel) this.getContentPane();
        JRootPane jp1 = (JRootPane) this.getRootPane();
        jp.setOpaque(false);
        jp1.setOpaque(false);
//        this.setUndecorated(true);

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/menu_logo.png"));
        Image img1 = icon1.getImage().getScaledInstance(320,194,Image.SCALE_FAST);
        JLabel myLogo = new JLabel(new ImageIcon(img1));
        myLogo.setBounds(0,0,320,194);

        this.add(myLogo);

        ImageIcon buttonIcon1 = new ImageIcon(getClass().getResource("/button_renji.png"));
        Image buttonImg1 = buttonIcon1.getImage().getScaledInstance(180,66,Image.SCALE_FAST);
        buttonIcon1 = new ImageIcon(buttonImg1);

        ImageIcon buttonIcon2 = new ImageIcon(getClass().getResource("/button_benji.png"));
        Image buttonImg2 = buttonIcon2.getImage().getScaledInstance(180,66,Image.SCALE_FAST);
        buttonIcon2 = new ImageIcon(buttonImg2);

        ImageIcon buttonIcon3 = new ImageIcon(getClass().getResource("/button_lianji.png"));
        Image buttonImg3 = buttonIcon3.getImage().getScaledInstance(180,66,Image.SCALE_FAST);
        buttonIcon3 = new ImageIcon(buttonImg3);

        ImageIcon buttonIcon4 = new ImageIcon(getClass().getResource("/button_renji.png"));
        Image buttonImg4 = buttonIcon4.getImage().getScaledInstance(180,66,Image.SCALE_FAST);
        buttonIcon4 = new ImageIcon(buttonImg4);

        jb[0] = new JButton(buttonIcon1);
        jb[1] = new JButton(buttonIcon2);
        jb[2] = new JButton(buttonIcon3);
        jb[3] = new JButton(buttonIcon4);

        MainMenuListener mml = new MainMenuListener(this);

        for(int i=0;i<4;i++){
            this.add(jb[i]);
            jb[i].addActionListener(mml);
            jb[i].setBorderPainted(false);
            jb[i].setContentAreaFilled(false);
        }

        this.setVisible(true);
    }
}
