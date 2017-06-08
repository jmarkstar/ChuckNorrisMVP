package com.jmarkstar.chucknorris.ui.joke;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jmarkstar.chucknorris.R;
import com.jmarkstar.core.domain.model.JokeModel;
import java.util.List;
import java.util.Random;

/**
 * Created by jmarkstar on 8/06/2017.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.VHJoke> {

    private static final int [] IMAGES = {R.drawable.chucknorris, R.drawable.chuck_norris_2, R.drawable.chuck_norris_3, R.drawable.chuck_norris_4,};

    private List<JokeModel> jokes;
    private Context context;

    public JokeAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<JokeModel> jokes){
        this.jokes = jokes;
    }

    @Override public VHJoke onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_jokes_item, parent, false);
        return new VHJoke(view);
    }

    @Override public void onBindViewHolder(VHJoke holder, int position) {
        JokeModel joke = jokes.get(position);
        holder.tvJoke.setText(joke.getJoke().replace("%quot;","\""));

        int random = new Random().nextInt(IMAGES.length);
        Drawable icon = ContextCompat.getDrawable(context, IMAGES[random]);
        holder.ivPhoto.setImageDrawable(icon);
    }

    @Override public int getItemCount() {
        if(jokes==null)return 0;
        else return jokes.size();
    }

    class VHJoke extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvJoke;

        public VHJoke(View itemView) {
            super(itemView);
            ivPhoto = (ImageView)itemView.findViewById(R.id.iv_chuck_norris);
            tvJoke = (TextView)itemView.findViewById(R.id.tv_joke);
        }
    }
}
