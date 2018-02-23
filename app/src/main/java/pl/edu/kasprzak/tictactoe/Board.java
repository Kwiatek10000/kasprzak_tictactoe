package pl.edu.kasprzak.tictactoe;

import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;

public class Board {
    private Button buttons[][];
    private MainActivity activity;
    private String textonboard = "";
    //Dodałem zmienną potrzebną do sprawdzenia remisu.
    public Board(MainActivity activity) {
        this.activity = activity;
        buttons = new Button[3][3];
    }
    public void setButton(Button button, int x, int y) {
        buttons[x][y] = button;
        buttons[x][y].setTextColor(Color.BLACK);
        //Ustawiam domyślmny kolor czcionki na przyciskach na czarny.
        button.setOnClickListener(new ButtonOnClick(button, this));
    }
    public void iswin() {
        //Funkcja sprawdzająca czy pojawiła się wygrana.
        check("X");
        //Wywołuję funkcję sprawdzającą czy X wygrał.
        check("O");
        //Wywołuję funkcję sprawdzającą czy O wygrało.
        textonboard="";
        for (int a=0;a<3;a++) {
            for (int b=0;b<3;b++) {
                textonboard = textonboard + buttons[a][b].getText();
                //Spisuję stan planszy do zmiennej.
            }
        }
        if (textonboard.length()==9) {
            //Sprawdzam długość zmiennej ze stanem planszy. Jeśli jest równa 9 to znaczy, że wszystkie pola są zapełnione, a nie padła wygrana co oznacza remis.
            Toast toast = Toast.makeText(this.activity.getApplicationContext(), "Gra zakończyła się remisem. Kliknij, aby rozpocząć nową grę.", Toast.LENGTH_LONG);
            toast.show();
            //Wyświetlenie komunikatu o remisie.
            ButtonOnClick.gameended = true;
            //Zmieniam zmienną, aby przekazać, że rozgrywka się zakończyła.
        }
    }
    public void check(String what) {
        //Funkcja sprawdza czy padła wygrana dla podanego jako argument znaku.
        for (int x=0;x<3;x++) {
            if (buttons[x][0].getText().equals(what)&&buttons[x][1].getText().equals(what)&&buttons[x][2].getText().equals(what)) {
                //Sprawdzenie możliwości wygranych w wierszach.
                end(what,x,0,x,1,x,2);
                //Wywołanie funcji kończącej grę z podaniem zwycięzcy oraz współrzędnych pól, na których padła wygrana.
            }
        }
        for (int y=0;y<3;y++) {
            if (buttons[0][y].getText().equals(what)&&buttons[1][y].getText().equals(what)&&buttons[2][y].getText().equals(what)) {
                //Sprawdzanie możliwości wygranych w kolumnach.
                end(what,0,y,1,y,2,y);
            }
        }
        if (buttons[0][0].getText().equals(what)&&buttons[1][1].getText().equals(what)&&buttons[2][2].getText().equals(what)) {
            //Sprawdzanie możliwości wygranych po pierwszym skosie.
            end(what,0,0,1,1,2,2);
        }
        if (buttons[0][2].getText().equals(what)&&buttons[1][1].getText().equals(what)&&buttons[2][0].getText().equals(what)) {
            //Sprawdzanie możliwości wygranych po drugim skosie.
            end(what,0,2,1,1,2,0);
        }
    }
    public void end(String winner, int cordx1, int cordy1, int cordx2, int cordy2, int cordx3, int cordy3) {
        //Funkcja kończąca grę.
        Toast toast = Toast.makeText(this.activity.getApplicationContext(), "Gracz " + winner + " wygrał. Kliknij, aby rozpocząć nową grę.", Toast.LENGTH_LONG);
        toast.show();
        //Wyświetlenie komunikatu o zwycięstwie podanego gracza.
        ButtonOnClick.gameended = true;
        //Nadanie, że rozgrywka się zakończyła.
        buttons[cordx1][cordy1].setTextColor(Color.RED);
        buttons[cordx2][cordy2].setTextColor(Color.RED);
        buttons[cordx3][cordy3].setTextColor(Color.RED);
        //Zmiana koloru czcionki na czerwony na polach, na których padła wygrana (oznaczenie gdzie padła wygrana)
    }
    public void restart() {
        //Funkcja restartująca planszę.
        ButtonOnClick.gameended = false;
        ButtonOnClick.isOMove = false;
        //Przypisanie domyślnych wartości zmiennym odpowiedzianym za trwanie rozgrywki i ruch konkretnego gracza.
        for (int m=0;m<3;m++) {
            for (int n=0;n<3;n++) {
                buttons[m][n].setText("");
                buttons[m][n].setTextColor(Color.BLACK);
                //Ustawienie wszystkim przyciskom pustego tekstu i czarnego koloru czcionki.
            }
        }
    }
}


