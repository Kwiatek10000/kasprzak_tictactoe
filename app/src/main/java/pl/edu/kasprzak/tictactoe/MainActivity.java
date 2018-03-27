package pl.edu.kasprzak.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Board board;
    private Button btnSettings;
    private Button btnBack;
    private String state = "";
    private int xwin;
    private int owin;
    private int nowin;
    public int color = Color.BLACK;
    public int color2 = Color.BLACK;
    public boolean singleplayer;
    public int difficulty;
    private static final String PREFERENCES_NAME = "MyPreferences";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        board = new Board(this);

        board.setButton((Button) findViewById(R.id.b00), 0, 0);
        board.setButton((Button) findViewById(R.id.b01), 0, 1);
        board.setButton((Button) findViewById(R.id.b02), 0, 2);
        board.setButton((Button) findViewById(R.id.b10), 1, 0);
        board.setButton((Button) findViewById(R.id.b11), 1, 1);
        board.setButton((Button) findViewById(R.id.b12), 1, 2);
        board.setButton((Button) findViewById(R.id.b20), 2, 0);
        board.setButton((Button) findViewById(R.id.b21), 2, 1);
        board.setButton((Button) findViewById(R.id.b22), 2, 2);
        board.setButton((Button) findViewById(R.id.b30), 3, 0);
        board.setButton((Button) findViewById(R.id.b31), 3, 1);

        btnSettings = (Button) findViewById(R.id.b32);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startSettings = new Intent(getApplicationContext(), Settings.class);
                startActivity(startSettings);
                finish();
            }
        });

        btnBack = (Button) findViewById(R.id.b33);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startLaunchApp = new Intent(getApplicationContext(), LaunchApp.class);
                startActivity(startLaunchApp);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = board.saveProgress();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString("start", state);
        preferencesEditor.putInt("xwin", board.xwin);
        preferencesEditor.putInt("owin", board.owin);
        preferencesEditor.putInt("nowin", board.nowin);
        preferencesEditor.putInt("key", color);
        preferencesEditor.putInt("key2", color2);
        preferencesEditor.putBoolean("singleplayer", singleplayer);
        preferencesEditor.putInt("difficulty", difficulty);
        preferencesEditor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        state = preferences.getString("start", "");
        xwin = preferences.getInt("xwin", 0);
        owin = preferences.getInt("owin", 0);
        nowin = preferences.getInt("nowin", 0);
        color = preferences.getInt("key", 0);
        color2 = preferences.getInt("key2", 0);
        singleplayer = preferences.getBoolean("singleplayer",true);
        difficulty = preferences.getInt("difficulty", difficulty);

        board.setColor(color, color2);
        board.loadProgress(state, xwin, owin, nowin);
    }
}