package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class loadScreen extends ScreenAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite fon;
    private Sprite loading;
    private Sprite load;
    private int temp;
    private float count;
    private SpriteLoad sl;

    loadScreen() {
        fon = new Sprite(new Texture("texture/UI/fon.png"));
        load = new Sprite(new Texture("texture/UI/load.png"));
        loading = new Sprite(new Texture("texture/UI/loading.png"));
        fon.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        float h = Gdx.graphics.getHeight()/3;
        float w = h/loading.getHeight()*loading.getWidth();
        loading.setSize(w,h);
        load.setSize(w,h);
        h=Gdx.graphics.getHeight()/2f-h/2f;
        w=Gdx.graphics.getWidth()/2f-w/2f;
        loading.setPosition(w,h);
        load.setPosition(w,h);
        loading.setSize(0,loading.getHeight());
        sl = new SpriteLoad(this);
        count=load.getWidth()/sl.getCount();
    }


    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!sl.getLoad()){
            sl.loadi();
        }else{
            BackgroundManager bg = new BackgroundManager();
            bg.load();
            MyGdxGame.frame =new Frame(bg);
            new Data();
            MyGdxGame.myGdxGame.getScreen().dispose();
            MyGdxGame.myGdxGame.setMenu();
        }
        batch.begin();
        fon.draw(batch);
        loading.draw(batch);
        load.draw(batch);
        batch.end();
    }

    @Override
    public void show() {
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch=new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
    }

    void count() {
        loading.setSize(loading.getWidth()+count,loading.getHeight());
    }
}
