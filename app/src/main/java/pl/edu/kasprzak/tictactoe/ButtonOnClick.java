package pl.edu.kasprzak.tictactoe;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class ButtonOnClick implements View.OnClickListener {

    public static boolean gameended = false;
    public static boolean isOMoveOnStart = false;
    public static boolean isOMove = false;
    private Board board;
    private Button button;
    private MainActivity activity;
    private AI ai;

    public ButtonOnClick(Button button, Board board, MainActivity activity, AI ai) {
        this.button = button;
        this.board = board;
        this.activity = activity;
        this.ai = ai;
    }

    @Override
    public void onClick(View view) {
        if (gameended==false) {
            if (button.getText().length() == 0) {
                if (!activity.singleplayer) {

                    if (isOMove) {
                        button.setText("O");
                    } else {
                        button.setText("X");
                    }

                    isOMove = !isOMove;

                    if (isOMove) {
                        board.who = "O";
                    } else {
                        board.who = "X";
                    }

                    board.iswin(false);

                } else {
                    if (!isOMove) {
                        button.setText("X");

                        isOMove = !isOMove;

                        if (isOMove) {
                            board.who = "O";
                        } else {
                            board.who = "X";
                        }

                        board.iswin(false);

                        if (!gameended) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            ai.aiMove();

                            isOMove = !isOMove;

                            if (isOMove) {
                                board.who = "O";
                            } else {
                                board.who = "X";
                            }

                            board.iswin(false);
                        }
                    }
                }
            }
        } else {
            board.restart();
        }
    }
}