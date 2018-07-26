package brain_socket.com.dekaneh.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class SyncScroll extends ScrollView {
    private View anchorView;
    private View syncView;


    private boolean scrollable = true;

    public SyncScroll(Context context) {
        super(context);
    }

    public SyncScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SyncScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setAnchorView(View v) {
        anchorView = v;
        syncViews();
    }


    public void setSynchronizedView(View v) {
        syncView = v;
        syncViews();
    }

    private void syncViews() {
        if (anchorView == null || syncView == null)
            return;

        //the golden line :p
        int distance = anchorView.getTop() - syncView.getTop();
        syncView.offsetTopAndBottom(distance);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        syncViews();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (anchorView == null || syncView == null) {
            return;
        }

        //Distance between the anchor view and the scroll position
        int matchDistance = anchorView.getTop() - getScrollY();
        //Distance between scroll position and sync view
        int offset = getScrollY() - syncView.getTop();
        //Check if anchor is scrolled off screen
        if (matchDistance < 0) {
            syncView.offsetTopAndBottom(offset); // this line keeps the syncView fixed relative to the anchorView
        } else {
            syncViews();
        }
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    public boolean isScrollable() {
        return scrollable;
    }


    public View getAnchorView() {
        return anchorView;
    }

    public View getSyncView() {
        return syncView;
    }


    //to disable scrolling (not working) :/
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // if we can scroll pass the event to the superclass
                if (isScrollable())
                    return super.onTouchEvent(ev);
                // only continue to handle the touch event if scrolling enabled
                return isScrollable(); // mScrollable is always false at this point
            default:
                return super.onTouchEvent(ev);
        }
    }

    //to disable scrolling
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScrollable()) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return scrollable && super.canScrollVertically(direction);
    }

    //to check if it's on top! DUH! -.-
    public boolean isTop()
    {
        int distance = Math.abs(anchorView.getTop() - syncView.getTop());
        return distance == syncView.getHeight();
    }


    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        super.setOnScrollChangeListener(l);


    }
}