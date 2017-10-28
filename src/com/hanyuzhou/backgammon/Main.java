package com.hanyuzhou.backgammon;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainMenu mm = new MainMenu();
        mm.initMenu();
    }
}
