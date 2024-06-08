package com.example.abbajabbajabba;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button changewallper;
    Timer mytimer;
    Drawable drawable;
    WallpaperManager wpm;
    int prev = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mytimer=new Timer();
        wpm=WallpaperManager.getInstance(this);
        changewallper=findViewById(R.id.button);
        changewallper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setWallpaper();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void setWallpaper() throws IOException {
        mytimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (prev == 1) {
                    drawable = getResources().getDrawable(R.drawable.l1);
                    prev = 2;
                }

                else if(prev==2)

                {
                    drawable = getResources().getDrawable(R.drawable.l2);
                    prev = 3;
                }
                else if(prev==3)
                {
                    drawable = getResources().getDrawable(R.drawable.l3);
                    prev = 4;
                }
                else if(prev==4)

                {
                    drawable = getResources().getDrawable(R.drawable.l4);
                    prev = 5;
                }
                else if(prev==5)

                {
                    drawable = getResources().getDrawable(R.drawable.l5);
                    prev = 1;
                }

                Bitmap wp = ((BitmapDrawable) drawable).getBitmap();
                try

                {
                    wpm.setBitmap(wp);
                }catch(IOException e)

                {
                    e.printStackTrace();
                }

            }
        },0,30000);
    }
}