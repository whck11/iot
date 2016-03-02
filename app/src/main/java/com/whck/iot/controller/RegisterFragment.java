package com.whck.iot.controller;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.whck.iot.R;
import com.whck.iot.model.GetPostUtil;
import com.whck.iot.model.ServerUtil;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    private TextView tvUsername, tvPassword, tvPassword2, tvEmail, tvPhone, tvCode;
    private Button btnSend, btnRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        tvUsername = (TextView) root.findViewById(R.id.reg_username);
        tvPassword = (TextView) root.findViewById(R.id.reg_password);
        tvPassword2 = (TextView) root.findViewById(R.id.reg_password2);
        tvEmail = (TextView) root.findViewById(R.id.reg_email);
        tvPhone = (TextView) root.findViewById(R.id.reg_phone);
        tvCode = (TextView) root.findViewById(R.id.reg_code);
        btnSend = (Button) root.findViewById(R.id.reg_btn_send_code);
        btnRegister = (Button) root.findViewById(R.id.reg_btn_register);
        sendCodeListener();
        registerListener();
        setHandler();
        return root;
    }

    private ObjectMapper mapper = new ObjectMapper();
    private static final String SEND_CODE_ERROR_MSG = "send_code_error_msg";
    private Handler handler;

    private void setHandler() {
        this.handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0x0003:
                        Toast.makeText(getActivity(), msg.getData().getString(SEND_CODE_ERROR_MSG), Toast.LENGTH_SHORT).show();
                        break;
                    case 0x0004:
                        Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                        break;
                    case 0x0005:
                        Toast.makeText(getActivity(), msg.getData().getString(REGISTER_ERROR_MSG), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    private void registerListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String username = tvUsername.getText().toString();
                        String email = tvEmail.getText().toString();
                        String password = tvPassword.getText().toString();
                        String password2 = tvPassword2.getText().toString();
                        String phones = tvPhone.getText().toString();
                        String code = tvCode.getText().toString();
                        String param = "username=" + username + "&email=" + email + "&password=" + password + "&password2=" + password2 + "&phone=" + phones + "&code=" + code;
                        String result = GetPostUtil.sendPost(ServerUtil.CONTEXTPATH + "user/register.do", param);
                        JavaType type1 = mapper.getTypeFactory().constructType(Map.class,Serializable.class);
                        try {
                            Map<String, Serializable> map = mapper.readValue(result, type1);
                            if (map.containsKey("success")) {
                                handler.sendEmptyMessage(0x0004);
                            } else {
                                Message message = new Message();
                                message.what = 0x0005;
                                message.setData(new Bundle());
                                message.getData().putString(REGISTER_ERROR_MSG, map.get("msg").toString());
                                handler.sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    private void sendCodeListener() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String phone = tvPhone.getText().toString();
                        String params = "phone=" + phone;
                        String tmp = GetPostUtil.sendGet(ServerUtil.CONTEXTPATH + "user/sendCode.do", params);
                        JavaType type = mapper.getTypeFactory().constructType(Map.class,Serializable.class);
                        try {
                            Map<String, Serializable> map = mapper.readValue(tmp, type);
                            if (map.containsKey("success")) {
                                RegisterFragment.this.code = map.get("code").toString();
                            } else {
                                Message message = new Message();
                                message.what = 0x0003;
                                message.setData(new Bundle());
                                message.getData().putString(SEND_CODE_ERROR_MSG, map.get("msg").toString());
                                handler.sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }

    private String code;
    private static final String REGISTER_ERROR_MSG = "login_error_msg";
}
