package com.andy.android;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HelloSpinner extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		find_and_modify_view();
		setTitle("SpinnerActivity");
	}

	// 方法1
	private static final String[] mCountries = { "China", "Russia", "Germany",
			"Ukraine", "Belarus", "USA" };

	private Spinner spinner_2;

	private void find_and_modify_view() {
		Spinner spinner_c = (Spinner) findViewById(R.id.spinnerId);
		ArrayList<String> allcountries = new ArrayList<String>();
		for (int i = 0; i < mCountries.length; i++) {
			allcountries.add(mCountries[i]);
		}
		// 使用范型
		ArrayAdapter<String> aspnCountries = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, allcountries);

		aspnCountries
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_c.setAdapter(aspnCountries);

		spinner_2 = (Spinner) findViewById(R.id.spinner_2);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.countries, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_2.setAdapter(adapter);

	}
}
