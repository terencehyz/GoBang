package com.hanyuzhou.backgammon;

public interface GoBangInterface {
    //棋子的大小
    public static final int Size = 44;
    public static final int X = 25;
    public static final int Y = 25;
    public static final int Row = 15;
    public static final int Coloum = 15;
    //存旗子的数组
    public static final int[][] array = new int[Row][Coloum];
    public static final int[][] oldp = new int[Row][Coloum];
    public static final int[][] newp = new int[Row][Coloum];

    public boolean canPlay = true;
}
