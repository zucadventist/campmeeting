package nzc.camp.Shona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import nzc.camp.English.ContactActivity;
import nzc.camp.English.LessonActivity;
import nzc.camp.English.LessonsListActivity;
import nzc.camp.English.MainActivity;
import nzc.camp.LanguageSelectActivity;
import nzc.camp.R;

public class LessonList2Activity extends AppCompatActivity {
    ListView lessons_lv;
    ArrayList<HashMap<String, String>> lesson_list;
    JSONObject obj, intro_obj;
    JSONArray arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list2);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Zvidzidzo");

        lesson_list = new ArrayList<>();
        String jsonString = loadJSONFromAsset("musangano.json");

        try
        {
            obj = new JSONObject(jsonString);
            arr = obj.getJSONArray("lessons");

            for(int i = 0; i < arr.length(); i++)
            {
                if(arr.getJSONObject(i).getString("category").equals("lesson"))
                {
                    HashMap<String, String> lesson = new HashMap<>();
                    lesson.put("id", arr.getJSONObject(i).getString("id"));
                    lesson.put("title", arr.getJSONObject(i).getString("title"));
                    lesson.put("title2", arr.getJSONObject(i).getString("title2"));
                    lesson.put("body", arr.getJSONObject(i).getString("text"));
//                    lesson.put("image_url", arr.getJSONObject(i).getString("image_url"));

                    lesson_list.add(lesson);
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


        lessons_lv = findViewById(R.id.lessons_lv);

        ListAdapter adapter = new SimpleAdapter(this, lesson_list,
                R.layout.list_item, new String[]{ "id","title2"},
                new int[]{R.id.id_tv, R.id.title_tv});

        lessons_lv.setAdapter(adapter);

        lessons_lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), Lessons2Activity.class);


                //Toast.makeText(getApplicationContext(), lesson_list.get(position).get("id"), Toast.LENGTH_SHORT).show();
                intent.putExtra("id", lesson_list.get(position).get("id"));
                intent.putExtra("title", lesson_list.get(position).get("title"));
                intent.putExtra("title2", lesson_list.get(position).get("title2"));
                intent.putExtra("body", lesson_list.get(position).get("body"));
//                intent.putExtra("image_url", lesson_list.get(position).get("image_url"));
//                intent2.putExtra("id", lesson_list.get(position).get("id"));
//                intent2.putExtra("image_url", lesson_list.get(position).get("image_url"));
//                setIntent(intent2);
                startActivity(intent);
            }
        });
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