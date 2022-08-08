package sg.edu.np.practical_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String[] condition = {"UNFOLLOW", "FOLLOW"};
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent recieve = getIntent();
        int num = recieve.getIntExtra("random", 0);
        String name = "MAD " + num;

        TextView pName = findViewById(R.id.textView);

        pName.setText(name);

        Button myButton = findViewById(R.id.button);
        Button myButton2 = findViewById(R.id.button2);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status) {
                    myButton.setText(condition[1]);
                    status = false;

                    Toast.makeText(MainActivity.this, "UNFOLLOWED", Toast.LENGTH_SHORT).show();
                }
                else {
                    myButton.setText(condition[0]);
                    status = true;
                    Toast.makeText(MainActivity.this, "FOLLOWED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent message = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(message);
            }
        });
    }
}