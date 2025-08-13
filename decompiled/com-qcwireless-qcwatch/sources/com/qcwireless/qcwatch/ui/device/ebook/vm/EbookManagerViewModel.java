package com.qcwireless.qcwatch.ui.device.ebook.vm;

import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.EbookRepository;
import com.qcwireless.qcwatch.ui.device.ebook.bean.Ebook;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EbookManagerViewModel.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookManagerViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "ebookRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository;)V", "deviceEbookList", "", "Lcom/qcwireless/qcwatch/ui/device/ebook/bean/Ebook;", "getDeviceEbookList", "()Ljava/util/List;", "setDeviceEbookList", "(Ljava/util/List;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EbookManagerViewModel extends BaseViewModel {
    private List<Ebook> deviceEbookList;
    private EbookRepository ebookRepository;

    public EbookManagerViewModel(EbookRepository ebookRepository) {
        Intrinsics.checkNotNullParameter(ebookRepository, "ebookRepository");
        this.ebookRepository = ebookRepository;
        this.deviceEbookList = new ArrayList();
    }

    public final List<Ebook> getDeviceEbookList() {
        return this.deviceEbookList;
    }

    public final void setDeviceEbookList(List<Ebook> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.deviceEbookList = list;
    }
}
