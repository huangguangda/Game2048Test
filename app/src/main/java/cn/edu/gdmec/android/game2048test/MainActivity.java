package cn.edu.gdmec.android.game2048test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.adsmogo.interstitial.AdsMogoInterstitialManager;
import com.adsmogo.util.AdsMogoLayoutPosition;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvMaxScore;
    private Button btRestart;
    private GameView gameView;

    public static MainActivity mainActivity;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int score, maxscore;

    public MainActivity(){
        mainActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate ( savedInstanceState );

        pref = getSharedPreferences ( "data", MODE_PRIVATE );
        if (pref.contains ( "MaxScore" )){
            maxscore = getData();
        }

        setContentView ( R.layout.activity_main );

        /**
         * 设置全插屏展示的activity
         */
        AdsMogoInterstitialManager.setInitActivity(this);
        // 初始化(必须先设置默认的AppKey和Activity对象才能通过此方法初始化SDK)
        AdsMogoInterstitialManager.shareInstance().initDefaultInterstitial();
        AdsMogoInterstitialManager.shareInstance().defaultInterstitialShow(true);
        AdsMogoInterstitialManager.shareInstance().defaultInterstitial().interstitialShow(true);
        AdsMogoLayout adsMogoLayoutCode = new AdsMogoLayout(this, "b02da412b8504d32a025fd1a42ba23fc",
                AdsMogoLayoutPosition.CENTER_BOTTOM, 0, false);
        adsMogoLayoutCode.setAdsMogoListener(new AdsMogoListener () {

            @Override
            public void onRequestAd(String arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onReceiveAd(ViewGroup arg0, String arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onRealClickAd() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onInitFinish() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFailedReceiveAd() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onCloseMogoDialog() {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onCloseAd() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onClickAd(String arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public Class getCustomEvemtPlatformAdapterClass(AdsMogoCustomEventPlatformEnum arg0) {
                // TODO Auto-generated method stub
                return null;
            }
        });

        tvScore = (TextView) findViewById(R.id.textscore);

        tvMaxScore = (TextView) findViewById(R.id.texMaxscore);

        showMaxScore();

        btRestart = (Button) findViewById(R.id.btRe);
        gameView = (GameView) findViewById(R.id.gvGameView);
        btRestart.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("亲爱的玩家").setIcon(R.drawable.ico2048).setMessage("你确定要重新开始游戏吗？");
                builder.setNegativeButton("重新开始", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        gameView.startGame();// ���¿�ʼ��Ϸ
                    }
                });
                builder.setPositiveButton("清除最高分", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        clearMaxScore();
                    }
                });
                builder.show();

            }
        });

    }


    private int getData() {

        int MaxScore = pref.getInt("MaxScore", maxscore);
        maxscore = MaxScore;
        return maxscore;
    }

    private void saveDate() {
        editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putInt("MaxScore", maxscore);
        editor.commit();
    }

    @Override
    protected void onPause() {
        saveDate();
        // TODO Auto-generated method stub
        super.onPause();

    }

    public void clearScore() {
        score = 0;
        showScore();

    }

    public void addScore(int s) {
        score += s;
        showScore();

    }

    public int getScore() {
        return score;

    }

    private void showScore() {
        // TODO Auto-generated method stub
        tvScore.setText(score + "");

    }

    public void clearMaxScore() {

        maxscore = 0;
        showMaxScore();

    }

    public void getMaxScore() {
        if (score > maxscore) {
            maxscore = score;
        }
        showMaxScore();

    }

    private void showMaxScore() {
        // TODO Auto-generated method stub
        tvMaxScore.setText(maxscore + "");

    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }
}
