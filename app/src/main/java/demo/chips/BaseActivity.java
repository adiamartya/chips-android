package demo.chips;

import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aditya.amartya on 5/10/2016.
 */
public class BaseActivity extends AppCompatActivity {
    protected void inflateLayout(LinearLayout layoutContainer, ArrayList<String> data) {

        /*
        Creating a horizontal linear layout container for inflating single row with textviews
         */
        LinearLayout.LayoutParams layoutParamsHorizontal = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayoutInner = new LinearLayout(getApplicationContext());
        linearLayoutInner.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutInner.setLayoutParams(layoutParamsHorizontal);
/*
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 */

        LinearLayout.LayoutParams layoutParamsContainer = (LinearLayout.LayoutParams) layoutContainer.getLayoutParams();
        int maxWidth = getMaxWidth(); //Display width
        int leftMargin = layoutParamsContainer.leftMargin; //Left margin of container
        int rightMargin = layoutParamsContainer.rightMargin; //Right margin of container

        int widthSoFar = leftMargin + rightMargin;

        int textMarginRight = dpToPixelValue(8);//8dp margin for each textview
        int textMarginTopBottom = dpToPixelValue(3);//8dp margin for each textview

        for (int i = 0; i < data.size(); i++) {

            /*
            Creating and initializing textview for displaying movie name
             */
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, textMarginTopBottom, textMarginRight, textMarginTopBottom);
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(layoutParams);
            textView.setBackgroundResource(R.drawable.rectangular_box);

            textView.setText(data.get(i));
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_color));

            textView.measure(0, 0);

            widthSoFar += textView.getMeasuredWidth() + layoutParams.rightMargin;

            if (widthSoFar >= maxWidth) {
                layoutContainer.addView(linearLayoutInner);
                linearLayoutInner = new LinearLayout(getApplicationContext());
                linearLayoutInner.setLayoutParams(layoutParamsHorizontal);
                linearLayoutInner.setOrientation(LinearLayout.HORIZONTAL);
                linearLayoutInner.addView(textView);
                widthSoFar = textView.getMeasuredWidth() + leftMargin + rightMargin + layoutParams.rightMargin;
            } else {
                linearLayoutInner.addView(textView);
            }
        }
        layoutContainer.addView(linearLayoutInner);
    }

    /*
    Get width of the screen
     */
    private int getMaxWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }


    /*
    Get values in pixel
    input in dp
    output in px
     */
    private int dpToPixelValue(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }
}
