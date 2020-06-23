package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

class Dino {

    private Sprite sprite;
    private boolean inFly=false;
    private int Y=0;
    private float speedFly=0;
    private boolean inGopnik = false;
    private boolean onWorm=false;
    private float y=0;
    private float x;
    private Sprite[] anim;
    private Sprite[] gAnim;
    private float timeAnim=0f;
    private int skinNum;
    private boolean gSkinNum=false;
    private Rectangle temp=new Rectangle();
    private Sprite[] fly;
    private int money;


    Dino(){
        money=new Data().getMoney();
        System.out.println(money);
        gAnim= new Sprite[2];
        gAnim[0]=SpriteLoad.getSprite(17);
        gAnim[1]=SpriteLoad.getSprite(18);
        anim= new Sprite[7];
        sprite = SpriteLoad.getSprite(10);
        int j=10;
        for(int i=0;i<anim.length;i++){
            anim[i]=SpriteLoad.getSprite(j);
            j++;
        }
        fly= new Sprite[2];
        fly[0]=SpriteLoad.getSprite(19);
        fly[1]=SpriteLoad.getSprite(20);
        x=Gdx.graphics.getWidth()/11f;
    }

    void spawn(){
        x=Gdx.graphics.getWidth()/11f;
        for (Sprite value : anim) {
            value.setPosition(x, y);
        }
        sprite.setPosition(x,y);
        gAnim[0].setPosition(x,y);
        gAnim[1].setPosition(x,y);
        fly[0].setPosition(x,y);
        fly[1].setPosition(x,y);
        Y= (int) sprite.getY();
        speedFly=0;
        inFly=false;
        onWorm=false;
    }


    float resize(float height) {
        float h = Gdx.graphics.getHeight()/5f;
        float w = ((h/sprite.getHeight())*sprite.getWidth());
        for(int i=0;i<anim.length;i++){
            anim[i].setSize(w,h);
        }
        sprite.setSize(w,h);
        h=Gdx.graphics.getHeight()/6.5f;
        w = (h/gAnim[0].getHeight())*gAnim[0].getWidth();
        gAnim[0].setSize(w,h);
        gAnim[1].setSize(w,h);
        w = anim[0].getWidth();
        h = (w/fly[0].getWidth()*fly[0].getHeight());
        fly[0].setSize(w,h);
        fly[1].setSize(w,h);
        y=height;
        spawn();
        return gAnim[0].getHeight();
    }

    void update(float delta, Rectangle rect){
        timeAnim+=delta;
        if(!inFly&&!onWorm) {
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                inGopnik = true;
                if(timeAnim>=0.05f){
                    gSkinNum=!gSkinNum;
                    timeAnim=0;
                }
            } else {
                inGopnik = false;
            }
        }else if(onWorm){
            checkGround(rect);
        }
        if (!inGopnik&&!inFly&&(Gdx.input.isKeyJustPressed(Input.Keys.UP)||Gdx.input.isKeyJustPressed(Input.Keys.W))){
            inFly=true;
            speedFly=Gdx.graphics.getHeight()/37f;
        }
        if(inFly){
            speedFly-=delta*40;
            Y+=speedFly;
            checkGround(rect);
        }
        if(timeAnim>=0.07f){
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

    Color getColor(){
        return Color.GREEN;
    }

    private void checkGround(Rectangle rect) {
        if(Y<y){
            Y= (int) y;
            inFly=false;
        }else if(rect!=null){
            if(getReckt().y>rect.y&&getReckt().y<rect.y+rect.height*2) {
                if (getReckt().x + getReckt().width > rect.x && rect.x + rect.width > getReckt().x) {
                    if (getReckt().y > rect.y && getReckt().y < rect.y + rect.height) {
                        Y = (int) (rect.y + rect.height);
                        onWorm=true;
                        speedFly=0;
                    }else{
                        onWorm=false;
                    }
                }else{
                    onWorm=false;
                }
            }else{
                onWorm=false;
            }
        }else{
            onWorm=false;
        }
    }

    void draw(SpriteBatch batch){
        if(inGopnik) {
            if(gSkinNum) {
                gAnim[0].draw(batch);
            }else{
                gAnim[1].draw(batch);
            }
        }else if(inFly){
            if(onWorm){
                anim[skinNum].draw(batch);
            }else {
                if (speedFly > 0) {
                    fly[0].draw(batch);
                } else {
                    fly[1].draw(batch);
                }
            }
        }else {
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

    void checkCol(Rectangle enemy, Frame frame) {
        if(enemy.overlaps(getReckt())){
            frame.dead();
        }

    }

    public float getH() {
        return gAnim[0].getHeight();
    }
    float getY(){
        return gAnim[0].getY();
    }

    void checkMoney(MoneyManager money) {
        for(int i=0;i<money.getRect().length;i++){
            if(money.getRect()[i]==null){
                continue;
            }
            if(getReckt().overlaps(money.getRect()[i])){
                money.delete(i);
                this.money++;
                System.out.println(this.money);
                new Data().saveMoney(this.money);
            }
        }
    }
}
