package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

class Dino {

    private Sprite sprite;
    private boolean inFly=false;
    private int Y=0;
    private float speedFly=0;
    private boolean inGopnik = false;
    private float y=0;
    private Sprite[] anim;
    private Sprite[] gAnim;
    private float timeAnim=0f;
    private int skinNum;
    private boolean gSkinNum=false;
    private Rectangle temp=new Rectangle();
    private Sprite[] fly;


    Dino(){
        gAnim= new Sprite[2];
        gAnim[0]=new Sprite(new Texture("textrure/animGopnik/gopnik0.png"));
        gAnim[1]=new Sprite(new Texture("textrure/animGopnik/gopnik1.png"));
        anim= new Sprite[7];
        sprite = new Sprite(new Texture("textrure/animation/anim0.png"));
        float w = MyGdxGame.H/5;
        float h = (w/sprite.getWidth()*sprite.getHeight());
        for(int i=0;i<anim.length;i++){
            anim[i]=new Sprite(new Texture("textrure/animation/anim"+i+".png"));
            anim[i].setSize(w,h);
        }
        sprite.setSize(w,h);
        w = sprite.getWidth();
        h = (w/gAnim[0].getWidth()*gAnim[0].getHeight());
        gAnim[0].setSize(w,h);
        gAnim[1].setSize(w,h);
        fly= new Sprite[2];
        sprite.setSize(w,h);
        fly[0]=new Sprite(new Texture("textrure/fly/up.png"));
        fly[1]=new Sprite(new Texture("textrure/fly/down.png"));
        w = sprite.getWidth();
        h = (w/fly[0].getWidth()*fly[0].getHeight());
        fly[0].setSize(w,h);
        fly[1].setSize(w,h);
    }

    void spawn(){
        for (Sprite value : anim) {
            value.setPosition(150, y);
        }
        sprite.setPosition(150,y);
        gAnim[0].setPosition(150,y);
        gAnim[1].setPosition(150,y);
        fly[0].setPosition(150,y);
        fly[1].setPosition(150,y);
        Y= (int) sprite.getY();
        speedFly=0;
        inFly=false;
    }


    void resize(float height) {
        float w = MyGdxGame.H/5;
        float h = (w/sprite.getWidth()*sprite.getHeight());
        sprite.setSize(w,h);
        w = sprite.getWidth();
        h = (w/gAnim[0].getWidth()*gAnim[0].getHeight());
        gAnim[0].setSize(w,h);
        gAnim[1].setSize(w,h);
        w = sprite.getWidth();
        h = (w/fly[0].getWidth()*fly[0].getHeight());
        fly[0].setSize(w,h);
        fly[1].setSize(w,h);
        y=height;
        spawn();
    }

    void update(float delta){
        timeAnim+=delta;
        if(!inFly) {
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                inGopnik = true;
                if(timeAnim>=0.05f){
                    gSkinNum=!gSkinNum;
                    timeAnim=0;
                }
            } else {
                inGopnik = false;
            }
        }
        if (!inGopnik&&!inFly&&(Gdx.input.isKeyJustPressed(Input.Keys.UP)||Gdx.input.isKeyJustPressed(Input.Keys.W))){
            inFly=true;
            speedFly=18f;
        }
        if(inFly){
            speedFly-=delta*40;
            Y+=speedFly;
            if(Y<y){
                Y= (int) y;
                inFly=false;
            }
        }
        if(timeAnim>=0.1f){
            skinNum++;
            if(skinNum==anim.length){
                skinNum=0;
            }
            timeAnim=0;
        }
        anim[0].setY(Y);
        anim[skinNum].setY(Y);
        sprite.setY(Y);
        fly[0].setY(Y);
        fly[1].setY(Y);

    }

    void draw(SpriteBatch batch){
        if(inGopnik) {
            if(gSkinNum) {
                gAnim[0].draw(batch);
            }else{
                gAnim[1].draw(batch);
            }
        }else if(inFly){
            if(speedFly>0){
                fly[0].draw(batch);
            }else{
                fly[1].draw(batch);
            }
        }else{
            anim[skinNum].draw(batch);
        }
    }

     Rectangle getReckt(){
        if (inGopnik) {
            return gAnim[0].getBoundingRectangle();
        }else{
            temp=anim[0].getBoundingRectangle();
            temp.x+=temp.width/6;
            temp.width-=temp.width/3;
            return temp;
        }
    }

    void checkCol(Kaktus kaktus, Frame frame) {
        if(kaktus.getRect().overlaps(getReckt())){
            frame.dead();
        }

    }

    public float getH() {
        return gAnim[0].getHeight();
    }
    float getY(){
        return gAnim[0].getY();
    }
}
