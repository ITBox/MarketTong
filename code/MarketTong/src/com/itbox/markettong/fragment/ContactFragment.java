package com.itbox.markettong.fragment;

import java.util.ArrayList;

import com.itbox.markettong.R;
import com.itbox.markettong.SearchActivity;
import com.itbox.markettong.adapter.ContactAdapter;
import com.itbox.markettong.bean.ContactsBean;
import com.itbox.markettong.util.ContactLoader;
import com.itbox.markettong.widget.SideBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ContactFragment extends BaseFragment implements OnItemClickListener{
    @InjectView(R.id.search_ll)
    LinearLayout mSearchLL;
    @InjectView(R.id.contact_listview)
    ListView mLLContact;
    @InjectView(R.id.sideBar)
    SideBar indexBar;
	private TextView mDialogText;
    private MyAsyncQueryHandler queryHandler;
    private ArrayList<ContactsBean> contactList = new ArrayList<ContactsBean>();
	private ContactAdapter contactAdapter;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.fragment_contact, null);
		ButterKnife.inject(this, layout);
		contactAdapter = new ContactAdapter(mActThis, contactList);
		mLLContact.setAdapter(contactAdapter);
		mLLContact.setOnItemClickListener(this);
		queryHandler = new MyAsyncQueryHandler(mActThis.getContentResolver());
		
		WindowManager mWindowManager = (WindowManager) mActThis.getSystemService(Context.WINDOW_SERVICE);
		indexBar.setListView(mLLContact);
		mDialogText = (TextView) LayoutInflater.from(mActThis).inflate(R.layout.list_position, null);
		mDialogText.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
		mWindowManager.addView(mDialogText, lp);
		indexBar.setTextView(mDialogText);
		return layout;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		contactList.clear();
		queryHandler.startQuery(0, null, ContactsContract.Contacts.CONTENT_URI, ContactLoader.PROJECTION, null, null, "sort_key_alt");
	}
	
	@OnClick(R.id.search_ll)
	public void clickSearch() {
		startActivity(new Intent(mActThis, SearchActivity.class));
	}
	
	@SuppressLint("HandlerLeak")
	private class MyAsyncQueryHandler extends AsyncQueryHandler {

		public MyAsyncQueryHandler(ContentResolver cr) {
			super(cr);
		}

		@Override
		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
			ContactsBean bean = null;
//			String tmp = "";
			while (cursor.moveToNext()) {
				bean = new ContactsBean();
				bean.setId(cursor.getLong(0));
				// bean.setContactId(cursor.getInt(1));
				bean.setName(cursor.getString(1));
				bean.setPhotoId(cursor.getLong(2));
				bean.setLetter(cursor.getString(3));
				bean.setLastName(cursor.getString(1).substring(cursor.getString(1).length() - 1, cursor.getString(1).length()));
//				bean.setType(ContactsBean.ITEM);
//				if (!bean.getLetter().equals(tmp)) {
//					ContactsBean section = new ContactsBean();
//					section.setType(ContactsBean.SECTION);
//					section.setName(bean.getName());
//					section.setLetter(bean.getLetter());
//					contactList.add(section);
//					tmp = bean.getLetter();
//				}
				contactList.add(bean);
			}
			cursor.close();
			if (contactList.size() > 0) {
				contactAdapter.setNewList(contactList);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}
}
