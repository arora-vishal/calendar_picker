package com.squareup.timessquare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vishalarora on 16/04/17.
 */

public class CalendarView extends LinearLayout {

    private DateFormat weekdayNameFormat;
    private Locale locale;

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);

        // add the header day row as first element in the layout

        ViewGroup headerDayRowView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.days_header, null);

        Calendar today = Calendar.getInstance();

        final int originalDayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        Logr.d(" originalDayOfWeek " + originalDayOfWeek);

        int firstDayOfWeek = today.getFirstDayOfWeek();

        Logr.d("firstDayOfWeek" + firstDayOfWeek);

        weekdayNameFormat = new SimpleDateFormat(context.getString(R.string.day_name_format), locale);
        locale = Locale.getDefault();

        final CalendarRowView headerRow = (CalendarRowView) headerDayRowView.getChildAt(0);
        for (int offset = 0; offset < 7; offset++) {
            today.set(Calendar.DAY_OF_WEEK, getDayOfWeek(firstDayOfWeek, offset, isRtl(locale)));
            final TextView textView = (TextView) headerRow.getChildAt(offset);
            textView.setText(weekdayNameFormat.format(today.getTime()));
        }

        addView(headerDayRowView);

        // add calendar picker view

    }

    private static int getDayOfWeek(int firstDayOfWeek, int offset, boolean isRtl) {
        int dayOfWeek = firstDayOfWeek + offset;
        if (isRtl) {
            return 8 - dayOfWeek;
        }
        return dayOfWeek;
    }


    private static boolean isRtl(Locale locale) {
        // TODO convert the build to gradle and use getLayoutDirection instead of this (on 17+)?
        final int directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT
                || directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
}
