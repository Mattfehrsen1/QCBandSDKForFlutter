package com.qcwireless.qcwatch.ui.base.api;

import androidx.core.app.NotificationCompat;
import com.oudmon.ble.base.util.AppUtil;
import com.qcwireless.qc_utils.date.LanguageUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* compiled from: QcRetrofitClient.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u000b\u001a\u00020\b¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/api/QcRetrofitClient;", "Lcom/qcwireless/qcwatch/ui/base/api/BaseRetrofitClient;", "()V", "handleBuilder", "", "builder", "Lokhttp3/OkHttpClient$Builder;", "serverSwitching", "Lcom/qcwireless/qcwatch/ui/base/api/QcService;", "hw", "", NotificationCompat.CATEGORY_SERVICE, "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QcRetrofitClient extends BaseRetrofitClient {
    public static final QcRetrofitClient INSTANCE = new QcRetrofitClient();

    private QcRetrofitClient() {
    }

    public final QcService service() {
        if (LanguageUtil.isChina()) {
            if (QCApplication.INSTANCE.getGetInstance().getChinaServer()) {
                return serverSwitching(false);
            }
            return serverSwitching(true);
        }
        if (QCApplication.INSTANCE.getGetInstance().getPingHwServer()) {
            return serverSwitching(true);
        }
        return serverSwitching(false);
    }

    private final QcService serverSwitching(boolean hw) {
        if (hw) {
            return (QcService) getService(QcService.class, "https://api1.qcwxkjvip.com/qcwx/");
        }
        return (QcService) getService(QcService.class, "https://china.qcwxwire.com/qcwx/");
    }

    @Override // com.qcwireless.qcwatch.ui.base.api.BaseRetrofitClient
    protected void handleBuilder(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        try {
            final String appName = AppUtil.getAppName(QJavaApplication.getInstance().getApplication());
            final String versionName = AppUtil.getVersionName(QJavaApplication.getInstance().getApplication());
            builder.addInterceptor(new Interceptor() { // from class: com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient$handleBuilder$$inlined$-addInterceptor$1
                @Override // okhttp3.Interceptor
                public final Response intercept(Interceptor.Chain chain) {
                    Intrinsics.checkNotNullParameter(chain, "chain");
                    return chain.proceed(chain.request().newBuilder().addHeader("token", UserConfig.INSTANCE.getInstance().getUserToken()).addHeader("User-Agent", appName + '/' + versionName).build());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
