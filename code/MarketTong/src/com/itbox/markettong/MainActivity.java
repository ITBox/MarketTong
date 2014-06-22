package com.itbox.markettong;

import com.itbox.markettong.fragment.ContactFragment;
import com.itbox.markettong.fragment.HomeFragment;
import com.itbox.markettong.fragment.MimeFragment;
import com.itbox.markettong.residemenu.ResideMenu;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class MainActivity extends BaseActivity {
	@InjectView(android.R.id.tabhost)
	protected FragmentTabHost mTabHost;
	// 定义一个布局
	private LayoutInflater layoutInflater;

	// 定义数组来存放Fragment界面
	@SuppressWarnings("rawtypes")
	private Class fragmentArray[] = { HomeFragment.class, ContactFragment.class, MimeFragment.class };
	// 定义数组来存放按钮图片
	private int mImageViewArray[] = { R.drawable.selector_tabbar_home, R.drawable.selector_tabbar_contact, R.drawable.selector_tabbar_mime };

	// Tab选项卡的文字
	private String mTextviewArray[] = { "首页", "通讯录", "我的" };

	private static ResideMenu resideMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		ButterKnife.inject(this);
		initViews();
		setUpMenu();
	}

	private void setUpMenu() {
		// TODO Auto-generated method stub
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
		resideMenu.setScaleValue(0.6f);
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
//		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
		}

		@Override
		public void closeMenu() {
		}
	};

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	public static ResideMenu getResideMenu() {
		return resideMenu;
	}

	/**
	 * 初始化组件
	 */
	private void initViews() {
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
		}
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		return view;
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

}
