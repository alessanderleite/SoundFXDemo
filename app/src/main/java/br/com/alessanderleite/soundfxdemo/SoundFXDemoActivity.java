package br.com.alessanderleite.soundfxdemo;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SoundFXDemoActivity extends AppCompatActivity implements View.OnClickListener {

    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributesBuilder;

    int soundID_coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_fx_demo);

        Button myfirstbutton_m2 = (Button) findViewById(R.id.button_myfirstbutton_m2);
        myfirstbutton_m2.setOnClickListener(this);

        Button button_coinsound = (Button) findViewById(R.id.button_coinsound);
        button_coinsound.setOnClickListener(this);

        attributesBuilder = new AudioAttributes.Builder();
        attributesBuilder.setUsage(AudioAttributes.USAGE_ALARM);
        attributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
        attributes = attributesBuilder.build();

        soundPoolBuilder = new SoundPool.Builder();
        soundPoolBuilder.setAudioAttributes(attributes);
        soundPool = soundPoolBuilder.build();

        soundID_coin = soundPool.load(this, R.raw.gamecoin,1);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_coinsound:
                soundPool.play(soundID_coin,1,1,0,0,1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        soundPool.release();
    }
}
