package com.severgames.dino.enemies;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.dino.SpriteLoad;

class Wormix {
        private static Sprite sprite;
        private boolean isSpawn;
        private float X;
        private Rectangle temp=new Rectangle();
        private float w,h;
        private Mother mother;

        Wormix(Mother mother){
            this.mother=mother;
            sprite= SpriteLoad.getSprite(0);
            isSpawn=false;
        }

        void spawn(){
            isSpawn=true;
            X= Gdx.graphics.getWidth()+sprite.getWidth()/2;
            sprite.setX(X);
        }

        void update(float delta){
            X-= EnemyManager.speed*delta;
            EnemyManager.speed+=delta;
            if(X<-sprite.getWidth()){
                isSpawn=false;
                mother.imDead(getClass()+"");
            }
            sprite.setX(X);
        }

        void draw(SpriteBatch batch){
            sprite.draw(batch);
        }

        void resize(float H){
            h = Gdx.graphics.getHeight()/5;
            w = (h/sprite.getHeight()*sprite.getWidth());
            sprite.setSize(w,h);
            sprite.setPosition(Gdx.graphics.getWidth()+sprite.getWidth()/2,H);
        }

        Rectangle getRect(){
            temp=sprite.getBoundingRectangle(); //TODO edit temp
            temp.width=temp.width/6;
            temp.height-=temp.height/6;
            return temp;
        }
        Rectangle getGround(){
            temp=sprite.getBoundingRectangle();
            //temp.height-=temp.height/8;
            //temp.width-=temp.width/5;
            temp.x+=temp.width/5;
            temp.width-=temp.width/5;
            temp.y+=temp.height-temp.height/7;
            temp.height=temp.height/10;
            return temp;
        }
}
