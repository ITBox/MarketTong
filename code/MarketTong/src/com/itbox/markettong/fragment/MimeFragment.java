package com.itbox.markettong.fragment;

/**
 *  我的
 */
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.itbox.markettong.MainActivity;
import com.itbox.markettong.R;
import com.itbox.markettong.SettingActivity;
import com.itbox.markettong.residemenu.ResideMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MimeFragment extends BaseFragment implements OnClickListener {
	private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();
	@InjectView(R.id.mime_collect)
	TextView mMimeCollect;
	@InjectView(R.id.mime_del)
	TextView mMimeDel;
	@InjectView(R.id.viewpager)
	ViewPager mVp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_mime, null);
		ButterKnife.inject(this, layout);
		MimeCollectFragment collectFragment = new MimeCollectFragment();
		MimeDelFragment delFragment = new MimeDelFragment();
		fragmentsList.add(collectFragment);
		fragmentsList.add(delFragment);

		MimeAdapter adapter = new MimeAdapter(getFragmentManager());
		mVp.setAdapter(adapter);
		mVp.setCurrentItem(0);
		mVp.setOnPageChangeListener(onPageChangeListener);
		mMimeCollect.setOnClickListener(new MyOnClickListener(0));
		mMimeDel.setOnClickListener(new MyOnClickListener(1));
		setSelect(0);
		return layout;
	}

	private class MyOnClickListener implements OnClickListener {
		private int num;

		public MyOnClickListener(int pos) {
			this.num = pos;
		}

		@Override
		public void onClick(View v) {
			mVp.setCurrentItem(num);
		}
	}

	private void setSelect(int pos) {
		switch (pos) {
		case 0:
			mMimeCollect.setBackgroundResource(R.drawable.rect_left_pressed);
			mMimeDel.setBackgroundResource(R.drawable.rect_right_normal);
			break;
		case 1:
			mMimeCollect.setBackgroundResource(R.drawable.rect_left_normal);
			mMimeDel.setBackgroundResource(R.drawable.rect_right_pressed);
			break;
		default:
			break;
		}
	}

	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			setSelect(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	private class MimeAdapter extends FragmentPagerAdapter {

		public MimeAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentsList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentsList.size();
		}
	}

	@OnClick({ R.id.mime_setting })
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mime_setting:
			startActivity(new Intent(mActThis, SettingActivity.class));
			mActThis.overridePendingTransition(R.anim.open_next, R.anim.close_main);
			break;

		default:
			break;
		}
	}

}
