package com.itbox.markettong.widget;

import com.itbox.markettong.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SideBar extends View {  
	    private char[] l;  
	    private SectionIndexer sectionIndexter = null;  
	    private ListView list;  
	    private TextView mDialogText;
		private int singleHeight;
		
	    public SideBar(Context context) {  
	        super(context);  
	        init();  
	    }  
	    public SideBar(Context context, AttributeSet attrs) {  
	        super(context, attrs);  
	        init();  
	    }  
	    public SideBar(Context context, AttributeSet attrs, int defStyle) {  
	    	super(context, attrs, defStyle); 
	    	init();  
	    }  
	    
	    private void init() {  
	        l = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',  
	                'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };  
	    }  
	    public void setListView(ListView _list) { 
	        list = _list;  
	        sectionIndexter = (SectionIndexer) _list.getAdapter();  
//	        HeaderViewListAdapter ha = (HeaderViewListAdapter) _list.getAdapter();
//	    	ContactAdapter ad = (ContactAdapter)ha.getWrappedAdapter();
//			sectionIndexter = (SectionIndexer)ad;
	    }  
	    public void setTextView(TextView mDialogText) {  
	    	this.mDialogText = mDialogText;  
	    }  
	    public boolean onTouchEvent(MotionEvent event) {  
	        super.onTouchEvent(event);  
	        int i = (int) event.getY(); 
//	        Log.i("youzh", "i-------------"+i);
	        int idx = i / singleHeight;
//	        Log.i("youzh", "idx-------------"+idx);
	        if (idx >= l.length) {  
	            idx = l.length - 1;  
	        } else if (idx < 0) {  
	            idx = 0;  
	        }  
	        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {  
	        	mDialogText.setVisibility(View.VISIBLE);
	        	mDialogText.setText(""+l[idx]);
	            if (sectionIndexter == null) {  
	                sectionIndexter = (SectionIndexer) list.getAdapter();  
	            } 
	        	if (sectionIndexter != null){
	        		
	        		int position = sectionIndexter.getPositionForSection(l[idx]);  
//	            Log.i("youzh", "position-------------"+position);
	        		if (position == -1) {  
	        			return true;  
	        		}  
	        		list.setSelection(position);
	        		setBackgroundResource(R.drawable.sidebar_background);
	        	}
	        }else{
	        	setBackgroundResource(android.R.color.transparent);
	        	mDialogText.setVisibility(View.INVISIBLE);
	        }  
	        return true;  
	    }  
	    @SuppressLint("DrawAllocation")
		protected void onDraw(Canvas canvas) {  
	    	int height = getHeight();
//	    	Log.i("youzh", height+"");
	    	singleHeight = height / l.length;
//	    	Log.i("youzh", "singleHeight-------------"+singleHeight);
	        Paint paint = new Paint();  
	        paint.setColor(0xff595c61);  
	        paint.setTextSize(25);  
	        paint.setTextAlign(Paint.Align.CENTER);  
	        float widthCenter = getMeasuredWidth() / 2;  
	        for (int i = 0; i < l.length; i++) {  
	            canvas.drawText(String.valueOf(l[i]), widthCenter, singleHeight + (i * singleHeight), paint);  
	        }  
	        super.onDraw(canvas);  
	    }  
}
