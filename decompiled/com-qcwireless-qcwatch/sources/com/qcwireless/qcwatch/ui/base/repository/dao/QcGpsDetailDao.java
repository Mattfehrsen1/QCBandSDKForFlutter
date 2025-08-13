package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcGpsDetailDao.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0004H'J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H'J\n\u0010\b\u001a\u0004\u0018\u00010\u0002H'J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bH'¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcGpsDetailDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/GpsDetail;", "queryAll", "", "queryByStartTime", "start", "", "queryFirstByStartTime", "queryListByDate", "date", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcGpsDetailDao extends BaseDao<GpsDetail> {
    List<GpsDetail> queryAll();

    GpsDetail queryByStartTime(long start);

    GpsDetail queryFirstByStartTime();

    List<GpsDetail> queryListByDate(String date);
}
