package com.PosterMaster.Posters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.PosterMaster.Posters.adapter.InvitationAdapter;
import com.PosterMaster.Posters.classes.Functions;
import com.PosterMaster.Posters.classes.PermissionUtils;
import com.PosterMaster.Posters.databinding.ActivityCustomTamplateBinding;
import com.PosterMaster.Posters.editor.EditorActivity;
import com.PosterMaster.Posters.editor.utils.TamplateUtils;
import com.PosterMaster.Posters.model.InvitationModel;
import com.PosterMaster.Posters.model.PostsModel;
import com.PosterMaster.Posters.responses.HomeResponse;
import com.PosterMaster.Posters.viewmodel.HomeViewModel;

public class CustomTamplateActivity extends AppCompatActivity {

    ActivityCustomTamplateBinding binding;
    HomeViewModel homeViewModel;
    PermissionUtils takePermissionUtils;
    String ratio = "";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomTamplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        callgetCards(getIntent().getStringExtra("category_id"));
        binding.titleTv.setText(getIntent().getStringExtra("name"));
    }

    private void callgetCards(String toString) {
        binding.shimmerLay.startShimmer();
        binding.shimmerLay.setVisibility(View.VISIBLE);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getInvitationCardsByCatId(toString).observe(this, new Observer<HomeResponse>() {
            @Override
            public void onChanged(HomeResponse homeResponse) {
                Functions.cancelLoader();
                if (homeResponse.getCustomTamplates() != null) {
                    binding.shimmerLay.stopShimmer();
                    binding.shimmerLay.setVisibility(View.GONE);
                    binding.recycler.setAdapter(new InvitationAdapter(context, homeResponse.getCustomTamplates(), new InvitationAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, InvitationModel postsModels, int pos) {
                            if (postsModels.getPremium().equals("1")){
                                PostsModel postsModel = new PostsModel();
                                postsModel.setPremium("1");
                                EditorActivity.postsModel = postsModel;
                            }
                            selectedJson = postsModels.getJson();

                            Functions.showLoader(context);
                            StartTamplateProcess process = new StartTamplateProcess();
                            process.doInBackground();
                        }
                    },2,getResources().getDimension(R.dimen._2ssp)));

                    if (!(homeResponse.getCustomTamplates().size() > 0)){
                        binding.noDataLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    String selectedJson = "";
    private final class StartTamplateProcess extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Functions.showLoader(context);
        }

        @Override
        protected String doInBackground(Void... params) {
            TamplateUtils tamplateUtils = new TamplateUtils(CustomTamplateActivity.this);
            tamplateUtils.openEditorActivity(selectedJson);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Functions.cancelLoader();
        }
    }

    public void finish(View view) {
        finish();
    }
}