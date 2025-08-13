package com.qcwireless.qcwatch.ui.device.contact.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.ContactsRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity;
import com.qcwireless.qcwatch.ui.device.contact.bean.ContactBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: ContactViewModel.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u00029:B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u00100\u001a\u0002012\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0014\u00103\u001a\u0002012\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0006\u00104\u001a\u000201J\u0014\u00105\u001a\u0002012\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0006\u00107\u001a\u000201J\u0006\u00108\u001a\u000201R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u0014R \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0012\"\u0004\b!\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R \u0010)\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0012\"\u0004\b+\u0010\u0014R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000b0&8F¢\u0006\u0006\u001a\u0004\b-\u0010(R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\r0&8F¢\u0006\u0006\u001a\u0004\b/\u0010(¨\u0006;"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "contactsRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/ContactsRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/ContactsRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_localUiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel$LocalContactUI;", "_textStatus", "", "_uiState", "Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel$ContactUI;", "addList", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;", "getAddList", "()Ljava/util/List;", "setAddList", "(Ljava/util/List;)V", "contactsBackUpList", "Lcom/qcwireless/qcwatch/ui/device/contact/bean/ContactBean;", "getContactsBackUpList", "setContactsBackUpList", "contactsList", "getContactsList", "setContactsList", "deleteList", "getDeleteList", "setDeleteList", "deviceContacts", "getDeviceContacts", "setDeviceContacts", "localList", "getLocalList", "setLocalList", "localUiState", "Landroidx/lifecycle/LiveData;", "getLocalUiState", "()Landroidx/lifecycle/LiveData;", "tempList", "getTempList", "setTempList", "textStatus", "getTextStatus", "uiState", "getUiState", "addContact", "", "mutableList", "addContactDefaultStatus", "deleteLocalContact", "initData", "list", "queryDeviceContact", "queryLocalContactList", "ContactUI", "LocalContactUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactViewModel extends BaseViewModel {
    private final MutableLiveData<LocalContactUI> _localUiState;
    private final MutableLiveData<Boolean> _textStatus;
    private final MutableLiveData<ContactUI> _uiState;
    private List<ContactsEntity> addList;
    private List<ContactBean> contactsBackUpList;
    private List<ContactBean> contactsList;
    private final ContactsRepository contactsRepository;
    private List<ContactsEntity> deleteList;
    private List<ContactsEntity> deviceContacts;
    private DeviceSettingRepository deviceSettingRepository;
    private List<ContactsEntity> localList;
    private List<ContactsEntity> tempList;

    public ContactViewModel(ContactsRepository contactsRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(contactsRepository, "contactsRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.contactsRepository = contactsRepository;
        this.deviceSettingRepository = deviceSettingRepository;
        this.contactsList = new ArrayList();
        this.contactsBackUpList = new ArrayList();
        this.addList = new ArrayList();
        this.localList = new ArrayList();
        this.deleteList = new ArrayList();
        this.deviceContacts = new ArrayList();
        this.tempList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._localUiState = new MutableLiveData<>();
        this._textStatus = new MutableLiveData<>();
    }

    public final List<ContactBean> getContactsList() {
        return this.contactsList;
    }

    public final void setContactsList(List<ContactBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.contactsList = list;
    }

    public final List<ContactBean> getContactsBackUpList() {
        return this.contactsBackUpList;
    }

    public final void setContactsBackUpList(List<ContactBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.contactsBackUpList = list;
    }

    public final List<ContactsEntity> getAddList() {
        return this.addList;
    }

    public final void setAddList(List<ContactsEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.addList = list;
    }

    public final List<ContactsEntity> getLocalList() {
        return this.localList;
    }

    public final void setLocalList(List<ContactsEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.localList = list;
    }

    public final List<ContactsEntity> getDeleteList() {
        return this.deleteList;
    }

    public final void setDeleteList(List<ContactsEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.deleteList = list;
    }

    public final List<ContactsEntity> getDeviceContacts() {
        return this.deviceContacts;
    }

    public final void setDeviceContacts(List<ContactsEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.deviceContacts = list;
    }

    public final List<ContactsEntity> getTempList() {
        return this.tempList;
    }

    public final void setTempList(List<ContactsEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.tempList = list;
    }

    public final LiveData<ContactUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<LocalContactUI> getLocalUiState() {
        return this._localUiState;
    }

    public final LiveData<Boolean> getTextStatus() {
        return this._textStatus;
    }

    public final void initData(final List<ContactsEntity> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        try {
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<ContactViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel.initData.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ContactViewModel contactViewModel) {
                    invoke2(contactViewModel);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ContactViewModel ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    List<ContactBean> contactsFromPhone = ktxRunOnBgSingle.contactsRepository.getContactsFromPhone(list);
                    ContactUI contactUI = new ContactUI();
                    contactUI.setList(contactsFromPhone);
                    ktxRunOnBgSingle._uiState.postValue(contactUI);
                }
            });
        } catch (Exception unused) {
        }
    }

    public final void deleteLocalContact() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<ContactViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel.deleteLocalContact.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContactViewModel contactViewModel) {
                invoke2(contactViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContactViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.contactsRepository.deleteAllData();
            }
        });
    }

    public final void queryLocalContactList() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<ContactViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel.queryLocalContactList.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContactViewModel contactViewModel) {
                invoke2(contactViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContactViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<ContactsEntity> listQueryAll = ktxRunOnBgSingle.contactsRepository.queryAll();
                LocalContactUI localContactUI = new LocalContactUI();
                localContactUI.setList(listQueryAll);
                XLog.i(Integer.valueOf(listQueryAll.size()));
                ktxRunOnBgSingle._localUiState.postValue(localContactUI);
            }
        });
    }

    public final void addContactDefaultStatus(final List<ContactsEntity> mutableList) {
        Intrinsics.checkNotNullParameter(mutableList, "mutableList");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<ContactViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel.addContactDefaultStatus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContactViewModel contactViewModel) {
                invoke2(contactViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContactViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.contactsRepository.deleteAllData();
                ktxRunOnBgSingle.contactsRepository.addDefaultContact(mutableList);
            }
        });
    }

    public final void addContact(List<ContactsEntity> mutableList) {
        Intrinsics.checkNotNullParameter(mutableList, "mutableList");
        this.deviceContacts.clear();
        this.deviceContacts.addAll(mutableList);
        this.deviceSettingRepository.saveContact(mutableList);
    }

    /* compiled from: ContactViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel$queryDeviceContact$1", f = "ContactViewModel.kt", i = {}, l = {95, 95}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel$queryDeviceContact$1, reason: invalid class name and case insensitive filesystem */
    static final class C05141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05141(Continuation<? super C05141> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContactViewModel.this.new C05141(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = ContactViewModel.this.deviceSettingRepository.getDeviceContact(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final ContactViewModel contactViewModel = ContactViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel.queryDeviceContact.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((List<ContactsEntity>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(List<ContactsEntity> list, Continuation<? super Unit> continuation) {
                    LocalContactUI localContactUI = new LocalContactUI();
                    if (list != null) {
                        localContactUI.setList(list);
                        contactViewModel._textStatus.postValue(Boxing.boxBoolean(true));
                        contactViewModel.getDeviceContacts().clear();
                        contactViewModel.getDeviceContacts().addAll(list);
                        contactViewModel.contactsRepository.addDefaultContact(list);
                    } else {
                        localContactUI.setList(new ArrayList());
                    }
                    XLog.i(Boxing.boxInt(localContactUI.getList().size()));
                    contactViewModel._localUiState.postValue(localContactUI);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryDeviceContact() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05141(null), 3, null);
    }

    /* compiled from: ContactViewModel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel$ContactUI;", "", "()V", "list", "", "Lcom/qcwireless/qcwatch/ui/device/contact/bean/ContactBean;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ContactUI {
        private List<ContactBean> list = new ArrayList();

        public final List<ContactBean> getList() {
            return this.list;
        }

        public final void setList(List<ContactBean> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.list = list;
        }
    }

    /* compiled from: ContactViewModel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel$LocalContactUI;", "", "()V", "list", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LocalContactUI {
        private List<ContactsEntity> list = new ArrayList();

        public final List<ContactsEntity> getList() {
            return this.list;
        }

        public final void setList(List<ContactsEntity> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.list = list;
        }
    }
}
