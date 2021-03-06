package com.mcgrady.common_core.interf;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>提供外部释放ViewHolder资源接口</p>
 *
 * @author: mcgrady
 * @date: 2019/1/15
 */

public interface IViewHolderRelease<T extends RecyclerView.ViewHolder> {

    void onRelease(T helper);
}
