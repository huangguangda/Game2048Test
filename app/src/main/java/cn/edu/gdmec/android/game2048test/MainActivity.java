package cn.edu.gdmec.android.game2048test;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvMaxScore;
    private Button btRestart;
    private GameView gameView;

    public static MainActivity mainActivity;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int score, maxscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
    }
}
