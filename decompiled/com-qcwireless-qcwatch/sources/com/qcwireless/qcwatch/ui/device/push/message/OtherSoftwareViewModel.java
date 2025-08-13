package com.qcwireless.qcwatch.ui.device.push.message;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.MessagePushEntity;
import com.qcwireless.qcwatch.ui.device.push.bean.SoftwarePush;
import com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OtherSoftwareViewModel.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\nR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/OtherSoftwareViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "mpRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushViewModel$MessagePushUI;", "softList", "", "Lcom/qcwireless/qcwatch/ui/device/push/bean/SoftwarePush;", "getSoftList", "()Ljava/util/List;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "queryOpenSoftWare", "", "activity", "Landroid/app/Activity;", "saveBean", "entity", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OtherSoftwareViewModel extends BaseViewModel {
    private final MutableLiveData<MessagePushViewModel.MessagePushUI> _uiState;
    private MessagePushRepository mpRepository;
    private final List<SoftwarePush> softList;

    public OtherSoftwareViewModel(MessagePushRepository mpRepository) {
        Intrinsics.checkNotNullParameter(mpRepository, "mpRepository");
        this.mpRepository = mpRepository;
        this.softList = new ArrayList();
        this._uiState = new MutableLiveData<>();
    }

    public final List<SoftwarePush> getSoftList() {
        return this.softList;
    }

    public final LiveData<MessagePushViewModel.MessagePushUI> getUiState() {
        return this._uiState;
    }

    public final void queryOpenSoftWare(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<OtherSoftwareViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.OtherSoftwareViewModel.queryOpenSoftWare.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OtherSoftwareViewModel otherSoftwareViewModel) {
                invoke2(otherSoftwareViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OtherSoftwareViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                PackageManager packageManager = activity.getPackageManager();
                Intrinsics.checkNotNullExpressionValue(packageManager, "activity.packageManager");
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "packageManager.queryIntentActivities(intent, 0)");
                for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                    String string = resolveInfo.activityInfo.loadLabel(packageManager).toString();
                    String packageName = resolveInfo.activityInfo.packageName;
                    Drawable icon = resolveInfo.activityInfo.loadIcon(packageManager);
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    Intrinsics.checkNotNullExpressionValue(icon, "icon");
                    arrayList2.add(new SoftwarePush(string, packageName, false, icon));
                }
                Iterator it = arrayList2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        SoftwarePush softwarePush = (SoftwarePush) it.next();
                        MessagePushEntity messagePushEntityQueryByPackageName = ktxRunOnBgSingle.mpRepository.queryByPackageName(softwarePush.getPackageName());
                        if (messagePushEntityQueryByPackageName == null) {
                            arrayList.add(new SoftwarePush(softwarePush.getName(), softwarePush.getPackageName(), false, softwarePush.getIcon()));
                        } else if (messagePushEntityQueryByPackageName.getOpen() == 3) {
                            arrayList.add(new SoftwarePush(softwarePush.getName(), softwarePush.getPackageName(), messagePushEntityQueryByPackageName.getOpen() == 3, softwarePush.getIcon()));
                        }
                    } else {
                        ktxRunOnBgSingle._uiState.postValue(new MessagePushViewModel.MessagePushUI(true, arrayList));
                        return;
                    }
                }
            }
        });
    }

    public final void saveBean(final SoftwarePush entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<OtherSoftwareViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.OtherSoftwareViewModel.saveBean.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OtherSoftwareViewModel otherSoftwareViewModel) {
                invoke2(otherSoftwareViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OtherSoftwareViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (entity.getSwitch()) {
                    ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(entity.getPackageName(), 3));
                } else {
                    ktxRunOnBgSingle.mpRepository.deleteByPackageName(entity.getPackageName());
                }
            }
        });
    }
}
