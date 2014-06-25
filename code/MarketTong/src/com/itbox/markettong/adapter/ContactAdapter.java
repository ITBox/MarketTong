package com.itbox.markettong.adapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itbox.markettong.R;
import com.itbox.markettong.bean.ContactsBean;
import com.itbox.markettong.util.Utils;
import com.itbox.markettong.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter implements SectionIndexer{
	String[] itemColors = { "#f2a400", "#e00707", "#4ac925", "#00d5f2", "#4EC0A5" };
	int [] itemDrawable = {R.drawable.ic_contact_1, R.drawable.ic_contact_2,R.drawable.ic_contact_3,R.drawable.ic_contact_4,R.drawable.ic_contact_5};
	private Context ctx;
	private ArrayList<ContactsBean> list;
	
	public ContactAdapter(Context ctx, ArrayList<ContactsBean> list) {
		this.ctx = ctx;
		this.list = list;
	}
	
    public void setNewList(ArrayList<ContactsBean> list) {
    	this.list = list;
    	notifyDataSetChanged();
    }
    
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		if (position >= 0 && position < list.size()) {
			return list.get(position);
		} else {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactHolder contactHolder = null;
		if (convertView == null) {
			convertView = View.inflate(ctx, R.layout.item_list_contact, null);
			contactHolder = new ContactHolder(convertView);
			convertView.setTag(contactHolder);
		} else {
			contactHolder = (ContactHolder) convertView.getTag();
		}
		ContactsBean bean = (ContactsBean) getItem(position);
		long photoId = bean.getPhotoId();
		if (photoId > 0) {
			contactHolder.mContactPhotoTV.setVisibility(View.GONE);
			Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, bean.getId());
			Picasso.with(ctx).load(uri).placeholder(R.drawable.ic_contact_head).into(contactHolder.mContactPhoto);
		} else {
			Picasso.with(ctx).load(itemDrawable[position%5]).into(contactHolder.mContactPhoto);;
			contactHolder.mContactPhotoTV.setVisibility(View.VISIBLE);
			contactHolder.mContactPhotoTV.setText(bean.getLastName());
		}
		
		Utils.nullGone(contactHolder.mContactUserName, bean.getName());
		Utils.nullGone(contactHolder.mContactUserCompany, bean.getCompanyName());
		return convertView;
	}
	
	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			String letter = list.get(i).getLetter();
			char firstChar = letter.toUpperCase().charAt(0);
			if (firstChar == sectionIndex) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		return 0;
	} 
	
	class ContactHolder {
        @InjectView(R.id.contact_userPhoto)
        CircleImageView mContactPhoto;
        @InjectView(R.id.contact_userPhoto_tv)
        TextView mContactPhotoTV;
        @InjectView(R.id.contact_userName)
        TextView mContactUserName;
        @InjectView(R.id.contact_userCompany)
        TextView mContactUserCompany;
        
		public ContactHolder(View view) {
			ButterKnife.inject(this, view);
		}
		
	}
}
