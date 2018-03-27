package pl.edu.kasprzak.tictactoe;

import android.graphics.Color;
import android.widget.Button;

public class Board {

    public Button buttons[][];
    private MainActivity activity;
    private AI ai;
    private String textonboard = "";
    public String who = "";
    public int color = Color.BLACK;
    public int color2 = Color.RED;
    public int xwin = 0;
    public int owin = 0;
    public int nowin = 0;

    public Board(MainActivity activity) {
        this.activity = activity;
        buttons = new Button[6][6];
        ai = new AI(this, activity);
    }

    public void setButton(Button button, int x, int y) {
        buttons[x][y] = button;
        buttons[x][y].setTextColor(color);
        button.setOnClickListener(new ButtonOnClick(button, this, activity, ai));
    }

    public void setColor(int color, int color2) {
        this.color = color;
        this.color2 = color2;
    }

    public void iswin(boolean load) {
        check("X", load);
        check("O", load);

        textonboard="";
        for (int a=0;a<3;a++) {
            for (int b=0;b<3;b++) {
                textonboard = textonboard + buttons[a][b].getText();
            }
        }

        if (ButtonOnClick.gameended==false) {
            if (textonboard.length()==9) {
                buttons[3][0].setText("Gra zakończyła się remisem.\nKliknij, aby rozpocząć nową grę.");
                if (!load) {
                    nowin++;
                }
                buttons[3][1].setText("Wygrane X: " + xwin + "\nWygrane O: " + owin + "\nRemisy: " + nowin);
                ButtonOnClick.gameended = true;
            }
        }

        if (ButtonOnClick.gameended==false) {
            buttons[3][0].setText("Kolej gracza " + who);
        }
    }

    public void check(String what, boolean load) {
        for (int x=0;x<3;x++) {
            if (buttons[x][0].getText().equals(what)&&buttons[x][1].getText().equals(what)&&buttons[x][2].getText().equals(what)) {
                end(what,x,0,x,1,x,2, load);
            }
        }

        for (int y=0;y<3;y++) {
            if (buttons[0][y].getText().equals(what)&&buttons[1][y].getText().equals(what)&&buttons[2][y].getText().equals(what)) {
                end(what,0,y,1,y,2,y, load);
            }
        }

        if (buttons[0][0].getText().equals(what)&&buttons[1][1].getText().equals(what)&&buttons[2][2].getText().equals(what)) {
            end(what,0,0,1,1,2,2, load);
        }

        if (buttons[0][2].getText().equals(what)&&buttons[1][1].getText().equals(what)&&buttons[2][0].getText().equals(what)) {
            end(what,0,2,1,1,2,0, load);
        }
    }

    public void end(String winner, int cordx1, int cordy1, int cordx2, int cordy2, int cordx3, int cordy3, boolean load) {
        buttons[3][0].setText("Gracz " + winner + " wygrał.\nKliknij, aby rozpocząć nową grę.");

        if (ButtonOnClick.gameended==false) {
            if (!load) {
                if (winner=="X") {
                    xwin++;
                } else {
                    owin++;
                }
            }
        }

        buttons[3][1].setText("Wygrane X: " + xwin + "\nWygrane O: " + owin + "\nRemisy: " + nowin);
        buttons[cordx1][cordy1].setTextColor(color2);
        buttons[cordx2][cordy2].setTextColor(color2);
        buttons[cordx3][cordy3].setTextColor(color2);
        ButtonOnClick.gameended = true;
    }

    public void restart() {
        ButtonOnClick.gameended = false;
        ButtonOnClick.isOMoveOnStart = !ButtonOnClick.isOMoveOnStart;
        ButtonOnClick.isOMove = ButtonOnClick.isOMoveOnStart;

        if (ButtonOnClick.isOMove) {
            who = "O";
        } else {
            who = "X";
        }

        buttons[3][0].setText("Kolej gracza " + who);

        for (int m=0;m<3;m++) {
            for (int n=0;n<3;n++) {
                buttons[m][n].setText("");
                buttons[m][n].setTextColor(color);
            }
        }

        if (activity.singleplayer && who == "O") {
            ai.aiMove();

            ButtonOnClick.isOMove = !ButtonOnClick.isOMove;

            if (ButtonOnClick.isOMove) {
                who = "O";
            } else {
                who = "X";
            }

            iswin(false);
        }
    }

    public String saveProgress() {
        String progress = "";

        progress = "g" + readState(ButtonOnClick.gameended) + ":m" + readState(ButtonOnClick.isOMove) + ":s" + readState(ButtonOnClick.isOMoveOnStart) + saveBoard();

        return(progress);
    }

    public String readState(boolean var) {
        String state = "";

        if (var) {
            state = "t";
        } else {
            state = "f";
        }

        return(state);
    }

    public String saveBoard() {
        String boardState = "";
        String rob = "";

        for (int x=0;x<3;x++) {
            for (int y=0;y<3;y++) {
                if (buttons[x][y].getText().equals("X")) {
                    rob = ":" + x + y + "x";
                } else if (buttons[x][y].getText().equals("O")) {
                    rob = ":" + x + y + "o";
                } else {
                    rob = ":" + x + y + "n";
                }
                boardState = boardState + rob;
            }
        }

        return(boardState);
    }

    public void loadProgress(String progressToLoad, int xw, int ow, int nw) {
        ButtonOnClick.gameended = false;
        ButtonOnClick.isOMove = getState(progressToLoad.substring(4,5));
        ButtonOnClick.isOMoveOnStart = getState(progressToLoad.substring(7,8));

        buttons[0][0].setText(getBoard(progressToLoad.substring(11,12)));
        buttons[0][1].setText(getBoard(progressToLoad.substring(15,16)));
        buttons[0][2].setText(getBoard(progressToLoad.substring(19,20)));

        buttons[1][0].setText(getBoard(progressToLoad.substring(23,24)));
        buttons[1][1].setText(getBoard(progressToLoad.substring(27,28)));
        buttons[1][2].setText(getBoard(progressToLoad.substring(31,32)));

        buttons[2][0].setText(getBoard(progressToLoad.substring(35,36)));
        buttons[2][1].setText(getBoard(progressToLoad.substring(39,40)));
        buttons[2][2].setText(getBoard(progressToLoad.substring(43,44)));

        xwin = xw;
        owin = ow;
        nowin = nw;
        buttons[3][1].setText("Wygrane X: " + xwin + "\nWygrane O: " + owin + "\nRemisy: " + nowin);

        for (int m=0;m<3;m++) {
            for (int n=0;n<3;n++) {
                buttons[m][n].setTextColor(color);
            }
        }

        if (ButtonOnClick.isOMove) {
            who = "O";
        } else {
            who = "X";
        }

        iswin(true);
    }

    public boolean getState(String var) {
        boolean state;

        if (var.contentEquals("t")) {
            state = true;
        } else {
            state = false;
        }

        return(state);
    }

    public CharSequence getBoard(String var) {
        CharSequence value;

        if (var.contentEquals("x")) {
            value = "X";
        } else if (var.contentEquals("o")) {
            value = "O";
        } else {
            value = "";
        }

        return(value);
    }
}