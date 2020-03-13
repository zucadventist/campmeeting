package nzc.camp.English;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import nzc.camp.R;

import static nzc.camp.English.LessonActivity.body_tv;

public class SettingsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String MY_PREFS_NAME = "campprefs";

    RadioGroup radioGroup;
    RadioButton rb_small, rb_medium, rb_large;
    Button btn_save_settings;
    SeekBar bar;
    TextView textView;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

        radioGroup=(RadioGroup) findViewById(R.id.radiogroup_fontsize);
        rb_small=(RadioButton) findViewById(R.id.rb_small);
        rb_medium=(RadioButton) findViewById(R.id.rb_medium);
        rb_large=(RadioButton) findViewById(R.id.rb_large );
        btn_save_settings=(Button) findViewById(R.id.save_settings);
        textView = (TextView)findViewById(R.id.changeFont);
        bar = (SeekBar)findViewById(R.id.seekBar);


        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int e, boolean b) {
                textView.setTextSize(Float.valueOf(e));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
//


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (rb_small.isChecked()) {
            body_tv.setTextSize(20);
        }
        if (rb_medium.isChecked()) {
            body_tv.setTextSize(40);
        }
        if (rb_large.isChecked()) {
            body_tv.setTextSize(60);
        }



    sp = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
   final int e = sp.getInt(getPackageName(), 0);
//
    body_tv = findViewById(R.id.body_tv);

    Handler h = new Handler();

                h.postDelayed(new Runnable() {
        @Override
        public void run() {
            body_tv.setTextSize(0, e);
        }
    }, 250); // 250 ms delay

}


    @Override
    protected void onPause() {
        super.onPause();
        editor = sp.edit();
        editor.putInt(getPackageName(), (int) body_tv.getTextSize());
        editor.apply();
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
