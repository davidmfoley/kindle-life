package com.substantial.life;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private WorldView worldView;
	private World world;
	private Timer timer;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);	

        world = new World();
        
        world.toggle(11,11);
        world.toggle(12,11);
        world.toggle(13,11);
                
        
        worldView = (WorldView) findViewById(R.id.worldView);
    	worldView.setWorld(world);
    	
    	world.setOnChangeHandler(new Runnable(){
			public void run() {
				MainActivity.this.runOnUiThread(new Runnable(){
					public void run() {
						MainActivity.this.worldView.invalidate();
					}});
			}});
    	
    	timer = new Timer();
    	TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainActivity.this.world.evolve();
				
			}};
    	timer.scheduleAtFixedRate(task, 500, 500);
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
