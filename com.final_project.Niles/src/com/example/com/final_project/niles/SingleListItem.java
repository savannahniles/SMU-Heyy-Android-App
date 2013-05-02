package com.example.com.final_project.niles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
 
public class SingleListItem extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);
         
        TextView txtPost = (TextView) findViewById(R.id.single_post_label);
         
        Intent i = getIntent();
        // getting attached intent data
        String post = i.getStringExtra("post");
        // displaying selected product name
        txtPost.setText(post);
         
    }
}
