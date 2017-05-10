package com.andy.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HelloGridView extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTitle("网格视图");
		GridView gv = (GridView) findViewById(R.id.grid_view);
		gv.setAdapter(new ImageAdapter(this));

	}

	public class ImageAdapter extends BaseAdapter {
		// 定义Context
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		// 图片的引用 定义整型数组 即图片源
		private Integer[] mThumbIds = { R.drawable.grid_view_01,
				R.drawable.grid_view_02, R.drawable.grid_view_03,
				R.drawable.grid_view_04, R.drawable.grid_view_05,
				R.drawable.grid_view_06, R.drawable.grid_view_07,
				R.drawable.grid_view_08, R.drawable.grid_view_09,
				R.drawable.grid_view_10, R.drawable.grid_view_11,
				R.drawable.grid_view_12, R.drawable.grid_view_13,
				R.drawable.grid_view_14, R.drawable.grid_view_15,
				R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3,
				R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6,
				R.drawable.sample_7 };

		// 获取图片的个数
		public int getCount() {
			return mThumbIds.length;
		}

		// 获取图片在库中的位置
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		// 获取图片ID
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); 
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(mThumbIds[position]);
			return imageView;
		}

	}
}