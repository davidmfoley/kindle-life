package com.substantial.life;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private WorldView worldView;
	private World world;
	private Timer timer;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);	

        world = new World();
        
        worldView = (WorldView) findViewById(R.id.worldView);
    	worldView.setWorld(world);
    	
    	world.setOnChangeHandler(new Runnable(){
			public void run() {
				MainActivity.this.refreshWorldView();
			}});
    	
    	
    	bindButton(R.id.stopStart, new OnClickListener(){
			public void onClick(View arg0) {
				if (timer != null) {
					timer.cancel();
					timer = null;
				}
				else {
					startTimer();
				}
			}});
    	
    	bindButton(R.id.zoomAllTheWayOut, new OnClickListener(){
			public void onClick(View arg0) {
				MainActivity.this.worldView.zoomToFitAll();
				MainActivity.this.refreshWorldView();
			}});

    	bindButton(R.id.zoomIn, new OnClickListener(){
			public void onClick(View arg0) {
				MainActivity.this.worldView.zoomIn();
				MainActivity.this.refreshWorldView();
			}});
    	
    	bindButton(R.id.zoomOut, new OnClickListener(){
			public void onClick(View arg0) {
				MainActivity.this.worldView.zoomOut();
				MainActivity.this.refreshWorldView();
			}});
    }
	
	protected void refreshWorldView() {
		runOnUiThread(new Runnable(){
			public void run() {
				MainActivity.this.worldView.invalidate();
			}});
	}
	
	private void startTimer() {
		timer = new Timer();
    	TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainActivity.this.world.evolve();
				
			}};
    	timer.scheduleAtFixedRate(task, 500, 500);
	}

    private void bindButton(int id, OnClickListener listener) {
    	View view = findViewById(id);
    	view.setOnClickListener(listener);
	}

	protected void nextFrame() {
    	
    	
    	
		// TODO Auto-generated method stub
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
