package com.notloki.internettest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    public static final String URL =
            "https://sentry.cordanths.com/Sentry/WebCheckin/Log";
    private final String body = "phone=8662072911&last_name=Burke+Jr&ivr_code=102874&lang=en";
    private static String result = "NotNull";
    public TextView main_textview_one;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // User user = new User("Burke Jr", "8662072911", "102874");
        getBondData();
        main_textview_one = findViewById(R.id.main_textView_one);

        main_textview_one.setText(result);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("com.notloki.internettest.results", result);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result = savedInstanceState.getString("com.notloki.internettest.results", "NotNull");
        main_textview_one = findViewById(R.id.main_textView_one);
        main_textview_one.setText(result);
    }

    public void getBondData() {

        OkHttpClient client = new OkHttpClient();
        // String body = ("phone=" + phone + "&" + "last_name=" +
        // last_name + "&" + "ivr_code=" + ivr_code + "&" + "lang=" + lang);
        // String body =  user.toString();

        Request request = new Request.Builder()

                .url(URL)
                .addHeader("authority", "sentry.cordanths.com")
                .addHeader("accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Mobile Safari/537.36")
                .addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Origin", "https://www.mycallin.com")
                .addHeader("sec-fetch-site", "cross-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://www.mycallin.com/")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .post(RequestBody
                        .create(body, MediaType
                                .parse("application/x-www-form-urlencoded")))
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("Fail");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException,
                    NullPointerException {

                System.out.println("Success");
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    throw e;
                } catch (NullPointerException n) {
                    n.printStackTrace();
                    throw n;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        main_textview_one = findViewById(R.id.main_textView_one);
                        main_textview_one.setText(result);
                    }
                });
            }
        });
    }
}