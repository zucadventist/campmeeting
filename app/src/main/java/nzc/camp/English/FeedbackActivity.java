package nzc.camp.English;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import nzc.camp.LanguageSelectActivity;
import nzc.camp.R;

public class FeedbackActivity extends AppCompatActivity {

    Button send_feedback_btn, cancel_feedback_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Feedback");
        setContentView(R.layout.activity_feedback);
        send_feedback_btn = findViewById(R.id.btn_send_feedback);
        cancel_feedback_btn = findViewById(R.id.btn_cancel_feedback);

        send_feedback_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

              sendEmail();

            }

        });
        cancel_feedback_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    protected void sendEmail()
    {
        EditText edt_comment_feedback, edt_name_feedback;
        edt_comment_feedback = findViewById(R.id.edt_comment_feedback);
        edt_name_feedback = findViewById(R.id.edt_name_feedback);
        edt_comment_feedback.setScroller(new Scroller(getBaseContext()));
        edt_comment_feedback.setVerticalScrollBarEnabled(true);
        edt_comment_feedback.setMovementMethod(new ScrollingMovementMethod());
        String content = edt_comment_feedback.getText().toString();
        String feedbackname = edt_name_feedback.getText().toString();
        String[] to = new String[]{"media@nzc.adventist.org"};
        String[] cc = new String[]{"mauriceg@nzc.adventist.org"};
        String message = content;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Email from "+ feedbackname);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        try
        {
            startActivity(Intent.createChooser(intent, "Pick Email Client.."));
        }
        catch(android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(getApplicationContext(), "No email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu_feedback, menu);
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
//            
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

