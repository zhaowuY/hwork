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
	private static final String[] countriesStr = { "����", "�ӻ�", "�人", "��ͷ" };
	private TextView myTextView;
	private EditText myEditText;
	private Button myButton_add;
	private Button myButton_remove;
	private Spinner mySpinner;
	private ArrayAdapter adapter;
	private List allCountries;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		/* ����main.xml Layout */
		setContentView(R.layout.activity_main);allCountries = new ArrayList();
		for (int i = 0; i < countriesStr.length; i++)
		{
			allCountries.add(countriesStr[i]);
		}
		/* new ArrayAdapter�������allCountries���� */
		adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,allCountries);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		/* ��findViewById()ȡ�ö��� */
		myTextView = (TextView) findViewById(R.id.myTextView);
		myEditText = (EditText) findViewById(R.id.myEditText);
		myButton_add = (Button) findViewById(R.id.myButton_add);
		myButton_remove = (Button) findViewById(R.id.myButton_remove);
		mySpinner = (Spinner) findViewById(R.id.mySpinner);

		/* ��ArrayAdapter����Spinner������ */
		mySpinner.setAdapter(adapter);
		/* ��myButton_add����OnClickListener */
		myButton_add.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				String newCountry = myEditText.getText().toString();
				/* �ȱȶ�������ֵ�Ƿ��Ѵ��ڣ������ڲſ����� */
				for (int i = 0; i < adapter.getCount(); i++)
				{
					if (newCountry.equals(adapter.getItem(i)))
					{
						return;
					}
				}
				if (!newCountry.equals(""))/* �����Ϊ�� */
				{
					/* ��ֵ������adapter */adapter.add(newCountry);
					/* ȡ��������ֵ��λ�� */
					int position = adapter.getPosition(newCountry);
					/* ��Spinnerѡȡ��������ֵ��λ�� */
					mySpinner.setSelection(position);
					/* ��myEditText��գ��о��������Ľ������spinner�� */
					myEditText.setText("");
				}
			}
		});
		/* ��myButton_remove����OnClickListener */
		myButton_remove.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View arg0)
			{
				if (mySpinner.getSelectedItem() != null)
				{
					/* �Ƴ�mySpinner��ֵ */
					adapter.remove(mySpinner.getSelectedItem().toString());
					/* ��myEditText��� */
					myEditText.setText("");
					if (adapter.getCount() == 0)
					{
						/* ��myTextView��� */
						myTextView.setText("");
					}
				}
			}
		});
		/* ��mySpinner����OnItemSelectedListener */
		mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView arg0, View arg1,int arg2, long arg3)
			{
				/* ����ѡmySpinner��ֵ����myTextView�� */
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
