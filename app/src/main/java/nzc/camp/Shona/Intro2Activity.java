package nzc.camp.Shona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import nzc.camp.English.ContactActivity;
import nzc.camp.English.LessonsListActivity;
import nzc.camp.English.MainActivity;
import nzc.camp.LanguageSelectActivity;
import nzc.camp.R;

public class Intro2Activity extends AppCompatActivity {
    TextView title_tv, body_tv;
    JSONObject obj, intro_obj;
    JSONArray arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sumo");

        title_tv = findViewById(R.id.title_tv);
        body_tv = findViewById(R.id.body_tv);

        String jsonString = loadJSONFromAsset("musangano.json");

        try
        {
            obj = new JSONObject(jsonString);
            arr = obj.getJSONArray("lessons");
            intro_obj = arr.getJSONObject(0);
            title_tv.setText(Html.fromHtml(intro_obj.getString("title")));
            body_tv.setText(Html.fromHtml(intro_obj.getString("text")));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


    }

    public String loadJSONFromAsset(String filename)
    {
        String json = null;

        try
        {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu_intro, menu);
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
//
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