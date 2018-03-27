package pl.edu.kasprzak.tictactoe;

import java.util.Random;

public class AI extends MainActivity {

    private MainActivity activity;
    private Board board;
    private int xcord;
    private int ycord;
    private boolean done;
    private String combination;

    public AI(Board board, MainActivity activity) {
        this.board = board;
        this.activity = activity;
    }

    public void aiMove() {
        if (activity.difficulty == 1) {
            if (findDanger("O") == 0) {
                done = false;
                while (done==false) {
                    xcord = new Random().nextInt(3);
                    ycord = new Random().nextInt(3);

                    if (board.buttons[xcord][ycord].getText().length() == 0) {
                        board.buttons[xcord][ycord].setText("O");
                        done = true;
                    }
                }
            }
        } else if (activity.difficulty == 2) {
            if (findDanger("O") == 0) {
                if (findDanger("X") == 0) {
                    done = false;
                    while (done==false) {
                        xcord = new Random().nextInt(3);
                        ycord = new Random().nextInt(3);

                        if (board.buttons[xcord][ycord].getText().length() == 0) {
                            board.buttons[xcord][ycord].setText("O");
                            done = true;
                        }
                    }
                }
            }
        } else if (activity.difficulty == 3) {
            if (findDanger("O") == 0) {
                if (findDanger("X") == 0) {
                    if (checkConditions() == 0) {
                        done = false;
                        while (done==false) {
                            xcord = new Random().nextInt(3);
                            ycord = new Random().nextInt(3);

                            if (board.buttons[xcord][ycord].getText().length() == 0) {
                                board.buttons[xcord][ycord].setText("O");
                                done = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public int findDanger(String what) {
        for (int x=0;x<3;x++) {
            if (board.buttons[x][0].getText().equals(what)&&board.buttons[x][1].getText().equals(what)&&board.buttons[x][2].getText().equals("")) {
                board.buttons[x][2].setText("O");
                return(1);
            } else if (board.buttons[x][0].getText().equals(what)&&board.buttons[x][1].getText().equals("")&&board.buttons[x][2].getText().equals(what)) {
                board.buttons[x][1].setText("O");
                return(1);
            } else if (board.buttons[x][0].getText().equals("")&&board.buttons[x][1].getText().equals(what)&&board.buttons[x][2].getText().equals(what)) {
                board.buttons[x][0].setText("O");
                return(1);
            }

            if (board.buttons[0][x].getText().equals(what)&&board.buttons[1][x].getText().equals(what)&&board.buttons[2][x].getText().equals("")) {
                board.buttons[2][x].setText("O");
                return(1);
            } else if (board.buttons[0][x].getText().equals(what)&&board.buttons[1][x].getText().equals("")&&board.buttons[2][x].getText().equals(what)) {
                board.buttons[1][x].setText("O");
                return(1);
            } else if (board.buttons[0][x].getText().equals("")&&board.buttons[1][x].getText().equals(what)&&board.buttons[2][x].getText().equals(what)) {
                board.buttons[0][x].setText("O");
                return(1);
            }
        }

        if (board.buttons[0][0].getText().equals(what)&&board.buttons[1][1].getText().equals(what)&&board.buttons[2][2].getText().equals("")) {
            board.buttons[2][2].setText("O");
            return(1);
        } else if (board.buttons[0][0].getText().equals(what)&&board.buttons[1][1].getText().equals("")&&board.buttons[2][2].getText().equals(what)) {
            board.buttons[1][1].setText("O");
            return(1);
        } else if (board.buttons[0][0].getText().equals("")&&board.buttons[1][1].getText().equals(what)&&board.buttons[2][2].getText().equals(what)) {
            board.buttons[0][0].setText("O");
            return(1);
        }

        if (board.buttons[0][2].getText().equals(what)&&board.buttons[1][1].getText().equals(what)&&board.buttons[2][0].getText().equals("")) {
            board.buttons[2][0].setText("O");
            return(1);
        } else if (board.buttons[0][2].getText().equals(what)&&board.buttons[1][1].getText().equals("")&&board.buttons[2][0].getText().equals(what)) {
            board.buttons[1][1].setText("O");
            return(1);
        } else if (board.buttons[0][2].getText().equals("")&&board.buttons[1][1].getText().equals(what)&&board.buttons[2][0].getText().equals(what)) {
            board.buttons[0][2].setText("O");
            return(1);
        }

        return(0);
    }

    public int checkConditions() {
        combination = "";
        for (int a=0;a<3;a++) {
            for (int b=0;b<3;b++) {
                if (board.buttons[a][b].getText().equals("")) {
                    combination = combination + "N";
                } else {
                    combination = combination + board.buttons[a][b].getText();
                }
            }
        }

        if (        combination.contentEquals("NNNNNXOXO") ||
                    combination.contentEquals("NNXNNNOXO") ||
                    combination.contentEquals("NNNNNNOXN") ||
                    combination.contentEquals("NNNNNNONX") ||
                    combination.contentEquals("NXNXONNNN") ||
                    combination.contentEquals("NNXXONNNN") ||
                    combination.contentEquals("NXNNONXNN") ||
                    combination.contentEquals("NNXNXNONN")) {

            board.buttons[0][0].setText("O");
            return(1);

        } else if ( combination.contentEquals("NNNNXNONN") ||
                    combination.contentEquals("NNNXNNOXO") ||
                    combination.contentEquals("XNNNNNOXO") ||
                    combination.contentEquals("ONNXNNOXN") ||
                    combination.contentEquals("ONNXNNONX") ||
                    combination.contentEquals("NXNNOXNNN") ||
                    combination.contentEquals("NXNNONNNX") ||
                    combination.contentEquals("XNNNOXNNN")) {

            board.buttons[0][2].setText("O");
            return(1);

        } else if ( combination.contentEquals("NNNNNNNNN") ||
                    combination.contentEquals("NNNNXNNNN") ||
                    combination.contentEquals("NNNXONNXN") ||
                    combination.contentEquals("XNNNONNXN") ||
                    combination.contentEquals("NNNXONNNX")) {

            board.buttons[2][0].setText("O");
            return(1);

        } else if ( combination.contentEquals("NXNNNNONN") ||
                    combination.contentEquals("NNNNNXONN") ||
                    combination.contentEquals("NNXNNNONN") ||
                    combination.contentEquals("NNNXNNONN") ||
                    combination.contentEquals("XNNNNNONN") ||
                    combination.contentEquals("NNNNOXNXN") ||
                    combination.contentEquals("NNNNOXXNN") ||
                    combination.contentEquals("NNXNONNXN") ||
                    combination.contentEquals("NXNNONNXN") ||
                    combination.contentEquals("NNNXOXNNN")) {

            board.buttons[2][2].setText("O");
            return(1);

        } else if ( combination.contentEquals("XNNNNNNNN") ||
                    combination.contentEquals("NXNNNNNNN") ||
                    combination.contentEquals("NNXNNNNNN") ||
                    combination.contentEquals("NNNXNNNNN") ||
                    combination.contentEquals("NNNNNXNNN") ||
                    combination.contentEquals("NNNNNNXNN") ||
                    combination.contentEquals("NNNNNNNXN") ||
                    combination.contentEquals("NNNNNNNNX")) {

            board.buttons[1][1].setText("O");
            return(1);

        } else if ( combination.contentEquals("NNXNONXNN") ||
                    combination.contentEquals("XNNNONNNX")) {

            board.buttons[2][1].setText("O");
            return(1);

        }

        return(0);
    }
}
