package com.severgames.dino.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class EnemyManager implements Mother {

    public static float speed = 600f;
    private Wormix wormix;
    private Wall wall;
    private int type;
    private boolean isSpawn;
    private float time;
    private float timeA;
    private Random r;

    public EnemyManager(){
        isSpawn=false;
        time=0f;
        r=new Random();
        wall=new Wall(this);
        wormix = new Wormix(this);
        timeA=4f;
    }

    public void update(float delta){
        timeA-=delta/40;
        if(isSpawn) {
            if (type == 0) {
                wormix.update(delta);
            }else if(type==1){
                wall.update(delta);
            }
        }else{
            time+=delta;
            if(time>=timeA){
                spaw();
            }
        }
    }
    public void draw(SpriteBatch batch){
        if(isSpawn) {
            if (type == 0) {
                wormix.draw(batch);
            } else if (type == 1) {
                wall.draw(batch);
            }
        }
    }
    public void draw1(SpriteBatch batch){
        if(isSpawn) {
            if (type == 1) {
                wall.draw1(batch);
            }
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
        timeA=4f;
        spaw();
    }
    private void spaw(){
        int count = 2;
        type=r.nextInt(count);
        //type=1;
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

    public Rectangle getGround() {
        if(isSpawn&&type==0){
            return wormix.getGround();
        }
        return null;
    }

    public Color getGroundColor() {
        return Color.BLUE;
    }

    public Color getColor() {
        return Color.RED;
    }
}
