package com.severgames.dino.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

class Wall {
    private static Sprite sprite;
    private static Sprite sprite1;
    private static Sprite sprite2;
    private boolean isSpawn;
    private float X;
    private Rectangle temp=new Rectangle();
    private float w,h;
    private Mother mother;

    Wall(Mother mother){
        this.mother=mother;
        sprite=new Sprite(new Texture("texture/enemy/3.png"));
        sprite1=new Sprite(new Texture("texture/enemy/fonEnemy.png"));
        sprite2=new Sprite(new Texture("texture/enemy/fonEnemy2.png"));
    }
    void spawn(){
        isSpawn=true;
        X= Gdx.graphics.getWidth()+sprite.getWidth();
        sprite.setX(X);
        sprite2.setX(X);
        sprite1.setX(X-50);
    }
    void update(float delta){
        X-= EnemyManager.speed*delta;
        EnemyManager.speed+=delta;
        if(X<-sprite.getWidth()){
            isSpawn=false;
            mother.imDead(getClass()+"");
        }
        sprite.setX(X);
        sprite1.setX(X);
        sprite2.setX(X+sprite1.getWidth()/3f);
    }
    void draw(SpriteBatch batch){
        sprite.draw(batch);
        sprite1.draw(batch);
    }
    void draw1(SpriteBatch batch){
        sprite2.draw(batch);
    }
    void resize(float H,float he){
        h = he;
        w = (h/sprite1.getHeight()*sprite1.getWidth());
        sprite1.setSize(w,h);
        sprite1.setPosition(Gdx.graphics.getWidth(),H);
        h = he+sprite1.getHeight()/5;
        w = (h/sprite2.getHeight()*sprite2.getWidth());
        sprite2.setSize(w,h);
        sprite2.setPosition(Gdx.graphics.getWidth()+sprite1.getWidth()/3,H);
        h = Gdx.graphics.getHeight()/2;
        w = (h/sprite.getHeight()*sprite.getWidth());
        sprite.setSize(w,h);
        sprite.setPosition(Gdx.graphics.getWidth(),he+H+1);
    }

    Rectangle getRect(){
        temp=sprite.getBoundingRectangle(); //TODO edit temp
        return temp;
    }
}
