/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.example.administrator.yoursecret;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.example.administrator.yoursecret.Write.WriteImagesAdapter;
import com.example.administrator.yoursecret.utils.AppContants;
import com.example.administrator.yoursecret.utils.GlideImageLoader;
import com.github.chrisbanes.photoview.PhotoView;

public class ViewPagerActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
		ViewPager viewPager = (HackyViewPager) findViewById(R.id.view_pager);



		viewPager.setAdapter(new SamplePagerAdapter());
        int startPos = getIntent().getIntExtra(AppContants.POSITION,0);
        viewPager.setCurrentItem(startPos);
	}

	static class SamplePagerAdapter extends PagerAdapter {

//		private static final int[] sUris = { R.drawable.sample,R.drawable.sample};

		@Override
		public int getCount() {
			return WriteImagesAdapter.getInstance().getmDatas().size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
            GlideImageLoader.loadImage(container.getContext(),WriteImagesAdapter.getInstance().getmDatas().get(position),photoView);

			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

//	public static void setImage(PhotoView photoView,Object object){
//        if(object instanceof Integer){
//            photoView.setImageResource((Integer)object);
//            return;
//        }
//        if(object instanceof Uri){
//            photoView.setImageURI((Uri)object);
//            return;
//        }
//        if(object instanceof Bitmap){
//            photoView.setImageBitmap((Bitmap)object);
//            return;
//        }
//    }
}