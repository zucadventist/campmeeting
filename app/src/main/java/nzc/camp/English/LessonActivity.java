package nzc.camp.English;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import nzc.camp.LanguageSelectActivity;
import nzc.camp.R;


public class LessonActivity extends AppCompatActivity {
    TextView fontsize_tv;
    TextView title_tv;
    static TextView body_tv;
    ScrollView scroll_sv;
    TextView title_img;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String MY_PREFS_NAME = "campprefs";
    String id, title2, body;
    SeekBar bar;
    int f;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);


        bar = (SeekBar)findViewById(R.id.seekBar);

        title_tv = findViewById(R.id.title_tv);
        body_tv = findViewById(R.id.body_tv);
//        title_img = findViewById(R.id.title_img);
//        CharSequence body = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title2 = intent.getStringExtra("title2");
        body = intent.getStringExtra("body");

//        CharSequence body = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
//        Bundle extras = this.getIntent().getExtras();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(id + ". " + title2);

        sp = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        float fs = sp.getFloat("fontsize", 20);
        bar.setProgress((int) fs);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int e, boolean b) {
                body_tv.setTextSize(Float.valueOf(e));
                title_tv.setTextSize(Float.valueOf(e));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putFloat("fontsize", body_tv.getTextSize());
                editor.putFloat("fontsize", title_tv.getTextSize());
                editor.apply();
            }
        });

        title_tv.setText(Html.fromHtml(intent.getStringExtra("title")));
        body_tv.setText(Html.fromHtml(intent.getStringExtra("body")));

//        Spannable spanText = Spannable.Factory.getInstance().newSpannable(body);
//        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 14, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        body_tv.setText(spanText);



                final int y = sp.getInt(getPackageName() + id, 0);


                scroll_sv = findViewById(R.id.scroll_sv);


                Handler h = new Handler();

                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scroll_sv.scrollTo(0, y);

                    }
                }, 250); // 250 ms delay
        int start = body_tv.getSelectionStart();
        int end = body_tv.getSelectionEnd();

            }


            @Override
            protected void onPause() {
                super.onPause();
                editor = sp.edit();
                editor.putInt(getPackageName() + id, scroll_sv.getScrollY());
                editor.putFloat("fontsize", body_tv.getTextSize());
                editor.putFloat("fontsize", title_tv.getTextSize());
                editor.apply();
            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu_lessons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.home_menu:
                Intent intenti = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intenti);
                break;
            case R.id.feedback_menu:
                Intent intentf = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intentf);
                break;
            case R.id.lessons_menu:
                Intent intentl = new Intent(getApplicationContext(), LessonsListActivity.class);
                startActivity(intentl);
                break;

            case R.id.language_menu:
                Intent intentl2 = new Intent(getApplicationContext(), LanguageSelectActivity.class);
                startActivity(intentl2);
                break;

            case R.id.about_menu:
                Intent intentab = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intentab);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}


