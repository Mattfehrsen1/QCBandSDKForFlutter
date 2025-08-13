package com.qcwireless.qcwatch.ui.base.repository.dao;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;

/* compiled from: BaseDao.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH'J!\u0010\n\u001a\u00020\u00042\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000b\"\u00028\u0000H'¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\tH'J\u0015\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", ExifInterface.GPS_DIRECTION_TRUE, "", "delete", "", "element", "(Ljava/lang/Object;)V", "deleteList", "elements", "", "deleteSome", "", "([Ljava/lang/Object;)V", "insert", "insertAll", "list", "update", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface BaseDao<T> {
    void delete(T element);

    void deleteList(List<T> elements);

    void deleteSome(T... elements);

    void insert(T element);

    void insertAll(List<T> list);

    void update(T element);
}
