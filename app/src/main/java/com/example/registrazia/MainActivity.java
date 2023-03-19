package com.example.registrazia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText editTextStr, editTextNum;
    Button btnSave, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "LOGIN";
    final String SAVED_NUM = "PASSWORD";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStr = (EditText) findViewById(R.id.editTextStr);
        editTextNum = (EditText) findViewById(R.id.editTextNum);

        btnSave = (Button) findViewById(R.id.login);
        btnSave.setOnClickListener((View.OnClickListener) this);

        btnLoad = (Button) findViewById(R.id.register);
        btnLoad.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                saveData();
                break;
            case R.id.login:
                loadData();
                break;
            default:
                break;
        }
    }

    void saveData() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userName = editTextStr.getText().toString();
        int userPassword = Integer.parseInt(editTextNum.getText().toString());
        if (sharedPreferences.contains(userName)) {
            Toast.makeText(this, "Пользователь c таким логином уже существует", Toast.LENGTH_LONG).show();
        } else {
            editor.putString(userName, userName);
            editor.putInt(userName + userPassword, userPassword);
            editor.commit();
            Toast.makeText(this, "Пользователь  зарегистрировался", Toast.LENGTH_LONG).show();
        }
    }

    void loadData() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String userName = editTextStr.getText().toString();
        int userPassword = Integer.parseInt(editTextNum.getText().toString());
        if (sharedPreferences.contains(userName)) {
            if (sharedPreferences.contains(userName + userPassword)) {
                if (sharedPreferences.getInt(userName + userPassword, userPassword) == userPassword) {
                    Toast.makeText(this, "Пользователь  авторизовался", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Неправильный пароль", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Неправильный пароль", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Пользователь  не зарегистрирован", Toast.LENGTH_LONG).show();
        }
    }
}
