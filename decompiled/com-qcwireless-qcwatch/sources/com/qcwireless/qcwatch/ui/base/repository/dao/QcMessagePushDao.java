package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.MessagePushEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcMessagePushDao.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH'J\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\nH'J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\fH'¨\u0006\u000e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMessagePushDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/MessagePushEntity;", "deleteByPackageName", "", "name", "", "queryByName", "queryByNameAndOpen", "open", "", "queryByOpen", "", "queryByStatus", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcMessagePushDao extends BaseDao<MessagePushEntity> {
    void deleteByPackageName(String name);

    MessagePushEntity queryByName(String name);

    MessagePushEntity queryByNameAndOpen(String name, int open);

    List<MessagePushEntity> queryByOpen(int open);

    List<MessagePushEntity> queryByStatus();
}
