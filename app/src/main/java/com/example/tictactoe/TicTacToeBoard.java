package com.example.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {

    private final int boardColor;
    private final int xColor;
    private final int oColor;
    private final int winningLineColor;
    private final Paint paint = new Paint();

    private int cellSize = getWidth()/3;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTacToeBoard, 0, 0);
        try{
            boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardColor, 0 );
            xColor = a.getInteger(R.styleable.TicTacToeBoard_XColor, 0);
            oColor = a.getInteger(R.styleable.TicTacToeBoard_OColor, 0);
            winningLineColor = a.getInteger(R.styleable.TicTacToeBoard_winningLineColor, 0);
        } finally{
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension /3;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);

        drawX(canvas, 2, 2);
        drawO(canvas, 1, 1);
    }

    private void drawGameBoard(Canvas canvas)
    {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int c = 1; c <3; c++)
        {
            canvas.drawLine(cellSize * c, 0, cellSize * c, canvas.getWidth(), paint);
        }
        for(int r = 1; r <3; r++)
        {
            canvas.drawLine( 0,cellSize * r, canvas.getWidth(), cellSize * r, paint);
        }
    }

    private void drawX(Canvas canvas, int row, int col)
    {
        paint.setColor(xColor);
        canvas.drawLine((float)((col + 1) * cellSize - cellSize *0.2),
                        (float)(row * cellSize + cellSize *0.2),
                        (float)(col * cellSize + cellSize *0.2),
                        (float)((row + 1) * cellSize - cellSize *0.2),
                        paint);
        canvas.drawLine((float)(col * cellSize+ cellSize *0.2),
                        (float)(row * cellSize+ cellSize *0.2),
                        (float)((col + 1) * cellSize- cellSize *0.2),
                        (float)((row + 1) * cellSize- cellSize *0.2),
                        paint);
    }
    private void drawO(Canvas canvas, int row, int col)
    {
        paint.setColor(oColor);
        canvas.drawOval((float)(col * cellSize+ cellSize *0.2),
                (float)(row * cellSize+ cellSize *0.2),
                (float)((col * cellSize + cellSize) - cellSize *0.2),
                (float)((row * cellSize + cellSize)- cellSize *0.2),
                paint);
    }

}
