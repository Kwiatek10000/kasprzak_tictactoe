package pl.edu.kasprzak.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = new Board(this);
        board.setButton((Button)findViewById(R.id.b00), 0, 0);
        board.setButton((Button)findViewById(R.id.b01), 0, 1);
        board.setButton((Button)findViewById(R.id.b02), 0, 2);
        board.setButton((Button)findViewById(R.id.b10), 1, 0);
        board.setButton((Button)findViewById(R.id.b11), 1, 1);
        board.setButton((Button)findViewById(R.id.b12), 1, 2);
        board.setButton((Button)findViewById(R.id.b20), 2, 0);
        board.setButton((Button)findViewById(R.id.b21), 2, 1);
        board.setButton((Button)findViewById(R.id.b22), 2, 2);
    }
}
//Tutaj nie zmieniłem nic...