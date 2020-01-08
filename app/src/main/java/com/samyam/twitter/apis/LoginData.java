package com.samyam.twitter.apis;

import com.samyam.twitter.model.SignUp;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginData {
    boolean isSuccess = false;
    public static String Token;

    public boolean checkUser(String username, String password) {

        APIClass usersAPI = new APIClass();
        Call<SignUp> usersCall = usersAPI.calls().checkUser(username, password);

        try {
            Response<SignUp> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                SignUp signUpResponse=loginResponse.body();
                Token=signUpResponse.getToken();

                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
