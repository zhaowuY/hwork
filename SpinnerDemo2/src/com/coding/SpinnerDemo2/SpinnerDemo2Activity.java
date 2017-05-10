package com.coding.SpinnerDemo2;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerDemo2Activity extends Activity{
	private static final String[] countriesStr = { "广州", "从化", "武汉", "汕头" };
	private TextView myTextView;
	private EditText myEditText;
	private Button myButton_add;
	private Button myButton_remove;
	private Spinner mySpinner;
	private ArrayAdapter adapter;
	private List allCountries;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_main);allCountries = new ArrayList();
		for (int i = 0; i < countriesStr.length; i++)
		{
			allCountries.add(countriesStr[i]);
		}
		/* new ArrayAdapter物件并将allCountries传入 */
		adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,allCountries);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		/* 以findViewById()取得对象 */
		myTextView = (TextView) findViewById(R.id.myTextView);
		myEditText = (EditText) findViewById(R.id.myEditText);
		myButton_add = (Button) findViewById(R.id.myButton_add);
		myButton_remove = (Button) findViewById(R.id.myButton_remove);
		mySpinner = (Spinner) findViewById(R.id.mySpinner);

		/* 将ArrayAdapter加入Spinner对象中 */
		mySpinner.setAdapter(adapter);
		/* 将myButton_add加入OnClickListener */
		myButton_add.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				String newCountry = myEditText.getText().toString();
				/* 先比对新增的值是否已存在，不存在才可新增 */
				for (int i = 0; i < adapter.getCount(); i++)
				{
					if (newCountry.equals(adapter.getItem(i)))
					{
						return;
					}
				}
				if (!newCountry.equals(""))/* 如果不为空 */
				{
					/* 将值新增至adapter */adapter.add(newCountry);
					/* 取得新增的值的位置 */
					int position = adapter.getPosition(newCountry);
					/* 将Spinner选取在新增的值的位置 */
					mySpinner.setSelection(position);
					/* 将myEditText清空，感觉从输入框的结果飞入spinner中 */
					myEditText.setText("");
				}
			}
		});
		/* 将myButton_remove加入OnClickListener */
		myButton_remove.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View arg0)
			{
				if (mySpinner.getSelectedItem() != null)
				{
					/* 移除mySpinner的值 */
					adapter.remove(mySpinner.getSelectedItem().toString());
					/* 将myEditText清空 */
					myEditText.setText("");
					if (adapter.getCount() == 0)
					{
						/* 将myTextView清空 */
						myTextView.setText("");
					}
				}
			}
		});
		/* 将mySpinner加入OnItemSelectedListener */
		mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView arg0, View arg1,int arg2, long arg3)
			{
				/* 将所选mySpinner的值带入myTextView中 */
				myTextView.setText(arg0.getSelectedItem().toString());
			}
			@Override
			public void onNothingSelected(AdapterView arg0)
			{
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spinner_demo2, menu);
		return true;
	}

}
