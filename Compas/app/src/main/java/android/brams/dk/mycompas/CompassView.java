package android.brams.dk.mycompas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class CompassView extends View {
    private Paint paint;
    private float position = 0;

    public CompassView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(4);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x0 = getMeasuredWidth() / 2;
        int y0 = getMeasuredHeight() / 2;

        float radius = (float) (Math.max(x0, y0) * 0.6);
        canvas.drawCircle(x0, y0, radius, paint);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        float x1=(float) (x0 + radius*Math.sin((double)(-position) / 180 * 3.143));
        float y1=(float) (y0 - radius*Math.cos((double)(-position) / 180 * 3.143));
        canvas.drawLine(x0, y0, x1, y1, paint);

        canvas.drawText(String.valueOf(position), x0, y0, paint);
        canvas.drawText("N", x1, y1, paint);
    }

    public void updateData(float position) {
        this.position = position;
        invalidate();
    }

}