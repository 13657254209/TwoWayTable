package com.pan.twowaytable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView topRecyclerView, leftRecyclerView, contentRecyclerView;
    private RecyclerView.Adapter topAdapter, leftAdapter, contentAdapter;
    private String[] titles = {"完成率\n(%)", "销售额\n(元)", "销售目标\n(亿)", "销售套数\n(套)", "销售面积\n(㎡)", "销售均价\n(元/㎡)", "回款率\n(%)", "逾期签约金额\n(元)", "逾期签约套数\n(套)"};
    private String[] cities = {"太原", "天津", "广州", "浙江", "北京", "上海", "江苏", "海南", "福建", "西安", "香河", "安徽", "哈尔滨", "佛山", "重庆", "惠州", "惠东", "包头", "石家庄", "梅州", "郑州"};
    private List<ContentBean> contentBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        topRecyclerView = (RecyclerView) findViewById(R.id.topRecyclerView);
        LinearLayoutManager topManager = new LinearLayoutManager(this);
        topManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecyclerView.setLayoutManager(topManager);
        topAdapter = new TopAdapter(titles, this);
        topRecyclerView.setAdapter(topAdapter);

        leftRecyclerView = (RecyclerView) findViewById(R.id.leftRecyclerView);
        LinearLayoutManager leftManager = new LinearLayoutManager(this);
        leftManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftAdapter = new LeftAdapter(cities, this);
        leftRecyclerView.setLayoutManager(leftManager);
        leftRecyclerView.setAdapter(leftAdapter);
        final RecyclerView.OnScrollListener leftListener = new SelfRemovingOnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    contentRecyclerView.scrollBy(dx, dy);
                }
            }
        };
        leftRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (contentRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
                    return true;
                }
                Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
                if (!ret) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                final int action;
                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && contentRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.removeOnScrollListener(leftListener);
                    rv.addOnScrollListener(leftListener);
                } else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(leftListener);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        genData();
        contentRecyclerView = (RecyclerView) findViewById(R.id.contentRecyclerView);
        LinearLayoutManager contentManager = new LinearLayoutManager(this);
        contentManager.setOrientation(LinearLayoutManager.VERTICAL);
        contentAdapter = new ContentAdapter(contentBeanList, this);
        contentRecyclerView.setLayoutManager(contentManager);
        contentRecyclerView.setAdapter(contentAdapter);
        final RecyclerView.OnScrollListener contentListener = new SelfRemovingOnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    leftRecyclerView.scrollBy(dx, dy);
                }
            }
        };
        contentRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (leftRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
                    return true;
                }
                Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
                if (!ret) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                final int action;
                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && leftRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.removeOnScrollListener(contentListener);
                    rv.addOnScrollListener(contentListener);
                } else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(contentListener);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void genData() {
        contentBeanList.clear();
        for (int i = 0; i < 21; i++) {
            ContentBean bean = new ContentBean();
            bean.setT0((int) (Math.random() * 10000));
            bean.setT1((int) (Math.random() * 10000));
            bean.setT2((int) (Math.random() * 10000));
            bean.setT3((int) (Math.random() * 10000));
            bean.setT4((int) (Math.random() * 10000));
            bean.setT5((int) (Math.random() * 10000));
            bean.setT6((int) (Math.random() * 10000));
            bean.setT7((int) (Math.random() * 10000));
            bean.setT8((int) (Math.random() * 10000));
            contentBeanList.add(bean);
        }
    }

    private class SelfRemovingOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public final void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                recyclerView.removeOnScrollListener(this);
            }
        }
    }
}
