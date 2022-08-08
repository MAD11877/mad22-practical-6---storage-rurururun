package sg.edu.np.practical_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerViewHolder> {
    ArrayList<User> userList;
    OnNoteListener monNoteListener;

    public recyclerAdapter(ArrayList<User> userList, OnNoteListener onNoteListener){
        this.userList = userList;
        this.monNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_holder, parent, false);
        return new recyclerViewHolder(itemView, monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewHolder holder, int position) {
        String userName = userList.get(position).getName();
        String description = userList.get(position).getDescription();

        if (userName.endsWith("7")){
            holder.bigProfile.setVisibility(View.VISIBLE);
        }
        else{
            holder.bigProfile.setVisibility(View.GONE);
        }

        holder.name.setText(userName);
        holder.desc.setText(description);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
