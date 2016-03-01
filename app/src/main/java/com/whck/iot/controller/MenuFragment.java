package com.whck.iot.controller;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.whck.iot.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {


    public MenuFragment() {
        // Required empty public constructor
    }

    private Button btn1, btn2, btn3, btn4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        btn1 = (Button) root.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) root.findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) root.findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = (Button) root.findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Bundle arguments1 = new Bundle();
                arguments1.putString(HomeFragment.LABEL, btn1.getText().toString());
                HomeFragment fragment1 = new HomeFragment();
                fragment1.setArguments(arguments1);
                getFragmentManager().beginTransaction().replace(R.id.main_container, fragment1).commit();
                break;
            case R.id.btn2:
                Bundle arguments2 = new Bundle();
                arguments2.putString(HomeFragment.LABEL, btn2.getText().toString());
                HomeFragment fragment2 = new HomeFragment();
                fragment2.setArguments(arguments2);
                getFragmentManager().beginTransaction().replace(R.id.main_container, fragment2).commit();
                break;
            case R.id.btn3:
                Bundle arguments3 = new Bundle();
                arguments3.putString(HomeFragment.LABEL, btn3.getText().toString());
                HomeFragment fragment3 = new HomeFragment();
                fragment3.setArguments(arguments3);
                getFragmentManager().beginTransaction().replace(R.id.main_container, fragment3).commit();
                break;
            case R.id.btn4:
                Bundle arguments4 = new Bundle();
                arguments4.putString(HomeFragment.LABEL, btn4.getText().toString());
                HomeFragment fragment4 = new HomeFragment();
                fragment4.setArguments(arguments4);
                getFragmentManager().beginTransaction().replace(R.id.main_container, fragment4).commit();
                break;
        }
    }
}
