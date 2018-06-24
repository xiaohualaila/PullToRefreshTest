package com.example.admin.pulltorefreshtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private List<String> list = new ArrayList<>();
    private RefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        //设置RecyclerView管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        for(int i = 0 ;i<10;i++){
            list.add("第 "+i+" 个");
        }

       //初始化适配器
        mAdapter = new MyAdapter(list);
       //设置添加或删除item时的动画，这里使用默认动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       //设置适配器
        recyclerView.setAdapter(mAdapter);
         refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                addonRefreshData();
                refreshlayout.finishRefresh();//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                addonLoadMoreData();
                refreshlayout.finishLoadMore();//传入false表示加载失败
            }
        });
    }

    public void addonRefreshData(){
        list.clear();
        for(int i = 0 ;i<5;i++){
            list.add("新数据"+i+" 个");

        }
        mAdapter.updataData(list);
    }
    public void addonLoadMoreData(){
        for(int i = 0 ;i<5;i++){
            list.add("加载数据"+i+" 个");
        }
        mAdapter.updataData(list);

    }
}
