
package com.jake.health.ui.base;

import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jake.health.R;
import com.jake.health.ui.adapter.BaseListAdapter;
import com.jake.health.ui.widgt.MaterialProgressBar;
import com.jake.health.ui.widgt.StatusView;
import com.jake.health.utils.ToastUtil;

import java.util.List;

public abstract class BaseListFragment<T> extends BaseWorkerFragment implements
        AbsListView.OnScrollListener, View.OnClickListener, AdapterView.OnItemClickListener {

    protected StatusView mStatusView;

    protected ListView mListView;

    protected View mFooterView;

    protected BaseListAdapter<T> mAdapter;

    protected int PAGE_SIZE = 10;

    private int mPageIndex = 0;

    // 数据是否已经全部加载完
    public boolean mHasLoadAllData;


    private boolean mRemoveFooterViewAfterAllDataLoaded = false;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public void slide2Top() {
        if (mListView != null) {
            mListView.setSelection(0);
        }
    }

    public void setRefreshEnable(boolean enable) {
        mSwipeRefreshLayout.setEnabled(enable);
    }

    protected void setLoadViewBackgroundColor(int color) {
        mFooterView.setBackgroundColor(color);
    }

    @Override
    protected void initView() {
        mStatusView = new StatusView(getActivity());
        mStatusView.setContentView(findLayoutId());
        setContentView(mStatusView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout
                .setProgressBackgroundColorSchemeColor(getColor(R.color.title_background));
        mSwipeRefreshLayout.setColorSchemeColors(getColor(R.color.white));
        mListView = (ListView) findViewById(R.id.lv_list);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);
        addHeaderView(mListView);
        if (mListView.getHeaderViewsCount() <= 0) {
            // 部分手机必须先addHeader后footer才能正常显示
            mListView.addHeaderView(new View(getActivity()));
        }
        mFooterView = getFooterView();
        if (mFooterView == null) {
            mFooterView = inflate(R.layout.footer_base_list_fragment, null);
            mFooterView.setVisibility(View.GONE);
            mFooterView.findViewById(R.id.rl_load_more).setOnClickListener(this);
        }
        mListView.addFooterView(mFooterView);
        mAdapter = createAdapter();
        mListView.setAdapter(mAdapter);

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mStatusView.setStatusListener(new StatusView.StatusListener() {
            @Override
            public void reLoadPageData() {
                reLoadData();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reLoadData();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        sendEmptyBackgroundMessage(MSG_BACK_LOAD_DATA);
    }

    // 重新加载数据，页面索引清0
    public void reLoadData() {
        mHasLoadAllData = false;
        mPageIndex = 0;
        sendEmptyBackgroundMessage(MSG_BACK_LOAD_DATA);
    }

    protected final static int STATE_LOADING = 0X01;

    protected final static int STATE_LOAD_MORE = 0X02;

    protected final static int STATE_LOAD_ALL_DATA = 0X03;

    // 设置footerView状态
    protected void setFooterViewState(int state) {
        if (mFooterView.getVisibility() != View.VISIBLE) {
            mFooterView.setVisibility(View.VISIBLE);
        }
        TextView textView = (TextView) mFooterView.findViewById(R.id.tv_loading_tips);
        MaterialProgressBar progressBar = (MaterialProgressBar) mFooterView.findViewById(R.id.mpb_progress_bar);
        if (textView == null || progressBar == null) {
            return;
        }
        if (state == STATE_LOADING) {
            // 显示正在加载
            textView.setText(R.string.loading);
            progressBar.show();
        } else if (state == STATE_LOAD_MORE) {
            // 显示加载更多
            textView.setText(R.string.load_more);
            progressBar.hide();
        } else if (state == STATE_LOAD_ALL_DATA) {
            // 显示数据全部加载
            textView.setText(R.string.load_finish);
            progressBar.hide();
        }
    }

    public static final int MSG_UI_START_LOADING = 0x1000;

    public static final int MSG_UI_LOAD_SUCCESS = 0x1001;

    public static final int MSG_UI_LOAD_FAIL = 0x1002;

    public static final int MSG_UI_ALL_DATA_HAVE_LOADED = 0x1003;

    public static final int MSG_UI_NO_DATA = 0x1004;

    public static final int MSG_UI_FINISH_REFRESH = 0x1005;

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);
        switch (msg.what) {
            case MSG_UI_START_LOADING:
                // 开始加载数据，显示loading
                if (mPageIndex == 1) {
                    if (mAdapter.getCount() == 0) {
                        mStatusView.showLoadingView();
                    } else {
                        mFooterView.setVisibility(View.GONE);
                    }
                } else {
                    setFooterViewState(STATE_LOADING);
                }
                break;
            case MSG_UI_LOAD_SUCCESS:
                // 数据加载成功,且数据条数不为0
                if (msg.obj != null && msg.obj instanceof List<?>) {
                    List<T> list = (List<T>) msg.obj;
                    if (mPageIndex == 1 && mAdapter.getCount() > 0) {
                        mAdapter.setDataAndNotifyDataSetChanged(list);
                    } else {
                        mAdapter.addAllAndNotifyDataSetChanged(list);
                    }
                }
                setFooterViewState(STATE_LOAD_MORE);
                mStatusView.showContentView();
                break;
            case MSG_UI_LOAD_FAIL:
                // 数据加载失败
                if (mPageIndex > 0) {
                    mPageIndex--;
                }
                if (mPageIndex != 0) {
                    setFooterViewState(STATE_LOAD_MORE);
                    ToastUtil.show(R.string.load_fail_toast);
                } else {
                    mStatusView.showFailView();
                }
                break;
            case MSG_UI_ALL_DATA_HAVE_LOADED:
                mHasLoadAllData = true;
                setFooterViewState(STATE_LOAD_ALL_DATA);
                if (mRemoveFooterViewAfterAllDataLoaded) {
                    mListView.removeFooterView(mFooterView);
                }
                break;
            case MSG_UI_NO_DATA:
                // 数据加载成功,但数据条数为0
                if (mPageIndex > 0) {
                    mPageIndex--;
                }
                if (mPageIndex == 0) {
                    mStatusView.showNoDataView();
                }
                mHasLoadAllData = true;
                setFooterViewState(STATE_LOAD_ALL_DATA);
                if (mRemoveFooterViewAfterAllDataLoaded) {
                    mListView.removeFooterView(mFooterView);
                }
                break;
            case MSG_UI_FINISH_REFRESH:
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                break;
        }
    }


    protected static final int MSG_BACK_LOAD_DATA = 0x2000;

    @Override
    public void handleBackgroundMessage(Message msg) {
        super.handleBackgroundMessage(msg);
        switch (msg.what) {
            case MSG_BACK_LOAD_DATA:
                baseLoadDataTask();
                break;
        }
    }

    private void baseLoadDataTask() {
        // 开始请求数据
        ++mPageIndex;
        sendEmptyUiMessage(MSG_UI_START_LOADING);
        List<T> list = loadData();
        if (list != null) {
            List<T> dealResult = dealData(list);
            if (dealResult != null && dealResult.size() > 0) {
                Message message = obtainUiMessage();
                message.what = MSG_UI_LOAD_SUCCESS;
                message.obj = dealResult;
                message.sendToTarget();
            } else {
                sendEmptyUiMessage(MSG_UI_NO_DATA);
            }
            if (list.size() < PAGE_SIZE) {
                sendEmptyUiMessage(MSG_UI_ALL_DATA_HAVE_LOADED);
            }
        } else {
            // apps为null表示加载数据失败
            sendEmptyUiMessage(MSG_UI_LOAD_FAIL);
        }
        if (mPageIndex == 1 && mAdapter.getCount() > 0) {
            sendEmptyUiMessage(MSG_UI_FINISH_REFRESH);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > mListView.getHeaderViewsCount() - 1
                && position - mListView.getHeaderViewsCount() < mAdapter.getCount()) {
            onListItemClick((T) mAdapter.getItem(position - mListView.getHeaderViewsCount()));
        }
        onListItemClick(parent, view, position, id);
    }

    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mListView == null) {
            return;
        }
        // 不调用这个在魅族手机上滚动时会出现显示不全的情况
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            if (mListView != null) {
                mListView.requestLayout();
            }
        }
        // 滚动到底部
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                || scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
            if (view.getLastVisiblePosition() >= (view.getCount() - 1) && !mHasLoadAllData) {
                MaterialProgressBar progressBar = (MaterialProgressBar) mFooterView.findViewById(R.id.mpb_progress_bar);
                if (progressBar.getVisibility() == View.VISIBLE) {
                    return;
                }
                progressBar.hide();
                sendEmptyBackgroundMessage(MSG_BACK_LOAD_DATA);
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
    }

    @Override
    public void onClick(View v) {
        if (mFooterView != null && mFooterView.findViewById(R.id.mpb_progress_bar).getVisibility() != View.VISIBLE && !mHasLoadAllData) {
            sendEmptyBackgroundMessage(MSG_BACK_LOAD_DATA);
        }

    }

    /**
     * 指定layout资源id
     *
     * @return
     */
    protected int findLayoutId() {
        return R.layout.fragment_base_list;
    }

    /**
     * 添加头部
     *
     * @param listView
     */
    protected void addHeaderView(ListView listView) {
    }

    /**
     * 获取尾部
     */
    protected View getFooterView() {
        return null;
    }

    /**
     * 创建适配器
     *
     * @return
     */
    protected abstract BaseListAdapter<T> createAdapter();

    /**
     * 加载数据，子类重写
     *
     * @return
     */
    protected abstract List<T> loadData();

    /**
     * 列表点击回调
     *
     * @param t
     */
    protected void onListItemClick(T t) {

    }

    /**
     * 处理返回数据
     *
     * @param dataList
     */
    protected List<T> dealData(List<T> dataList) {
        return dataList;
    }


    /**
     * 设置当所有数据加载完毕时是否移除footerView
     *
     * @param remove
     */
    protected void setRemoveFooterViewAfterAllDataLoaded(boolean remove) {
        mRemoveFooterViewAfterAllDataLoaded = remove;
    }

    /**
     * 获取页码
     *
     * @return
     */
    protected int getPageIndex() {
        return mPageIndex;
    }

    /**
     * 设置页码
     *
     * @param pageIndex
     */
    protected void setPageIndex(int pageIndex) {
        mPageIndex = pageIndex;
    }
}
