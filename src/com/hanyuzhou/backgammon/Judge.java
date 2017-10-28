package com.hanyuzhou.backgammon;

import javax.swing.*;

import static com.hanyuzhou.backgammon.GoBangListener.num;

public class Judge implements GoBangInterface{

    private int x,y;
    private int count;
    private JFrame frame;
    private GoBangPanel panel;

    public Judge(int x,int y,JFrame frame,GoBangPanel panel)
    {
        this.x = x;
        this.y = y;
        count = 1;
        this.frame = frame;
        this.panel = panel;
    }

    public void restart(){
        panel.restart();
    }

    public void back(){
        MainMenu mm = new MainMenu();
        try {
            mm.initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j]=0;
            }
        }
        frame.dispose();
    }

    public void judge()
    {
        count = 1;
        //竖向检查
        for(int i = x+1; i < Coloum; i++)
        {
            if(array[i][y] != 0 )
            {
                if((array[x][y]%2) == (array[i][y]%2))
                {
                    count++;
                }else break;
            }else break;
        }
        for(int i = x-1; i >= 0; i--)
        {
            if(array[i][y] != 0 )
            {
                if((array[x][y]%2) == (array[i][y]%2))
                    count++;
                else break;
            }else break;
        }
        if(count>=5)
        {
            num = 1;
            int res;
            if(array[x][y]%2 != 0)
            {
                res=JOptionPane.showConfirmDialog(null, "黑方获胜! 是否继续?", "黑方胜", JOptionPane.YES_NO_OPTION);
            }else
            {
                res=JOptionPane.showConfirmDialog(null, "白方获胜! 是否继续?", "白方胜", JOptionPane.YES_NO_OPTION);
            }
            if(res==JOptionPane.YES_OPTION){
                restart();
            }else{
                back();
            }
            return ;
        }
        count = 1;
        //横向检查
        for(int i = y+1; i < Row; i++)
        {
            if(array[x][i] != 0 )
            {
                if((array[x][y]%2) == (array[x][i])%2)
                    count++;
                else break;
            }else break;
        }
        for(int i = y-1; i >= 0; i--)
        {
            if(array[x][i] != 0 )
            {
                if(array[x][y]%2 == array[x][i]%2)
                    count++;
                else break;
            }else break;
        }
        if(count>=5)
        {
            num = 1;
            int res;
            if(array[x][y]%2 != 0)
            {
                res=JOptionPane.showConfirmDialog(null, "黑方获胜! 是否继续?", "黑方胜", JOptionPane.YES_NO_OPTION);
            }else
            {
                res=JOptionPane.showConfirmDialog(null, "白方获胜! 是否继续?", "白方胜", JOptionPane.YES_NO_OPTION);
            }
            if(res==JOptionPane.YES_OPTION){
                restart();
            }else{
                back();
            }
            return ;
        }

        count = 1;
        //斜向检查（从左上角到右下角）
        for(int i = x-1,j = y-1;i>=0&&j>=0; i--,j--)
        {
            if(array[i][j] != 0 )
            {
                if((array[x][y]%2) == (array[i][j]%2))
                    count++;
                else break;
            }else break;
        }
        for(int i = x+1,j = y+1;i<Coloum&&j<Row; i++,j++)
        {
            if(array[i][j] != 0 )
            {
                if((array[x][y]%2) == (array[i][j]%2))
                    count++;
                else break;
            }else break;
        }
        if(count>=5)
        {
            num = 1;
            int res;
            if(array[x][y]%2 != 0)
            {
                res=JOptionPane.showConfirmDialog(null, "黑方获胜! 是否继续?", "黑方胜", JOptionPane.YES_NO_OPTION);
            }else
            {
                res=JOptionPane.showConfirmDialog(null, "白方获胜! 是否继续?", "白方胜", JOptionPane.YES_NO_OPTION);
            }
            if(res==JOptionPane.YES_OPTION){
                restart();
            }else{
                back();
            }
            return ;
        }
        count = 1;
        //斜向检查（从左下角到右上角）
        for(int i = x-1,j = y+1;i>=0&&j<Row; i--,j++)
        {
            if(array[i][j] != 0 )
            {
                if((array[x][y]%2) == (array[i][j]%2))
                    count++;
                else break;
            }else break;
        }
        for(int i = x+1,j = y-1;i<Coloum&&j>=0; i++,j--)
        {
            if(array[i][j] != 0 )
            {
                if((array[x][y]%2) == (array[i][j]%2))
                    count++;
                else break;
            }else break;
        }
        if(count>=5)
        {
            num = 1;
            int res;
            if(array[x][y]%2 != 0)
            {
                res=JOptionPane.showConfirmDialog(null, "黑方获胜! 是否继续?", "黑方胜", JOptionPane.YES_NO_OPTION);
            }else
            {
                res=JOptionPane.showConfirmDialog(null, "白方获胜! 是否继续?", "白方胜", JOptionPane.YES_NO_OPTION);
            }
            if(res==JOptionPane.YES_OPTION){
                restart();
            }else{
                back();
            }
            return ;
        }
    }
}