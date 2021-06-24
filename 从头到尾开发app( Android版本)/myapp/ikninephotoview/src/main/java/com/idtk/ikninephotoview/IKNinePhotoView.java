package com.idtk.ikninephotoview;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Idtk on 2017/3/8.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 * des : 九宫格View
 */

public class IKNinePhotoView extends FrameLayout implements Observer{

    private OnImageClickListener mListener ;
    private IKNinePhotoViewAdapter adapter;
    private int border = 5;
    private int childSize;
    private ArrayList<IKNinePhotoViewHolder> mRecyclerList = new ArrayList<>();

    public interface OnImageClickListener {
        void onImageClicked(int position, View view, int id) ;
    }

    public void setOnImageClickListener(OnImageClickListener listener) {
        mListener = listener ;
    }

    public IKNinePhotoView(@NonNull Context context) {
        super(context);
    }

    public IKNinePhotoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IKNinePhotoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IKNinePhotoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        generateDefaultLayoutParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ninePhotoMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        childLayout(left, top, right, bottom);
    }

    private void ninePhotoCreateView(){
        removeAllViews();
        for (int i = 0; i < adapter.getItemCount(); i++) {
//            removeView(getChildAt(i));
            addView(generateViewHolder(i).getItemView(),generateDefaultLayoutParams());
        }
    }

    private void ninePhotoMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec)-getPaddingLeft()-getPaddingRight();
        int height;
	
	if (adapter == null) return;

        //if (adapter.getItemCount() < 0 || adapter.getItemCount() > 9) {
        //    throw new IllegalStateException("itemCount may not be more than 9 or less than 0");
       // }

        if (adapter.getItemCount() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }

        if (adapter.getItemCount() > 1) {
            childSize = (width - border * 2) / 4;
            height = (int) (childSize * (int) Math.ceil(adapter.getItemCount() / 4.0) + border * (int) Math.ceil(adapter.getItemCount() / 4.0 - 1));
            /*
            if (adapter.getItemCount() == 4 || adapter.getItemCount() == 2) {
                int currentWidth = childSize*3 + border*2;
                setMeasuredDimension(currentWidth + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
            }else {
            */
                int currentWidth = childSize*4 + border*3;
                setMeasuredDimension(currentWidth + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
            //}
        } else {
            childSize = width/4;
            height = width/4;
            setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
        }

        /*
        if (adapter.getItemCount() > 1) {
            childSize = (width - border * 2) / 3;
            height = (int) (childSize * (int) Math.ceil(adapter.getItemCount() / 3.0) + border * (int) Math.ceil(adapter.getItemCount() / 3.0 - 1));
            if (adapter.getItemCount() == 4 || adapter.getItemCount() == 2) {
                int currentWidth = childSize*2 + border;
                setMeasuredDimension(currentWidth + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
            }else {
                int currentWidth = childSize*3 + border*2;
                setMeasuredDimension(currentWidth + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
            }
        } else {
            childSize = width/3;
            height = width/3;
            setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
        }

         */
		ninePhotoCreateView();
    }

    private void childLayout(int left, int top, int right, int bottom) {
	    
	if (adapter == null){
	    return;
    }

        //if (adapter.getItemCount() < 0 || adapter.getItemCount() > 9) {
        //    throw new IllegalStateException("itemCount may not be more than 9 or less than 0");
        //}

        int count = adapter.getItemCount();
        int colNum = 4;
        //if (count == 4){
        //    colNum = 2;
        //}

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);

            if (childView == null){
                return;
            }

            if (adapter != null && mRecyclerList.get(i) != null &&!mRecyclerList.get(i).getFlag()) {
                adapter.displayView(generateViewHolder(i), i, childSize);
                mRecyclerList.get(i).setFlag(true);
            }

            int rows = i / colNum;
            int cols = i % colNum;

            int childLeft = getPaddingLeft() + (childSize + border) * (cols);
            int childTop = getPaddingTop() + (childSize + border) * (rows);
            int childRight = childLeft + childSize;
            int childBottom = childTop + childSize;
            childView.layout(childLeft, childTop, childRight, childBottom);

            //childView.findViewById(R.id.nine_pic).layout(childLeft, childTop, childRight, childBottom);

            final int position = i ;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onImageClicked(position, v, v.getId());
                    }
                }
            });
        }
    }



    public void setAdapter(IKNinePhotoViewAdapter adapter){
        this.adapter = adapter;
        adapter.addObserver(this);
//        mRecyclerList.clear();
        for(IKNinePhotoViewHolder holder: mRecyclerList){
            holder.setFlag(false);
        }
    }

    public void setBorder(int border){
        this.border = border;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof IKNinePhotoViewAdapter){
            this.adapter = (IKNinePhotoViewAdapter) o;
            adapter.addObserver(this);
//            mRecyclerList.clear();
            for(IKNinePhotoViewHolder holder: mRecyclerList){
                holder.setFlag(false);
            }
            requestLayout();
            invalidate();
        }
    }

    private IKNinePhotoViewHolder generateViewHolder(int position){
        if (position < mRecyclerList.size()) {
            return mRecyclerList.get(position);
        } else {
            if (adapter != null){
                IKNinePhotoViewHolder holder = adapter.createView(IKNinePhotoView.this);
                if (holder == null){
                    return null;
                }

                mRecyclerList.add(holder);
                return holder;
            } else
                return null;
        }
    }

    public void clearRecycler(){
        mRecyclerList.clear();
    }
}
