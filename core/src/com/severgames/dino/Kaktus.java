package com.severgames.dino;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

class Kaktus {

    private Sprite sprite;
    private Sprite stone;
    private Sprite [] fons;
    private float speed=600f;
    private float X;
    private Rectangle temp=new Rectangle();
    private float timespawn=0;
    private float y=0;
    private boolean spawn=false;
    private Random r = new Random();
    private boolean type=false;


    Kaktus(){
        fons= new Sprite[2];
        fons[0]=new Sprite(new Texture("textrure/fonEnemy.png"));
        fons[1]=new Sprite(new Texture("textrure/fonEnemy2.png"));
        sprite=new Sprite(new Texture("textrure/enemy.png"));
        stone= new Sprite(new Texture("textrure/enemy2.png"));
        float w = MyGdxGame.H/5;
        float h = (w/sprite.getWidth()*sprite.getHeight());
        sprite.setSize(w,h);
        sprite.setPosition(MyGdxGame.W-sprite.getWidth(),MyGdxGame.H/5+10);
        X = sprite.getX();
    }

    Rectangle getRect(){
        if(type){
            return stone.getBoundingRectangle();
        }else {
            temp = sprite.getBoundingRectangle();
            temp.width -= 20;
            temp.height -= 15;
            return temp;
        }
    }

    void setXC(boolean c){
        if(c){
            X=MyGdxGame.W-sprite.getWidth();
            sprite.setX(X);
        }else{
            X=(MyGdxGame.W-sprite.getWidth())/2;
            sprite.setX(X);
        }
    }


    void resize(float height,float he,float Y) {
        y=height;
        float w = MyGdxGame.H/5;
        float h = (w/sprite.getWidth()*sprite.getHeight());
        sprite.setSize(w,h);
        sprite.setY(y);
        h=he;
        w = (h/fons[0].getHeight()*fons[0].getWidth());
        fons[0].setSize(w,h);
        h=he;
        w = (h/fons[1].getHeight()*fons[1].getWidth());
        fons[1].setSize(w,h);
        w = fons[1].getWidth();
        h = (w/stone.getWidth()*stone.getHeight());
        stone.setSize(w,h);
        fons[0].setY(height);
        fons[1].setY(height);
        stone.setY(he+Y);
    }

    void spawn(){
        X=MyGdxGame.W;
        if(type){
            fons[0].setX(X);
            fons[1].setX(X+fons[0].getWidth()/3);
            stone.setX(X+fons[0].getWidth()/3);
        }else {
            sprite.setX(X);
        }
        speed=700f;
    }

    void draw(SpriteBatch batch){
        if(timespawn>=3.0f) {
            if(type){
                fons[0].draw(batch);
                fons[1].draw(batch);
                stone.draw(batch);
            }else {
                sprite.draw(batch);
            }
        }
    }



    void update(float delta){
        timespawn+=delta;
        if(timespawn>=3.0f) {
            if(!spawn){
                spawn();
                spawn=true;
            }
            X -= speed * delta;
            speed += delta;
            if (X < -sprite.getWidth()) {
                timespawn=1.5f;
                type = r.nextBoolean();
                if(type){
                    X = MyGdxGame.W;
                    fons[0].setX(X);
                    fons[1].setX(X+fons[0].getWidth()/3);
                    stone.setX(X+fons[0].getWidth()/3);
                }else {
                    X = MyGdxGame.W + sprite.getWidth();
                }
            }
            fons[0].setX(X-fons[0].getWidth()/3);
            fons[1].setX(X);
            stone.setX(X);
            sprite.setX(X);
        }

    }



}
