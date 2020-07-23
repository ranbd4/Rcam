package com.ranapplications.rcam.cameraFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ranapplications.rcam.R;
import com.ranapplications.rcam.global.MakeVisible;
import com.ranapplications.rcam.mainActivity.mainFragment.Cameras;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * A fragment that present all the alerts list for a specific camera
 *
 */
public class CameraFragment extends Fragment {

    private View view;
    private ArrayList<CameraAlertsClass> cameraAlertsClasses;
    private Cameras camera;
    private CameraFragmentAdapter mAdapter;

    public CameraFragment(Cameras camera) {
        this.camera = camera;
    }

    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_camera, container, false);

        init();

        return view;
    }

    private void init() {
        setTitle();
        /**
         * A callback class
         * @GetCameraAlertsCallback = an interface that will return an arrayList with
         *                            all the alerts from the Firebase
         */
        new GetCameraAlerts(new GetCameraAlerts.GetCameraAlertsCallback() {
            @Override
            public void onDone(ArrayList<CameraAlertsClass> cameraAlertsClasses){
                progressBarDisable();
                if (cameraAlertsClasses.isEmpty()){
                    Toast.makeText(view.getContext(), Objects.requireNonNull(getActivity()).getString(R.string.empty), Toast.LENGTH_SHORT).show();
                    return;
                }
                //Save all the alerts to our ArrayList
                CameraFragment.this.cameraAlertsClasses = cameraAlertsClasses;
                setUpRecyclerView();

            }
        }, camera.getCameraID());
    }

    /**
     * Set The text title based on the camera name
     */
    private void setTitle() {
        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText(camera.getName());
    }

    private void setUpRecyclerView() {
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerViewCamerasAlerts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
        mAdapter = new CameraFragmentAdapter(view.getContext(),cameraAlertsClasses);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Disable the progressBar to after the loading done
     */
    private void progressBarDisable() {
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        MakeVisible makeVisible = (MakeVisible) getActivity();
        Objects.requireNonNull(makeVisible).makeVisible();
        super.onDetach();
    }

}
