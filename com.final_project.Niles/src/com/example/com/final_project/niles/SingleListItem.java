package com.example.com.final_project.niles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
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
	    
	    Intent i = getIntent();
	    String location = i.getStringExtra("location");
	    Log.i("SLN", "Now Loc=" + location);
	    
	    String coordinates[] = {"32.841912", "-96.784996"};
	    
	    if (location.equals("Dallas Hall")) {
	        coordinates[0] = "32.845085";
	        coordinates[1] = "-96.784857";

	    }
	    else if (location.equals("Meadows")) {
	        coordinates[0] = "32.841168";
	        coordinates[1] = "-96.785383";

	    }
	    else if (location.equals("Peyton")) {
	        coordinates[0] = "32.84179";
	        coordinates[1] = "-96.786482";

	    }
	    else if ((location.equals("Outside")) || (location.equals("Bishop Boulevard"))) {
	        coordinates[0] = "32.841912";
	        coordinates[1] = "-96.784996";

	    }
	    else if (location.equals("VS")) {
	        coordinates[0] = "32.842611";
	        coordinates[1] = "-96.786182";

	    }
	    else if (location.equals("Daniel House")) {
	        coordinates[0] = "32.846766";
	        coordinates[1] = "-96.782185";

	    }
	    else if (location.equals("Fondren Library")) {
	       
	        coordinates[0] = "32.84444";
	        coordinates[1] = "-96.783612";

	    }
	    else if ((location.equals("Mac's Place")) || (location.equals("MoMac"))) {
	      
	        coordinates[0] = "32.839663";
	        coordinates[1] = "-96.784342";

	    }
	    else if ((location.equals("Lyle")) || (location.equals("Caruth")) || (location.equals("Embrey"))) {
	    
	        coordinates[0] = "32.842516";
	        coordinates[1] = "-96.782427";

	    }
	    else if (location.equals("Cox")) {

	        coordinates[0] = "32.842209";
	        coordinates[1] = "-96.784154";

	    }
	    else if (location.equals("Umphrey-Lee")) {
	        coordinates[0] = "32.843323";
	        coordinates[1] = "-96.785844";

	    }
	    else if (location.equals("Dedman Life Science")) {
	        coordinates[0] = "32.845995";
	        coordinates[1] = "-96.783725";

	    }
	    else if (location.equals("Bridwell")) {
	        coordinates[0] = "32.839884";
	        coordinates[1] = "-96.785715";

	    }
	    
	    MapController mc;
	    GeoPoint p;
	    
	    mc = mapView.getController();
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
 
        p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
 
        mc.animateTo(p);
        mc.setZoom(17); 
        mapView.invalidate();


	}
}
