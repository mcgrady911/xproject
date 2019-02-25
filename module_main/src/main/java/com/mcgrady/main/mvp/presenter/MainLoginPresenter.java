package com.mcgrady.main.mvp.presenter;

import android.app.Application;

import com.mcgrady.common_core.di.scope.ActivityScope;
import com.mcgrady.common_core.handler.RxErrorHandler;
import com.mcgrady.common_core.http.imageloader.ImageLoader;
import com.mcgrady.common_core.mvp.BasePresenter;
import com.mcgrady.main.mvp.contract.MainLoginContract;

import javax.inject.Inject;



/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2018 14:30
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class MainLoginPresenter extends BasePresenter<MainLoginContract.Model, MainLoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;

    @Inject
    public MainLoginPresenter(MainLoginContract.Model model, MainLoginContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
