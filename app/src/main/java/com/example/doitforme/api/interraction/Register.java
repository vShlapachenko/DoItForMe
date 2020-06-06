package com.example.doitforme.api.interraction;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.doitforme.data.model.UserDTO;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AsyncTask<String, String, Integer> {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final int BAD_REQUEST = 400;
    public AsyncRegisterResponse response = null;

    UserDTO userDTO;


    public Register(UserDTO userDTO, AsyncRegisterResponse response) {
        this.userDTO = userDTO;
        this.response = response;
    }

    public Register() {
    }

    OkHttpClient client = new OkHttpClient();

    int post(String url) throws IOException {
        String json = new Gson().toJson(userDTO);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.code();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {
        String urlString = "http://10.0.2.2:8080/api/register";

        try {
            int result = post(urlString);
            response.processFinish(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BAD_REQUEST;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
