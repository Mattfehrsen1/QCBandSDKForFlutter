package com.qcwireless.qcwatch.ui.base.repository.healthy;

import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcStepTotalDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.StepTotal;
import com.qcwireless.qcwatch.ui.base.view.QStepMonthHistoryBarChart;
import com.qcwireless.qcwatch.ui.base.view.QStepWeekHistoryBarChart;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StepHistoryRepository.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/healthy/StepHistoryRepository;", "", "()V", "stepTotalDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcStepTotalDao;", "queryMonthHistoryStepByDate", "", "Lcom/qcwireless/qcwatch/ui/base/view/QStepMonthHistoryBarChart$StepDataBean;", "start", "Lcom/qcwireless/qc_utils/date/DateUtil;", "end", "queryWeekHistoryStepByDate", "Lcom/qcwireless/qcwatch/ui/base/view/QStepWeekHistoryBarChart$StepDataBean;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StepHistoryRepository {
    private final QcStepTotalDao stepTotalDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcStepTotalDao();

    public final List<QStepWeekHistoryBarChart.StepDataBean> queryWeekHistoryStepByDate(DateUtil start, DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
        dateUtil.setHour(0);
        dateUtil.setMinute(0);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<StepTotal> listQueryByAddressAndDate = this.stepTotalDao.queryByAddressAndDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), String.valueOf(start.getZeroTime()), String.valueOf(end.getUnixTimestamp()));
        for (int i = 0; i < 7; i++) {
            String y_m_d = dateUtil.getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "tempDate.y_M_D");
            linkedHashMap.put(y_m_d, new QStepWeekHistoryBarChart.StepDataBean(dateUtil.getUnixTimestamp(), 0));
            dateUtil.addDay(1);
        }
        for (StepTotal stepTotal : listQueryByAddressAndDate) {
            String dateStr = stepTotal.getDateStr();
            QStepWeekHistoryBarChart.StepDataBean stepDataBean = (QStepWeekHistoryBarChart.StepDataBean) linkedHashMap.get(dateStr);
            if (stepDataBean != null) {
                stepDataBean.setSteps(stepTotal.getStep());
            }
            if (stepDataBean != null) {
                stepDataBean.setDistance(stepTotal.getDistance());
            }
            if (stepDataBean != null) {
                stepDataBean.setCalorie(stepTotal.getCarolie());
            }
            if (stepDataBean != null) {
                linkedHashMap.put(dateStr, stepDataBean);
            }
        }
        return new ArrayList(linkedHashMap.values());
    }

    public final List<QStepMonthHistoryBarChart.StepDataBean> queryMonthHistoryStepByDate(DateUtil start, DateUtil end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        DateUtil dateUtil = new DateUtil(start.getUnixTimestamp(), true);
        dateUtil.setHour(0);
        dateUtil.setMinute(0);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<StepTotal> listQueryByAddressAndDate = this.stepTotalDao.queryByAddressAndDate(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), String.valueOf(start.getZeroTime()), String.valueOf(end.getUnixTimestamp()));
        int daysOfMonth = DateUtil.getDaysOfMonth(start.toDate());
        if (1 <= daysOfMonth) {
            int i = 1;
            while (true) {
                String y_m_d = dateUtil.getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "tempDate.y_M_D");
                linkedHashMap.put(y_m_d, new QStepMonthHistoryBarChart.StepDataBean(dateUtil.getUnixTimestamp(), 0));
                dateUtil.addDay(1);
                if (i == daysOfMonth) {
                    break;
                }
                i++;
            }
        }
        for (StepTotal stepTotal : listQueryByAddressAndDate) {
            String dateStr = stepTotal.getDateStr();
            QStepMonthHistoryBarChart.StepDataBean stepDataBean = (QStepMonthHistoryBarChart.StepDataBean) linkedHashMap.get(dateStr);
            if (stepDataBean != null) {
                stepDataBean.setSteps(stepTotal.getStep());
            }
            if (stepDataBean != null) {
                stepDataBean.setDistance(stepTotal.getDistance());
            }
            if (stepDataBean != null) {
                stepDataBean.setCalorie(stepTotal.getCarolie());
            }
            if (stepDataBean != null) {
                linkedHashMap.put(dateStr, stepDataBean);
            }
        }
        return new ArrayList(linkedHashMap.values());
    }
}
