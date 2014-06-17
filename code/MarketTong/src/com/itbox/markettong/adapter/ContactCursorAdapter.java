package com.itbox.markettong.adapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itbox.markettong.R;
import com.itbox.markettong.widget.CircleImageView;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactCursorAdapter extends CursorAdapter {
	
	private Context ctx;
	
	public ContactCursorAdapter(Context context, Cursor c) {
		super(context, c, true);
		ctx = context;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = View.inflate(ctx, R.layout.item_list_contact, null);
		new ContactHolder(view);
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ContactHolder contactHolder = (ContactHolder) view.getTag();
		

	}
	
	static class ContactHolder {
        @InjectView(R.id.contact_userPhoto)
        CircleImageView mContactPhoto;
        @InjectView(R.id.contact_userName)
        TextView mContactUserName;
        @InjectView(R.id.contact_userCompany)
        TextView mContactUserCompany;
        
		public ContactHolder(View view) {
			ButterKnife.inject(this, view);
			view.setTag(this);
		}
		
	}
}
