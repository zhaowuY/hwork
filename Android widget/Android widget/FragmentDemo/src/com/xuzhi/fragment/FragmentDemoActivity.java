package com.xuzhi.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentDemoActivity extends Activity {

	public static String[] array = { "text1,", "text2", "text3", "text4",
			"text5,", "text6", "text7", "text8" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public static class TitlesFragment extends ListFragment {

		boolean mDualPane;
		int mCurCheckPosition = 0;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			System.out.println("Fragment-->onCreate");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			System.out.println("Fragment-->onCreateView");
			return super.onCreateView(inflater, container, savedInstanceState);
		}

		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			System.out.println("Fragment-->onPause");
		}

		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			super.onStop();

			System.out.println("Fragment-->onStop");
		}

		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			System.out.println("Fragment-->onAttach");
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			System.out.println("Fragment-->onStart");
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			System.out.println("Fragment-->onResume");
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			System.out.println("Fragment-->onDestroy");
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			System.out.println("Fragment-->onActivityCreted");
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, array));

			View detailsFrame = getActivity().findViewById(R.id.details);

			mDualPane = detailsFrame != null
					&& detailsFrame.getVisibility() == View.VISIBLE;

			if (savedInstanceState != null) {
				// �ӱ����״̬��ȡ������
				mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
			}

			if (mDualPane) {
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

				showDetails(mCurCheckPosition);
			}
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			// TODO Auto-generated method stub
			super.onSaveInstanceState(outState);
			// ���浱ǰ���±�
			outState.putInt("curChoice", mCurCheckPosition);
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);
			showDetails(position);
		}

		void showDetails(int index) {
			mCurCheckPosition = index;
			if (mDualPane) {
				getListView().setItemChecked(index, true);
				DetailsFragment details = (DetailsFragment) getFragmentManager()
						.findFragmentById(R.id.details);
				if (details == null || details.getShownIndex() != index) {
					details = DetailsFragment.newInstance(mCurCheckPosition);

					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();

					ft.replace(R.id.details, details);
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);// ���ö���Ч��
					ft.commit();// �ύ
				}
			} else {
				new AlertDialog.Builder(getActivity())
						.setTitle(android.R.string.dialog_alert_title)
						.setMessage(array[index])
						.setPositiveButton(android.R.string.ok, null).show();
			}
		}
	}

	/**
	 * ��Ϊ�����һ���֣�Ϊfragment �ṩһ��layout
	 * 
	 * @author terry
	 * 
	 */
	public static class DetailsFragment extends Fragment {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
		}

		public static DetailsFragment newInstance(int index) {
			DetailsFragment details = new DetailsFragment();
			Bundle args = new Bundle();
			args.putInt("index", index);
			details.setArguments(args);
			return details;
		}

		public int getShownIndex() {
			return getArguments().getInt("index", 0);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			if (container == null)
				return null;

			ScrollView scroller = new ScrollView(getActivity());
			TextView text = new TextView(getActivity());

			int padding = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
							.getResources().getDisplayMetrics());
			text.setPadding(padding, padding, padding, padding);
			scroller.addView(text);

			text.setText(array[getShownIndex()]);
			return scroller;
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			// TODO Auto-generated method stub
			super.onCreateOptionsMenu(menu, inflater);
			menu.add("Menu 1a")
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			menu.add("Menu 1b")
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			Toast.makeText(
					getActivity(),
					"index is" + getShownIndex() + " && menu text is "
							+ item.getTitle(), 1000).show();
			return super.onOptionsItemSelected(item);
		}
	}
}