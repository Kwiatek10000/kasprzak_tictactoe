package pl.edu.kasprzak.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LaunchApp extends AppCompatActivity{

    private String startState = "gf:mf:sf:00n:01n:02n:10n:11n:12n:20n:21n:22n";
    private Boolean single;
    private int difficulty;
    private SharedPreferences preferences;
    private static final String PREFERENCES_NAME = "MyPreferences";
    private Button singleplayer;
    private Button multiplayer;
    private Button easy;
    private Button medium;
    private Button imposible;
    private LinearLayout firstMenu;
    private LinearLayout secondMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchapp);

        singleplayer = (Button) findViewById(R.id.singleplayer);
        multiplayer = (Button) findViewById(R.id.multiplayer);
        easy = (Button) findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        imposible = (Button) findViewById(R.id.imposible);

        firstMenu = (LinearLayout) findViewById(R.id.firsMenu);
        secondMenu = (LinearLayout) findViewById(R.id.secondMenu);

        firstMenu.setVisibility(View.VISIBLE);
        secondMenu.setVisibility(View.INVISIBLE);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        singleplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                single = true;
                firstMenu.setVisibility(View.INVISIBLE);
                secondMenu.setVisibility(View.VISIBLE);
            }
        });

        multiplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                single = false;
                difficulty = 0;
                launch();
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                difficulty = 1;
                launch();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                difficulty = 2;
                launch();
            }
        });

        imposible.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                difficulty = 3;
                launch();
            }
        });
    }

    public void launch() {
        final SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString("start", startState);
        preferencesEditor.putInt("xwin", 0);
        preferencesEditor.putInt("owin", 0);
        preferencesEditor.putInt("nowin", 0);
        preferencesEditor.putInt("key", Color.BLACK);
        preferencesEditor.putInt("key2", Color.RED);
        preferencesEditor.putBoolean("singleplayer", single);
        preferencesEditor.putInt("difficulty", difficulty);
        preferencesEditor.commit();

        Intent startMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startMainActivity);
        finish();
    }
}