package com.google.android.gms.internal.auth;

import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.realsil.sdk.bbpro.params.Mmi;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzhm implements zzhk {
    public static final zzcz<Double> zza;
    public static final zzcz<Boolean> zzb;
    public static final zzcz<Long> zzc;
    public static final zzcz<Long> zzd;
    public static final zzcz<Boolean> zze;
    public static final zzcz<zzhi> zzf;
    public static final zzcz<Boolean> zzg;
    public static final zzcz<Long> zzh;
    public static final zzcz<Long> zzi;
    public static final zzcz<Boolean> zzj;
    public static final zzcz<Boolean> zzk;
    public static final zzcz<Long> zzl;
    public static final zzcz<Boolean> zzm;
    public static final zzcz<Double> zzn;

    static {
        zzcx zzcxVarZza = new zzcx(zzcq.zza("com.google.android.gms.auth_account")).zza();
        zza = zzcxVarZza.zzb("getTokenRefactor__account_data_service_sample_percentage", 0.0d);
        zzb = zzcxVarZza.zzd("getTokenRefactor__account_data_service_tokenAPI_usable", true);
        zzc = zzcxVarZza.zzc("getTokenRefactor__account_manager_timeout_seconds", 20L);
        zzd = zzcxVarZza.zzc("getTokenRefactor__android_id_shift", 0L);
        zze = zzcxVarZza.zzd("getTokenRefactor__authenticator_logic_improved", false);
        try {
            zzf = zzcxVarZza.zze("getTokenRefactor__blocked_packages", zzhi.zzl(new byte[]{10, 19, Mmi.AU_MMI_AUDIO_EFFECT_NEXT, 111, 109, LargeDataHandler.ACTION_BT_MAC_Protocol, 97, Constants.CMD_HEALTH_PPG_DATA, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, 114, 111, 105, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, LargeDataHandler.ACTION_BT_MAC_Protocol, Mmi.AU_MMI_RWS_RESET_TO_DEFAULT, Mmi.AU_MMI_AUDIO_PASS_THROUGH, Constants.CMD_HEALTH_PPG_DATA, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, 105, Constants.CMD_HEALTH_PPG_DATA, Mmi.AU_MMI_OUTPUT_INDICATION_1, 10, 32, Mmi.AU_MMI_AUDIO_EFFECT_NEXT, 111, 109, LargeDataHandler.ACTION_BT_MAC_Protocol, Mmi.AU_MMI_OUTPUT_INDICATION_1, 111, 111, Mmi.AU_MMI_OUTPUT_INDICATION_1, 108, Mmi.AU_MMI_AUDIO_PASS_THROUGH, LargeDataHandler.ACTION_BT_MAC_Protocol, 97, Constants.CMD_HEALTH_PPG_DATA, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, 114, 111, 105, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, LargeDataHandler.ACTION_BT_MAC_Protocol, 97, 112, 112, 115, LargeDataHandler.ACTION_BT_MAC_Protocol, 109, Mmi.AU_MMI_AUDIO_PASS_THROUGH, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 116, 105, Constants.CMD_HEALTH_PPG_DATA, Mmi.AU_MMI_OUTPUT_INDICATION_1, 115, 10, 33, Mmi.AU_MMI_AUDIO_EFFECT_NEXT, 111, 109, LargeDataHandler.ACTION_BT_MAC_Protocol, Mmi.AU_MMI_OUTPUT_INDICATION_1, 111, 111, Mmi.AU_MMI_OUTPUT_INDICATION_1, 108, Mmi.AU_MMI_AUDIO_PASS_THROUGH, LargeDataHandler.ACTION_BT_MAC_Protocol, 97, Constants.CMD_HEALTH_PPG_DATA, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, 114, 111, 105, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, LargeDataHandler.ACTION_BT_MAC_Protocol, 97, 112, 112, 115, LargeDataHandler.ACTION_BT_MAC_Protocol, 109, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 115, 115, 97, Mmi.AU_MMI_OUTPUT_INDICATION_1, 105, Constants.CMD_HEALTH_PPG_DATA, Mmi.AU_MMI_OUTPUT_INDICATION_1}), zzhl.zza);
            zzg = zzcxVarZza.zzd("getTokenRefactor__chimera_get_token_evolved", true);
            zzh = zzcxVarZza.zzc("getTokenRefactor__clear_token_timeout_seconds", 20L);
            zzi = zzcxVarZza.zzc("getTokenRefactor__default_task_timeout_seconds", 20L);
            zzj = zzcxVarZza.zzd("getTokenRefactor__gaul_accounts_api_evolved", false);
            zzk = zzcxVarZza.zzd("getTokenRefactor__gaul_token_api_evolved", false);
            zzl = zzcxVarZza.zzc("getTokenRefactor__get_token_timeout_seconds", 120L);
            zzm = zzcxVarZza.zzd("getTokenRefactor__gms_account_authenticator_evolved", true);
            zzn = zzcxVarZza.zzb("getTokenRefactor__gms_account_authenticator_sample_percentage", 0.0d);
        } catch (zzew e) {
            throw new AssertionError("Could not parse proto flag \"getTokenRefactor__blocked_packages\"", e);
        }
    }

    @Override // com.google.android.gms.internal.auth.zzhk
    public final zzhi zza() {
        return zzf.zzb();
    }

    @Override // com.google.android.gms.internal.auth.zzhk
    public final boolean zzb() {
        return zzj.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.auth.zzhk
    public final boolean zzc() {
        return zzk.zzb().booleanValue();
    }
}
