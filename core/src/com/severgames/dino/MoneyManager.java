package com.severgames.dino;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MoneyManager {
    private Money[] money;
    private Random r;
    private int tmp;
    private float time=0;

    MoneyManager(){
        money=new Money[8];
        for (int i = 0; i < money.length; i++) {
            money[i]=new Money();
        }
        r= new Random();
    }

    private void spawn(){
        if(time>=3.0f){
            tmp = 3 + r.nextInt(5);
            for (int i = 0; i < tmp; i++) {
                money[i].spawn(i);
            }
            time=0;
        }

    }

    void draw(SpriteBatch batch){
        for(Money m:money){
            m.draw(batch);
        }
    }

    void update(float delta){
        time+=delta;
        spawn();
        for(Money m:money){
            m.update(delta);
        }
    }

    void resize(float he){
        for(Money m:money){
            m.resize(he);
        }
    }
}
