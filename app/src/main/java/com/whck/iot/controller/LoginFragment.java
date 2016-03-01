package com.whck.iot.controller;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whck.iot.R;
import com.whck.iot.model.GetPostUtil;
import com.whck.iot.model.LocalUtil;
import com.whck.iot.model.ServerUtil;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

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
        btnLogin = (Button) root.findViewById(R.id.login_btn_login);
        btnLogin.setOnClickListener(this);
        btnRegister = (Button) root.findViewById(R.id.login_btn_register);
        btnRegister.setOnClickListener(this);
        txtPassword = (EditText) root.findViewById(R.id.login_password);
        txtUsername = (AutoCompleteTextView) root.findViewById(R.id.login_username);
        return root;
    }

    private View root;
    private AutoCompleteTextView txtUsername;
    private EditText txtPassword;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x0001:
                    Toast.makeText(getActivity(), msg.getData().getString("msg"), Toast.LENGTH_SHORT).show();
                    break;
                case 0x0002:
                    Map<String,Object> user= (Map<String, Object>) msg.getData().getSerializable("user");
                    LocalUtil.EDITOR.putString("username", (String) user.get("username"));
                    LocalUtil.EDITOR.putInt("id", (Integer) user.get("id"));
                    LocalUtil.EDITOR.putString("name", (String) user.get("name"));
                    String name=LocalUtil.PREFERENCES.getString("name","存储失败");
                    Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                final String params = "username=" + username + "&password=" + password;
                new Thread() {
                    @Override
                    public void run() {
                        String tmp = GetPostUtil.sendPost(ServerUtil.CONTEXTPATH + "user/login.do", params);
                        ObjectMapper mapper = new ObjectMapper();
                        JavaType type = mapper.getTypeFactory().constructType(Map.class, Serializable.class);
                        try {
                            Map<String, Object> map = new ObjectMapper().readValue(tmp, type);
                            if (map.containsKey("msg")) {
                                Message message = new Message();
                                message.what = 0x0001;
                                message.setData(new Bundle());
                                message.getData().putString("msg", map.get("msg").toString());
                                handler.sendMessage(message);
                            }
                            Boolean success = (Boolean) map.get("success");
                            if ((null != success) && success) {
                                Map<String,Object> user= (Map<String, Object>) map.get("user");
                                Message message=new Message();
                                message.what=0x0002;
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("user", (Serializable) user);
                                message.setData(bundle);
                                handler.sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.login_btn_register:
                RegisterFragment fragment = new RegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                break;
        }
    }
}
