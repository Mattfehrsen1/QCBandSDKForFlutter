package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EbookEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/EbookEntity;", "", "bookName", "", "firstName", "filePath", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBookName", "()Ljava/lang/String;", "setBookName", "(Ljava/lang/String;)V", "getFilePath", "setFilePath", "getFirstName", "setFirstName", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class EbookEntity {
    private String bookName;
    private String filePath;
    private String firstName;

    public static /* synthetic */ EbookEntity copy$default(EbookEntity ebookEntity, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ebookEntity.bookName;
        }
        if ((i & 2) != 0) {
            str2 = ebookEntity.firstName;
        }
        if ((i & 4) != 0) {
            str3 = ebookEntity.filePath;
        }
        return ebookEntity.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getBookName() {
        return this.bookName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFirstName() {
        return this.firstName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    public final EbookEntity copy(String bookName, String firstName, String filePath) {
        Intrinsics.checkNotNullParameter(bookName, "bookName");
        Intrinsics.checkNotNullParameter(firstName, "firstName");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        return new EbookEntity(bookName, firstName, filePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EbookEntity)) {
            return false;
        }
        EbookEntity ebookEntity = (EbookEntity) other;
        return Intrinsics.areEqual(this.bookName, ebookEntity.bookName) && Intrinsics.areEqual(this.firstName, ebookEntity.firstName) && Intrinsics.areEqual(this.filePath, ebookEntity.filePath);
    }

    public int hashCode() {
        return (((this.bookName.hashCode() * 31) + this.firstName.hashCode()) * 31) + this.filePath.hashCode();
    }

    public String toString() {
        return "EbookEntity(bookName=" + this.bookName + ", firstName=" + this.firstName + ", filePath=" + this.filePath + ')';
    }

    public EbookEntity(String bookName, String firstName, String filePath) {
        Intrinsics.checkNotNullParameter(bookName, "bookName");
        Intrinsics.checkNotNullParameter(firstName, "firstName");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.bookName = bookName;
        this.firstName = firstName;
        this.filePath = filePath;
    }

    public final String getBookName() {
        return this.bookName;
    }

    public final void setBookName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bookName = str;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.firstName = str;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final void setFilePath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.filePath = str;
    }
}
