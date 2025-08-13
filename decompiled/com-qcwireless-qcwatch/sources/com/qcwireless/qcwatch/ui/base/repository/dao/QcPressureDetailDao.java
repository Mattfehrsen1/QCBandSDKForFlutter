package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.PressureDetail;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcPressureDetailDao.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J&\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H'J\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H'J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H'¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcPressureDetailDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/PressureDetail;", "queryByAddressAndDate", "", "deviceAddress", "", "start", "end", "queryBySync", "queryDaysSyncDate", "queryLastSyncDate", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "queryPressureByDate", "date", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcPressureDetailDao extends BaseDao<PressureDetail> {
    List<PressureDetail> queryByAddressAndDate(String deviceAddress, String start, String end);

    List<PressureDetail> queryBySync();

    List<PressureDetail> queryDaysSyncDate(String deviceAddress);

    PressureDetail queryLastSyncDate(String deviceAddress, String content);

    PressureDetail queryPressureByDate(String deviceAddress, String date);
}
