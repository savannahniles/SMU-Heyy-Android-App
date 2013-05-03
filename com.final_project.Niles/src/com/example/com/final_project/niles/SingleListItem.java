package com.example.com.final_project.niles;

import com.google.android.gms.maps.MapView;
import com.google.android.maps.MapActivity;

import android.os.Bundle;
 
public class SingleListItem extends MapActivity{
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	}
}
