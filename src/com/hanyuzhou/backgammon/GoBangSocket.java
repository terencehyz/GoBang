package com.hanyuzhou.backgammon;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class GoBangSocket implements GoBangInterface {
    private Socket socket = null;
    private ObjectOutputStream send;
    private ObjectInputStream receive;

    void c(String host, int port) {
        try {
            socket = new Socket(host, port);
            JOptionPane.showMessageDialog(null, "游戏开始");
            send = new ObjectOutputStream(socket.getOutputStream());
            receive = new ObjectInputStream(socket.getInputStream());
            new MThread(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface Listener {
        void msg(String msg);
    }

    private Listener listener;
    public GoBangPanel goBangPanel;

    public void setGoBangPanel(GoBangPanel goBangPanel) {
        this.goBangPanel = goBangPanel;
    }

    public void setListener(Listener l) {
        this.listener = l;
    }

    void s(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
//            JOptionPane.showMessageDialog(null, "游戏开始");
            send = new ObjectOutputStream(socket.getOutputStream());
            receive = new ObjectInputStream(socket.getInputStream());
            new MThread(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void send(String msg) {
        try {
            send.writeObject(msg);
            send.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MThread extends Thread {
        public Socket socket;

        MThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;

            while (true) {
                try {
                    try {
                        line = String.valueOf(receive.readObject());
                        System.out.println("接受来自对方："+line);
                        String[] strings = line.split(",");
                        int row = Integer.parseInt(strings[0]);
                        int coloum = Integer.parseInt(strings[1]);
                        Point point = new Point(row,coloum);
                        goBangPanel.handleMessage(point);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
