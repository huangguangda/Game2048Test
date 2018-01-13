package cn.edu.gdmec.android.game2048test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2018/1/14.
 */

public class GameView extends GridLayout {

    private int row = 4;// 游戏界面行数、列数

    private UnitCard[][] cards = new UnitCard[row][row];// 创建二维数组来存储每个数字卡片对象
    private List<Point> emptyPoint = new ArrayList<Point> ();

    /**
     * 构造函数 该形式的构造函数用于直接在activity内调用 该程序实例中 该构造器并不起作用，可以删除该段代码
     */
    public GameView(Context context) {
        super(context);

        initGameView();

    }

    /**
     * 构造函数 在布局文件中调用是使用该构造函数
     */
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initGameView();

    }

    /**
     * 初始化游戏界面
     */
    private void initGameView() {
        // TODO Auto-generated method stub
        setColumnCount(row);// 设置GridLayout的列数
        setRowCount(row);// 设置GridLayout的行数
        setBackgroundColor(0xffffefd5);

        /**
         * 触摸监听器
         */
        setOnTouchListener(new OnTouchListener() {
            private float startX, startY, moveX, moveY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // 判断手指滑动的方向
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        moveX = event.getX() - startX;
                        moveY = event.getY() - startY;
                        if (Math.abs(moveX) > Math.abs(moveY)) {
                            if (moveX < -5) {
                                // 执行向左滑动的方法
                                left();
                                // 设置卡片的背景色
                                setbackColor();

                            }
                            if (moveX > 5) {
                                right();
                                setbackColor();

                            }
                        } else {
                            if (moveY > 5) {
                                down();
                                setbackColor();

                            }
                            if (moveY < -5) {
                                up();
                                setbackColor();

                            }
                        }
                        break;

                    default:
                        break;
                }

                return true;
            }
        });

    }

    /**
     * 适应不同屏幕，获得屏幕的尺寸
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        int unitCardSize = (Math.min(w, h) - 10) / row;
        addunitCard(unitCardSize, unitCardSize);
        startGame();

    }

    /**
     * 添加数字卡片
     *
     * @param uCardWidth
     *            卡片宽度
     * @param uCardHeight
     *            卡片高度
     */
    public void addunitCard(int uCardWidth, int uCardHeight) {
        UnitCard uCard;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {
                uCard = new UnitCard(getContext());
                uCard.setNum(0);
                addView(uCard, uCardWidth, uCardHeight);
                cards[x][y] = uCard;

            }

        }
    }

    /**
     * 初始化游戏刚开始的界面
     */
    public void startGame() {
        MainActivity.getMainActivity().clearScore();
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {
                cards[x][y].setNum(0);//将界面初始化为空
            }
        }
//		加入两个随机数字
        addRandomNum();
        addRandomNum();
        setbackColor();

    }

    /**
     * 添加随机数
     */
    public void addRandomNum() {
        emptyPoint.clear();
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {
                if (cards[x][y].getNum() == 0) {
                    emptyPoint.add(new Point(x, y));//

                }
            }
        }

        Point p = emptyPoint.remove((int) (Math.random() * emptyPoint.size()));
        cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }

    /**
     * 向左滑动的方法
     */
    private void left() {

        boolean judge = false;

        for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {
                for (int x1 = x + 1; x1 < row; x1++) {
                    if (cards[x1][y].getNum() > 0) {
                        if (cards[x][y].getNum() == 0) {
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x1][y].setNum(0);
                            judge = true;
                            x--;

                        } else if (cards[x][y].getNum() == cards[x1][y].getNum()) {

                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            MainActivity.getMainActivity().addScore(cards[x][y].getNum());
                            MainActivity.getMainActivity().getMaxScore();
                            cards[x1][y].setNum(0);
                            judge = true;

                        }
                        break;

                    }
                }
            }

        }
        if (judge) {
            checkWin();
            addRandomNum();
            checkComplete();
        }

    }

    /**
     * 向右滑动的方法
     */
    private void right() {
        boolean judge = false;
        for (int y = 0; y < row; y++) {
            for (int x = row - 1; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cards[x1][y].getNum() > 0) {
                        if (cards[x][y].getNum() == 0) {
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x1][y].setNum(0);

                            x++;
                            judge = true;

                        } else if (cards[x][y].getNum() == cards[x1][y].getNum()) {

                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            MainActivity.getMainActivity().addScore(cards[x][y].getNum());
                            MainActivity.getMainActivity().getMaxScore();
                            cards[x1][y].setNum(0);
                            judge = true;

                        }
                        break;

                    }
                }
            }

        }
        if (judge) {
            checkWin();
            addRandomNum();
            checkComplete();
        }

    }

    /**
     *向上滑动的方法
     */
    private void up() {
        boolean judge = false;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {
                for (int x1 = x + 1; x1 < row; x1++) {
                    if (cards[y][x1].getNum() > 0) {
                        if (cards[y][x].getNum() == 0) {
                            cards[y][x].setNum(cards[y][x1].getNum());
                            cards[y][x1].setNum(0);

                            x--;
                            judge = true;
                        } else if (cards[y][x].getNum() == cards[y][x1].getNum()) {

                            cards[y][x].setNum(cards[y][x].getNum() * 2);
                            MainActivity.getMainActivity().addScore(cards[y][x].getNum());
                            MainActivity.getMainActivity().getMaxScore();
                            cards[y][x1].setNum(0);
                            judge = true;

                        }
                        break;

                    }
                }
            }
        }
        if (judge) {
            checkWin();
            addRandomNum();
            checkComplete();
        }
    }

    /**
     * 向下滑动的方法
     */
    private void down() {
        boolean judge = false;
        for (int y = 0; y < row; y++) {
            for (int x = row - 1; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cards[y][x1].getNum() > 0) {
                        if (cards[y][x].getNum() == 0) {
                            cards[y][x].setNum(cards[y][x1].getNum());
                            cards[y][x1].setNum(0);

                            x++;
                            judge = true;
                        } else if (cards[y][x].getNum() == cards[y][x1].getNum()) {

                            cards[y][x].setNum(cards[y][x].getNum() * 2);
                            MainActivity.getMainActivity().addScore(cards[y][x].getNum());
                            MainActivity.getMainActivity().getMaxScore();
                            cards[y][x1].setNum(0);
                            judge = true;

                        }
                        break;

                    }
                }
            }

        }
        if (judge) {
            checkWin();
            addRandomNum();
            checkComplete();
        }

    }

    /**
     * 检查游戏是否结束
     */
    private void checkComplete() {
        boolean complete = true;
        All: for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {
                if (cards[x][y].getNum() == 0 || (x > 0 && cards[x][y].getNum() == (cards[x - 1][y]).getNum())
                        || (x < 3 && cards[x][y].getNum() == (cards[x + 1][y].getNum()))
                        || (y > 0 && cards[x][y].getNum() == (cards[x][y - 1]).getNum())
                        || (y < 3 && cards[x][y].getNum() == (cards[x][y + 1].getNum()))) {
                    complete = false;
                    break All;

                }

            }

        }

        if (complete) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle("亲爱的玩家").setMessage("你的得分为" + MainActivity.getMainActivity().getScore() + "分")
                    .setIcon(R.drawable.ico2048);
            dialog.setNegativeButton("退出", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.getMainActivity().finish();

                }
            });
            dialog.setPositiveButton("重新开始", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    startGame();

                }
            });
            dialog.show();
        }
    }

    /**
     * 检查是否得到2048，即游戏胜利
     */
    private void checkWin() {
        boolean complete = false;
        All: for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {
                if (cards[x][y].getNum() == 2048) {
                    complete = true;
                    break All;
                }

            }

        }

        if (complete) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle("亲爱的玩家，恭喜").setMessage("你成功得到了2048").setIcon(R.drawable.ico2048);
            dialog.setNegativeButton("退出", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.getMainActivity().finish();

                }
            });
            dialog.setPositiveButton("再来一次", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    startGame();

                }
            });
            dialog.show();

        }

    }

    /**
     * 设置卡片颜色，不同数字颜色不同
     */
    private void setbackColor() {
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {

                if (cards[x][y].getNum() == 0) {
                    cards[x][y].setbackColor(0xffffdead);
                }
                if (cards[x][y].getNum() == 2) {
                    cards[x][y].setbackColor(0xffffb90f);
                }
                if (cards[x][y].getNum() == 4) {
                    cards[x][y].setbackColor(0xffff8c00);
                }
                if (cards[x][y].getNum() == 8) {
                    cards[x][y].setbackColor(0xffff7f50);
                }
                if (cards[x][y].getNum() == 16) {
                    cards[x][y].setbackColor(0xffff6eb4);
                }
                if (cards[x][y].getNum() == 32) {
                    cards[x][y].setbackColor(0xffff3030);
                }
                if (cards[x][y].getNum() == 64) {
                    cards[x][y].setbackColor(0xffff1493);
                }
                if (cards[x][y].getNum() == 128) {
                    cards[x][y].setbackColor(0xffff00ff);
                }
                if (cards[x][y].getNum() == 256) {
                    cards[x][y].setbackColor(0xffff0000);
                }
                if (cards[x][y].getNum() == 512) {
                    cards[x][y].setbackColor(0xffe066ff);
                }
                if (cards[x][y].getNum() == 1024) {
                    cards[x][y].setbackColor(0xff7fff00);
                }
                if (cards[x][y].getNum() == 2048) {
                    cards[x][y].setbackColor(0xffffff00);
                }

            }

        }

    }
}