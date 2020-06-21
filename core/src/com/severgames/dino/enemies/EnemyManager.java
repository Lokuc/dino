package com.severgames.dino.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class EnemyManager implements Mother {

    static float speed = 600f;
    private Wormix wormix;
    private Wall wall;
    private int type;
    private boolean isSpawn;
    private float time;
    private Random r;

    public EnemyManager(){
        isSpawn=false;
        time=0f;
        r=new Random();
        wall=new Wall(this);
        wormix = new Wormix(this);
    }

    public void update(float delta){
        if(isSpawn) {
            if (type == 0) {
                wormix.update(delta);
            }else if(type==1){
                wall.update(delta);
            }
        }else{
            time+=delta;
            if(time>=4f){
                spawn();
            }
        }
    }
    public void draw(SpriteBatch batch){
        if(type==0){
            wormix.draw(batch);
        }else if(type==1){
            wall.draw(batch);
        }
    }
    public void draw1(SpriteBatch batch){
        if(type==1){
            wall.draw1(batch);
        }
    }
    public Rectangle getRect(){
        if(type==0){
            return wormix.getRect();
        }else if(type==1){
            return wall.getRect();
        }
        return null;
    }
    public void spawn() {
        int count = 2;
        //type=r.nextInt(count);
        type=1;
        isSpawn=true;
        if(type==0){
            wormix.spawn();
        }else if(type==1){
            wall.spawn();
        }
    }
    public void resize(float H,float h){
        wormix.resize(H);
        wall.resize(H,h);
    }
    @Override
    public void imDead(String type) {
        isSpawn=false;
        time=0f;
    }
}