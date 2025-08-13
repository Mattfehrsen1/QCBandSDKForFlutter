package com.qcwireless.qcwatch.ui.base.repository.device;

import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcEbookDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.EbookEntity;
import com.qcwireless.qcwatch.ui.device.ebook.bean.Ebook;
import com.qcwireless.qcwatch.ui.device.ebook.bean.EbookSortComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EbookRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository;", "", "()V", "qcEbookDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcEbookDao;", "deleteBook", "", "name", "", "getAllBooks", "", "Lcom/qcwireless/qcwatch/ui/device/ebook/bean/Ebook;", "saveBook", "ebookEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/EbookEntity;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EbookRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<EbookRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<EbookRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.EbookRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final EbookRepository invoke() {
            return new EbookRepository();
        }
    });
    private final QcEbookDao qcEbookDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcEbookDao();

    public final List<Ebook> getAllBooks() {
        ArrayList arrayList = new ArrayList();
        for (EbookEntity ebookEntity : this.qcEbookDao.queryEbooks()) {
            Ebook ebook = new Ebook();
            ebook.setFirstName(ebookEntity.getFirstName());
            ebook.setName(ebookEntity.getBookName());
            ebook.setPath(ebookEntity.getFilePath());
            arrayList.add(ebook);
        }
        Collections.sort(arrayList, new EbookSortComparator());
        return arrayList;
    }

    public final void saveBook(EbookEntity ebookEntity) {
        Intrinsics.checkNotNullParameter(ebookEntity, "ebookEntity");
        this.qcEbookDao.insert(ebookEntity);
    }

    public final void deleteBook(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.qcEbookDao.deleteBookName(name);
    }

    /* compiled from: EbookRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EbookRepository getGetInstance() {
            return (EbookRepository) EbookRepository.getInstance$delegate.getValue();
        }
    }
}
