package com.severgames.dino;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteLoad  {

    private static Sprite[] sprites;
    private static  String[] path = new String[]{
            "texture/enemy/0.png",
            "texture/enemy/3.png",
            "texture/enemy/fonEnemy.png",
            "texture/enemy/fonEnemy2.png",  //3
            "texture/fon/back.png",
            "texture/fon/filter.png",
            "texture/fon/fon.png",
            "texture/fon/line.png",
            "texture/fon/plane.png",
            "texture/fon/statuya.png",      //9
            "texture/person/animation/anim0.png",
            "texture/person/animation/anim1.png",
            "texture/person/animation/anim2.png",
            "texture/person/animation/anim3.png",
            "texture/person/animation/anim4.png",
            "texture/person/animation/anim5.png",
            "texture/person/animation/anim6.png",
            "texture/person/animGopnik/gopnik0.png", //17
            "texture/person/animGopnik/gopnik1.png",
            "texture/person/fly/down.png",
            "texture/person/fly/up.png",             //20
            "texture/money/KEY0.png",
            "texture/money/KEY1.png",
            "texture/money/KEY2.png",
            "texture/money/KEY3.png",
            "texture/money/KEY4.png",
            "texture/money/KEY5.png",            //26
            "texture/money/hide/hide0.png",
            "texture/money/hide/hide1.png",
            "texture/money/hide/hide2.png",
            "texture/money/hide/hide3.png",
            "texture/money/hide/hide4.png",
            "texture/money/hide/hide5.png",
            "texture/money/hide/hide6.png",      //33
            "texture/enemy/2.png"

    };
    private loadScreen ld;
    private int iter=0;

    public SpriteLoad(loadScreen loadScreen){
        ld = loadScreen;
        sprites=new Sprite[path.length];
    }

    public static Sprite getSprite(int i){
        return sprites[i];
    }

    public SpriteLoad(){

    }

    public void load(){
        sprites=new Sprite[path.length];
        for(int i=0;i<path.length;i++){
            sprites[i]=new Sprite(new Texture(path[i]));
            ld.count();
        }
    }

    public boolean getLoad(){
        return iter==path.length;
    }

    public void loadi(){
        sprites[iter]=new Sprite(new Texture(path[iter]));
        sprites[iter].getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ld.count();
        iter++;
    }

    float getCount() {
        return path.length;
    }
}
