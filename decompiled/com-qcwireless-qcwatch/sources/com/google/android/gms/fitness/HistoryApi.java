package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public interface HistoryApi {

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class ViewIntentBuilder {
        private final Context zza;
        private final DataType zzb;
        private DataSource zzc;
        private long zzd;
        private long zze;
        private String zzf;

        public ViewIntentBuilder(Context context, DataType dataType) {
            this.zza = context;
            this.zzb = dataType;
        }

        public Intent build() {
            Intent intent;
            ResolveInfo resolveInfoResolveActivity;
            Preconditions.checkState(this.zzd > 0, "Start time must be set");
            Preconditions.checkState(this.zze > this.zzd, "End time must be set and after start time");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(DataType.getMimeType(this.zzc.getDataType()));
            intent2.putExtra(Fitness.EXTRA_START_TIME, this.zzd);
            intent2.putExtra(Fitness.EXTRA_END_TIME, this.zze);
            SafeParcelableSerializer.serializeToIntentExtra(this.zzc, intent2, DataSource.EXTRA_DATA_SOURCE);
            if (this.zzf == null || (resolveInfoResolveActivity = this.zza.getPackageManager().resolveActivity((intent = new Intent(intent2).setPackage(this.zzf)), 0)) == null) {
                return intent2;
            }
            intent.setComponent(new ComponentName(this.zzf, resolveInfoResolveActivity.activityInfo.name));
            return intent;
        }

        public ViewIntentBuilder setDataSource(DataSource dataSource) {
            Preconditions.checkArgument(dataSource.getDataType().equals(this.zzb), "Data source %s is not for the data type %s", dataSource, this.zzb);
            this.zzc = dataSource;
            return this;
        }

        public ViewIntentBuilder setPreferredApplication(String str) {
            this.zzf = str;
            return this;
        }

        public ViewIntentBuilder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.zzd = timeUnit.toMillis(j);
            this.zze = timeUnit.toMillis(j2);
            return this;
        }
    }

    PendingResult<Status> deleteData(GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest);

    PendingResult<Status> insertData(GoogleApiClient googleApiClient, DataSet dataSet);

    PendingResult<DailyTotalResult> readDailyTotal(GoogleApiClient googleApiClient, DataType dataType);

    PendingResult<DailyTotalResult> readDailyTotalFromLocalDevice(GoogleApiClient googleApiClient, DataType dataType);

    PendingResult<DataReadResult> readData(GoogleApiClient googleApiClient, DataReadRequest dataReadRequest);

    PendingResult<Status> registerDataUpdateListener(GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest);

    PendingResult<Status> unregisterDataUpdateListener(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> updateData(GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest);
}
