package pl.edu.kasprzak.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends Activity {

    private Button btnOK;

    private Button btnBlackText;
    private Button btnBlueText;
    private Button btnGreenText;
    private Button btnYellowText;
    private Button btnRedText;
    private Button btnPreviewText;

    private Button btnBlackWin;
    private Button btnBlueWin;
    private Button btnGreenWin;
    private Button btnYellowWin;
    private Button btnRedWin;
    private Button btnPreviewWin;

    private String state;
    private int xwin;
    private int owin;
    private int nowin;
    public int color = Color.BLACK;
    public int color2 = Color.RED;
    public boolean singleplayer;
    public int difficulty;

    private static final String PREFERENCES_NAME = "MyPreferences";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        btnPreviewText = (Button) findViewById(R.id.btnPreviewText);
        btnPreviewText.setBackgroundColor(color);

        btnPreviewWin = (Button) findViewById(R.id.btnPreviewWin);
        btnPreviewWin.setBackgroundColor(color2);

        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startMainActivity);
                finish();

            }
        });

        btnBlackText = (Button) findViewById(R.id.btnBlackText);
        btnBlackText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewText.setBackgroundColor(Color.BLACK);
                color = Color.BLACK;
            }
        });

        btnBlueText = (Button) findViewById(R.id.btnBlueText);
        btnBlueText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewText.setBackgroundColor(Color.BLUE);
                color = Color.BLUE;
            }
        });

        btnGreenText = (Button) findViewById(R.id.btnGreenText);
        btnGreenText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewText.setBackgroundColor(Color.GREEN);
                color = Color.GREEN;
            }
        });

        btnYellowText = (Button) findViewById(R.id.btnYellowText);
        btnYellowText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewText.setBackgroundColor(Color.YELLOW);
                color = Color.YELLOW;
            }
        });

        btnRedText = (Button) findViewById(R.id.btnRedText);
        btnRedText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewText.setBackgroundColor(Color.RED);
                color = Color.RED;
            }
        });

        btnBlackWin = (Button) findViewById(R.id.btnBlackWin);
        btnBlackWin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewWin.setBackgroundColor(Color.BLACK);
                color2 = Color.BLACK;
            }
        });

        btnBlueWin = (Button) findViewById(R.id.btnBlueWin);
        btnBlueWin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewWin.setBackgroundColor(Color.BLUE);
                color2 = Color.BLUE;
            }
        });

        btnGreenWin = (Button) findViewById(R.id.btnGreenWin);
        btnGreenWin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewWin.setBackgroundColor(Color.GREEN);
                color2 = Color.GREEN;
            }
        });

        btnYellowWin = (Button) findViewById(R.id.btnYellowWin);
        btnYellowWin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewWin.setBackgroundColor(Color.YELLOW);
                color2 = Color.YELLOW;
            }
        });

        btnRedWin = (Button) findViewById(R.id.btnRedWin);
        btnRedWin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPreviewWin.setBackgroundColor(Color.RED);
                color2 = Color.RED;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString("start", state);
        preferencesEditor.putInt("xwin", xwin);
        preferencesEditor.putInt("owin", owin);
        preferencesEditor.putInt("nowin", nowin);
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
        btnPreviewText.setBackgroundColor(color);
        btnPreviewWin.setBackgroundColor(color2);
    }
}