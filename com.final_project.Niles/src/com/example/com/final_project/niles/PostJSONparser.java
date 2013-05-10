package com.example.com.final_project.niles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** A class to parse json data */
public class PostJSONparser {

	// Receives a JSONObject and returns a list
	public List<HashMap<String,Object>> parse(JSONObject jObject){

		JSONObject jPostObject = null;
		JSONArray jPostArray = null;
		try {
			// Retrieves all the elements in the 'entry' array
jPostObject = jObject.getJSONObject("feed");
jPostArray = jPostObject.getJSONArray("entry");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Invoking getPosts with the array of json object
		// where each json object represent a post
	return getPosts(jPostArray);
	}

	private List<HashMap<String, Object>> getPosts(JSONArray jPostArray){
		int postCount = jPostArray.length();
		List<HashMap<String, Object>> postList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> post = null;

		// Taking each post, parses and adds to list object
		for(int i=postCount; i>0;i--){
			try {
				// Call getPost with post JSON object to parse the post
				post = getPost((JSONObject)jPostArray.get(i));
				postList.add(post);

			} catch (JSONException e) {
				e.printStackTrace();
				}
		}
		
		return postList;
	}

	// Parsing the Country JSON object
	private HashMap<String, Object> getPost(JSONObject jPost){

		HashMap<String, Object> post = new HashMap<String, Object>();
		
		/*String countryName = "";
		String flag="";
		String language = "";
		String capital = "";
		String currencyCode = "";
		String currencyName = ""; */

		String timestamp = "";
		String where = "";
		String text = "";

	try {
			/*
            countryName = jPost.getString("countryname");
            flag = jPost.getString("flag");
            language = jPost.getString("language");
            capital = jPost.getString("capital");
            currencyCode = jPost.getJSONObject("currency").getString("code");
            currencyName = jPost.getJSONObject("currency").getString("currencyname");
			*/
			timestamp = jPost.getJSONObject("title").getString("$t");
			where = jPost.getJSONObject("gsx$whereareyou").getString("$t");
			text = jPost.getJSONObject("gsx$whatsonyourmind").getString("$t");

			/*
            String details =        "Language : " + language + "\n" +
                                "Capital : " + capital + "\n" +
                                "Currency : " + currencyName + "(" + currencyCode + ")";
 
			post.put("country", countryName);
			post.put("flag", R.drawable.blank);
			post.put("flag_path", flag);
			post.put("details", details);
			*/
			post.put("timestamp", timestamp);
			post.put("location", where);
			post.put("text", text);

	} catch (JSONException e) {
		e.printStackTrace();
	}
	return post;
	}
}