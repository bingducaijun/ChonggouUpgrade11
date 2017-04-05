package com.bdcj.jcj.chonggouupgrade;

import com.bdcj.jcj.chonggouupgrade.ui.AffordBean;
import com.ta.TAApplication;

/**
 * Created by Administrator on 2017/3/25.
 */

public class AffordApp extends TAApplication {
    private static TAApplication application;
    private static AffordBean mAffordBean;

    public static AffordApp getInstance() {
        return (AffordApp) getApplication();
    }

    public static AffordBean getmAffordBean() {
        if (mAffordBean == null)
            mAffordBean = AffordBean.getInstance();
        return mAffordBean;
    }
}
