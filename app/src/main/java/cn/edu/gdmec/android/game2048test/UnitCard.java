package cn.edu.gdmec.android.game2048test;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Jack on 2018/1/14.
 */


public class UnitCard extends FrameLayout {
    private int num;
    private TextView card;

    /**
     * 获得卡片所对应的数字
     *
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     * 设置卡片上的数字
     *
     * @param num
     *
     */
    public void setNum(int num) {
        this.num = num;
        if (num == 0) {
            card.setText("");
        } else {
            card.setText(num + "");
        }
    }

    /**
     * 设置每个卡片的颜色
     *
     * @param color
     *
     */
    public void setbackColor(int color) {
        card.setBackgroundColor(color);

    }

    /**
     *提供构造函数
     *
     * @param context
     */
    public UnitCard(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        card = new TextView(getContext());
        card.setTextSize(30);
        // card.setBackgroundColor(0xffffdead);
        card.setGravity( Gravity.CENTER);
        LayoutParams lParams = new LayoutParams(-1, -1);
        lParams.setMargins(10, 10, 0, 0);
        addView(card, lParams);

        setNum(0);

    }

}
