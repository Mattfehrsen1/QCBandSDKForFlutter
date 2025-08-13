package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.HRVManualEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: ManualHrvDao.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH'J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/ManualHrvDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/HRVManualEntity;", "queryByDate", "mac", "", "date", "queryByTimestamp", "", "start", "", "end", "queryDataAll", "", "queryDataLimit", "queryDataLimitLast", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ManualHrvDao extends BaseDao<HRVManualEntity> {
    HRVManualEntity queryByDate(String mac, String date);

    List<HRVManualEntity> queryByTimestamp(String mac, int start, int end);

    List<HRVManualEntity> queryDataAll(String mac, String date);

    List<HRVManualEntity> queryDataLimit(String mac, String date);

    HRVManualEntity queryDataLimitLast(String mac);
}
