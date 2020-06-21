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
    private MyGdxGame myGdxGame;
    private int temp;

    loadScreen(MyGdxGame myGdxGame) {
        this.myGdxGame=myGdxGame;
        fon = new Sprite(new Texture("texture/UI/fon.png"));
        loading = new Sprite(new Texture("texture/UI/loading.png"));
        float w = Gdx.graphics.getWidth();
        float h = (w/fon.getWidth()*fon.getHeight());
        fon.setSize(w,h);
    }


    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(true){
            myGdxGame.getScreen().dispose();
            myGdxGame.setScreen(new Menu(myGdxGame));
        }
        batch.end();
    }

    @Override
    public void show() {
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch=new SpriteBatch();
    }

}
