package com.whck.iot.controller;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whck.iot.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {


    public LoginFragment() {
        // Required empty public constructor
    }

    private Button btnLogin, btnRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = (Button) root.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnRegister = (Button) root.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        return root;
    }

    private View root;
    private AutoCompleteTextView txtUsername;
    private EditText txtPassword;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                break;
            case R.id.login_btn_register:
                RegisterFragment fragment = new RegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                break;
        }
    }
}
