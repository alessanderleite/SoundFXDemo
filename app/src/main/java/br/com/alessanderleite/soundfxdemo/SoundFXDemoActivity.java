package br.com.alessanderleite.soundfxdemo;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
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

    AudioManager audioManager;
    float curVolume, maxVolume, volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_fx_demo);

        Button button_coinsound = (Button) findViewById(R.id.button_coinsound);
        button_coinsound.setOnClickListener(this);

        Button myfirstbutton_m2 = (Button) findViewById(R.id.button_lowsound);
        myfirstbutton_m2.setOnClickListener(this);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        createSoundPool();
        loadSounds();
        volumeSounds();
    }

    protected void volumeSounds() {

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        curVolume = (float)audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float)audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volume = curVolume / maxVolume;
    }

    protected void loadSounds() {

        soundID_coin = soundPool.load(this, R.raw.gamecoin,1);

    }

    protected void createSoundPool() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            attributesBuilder = new AudioAttributes.Builder();
            attributesBuilder.setUsage(AudioAttributes.USAGE_ALARM);
            attributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
            attributes = attributesBuilder.build();

            soundPoolBuilder = new SoundPool.Builder();
            soundPoolBuilder.setAudioAttributes(attributes);
            soundPool = soundPoolBuilder.build();

        } else {

            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        soundPool.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createSoundPool();
        loadSounds();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_coinsound:
                soundPool.play(soundID_coin,1,1,0,0,1);
                break;

            case R.id.button_lowsound:
                soundPool.play(soundID_coin, volume/8, volume/8,0,0,1);
                break;

        }
    }
}
