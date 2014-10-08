package com.example.androidchess;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ReplayList extends Activity {

	
	
	MoveArray thearray;
	
	static ArrayList<MoveArray> masterarray = new ArrayList<MoveArray>();
	static int selected;
	int i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_replay_list);
		ListView gamelist = (ListView) findViewById(R.id.list);
		load();

		gamelist.setAdapter(new ArrayAdapter<MoveArray>(this, android.R.layout.simple_list_item_1, masterarray) );
		
		  gamelist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener()
		   {
		      public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3) 
		      {
		    	  selected = position;
		    	  Intent myIntent = new Intent(v.getContext(), ReplayActivity.class);
	              startActivityForResult(myIntent, 2);; 
		          
		      }
		   });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.replay_list, menu);
		return true;
	}
	
	public void load()
	{
		MoveArray newobject;
		
		try{
		FileInputStream fis = openFileInput("movelist.dat");
		ObjectInputStream is = new ObjectInputStream(fis);
		
		while ((newobject = (MoveArray) is.readObject()) != null) {
            masterarray.add(newobject);
        }
		
		//is.close();
		}
		catch (IOException | ClassNotFoundException e){}

		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */


}
