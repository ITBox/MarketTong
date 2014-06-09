package com.itbox.markettong.fragment;

import com.itbox.markettong.R;
import com.itbox.markettong.adapter.HomeAdapter;
import com.itbox.markettong.adapter.HomeAdapter.OnItemMoreClickListener;
import com.itbox.markettong.parallaxscroll.ParallaxListView;
import com.itbox.markettong.util.Common;
import com.itbox.markettong.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class HomeFragment extends BaseFragment implements OnItemClickListener, OnItemMoreClickListener{
	@InjectView(R.id.list_view)
	ParallaxListView mHomeListView;
	private HomeAdapter homeAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.fragment_home, null);
		ButterKnife.inject(this, layout);
		homeAdapter = new HomeAdapter(mActThis, HomeFragment.this);
		mHomeListView.setAdapter(homeAdapter);
		mHomeListView.setOnItemClickListener(this);
		return layout;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(int pos, int actionId) {
		// TODO Auto-generated method stub
		switch (actionId) {
		case Common.ID_COLLECT:
			ToastUtil.showMsg(mActThis, "收藏");
			break;
		case Common.ID_TOCONTACT:
			ToastUtil.showMsg(mActThis, "添加到通讯录");
			break;
		case Common.ID_DELETE:
			ToastUtil.showMsg(mActThis, "删除");
			break;
		default:
			break;
		}
	}

}
