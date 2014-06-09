package com.itbox.markettong.adapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itbox.markettong.R;
import com.itbox.markettong.bean.ContactsBean;
import com.itbox.markettong.util.Utils;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {
    
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
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactHolder contactHolder = null;
		ContactsBean bean = (ContactsBean) getItem(position);
		if (convertView == null) {
			convertView = View.inflate(ctx, R.layout.item_list_contact, null);
			contactHolder = new ContactHolder(convertView);
			convertView.setTag(contactHolder);
		} else {
			contactHolder = (ContactHolder) convertView.getTag();
		}
		long photoId = bean.getPhotoId();
		if (photoId == 0) {
			contactHolder.mContactPhoto.setImageResource(R.drawable.contacts_none_localcontacts_icon);
		} else {
			contactHolder.mContactPhoto.setImageContact(bean.getId());
		}
		
		Utils.nullGone(contactHolder.mContactUserName, bean.getName());
		Utils.nullGone(contactHolder.mContactUserCompany, bean.getCompanyName());
		return convertView;
	}
	
	class ContactHolder {
        @InjectView(R.id.contact_userPhoto)
        SmartImageView mContactPhoto;
        @InjectView(R.id.contact_userName)
        TextView mContactUserName;
        @InjectView(R.id.contact_userCompany)
        TextView mContactUserCompany;
        
		public ContactHolder(View view) {
			ButterKnife.inject(this, view);
		}
		
	} 
}
