package nzc.camp.Shona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import nzc.camp.English.ContactActivity;
import nzc.camp.English.LessonsListActivity;
import nzc.camp.English.MainActivity;
import nzc.camp.LanguageSelectActivity;
import nzc.camp.R;

public class Lessons2Activity extends AppCompatActivity {

    TextView id_tv;
    TextView title_tv;
    TextView body_tv;
    ScrollView scroll_sv;
    TextView title_img;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String MY_PREFS_NAME = "campprefs2";
    String id, title2, image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons2);

        title_tv = findViewById(R.id.title_tv);
        body_tv = findViewById(R.id.body_tv);
//        title_img = findViewById(R.id.title_img);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title2 = intent.getStringExtra("title2");
//        image2 = intent.getStringExtra("image_url");
//        Bundle extras = this.getIntent().getExtras();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(id + ". " + title2);


        title_tv.setText(Html.fromHtml(intent.getStringExtra("title")));
        body_tv.setText(Html.fromHtml(intent.getStringExtra("body")));



        sp = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final int y = sp.getInt(getPackageName() + id, 0);

        scroll_sv = findViewById(R.id.scroll_sv);

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                scroll_sv.scrollTo(0, y);
            }
        }, 250); // 250 ms delay

    }


    @Override
    protected void onPause() {
        super.onPause();
        editor = sp.edit();
        editor.putInt(getPackageName() + id, scroll_sv.getScrollY());
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
                Intent intenti = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intenti);
                break;
            case R.id.feedback_menu:
                Intent intentf = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intentf);
                break;
            case R.id.lessons_menu:
                Intent intentl = new Intent(getApplicationContext(), LessonList2Activity.class);
                startActivity(intentl);
                break;

            case R.id.language_menu:
                Intent intentl2 = new Intent(getApplicationContext(), LanguageSelectActivity.class);
                startActivity(intentl2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}


