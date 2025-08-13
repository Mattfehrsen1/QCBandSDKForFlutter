package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import com.androidnetworking.common.ANConstants;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbz;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzr extends zzbz {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private static final ArrayMap<String, FastJsonResponse.Field<?, ?>> zzb;
    final int zza;
    private List<String> zzc;
    private List<String> zzd;
    private List<String> zze;
    private List<String> zzf;
    private List<String> zzg;

    static {
        ArrayMap<String, FastJsonResponse.Field<?, ?>> arrayMap = new ArrayMap<>();
        zzb = arrayMap;
        arrayMap.put("registered", FastJsonResponse.Field.forStrings("registered", 2));
        arrayMap.put("in_progress", FastJsonResponse.Field.forStrings("in_progress", 3));
        arrayMap.put(ANConstants.SUCCESS, FastJsonResponse.Field.forStrings(ANConstants.SUCCESS, 4));
        arrayMap.put("failed", FastJsonResponse.Field.forStrings("failed", 5));
        arrayMap.put("escrowed", FastJsonResponse.Field.forStrings("escrowed", 6));
    }

    public zzr() {
        this.zza = 1;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final Map<String, FastJsonResponse.Field<?, ?>> getFieldMappings() {
        return zzb;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final Object getFieldValue(FastJsonResponse.Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                return Integer.valueOf(this.zza);
            case 2:
                return this.zzc;
            case 3:
                return this.zzd;
            case 4:
                return this.zze;
            case 5:
                return this.zzf;
            case 6:
                return this.zzg;
            default:
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(safeParcelableFieldId);
                throw new IllegalStateException(sb.toString());
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final boolean isFieldSet(FastJsonResponse.Field field) {
        return true;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setStringsInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<String> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.zzc = arrayList;
            return;
        }
        if (safeParcelableFieldId == 3) {
            this.zzd = arrayList;
            return;
        }
        if (safeParcelableFieldId == 4) {
            this.zze = arrayList;
        } else if (safeParcelableFieldId == 5) {
            this.zzf = arrayList;
        } else {
            if (safeParcelableFieldId != 6) {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string list.", Integer.valueOf(safeParcelableFieldId)));
            }
            this.zzg = arrayList;
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzc, false);
        SafeParcelWriter.writeStringList(parcel, 3, this.zzd, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.zze, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzf, false);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    zzr(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5) {
        this.zza = i;
        this.zzc = list;
        this.zzd = list2;
        this.zze = list3;
        this.zzf = list4;
        this.zzg = list5;
    }
}
