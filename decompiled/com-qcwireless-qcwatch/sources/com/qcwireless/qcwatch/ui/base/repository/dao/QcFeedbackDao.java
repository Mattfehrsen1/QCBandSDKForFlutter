package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.FeedbackEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcFeedbackDao.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0004H'¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcFeedbackDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/FeedbackEntity;", "queryFeedbackList", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcFeedbackDao extends BaseDao<FeedbackEntity> {
    List<FeedbackEntity> queryFeedbackList();
}
