package pl.edu.kasprzak.tictactoe;

import android.view.View;
import android.widget.Button;

public class ButtonOnClick implements View.OnClickListener {
    public static boolean gameended = false;
    //Dodałem zmienną oznaczającą koniec gry i przypisuję jej wartość "false" co oznacza, że gra jeszcze trwa.
    public static boolean isOMove = false;
    //Tutaj zmieniłem na "false", bo chcę, aby X zaczynał rozgrywkę.
    private Board board;
    private Button button;
    public ButtonOnClick(Button button, Board board) {
        this.button = button;
        this.board = board;
    }
    @Override
    public void onClick(View view) {
        if (gameended==false) {
            //Sprawdzam w warunku czy gra trwa.
            if (button.getText().length() == 0) {
                if (isOMove) {
                    button.setText("O");

                } else {
                    button.setText("X");
                }
                isOMove = !isOMove;
                board.iswin();
                //Wywołuję funkcję sprawdzającą czy pojawiła się wygrana.
            }
        } else {
            board.restart();
            //Jeśli gra się zakończyła kliknięcie wywoła funkcję restartującą planszę.
        }
    }
}


