package sg.edu.np.practical_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String[] condition = {"UNFOLLOW", "FOLLOW"};
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent receiving = getIntent();
        String name = receiving.getStringExtra("name");
        String desc = receiving.getStringExtra("desc");
        int num = receiving.getIntExtra("cond", 0);
        int id = receiving.getIntExtra("id", 0);
        boolean status = receiving.getBooleanExtra("status", false);
        u = new User(name, desc, id, status);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = findViewById(R.id.button);
        Button myButton2 = findViewById(R.id.button2);
        TextView text = (TextView) findViewById(R.id.textView);
        TextView text1 = (TextView) findViewById(R.id.textView2);

        text.setText(u.Name);
        text1.setText(u.Description);

        if (!u.Followed) {
            myButton.setText(condition[1]);
        }
        else {
            myButton.setText(condition[0]);
        }

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!u.Followed) {
                    myButton.setText(condition[0]);
                    u.Followed = true;

                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
                else {
                    myButton.setText(condition[1]);
                    u.Followed = false;

                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent messageActivity = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(messageActivity);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("follow", u.Followed);
        Intent test = new Intent(MainActivity.this, ListActivity.class);
        test.putExtras(bundle);
        setResult(RESULT_OK, test);
        finish();
    }
}