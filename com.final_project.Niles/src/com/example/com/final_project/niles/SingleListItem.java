package com.example.com.final_project.niles;

import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
 
public class SingleListItem extends MapActivity{
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.single_list_item_view);
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    Log.i("SLN", "On Create of Activity 2");
	    Log.i("SLN", getIntent().getExtras().getString("posts"));
	}
}
