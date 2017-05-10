package com.coding.autocompletetextviewdemo;


import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AutoCompleteTextViewDemoActivity extends Activity {
	private static final String[] autoStr = new String[] { "Italy","IT","item","its","itself"}; 
    /** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 /* ����main.xml Layout */ 
	    setContentView(R.layout.main); 
	    /* new ArrayAdapter���󲢽�autoStr�ַ������鴫�� */
	    ArrayAdapter adapter = 
	      new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, autoStr);
	    /* ��findViewById()ȡ��AutoCompleteTextView���� */ 
	    AutoCompleteTextView myAutoCompleteTextView = 
	      (AutoCompleteTextView) findViewById(R.id.myAutoCompleteTextView);
	    /* ��ArrayAdapter����AutoCompleteTextView������ */
	    myAutoCompleteTextView.setAdapter(adapter); 
	    
	    
	    /** 
         * ��MultiAutoCompleteTextView��ʵ�� 
         * �̳���AutoCompleteTextView���ӳ�AutoCompleteTextView�ĳ��� 
         * ����AutoCompleteTextView���������MultiAutoCompleteTextView�������������һֱ�����µ�ѡȡֵ�� 
         * ��д��ʽҲ������ͬ���ڽ���setAdapter֮����Ҫ����setTokenizer(),�������ִ���  
         */ 
        MultiAutoCompleteTextView myMultiAutoCompleteTextView = (MultiAutoCompleteTextView)findViewById(R.id.myMultiAutoCompleteTextView); 
        myMultiAutoCompleteTextView.setAdapter(adapter); 
        myMultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); 
         
	}


}
