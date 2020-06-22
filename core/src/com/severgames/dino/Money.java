package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.dino.enemies.EnemyManager;

class Money {
    private Sprite[] sprite;
    private boolean active;
    private float time;
    private int num;
    private float X;

    Money(){
        sprite=new Sprite[6];
        int j=21;
        for(int i=0;i<sprite.length;i++){
            sprite[i]=new Sprite();
            sprite[i].set(SpriteLoad.getSprite(j));
            j++;
        }
        active=false;
        X=0;
    }

    void spawn(int i){
        num=0;
        time=0;
        active=true;
        X=Gdx.graphics.getWidth()+sprite[0].getWidth()*i;
    }

    void update(float delta){
        if(active) {
            time += delta;
            if (time >= 0.08f) {
                num++;
                time = 0;
            }
            if (num == sprite.length) {
                num = 0;
            }
            X-= EnemyManager.speed*delta;
            if (X < -sprite[0].getWidth()) {
                active=false;
            }
            sprite[num].setX(X);
        }
    }

    void draw(SpriteBatch batch){
        if(active){
            sprite[num].draw(batch);
        }
    }

    void resize(float he) {
        float h = Gdx.graphics.getHeight()/16;
        float w=h/sprite[0].getHeight()*sprite[0].getWidth();
        he+=Gdx.graphics.getHeight()/10;
        for(Sprite s:sprite){
            s.setSize(w,h);
            s.setY(he);
        }
    }
}
