package sg.edu.np.practical_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ImageView profile = findViewById(R.id.imageView3);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popout = new AlertDialog.Builder(ListActivity.this);

                popout.setTitle("Profile");
                popout.setMessage("MADness");
                popout.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        closeContextMenu();
                    }
                });
                popout.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Random random = new Random();
                        int rand = random.nextInt();
                        Intent main = new Intent(ListActivity.this, MainActivity.class);
                        main.putExtra("random", rand);
                        startActivity(main);
                    }
                });
                popout.create().show();
            }
        });
    }
}