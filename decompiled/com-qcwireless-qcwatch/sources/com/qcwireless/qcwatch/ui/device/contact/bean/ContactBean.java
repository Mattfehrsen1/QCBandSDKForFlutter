package com.qcwireless.qcwatch.ui.device.contact.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContactBean.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/bean/ContactBean;", "", "()V", "contactName", "", "getContactName", "()Ljava/lang/String;", "setContactName", "(Ljava/lang/String;)V", "firstName", "getFirstName", "setFirstName", "phoneNumber", "getPhoneNumber", "setPhoneNumber", "select", "", "getSelect", "()Z", "setSelect", "(Z)V", "equals", FitnessActivities.OTHER, "hashCode", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactBean {
    private boolean select;
    private String phoneNumber = "";
    private String contactName = "";
    private String firstName = "";

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final void setPhoneNumber(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phoneNumber = str;
    }

    public final String getContactName() {
        return this.contactName;
    }

    public final void setContactName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contactName = str;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.firstName = str;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Objects.requireNonNull(other, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.device.contact.bean.ContactBean");
        ContactBean contactBean = (ContactBean) other;
        return Intrinsics.areEqual(this.phoneNumber, contactBean.phoneNumber) && Intrinsics.areEqual(this.contactName, contactBean.contactName);
    }

    public int hashCode() {
        return (this.phoneNumber.hashCode() * 31) + this.contactName.hashCode();
    }
}
