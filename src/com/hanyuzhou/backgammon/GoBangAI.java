package com.hanyuzhou.backgammon;

import java.awt.*;

public class GoBangAI implements GoBangInterface {
    public Point AI() {
        //int i, j;
        int x, y;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        int qiju[][][][] = new int[15][15][8][2];
        int a1[][] = new int[15][15];
        int a2[][] = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                oldp[i][j] = array[i][j];
                newp[i][j] = array[i][j];
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 2; l++) {
                        qiju[i][j][k][l] = 0;
                    }
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                a1[i][j] = 0;
                a2[i][j] = 0;
            }
        }

        HowManyInLine(oldp, qiju);
        ValueTheChessboardNaive(oldp, qiju, a1, a2);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (a1[x1][y1] < a1[i][j]) {
                    x1 = i;
                    y1 = j;
                    // black
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (a2[x2][y2] < a2[i][j]) {
                    x2 = i;
                    y2 = j;
                    // white
                }
            }
        }
        if (a1[x1][y1] > a2[x2][y2]) {
            x = x1;
            y = y1;
        } else {
            x = x2;
            y = y2;
        }
        Point p = new Point(x,y);
        return p;
    }

    private void ValueTheChessboardNaive(int[][] oldp, int[][][][] qiju, int[][] a1, int[][] a2) {
        int i, j, k;
        int win;
        for ( i = 0; i <= 14; i++ )  // col
            for ( j = 0; j <= 14; j++ )  // row
            {
                if ( array[i][j] == 0 )
                {
                    win = 0;
                    for ( k = 0; k < 4; k++ )  // direction
                    {
                        if ( qiju[i][j][k][0] + qiju[i][j][k+4][0] >= 4 )
                            win += 10000;
                        else if ( qiju[i][j][k][0] + qiju[i][j][k+4][0] == 3 )
                            win += 1000;
                        else if ( qiju[i][j][k][0] + qiju[i][j][k+4][0] == 2 )
                            win += 100;
                        else if ( qiju[i][j][k][0] + qiju[i][j][k+4][0] == 1 )
                            win += 10;
                    }
                    a1[i][j] = win;  // black
                    win = 0;
                    for ( k = 0; k < 4; k++ )  // direction
                    {
                        if ( qiju[i][j][k][1] + qiju[i][j][k+4][1] >= 4 )
                            win += 10000;
                        else if ( qiju[i][j][k][1] + qiju[i][j][k+4][1] == 3 )
                            win += 1000;
                        else if ( qiju[i][j][k][1] + qiju[i][j][k+4][1] == 2 )
                            win += 100;
                        else if ( qiju[i][j][k][1] + qiju[i][j][k+4][1] == 1 )
                            win += 10;
                    }
                    a2[i][j] = win;  // white
                }
            }
    }

    private void HowManyInLine(int[][] oldp, int[][][][] qiju) {
        int i, j, k, t, cnt;
        int tx, ty;
        int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
        int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
        for (i = 0; i < 15; i++) {
            for (j = 0; j < 15; j++) {
                if (oldp[i][j] == 0) {
                    for (k = 0; k < 8; k++) {
                        cnt = 0;
                        tx = i;
                        ty = j;
                        for (t = 0; t < 5; t++) {
                            tx += dx[k];
                            ty += dy[k];
                            if (tx > 14 || tx < 0 || ty > 14 || ty < 0) {
                                break;
                            }
                            if (oldp[tx][ty] == 1) {
                                cnt++;
                            } else break;
                        }
                        qiju[i][j][k][0] = cnt;
                        // white
                        cnt = 0;
                        tx = i;
                        ty = j;
                        for (t = 0; t < 5; t++){
                            tx += dx[k];
                            ty += dy[k];
                            if ( tx > 14 || tx < 0 || ty > 14 || ty < 0 )
                                break;
                            if( oldp[tx][ty] == 2 )
                                cnt++;
                            else
                                break;
                        }
                        qiju[i][j][k][1] = cnt;
                    }
                }
            }
        }
    }
}
