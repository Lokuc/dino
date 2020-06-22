package com.severgames.dino;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteLoad  {

    private static Sprite[] sprites;
    private static  String[] path = new String[]{
            "texture/enemy/0.png",
            "texture/enemy/3.png",
            "texture/enemy/fonEnemy.png",
            "texture/enemy/fonEnemy2.png",
            "texture/fon/back.png",
            "texture/fon/filter.png",
            "texture/fon/fon.png",
            "texture/fon/line.png",
            "texture/fon/plane.png",
            "texture/fon/statuya.png",
            "texture/person/animation/anim0.png",
            "texture/person/animation/anim1.png",
            "texture/person/animation/anim2.png",
            "texture/person/animation/anim3.png",
            "texture/person/animation/anim4.png",
            "texture/person/animation/anim5.png",
            "texture/person/animation/anim6.png",
            "texture/person/animGopnik/gopnik0.png",
            "texture/person/animGopnik/gopnik1.png",
            "texture/person/fly/down.png",
            "texture/person/fly/up.png"

    };
    private loadScreen ld;
    private int iter=0;

    public SpriteLoad(loadScreen loadScreen){
        ld = loadScreen;
        sprites=new Sprite[path.length];
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
        ld.count();
        iter++;
    }

    float getCount() {
        return path.length;
    }
}
