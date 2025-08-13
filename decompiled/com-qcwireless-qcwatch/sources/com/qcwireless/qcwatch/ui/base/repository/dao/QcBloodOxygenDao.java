package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.BloodOxygenEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcBloodOxygenDao.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H'J \u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH'¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcBloodOxygenDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/BloodOxygenEntity;", "queryBloodOxygenByDate", "mac", "", "dateStr", "queryBloodOxygenByDateDesc", "queryUploadToServer", "", "startTime", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcBloodOxygenDao extends BaseDao<BloodOxygenEntity> {
    BloodOxygenEntity queryBloodOxygenByDate(String mac, String dateStr);

    BloodOxygenEntity queryBloodOxygenByDateDesc(String mac);

    List<BloodOxygenEntity> queryUploadToServer(String mac, long startTime);
}
