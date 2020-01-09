package com.samyam.twitter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.samyam.twitter.apis.APIClass;
import com.samyam.twitter.model.ImageModel;
import com.samyam.twitter.model.SignUp;
import com.samyam.twitter.model.User;
import com.samyam.twitter.strictMode.StrictModeClass;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageActivity extends AppCompatActivity {
    ImageView profile;
    Button login;
    String password, email, username, imageName;
    String imagePath = "";
    public static String token = "Bearer ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        profile = findViewById(R.id.profile);
        login = findViewById(R.id.final_next);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
            username = bundle.getString("username");
            password = bundle.getString("password");
        }
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagePath.isEmpty()) {
                    Toast.makeText(ImageActivity.this, "Select Image first", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ImageActivity.this, "Allah" + imagePath, Toast.LENGTH_SHORT).show();
                   // saveOnlyImage();

                signUp();
                }
            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        profile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveOnlyImage() {
        Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image",
                file.getName(), requestBody);

        APIClass usersAPI = new APIClass();
        Call<ImageModel> response = usersAPI.calls().uploadImage(body);

        Toast.makeText(this, "after image ", Toast.LENGTH_SHORT).show();

        StrictModeClass.StrictMode();
        //Using Synchronous method
        try {
            Response<ImageModel> imageResponseResponse = response.execute();
            imageName = imageResponseResponse.body().getImage();
            Toast.makeText(this, "after image " + imageName, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void signUp() {
        User users = new User(email, password, username, "1578565317549pietersen.jpg");

        APIClass usersAPI = new APIClass();
        final Call<SignUp> signUpCall = usersAPI.calls().register(users);

        signUpCall.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ImageActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                SignUp signUpResponse = response.body();
                token = signUpResponse.getToken();
                Log.d("token", token);
                Intent intent = new Intent(ImageActivity.this, HomeActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Toast.makeText(ImageActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
