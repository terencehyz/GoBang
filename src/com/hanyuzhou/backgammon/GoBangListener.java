package com.hanyuzhou.backgammon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GoBangListener extends MouseAdapter implements GoBangInterface {

    private Judge j;
    private JFrame frame;
    private GoBangPanel panel;
    private Graphics2D g;
    private int x, y;
    static int num = 1;
    private int intitate;
    public GoBangSocket send;
    private int mode;

    public void setSend(GoBangSocket outputStream) {
        send = outputStream;
    }

    public GoBangListener(GoBangPanel panel, JFrame frame, int intitate, int mode) {
        this.intitate = intitate;
        this.mode = mode;
        this.panel = panel;
        this.frame = frame;
        g = (Graphics2D) this.panel.getGraphics();
    }

    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (mode == 2) {
            if (!panel.isPlay())
                return;
        }
        int row = (y - Y + Size / 2) / Size;
        int coloum = (x - X + Size / 2) / Size;
        if (row < Row && coloum < Coloum) {
            if (array[row][coloum] == 0) {
                x = X + coloum * Size - Size / 2;
                y = Y + row * Size - Size / 2;
                if (num % 2 != 0) {
                    g.setColor(Color.black);
                    array[row][coloum] = 1;
                } else {
                    g.setColor(Color.white);
                    array[row][coloum] = 2;
                }
                g.fillOval(x, y, Size, Size);
                for (int i = 0; i < array.length; i++) {
                    for (int k = 0; k < array[i].length; k++) {
                        System.out.print(array[i][k] + " ");
                    }
                    System.out.println();
                }
                if (mode == 2) {
                    System.out.println("传输内容" + new Point(row, coloum).toString());
                    send.send(row + "," + coloum);
                    panel.setPlay();
                }
                num++;
                j = new Judge(row, coloum, frame, panel);
                j.judge();
            }
        }
        if (mode == 0) {
            GoBangAI ai = new GoBangAI();
            Point p = ai.AI();
            row = p.x;
            coloum = p.y;
            x = X + coloum * Size - Size / 2;
            y = Y + row * Size - Size / 2;
            if (num % 2 != 0) {
                g.setColor(Color.black);
                array[row][coloum] = 1;
            } else {
                g.setColor(Color.white);
                array[row][coloum] = 2;
            }
            g.fillOval(x, y, Size, Size);
            num++;
            j = new Judge(row, coloum, frame, panel);
            j.judge();
        }
    }
}
