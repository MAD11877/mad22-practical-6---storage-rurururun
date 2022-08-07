package sg.edu.np.practical_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final String[] condition = {"UNFOLLOW", "FOLLOW"};
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.button);
        Button myButton2 = findViewById(R.id.button2);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status) {
                    myButton.setText(condition[1]);
                    status = false;
                }
                else {
                    myButton.setText(condition[0]);
                    status = true;
                }
            }
        });
    }
}