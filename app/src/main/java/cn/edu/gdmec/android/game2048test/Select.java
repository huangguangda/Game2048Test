package cn.edu.gdmec.android.game2048test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.adsmogo.interstitial.AdsMogoInterstitialManager;
import com.adsmogo.util.AdsMogoLayoutPosition;

/**
 * Created by Jack on 2018/1/14.
 */


public class Select extends Activity implements View.OnClickListener {
    // 三个按钮
    private Button btStart;
    private Button btRule;
    private Button btAbout;
    // 芒果聚合平台id
    private String mogoID = "b02da412b8504d32a025fd1a42ba23fc";

    private long exitTime = 0;

    /**
     * 再按一次退出程序 代码 可直接复制到程序中使用 包含上面的 private long exitTime = 0;
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除标题栏
        // 注意：该句代码要放在任何显示view代码之前否则会报错，建议放在onCreate之后，紧邻onCreate
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        // 将main.xml布局显示出来
        setContentView(R.layout.main);

        // 芒果聚合平台广告使用
        /**
         * 初始化全插屏的mogoID(全插屏全局有效)
         */
        AdsMogoInterstitialManager.setDefaultInitAppKey(mogoID);
        /**
         * 设置全插屏展示的activity
         */
        AdsMogoInterstitialManager.setInitActivity(this);

        // 初始化(必须先设置默认的AppKey和Activity对象才能通过此方法初始化SDK)
        // 显示插屏
        AdsMogoInterstitialManager.shareInstance().initDefaultInterstitial();
        AdsMogoInterstitialManager.shareInstance().defaultInterstitialShow(true);
        AdsMogoInterstitialManager.shareInstance().defaultInterstitial().interstitialShow(true);
        // 显示广告条
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
        // 初始化按钮
        btStart = (Button) findViewById(R.id.btStart);
        btRule = (Button) findViewById(R.id.btRule);
        btAbout = (Button) findViewById(R.id.btAbout);
        // 为按钮设置监听器，因为该类implements
        // OnClickListener，故用此形式调用监听器，点击执行的方法在下面重写的onClick
        btStart.setOnClickListener(this);
        btAbout.setOnClickListener(this);
        btRule.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btStart:
                // 用Intent来打开另外一个activity
                Intent intent = new Intent(Select.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.btRule:
                Intent intent2 = new Intent(Select.this, Rule.class);
                startActivity(intent2);

                break;
            case R.id.btAbout:
                Intent intent3 = new Intent(Select.this, About.class);
                startActivity(intent3);

                break;

        }

    }

}
