package com.qcwireless.qcwatch.ui.base.api;

import androidx.core.app.NotificationCompat;
import com.oudmon.ble.base.util.AppUtil;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* compiled from: QcRetrofitChinaClient.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/api/QcRetrofitChinaClient;", "Lcom/qcwireless/qcwatch/ui/base/api/BaseRetrofitClient;", "()V", NotificationCompat.CATEGORY_SERVICE, "Lcom/qcwireless/qcwatch/ui/base/api/QcService;", "getService", "()Lcom/qcwireless/qcwatch/ui/base/api/QcService;", "service$delegate", "Lkotlin/Lazy;", "handleBuilder", "", "builder", "Lokhttp3/OkHttpClient$Builder;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QcRetrofitChinaClient extends BaseRetrofitClient {
    public static final QcRetrofitChinaClient INSTANCE = new QcRetrofitChinaClient();

    /* renamed from: service$delegate, reason: from kotlin metadata */
    private static final Lazy service = LazyKt.lazy(new Function0<QcService>() { // from class: com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient$service$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final QcService invoke() {
            return (QcService) QcRetrofitChinaClient.INSTANCE.getService(QcService.class, "https://china.qcwxwire.com/qcwx/");
        }
    });

    private QcRetrofitChinaClient() {
    }

    public final QcService getService() {
        return (QcService) service.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.api.BaseRetrofitClient
    protected void handleBuilder(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        try {
            final String appName = AppUtil.getAppName(QJavaApplication.getInstance().getApplication());
            final String versionName = AppUtil.getVersionName(QJavaApplication.getInstance().getApplication());
            builder.addInterceptor(new Interceptor() { // from class: com.qcwireless.qcwatch.ui.base.api.QcRetrofitChinaClient$handleBuilder$$inlined$-addInterceptor$1
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
