package nzc.camp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import nzc.camp.English.MainActivity;
import nzc.camp.Shona.Main2Activity;

public class LanguageSelectActivity extends AppCompatActivity {
    Button btn_eng, btn_sn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);


        btn_eng = findViewById(R.id.btn_eng);
        btn_sn = findViewById(R.id.btn_shona);

        btn_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn_sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);

            }
        });
    }
}
