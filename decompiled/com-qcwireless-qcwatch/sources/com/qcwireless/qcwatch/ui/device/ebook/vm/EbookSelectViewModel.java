package com.qcwireless.qcwatch.ui.device.ebook.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.base.event.FinishEbookAddFirstEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.EbookRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.EbookEntity;
import com.qcwireless.qcwatch.ui.device.ebook.bean.Ebook;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: EbookSelectViewModel.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u001aR\u0018\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001b\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\u00000\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookSelectViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "ebookRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/EbookRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookSelectViewModel$EbookUi;", "addList", "", "Lcom/qcwireless/qcwatch/ui/device/ebook/bean/Ebook;", "getAddList", "()Ljava/util/List;", "setAddList", "(Ljava/util/List;)V", "songList", "getSongList", "setSongList", "songListBackUp", "getSongListBackUp", "setSongListBackUp", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "addEbooks", "", "bean", "deleteBook", "name", "", "saveBook", "ebookEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/EbookEntity;", "selectEbook", "EbookUi", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EbookSelectViewModel extends BaseViewModel {
    private final MutableLiveData<EbookUi> _uiState;
    private List<Ebook> addList;
    private EbookRepository ebookRepository;
    private List<Ebook> songList;
    private List<Ebook> songListBackUp;

    public EbookSelectViewModel(EbookRepository ebookRepository) {
        Intrinsics.checkNotNullParameter(ebookRepository, "ebookRepository");
        this.ebookRepository = ebookRepository;
        this.songList = new ArrayList();
        this.songListBackUp = new ArrayList();
        this.addList = new ArrayList();
        this._uiState = new MutableLiveData<>();
    }

    public final List<Ebook> getSongList() {
        return this.songList;
    }

    public final void setSongList(List<Ebook> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.songList = list;
    }

    public final List<Ebook> getSongListBackUp() {
        return this.songListBackUp;
    }

    public final void setSongListBackUp(List<Ebook> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.songListBackUp = list;
    }

    public final List<Ebook> getAddList() {
        return this.addList;
    }

    public final void setAddList(List<Ebook> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.addList = list;
    }

    public final LiveData<EbookUi> getUiState() {
        return this._uiState;
    }

    public final void selectEbook() {
        try {
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<EbookSelectViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.vm.EbookSelectViewModel.selectEbook.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(EbookSelectViewModel ebookSelectViewModel) {
                    invoke2(ebookSelectViewModel);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(EbookSelectViewModel ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    List<Ebook> allBooks = ktxRunOnBgSingle.ebookRepository.getAllBooks();
                    XLog.i(Integer.valueOf(allBooks.size()));
                    EbookUi ebookUi = ktxRunOnBgSingle.new EbookUi();
                    ebookUi.setList(TypeIntrinsics.asMutableList(allBooks));
                    ktxRunOnBgSingle._uiState.postValue(ebookUi);
                }
            });
        } catch (Exception unused) {
        }
    }

    public final void addEbooks(final Ebook bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<EbookSelectViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.vm.EbookSelectViewModel.addEbooks.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(EbookSelectViewModel ebookSelectViewModel) {
                invoke2(ebookSelectViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(EbookSelectViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                XLog.i(bean);
                EventBus.getDefault().post(new FinishEbookAddFirstEvent(bean.getName(), bean.getPath()));
            }
        });
    }

    public final void saveBook(EbookEntity ebookEntity) {
        Intrinsics.checkNotNullParameter(ebookEntity, "ebookEntity");
        this.ebookRepository.saveBook(ebookEntity);
    }

    public final void deleteBook(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<EbookSelectViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.vm.EbookSelectViewModel.deleteBook.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(EbookSelectViewModel ebookSelectViewModel) {
                invoke2(ebookSelectViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(EbookSelectViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.ebookRepository.deleteBook(name);
            }
        });
    }

    /* compiled from: EbookSelectViewModel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookSelectViewModel$EbookUi;", "", "(Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookSelectViewModel;)V", "list", "", "Lcom/qcwireless/qcwatch/ui/device/ebook/bean/Ebook;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class EbookUi {
        private List<Ebook> list = new ArrayList();

        public EbookUi() {
        }

        public final List<Ebook> getList() {
            return this.list;
        }

        public final void setList(List<Ebook> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.list = list;
        }
    }
}
