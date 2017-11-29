package com.example.hcm_102_0006.android_project_m.view.ui.genre;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.service.model.Genres;
import com.example.hcm_102_0006.android_project_m.databinding.ItemGenres2Binding;

import java.util.List;

/**
 * Created by hcm-102-0006 on 22/11/2017.
 */

public class AdapterGenres extends RecyclerView.Adapter<AdapterGenres.MyViewHolder> {

    private Activity mContext;
    public static final String KEY_RESULT = "GENRES";
    private List<Genres> mGenres;
    public AdapterGenres(List<Genres> mGenres,Activity context) {
        this.mContext = context;
        this.mGenres = mGenres;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenres2Binding itemGenres2Binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_genres_2, parent, false);
        return new MyViewHolder(itemGenres2Binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setBinding(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ObservableField<String> title = new ObservableField<>();

        public ItemGenres2Binding itemGenres2Binding;

        public MyViewHolder(ItemGenres2Binding itemGenres2Binding) {
            super(itemGenres2Binding.getRoot());
            this.itemGenres2Binding = itemGenres2Binding;
        }

        public void setBinding(Genres genres) {
            if (itemGenres2Binding.getItemGenres() == null) itemGenres2Binding.setItemGenres(this);
            title.set(genres.getName());
        }

        public void onResultGenre(View view){
            Intent intent = new Intent();
            intent.putExtra(KEY_RESULT,mGenres.get(getAdapterPosition()));
            mContext.setResult(Activity.RESULT_OK,intent);
            mContext.finish();
        }

    }
}
