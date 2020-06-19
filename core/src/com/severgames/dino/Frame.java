package com.severgames.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

import java.awt.Shape;

public class Frame extends ScreenAdapter {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Dino dino;
    private Kaktus[] kaktus;
    boolean active=true;
    private Button over;
    private float score=0f;
    Dj dj = new Dj();
    private BitmapFont font;
    private BackgroundManager meneger;
    private ShapeRenderer shapeRenderer;


    @Override
    public void show() {

        shapeRenderer=new ShapeRenderer();


        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,MyGdxGame.W,MyGdxGame.H);
        dino = new Dino();
        dino.spawn();
        float w = MyGdxGame.W;
        float h;
        h = MyGdxGame.H;
        kaktus = new Kaktus[2];
        kaktus[0]=new Kaktus();
        kaktus[1]=new Kaktus();
        kaktus[0].setXC(true);
        //kaktus[1].setXC(false);
        over= new Button("textrure/gameover.png");
        over.setSizeW(4);
        over.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
        over.addClickListener(new ClickListener() {
            @Override
            public void click(String id) {
                if(over.id(id)){
                    dino.spawn();
                    kaktus[0].spawn();
                    active=true;
                    meneger.reSpawn();
                    dj.playRun();
                    dj.playFon();
                }
            }
        },camera);
        dj.load();
        dj.playFon();
        dj.playRun();

        font = new BitmapFont();
        font.setColor(Color.GREEN);

    }

    @Override
    public void render(float delta) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(active) {
            update(delta);

            batch.begin();

            meneger.drawFon(batch,delta);
            meneger.drawBack(batch,delta);
            meneger.drawPlane(batch,delta);
            meneger.drawLine(batch,delta);
            dino.draw(batch);
            kaktus[0].draw(batch);
            //kaktus[1].draw(batch);
            meneger.drawFilter(batch,delta);
            //7
            font.draw(batch,Gdx.graphics.getFramesPerSecond()+" fps",200,500);
            if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                tmp(dino.getReckt());
                tmp(kaktus[0].getRect());
                shapeRenderer.end();
            }

            batch.end();
        }else {
            batch.begin();
            over.draw(batch);
            batch.end();
        }

    }

    private void tmp(Rectangle r){
        shapeRenderer.rect(r.x,r.y,r.width,r.height);
    }


    private void update(float delta){
        meneger.updTime(delta);
        score+=delta*3;

        dino.update(delta);
        kaktus[0].update(delta);
        //kaktus[1].update(delta);
        if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            dino.checkCol(kaktus[0], this);
        }
        //dino.checkCol(kaktus[1],this);
    }

    @Override
    public void resize(int width, int height) {
        MyGdxGame.H=height;
        MyGdxGame.W=width;
        camera.setToOrtho(false,width,height);

        float tmp = meneger.resize();

        over.setSizeW(4);
        over.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);

        dino.resize(tmp);
        kaktus[0].resize(tmp,dino.getH(),dino.getY());




    }

    Frame(BackgroundManager meneger){
        this.meneger = meneger;
    }


    void dead() {
        active=false;
        dj.pause();
        dj.playEnd();
    }
}
