package com.example.administrator.yoursecret;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.example.administrator.yoursecret.Write.BitmapUtil;
import com.example.administrator.yoursecret.Write.WriteImagesAdapter;
import com.example.administrator.yoursecret.utils.AppContants;
import com.example.administrator.yoursecret.utils.BaseRecyclerAdapter;
import com.example.administrator.yoursecret.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {
    private Activity activity;

    private RecyclerView recyclerView;

    private static int nextBitmapIndex = 0;

    public final static int TAKE_PHOTO_REQUEST = 0;


    private WriteImagesAdapter adapter;

    private Uri curImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        activity = this;

        recyclerView = (RecyclerView) findViewById(R.id.photos);

        //to change to fit the mvp architecture


        adapter = WriteImagesAdapter.getInstance();
        adapter.setContext(this);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Intent intent = new Intent(activity, ViewPagerActivity.class);
                intent.putExtra(AppContants.POSITION,position);
                activity.startActivity(intent);
            }
        });
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_write_image,null);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                curImageUri= Uri.fromFile(BitmapUtil.getTempImage(nextBitmapIndex++));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,curImageUri);
                startActivityForResult(intent,TAKE_PHOTO_REQUEST);
            }
        });
        adapter.setFooterView(footer);
        adapter.setScaleFooterView(false);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10,10,10,10));
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO_REQUEST:
                if(resultCode==RESULT_OK && null!=curImageUri){
                    adapter.addData(curImageUri);
                }
        }
    }
}