package com.ranapplications.rcam.logIn;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ranapplications.rcam.mainActivity.MainActivity;
import com.ranapplications.rcam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInEmailFragment extends Fragment implements LogInManager.LogInManagerCallBack {

    private View view;

    public LogInEmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_log_in_email, container, false);

        init();

        return view;
    }

    private void init() {
        view.findViewById(R.id.btmLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
        
    }

    private void logIn() {
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        
        if (email.isEmpty()  || password.isEmpty()){
            Toast.makeText(getContext(), getActivity().getString(R.string.you_must_enter_an_Email_address_and_password_to_continue), Toast.LENGTH_SHORT).show();
            return;
        }

        new LogInManager(this, email, password);
    }

    @Override
    public void onDone(boolean loginSucceeded) {
        updateUI(loginSucceeded);
    }

    private void updateUI(boolean loginSucceeded) {
        if (loginSucceeded){
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }else{
            Toast.makeText(getContext(), getActivity().getString(R.string.Authentication_failed), Toast.LENGTH_SHORT).show();
        }
    }
}
