package com.mcgrady.common_res.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mcgrady.xskeleton.base.IPresenter;

import java.util.List;

/**
 * 区别于{@link com.mcgrady.xskeleton.base.BaseFragment}的是这里添加了第三方库依赖
 *
 * Created by mcgrady on 2020/4/28.
 */
public abstract class BaseLazyLoadFragment<P extends IPresenter> extends BaseFragment<P> {

    private boolean isViewCreated; // 界面是否已创建完成
    private boolean isVisibleToUser; // 是否对用户可见
    private boolean isDataLoaded; // 数据是否已请求

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }

    /**
     * 保证在initData后触发
     */
    @Override
    public void onResume() {
        super.onResume();
        isViewCreated = true;
        tryLoadData();
    }

    /**
     * ViewPager场景下，判断父fragment是否可见
     */
    private boolean isParentVisible() {
        Fragment fragment = getParentFragment();
        return fragment == null || (fragment instanceof BaseLazyLoadFragment && ((BaseLazyLoadFragment) fragment).isVisibleToUser);
    }

    /**
     * ViewPager场景下，当前fragment可见时，如果其子fragment也可见，则让子fragment请求数据
     */
    private void dispatchParentVisibleState() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment child : fragments) {
            if (child instanceof com.mcgrady.xskeleton.base.BaseLazyLoadFragment && ((BaseLazyLoadFragment) child).isVisibleToUser) {
                ((BaseLazyLoadFragment) child).tryLoadData();
            }
        }
    }

    public void tryLoadData() {
        if (isViewCreated && isVisibleToUser && isParentVisible() && !isDataLoaded) {
            lazyLoadData();
            isDataLoaded = true;
            //通知子Fragment请求数据
            dispatchParentVisibleState();
        }
    }

    /**
     * 第一次可见时触发调用,此处实现具体的数据请求逻辑
     */
    protected abstract void lazyLoadData();

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
