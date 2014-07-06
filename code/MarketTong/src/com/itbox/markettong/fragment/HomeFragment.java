package com.itbox.markettong.fragment;

import wei.mark.standout.StandOutWindow;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.itbox.markettong.MainActivity;
import com.itbox.markettong.R;
import com.itbox.markettong.adapter.HomeAdapter;
import com.itbox.markettong.adapter.HomeAdapter.OnItemMoreClickListener;
import com.itbox.markettong.dialog.OperateDialog;
import com.itbox.markettong.doblist.DobList;
import com.itbox.markettong.doblist.NoListViewException;
import com.itbox.markettong.doblist.OnLoadMoreListener;
import com.itbox.markettong.jazzylistview.JazzyListView;
import com.itbox.markettong.residemenu.ResideMenu;
import com.itbox.markettong.systembartint.SystemBarTintManager;
import com.itbox.markettong.util.Common;
import com.itbox.markettong.util.DimenUtil;
import com.itbox.markettong.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeFragment extends BaseFragment implements LoaderCallbacks<Cursor>, OnItemClickListener, OnItemMoreClickListener {
	@InjectView(R.id.list_view)
	JazzyListView mHomeListView;
	@InjectView(R.id.add)
	TextView mAdd;
	@InjectView(R.id.home_title)
	RelativeLayout mHomeTitle;
	private HomeAdapter homeAdapter;
	private DobList dobList;
	private View mPopView;
	private PopupWindow pw;
	private int homeTitleHigh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(mActThis);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setNavigationBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.bg_master);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_home, null);
		ButterKnife.inject(this, layout);
		// initDobList(mHomeListView);
		homeAdapter = new HomeAdapter(mActThis, HomeFragment.this);
		mHomeListView.setAdapter(homeAdapter);
		mHomeListView.setOnItemClickListener(this);
		ResideMenu resideMenu = MainActivity.getResideMenu();
		resideMenu.setSwipeDirectionEnable(ResideMenu.DIRECTION_LEFT);

		mPopView = LayoutInflater.from(mActThis).inflate(R.layout.fragment_add_pop, null);
		pw = new PopupWindow(mPopView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		new PopView(mPopView);
		return layout;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		MainActivity.getResideMenu().setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
	}

	public class PopView {
		@InjectView(R.id.add_sd)
		ImageView mAddSd;

		public PopView(View view) {
			ButterKnife.inject(this, view);
			// TODO Auto-generated constructor stub
		}

		@OnClick(R.id.add_sd)
		public void AddSd() {
			Intent target = FileUtils.createGetContentIntent();
			// Create the chooser Intent
			Intent intent = Intent.createChooser(target, getString(R.string.chooser_title));
			try {
				startActivityForResult(intent, Common.ADD_SD_REQUEST);
				pw.dismiss();
			} catch (ActivityNotFoundException e) {
				// The reason for the existence of aFileChooser
			}
		}
	}

	private void initDobList(ListView listview) {
		dobList = new DobList();
		try {
			dobList.register(listview);
			dobList.addDefaultLoadingFooterView();
			dobList.setOnLoadMoreListener(new OnLoadMoreListener() {

				@Override
				public void onLoadMore(int totalItemCount) {
					// TODO Auto-generated method stub
					addData(10);
				}
			});
		} catch (NoListViewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addData(20);
	}

	private void addData(int i) {
		// TODO Auto-generated method stub

	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = mActThis.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	@OnClick(R.id.add)
	public void add() {
		// StandOutWindow.closeAll(mActThis, OperateDialog.class);
		// StandOutWindow.show(mActThis, OperateDialog.class,
		// StandOutWindow.DEFAULT_ID);
		showPop();
	}

	private void showPop() {
		// TODO Auto-generated method stub
//		ViewTreeObserver treeObserver = mHomeTitle.getViewTreeObserver();
//		treeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//
//			@SuppressWarnings("deprecation")
//			@Override
//			public void onGlobalLayout() {
//				mHomeTitle.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//				homeTitleHigh = mHomeTitle.getHeight();
//			}
//		});
		// pw.setBackgroundDrawable(new
		// ColorDrawable(Color.parseColor("#66000000")));
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.showAsDropDown(mAdd, 0, homeTitleHigh + 12);
		pw.setAnimationStyle(R.style.popwin_anim_style);
		pw.setFocusable(true);
		pw.setOutsideTouchable(true);
		pw.update();
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

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("static-access")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == mActThis.RESULT_OK) {
			switch (requestCode) {
			case Common.ADD_SD_REQUEST:
				if (data != null) {
					final Uri uri = data.getData();
					try {
						final String path = FileUtils.getPath(mActThis, uri);
						ToastUtil.showMsg(mActThis, "File Selected: " + path);
					} catch (Exception e) {
					}
				}
				break;

			default:
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
