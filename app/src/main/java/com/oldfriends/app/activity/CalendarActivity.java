package com.oldfriends.app.activity;

/**
 * lh on 2016/2/24.
 */
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.oldfriends.app.R;
import com.oldfriends.app.util.StringUtils;
import com.oldfriends.app.util.Utility;
import com.oldfriends.app.view.CalendarView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarActivity extends BaseActivity
{
    public static final String CALENDAR_TIME = "calendar_time";
    private String chooseTime;

    private void initContent()
    {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date localDate = new Date();
        this.chooseTime = localSimpleDateFormat.format(localDate);
        ((TextView)findViewById(R.id.consume_time_current)).setText(this.chooseTime.substring(0, 8));
        CalendarView localCalendarView = (CalendarView)findViewById(R.id.customCalendar);
        CalendarView.OnItemClickListener local3 = new CalendarView.OnItemClickListener()
        {
            public void OnItemClick(Date paramAnonymousDate)
            {
                SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                chooseTime = localSimpleDateFormat.format(paramAnonymousDate);
            }
        };
        localCalendarView.setOnItemClickListener(local3);
    }

    private void initTop()
    {
        RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.consume_time_top_layout);
        ((TextView)localRelativeLayout.findViewById(R.id.common_font_title)).setText("消费时间");
        View localView1 = localRelativeLayout.findViewById(R.id.common_font_left_layout);
        View.OnClickListener local1 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Utility.finishActivityTranslate(CalendarActivity.this);
            }
        };
        localView1.setOnClickListener(local1);
        View localView2 = localRelativeLayout.findViewById(R.id.common_font_right_layout);
        View.OnClickListener local2 = new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (StringUtils.isNotBlank(CalendarActivity.this.chooseTime))
                {
                    CalendarActivity localCalendarActivity = CalendarActivity.this;
                    Toast.makeText(localCalendarActivity, "chooseTime=" + CalendarActivity.this.chooseTime, Toast.LENGTH_LONG).show();
                    Intent localIntent = new Intent();
                    localIntent.putExtra(CALENDAR_TIME, CalendarActivity.this.chooseTime);
                    CalendarActivity.this.setResult(RESULT_OK, localIntent);
                    CalendarActivity.this.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
                    CalendarActivity.this.finish();
                    return;
                }
                Toast.makeText(CalendarActivity.this, "请选择具体的时间!", Toast.LENGTH_LONG).show();
            }
        };
        localView2.setOnClickListener(local2);
    }

    protected int getLayoutResId()
    {
        return R.layout.activity_choose_consume_time_layout;
    }

    protected void initView()
    {
        initTop();
        initContent();
    }
}
