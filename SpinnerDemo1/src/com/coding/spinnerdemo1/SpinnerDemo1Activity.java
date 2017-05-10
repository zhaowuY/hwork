package com.coding.spinnerdemo1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerDemo1Activity extends Activity
{
	private static final String[] countriesStr ={ "广州", "从化", "武汉", "汕头" };
	private TextView myTextView;
	private Spinner mySpinner;
	private ArrayAdapter adapter;
	Animation myAnimation;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myTextView = (TextView) findViewById(R.id.myTextView);
		mySpinner = (Spinner) findViewById(R.id.mySpinner);
		adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
				countriesStr);
		/* myspinner_dropdown为自定义下拉菜单样式定义在res/layout目录下 */
		adapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		/* 将ArrayAdapter加入Spinner对象中 */mySpinner.setAdapter(adapter);

		/* 将mySpinner加入OnItemSelectedListener */
		mySpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
				{
					@Override
					public void onItemSelected(AdapterView arg0, View arg1,
							int arg2, long arg3)
					{
						/* 将所选mySpinner的值带入myTextView中 */
						myTextView.setText("选择的是" + countriesStr[arg2]);
						/* 将mySpinner显示 */
						arg0.setVisibility(View.VISIBLE);
					}

					@Override
					public void onNothingSelected(AdapterView arg0)
					{
						// TODO Auto-generated method stub
					}
				});
		/* 取得Animation定义在res/anim目录下 */
		myAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim);
		/* 将mySpinner加入OnTouchListener */
		mySpinner.setOnTouchListener(new Spinner.OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				/* 将mySpinner执行Animation */
				v.startAnimation(myAnimation);
				/* 将mySpinner隐藏 */
				v.setVisibility(View.INVISIBLE);
				return false;
			}
		});
		mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus)
			{
				// TODO Auto-generated method stub
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spinner_demo1, menu);
		return true;
	}
}
