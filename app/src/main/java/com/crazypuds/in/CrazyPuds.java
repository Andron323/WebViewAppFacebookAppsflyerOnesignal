package com.crazypuds.in;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CrazyPuds extends AppCompatActivity {

    private SoundPool soundPool;
    private int soundFleita;
    private int soundBarabam;
    private int soundGuitar;
    private int soundDJ;
    private int soundPiano;
    private int soundSaksafon;
    private int soundSkripka;
    private int soundTruba;
    private int soundBayan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_puds);

    }

    private void sound() {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundBarabam = soundPool.load(getApplicationContext(), R.raw.barabam, 1);
        soundFleita = soundPool.load(getApplicationContext(), R.raw.fleita, 1);
        soundGuitar = soundPool.load(getApplicationContext(), R.raw.guitar, 1);
        soundDJ = soundPool.load(getApplicationContext(), R.raw.dj, 1);
        soundPiano = soundPool.load(getApplicationContext(), R.raw.piano, 1);
        soundSaksafon = soundPool.load(getApplicationContext(), R.raw.saksa, 1);
        soundSkripka = soundPool.load(getApplicationContext(), R.raw.skripka, 1);
        soundTruba = soundPool.load(getApplicationContext(), R.raw.truba, 1);
        soundBayan = soundPool.load(getApplicationContext(), R.raw.bayan, 1);
    }

    public void playFleita(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundBarabam, 1.4f, 1.4f, 0, 0, 9f);
    }

    public void playBaraban(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundFleita, 1.1f, 1.1f, 0, 0, 9f);
    }

    public void playGuitar(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundGuitar, 1.1f, 1.1f, 0, 0, 9f);
    }

    public void playDJ(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundDJ, 1.3f, 1.3f, 0, 0, 9f);
    }

    public void playPiano(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundPiano, 1.1f, 1.1f, 0, 0, 9f);
    }

    public void playSaksafon(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundSaksafon, 1.2f, 1.2f, 0, 0, 9f);
    }

    public void playSkripka(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundSkripka, 1.1f, 1.1f, 0, 0, 9f);
    }

    public void playTruba(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundTruba, 1.2f, 1.2f, 0, 0, 9f);
    }

    public void playBayan(View view) {
        if (soundPool == null) sound();
        soundPool.play(soundBayan, 1.3f, 1.3f, 0, 0, 9f);
    }
}