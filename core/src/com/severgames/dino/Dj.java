package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Dj {

    private Sound[] sounds; //fon, end , run
    private long[] ids;
    Dj(){
        sounds=new Sound[2];
        ids=new long[2];
    }

    void load(){
        sounds[0] = Gdx.audio.newSound(Gdx.files.internal("sound/fon.mp3"));
        sounds[1] = Gdx.audio.newSound(Gdx.files.internal("sound/end.mp3"));
    }

    void playFon(){
        ids[0]=sounds[0].loop( 0.5f);
    }
    void playEnd(){
        ids[1]=sounds[1].play(0.7f);
    }

    void resumeFon(){
        sounds[0].resume(ids[0]);
    }


    void pause() {
        sounds[0].pause(ids[0]);
    }
}
