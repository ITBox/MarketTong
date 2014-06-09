package com.itbox.markettong.util;

import com.itbox.markettong.bean.ContactsBean;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ContactLoader {
	public final static String[] PROJECTION = new String[] { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_ID, "sort_key" };
	private static final Uri DATA_URI = ContactsContract.Data.CONTENT_URI;
	private static final String[] DATA_PROJECT = new String[] { ContactsContract.Data.MIMETYPE, ContactsContract.Data.DATA1, ContactsContract.Data.DATA4 };
	private static final String DATA_SELECTION = ContactsContract.Data.CONTACT_ID + "=?";

	private static final String phoneType = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;// 手机号
	private static final String emailType = ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;// EMail
	private static final String nameType = ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE;// 姓名
	private static final String organizationType = ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE;// 组织

	private static final String addressType = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE;// 地址
	private static final String webSiteType = ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE;// 网站
	private static final String imType = ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE;// 即时通信
	private static final String niceNameType = ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE;// 称呼
	private static final String eventType = ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE;// 生日
	private static final String relationType = ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE;// 关系

	public static ContactsBean queryContactInfo(Context ctx, ContactsBean bean) {
		Cursor dataCursor = ctx.getContentResolver().query(DATA_URI, DATA_PROJECT, DATA_SELECTION, new String[] { bean.getId() + "" }, null);
		// 遍历游标
		while (dataCursor.moveToNext()) {
			String mimetype = dataCursor.getString(0);
			String data1 = dataCursor.getString(1);
			if (phoneType.equals(mimetype)) {
				bean.addPhone(data1);
			} else if (emailType.equals(mimetype)) {
				// 邮箱
				bean.setEmail(data1);
			} else if (nameType.equals(mimetype)) {
				// 姓名
				bean.setName(data1);
			} else if (organizationType.equals(mimetype)) {
				// 组织信息
				String data4 = dataCursor.getString(2);
				bean.setCompanyName(data1);
				bean.setComPosition(data4);
			} else if (addressType.equals(mimetype)) {
				// 地址
				bean.setAddress(data1);
			}
		}
		dataCursor.close();
		return bean;
	}
}
