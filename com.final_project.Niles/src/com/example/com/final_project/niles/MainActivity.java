package com.example.com.final_project.niles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {
	
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		// URL to the JSON data
        String strUrl = "https://spreadsheets.google.com/feeds/list/0AjImsUF0T00udEU4NWZhVkdnY294REVuMzRPUGxoQ1E/od6/public/values?alt=json";
        // Creating a new non-ui thread task to download json data
        DownloadTask downloadTask = new DownloadTask();
        // Starting the download process
        downloadTask.execute(strUrl);
        
		 // storing string resources into Array
        //final String[] posts = getResources().getStringArray(R.array.posts);
         
        // Binding resources Array to ListAdapter
        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, posts));
        
        //lv = getListView();
        // Getting a reference to ListView of activity_main
        lv = (ListView) findViewById(R.id.lv_posts);
        
        // listening to single list item on click
        
        
        /************
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
               
              // selected item 
              String post = posts[position];
               
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("posts", post);
              startActivity(i);
             
          }
        });*********/
        
	}
	
	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        try{
            URL url = new URL(strUrl);
 
            // Creating an http connection to communicate with url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
 
            // Connecting to url
            urlConnection.connect();
 
            // Reading data from url
            iStream = urlConnection.getInputStream();
 
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
 
            StringBuffer sb  = new StringBuffer();
 
            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }
 
            data = sb.toString();
 
            br.close();
 
        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
        }
 
        return data;
    }
 
    /** AsyncTask to download json data */
    private class DownloadTask extends AsyncTask<String, Integer, String>{
        String data = null;
        @Override
        protected String doInBackground(String... url) {
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            Log.i("SLN", data);
            return data;
        }
 
        @Override
        protected void onPostExecute(String result) {
 
            // The parsing of the xml data is done in a non-ui thread
            ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();
 
            // Start parsing xml data
            listViewLoaderTask.execute(result);
        }
    }
    
    /** AsyncTask to parse json data and load ListView */
    private class ListViewLoaderTask extends AsyncTask<String, Void, SimpleAdapter>{
 
        JSONObject jObject;
        // Doing the parsing of xml data in a non-ui thread
        @Override
        protected SimpleAdapter doInBackground(String... strJson) {
            try{
                jObject = new JSONObject(strJson[0]);
                PostJSONparser postJSONparser = new PostJSONparser();
                postJSONparser.parse(jObject);
            }catch(Exception e){
                Log.d("JSON Exception1",e.toString());
            }
 
            // Instantiating JSON parser class
            PostJSONparser postJSONparser = new PostJSONparser();
 
            // A list object to store the parsed countries list
            List<HashMap<String, Object>> postsList = null;
 
            try{
                // Getting the parsed data as a List construct
                postsList = postJSONparser.parse(jObject);
            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
 
            // Keys used in HashMap
            String[] from = { "timestamp","location","text"};
 
            // IDs of views in listview_layout
            int[] to = { R.id.tv_timestamp,R.id.tv_location,R.id.tv_text};
 
            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), postsList, R.layout.lv_layout, from, to);
 
            return adapter;
        }
 
        /** Invoked by the Android on "doInBackground" is executed */
        @Override
        protected void onPostExecute(SimpleAdapter adapter) {
 
            // Setting adapter for the listview
            lv.setAdapter(adapter);
 
            /*
            for(int i=0;i<adapter.getCount();i++){
                HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem(i);
                String imgUrl = (String) hm.get("flag_path");
                ImageLoaderTask imageLoaderTask = new ImageLoaderTask();
 
                HashMap<String, Object> hmDownload = new HashMap<String, Object>();
                hm.put("flag_path",imgUrl);
                hm.put("position", i);
 
                // Starting ImageLoaderTask to download and populate image in the listview
                imageLoaderTask.execute(hm);
            }*/
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
