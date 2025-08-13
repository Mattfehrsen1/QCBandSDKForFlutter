package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.SleepLunchProtocol;
import kotlin.Metadata;

/* compiled from: QcSleepLunchProtocolDao.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0005H'¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSleepLunchProtocolDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepLunchProtocol;", "queryByDate", "dateStr", "", "deviceAddress", "queryLastSyncDate", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcSleepLunchProtocolDao extends BaseDao<SleepLunchProtocol> {
    SleepLunchProtocol queryByDate(String dateStr, String deviceAddress);

    SleepLunchProtocol queryLastSyncDate(String deviceAddress);
}
