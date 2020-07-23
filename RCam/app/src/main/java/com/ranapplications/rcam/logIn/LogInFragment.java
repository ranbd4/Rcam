package com.ranapplications.rcam.logIn;


import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ranapplications.rcam.DetailsTransition;
import com.ranapplications.rcam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {

    private View view;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_log_in, container, false);

        init();

        return view;
    }

    private void init() {
        view.findViewById(R.id.btmLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mFragment = new LogInEmailFragment();
                mFragment.setSharedElementEnterTransition(new DetailsTransition());
                mFragment.setEnterTransition(new Fade());
                setExitTransition(new Fade());
                mFragment.setSharedElementReturnTransition(new DetailsTransition());

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addSharedElement(view, view.getTransitionName())
                        .replace(R.id.frameLayout, mFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        view.findViewById(R.id.btmSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
