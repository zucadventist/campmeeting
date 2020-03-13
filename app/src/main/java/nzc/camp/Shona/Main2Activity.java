package nzc.camp.Shona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import nzc.camp.English.AboutActivity;
import nzc.camp.English.ContactActivity;
import nzc.camp.English.IntroActivity;
import nzc.camp.English.LessonsListActivity;
import nzc.camp.English.ThemeSongActivity;
import nzc.camp.LanguageSelectActivity;
import nzc.camp.R;

public class Main2Activity extends AppCompatActivity {

    Button intro_btn, theme_song_btn, lessons_btn, feedback_btn, about_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Musangano weMatumba 2020");

        intro_btn = findViewById(R.id.intro_btn);
        theme_song_btn = findViewById(R.id.theme_song_btn);
        lessons_btn = findViewById(R.id.lessons_btn);
        feedback_btn = findViewById(R.id.feedback_btn);
        about_btn = findViewById(R.id.about_btn);

        intro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Intro2Activity.class);
                startActivity(intent);
            }
        });

        theme_song_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ThemeSong2Activity.class);
                startActivity(intent);
            }
        });

        lessons_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LessonList2Activity.class);
                startActivity(intent);
            }
        });
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Contact2Activity.class);
                startActivity(intent);
            }
        });
        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About2Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu_sn, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.intro_menu_sn:
                Intent intenti = new Intent(getApplicationContext(), Intro2Activity.class);
                startActivity(intenti);
                break;
            case R.id.feedback_menu_sn:
                Intent intentf = new Intent(getApplicationContext(), Contact2Activity.class);
                startActivity(intentf);
                break;
            case R.id.lessons_menu_sn:
                Intent intentl = new Intent(getApplicationContext(), LessonList2Activity.class);
                startActivity(intentl);
                break;
            case R.id.language_menu_sn:
                Intent intentl2 = new Intent(getApplicationContext(), LanguageSelectActivity.class);
                startActivity(intentl2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}