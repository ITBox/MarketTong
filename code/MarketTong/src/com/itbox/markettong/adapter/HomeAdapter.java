package com.itbox.markettong.adapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itbox.markettong.R;
import com.itbox.markettong.quickaction.ActionItem;
import com.itbox.markettong.quickaction.QuickAction;
import com.itbox.markettong.quickaction.QuickAction.OnActionItemClickListener;
import com.itbox.markettong.util.Common;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {
	String[] itemColors = { "#f2a400", "#e00707", "#4ac925", "#00d5f2", "#4EC0A5" };
	private Context ctx;
	private QuickAction quickAction;
	private OnItemMoreClickListener listener;
	private int clickPosition = 0;
	public HomeAdapter(Context context, OnItemMoreClickListener listener) {
		this.ctx = context;
		this.listener = listener;
		ActionItem itemCollect = new ActionItem(Common.ID_COLLECT, "收藏");
		ActionItem itemToCotact = new ActionItem(Common.ID_TOCONTACT, "添加到通讯录");
		ActionItem itemDel = new ActionItem(Common.ID_COLLECT, "删除");
		quickAction = new QuickAction(context, QuickAction.VERTICAL);
		quickAction.addActionItem(itemCollect);
		quickAction.addActionItem(itemToCotact);
		quickAction.addActionItem(itemDel);
		quickAction.setOnActionItemClickListener(actionItemClickListener);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = View.inflate(ctx, R.layout.item_list_home, null);
			viewHolder = new ViewHolder(convertView);
			viewHolder.mUserMore.setOnClickListener(moreListener);
			convertView.setTag(viewHolder);
			viewHolder.mUserMore.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.pos = position;
		viewHolder.ivLeft.setBackgroundColor(Color.parseColor(itemColors[position % 5]));
		viewHolder.ivTop.setBackgroundColor(Color.parseColor(itemColors[position % 5]));
		return convertView;
	}

	private OnClickListener moreListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ViewHolder tag = (ViewHolder) v.getTag();
			clickPosition = tag.pos;
			quickAction.show(v);
		}
	};
	
    private OnActionItemClickListener actionItemClickListener = new OnActionItemClickListener() {
		
		@Override
		public void onItemClick(QuickAction source, int pos, int actionId) {
			// TODO Auto-generated method stub
			if (listener != null) {
				listener.onItemClick(clickPosition, actionId);
			}
		}
	};
	class ViewHolder {
		@InjectView(R.id.iv_left)
		ImageView ivLeft;
		@InjectView(R.id.iv_top)
		ImageView ivTop;
		@InjectView(R.id.item_user_name)
		TextView mUserName;
		@InjectView(R.id.item_user_num)
		TextView mUserNum;
		@InjectView(R.id.item_user_company)
		TextView mUserCompany;
		@InjectView(R.id.item_more)
		TextView mUserMore;
        int pos;
        
		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
	
	public interface OnItemMoreClickListener {
		void onItemClick(int pos, int actionId);
	}
}
