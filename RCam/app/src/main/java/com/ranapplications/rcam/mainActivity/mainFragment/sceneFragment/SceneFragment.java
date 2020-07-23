package com.ranapplications.rcam.mainActivity.mainFragment.sceneFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ranapplications.rcam.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends Fragment implements SceneAdapter.SceneAdapterCallBack {

    private View view;
    private SceneAdapter mAdapter;
    private ArrayList<SceneClass> sceneClasses;

    public SceneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scene, container, false);

        setUpRecyclerViewScene();
        return view;
    }

    private void setUpRecyclerViewScene() {
        sceneClasses = SceneFragmentHelper.getInstance().getSceneClasses();
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerViewScene);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
        mAdapter = new SceneAdapter(getContext(),sceneClasses, this);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onUserClick() {
        sceneClasses = SceneFragmentHelper.getInstance().getSceneClasses();
        mAdapter.notifyDataSetChanged();
    }
}
