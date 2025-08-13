package com.qcwireless.qcwatch.ui.base.repository.device;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcContactsDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity;
import com.qcwireless.qcwatch.ui.device.contact.bean.ContactBean;
import com.qcwireless.qcwatch.ui.device.contact.bean.PinYinStringHelper;
import com.qcwireless.qcwatch.ui.device.contact.bean.SortComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContactsRepository.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0006\u0010\u000e\u001a\u00020\nJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\fR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/ContactsRepository;", "", "()V", "PROJECTION", "", "", "[Ljava/lang/String;", "qcContactDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcContactsDao;", "addDefaultContact", "", "mutableList", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;", "deleteAllData", "getContacts", "Lcom/qcwireless/qcwatch/ui/device/contact/bean/ContactBean;", "localList", "context", "Landroid/content/Context;", "getContactsFromPhone", "queryAll", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactsRepository {
    private final QcContactsDao qcContactDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcContactDao();
    private final String[] PROJECTION = {"display_name", "data1"};

    public final void addDefaultContact(List<ContactsEntity> mutableList) {
        Intrinsics.checkNotNullParameter(mutableList, "mutableList");
        if (mutableList.size() > 0) {
            this.qcContactDao.insertAll(mutableList);
        }
    }

    public final void deleteAllData() {
        this.qcContactDao.deleteList(this.qcContactDao.queryAll(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear()));
    }

    public final List<ContactsEntity> queryAll() {
        return this.qcContactDao.queryAll(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    private final List<ContactBean> getContacts(List<ContactsEntity> localList, Context context) {
        ArrayList arrayList = new ArrayList();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, this.PROJECTION, null, null, "sort_key");
                if (cursorQuery != null) {
                    int columnIndex = cursorQuery.getColumnIndex("display_name");
                    int columnIndex2 = cursorQuery.getColumnIndex("data1");
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(columnIndex2);
                        String string2 = cursorQuery.getString(columnIndex);
                        ContactBean contactBean = new ContactBean();
                        String headChar = PinYinStringHelper.getHeadChar(string2);
                        Intrinsics.checkNotNullExpressionValue(headChar, "getHeadChar(displayName)");
                        contactBean.setFirstName(headChar);
                        contactBean.setPhoneNumber(string);
                        contactBean.setContactName(string2);
                        if (!PinYinStringHelper.isLetter(contactBean.getFirstName())) {
                            contactBean.setFirstName("#");
                        }
                        Iterator<ContactsEntity> it = localList.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                ContactsEntity next = it.next();
                                if (Intrinsics.areEqual(next.getContactName(), contactBean.getContactName()) && Intrinsics.areEqual(next.getPhoneNumber(), contactBean.getPhoneNumber())) {
                                    contactBean.setSelect(true);
                                    break;
                                }
                            }
                        }
                        arrayList.add(contactBean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursorQuery != null) {
                }
            }
            Collections.sort(arrayList, new SortComparator());
            return arrayList;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public final List<ContactBean> getContactsFromPhone(List<ContactsEntity> localList) {
        Intrinsics.checkNotNullParameter(localList, "localList");
        return getContacts(localList, QCApplication.INSTANCE.getCONTEXT());
    }
}
