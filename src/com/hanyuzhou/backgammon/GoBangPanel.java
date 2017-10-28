package com.hanyuzhou.backgammon;

import javax.swing.*;
import java.awt.*;

import static com.hanyuzhou.backgammon.GoBangListener.num;

public class GoBangPanel extends JPanel implements GoBangInterface{
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int intitate;
    private int mode;
    private boolean play = true;
    private JFrame f;

    public void gobang(int intitate, int mode, boolean play, GoBangSocket socket){
        this.mode = mode;
        this.intitate = intitate;
        if (intitate!=1){
            array[7][7]=1;
            num++;
        }
        //设置面板
        JFrame frame = new JFrame();
        f = frame;
        frame.setTitle("欢乐五子棋");
        frame.setBounds((width-850)/2,(height-700)/2,width,height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        //frame.setUndecorated(true);
        frame.setSize(850,700);
        //设置布局
        frame.setLayout(new BorderLayout());
        frame.add(this,BorderLayout.CENTER);
        //再jpanel组件上画棋子
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.DARK_GRAY);
        panel1.setPreferredSize(new Dimension(150,0));
        frame.add(panel1,BorderLayout.EAST);
        frame.setVisible(true);
        GoBangListener listener = new GoBangListener(this,frame, intitate, mode);
        this.addMouseListener(listener);
        if (mode == 2){
            listener.setSend(socket);
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        ImageIcon icon=new ImageIcon(getClass().getResource("/board.jpg"));
        Image img=icon.getImage();
        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
        Graphics2D gg = (Graphics2D)g;
        //画出整个棋盘
        for(int i = 0; i < Row; i++)//行
        {
            gg.drawLine(X, Y+i*Size, X+Size*(Coloum-1), Y+i*Size);
        }
        for(int i = 0; i < Coloum; i++)//列
        {
            gg.drawLine(X+i*Size, Y, X+i*Size, Y+Size*(Row-1));
        }
        //画出棋子
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array[i].length; j++)
            {
                if(array[i][j] != 0)
                {
                    if(array[i][j]%2 != 0)
                    {
                        gg.setColor(Color.black);
                    }else
                    {
                        gg.setColor(Color.white);
                    }
                    int x = Y+j*Size-Size/2;
                    int y = X+i*Size-Size/2;
                    gg.fillOval(x, y, Size, Size);
                }
            }
    }
    public void drawChess(Point point, Color color){
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(color);
        int coloum = point.y;
        int row = point.x;
        int y = Y + coloum*Size-Size/2;
        int x = X + row*Size-Size/2;
        g.fillOval(y,x,Size,Size);
    }
    public void setPlay(){
        play = !play;
    }
    public boolean isPlay() {
        return play;
    }
    public void handleMessage(Point point){
        Color color;
        if (num%2==0){
            color = Color.WHITE;
            array[point.x][point.y] = 2;
        }
        else {
            color = Color.BLACK;
            array[point.x][point.y] = 1;
        }
        drawChess(point,color);
        setPlay();
        num++;
        Judge judge = new Judge(point.x,point.y,f,this);
        judge.judge();
    }
    public void restart(){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j]=0;
            }
        }
        num = 1;
        if (intitate!=1){
            array[7][7]=1;
            num++;
        }
        this.paint(this.getGraphics());
    }
}
