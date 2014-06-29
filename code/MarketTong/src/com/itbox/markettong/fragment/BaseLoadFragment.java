package com.itbox.markettong.fragment;

import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.content.ContentProvider;
import com.activeandroid.query.Delete;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;

public class BaseLoadFragment<T extends Model> extends BaseFragment implements LoaderCallbacks<Cursor> {
	private CursorAdapter mAdapter;
	private Class<T> mClazz;
	private String mOrderBy;
	private String mSelection;

	public void initLoader(CursorAdapter adapter, Class<T> classz) {
		initLoad(adapter, classz, null, null);
	}

	public void initLoad(CursorAdapter adapter, Class<T> clazz, String selection, String orderBy) {
		mAdapter = adapter;
		mClazz = clazz;
		mSelection = selection;
		mOrderBy = orderBy;
		mActThis.getSupportLoaderManager().initLoader(getLoaderId(), null, this);
	}

	protected int getLoaderId() {
		return 0;
	}

	protected void saveData(int page, List<T> list) {
		ActiveAndroid.beginTransaction();
		try {
			new Delete().from(mClazz).where(mSelection).execute();
			if (list != null) {
				for (T er : list) {
					er.save();
				}
			}
			ActiveAndroid.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ActiveAndroid.endTransaction();
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return new CursorLoader(mActThis, ContentProvider.createUri(mClazz, null), null, mSelection, null, mOrderBy);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		if (arg1 != null) {
			mAdapter.swapCursor(arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		mAdapter.swapCursor(null);
	}

}
