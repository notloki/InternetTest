package com.notloki.internettest;


import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.io.File;
public class FileUtil extends Util {
    public static boolean isCached = false;
    String fileName;
    File dir;
    FileReader fileReader;
    File history;
    BufferedReader bufferedReader;
    String tmp;

    private String getFileName() {
        return "com.notloki.internettest.results-" + (new SimpleDateFormat(
                "MMddyyyy", Locale.ENGLISH)).format(Calendar.getInstance().getTime()) + ".txt";
    }
    public boolean checkCache(Context context) throws IOException {

        dir = new File(context.getFilesDir(), "history");
        history = new File(dir, getFileName());

        isCached = history.exists();

                return isCached;
    }

        public void saveToFile (Context context, String result){


            fileName = "com.notloki.internettest.results-" + (new SimpleDateFormat(
                    "MMddyyyy", Locale.ENGLISH)).format(Calendar.getInstance().getTime()) + ".txt";

            dir = new File(context.getFilesDir(), "history");
            if (!dir.exists())
                dir.mkdir();

            try {
                File results = new File(dir, getFileName());
                FileWriter writer = new FileWriter(results);
                writer.append(result);
                writer.flush();
                writer.close();
            } catch (Exception e) { 
                e.printStackTrace();
            }


        }

    }

