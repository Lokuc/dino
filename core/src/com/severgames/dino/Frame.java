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
import com.severgames.dino.enemies.EnemyManager;
import com.severgames.lib.Button;
import com.severgames.lib.ClickListener;

public class Frame extends ScreenAdapter {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Dino dino;
    private EnemyManager enemy;
    private boolean active=true;
    private Button over;
    private Button menu;
    private float score=0f;
    private Dj dj = new Dj();
    private BitmapFont font;
    private BackgroundManager manager;
    private ShapeRenderer shapeRenderer;
    private MoneyManager money;


    Frame(BackgroundManager manager){
        this.manager = manager;
        shapeRenderer=new ShapeRenderer();
        money=new MoneyManager();


        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        dino = new Dino();
        dino.spawn();
        enemy= new EnemyManager();
        menu=new Button("texture/UI/menu.png");
        menu.setSizeW(4);
        menu.setPosition(ClickListener.POSITION_HORIZONTAL.LeftBottom, ClickListener.POSITION_VERTICAL.DownBottom);
        menu.addClickListener(new ClickListener() {
            @Override
            public void click(String id) {
                //MyGdxGame.myGdxGame.getScreen().dispose();
                MyGdxGame.myGdxGame.setMenu();
            }
        },camera);
        over= new Button("texture/UI/gameover.png");
        over.setSizeW(4);
        over.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);
        over.addClickListener(new ClickListener() {
            @Override
            public void click(String id) {
                if(over.id(id)){
                    dino.spawn();
                    enemy.spawn();
                    active=true;
                    manager.reSpawn();
                    money.respawn();
                    dj.resumeFon();
                }
            }
        },camera);
        dj.load();


        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
        font.setColor(Color.GREEN);

    }


    @Override
    public void show() {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        dj.playFon();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.updateMatrices();
        batch.setProjectionMatrix(camera.combined);
        dino.spawn();
        enemy.spawn();
        active=true;
        manager.reSpawn();
        money.respawn();
        dj.resumeFon();
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            return;
        }
        camera.update();
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        if(active) {
            update(delta);

            batch.begin();

            manager.drawFon(batch,delta);
            manager.drawBack(batch,delta);
            manager.drawPlane(batch,delta);
            manager.drawLine(batch,delta);
            enemy.draw(batch);
            dino.draw(batch);
            enemy.draw1(batch);
            money.draw(batch);
            manager.drawFilter(batch,delta);
            font.draw(batch,Gdx.graphics.getFramesPerSecond()+" fps",200,500);
            if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                tmp(dino.getReckt(), dino.getColor());
                tmp(enemy.getRect(), enemy.getColor());
                tmp(enemy.getGround(), enemy.getGroundColor());
                for(Rectangle re:money.getRect()){
                    tmp(re,money.getColor());
                }
                shapeRenderer.end();
            }

            batch.end();
        }else {
            batch.begin();
            over.draw(batch);
            menu.draw(batch);
            batch.end();
        }

    }

    private void tmp(Rectangle r,Color c){
        if(r!=null) {
            shapeRenderer.setColor(c);
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
        }
    }


    private void update(float delta){
        manager.updTime(delta);
        score+=delta*3;

        money.update(delta);
        enemy.update(delta);
        dino.update(delta,enemy.getGround());
        dino.checkMoney(money);
        if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            dino.checkCol(enemy.getRect(), this);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.updateMatrices();
        camera.update();
        float tmp = manager.resize();
        money.resize(tmp);

        over.setSizeW(4);
        over.setPosition(ClickListener.POSITION_HORIZONTAL.Center, ClickListener.POSITION_VERTICAL.Center);

        float tm = dino.resize(tmp);
        enemy.resize(tmp,tm);

    }



    void dead() {
        active=false;
        dj.pause();
        dj.playEnd();
    }
}
