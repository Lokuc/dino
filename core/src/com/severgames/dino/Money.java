package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.dino.enemies.EnemyManager;

class Money {
    private Sprite[] sprite;
    private Sprite[] hide;
    private boolean active;
    private boolean hiden;
    private float time;
    private int num;
    private float X;

    Money(){
        sprite=new Sprite[6];
        hide=new Sprite[7];
        int j=21;
        for(int i=0;i<sprite.length;i++){
            sprite[i]=new Sprite();
            sprite[i].set(SpriteLoad.getSprite(j));
            j++;
        }
        for(int i=0;i<hide.length;i++){
            hide[i]=new Sprite();
            hide[i].set(SpriteLoad.getSprite(j));
            j++;
        }
        active=false;
        hiden=false;
        X=0;
    }

    void respawn(){
        num=0;
        time=0;
        active=false;
        hiden=false;
        X=Gdx.graphics.getWidth()+sprite[0].getWidth();
    }

    void spawn(int i){
        num=0;
        time=0;
        active=true;
        hiden=false;
        X=Gdx.graphics.getWidth()+sprite[0].getWidth()*i;
    }

    void update(float delta){
        if(active) {
            if(!hiden) {
                time += delta;
                if (time >= 0.08f) {
                    num++;
                    time = 0;
                }
                if (num == sprite.length) {
                    num = 0;
                }
                X -= EnemyManager.speed * delta;
                if (X < -sprite[0].getWidth()) {
                    active = false;
                }
                sprite[num].setX(X);
            }else{
                time+=delta;
                if(time>=0.09f){
                    num++;
                    time=0;
                }
                if(num==hide.length){
                    num=0;
                    hiden = false;
                    active=false;
                    return;
                }
                X -= EnemyManager.speed * delta;
                if (X < -hide[num].getWidth()) {
                    hiden = false;
                    active=false;
                    num=0;
                }
                hide[num].setX(X);
            }
        }
    }

    void draw(SpriteBatch batch){
        if(active){
            if(!hiden) {
                sprite[num].draw(batch);
            }else{
                hide[num].draw(batch);
            }
        }
    }

    Rectangle getRect(){
        if(!hiden&&active) {
            return sprite[num].getBoundingRectangle();
        }
        return null;
    }

    void delete(){
        hiden=true;
        num=0;
        time=0;
        X+=sprite[0].getWidth()/2-hide[0].getWidth()/2f;
    }

    void resize(float he) {
        float h = Gdx.graphics.getHeight()/16;
        float w=h/sprite[0].getHeight()*sprite[0].getWidth();
        he+=Gdx.graphics.getHeight()/10;
        for(Sprite s:sprite){
            s.setSize(w,h);
            s.setY(he);
        }
        w=h/hide[0].getHeight()*hide[0].getWidth();
        for(Sprite s:hide){
            s.setSize(w,h);
            s.setY(he);
        }
    }
}
