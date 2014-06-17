package com.itbox.markettong.dialog;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itbox.markettong.R;
import com.itbox.markettong.bean.ContactsBean;
import com.itbox.markettong.util.IntentUtil;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DialogPhones extends DialogFragment {
	private ContactsBean contactsBean;
	
	public static DialogPhones newIntance() {
		DialogPhones dialog = new DialogPhones();
		return dialog;
	}

	public void setBean(ContactsBean bean) {
		contactsBean = bean;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Dialog dialog = new Dialog(getActivity(), R.style.dialog);
		View view = View.inflate(getActivity(), R.layout.dialog_call, null);
		dialog.setContentView(view);
		PhonesHolder phonesHolder = new PhonesHolder(view);
		phonesHolder.phoneName.setText(contactsBean.getName());
		final PhonesAdapter adapter = new PhonesAdapter(getActivity(), contactsBean.getPhones());
		phonesHolder.phoneListView.setAdapter(adapter);
		phonesHolder.phoneListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String phoneNum = (String) adapter.getItem(position);
				IntentUtil.callPhone(getActivity(), phoneNum);
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	private class PhonesAdapter extends BaseAdapter {
		private Context ctx;
		private ArrayList<String> phones;

		public PhonesAdapter(Context context, ArrayList<String> phones) {
			this.ctx = context;
			this.phones = phones;
		}

		@Override
		public int getCount() {
			return phones.size();
		}

		@Override
		public Object getItem(int position) {
			return phones.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			PhonesItem item = null;
			if (convertView == null) {
				convertView = View.inflate(ctx, R.layout.dialog_call_item, null);
				item = new PhonesItem(convertView);
				convertView.setTag(item);
			} else {
				item = (PhonesItem) convertView.getTag();
			}
			item.phoneNum.setText((String) getItem(position));
			return convertView;
		}

	}

	class PhonesHolder {
		@InjectView(R.id.dialog_phone_name)
		TextView phoneName;
		@InjectView(R.id.dialog_phone_lv)
		ListView phoneListView;

		public PhonesHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	class PhonesItem {
		@InjectView(R.id.item_dialog_phone)
		TextView phoneNum;

		public PhonesItem(View view) {
			ButterKnife.inject(this, view);
		}
	}

}
