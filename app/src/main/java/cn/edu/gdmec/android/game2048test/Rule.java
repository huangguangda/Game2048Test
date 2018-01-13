package cn.edu.gdmec.android.game2048test;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.adsmogo.interstitial.AdsMogoInterstitialManager;
import com.adsmogo.util.AdsMogoLayoutPosition;

/**
 * Created by Jack on 2018/1/14.
 */


public class Rule extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule);
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

    }

}
