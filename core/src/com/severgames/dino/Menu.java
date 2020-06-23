package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

public class Menu extends ScreenAdapter implements ClickListener {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Button start;
    private Button settings;
    private BackgroundManager background;


    Menu(){
        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        start= new Button("texture/UI/start.png");
        start.setSizeW(3);
        start.addClickListener(this,camera);
        start.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
        settings=new Button("texture/UI/settings.png");
        settings.setSizeH(5);
        settings.setPosition(POSITION_HORIZONTAL.RightBottom,POSITION_VERTICAL.UpBottom);
        settings.addClickListener(this,camera);
    }

    @Override
    public void show() {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        start.setSizeW(3);
        batch.setProjectionMatrix(camera.combined);
        start.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0.1f,0.1f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        start.draw(batch);
        settings.draw(batch);

        batch.end();


    }


    @Override
    public void click(String id) {
        if(start.id(id)){
            //MyGdxGame.myGdxGame.getScreen().dispose();
            MyGdxGame.myGdxGame.setFrame();
        }
        if(settings.id(id)){

        }
    }
}
