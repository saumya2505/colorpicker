package com.example.colorpickerwithhexcodedisplay;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    int px;
    LinearLayout LLcolorPick;
    ImageView IVcolor;
    TextView TVfillColor,TVcolorCode;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IVcolor=findViewById(R.id.IVcolor);
        TVfillColor=findViewById(R.id.TVfillColor);
        TVcolorCode=findViewById(R.id.TVcolorCode);
        LLcolorPick=findViewById(R.id.LLcolorPick);
        seekBar=findViewById(R.id.seekbar);
        seekBar.setMax(11);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        seekBar.setProgress(seekBar.getMax());
        IVcolor.setDrawingCacheEnabled(true);
        IVcolor.buildDrawingCache(true);
        LLcolorPick.setDrawingCacheEnabled(true);
        LLcolorPick.buildDrawingCache(true);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float[] hsv = new float[3];

                Color.colorToHSV(px, hsv);
                hsv[2] = i*(hsv[2]*0.1f);
                TVfillColor.setBackgroundColor(Color.HSVToColor(hsv));
                TVfillColor.getBackground();
                if (TVfillColor.getBackground() instanceof ColorDrawable) {
                    ColorDrawable cd = (ColorDrawable) TVfillColor.getBackground();
                    int colorCode = cd.getColor();
                    String strcolorCode=Integer.toHexString(colorCode);
                    //TVcolorCode.setText(strcolorCode);
                    TVfillColor.setText(strcolorCode);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        IVcolor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN || motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    Bitmap bitmap=IVcolor.getDrawingCache();
                    if((int)motionEvent.getX()<bitmap.getWidth() && (int)motionEvent.getY()>=0 && (int)motionEvent.getY()<bitmap.getHeight() && (int)motionEvent.getX()>=0) {
                        px = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());
                        int r = Color.red(px);
                        int g = Color.green(px);
                        int b = Color.blue(px);
                        String colorCode = Integer.toHexString(px);
                        TVfillColor.setBackgroundColor(Color.rgb(r, g, b));
                        //TVcolorCode.setBackgroundColor(Color.rgb(r,g,b));
                        TVfillColor.setText(colorCode);
                    }

                }
                return true;
            }
        });
        LLcolorPick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN || motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    Bitmap bitmap=LLcolorPick.getDrawingCache();
                    if((int)motionEvent.getX()< bitmap.getWidth() && (int)motionEvent.getY()>=0 && (int)motionEvent.getY()<bitmap.getHeight() && (int)motionEvent.getX()>=0 ) {
                        px = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());
                        int r = Color.red(px);
                        int g = Color.green(px);
                        int b = Color.blue(px);
                        String colorCode = Integer.toHexString(px);
                        TVfillColor.setBackgroundColor(Color.rgb(r, g, b));
                        //TVcolorCode.setBackgroundColor(Color.rgb(r,g,b));
                        TVcolorCode.setText(colorCode);
                    }
                }
                return true;
            }
        });

    }
}
