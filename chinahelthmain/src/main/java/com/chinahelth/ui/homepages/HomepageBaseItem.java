package com.chinahelth.ui.homepages;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chinahelth.HealthConfig;
import com.chinahelth.R;
import com.chinahelth.support.bean.HomepageItemBean;
import com.chinahelth.support.utils.TimeUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caihanyuan on 15-7-20.
 */
public abstract class HomepageBaseItem implements ImageLoadingListener{

    protected Context mContext;

    protected View mItemRoot;

    protected TextView mTitile;

    protected TextView mFromText;

    protected TextView mCommentText;

    protected TextView mPublishTimeText;

    protected HomepageItemBean mHomepageItemData;

    protected DisplayImageOptions mDisplayImageOptions;

    public HomepageBaseItem(Context context) {
        mContext = context;
        mDisplayImageOptions = HealthConfig.getDefaultDisplayImageOptions();
        initView();
    }

    abstract void initView();

    private void setTitleText(CharSequence title) {
        if (mTitile != null) {
            mTitile.setText(title);
        }
    }

    protected void setTitleRead(boolean isReaded){
        if(isReaded){

        }else{

        }
    }

    private void setFromText(CharSequence from) {
        if (mFromText != null) {
            mFromText.setText(from);
        }
    }

    private void setCommentNumText(int nums) {
        String comment = nums + mContext.getResources().getString(R.string.health_comment);
        setCommentNumText(comment);
    }

    private void setCommentNumText(CharSequence commentNums) {
        if (mCommentText != null) {
            mCommentText.setText(commentNums);
        }
    }

    private void setPublishTimeText(long time){
        String timeText = null;
        SimpleDateFormat simpleDateFormat = null;
        long pastTime = System.currentTimeMillis() - time;
        if(pastTime < TimeUtility.ONE_HOUR){
            if(mPublishTimeText != null){
                mPublishTimeText.setVisibility(View.GONE);
            }
        }else if(pastTime <= TimeUtility.ONE_DAY){
            simpleDateFormat = new SimpleDateFormat("HH");
            String hour = simpleDateFormat.format(new Date(pastTime));
            timeText = hour + mContext.getResources().getString(R.string.homepage_item_hour_ago);
            setPublishTimeText(timeText);
        }else{
            simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            timeText = simpleDateFormat.format(new Date(time));
            setPublishTimeText(timeText);
        }
    }

    private void setPublishTimeText(CharSequence time) {
        if (mPublishTimeText != null) {
            mPublishTimeText.setVisibility(View.VISIBLE);
            mPublishTimeText.setText(time);
        }
    }

    protected void setHomepageItemData(HomepageItemBean homepageItemBean) {
        mHomepageItemData = homepageItemBean;
        setTitleText(mHomepageItemData.title);
        setFromText(mHomepageItemData.from);
        setCommentNumText(mHomepageItemData.commentNums);
        setPublishTimeText(mHomepageItemData.publisTime);
        setTitleRead(mHomepageItemData.isReaded);
    }

    public HomepageItemBean getHomepageItemData() {
        return mHomepageItemData;
    }

    public View getItem(){
        return mItemRoot;
    }

    @Override
    public void onLoadingStarted(String imageUri, View view) {
    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        Log.e(ImageLoader.TAG, "image uri:" + imageUri + "load failed", failReason.getCause());
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {

    }
}
