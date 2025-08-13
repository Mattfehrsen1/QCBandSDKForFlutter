package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzbw;
import com.google.android.gms.internal.auth.zzby;
import com.google.android.gms.internal.auth.zzcz;
import com.google.android.gms.internal.auth.zzhj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public class zzl {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";
    public static final String[] zza = {"com.google", "com.google.work", "cn.google"};
    public static final String zzb = "androidPackageName";
    private static final ComponentName zzc = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    private static final Logger zzd = zzd.zza("GoogleAuthUtil");

    zzl() {
    }

    public static void clearToken(Context context, String str) throws GoogleAuthException, IOException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        zzj(context, 8400000);
        Bundle bundle = new Bundle();
        String str2 = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", str2);
        String str3 = zzb;
        if (!bundle.containsKey(str3)) {
            bundle.putString(str3, str2);
        }
        zzcz.zze(context);
        if (zzhj.zze() && zzm(context)) {
            com.google.android.gms.internal.auth.zzg zzgVarZza = com.google.android.gms.internal.auth.zzh.zza(context);
            zzbw zzbwVar = new zzbw();
            zzbwVar.zza(str);
            try {
                zzh(zzgVarZza.zza(zzbwVar), "clear token");
                return;
            } catch (ApiException e) {
                zzk(e, "clear token");
            }
        }
        zzg(context, zzc, new zzh(str, bundle));
    }

    public static List<AccountChangeEvent> getAccountChangeEvents(Context context, int i, String str) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(str, "accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        zzj(context, 8400000);
        AccountChangeEventsRequest accountChangeEventsRequest = new AccountChangeEventsRequest();
        accountChangeEventsRequest.setAccountName(str);
        accountChangeEventsRequest.setEventIndex(i);
        zzcz.zze(context);
        if (zzhj.zzd() && zzm(context)) {
            try {
                AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) zzh(com.google.android.gms.internal.auth.zzh.zza(context).zzb(accountChangeEventsRequest), "account change events retrieval");
                zzi(accountChangeEventsResponse);
                return accountChangeEventsResponse.getEvents();
            } catch (ApiException e) {
                zzk(e, "account change events retrieval");
            }
        }
        return (List) zzg(context, zzc, new zzi(accountChangeEventsRequest));
    }

    public static String getAccountId(Context context, String str) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(str, "accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        zzj(context, 8400000);
        return getToken(context, str, "^^_account_id_^^", new Bundle());
    }

    public static String getToken(Context context, Account account, String str) throws IOException, GoogleAuthException {
        return getToken(context, account, str, new Bundle());
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken("com.google", str);
    }

    public static Bundle removeAccount(Context context, final Account account) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context);
        zzl(account);
        zzj(context, 8400000);
        zzcz.zze(context);
        if (zzhj.zze() && zzm(context)) {
            try {
                Bundle bundle = (Bundle) zzh(com.google.android.gms.internal.auth.zzh.zza(context).zzd(account), "account removal");
                zzi(bundle);
                return bundle;
            } catch (ApiException e) {
                zzk(e, "account removal");
            }
        }
        return (Bundle) zzg(context, zzc, new zzk() { // from class: com.google.android.gms.auth.zzf
            @Override // com.google.android.gms.auth.zzk
            public final Object zza(IBinder iBinder) throws RemoteException, IOException {
                Bundle bundleZzf = com.google.android.gms.internal.auth.zze.zzb(iBinder).zzf(account);
                if (bundleZzf != null) {
                    return bundleZzf;
                }
                throw new IOException("Service call returned null.");
            }
        });
    }

    public static Boolean requestGoogleAccountsAccess(Context context) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context);
        zzj(context, 11400000);
        String str = context.getApplicationInfo().packageName;
        zzcz.zze(context);
        if (zzhj.zze() && zzm(context)) {
            try {
                Bundle bundle = (Bundle) zzh(com.google.android.gms.internal.auth.zzh.zza(context).zze(str), "google accounts access request");
                String string = bundle.getString("Error");
                Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
                zzby zzbyVarZza = zzby.zza(string);
                if (zzby.SUCCESS.equals(zzbyVarZza)) {
                    return true;
                }
                if (!zzby.zzb(zzbyVarZza)) {
                    throw new GoogleAuthException(string);
                }
                Logger logger = zzd;
                String strValueOf = String.valueOf(zzbyVarZza);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 31);
                sb.append("isUserRecoverableError status: ");
                sb.append(strValueOf);
                logger.w("GoogleAuthUtil", sb.toString());
                throw new UserRecoverableAuthException(string, intent);
            } catch (ApiException e) {
                zzk(e, "google accounts access request");
            }
        }
        return (Boolean) zzg(context, zzc, new zzj(str));
    }

    public static TokenData zza(Context context, final Account account, final String str, Bundle bundle) throws GoogleAuthException, IOException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        Preconditions.checkNotEmpty(str, "Scope cannot be empty or null.");
        zzl(account);
        zzj(context, 8400000);
        final Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        String str2 = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", str2);
        String str3 = zzb;
        if (TextUtils.isEmpty(bundle2.getString(str3))) {
            bundle2.putString(str3, str2);
        }
        bundle2.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        zzcz.zze(context);
        if (zzhj.zze() && zzm(context)) {
            try {
                Bundle bundle3 = (Bundle) zzh(com.google.android.gms.internal.auth.zzh.zza(context).zzc(account, str, bundle2), "token retrieval");
                zzi(bundle3);
                return zzf(bundle3);
            } catch (ApiException e) {
                zzk(e, "token retrieval");
            }
        }
        return (TokenData) zzg(context, zzc, new zzk() { // from class: com.google.android.gms.auth.zzg
            @Override // com.google.android.gms.auth.zzk
            public final Object zza(IBinder iBinder) {
                return zzl.zzb(account, str, bundle2, iBinder);
            }
        });
    }

    static /* synthetic */ TokenData zzb(Account account, String str, Bundle bundle, IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundleZze = com.google.android.gms.internal.auth.zze.zzb(iBinder).zze(account, str, bundle);
        if (bundleZze != null) {
            return zzf(bundleZze);
        }
        throw new IOException("Service call returned null");
    }

    static /* synthetic */ Object zzd(Object obj) throws IOException {
        zzi(obj);
        return obj;
    }

    private static TokenData zzf(Bundle bundle) throws GoogleAuthException, IOException {
        TokenData tokenData;
        Parcelable.Creator<TokenData> creator = TokenData.CREATOR;
        ClassLoader classLoader = TokenData.class.getClassLoader();
        if (classLoader != null) {
            bundle.setClassLoader(classLoader);
        }
        Bundle bundle2 = bundle.getBundle("tokenDetails");
        if (bundle2 == null) {
            tokenData = null;
        } else {
            if (classLoader != null) {
                bundle2.setClassLoader(classLoader);
            }
            tokenData = (TokenData) bundle2.getParcelable("TokenData");
        }
        if (tokenData != null) {
            return tokenData;
        }
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzby zzbyVarZza = zzby.zza(string);
        if (!zzby.zzb(zzbyVarZza)) {
            if (zzby.NETWORK_ERROR.equals(zzbyVarZza) || zzby.SERVICE_UNAVAILABLE.equals(zzbyVarZza) || zzby.INTNERNAL_ERROR.equals(zzbyVarZza) || zzby.AUTH_SECURITY_ERROR.equals(zzbyVarZza)) {
                throw new IOException(string);
            }
            throw new GoogleAuthException(string);
        }
        Logger logger = zzd;
        String strValueOf = String.valueOf(zzbyVarZza);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 31);
        sb.append("isUserRecoverableError status: ");
        sb.append(strValueOf);
        logger.w("GoogleAuthUtil", sb.toString());
        throw new UserRecoverableAuthException(string, intent);
    }

    private static <T> T zzg(Context context, ComponentName componentName, zzk<T> zzkVar) throws IOException, GoogleAuthException {
        BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
        GmsClientSupervisor gmsClientSupervisor = GmsClientSupervisor.getInstance(context);
        try {
            try {
                if (!gmsClientSupervisor.bindService(componentName, blockingServiceConnection, "GoogleAuthUtil")) {
                    throw new IOException("Could not bind to service.");
                }
                try {
                    return zzkVar.zza(blockingServiceConnection.getService());
                } catch (RemoteException | InterruptedException e) {
                    Log.i("GoogleAuthUtil", "Error on service connection.", e);
                    throw new IOException("Error on service connection.", e);
                }
            } finally {
                gmsClientSupervisor.unbindService(componentName, blockingServiceConnection, "GoogleAuthUtil");
            }
        } catch (SecurityException e2) {
            Log.w("GoogleAuthUtil", String.format("SecurityException while bind to auth service: %s", e2.getMessage()));
            throw new IOException("SecurityException while binding to Auth service.", e2);
        }
    }

    private static <ResultT> ResultT zzh(Task<ResultT> task, String str) throws IOException, ApiException {
        try {
            return (ResultT) Tasks.await(task);
        } catch (InterruptedException e) {
            String str2 = String.format("Interrupted while waiting for the task of %s to finish.", str);
            zzd.w(str2, new Object[0]);
            throw new IOException(str2, e);
        } catch (CancellationException e2) {
            String str3 = String.format("Canceled while waiting for the task of %s to finish.", str);
            zzd.w(str3, new Object[0]);
            throw new IOException(str3, e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof ApiException) {
                throw ((ApiException) cause);
            }
            String str4 = String.format("Unable to get a result for %s due to ExecutionException.", str);
            zzd.w(str4, new Object[0]);
            throw new IOException(str4, e3);
        }
    }

    private static <T> T zzi(T t) throws IOException {
        if (t != null) {
            return t;
        }
        zzd.w("Service call returned null.", new Object[0]);
        throw new IOException("Service unavailable.");
    }

    private static void zzj(Context context, int i) throws GoogleAuthException {
        try {
            GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context.getApplicationContext(), i);
        } catch (GooglePlayServicesIncorrectManifestValueException e) {
            e = e;
            throw new GoogleAuthException(e.getMessage(), e);
        } catch (GooglePlayServicesNotAvailableException e2) {
            e = e2;
            throw new GoogleAuthException(e.getMessage(), e);
        } catch (GooglePlayServicesRepairableException e3) {
            throw new GooglePlayServicesAvailabilityException(e3.getConnectionStatusCode(), e3.getMessage(), e3.getIntent());
        }
    }

    private static void zzk(ApiException apiException, String str) {
        zzd.w("%s failed via GoogleAuthServiceClient, falling back to previous approach:\n%s", str, Log.getStackTraceString(apiException));
    }

    private static boolean zzm(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context, 17895000) != 0) {
            return false;
        }
        String str = context.getApplicationInfo().packageName;
        Iterator<String> it = zzhj.zzb().zzm().iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return false;
            }
        }
        return true;
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) throws IOException, GoogleAuthException {
        zzl(account);
        return zza(context, account, str, bundle).zza();
    }

    static void zze(Intent intent) throws URISyntaxException {
        if (intent == null) {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        try {
            Intent.parseUri(intent.toUri(1), 1);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }

    private static void zzl(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (TextUtils.isEmpty(account.name)) {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
        String[] strArr = zza;
        for (int i = 0; i < 3; i++) {
            if (strArr[i].equals(account.type)) {
                return;
            }
        }
        throw new IllegalArgumentException("Account type not supported");
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2, Bundle bundle) throws IOException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2, bundle);
    }
}
