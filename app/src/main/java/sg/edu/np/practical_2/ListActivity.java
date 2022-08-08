package sg.edu.np.practical_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements OnNoteListener {
    ArrayList<User> userList;
    RecyclerView recyclerView;
    User select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        userList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        for (int i = 1; i <= 20; i++) {
            String name;
            String desc;
            boolean follow;
            Random rand = new Random();

            name = "Name" + rand.nextInt();
            desc = "Description " + rand.nextInt();
            int cond = rand.nextInt();
            if (cond > 0) {
                follow = true;
            }
            else {
                follow = false;
            }

            User u = new User(name, desc, i, follow);
            userList.add(u);
        }

        recyclerAdapter adapter = new recyclerAdapter(userList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        select = userList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage(userList.get(position).Name);
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                closeContextMenu();
            }
        });
        builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bundle bundle = new Bundle();
                bundle.putString("name", select.Name);
                bundle.putString("desc", select.Description);
                bundle.putInt("cond", 1);
                bundle.putInt("id", select.Id);
                bundle.putBoolean("status", select.Followed);

                Intent profile = new Intent(ListActivity.this, MainActivity.class);
                profile.putExtras(bundle);
                startActivityForResult(profile, 123);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == RESULT_OK) {
            select.Followed = data.getBooleanExtra("follow", select.Followed);
        }
    }
}