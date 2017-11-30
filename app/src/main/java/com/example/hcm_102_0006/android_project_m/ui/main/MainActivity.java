package com.example.hcm_102_0006.android_project_m.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.hcm_102_0006.android_project_m.R;
import com.example.hcm_102_0006.android_project_m.data.source.MovieRepository;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieRemoteDataSource;
import com.example.hcm_102_0006.android_project_m.data.source.remote.MovieServiceClient;
import com.example.hcm_102_0006.android_project_m.databinding.ActivityHomeBinding;


public class MainActivity extends AppCompatActivity {

    public static final int RESPONSE = 810;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding mBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);

        MovieRepository mMovieRepository = new MovieRepository(new MovieRemoteDataSource(MovieServiceClient.getInstance()));
        mMainViewModel = new MainViewModel(this,mMovieRepository);
        mBinding.setViewModel(mMainViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMainViewModel.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mMainViewModel.handleActivityResult(requestCode,resultCode,data);
    }
}
