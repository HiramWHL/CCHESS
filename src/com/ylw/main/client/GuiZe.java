package com.ylw.main.client;

import java.util.ArrayList;

public class GuiZe {

    QiZi[][] qiZi;
    boolean canMove = false;

    int i;
    int j;

    public GuiZe(QiZi[][] qiZi) {
        this.qiZi = qiZi;
    }

    public boolean canMove(int startI, int startJ, int endI, int endJ, String name) {

        int maxI;
        int minI;
        int maxJ;
        int minJ;

        canMove = true;

        if (startI >= endI)
        {
            maxI = startI;
            minI = endI;
        } else
        {
            maxI = endI;
            minI = startI;
        }
        if (startJ >= endJ)
        {
            maxJ = startJ;
            minJ = endJ;
        } else {
            maxJ = endJ;
            minJ = startJ;
        }
        if (name.equals("車"))
        {
            this.ju(maxI, minI, maxJ, minJ);
        } else if (name.equals("馬"))
        {
            this.ma(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
        } else if (name.equals("相"))
        {
            this.xiang1(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
        } else if (name.equals("象"))
        {
            this.xiang2(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
        } else if (name.equals("士") || name.equals("仕"))
        {
            this.shi(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
        } else if (name.equals("帥") || name.equals("將"))
        {
            this.jiang(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
        } else if (name.equals("炮") || name.equals("砲"))
        {
            this.pao(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
        } else if (name.equals("兵"))
        {
            this.bing(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);

        } else if (name.equals("卒"))
        {
            this.zu(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ);
        }
        return canMove;
    }

    public void ju(int maxI, int minI, int maxJ, int minJ) {
        if (maxI == minI)
        {
            for (j = minJ + 1; j < maxJ; j++) {
                if (qiZi[maxI][j] != null)
                {
                    canMove = false;
                    break;
                }
            }
        } else if (maxJ == minJ)
        {
            for (i = minJ + 1; i < maxJ; i++) {
                if (qiZi[i][maxJ] != null)
                {
                    canMove = false;
                    break;
                }
            }
        } else if (maxI != minI && maxJ != minJ)
        {
            canMove = false;
        }
    }

    public void ma(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {

        int a = maxI - minI;
        int b = maxJ - minJ;

        if (a == 1 && b == 2)
        {
            if (startJ > endJ)
            {
                if (qiZi[startI][startJ - 1] != null)
                {
                    canMove = false;
                }
            } else {//如果是从上往下走
                if (qiZi[startI][startJ + 1] != null)//如果马腿处有棋子
                {
                    canMove = false;//不可以走
                }
            }
        } else if (a == 2 && b == 1)//如果是横着的"日"
        {
            if (startI > endI)//如果是从右往左走
            {
                if (qiZi[startI - 1][startJ] != null)//如果马腿处有棋子
                {
                    canMove = false;
                }
            } else {//如果是从左往右走
                if (qiZi[startI + 1][startJ] != null)//如果马腿处有棋子
                {
                    canMove = false;
                }
            }
        } else if (!((a == 2 && b == 1) || (a == 1 && b == 2)))//如果不时"日"字
        {
            canMove = false;
        }
    }

    public void xiang1(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {

        int a = maxI - minI;
        int b = maxJ - minJ;

        if (a == 2 && b == 2)//如果是"田"字
        {
            if (endJ > 4)//如果过河了
            {
                canMove = false;//不可以走
            }
            if (qiZi[(maxI + minI) / 2][(maxJ + minJ) / 2] != null)//如果"田"字中间有棋子
            {
                canMove = false;//不可以走
            }
        } else {
            canMove = false;//如果不是"田"字，不可以走
        }
    }

    public void xiang2(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {
        int a = maxI - minI;
        int b = maxJ - minJ;
        if (a == 2 && b == 2)
        {
            if (endJ < 5)
            {
                canMove = false;
            }
            if (qiZi[(maxI + minI) / 2][(maxJ + minJ) / 2] != null)
            {
                canMove = false;
            }
        } else {
            canMove = false;
        }
    }

    public void shi(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {

        int a = maxI - minI;
        int b = maxJ - minJ;

        if (a == 1 && b == 1)//如果是小斜线
        {
            if (startJ > 4)//如果是下方的士
            {
                if (endJ < 7) {
                    canMove = false;//如果下方的士越界，不可以走
                }
            } else {//如果是上方的仕
                if (endJ > 2) {
                    canMove = false;//如果上方的仕越界，不可以走
                }
            }
            if (endI > 5 || endI < 3)//如果左右越界，则不可以走
            {
                canMove = false;
            }
        } else {
            canMove = false;
        }
    }

    public void jiang(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {

        int a = maxI - minI;
        int b = maxJ - minJ;

        if ((a == 1 && b == 0) || (a == 0 && b == 1)) {//如果走的是一小格
            if (startJ > 4)//如果是下方的将
            {
                if (endJ < 7)//如果越界
                {
                    canMove = false;//不可以走
                }
            } else {//如果是上方的将
                if (endJ > 2)
                {
                    canMove = false;
                }
            }
            if (endI > 5 || endI < 3)//如果左右越界，不可以走
            {
                canMove = false;
            }
        } else {
            canMove = false;
        }
    }

    public void pao(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {

        if (maxI == minI)//如果走的竖线
        {
            if (qiZi[endI][endJ] != null)//如果终点有棋子
            {
                int count = 0;
                for (j = minJ + 1; j < maxJ; j++) {
                    if (qiZi[minI][j] != null)//判断起点与终点之间有几个棋子
                    {
                        count++;
                    }
                }
                if (count != 1) {//如果不是一个棋子
                    canMove = false;//不可以走
                }
            } else if (qiZi[endI][endJ] == null)//如果终点没有棋子
            {
                for (j = minJ + 1; j < maxJ; j++) {
                    if (qiZi[minI][j] != null)//如果起止点之间有棋子
                    {
                        canMove = false;//不可以走
                        break;
                    }
                }
            }
        } else if (maxJ == minJ)//如果走的是横线
        {
            if (qiZi[endI][endJ] != null)//如果终点有棋子
            {
                int count = 0;
                for (i = minI + 1; i < maxI; i++) {
                    if (qiZi[i][minJ] != null)//判断起点与终点之间有几个棋子
                    {
                        count++;
                    }
                }
                if (count != 1)//如果不是一个棋子
                {
                    canMove = false;//不可以走
                }
            } else if (qiZi[endI][endJ] == null)//如果终点没有棋子
            {
                for (i = minI + 1; i < maxI; i++) {
                    if (qiZi[i][minJ] != null)//如果起止点之间有棋子
                    {
                        canMove = false;//不可以走
                        break;
                    }
                }
            }
        } else if (maxJ != minJ && maxI != minI) {
            canMove = false;
        }
    }

    public void bing(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {
        if (startJ < 5)//如果还没有过河
        {
            if (startI != endI)//如果不是向前走
            {
                canMove = false;
            }
            if (endJ - startJ != 1)//如果走的不是一格
            {
                canMove = false;
            }
        } else {//如果已经过河
            if (startI == endI) {//如果走的是竖线
                if (endJ - startJ != 1)//如果走的不是一格
                {
                    canMove = false;
                }
            } else if (startJ == endJ) {//如果走的是横线
                if (maxI - minI != 1) {//如果走的不是一格
                    canMove = false;
                }
            } else if (startI != endI && startJ != endJ) {//如果走的既不是竖线，也不是横线，则不可以走
                canMove = false;
            }
        }
    }

    public void zu(int maxI, int minI, int maxJ, int minJ, int startI, int startJ, int endI, int endJ) {
        if (startJ > 4) {//如果还没有过河
            if (startI != endI) {//如果不是向前走
                canMove = false;
            }
            if (endJ - startJ != -1)//如果走的不是一格
            {
                canMove = false;
            }
        } else {//如果已经过河
            if (startI == endI) {//如果走的是竖线
                if (endJ - startJ != -1) {//如果走的不是一格
                    canMove = false;
                }
            } else if (startJ == endJ) {//如果走的是横线
                if (maxI - minI != 1) {//如果走的不是一格
                    canMove = false;
                }
            } else if (startI != endI && startJ != endJ) {//如果走的既不是竖线，也不是横线，则不可以走
                canMove = false;
            }
        }
    }
    private static int[] pos;
    private static QiPan board;
    private static char player;

    public static ArrayList<int[]> getNextMove(String piece, int[] pos, QiPan board) {
        GuiZe.pos = pos;
        GuiZe.board = board;
       GuiZe.player = piece.charAt(0);
        switch (piece.charAt(1)) {
            case 'j':
                return jRules();
            case 'm':
                return mRules();
            case 'p':
                return pRules();
            case 'x':
                return xRules();
            case 's':
                return sRules();
            case 'b':
                return bRules();
            case 'z':
                return zRules();
            default:
                return null;
        }
    }

    private static ArrayList<int[]> mRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] target = new int[][]{{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};
        int[][] obstacle = new int[][]{{0, -1}, {1, 0}, {1, 0}, {0, 1}, {0, 1}, {-1, 0}, {-1, 0}, {0, -1}};
        for (int i = 0; i < target.length; i++) {
            int[] e = new int[]{pos[0] + target[i][0], pos[1] + target[i][1]};
            int[] f = new int[]{pos[0] + obstacle[i][0], pos[1] + obstacle[i][1]};
            if (!board.isInside(e)) continue;
            if (board.isEmpty(f)) {
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            }
        }
        return moves;
    }

    private static ArrayList<int[]> jRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[] yOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] xOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int offset : yOffsets) {
            int[] rMove = new int[]{pos[0], pos[1] + offset};
            if (board.isEmpty(rMove)) moves.add(rMove);
            else if (board.isInside(rMove) && board.getPiece(rMove).color != player) {
                moves.add(rMove);
                break;
            } else break;
        }
        for (int offset : yOffsets) {
            int[] lMove = new int[]{pos[0], pos[1] - offset};
            if (board.isEmpty(lMove)) moves.add(lMove);
            else if (board.isInside(lMove) && board.getPiece(lMove).color != player) {
                moves.add(lMove);
                break;
            } else break;
        }
        for (int offset : xOffsets) {
            int[] uMove = new int[]{pos[0] - offset, pos[1]};
            if (board.isEmpty(uMove)) moves.add(uMove);
            else if (board.isInside(uMove) && board.getPiece(uMove).color != player) {
                moves.add(uMove);
                break;
            } else break;
        }
        for (int offset : xOffsets) {
            int[] dMove = new int[]{pos[0] + offset, pos[1]};
            if (board.isEmpty(dMove)) moves.add(dMove);
            else if (board.isInside(dMove) && board.getPiece(dMove).color != player) {
                moves.add(dMove);
                break;
            } else break;
        }
        return moves;
    }

    private static ArrayList<int[]> pRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[] yOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] xOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean rr = false, ll = false, uu = false, dd = false;
        for (int offset : yOffsets) {
            int[] rMove = new int[]{pos[0], pos[1] + offset};
            if (!board.isInside(rMove)) break;
            boolean e = board.isEmpty(rMove);
            if (!rr) {
                if (e) moves.add(rMove);
                else rr = true;
            } else if (!e) {
                if (board.getPiece(rMove).color != player) moves.add(rMove);
                break;
            }
        }
        for (int offset : yOffsets) {
            int[] lMove = new int[]{pos[0], pos[1] - offset};
            if (!board.isInside(lMove)) break;
            boolean e = board.isEmpty(lMove);
            if (!ll) {
                if (e) moves.add(lMove);
                else ll = true;
            } else if (!e) {
                if (board.getPiece(lMove).color != player) {
                    moves.add(lMove);
                }
                break;
            }
        }
        for (int offset : xOffsets) {
            int[] uMove = new int[]{pos[0] - offset, pos[1]};
            if (!board.isInside(uMove)) break;
            boolean e = board.isEmpty(uMove);
            if (!uu) {
                if (e) moves.add(uMove);
                else uu = true;
            } else if (!e) {
                if (board.getPiece(uMove).color != player) moves.add(uMove);
                break;
            }
        }
        for (int offset : xOffsets) {
            int[] dMove = new int[]{pos[0] + offset, pos[1]};
            if (!board.isInside(dMove)) break;
            boolean e = board.isEmpty(dMove);
            if (!dd) {
                if (e) moves.add(dMove);
                else dd = true;
            } else if (!e) {
                if (board.getPiece(dMove).color != player) moves.add(dMove);
                break;
            }
        }
        return moves;
    }

    private static ArrayList<int[]> xRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] target = new int[][]{{-2, -2}, {2, -2}, {-2, 2}, {2, 2}};
        int[][] obstacle = new int[][]{{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
        for (int i = 0; i < target.length; i++) {
            int[] e = new int[]{pos[0] + target[i][0], pos[1] + target[i][1]};
            int[] f = new int[]{pos[0] + obstacle[i][0], pos[1] + obstacle[i][1]};
            if (!board.isInside(e) || (e[0] > 4 && player == 'b') || (e[0] < 5 && player == 'r')) continue;
            if (board.isEmpty(f)) {
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            }
        }
        return moves;
    }

    private static ArrayList<int[]> sRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] target = new int[][]{{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        for (int[] aTarget : target) {
            int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
            if (!board.isInside(e) || ((e[0] > 2 || e[1] < 3 || e[1] > 5) && player == 'b') || ((e[0] < 7 || e[1] < 3 || e[1] > 5) && player == 'r'))
                continue;
            if (board.isEmpty(e)) moves.add(e);
            else if (board.getPiece(e).color != player) moves.add(e);
        }
        return moves;
    }

    private static ArrayList<int[]> bRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        /* 3*3 block */
        int[][] target = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] aTarget : target) {
            int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
            if (!board.isInside(e) || ((e[0] > 2 || e[1] < 3 || e[1] > 5) && player == 'b') || ((e[0] < 7 || e[1] < 3 || e[1] > 5) && player == 'r'))
                continue;
            if (board.isEmpty(e)) moves.add(e);
            else if (board.getPiece(e).color != player) moves.add(e);
        }
        /* opposite 'b' */
        boolean flag = true;
        int[] oppoBoss = (player == 'r') ? board.pieces.get("bb0").position : board.pieces.get("rb0").position;
        if (oppoBoss[1] == pos[1]) {
            for (int i = Math.min(oppoBoss[0], pos[0]) + 1; i < Math.max(oppoBoss[0], pos[0]); i++) {
                if (board.getPiece(i, pos[1]) != null) {
                    flag = false;
                    break;
                }
            }
            if (flag) moves.add(oppoBoss);
        }
        return moves;
    }

    private static ArrayList<int[]> zRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] targetU = new int[][]{{0, 1}, {0, -1}, {-1, 0}};
        int[][] targetD = new int[][]{{0, 1}, {0, -1}, {1, 0}};
        if (player == 'r') {
            if (pos[0] > 4) {
                int[] e = new int[]{pos[0] - 1, pos[1]};
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            } else {
                for (int[] aTarget : targetU) {
                    int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
                    if (!board.isInside(e)) continue;
                    if (board.isEmpty(e)) moves.add(e);
                    else if (board.getPiece(e).color != player) moves.add(e);
                }
            }
        }
        if (player == 'b') {
            if (pos[0] < 5) {
                int[] e = new int[]{pos[0] + 1, pos[1]};
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            } else {
                for (int[] aTarget : targetD) {
                    int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
                    if (!board.isInside(e)) continue;
                    if (board.isEmpty(e)) moves.add(e);
                    else if (board.getPiece(e).color != player) moves.add(e);
                }
            }
        }

        return moves;
    }
}

class RJXiangQi{
 private static int[] pos;
    private static QiPan board;
    private static char player;

    public static ArrayList<int[]> getNextMove(String piece, int[] pos, QiPan board) {
    	RJXiangQi.pos = pos;
    	RJXiangQi.board = board;
    	RJXiangQi.player = piece.charAt(0);
        switch (piece.charAt(1)) {
            case 'j':
                return jRules();
            case 'm':
                return mRules();
            case 'p':
                return pRules();
            case 'x':
                return xRules();
            case 's':
                return sRules();
            case 'b':
                return bRules();
            case 'z':
                return zRules();
            default:
                return null;
        }
    }

    private static ArrayList<int[]> mRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] target = new int[][]{{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};
        int[][] obstacle = new int[][]{{0, -1}, {1, 0}, {1, 0}, {0, 1}, {0, 1}, {-1, 0}, {-1, 0}, {0, -1}};
        for (int i = 0; i < target.length; i++) {
            int[] e = new int[]{pos[0] + target[i][0], pos[1] + target[i][1]};
            int[] f = new int[]{pos[0] + obstacle[i][0], pos[1] + obstacle[i][1]};
            if (!board.isInside(e)) continue;
            if (board.isEmpty(f)) {
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            }
        }
        return moves;
    }

    private static ArrayList<int[]> jRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[] yOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] xOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int offset : yOffsets) {
            int[] rMove = new int[]{pos[0], pos[1] + offset};
            if (board.isEmpty(rMove)) moves.add(rMove);
            else if (board.isInside(rMove) && board.getPiece(rMove).color != player) {
                moves.add(rMove);
                break;
            } else break;
        }
        for (int offset : yOffsets) {
            int[] lMove = new int[]{pos[0], pos[1] - offset};
            if (board.isEmpty(lMove)) moves.add(lMove);
            else if (board.isInside(lMove) && board.getPiece(lMove).color != player) {
                moves.add(lMove);
                break;
            } else break;
        }
        for (int offset : xOffsets) {
            int[] uMove = new int[]{pos[0] - offset, pos[1]};
            if (board.isEmpty(uMove)) moves.add(uMove);
            else if (board.isInside(uMove) && board.getPiece(uMove).color != player) {
                moves.add(uMove);
                break;
            } else break;
        }
        for (int offset : xOffsets) {
            int[] dMove = new int[]{pos[0] + offset, pos[1]};
            if (board.isEmpty(dMove)) moves.add(dMove);
            else if (board.isInside(dMove) && board.getPiece(dMove).color != player) {
                moves.add(dMove);
                break;
            } else break;
        }
        return moves;
    }

    private static ArrayList<int[]> pRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[] yOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] xOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean rr = false, ll = false, uu = false, dd = false;
        for (int offset : yOffsets) {
            int[] rMove = new int[]{pos[0], pos[1] + offset};
            if (!board.isInside(rMove)) break;
            boolean e = board.isEmpty(rMove);
            if (!rr) {
                if (e) moves.add(rMove);
                else rr = true;
            } else if (!e) {
                if (board.getPiece(rMove).color != player) moves.add(rMove);
                break;
            }
        }
        for (int offset : yOffsets) {
            int[] lMove = new int[]{pos[0], pos[1] - offset};
            if (!board.isInside(lMove)) break;
            boolean e = board.isEmpty(lMove);
            if (!ll) {
                if (e) moves.add(lMove);
                else ll = true;
            } else if (!e) {
                if (board.getPiece(lMove).color != player) {
                    moves.add(lMove);
                }
                break;
            }
        }
        for (int offset : xOffsets) {
            int[] uMove = new int[]{pos[0] - offset, pos[1]};
            if (!board.isInside(uMove)) break;
            boolean e = board.isEmpty(uMove);
            if (!uu) {
                if (e) moves.add(uMove);
                else uu = true;
            } else if (!e) {
                if (board.getPiece(uMove).color != player) moves.add(uMove);
                break;
            }
        }
        for (int offset : xOffsets) {
            int[] dMove = new int[]{pos[0] + offset, pos[1]};
            if (!board.isInside(dMove)) break;
            boolean e = board.isEmpty(dMove);
            if (!dd) {
                if (e) moves.add(dMove);
                else dd = true;
            } else if (!e) {
                if (board.getPiece(dMove).color != player) moves.add(dMove);
                break;
            }
        }
        return moves;
    }

    private static ArrayList<int[]> xRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] target = new int[][]{{-2, -2}, {2, -2}, {-2, 2}, {2, 2}};
        int[][] obstacle = new int[][]{{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
        for (int i = 0; i < target.length; i++) {
            int[] e = new int[]{pos[0] + target[i][0], pos[1] + target[i][1]};
            int[] f = new int[]{pos[0] + obstacle[i][0], pos[1] + obstacle[i][1]};
            if (!board.isInside(e) || (e[0] > 4 && player == 'b') || (e[0] < 5 && player == 'r')) continue;
            if (board.isEmpty(f)) {
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            }
        }
        return moves;
    }

    private static ArrayList<int[]> sRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] target = new int[][]{{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        for (int[] aTarget : target) {
            int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
            if (!board.isInside(e) || ((e[0] > 2 || e[1] < 3 || e[1] > 5) && player == 'b') || ((e[0] < 7 || e[1] < 3 || e[1] > 5) && player == 'r'))
                continue;
            if (board.isEmpty(e)) moves.add(e);
            else if (board.getPiece(e).color != player) moves.add(e);
        }
        return moves;
    }

    private static ArrayList<int[]> bRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        /* 3*3 block */
        int[][] target = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] aTarget : target) {
            int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
            if (!board.isInside(e) || ((e[0] > 2 || e[1] < 3 || e[1] > 5) && player == 'b') || ((e[0] < 7 || e[1] < 3 || e[1] > 5) && player == 'r'))
                continue;
            if (board.isEmpty(e)) moves.add(e);
            else if (board.getPiece(e).color != player) moves.add(e);
        }
        /* opposite 'b' */
        boolean flag = true;
        int[] oppoBoss = (player == 'r') ? board.pieces.get("bb0").position : board.pieces.get("rb0").position;
        if (oppoBoss[1] == pos[1]) {
            for (int i = Math.min(oppoBoss[0], pos[0]) + 1; i < Math.max(oppoBoss[0], pos[0]); i++) {
                if (board.getPiece(i, pos[1]) != null) {
                    flag = false;
                    break;
                }
            }
            if (flag) moves.add(oppoBoss);
        }
        return moves;
    }

    private static ArrayList<int[]> zRules() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[][] targetU = new int[][]{{0, 1}, {0, -1}, {-1, 0}};
        int[][] targetD = new int[][]{{0, 1}, {0, -1}, {1, 0}};
        if (player == 'r') {
            if (pos[0] > 4) {
                int[] e = new int[]{pos[0] - 1, pos[1]};
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            } else {
                for (int[] aTarget : targetU) {
                    int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
                    if (!board.isInside(e)) continue;
                    if (board.isEmpty(e)) moves.add(e);
                    else if (board.getPiece(e).color != player) moves.add(e);
                }
            }
        }
        if (player == 'b') {
            if (pos[0] < 5) {
                int[] e = new int[]{pos[0] + 1, pos[1]};
                if (board.isEmpty(e)) moves.add(e);
                else if (board.getPiece(e).color != player) moves.add(e);
            } else {
                for (int[] aTarget : targetD) {
                    int[] e = new int[]{pos[0] + aTarget[0], pos[1] + aTarget[1]};
                    if (!board.isInside(e)) continue;
                    if (board.isEmpty(e)) moves.add(e);
                    else if (board.getPiece(e).color != player) moves.add(e);
                }
            }
        }

        return moves;
    }


}