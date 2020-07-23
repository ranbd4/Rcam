package com.ranapplications.rcam.mainActivity.mainFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.ranapplications.rcam.R;
import com.ranapplications.rcam.cameraFragment.CameraFragment;
import com.ranapplications.rcam.global.DataManager;
import com.ranapplications.rcam.mainActivity.mainFragment.sceneFragment.SceneFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CamerasListAdapter.CamerasListAdapterCallback {

    private View view;
    private CamerasListAdapter mAdapter;
    private ArrayList<Cameras> cameras;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FrameLayout frameLayoutScreen;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        setUpFrameLayoutScene();

        setUpRecyclerView();

        setUpTopBar();

        setUpSwipeRefreshLayout();

        frameLayoutScreen = getActivity().findViewById(R.id.frameLayoutScreen);


        return view;
    }

    private void setUpSwipeRefreshLayout() {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }

    private void setUpTopBar() {
        // TODO: 2020-05-04 Add if image is null
        Glide.with(view).load(DataManager.getInstance().user.getImageUrl()).into((ImageView) view.findViewById(R.id.imageViewProfileImage));

        String titleText = getString(R.string.hi) + " " + DataManager.getInstance().user.getUserFirstName() + "!";
        TextView textViewUserName = view.findViewById(R.id.textViewUserName);
        textViewUserName.setText(titleText);
    }

    private void setUpRecyclerView(){
        cameras = DataManager.getInstance().cameras;

        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerViewCamerasList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
        mAdapter = new CamerasListAdapter(getContext(),cameras, this);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);


        new UpdateCameraStatus(new UpdateCameraStatus.UpdateCameraStatusCallback() {
            @Override
            public void onUpdateCameraStatusDone() {
                cameras = DataManager.getInstance().cameras;
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpFrameLayoutScene() {
        Fragment sceneFragment = new SceneFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutScene, sceneFragment).commit();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new UpdateCameraStatus(new UpdateCameraStatus.UpdateCameraStatusCallback() {
            @Override
            public void onUpdateCameraStatusDone() {
                cameras = DataManager.getInstance().cameras;
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClickRow(Cameras camera, int position) {
        cameras.get(position).setNewAlerts(0);
        mAdapter.notifyDataSetChanged();

        Fragment sceneFragment = new CameraFragment(camera);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.anim_in_test,0,0, R.anim.anim_out_test)
                .replace(R.id.frameLayoutScreen, sceneFragment, "fragment")
                .addToBackStack(null)
                .commit();

        frameLayoutScreen.setVisibility(View.VISIBLE);
        UpdateCameraStatus.resetNewAlertsCount(camera.getCameraID());
    }
}
