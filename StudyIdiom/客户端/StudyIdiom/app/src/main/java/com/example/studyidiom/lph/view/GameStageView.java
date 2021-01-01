package com.example.studyidiom.lph.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import com.example.studyidiom.R;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

public class GameStageView extends View {
    private Paint paint;

    private int itemHorizontalMove = 0;
    /**
     * Item to move the distance.
     */
    private int itemVerticalMove = 0;
    //字体大小
    private int textSize = (int) dpToPx(12);
    //item高度
    private int itemHeight = (int) dpToPx(20);
    //item宽度
    private int itemWidth = (int) dpToPx(50);
    /**
     * View the left margin
     */
    private int paddingLeft = (int) dpToPx(15);
    /**
     * View the top padding
     */
    private int paddingTop = (int) dpToPx(40);

    /**
     * View the top padding right
     */
    private int paddingRight = (int) dpToPx(15);
    //已经变绿的item数量
    private int itemCountRecord = 1;
    //Now you need to show it.
    private int nowPosition = 1;
    //正常的字体背景色
    private int textNormalColor = Color.parseColor("#FFFFFF");
    //未解锁的字体背景色
    private int textLockColor = Color.parseColor("#FFFFFF");
    private int itemNormalBackgroundColor = Color.parseColor("#3BD2B3");
    //未解锁的item背景色
    private int itemLockBackgroundColor = Color.parseColor("#DDDDDD");
    //每行items的数量
    private int lineColumns = Integer.parseInt(getResources().getString(R.string.lineColumns));
    //总共所有items的数量
    private int totalColumns = Integer.parseInt(getResources().getString(R.string.totalColumns));
    //水平分割线的宽度
    private int dividerHorizontalWidth = (int) dpToPx(10);
    //水平分割线的高度
    private int dividerHorizontalHeight = (int) dpToPx(5);
    //垂直分割线的宽度
    private int dividerVerticalWidth = (int) dpToPx(5);
    //垂直分割线的高度
    private int dividerVerticalHeight = (int) dpToPx(50);
    //Items canvas
    private Canvas canvas;
    /**
     * The row is total of items mod linePosition equal 0
     */
    private int fullRow;

    /**
     * The remainder of a dissatisfied row.
     */
    private int modNum;

    //当前row
    private int nowRow;

    //总行数
    private int totalRow;
    /**
     * Number of rows
     */
    private int linePosition = 0;
    //项目坐标记录
    private Map<String, String> itemCoordinates;

    public GameStageView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public GameStageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public GameStageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();
                //item 点击事件
                for (Map.Entry<String, String> entry : itemCoordinates.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    String[] coordinateStart = value.split("&")[0].split(",");
                    String[] coordinateEnd = value.split("&")[1].split(",");
                    if (Float.parseFloat(coordinateStart[0]) <= x && x <= Float.parseFloat(coordinateEnd[0]) && Float.parseFloat(coordinateStart[1]) <= y && y <= Float.parseFloat(coordinateEnd[1])) {
                        if (onGameItemClickListener != null) {
                            onGameItemClickListener.onGameItemClick((Integer.parseInt(key)), itemCountRecord <= nowPosition ? false : true);
                        }
                    }
                }
                break;

        }
        return true;
    }

    /**
     * Initializes the coordinate record and control properties.
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        itemCoordinates = new HashMap<>();
        if (attrs != null) {
            TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameStageView, defStyleAttr, 0);
            itemWidth = (int) array.getDimension(R.styleable.GameStageView_itemWidth, itemWidth);
            itemHeight = (int) array.getDimension(R.styleable.GameStageView_itemHeight, itemHeight);

            dividerHorizontalWidth = (int) array.getDimension(R.styleable.GameStageView_itemDividerHorizontalWidth, dividerHorizontalWidth);
            dividerHorizontalHeight = (int) array.getDimension(R.styleable.GameStageView_itemDividerHorizontalHeight, dividerHorizontalHeight);
            dividerVerticalWidth = (int) array.getDimension(R.styleable.GameStageView_itemDividerVerticalWidth, dividerVerticalWidth);
            dividerVerticalHeight = (int) array.getDimension(R.styleable.GameStageView_itemDividerVerticalHeight, dividerVerticalHeight);

            totalColumns = array.getInt(R.styleable.GameStageView_totalColumns, totalColumns);
            lineColumns = array.getInt(R.styleable.GameStageView_lineColumns, lineColumns);
            nowPosition = array.getInt(R.styleable.GameStageView_nowPosition, nowPosition);

            textSize = (int) array.getDimension(R.styleable.GameStageView_itemTextSize, textSize);
            textNormalColor = array.getColor(R.styleable.GameStageView_itemNormalTextColor, textNormalColor);
            textLockColor = array.getColor(R.styleable.GameStageView_itemLockTextColor, textLockColor);
            itemNormalBackgroundColor = array.getColor(R.styleable.GameStageView_itemNormalBgColor, itemNormalBackgroundColor);
            itemLockBackgroundColor = array.getColor(R.styleable.GameStageView_itemLockBgColor, itemLockBackgroundColor);
            paddingLeft = (int) array.getDimension(R.styleable.GameStageView_InnerPaddingLeft, paddingLeft);
            paddingTop = (int) array.getDimension(R.styleable.GameStageView_InnerPaddingTop, paddingTop);
            paddingRight = (int) array.getDimension(R.styleable.GameStageView_InnerPaddingRight, paddingRight);
            array.recycle();
        } else {
            Toast.makeText(getContext(), "666666666666", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        fullRow = totalColumns / lineColumns;
        modNum = totalColumns % lineColumns;
        totalRow = fullRow + (modNum == 0 ? 0 : 1);
        dividerHorizontalWidth = (getScreenWidth() - paddingLeft - paddingRight - itemWidth * lineColumns) / (lineColumns - 1);
        for (int row = 1; row <= totalRow; row++) {
            nowRow = row;
            itemVerticalMove = (row - 1) * (dividerVerticalHeight + itemHeight);
            int nowLineColumns = lineColumns;
            if (row <= fullRow) {
                nowLineColumns = lineColumns;
            } else {
                if (modNum != 0) {
                    nowLineColumns = modNum;
                }
            }
            if (row % 2 != 0) {
                for (int line = 1; line <= nowLineColumns; line++) {
                    linePosition = line;
                    itemCountRecord = (row - 1) * lineColumns + line;
                    itemHorizontalMove = (line - 1) * (itemWidth + dividerHorizontalWidth);
                    String text = itemCountRecord <= nowPosition ? "第" + itemCountRecord + "关" : "未解锁";
                    drawItems(text);
                }
            } else {
                nowLineColumns = nowLineColumns == lineColumns ? 1 : nowLineColumns;
                for (int line = lineColumns; line >= nowLineColumns; line--) {
                    linePosition = line;
                    itemCountRecord = (nowRow * lineColumns - line + 1);
                    itemHorizontalMove = (lineColumns - (lineColumns - line) - 1) * (itemWidth + dividerHorizontalWidth);
                    String text = itemCountRecord <= nowPosition ? "第" + (nowRow * lineColumns - line + 1) + "关" : "未解锁";
                    drawItems(text);
                }
            }
            Log.e(this.getClass().getSimpleName(), "itemVerticalMove:" + itemVerticalMove);
        }
    }

    //圆角矩形的绘制
    private void drawRoundRect(Canvas canvas) {
        paint = new Paint();
        paint.setColor(itemCountRecord <= nowPosition ? itemNormalBackgroundColor : itemLockBackgroundColor);
        RectF rectF = new RectF();
        rectF.left = paddingLeft + itemHorizontalMove;
        rectF.right = rectF.left + itemWidth;
        rectF.top = paddingTop + itemVerticalMove;
        rectF.bottom = rectF.top + itemHeight;
        canvas.drawRoundRect(rectF, 16, 16, paint);
        //item坐标记录
        itemCoordinates.put(itemCountRecord + "", rectF.left + "," + rectF.top + "&" + rectF.right + "," + rectF.bottom);
    }

    /**
     * Draw the round corner font item.
     *
     * @param canvas
     * @param text
     */
    //字体的绘制
    private void drawRoundRectText(Canvas canvas, String text) {
        paint = new Paint();
        paint.setColor(itemCountRecord <= nowPosition ? textNormalColor : textLockColor);
        paint.setTextSize(textSize);

        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        canvas.drawText(text, paddingLeft + itemHorizontalMove + itemWidth / 2 - textWidth / 2, paddingTop + itemVerticalMove + (itemHeight - textHeight / 2), paint);
    }


    //分割线的绘制
    private void drawHorizontalDivider(Canvas canvas) {
        //每行最后一个不再需要在右边的分割线
        if (linePosition % lineColumns == 0 || (nowRow == totalRow && linePosition == modNum)) {
            return;
        }
        paint = new Paint();
        paint.setColor(itemCountRecord <= nowPosition ? itemNormalBackgroundColor : itemLockBackgroundColor);
        Rect rect = new Rect();
        rect.left = paddingLeft + itemWidth + itemHorizontalMove;
        rect.right = rect.left + dividerHorizontalWidth;
        rect.top = paddingTop + itemHeight / 2 - dividerHorizontalHeight / 2 + itemVerticalMove;
        rect.bottom = rect.top + dividerHorizontalHeight;
        canvas.drawRect(rect, paint);
    }

    /**
     * Draw the vertical divider
     *
     * @param canvas
     */
    private void drawVerticalDivider(Canvas canvas) {
        //基数行最后一个Item显示,偶数行第一个Item显示,数据的最后一个不显示
        if ((nowRow % 2 == 0 && linePosition == 1) || (nowRow % 2 != 0 && linePosition == lineColumns)) {
            if (itemCountRecord == totalColumns) {
                return;
            }
            paint = new Paint();
            paint.setColor(itemCountRecord <= nowPosition ? itemNormalBackgroundColor : itemLockBackgroundColor);
            Rect rect = new Rect();
            rect.left = paddingLeft + itemWidth / 2 - dividerHorizontalHeight / 2 + itemHorizontalMove;
            rect.right = rect.left + dividerVerticalWidth;
            rect.top = paddingTop + itemHeight + itemVerticalMove;
            rect.bottom = rect.top + dividerVerticalHeight;
            canvas.drawRect(rect, paint);
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * dp to px
     *
     * @param dp
     * @return
     */
    public static float dpToPx(float dp) {
        return dp * getScreenDensity();
    }

    /**
     * Get the screen of density
     *
     * @return
     */
    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * Let me draw all the items
     *
     * @param text item text
     */
    private void drawItems(String text) {
        drawRoundRect(canvas);
        drawRoundRectText(canvas, text);
        drawHorizontalDivider(canvas);
        drawVerticalDivider(canvas);
    }
    private OnGameItemClickListener onGameItemClickListener;

    /**
     * Set item to listen for events.
     *
     * @param onGameItemClickListener
     */
    public void setOnGameItemClickListener(OnGameItemClickListener onGameItemClickListener) {
        this.onGameItemClickListener = onGameItemClickListener;
    }

    /**
     * Item click on the listener event.
     */
    public interface OnGameItemClickListener {
        void onGameItemClick(int position, boolean isLock);
    }
    public void setItemNormalBackgroundColor(int itemNormalBackgroundColor) {
        this.itemNormalBackgroundColor = itemNormalBackgroundColor;
    }

    public void setItemLockBackgroundColor(int itemLockBackgroundColor) {
        this.itemLockBackgroundColor = itemLockBackgroundColor;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextNormalColor(int textNormalColor) {
        this.textNormalColor = textNormalColor;
    }

    public void setTextLockColor(int textLockColor) {
        this.textLockColor = textLockColor;
    }

    public void setDividerHorizontalWidth(int dividerHorizontalWidth) {
        this.dividerHorizontalWidth = dividerHorizontalWidth;
    }

    public void setDividerHorizontalHeight(int dividerHorizontalHeight) {
        this.dividerHorizontalHeight = dividerHorizontalHeight;
    }

    public void setDividerVerticalWidth(int dividerVerticalWidth) {
        this.dividerVerticalWidth = dividerVerticalWidth;
    }

    public void setDividerVerticalHeight(int dividerVerticalHeight) {
        this.dividerVerticalHeight = dividerVerticalHeight;
    }

    public void setLineColumns(int lineColumns) {
        this.lineColumns = lineColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }


    public void setNowPosition(int nowPosition) {
        this.nowPosition = nowPosition;
    }

}
