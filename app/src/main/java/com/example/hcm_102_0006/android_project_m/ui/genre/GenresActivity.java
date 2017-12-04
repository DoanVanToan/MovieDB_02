package com.example.hcm_102_0006.android_project_m.ui.genre;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.source.GenreRepository;
import com.example.hcm_102_0006.android_project_m.data.source.remote.GenreRemoteDataSource;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieServiceClient;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityGenresBinding;

public class GenresActivity extends AppCompatActivity {

    private GenresViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GenreRepository repository = new GenreRepository(
                new GenreRemoteDataSource(MovieServiceClient.getInstance()));
        mViewModel = new GenresViewModel(this, repository);
        ActivityGenresBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_genres);
        binding.setViewModel(mViewModel);
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }
}