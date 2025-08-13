package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.MenstruationEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcMenstruationDao.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H'J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H'J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0002H'J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH'¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMenstruationDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/MenstruationEntity;", "deleteAll", "", "queryAll", "", "queryMaxByStartTime", "queryMenstruationByYearAndMonth", "year", "", "month", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcMenstruationDao extends BaseDao<MenstruationEntity> {
    void deleteAll();

    List<MenstruationEntity> queryAll();

    MenstruationEntity queryMaxByStartTime();

    List<MenstruationEntity> queryMenstruationByYearAndMonth(int year, int month);
}
