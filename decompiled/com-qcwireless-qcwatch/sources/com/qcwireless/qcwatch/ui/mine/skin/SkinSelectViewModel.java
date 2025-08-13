package com.qcwireless.qcwatch.ui.mine.skin;

import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.mine.skin.bean.SkinSelectBean;
import com.qcwireless.qcwatch.ui.mine.skin.util.SkinType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SkinSelectViewModel.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/skin/SkinSelectViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "()V", "skinList", "", "Lcom/qcwireless/qcwatch/ui/mine/skin/bean/SkinSelectBean;", "getSkinList", "()Ljava/util/List;", "initData", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkinSelectViewModel extends BaseViewModel {
    private final List<SkinSelectBean> skinList = new ArrayList();

    public final List<SkinSelectBean> getSkinList() {
        return this.skinList;
    }

    public final void initData() {
        this.skinList.clear();
        this.skinList.add(0, new SkinSelectBean(SkinType.INSTANCE.getSkin_Default(), false));
        this.skinList.add(1, new SkinSelectBean(SkinType.INSTANCE.getSkin_Pink(), false));
        this.skinList.add(2, new SkinSelectBean(SkinType.INSTANCE.getSkin_Black(), false));
        this.skinList.add(3, new SkinSelectBean(SkinType.INSTANCE.getSkin_Blue(), false));
        int skinType = UserConfig.INSTANCE.getInstance().getSkinType();
        if (skinType == SkinType.INSTANCE.getSkin_Default()) {
            this.skinList.set(0, new SkinSelectBean(SkinType.INSTANCE.getSkin_Default(), true));
            return;
        }
        if (skinType == SkinType.INSTANCE.getSkin_Pink()) {
            this.skinList.set(1, new SkinSelectBean(SkinType.INSTANCE.getSkin_Pink(), true));
        } else if (skinType == SkinType.INSTANCE.getSkin_Black()) {
            this.skinList.set(2, new SkinSelectBean(SkinType.INSTANCE.getSkin_Black(), true));
        } else if (skinType == SkinType.INSTANCE.getSkin_Blue()) {
            this.skinList.set(3, new SkinSelectBean(SkinType.INSTANCE.getSkin_Blue(), true));
        }
    }
}
