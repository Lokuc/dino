package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Data {

    private static Preferences preferences;

    public Data() {
        if(preferences==null){
            preferences = Gdx.app.getPreferences("Preference");
        }
        saveMoney(getMoney());
    }

    public int getMoney(){
        return preferences.getInteger("money");
    }

    public void saveMoney(int i){
        preferences.putInteger("money",i);
        preferences.flush();
    }
}
