package com.squareup.timessquare;

import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DefaultDayViewAdapter implements DayViewAdapter {

    @Override
    public void makeCellView(CalendarCellView parent) {

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, parent.getContext().getResources().getDisplayMetrics());

        LinearLayout linearLayout = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        /* day text view */
        TextView textView = new TextView(
                new ContextThemeWrapper(parent.getContext(), R.style.CalendarCell_CalendarDate));
        textView.setGravity(Gravity.CENTER);
        textView.setDuplicateParentStateEnabled(true);
        textView.setPadding(padding, padding, padding, padding);

        LinearLayout.LayoutParams dateLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(textView, dateLayoutParams);

        parent.addView(linearLayout, layoutParams);
        parent.setDayOfMonthTextView(textView);

        parent.setDayLayout(linearLayout);

        /* footer text view to hold prices or other relevant info to the day */
        TextView footerTextView = new TextView(
                new ContextThemeWrapper(parent.getContext(), R.style.CalendarCell_Footertext));
        footerTextView.setDuplicateParentStateEnabled(true);
        String rupee = parent.getContext().getResources().getString(R.string.rupee);
        footerTextView.setText(rupee + "2243");

        parent.addView(footerTextView);
        parent.setDayFooterTextView(footerTextView);

        /* footer image view */
    }
}
