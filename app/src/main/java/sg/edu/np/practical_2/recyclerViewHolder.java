package sg.edu.np.practical_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class recyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView profile;
    TextView name;
    TextView desc;
    ImageView bigProfile;
    OnNoteListener onNoteListener;

    public recyclerViewHolder(final View view, OnNoteListener onNoteListener){
        super(view);

        profile = view.findViewById(R.id.profile1);
        name = view.findViewById(R.id.name1);
        desc = view.findViewById(R.id.description1);
        bigProfile = view.findViewById(R.id.bigProfile);
        this.onNoteListener = onNoteListener;

        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onNoteListener.onNoteClick(getAdapterPosition());
    }
}
