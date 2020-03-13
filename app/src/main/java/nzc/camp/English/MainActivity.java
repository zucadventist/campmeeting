package nzc.camp.English;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import nzc.camp.LanguageSelectActivity;
import nzc.camp.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    Button intro_btn, theme_song_btn, lessons_btn, feedback_btn, about_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("NZC Camp Meeting 2020");
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        getSupportActionBar().setTitle("Adventists Believe");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        intro_btn = findViewById(R.id.intro_btn);
        theme_song_btn = findViewById(R.id.theme_song_btn);
        lessons_btn = findViewById(R.id.lessons_btn);
        feedback_btn = findViewById(R.id.feedback_btn);
        about_btn = findViewById(R.id.about_btn);

        intro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                startActivity(intent);
            }
        });

        theme_song_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ThemeSongActivity.class);
                startActivity(intent);
            }
        });

        lessons_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LessonsListActivity.class);
                startActivity(intent);
            }
        });
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent);
            }
        });
        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_intro:
                Intent intenti = new Intent(getApplicationContext(), IntroActivity.class);
                startActivity(intenti);
                break;
            case R.id.nav_feedback:
                Intent intentf = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intentf);
                break;
            case R.id.nav_lessons:
                Intent intentl = new Intent(getApplicationContext(), LessonsListActivity.class);
                startActivity(intentl);
                break;
//                open main activity for sending email without intent
            case R.id.nav_send:
                Toast.makeText(this, "Send to a friend", Toast.LENGTH_SHORT).show();
                Intent intentsend = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intentsend);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share your Faith", Toast.LENGTH_SHORT).show();
                break;
//
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.intro_menu:
                Intent intenti = new Intent(getApplicationContext(), IntroActivity.class);
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
//
        }

        return super.onOptionsItemSelected(item);
    }
}