package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.SportPlusDetail;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcSportPlusDetailDao.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H'J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H'J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H'J\"\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH'J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH'¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcSportPlusDetailDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SportPlusDetail;", "queryByAddressOrderByStartTime", "deviceAddress", "", "queryByDate", "", "dateStr", "queryBySync", "queryLastSyncDate", "querySportByStartTimeAndType", "type", "", "startTime", "", "querySportPlusByStartTime", "device", "start", "end", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcSportPlusDetailDao extends BaseDao<SportPlusDetail> {
    SportPlusDetail queryByAddressOrderByStartTime(String deviceAddress);

    List<SportPlusDetail> queryByDate(String dateStr, String deviceAddress);

    List<SportPlusDetail> queryBySync();

    SportPlusDetail queryLastSyncDate(String deviceAddress);

    SportPlusDetail querySportByStartTimeAndType(String deviceAddress, int type, long startTime);

    List<SportPlusDetail> querySportPlusByStartTime(String device, long start, long end);
}
