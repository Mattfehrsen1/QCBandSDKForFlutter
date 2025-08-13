package com.qcwireless.qcwatch.base.di;

import com.qcwireless.qcwatch.ui.base.repository.device.ContactsRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceBindRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.EbookRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.MusicRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.OTARepository;
import com.qcwireless.qcwatch.ui.base.repository.gps.GpsRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodOxygenRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.BloodSugarRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HealthyRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HeartRateDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.MenstruationRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.OneKeyCheckRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.PressureRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SleepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepHistoryRepository;
import com.qcwireless.qcwatch.ui.base.repository.healthy.TemperatureRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.FeedbackRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.RegisterRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository;
import com.qcwireless.qcwatch.ui.device.connect.DeviceBindViewModel;
import com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel;
import com.qcwireless.qcwatch.ui.device.dfu.vm.DeviceFirmwareUpdateViewModel;
import com.qcwireless.qcwatch.ui.device.dfu.vm.WatchFileDismissViewModel;
import com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel;
import com.qcwireless.qcwatch.ui.device.ebook.vm.EbookManagerViewModel;
import com.qcwireless.qcwatch.ui.device.ebook.vm.EbookSelectViewModel;
import com.qcwireless.qcwatch.ui.device.heart.HeartDetectionViewModel;
import com.qcwireless.qcwatch.ui.device.more.MoreSettingViewModel;
import com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel;
import com.qcwireless.qcwatch.ui.device.music.vm.MusicSelectViewModel;
import com.qcwireless.qcwatch.ui.device.music.vm.MyMusicListViewModel;
import com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel;
import com.qcwireless.qcwatch.ui.device.push.message.OtherSoftwareViewModel;
import com.qcwireless.qcwatch.ui.device.remind.AiReminderViewModel;
import com.qcwireless.qcwatch.ui.device.remind.drink.DrinkViewModel;
import com.qcwireless.qcwatch.ui.device.remind.longsit.LongSitViewModel;
import com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel;
import com.qcwireless.qcwatch.ui.device.theme.ThemeListViewModel;
import com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel;
import com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel;
import com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel;
import com.qcwireless.qcwatch.ui.home.bloodsugar.BloodSugarActivityViewModel;
import com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel;
import com.qcwireless.qcwatch.ui.home.gps.day.DayGpsFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.gps.vm.GPSActivityViewModel;
import com.qcwireless.qcwatch.ui.home.gps.vm.GPSRunActivityViewModel;
import com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel;
import com.qcwireless.qcwatch.ui.home.heart.HrvActivityViewModel;
import com.qcwireless.qcwatch.ui.home.menstruation.MenstruationViewModel;
import com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel;
import com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.pressure.month.MonthPressureFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.pressure.week.WeekPressureFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.sleep.day.DaySleepFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.sleep.month.MonthSleepFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.sleep.week.WeekSleepFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.sport.day.DaySportFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.sport.month.MonthSportFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.sport.vm.SportDetailViewModel;
import com.qcwireless.qcwatch.ui.home.sport.week.WeekSportViewModel;
import com.qcwireless.qcwatch.ui.home.step.day.DayStepFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.step.month.MonthStepFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.step.week.WeekStepFragmentViewModel;
import com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel;
import com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel;
import com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel;
import com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel;
import com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel;
import com.qcwireless.qcwatch.ui.mine.login.LoginViewModel;
import com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel;
import com.qcwireless.qcwatch.ui.mine.skin.SkinSelectViewModel;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel;
import com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel;
import com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel;
import com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel;
import com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragmentViewModel;
import com.qcwireless.qcwatch.ui.plate.vm.PlateFragmentViewModel;
import com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel;
import com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel;
import com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperTypeListViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.dsl.ModuleExtKt;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.definition.Options;
import org.koin.core.module.Module;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleKt;

/* compiled from: KoinModule.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"appModule", "", "Lorg/koin/core/module/Module;", "getAppModule", "()Ljava/util/List;", "repositoryModule", "getRepositoryModule", "()Lorg/koin/core/module/Module;", "viewModelModule", "getViewModelModule", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KoinModuleKt {
    private static final List<Module> appModule;
    private static final Module repositoryModule;
    private static final Module viewModelModule;

    static {
        Module moduleModule$default = ModuleKt.module$default(false, false, new Function1<Module, Unit>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Module module) {
                invoke2(module);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Module module) {
                Intrinsics.checkNotNullParameter(module, "$this$module");
                AnonymousClass1 anonymousClass1 = new Function2<Scope, DefinitionParameters, DeviceBindViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.1
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceBindViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceBindViewModel((DeviceBindRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceBindRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
                Kind kind = Kind.Factory;
                BeanDefinition beanDefinition = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceBindViewModel.class));
                beanDefinition.setDefinition(anonymousClass1);
                beanDefinition.setKind(kind);
                module.declareDefinition(beanDefinition, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition);
                AnonymousClass2 anonymousClass2 = new Function2<Scope, DefinitionParameters, LoginViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.2
                    @Override // kotlin.jvm.functions.Function2
                    public final LoginViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new LoginViewModel((LoginRepository) viewModel.get(Reflection.getOrCreateKotlinClass(LoginRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (UserProfileRepository) viewModel.get(Reflection.getOrCreateKotlinClass(UserProfileRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
                Kind kind2 = Kind.Factory;
                BeanDefinition beanDefinition2 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(LoginViewModel.class));
                beanDefinition2.setDefinition(anonymousClass2);
                beanDefinition2.setKind(kind2);
                module.declareDefinition(beanDefinition2, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition2);
                AnonymousClass3 anonymousClass3 = new Function2<Scope, DefinitionParameters, HeartDetectionViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.3
                    @Override // kotlin.jvm.functions.Function2
                    public final HeartDetectionViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HeartDetectionViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory3 = DefinitionFactory.INSTANCE;
                Kind kind3 = Kind.Factory;
                BeanDefinition beanDefinition3 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HeartDetectionViewModel.class));
                beanDefinition3.setDefinition(anonymousClass3);
                beanDefinition3.setKind(kind3);
                module.declareDefinition(beanDefinition3, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition3);
                AnonymousClass4 anonymousClass4 = new Function2<Scope, DefinitionParameters, LongSitViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.4
                    @Override // kotlin.jvm.functions.Function2
                    public final LongSitViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new LongSitViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory4 = DefinitionFactory.INSTANCE;
                Kind kind4 = Kind.Factory;
                BeanDefinition beanDefinition4 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(LongSitViewModel.class));
                beanDefinition4.setDefinition(anonymousClass4);
                beanDefinition4.setKind(kind4);
                module.declareDefinition(beanDefinition4, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition4);
                AnonymousClass5 anonymousClass5 = new Function2<Scope, DefinitionParameters, RegisterViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.5
                    @Override // kotlin.jvm.functions.Function2
                    public final RegisterViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new RegisterViewModel((RegisterRepository) viewModel.get(Reflection.getOrCreateKotlinClass(RegisterRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (UserProfileRepository) viewModel.get(Reflection.getOrCreateKotlinClass(UserProfileRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory5 = DefinitionFactory.INSTANCE;
                Kind kind5 = Kind.Factory;
                BeanDefinition beanDefinition5 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(RegisterViewModel.class));
                beanDefinition5.setDefinition(anonymousClass5);
                beanDefinition5.setKind(kind5);
                module.declareDefinition(beanDefinition5, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition5);
                AnonymousClass6 anonymousClass6 = new Function2<Scope, DefinitionParameters, HealthyViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.6
                    @Override // kotlin.jvm.functions.Function2
                    public final HealthyViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HealthyViewModel((HealthyRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HealthyRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (StepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(StepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (SleepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SleepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (HeartRateDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HeartRateDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (SportPlusRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SportPlusRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (BloodPressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodPressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (BloodOxygenRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodOxygenRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (TemperatureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(TemperatureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (BloodSugarRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodSugarRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (PressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(PressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (HRVRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HRVRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory6 = DefinitionFactory.INSTANCE;
                Kind kind6 = Kind.Factory;
                BeanDefinition beanDefinition6 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HealthyViewModel.class));
                beanDefinition6.setDefinition(anonymousClass6);
                beanDefinition6.setKind(kind6);
                module.declareDefinition(beanDefinition6, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition6);
                AnonymousClass7 anonymousClass7 = new Function2<Scope, DefinitionParameters, DeviceViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.7
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory7 = DefinitionFactory.INSTANCE;
                Kind kind7 = Kind.Factory;
                BeanDefinition beanDefinition7 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceViewModel.class));
                beanDefinition7.setDefinition(anonymousClass7);
                beanDefinition7.setKind(kind7);
                module.declareDefinition(beanDefinition7, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition7);
                AnonymousClass8 anonymousClass8 = new Function2<Scope, DefinitionParameters, DisturbViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.8
                    @Override // kotlin.jvm.functions.Function2
                    public final DisturbViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DisturbViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory8 = DefinitionFactory.INSTANCE;
                Kind kind8 = Kind.Factory;
                BeanDefinition beanDefinition8 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DisturbViewModel.class));
                beanDefinition8.setDefinition(anonymousClass8);
                beanDefinition8.setKind(kind8);
                module.declareDefinition(beanDefinition8, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition8);
                AnonymousClass9 anonymousClass9 = new Function2<Scope, DefinitionParameters, DeviceSettingViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.9
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceSettingViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceSettingViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory9 = DefinitionFactory.INSTANCE;
                Kind kind9 = Kind.Factory;
                BeanDefinition beanDefinition9 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceSettingViewModel.class));
                beanDefinition9.setDefinition(anonymousClass9);
                beanDefinition9.setKind(kind9);
                module.declareDefinition(beanDefinition9, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition9);
                AnonymousClass10 anonymousClass10 = new Function2<Scope, DefinitionParameters, MessagePushViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.10
                    @Override // kotlin.jvm.functions.Function2
                    public final MessagePushViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MessagePushViewModel((MessagePushRepository) viewModel.get(Reflection.getOrCreateKotlinClass(MessagePushRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory10 = DefinitionFactory.INSTANCE;
                Kind kind10 = Kind.Factory;
                BeanDefinition beanDefinition10 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MessagePushViewModel.class));
                beanDefinition10.setDefinition(anonymousClass10);
                beanDefinition10.setKind(kind10);
                module.declareDefinition(beanDefinition10, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition10);
                AnonymousClass11 anonymousClass11 = new Function2<Scope, DefinitionParameters, OtherSoftwareViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.11
                    @Override // kotlin.jvm.functions.Function2
                    public final OtherSoftwareViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new OtherSoftwareViewModel((MessagePushRepository) viewModel.get(Reflection.getOrCreateKotlinClass(MessagePushRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory11 = DefinitionFactory.INSTANCE;
                Kind kind11 = Kind.Factory;
                BeanDefinition beanDefinition11 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(OtherSoftwareViewModel.class));
                beanDefinition11.setDefinition(anonymousClass11);
                beanDefinition11.setKind(kind11);
                module.declareDefinition(beanDefinition11, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition11);
                AnonymousClass12 anonymousClass12 = new Function2<Scope, DefinitionParameters, AiReminderViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.12
                    @Override // kotlin.jvm.functions.Function2
                    public final AiReminderViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AiReminderViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory12 = DefinitionFactory.INSTANCE;
                Kind kind12 = Kind.Factory;
                BeanDefinition beanDefinition12 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AiReminderViewModel.class));
                beanDefinition12.setDefinition(anonymousClass12);
                beanDefinition12.setKind(kind12);
                module.declareDefinition(beanDefinition12, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition12);
                AnonymousClass13 anonymousClass13 = new Function2<Scope, DefinitionParameters, MoreSettingViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.13
                    @Override // kotlin.jvm.functions.Function2
                    public final MoreSettingViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MoreSettingViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory13 = DefinitionFactory.INSTANCE;
                Kind kind13 = Kind.Factory;
                BeanDefinition beanDefinition13 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MoreSettingViewModel.class));
                beanDefinition13.setDefinition(anonymousClass13);
                beanDefinition13.setKind(kind13);
                module.declareDefinition(beanDefinition13, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition13);
                AnonymousClass14 anonymousClass14 = new Function2<Scope, DefinitionParameters, DrinkViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.14
                    @Override // kotlin.jvm.functions.Function2
                    public final DrinkViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DrinkViewModel((DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory14 = DefinitionFactory.INSTANCE;
                Kind kind14 = Kind.Factory;
                BeanDefinition beanDefinition14 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DrinkViewModel.class));
                beanDefinition14.setDefinition(anonymousClass14);
                beanDefinition14.setKind(kind14);
                module.declareDefinition(beanDefinition14, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition14);
                AnonymousClass15 anonymousClass15 = new Function2<Scope, DefinitionParameters, WeekSportViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.15
                    @Override // kotlin.jvm.functions.Function2
                    public final WeekSportViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WeekSportViewModel((SportPlusRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SportPlusRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory15 = DefinitionFactory.INSTANCE;
                Kind kind15 = Kind.Factory;
                BeanDefinition beanDefinition15 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WeekSportViewModel.class));
                beanDefinition15.setDefinition(anonymousClass15);
                beanDefinition15.setKind(kind15);
                module.declareDefinition(beanDefinition15, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition15);
                AnonymousClass16 anonymousClass16 = new Function2<Scope, DefinitionParameters, UserProfileViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.16
                    @Override // kotlin.jvm.functions.Function2
                    public final UserProfileViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new UserProfileViewModel((UserProfileRepository) viewModel.get(Reflection.getOrCreateKotlinClass(UserProfileRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory16 = DefinitionFactory.INSTANCE;
                Kind kind16 = Kind.Factory;
                BeanDefinition beanDefinition16 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(UserProfileViewModel.class));
                beanDefinition16.setDefinition(anonymousClass16);
                beanDefinition16.setKind(kind16);
                module.declareDefinition(beanDefinition16, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition16);
                AnonymousClass17 anonymousClass17 = new Function2<Scope, DefinitionParameters, PlateFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.17
                    @Override // kotlin.jvm.functions.Function2
                    public final PlateFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new PlateFragmentViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory17 = DefinitionFactory.INSTANCE;
                Kind kind17 = Kind.Factory;
                BeanDefinition beanDefinition17 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(PlateFragmentViewModel.class));
                beanDefinition17.setDefinition(anonymousClass17);
                beanDefinition17.setKind(kind17);
                module.declareDefinition(beanDefinition17, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition17);
                AnonymousClass18 anonymousClass18 = new Function2<Scope, DefinitionParameters, DayStepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.18
                    @Override // kotlin.jvm.functions.Function2
                    public final DayStepFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DayStepFragmentViewModel((StepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(StepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory18 = DefinitionFactory.INSTANCE;
                Kind kind18 = Kind.Factory;
                BeanDefinition beanDefinition18 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DayStepFragmentViewModel.class));
                beanDefinition18.setDefinition(anonymousClass18);
                beanDefinition18.setKind(kind18);
                module.declareDefinition(beanDefinition18, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition18);
                AnonymousClass19 anonymousClass19 = new Function2<Scope, DefinitionParameters, WeekStepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.19
                    @Override // kotlin.jvm.functions.Function2
                    public final WeekStepFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WeekStepFragmentViewModel((StepHistoryRepository) viewModel.get(Reflection.getOrCreateKotlinClass(StepHistoryRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory19 = DefinitionFactory.INSTANCE;
                Kind kind19 = Kind.Factory;
                BeanDefinition beanDefinition19 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WeekStepFragmentViewModel.class));
                beanDefinition19.setDefinition(anonymousClass19);
                beanDefinition19.setKind(kind19);
                module.declareDefinition(beanDefinition19, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition19);
                AnonymousClass20 anonymousClass20 = new Function2<Scope, DefinitionParameters, MonthStepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.20
                    @Override // kotlin.jvm.functions.Function2
                    public final MonthStepFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MonthStepFragmentViewModel((StepHistoryRepository) viewModel.get(Reflection.getOrCreateKotlinClass(StepHistoryRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory20 = DefinitionFactory.INSTANCE;
                Kind kind20 = Kind.Factory;
                BeanDefinition beanDefinition20 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MonthStepFragmentViewModel.class));
                beanDefinition20.setDefinition(anonymousClass20);
                beanDefinition20.setKind(kind20);
                module.declareDefinition(beanDefinition20, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition20);
                AnonymousClass21 anonymousClass21 = new Function2<Scope, DefinitionParameters, DaySleepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.21
                    @Override // kotlin.jvm.functions.Function2
                    public final DaySleepFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DaySleepFragmentViewModel((SleepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SleepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory21 = DefinitionFactory.INSTANCE;
                Kind kind21 = Kind.Factory;
                BeanDefinition beanDefinition21 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DaySleepFragmentViewModel.class));
                beanDefinition21.setDefinition(anonymousClass21);
                beanDefinition21.setKind(kind21);
                module.declareDefinition(beanDefinition21, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition21);
                AnonymousClass22 anonymousClass22 = new Function2<Scope, DefinitionParameters, WeekSleepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.22
                    @Override // kotlin.jvm.functions.Function2
                    public final WeekSleepFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WeekSleepFragmentViewModel((SleepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SleepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory22 = DefinitionFactory.INSTANCE;
                Kind kind22 = Kind.Factory;
                BeanDefinition beanDefinition22 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WeekSleepFragmentViewModel.class));
                beanDefinition22.setDefinition(anonymousClass22);
                beanDefinition22.setKind(kind22);
                module.declareDefinition(beanDefinition22, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition22);
                AnonymousClass23 anonymousClass23 = new Function2<Scope, DefinitionParameters, MonthSleepFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.23
                    @Override // kotlin.jvm.functions.Function2
                    public final MonthSleepFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MonthSleepFragmentViewModel((SleepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SleepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory23 = DefinitionFactory.INSTANCE;
                Kind kind23 = Kind.Factory;
                BeanDefinition beanDefinition23 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MonthSleepFragmentViewModel.class));
                beanDefinition23.setDefinition(anonymousClass23);
                beanDefinition23.setKind(kind23);
                module.declareDefinition(beanDefinition23, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition23);
                AnonymousClass24 anonymousClass24 = new Function2<Scope, DefinitionParameters, HeartActivityViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.24
                    @Override // kotlin.jvm.functions.Function2
                    public final HeartActivityViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HeartActivityViewModel((HeartRateDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HeartRateDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (UserProfileRepository) viewModel.get(Reflection.getOrCreateKotlinClass(UserProfileRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory24 = DefinitionFactory.INSTANCE;
                Kind kind24 = Kind.Factory;
                BeanDefinition beanDefinition24 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HeartActivityViewModel.class));
                beanDefinition24.setDefinition(anonymousClass24);
                beanDefinition24.setKind(kind24);
                module.declareDefinition(beanDefinition24, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition24);
                AnonymousClass25 anonymousClass25 = new Function2<Scope, DefinitionParameters, MonthSportFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.25
                    @Override // kotlin.jvm.functions.Function2
                    public final MonthSportFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MonthSportFragmentViewModel((SportPlusRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SportPlusRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory25 = DefinitionFactory.INSTANCE;
                Kind kind25 = Kind.Factory;
                BeanDefinition beanDefinition25 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MonthSportFragmentViewModel.class));
                beanDefinition25.setDefinition(anonymousClass25);
                beanDefinition25.setKind(kind25);
                module.declareDefinition(beanDefinition25, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition25);
                AnonymousClass26 anonymousClass26 = new Function2<Scope, DefinitionParameters, DaySportFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.26
                    @Override // kotlin.jvm.functions.Function2
                    public final DaySportFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DaySportFragmentViewModel((SportPlusRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SportPlusRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory26 = DefinitionFactory.INSTANCE;
                Kind kind26 = Kind.Factory;
                BeanDefinition beanDefinition26 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DaySportFragmentViewModel.class));
                beanDefinition26.setDefinition(anonymousClass26);
                beanDefinition26.setKind(kind26);
                module.declareDefinition(beanDefinition26, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition26);
                AnonymousClass27 anonymousClass27 = new Function2<Scope, DefinitionParameters, GoalSettingViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.27
                    @Override // kotlin.jvm.functions.Function2
                    public final GoalSettingViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new GoalSettingViewModel((UserProfileRepository) viewModel.get(Reflection.getOrCreateKotlinClass(UserProfileRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory27 = DefinitionFactory.INSTANCE;
                Kind kind27 = Kind.Factory;
                BeanDefinition beanDefinition27 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(GoalSettingViewModel.class));
                beanDefinition27.setDefinition(anonymousClass27);
                beanDefinition27.setKind(kind27);
                module.declareDefinition(beanDefinition27, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition27);
                AnonymousClass28 anonymousClass28 = new Function2<Scope, DefinitionParameters, MenstruationViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.28
                    @Override // kotlin.jvm.functions.Function2
                    public final MenstruationViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MenstruationViewModel((MenstruationRepository) viewModel.get(Reflection.getOrCreateKotlinClass(MenstruationRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory28 = DefinitionFactory.INSTANCE;
                Kind kind28 = Kind.Factory;
                BeanDefinition beanDefinition28 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MenstruationViewModel.class));
                beanDefinition28.setDefinition(anonymousClass28);
                beanDefinition28.setKind(kind28);
                module.declareDefinition(beanDefinition28, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition28);
                AnonymousClass29 anonymousClass29 = new Function2<Scope, DefinitionParameters, ForgetPwdViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.29
                    @Override // kotlin.jvm.functions.Function2
                    public final ForgetPwdViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new ForgetPwdViewModel((FindPwdRepository) viewModel.get(Reflection.getOrCreateKotlinClass(FindPwdRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory29 = DefinitionFactory.INSTANCE;
                Kind kind29 = Kind.Factory;
                BeanDefinition beanDefinition29 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(ForgetPwdViewModel.class));
                beanDefinition29.setDefinition(anonymousClass29);
                beanDefinition29.setKind(kind29);
                module.declareDefinition(beanDefinition29, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition29);
                AnonymousClass30 anonymousClass30 = new Function2<Scope, DefinitionParameters, WatchFileDismissViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.30
                    @Override // kotlin.jvm.functions.Function2
                    public final WatchFileDismissViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WatchFileDismissViewModel((OTARepository) viewModel.get(Reflection.getOrCreateKotlinClass(OTARepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory30 = DefinitionFactory.INSTANCE;
                Kind kind30 = Kind.Factory;
                BeanDefinition beanDefinition30 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WatchFileDismissViewModel.class));
                beanDefinition30.setDefinition(anonymousClass30);
                beanDefinition30.setKind(kind30);
                module.declareDefinition(beanDefinition30, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition30);
                AnonymousClass31 anonymousClass31 = new Function2<Scope, DefinitionParameters, DeviceFirmwareUpdateViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.31
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceFirmwareUpdateViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceFirmwareUpdateViewModel((OTARepository) viewModel.get(Reflection.getOrCreateKotlinClass(OTARepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory31 = DefinitionFactory.INSTANCE;
                Kind kind31 = Kind.Factory;
                BeanDefinition beanDefinition31 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceFirmwareUpdateViewModel.class));
                beanDefinition31.setDefinition(anonymousClass31);
                beanDefinition31.setKind(kind31);
                module.declareDefinition(beanDefinition31, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition31);
                AnonymousClass32 anonymousClass32 = new Function2<Scope, DefinitionParameters, DiyWatchFaceViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.32
                    @Override // kotlin.jvm.functions.Function2
                    public final DiyWatchFaceViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DiyWatchFaceViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory32 = DefinitionFactory.INSTANCE;
                Kind kind32 = Kind.Factory;
                BeanDefinition beanDefinition32 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DiyWatchFaceViewModel.class));
                beanDefinition32.setDefinition(anonymousClass32);
                beanDefinition32.setKind(kind32);
                module.declareDefinition(beanDefinition32, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition32);
                AnonymousClass33 anonymousClass33 = new Function2<Scope, DefinitionParameters, GPSRunActivityViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.33
                    @Override // kotlin.jvm.functions.Function2
                    public final GPSRunActivityViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new GPSRunActivityViewModel((GpsRepository) viewModel.get(Reflection.getOrCreateKotlinClass(GpsRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory33 = DefinitionFactory.INSTANCE;
                Kind kind33 = Kind.Factory;
                BeanDefinition beanDefinition33 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(GPSRunActivityViewModel.class));
                beanDefinition33.setDefinition(anonymousClass33);
                beanDefinition33.setKind(kind33);
                module.declareDefinition(beanDefinition33, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition33);
                AnonymousClass34 anonymousClass34 = new Function2<Scope, DefinitionParameters, DayGpsFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.34
                    @Override // kotlin.jvm.functions.Function2
                    public final DayGpsFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DayGpsFragmentViewModel((GpsRepository) viewModel.get(Reflection.getOrCreateKotlinClass(GpsRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory34 = DefinitionFactory.INSTANCE;
                Kind kind34 = Kind.Factory;
                BeanDefinition beanDefinition34 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DayGpsFragmentViewModel.class));
                beanDefinition34.setDefinition(anonymousClass34);
                beanDefinition34.setKind(kind34);
                module.declareDefinition(beanDefinition34, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition34);
                AnonymousClass35 anonymousClass35 = new Function2<Scope, DefinitionParameters, GPSActivityViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.35
                    @Override // kotlin.jvm.functions.Function2
                    public final GPSActivityViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new GPSActivityViewModel((GpsRepository) viewModel.get(Reflection.getOrCreateKotlinClass(GpsRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory35 = DefinitionFactory.INSTANCE;
                Kind kind35 = Kind.Factory;
                BeanDefinition beanDefinition35 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(GPSActivityViewModel.class));
                beanDefinition35.setDefinition(anonymousClass35);
                beanDefinition35.setKind(kind35);
                module.declareDefinition(beanDefinition35, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition35);
                AnonymousClass36 anonymousClass36 = new Function2<Scope, DefinitionParameters, SkinSelectViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.36
                    @Override // kotlin.jvm.functions.Function2
                    public final SkinSelectViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new SkinSelectViewModel();
                    }
                };
                DefinitionFactory definitionFactory36 = DefinitionFactory.INSTANCE;
                Kind kind36 = Kind.Factory;
                BeanDefinition beanDefinition36 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(SkinSelectViewModel.class));
                beanDefinition36.setDefinition(anonymousClass36);
                beanDefinition36.setKind(kind36);
                module.declareDefinition(beanDefinition36, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition36);
                AnonymousClass37 anonymousClass37 = new Function2<Scope, DefinitionParameters, BloodPressureViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.37
                    @Override // kotlin.jvm.functions.Function2
                    public final BloodPressureViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new BloodPressureViewModel((BloodPressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodPressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory37 = DefinitionFactory.INSTANCE;
                Kind kind37 = Kind.Factory;
                BeanDefinition beanDefinition37 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(BloodPressureViewModel.class));
                beanDefinition37.setDefinition(anonymousClass37);
                beanDefinition37.setKind(kind37);
                module.declareDefinition(beanDefinition37, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition37);
                AnonymousClass38 anonymousClass38 = new Function2<Scope, DefinitionParameters, BloodOxygenViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.38
                    @Override // kotlin.jvm.functions.Function2
                    public final BloodOxygenViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new BloodOxygenViewModel((BloodOxygenRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodOxygenRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory38 = DefinitionFactory.INSTANCE;
                Kind kind38 = Kind.Factory;
                BeanDefinition beanDefinition38 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(BloodOxygenViewModel.class));
                beanDefinition38.setDefinition(anonymousClass38);
                beanDefinition38.setKind(kind38);
                module.declareDefinition(beanDefinition38, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition38);
                AnonymousClass39 anonymousClass39 = new Function2<Scope, DefinitionParameters, OneKeyCheckViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.39
                    @Override // kotlin.jvm.functions.Function2
                    public final OneKeyCheckViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new OneKeyCheckViewModel((OneKeyCheckRepository) viewModel.get(Reflection.getOrCreateKotlinClass(OneKeyCheckRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory39 = DefinitionFactory.INSTANCE;
                Kind kind39 = Kind.Factory;
                BeanDefinition beanDefinition39 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(OneKeyCheckViewModel.class));
                beanDefinition39.setDefinition(anonymousClass39);
                beanDefinition39.setKind(kind39);
                module.declareDefinition(beanDefinition39, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition39);
                AnonymousClass40 anonymousClass40 = new Function2<Scope, DefinitionParameters, FeedbackViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.40
                    @Override // kotlin.jvm.functions.Function2
                    public final FeedbackViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new FeedbackViewModel((FeedbackRepository) viewModel.get(Reflection.getOrCreateKotlinClass(FeedbackRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory40 = DefinitionFactory.INSTANCE;
                Kind kind40 = Kind.Factory;
                BeanDefinition beanDefinition40 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(FeedbackViewModel.class));
                beanDefinition40.setDefinition(anonymousClass40);
                beanDefinition40.setKind(kind40);
                module.declareDefinition(beanDefinition40, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition40);
                AnonymousClass41 anonymousClass41 = new Function2<Scope, DefinitionParameters, TemperatureViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.41
                    @Override // kotlin.jvm.functions.Function2
                    public final TemperatureViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new TemperatureViewModel((TemperatureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(TemperatureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory41 = DefinitionFactory.INSTANCE;
                Kind kind41 = Kind.Factory;
                BeanDefinition beanDefinition41 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(TemperatureViewModel.class));
                beanDefinition41.setDefinition(anonymousClass41);
                beanDefinition41.setKind(kind41);
                module.declareDefinition(beanDefinition41, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition41);
                AnonymousClass42 anonymousClass42 = new Function2<Scope, DefinitionParameters, FirstProfileViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.42
                    @Override // kotlin.jvm.functions.Function2
                    public final FirstProfileViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new FirstProfileViewModel((UserProfileRepository) viewModel.get(Reflection.getOrCreateKotlinClass(UserProfileRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory42 = DefinitionFactory.INSTANCE;
                Kind kind42 = Kind.Factory;
                BeanDefinition beanDefinition42 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(FirstProfileViewModel.class));
                beanDefinition42.setDefinition(anonymousClass42);
                beanDefinition42.setKind(kind42);
                module.declareDefinition(beanDefinition42, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition42);
                AnonymousClass43 anonymousClass43 = new Function2<Scope, DefinitionParameters, SportDetailViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.43
                    @Override // kotlin.jvm.functions.Function2
                    public final SportDetailViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new SportDetailViewModel((SportPlusRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SportPlusRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (UserProfileRepository) viewModel.get(Reflection.getOrCreateKotlinClass(UserProfileRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory43 = DefinitionFactory.INSTANCE;
                Kind kind43 = Kind.Factory;
                BeanDefinition beanDefinition43 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(SportDetailViewModel.class));
                beanDefinition43.setDefinition(anonymousClass43);
                beanDefinition43.setKind(kind43);
                module.declareDefinition(beanDefinition43, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition43);
                AnonymousClass44 anonymousClass44 = new Function2<Scope, DefinitionParameters, ContactViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.44
                    @Override // kotlin.jvm.functions.Function2
                    public final ContactViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new ContactViewModel((ContactsRepository) viewModel.get(Reflection.getOrCreateKotlinClass(ContactsRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (DeviceSettingRepository) viewModel.get(Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory44 = DefinitionFactory.INSTANCE;
                Kind kind44 = Kind.Factory;
                BeanDefinition beanDefinition44 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(ContactViewModel.class));
                beanDefinition44.setDefinition(anonymousClass44);
                beanDefinition44.setKind(kind44);
                module.declareDefinition(beanDefinition44, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition44);
                AnonymousClass45 anonymousClass45 = new Function2<Scope, DefinitionParameters, MusicSelectViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.45
                    @Override // kotlin.jvm.functions.Function2
                    public final MusicSelectViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MusicSelectViewModel((MusicRepository) viewModel.get(Reflection.getOrCreateKotlinClass(MusicRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory45 = DefinitionFactory.INSTANCE;
                Kind kind45 = Kind.Factory;
                BeanDefinition beanDefinition45 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MusicSelectViewModel.class));
                beanDefinition45.setDefinition(anonymousClass45);
                beanDefinition45.setKind(kind45);
                module.declareDefinition(beanDefinition45, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition45);
                AnonymousClass46 anonymousClass46 = new Function2<Scope, DefinitionParameters, MusicManagerViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.46
                    @Override // kotlin.jvm.functions.Function2
                    public final MusicManagerViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MusicManagerViewModel((MusicRepository) viewModel.get(Reflection.getOrCreateKotlinClass(MusicRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory46 = DefinitionFactory.INSTANCE;
                Kind kind46 = Kind.Factory;
                BeanDefinition beanDefinition46 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MusicManagerViewModel.class));
                beanDefinition46.setDefinition(anonymousClass46);
                beanDefinition46.setKind(kind46);
                module.declareDefinition(beanDefinition46, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition46);
                AnonymousClass47 anonymousClass47 = new Function2<Scope, DefinitionParameters, MyMusicListViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.47
                    @Override // kotlin.jvm.functions.Function2
                    public final MyMusicListViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MyMusicListViewModel((MusicRepository) viewModel.get(Reflection.getOrCreateKotlinClass(MusicRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory47 = DefinitionFactory.INSTANCE;
                Kind kind47 = Kind.Factory;
                BeanDefinition beanDefinition47 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MyMusicListViewModel.class));
                beanDefinition47.setDefinition(anonymousClass47);
                beanDefinition47.setKind(kind47);
                module.declareDefinition(beanDefinition47, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition47);
                AnonymousClass48 anonymousClass48 = new Function2<Scope, DefinitionParameters, EbookSelectViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.48
                    @Override // kotlin.jvm.functions.Function2
                    public final EbookSelectViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new EbookSelectViewModel((EbookRepository) viewModel.get(Reflection.getOrCreateKotlinClass(EbookRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory48 = DefinitionFactory.INSTANCE;
                Kind kind48 = Kind.Factory;
                BeanDefinition beanDefinition48 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(EbookSelectViewModel.class));
                beanDefinition48.setDefinition(anonymousClass48);
                beanDefinition48.setKind(kind48);
                module.declareDefinition(beanDefinition48, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition48);
                AnonymousClass49 anonymousClass49 = new Function2<Scope, DefinitionParameters, EbookManagerViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.49
                    @Override // kotlin.jvm.functions.Function2
                    public final EbookManagerViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new EbookManagerViewModel((EbookRepository) viewModel.get(Reflection.getOrCreateKotlinClass(EbookRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory49 = DefinitionFactory.INSTANCE;
                Kind kind49 = Kind.Factory;
                BeanDefinition beanDefinition49 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(EbookManagerViewModel.class));
                beanDefinition49.setDefinition(anonymousClass49);
                beanDefinition49.setKind(kind49);
                module.declareDefinition(beanDefinition49, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition49);
                AnonymousClass50 anonymousClass50 = new Function2<Scope, DefinitionParameters, BloodSugarActivityViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.50
                    @Override // kotlin.jvm.functions.Function2
                    public final BloodSugarActivityViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new BloodSugarActivityViewModel((BloodSugarRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodSugarRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory50 = DefinitionFactory.INSTANCE;
                Kind kind50 = Kind.Factory;
                BeanDefinition beanDefinition50 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(BloodSugarActivityViewModel.class));
                beanDefinition50.setDefinition(anonymousClass50);
                beanDefinition50.setKind(kind50);
                module.declareDefinition(beanDefinition50, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition50);
                AnonymousClass51 anonymousClass51 = new Function2<Scope, DefinitionParameters, WatchFaceInstallViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.51
                    @Override // kotlin.jvm.functions.Function2
                    public final WatchFaceInstallViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WatchFaceInstallViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory51 = DefinitionFactory.INSTANCE;
                Kind kind51 = Kind.Factory;
                BeanDefinition beanDefinition51 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WatchFaceInstallViewModel.class));
                beanDefinition51.setDefinition(anonymousClass51);
                beanDefinition51.setKind(kind51);
                module.declareDefinition(beanDefinition51, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition51);
                AnonymousClass52 anonymousClass52 = new Function2<Scope, DefinitionParameters, WatchMarketFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.52
                    @Override // kotlin.jvm.functions.Function2
                    public final WatchMarketFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WatchMarketFragmentViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory52 = DefinitionFactory.INSTANCE;
                Kind kind52 = Kind.Factory;
                BeanDefinition beanDefinition52 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WatchMarketFragmentViewModel.class));
                beanDefinition52.setDefinition(anonymousClass52);
                beanDefinition52.setKind(kind52);
                module.declareDefinition(beanDefinition52, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition52);
                AnonymousClass53 anonymousClass53 = new Function2<Scope, DefinitionParameters, HrvActivityViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.53
                    @Override // kotlin.jvm.functions.Function2
                    public final HrvActivityViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HrvActivityViewModel((HRVRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HRVRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory53 = DefinitionFactory.INSTANCE;
                Kind kind53 = Kind.Factory;
                BeanDefinition beanDefinition53 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HrvActivityViewModel.class));
                beanDefinition53.setDefinition(anonymousClass53);
                beanDefinition53.setKind(kind53);
                module.declareDefinition(beanDefinition53, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition53);
                AnonymousClass54 anonymousClass54 = new Function2<Scope, DefinitionParameters, DayPressureFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.54
                    @Override // kotlin.jvm.functions.Function2
                    public final DayPressureFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DayPressureFragmentViewModel((PressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(PressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory54 = DefinitionFactory.INSTANCE;
                Kind kind54 = Kind.Factory;
                BeanDefinition beanDefinition54 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DayPressureFragmentViewModel.class));
                beanDefinition54.setDefinition(anonymousClass54);
                beanDefinition54.setKind(kind54);
                module.declareDefinition(beanDefinition54, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition54);
                AnonymousClass55 anonymousClass55 = new Function2<Scope, DefinitionParameters, WeekPressureFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.55
                    @Override // kotlin.jvm.functions.Function2
                    public final WeekPressureFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WeekPressureFragmentViewModel((PressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(PressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory55 = DefinitionFactory.INSTANCE;
                Kind kind55 = Kind.Factory;
                BeanDefinition beanDefinition55 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WeekPressureFragmentViewModel.class));
                beanDefinition55.setDefinition(anonymousClass55);
                beanDefinition55.setKind(kind55);
                module.declareDefinition(beanDefinition55, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition55);
                AnonymousClass56 anonymousClass56 = new Function2<Scope, DefinitionParameters, MonthPressureFragmentViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.56
                    @Override // kotlin.jvm.functions.Function2
                    public final MonthPressureFragmentViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MonthPressureFragmentViewModel((PressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(PressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory56 = DefinitionFactory.INSTANCE;
                Kind kind56 = Kind.Factory;
                BeanDefinition beanDefinition56 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MonthPressureFragmentViewModel.class));
                beanDefinition56.setDefinition(anonymousClass56);
                beanDefinition56.setKind(kind56);
                module.declareDefinition(beanDefinition56, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition56);
                AnonymousClass57 anonymousClass57 = new Function2<Scope, DefinitionParameters, ThemeListViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.57
                    @Override // kotlin.jvm.functions.Function2
                    public final ThemeListViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new ThemeListViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory57 = DefinitionFactory.INSTANCE;
                Kind kind57 = Kind.Factory;
                BeanDefinition beanDefinition57 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(ThemeListViewModel.class));
                beanDefinition57.setDefinition(anonymousClass57);
                beanDefinition57.setKind(kind57);
                module.declareDefinition(beanDefinition57, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition57);
                AnonymousClass58 anonymousClass58 = new Function2<Scope, DefinitionParameters, ThemeDetailActivityViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.58
                    @Override // kotlin.jvm.functions.Function2
                    public final ThemeDetailActivityViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new ThemeDetailActivityViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory58 = DefinitionFactory.INSTANCE;
                Kind kind58 = Kind.Factory;
                BeanDefinition beanDefinition58 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(ThemeDetailActivityViewModel.class));
                beanDefinition58.setDefinition(anonymousClass58);
                beanDefinition58.setKind(kind58);
                module.declareDefinition(beanDefinition58, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition58);
                AnonymousClass59 anonymousClass59 = new Function2<Scope, DefinitionParameters, WallpaperMarketViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.59
                    @Override // kotlin.jvm.functions.Function2
                    public final WallpaperMarketViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WallpaperMarketViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory59 = DefinitionFactory.INSTANCE;
                Kind kind59 = Kind.Factory;
                BeanDefinition beanDefinition59 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WallpaperMarketViewModel.class));
                beanDefinition59.setDefinition(anonymousClass59);
                beanDefinition59.setKind(kind59);
                module.declareDefinition(beanDefinition59, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition59);
                AnonymousClass60 anonymousClass60 = new Function2<Scope, DefinitionParameters, WallpaperDetailActivityViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.60
                    @Override // kotlin.jvm.functions.Function2
                    public final WallpaperDetailActivityViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WallpaperDetailActivityViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory60 = DefinitionFactory.INSTANCE;
                Kind kind60 = Kind.Factory;
                BeanDefinition beanDefinition60 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WallpaperDetailActivityViewModel.class));
                beanDefinition60.setDefinition(anonymousClass60);
                beanDefinition60.setKind(kind60);
                module.declareDefinition(beanDefinition60, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition60);
                AnonymousClass61 anonymousClass61 = new Function2<Scope, DefinitionParameters, WallpaperTypeListViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.61
                    @Override // kotlin.jvm.functions.Function2
                    public final WallpaperTypeListViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WallpaperTypeListViewModel((WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory61 = DefinitionFactory.INSTANCE;
                Kind kind61 = Kind.Factory;
                BeanDefinition beanDefinition61 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WallpaperTypeListViewModel.class));
                beanDefinition61.setDefinition(anonymousClass61);
                beanDefinition61.setKind(kind61);
                module.declareDefinition(beanDefinition61, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition61);
                AnonymousClass62 anonymousClass62 = new Function2<Scope, DefinitionParameters, AiHealthViewModel>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$viewModelModule$1.62
                    @Override // kotlin.jvm.functions.Function2
                    public final AiHealthViewModel invoke(Scope viewModel, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new AiHealthViewModel((HealthyRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HealthyRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (StepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(StepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (SleepDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SleepDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (HeartRateDetailRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HeartRateDetailRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (SportPlusRepository) viewModel.get(Reflection.getOrCreateKotlinClass(SportPlusRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (WatchFaceRepository) viewModel.get(Reflection.getOrCreateKotlinClass(WatchFaceRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (BloodPressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodPressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (BloodOxygenRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodOxygenRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (TemperatureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(TemperatureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (BloodSugarRepository) viewModel.get(Reflection.getOrCreateKotlinClass(BloodSugarRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (PressureRepository) viewModel.get(Reflection.getOrCreateKotlinClass(PressureRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null), (HRVRepository) viewModel.get(Reflection.getOrCreateKotlinClass(HRVRepository.class), (Qualifier) null, (Function0<DefinitionParameters>) null));
                    }
                };
                DefinitionFactory definitionFactory62 = DefinitionFactory.INSTANCE;
                Kind kind62 = Kind.Factory;
                BeanDefinition beanDefinition62 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(AiHealthViewModel.class));
                beanDefinition62.setDefinition(anonymousClass62);
                beanDefinition62.setKind(kind62);
                module.declareDefinition(beanDefinition62, new Options(false, false, 1, null));
                ModuleExtKt.setIsViewModel(beanDefinition62);
            }
        }, 3, null);
        viewModelModule = moduleModule$default;
        Module moduleModule$default2 = ModuleKt.module$default(false, false, new Function1<Module, Unit>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Module module) {
                invoke2(module);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Module module) {
                Intrinsics.checkNotNullParameter(module, "$this$module");
                AnonymousClass1 anonymousClass1 = new Function2<Scope, DefinitionParameters, DeviceBindRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.1
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceBindRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceBindRepository();
                    }
                };
                DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
                Kind kind = Kind.Single;
                BeanDefinition beanDefinition = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceBindRepository.class));
                beanDefinition.setDefinition(anonymousClass1);
                beanDefinition.setKind(kind);
                module.declareDefinition(beanDefinition, new Options(false, false));
                AnonymousClass2 anonymousClass2 = new Function2<Scope, DefinitionParameters, LoginRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.2
                    @Override // kotlin.jvm.functions.Function2
                    public final LoginRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new LoginRepository();
                    }
                };
                DefinitionFactory definitionFactory2 = DefinitionFactory.INSTANCE;
                Kind kind2 = Kind.Single;
                BeanDefinition beanDefinition2 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(LoginRepository.class));
                beanDefinition2.setDefinition(anonymousClass2);
                beanDefinition2.setKind(kind2);
                module.declareDefinition(beanDefinition2, new Options(false, false));
                AnonymousClass3 anonymousClass3 = new Function2<Scope, DefinitionParameters, DeviceSettingRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.3
                    @Override // kotlin.jvm.functions.Function2
                    public final DeviceSettingRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new DeviceSettingRepository();
                    }
                };
                DefinitionFactory definitionFactory3 = DefinitionFactory.INSTANCE;
                Kind kind3 = Kind.Single;
                BeanDefinition beanDefinition3 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(DeviceSettingRepository.class));
                beanDefinition3.setDefinition(anonymousClass3);
                beanDefinition3.setKind(kind3);
                module.declareDefinition(beanDefinition3, new Options(false, false));
                AnonymousClass4 anonymousClass4 = new Function2<Scope, DefinitionParameters, HealthyRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.4
                    @Override // kotlin.jvm.functions.Function2
                    public final HealthyRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HealthyRepository();
                    }
                };
                DefinitionFactory definitionFactory4 = DefinitionFactory.INSTANCE;
                Kind kind4 = Kind.Single;
                BeanDefinition beanDefinition4 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HealthyRepository.class));
                beanDefinition4.setDefinition(anonymousClass4);
                beanDefinition4.setKind(kind4);
                module.declareDefinition(beanDefinition4, new Options(false, false));
                AnonymousClass5 anonymousClass5 = new Function2<Scope, DefinitionParameters, StepDetailRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.5
                    @Override // kotlin.jvm.functions.Function2
                    public final StepDetailRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new StepDetailRepository();
                    }
                };
                DefinitionFactory definitionFactory5 = DefinitionFactory.INSTANCE;
                Kind kind5 = Kind.Single;
                BeanDefinition beanDefinition5 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(StepDetailRepository.class));
                beanDefinition5.setDefinition(anonymousClass5);
                beanDefinition5.setKind(kind5);
                module.declareDefinition(beanDefinition5, new Options(false, false));
                AnonymousClass6 anonymousClass6 = new Function2<Scope, DefinitionParameters, SleepDetailRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.6
                    @Override // kotlin.jvm.functions.Function2
                    public final SleepDetailRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new SleepDetailRepository();
                    }
                };
                DefinitionFactory definitionFactory6 = DefinitionFactory.INSTANCE;
                Kind kind6 = Kind.Single;
                BeanDefinition beanDefinition6 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(SleepDetailRepository.class));
                beanDefinition6.setDefinition(anonymousClass6);
                beanDefinition6.setKind(kind6);
                module.declareDefinition(beanDefinition6, new Options(false, false));
                AnonymousClass7 anonymousClass7 = new Function2<Scope, DefinitionParameters, MessagePushRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.7
                    @Override // kotlin.jvm.functions.Function2
                    public final MessagePushRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MessagePushRepository();
                    }
                };
                DefinitionFactory definitionFactory7 = DefinitionFactory.INSTANCE;
                Kind kind7 = Kind.Single;
                BeanDefinition beanDefinition7 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MessagePushRepository.class));
                beanDefinition7.setDefinition(anonymousClass7);
                beanDefinition7.setKind(kind7);
                module.declareDefinition(beanDefinition7, new Options(false, false));
                AnonymousClass8 anonymousClass8 = new Function2<Scope, DefinitionParameters, StepHistoryRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.8
                    @Override // kotlin.jvm.functions.Function2
                    public final StepHistoryRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new StepHistoryRepository();
                    }
                };
                DefinitionFactory definitionFactory8 = DefinitionFactory.INSTANCE;
                Kind kind8 = Kind.Single;
                BeanDefinition beanDefinition8 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(StepHistoryRepository.class));
                beanDefinition8.setDefinition(anonymousClass8);
                beanDefinition8.setKind(kind8);
                module.declareDefinition(beanDefinition8, new Options(false, false));
                AnonymousClass9 anonymousClass9 = new Function2<Scope, DefinitionParameters, HeartRateDetailRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.9
                    @Override // kotlin.jvm.functions.Function2
                    public final HeartRateDetailRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HeartRateDetailRepository();
                    }
                };
                DefinitionFactory definitionFactory9 = DefinitionFactory.INSTANCE;
                Kind kind9 = Kind.Single;
                BeanDefinition beanDefinition9 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HeartRateDetailRepository.class));
                beanDefinition9.setDefinition(anonymousClass9);
                beanDefinition9.setKind(kind9);
                module.declareDefinition(beanDefinition9, new Options(false, false));
                AnonymousClass10 anonymousClass10 = new Function2<Scope, DefinitionParameters, UserProfileRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.10
                    @Override // kotlin.jvm.functions.Function2
                    public final UserProfileRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new UserProfileRepository();
                    }
                };
                DefinitionFactory definitionFactory10 = DefinitionFactory.INSTANCE;
                Kind kind10 = Kind.Single;
                BeanDefinition beanDefinition10 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(UserProfileRepository.class));
                beanDefinition10.setDefinition(anonymousClass10);
                beanDefinition10.setKind(kind10);
                module.declareDefinition(beanDefinition10, new Options(false, false));
                AnonymousClass11 anonymousClass11 = new Function2<Scope, DefinitionParameters, SportPlusRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.11
                    @Override // kotlin.jvm.functions.Function2
                    public final SportPlusRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new SportPlusRepository();
                    }
                };
                DefinitionFactory definitionFactory11 = DefinitionFactory.INSTANCE;
                Kind kind11 = Kind.Single;
                BeanDefinition beanDefinition11 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(SportPlusRepository.class));
                beanDefinition11.setDefinition(anonymousClass11);
                beanDefinition11.setKind(kind11);
                module.declareDefinition(beanDefinition11, new Options(false, false));
                AnonymousClass12 anonymousClass12 = new Function2<Scope, DefinitionParameters, WatchFaceRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.12
                    @Override // kotlin.jvm.functions.Function2
                    public final WatchFaceRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WatchFaceRepository();
                    }
                };
                DefinitionFactory definitionFactory12 = DefinitionFactory.INSTANCE;
                Kind kind12 = Kind.Single;
                BeanDefinition beanDefinition12 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WatchFaceRepository.class));
                beanDefinition12.setDefinition(anonymousClass12);
                beanDefinition12.setKind(kind12);
                module.declareDefinition(beanDefinition12, new Options(false, false));
                AnonymousClass13 anonymousClass13 = new Function2<Scope, DefinitionParameters, RegisterRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.13
                    @Override // kotlin.jvm.functions.Function2
                    public final RegisterRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new RegisterRepository();
                    }
                };
                DefinitionFactory definitionFactory13 = DefinitionFactory.INSTANCE;
                Kind kind13 = Kind.Single;
                BeanDefinition beanDefinition13 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(RegisterRepository.class));
                beanDefinition13.setDefinition(anonymousClass13);
                beanDefinition13.setKind(kind13);
                module.declareDefinition(beanDefinition13, new Options(false, false));
                AnonymousClass14 anonymousClass14 = new Function2<Scope, DefinitionParameters, MenstruationRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.14
                    @Override // kotlin.jvm.functions.Function2
                    public final MenstruationRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MenstruationRepository();
                    }
                };
                DefinitionFactory definitionFactory14 = DefinitionFactory.INSTANCE;
                Kind kind14 = Kind.Single;
                BeanDefinition beanDefinition14 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MenstruationRepository.class));
                beanDefinition14.setDefinition(anonymousClass14);
                beanDefinition14.setKind(kind14);
                module.declareDefinition(beanDefinition14, new Options(false, false));
                AnonymousClass15 anonymousClass15 = new Function2<Scope, DefinitionParameters, FindPwdRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.15
                    @Override // kotlin.jvm.functions.Function2
                    public final FindPwdRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new FindPwdRepository();
                    }
                };
                DefinitionFactory definitionFactory15 = DefinitionFactory.INSTANCE;
                Kind kind15 = Kind.Single;
                BeanDefinition beanDefinition15 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(FindPwdRepository.class));
                beanDefinition15.setDefinition(anonymousClass15);
                beanDefinition15.setKind(kind15);
                module.declareDefinition(beanDefinition15, new Options(false, false));
                AnonymousClass16 anonymousClass16 = new Function2<Scope, DefinitionParameters, OTARepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.16
                    @Override // kotlin.jvm.functions.Function2
                    public final OTARepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new OTARepository();
                    }
                };
                DefinitionFactory definitionFactory16 = DefinitionFactory.INSTANCE;
                Kind kind16 = Kind.Single;
                BeanDefinition beanDefinition16 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(OTARepository.class));
                beanDefinition16.setDefinition(anonymousClass16);
                beanDefinition16.setKind(kind16);
                module.declareDefinition(beanDefinition16, new Options(false, false));
                AnonymousClass17 anonymousClass17 = new Function2<Scope, DefinitionParameters, GpsRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.17
                    @Override // kotlin.jvm.functions.Function2
                    public final GpsRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new GpsRepository();
                    }
                };
                DefinitionFactory definitionFactory17 = DefinitionFactory.INSTANCE;
                Kind kind17 = Kind.Single;
                BeanDefinition beanDefinition17 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(GpsRepository.class));
                beanDefinition17.setDefinition(anonymousClass17);
                beanDefinition17.setKind(kind17);
                module.declareDefinition(beanDefinition17, new Options(false, false));
                AnonymousClass18 anonymousClass18 = new Function2<Scope, DefinitionParameters, BloodPressureRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.18
                    @Override // kotlin.jvm.functions.Function2
                    public final BloodPressureRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new BloodPressureRepository();
                    }
                };
                DefinitionFactory definitionFactory18 = DefinitionFactory.INSTANCE;
                Kind kind18 = Kind.Single;
                BeanDefinition beanDefinition18 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(BloodPressureRepository.class));
                beanDefinition18.setDefinition(anonymousClass18);
                beanDefinition18.setKind(kind18);
                module.declareDefinition(beanDefinition18, new Options(false, false));
                AnonymousClass19 anonymousClass19 = new Function2<Scope, DefinitionParameters, BloodOxygenRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.19
                    @Override // kotlin.jvm.functions.Function2
                    public final BloodOxygenRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new BloodOxygenRepository();
                    }
                };
                DefinitionFactory definitionFactory19 = DefinitionFactory.INSTANCE;
                Kind kind19 = Kind.Single;
                BeanDefinition beanDefinition19 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(BloodOxygenRepository.class));
                beanDefinition19.setDefinition(anonymousClass19);
                beanDefinition19.setKind(kind19);
                module.declareDefinition(beanDefinition19, new Options(false, false));
                AnonymousClass20 anonymousClass20 = new Function2<Scope, DefinitionParameters, OneKeyCheckRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.20
                    @Override // kotlin.jvm.functions.Function2
                    public final OneKeyCheckRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new OneKeyCheckRepository();
                    }
                };
                DefinitionFactory definitionFactory20 = DefinitionFactory.INSTANCE;
                Kind kind20 = Kind.Single;
                BeanDefinition beanDefinition20 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(OneKeyCheckRepository.class));
                beanDefinition20.setDefinition(anonymousClass20);
                beanDefinition20.setKind(kind20);
                module.declareDefinition(beanDefinition20, new Options(false, false));
                AnonymousClass21 anonymousClass21 = new Function2<Scope, DefinitionParameters, FeedbackRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.21
                    @Override // kotlin.jvm.functions.Function2
                    public final FeedbackRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new FeedbackRepository();
                    }
                };
                DefinitionFactory definitionFactory21 = DefinitionFactory.INSTANCE;
                Kind kind21 = Kind.Single;
                BeanDefinition beanDefinition21 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(FeedbackRepository.class));
                beanDefinition21.setDefinition(anonymousClass21);
                beanDefinition21.setKind(kind21);
                module.declareDefinition(beanDefinition21, new Options(false, false));
                AnonymousClass22 anonymousClass22 = new Function2<Scope, DefinitionParameters, TemperatureRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.22
                    @Override // kotlin.jvm.functions.Function2
                    public final TemperatureRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new TemperatureRepository();
                    }
                };
                DefinitionFactory definitionFactory22 = DefinitionFactory.INSTANCE;
                Kind kind22 = Kind.Single;
                BeanDefinition beanDefinition22 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(TemperatureRepository.class));
                beanDefinition22.setDefinition(anonymousClass22);
                beanDefinition22.setKind(kind22);
                module.declareDefinition(beanDefinition22, new Options(false, false));
                AnonymousClass23 anonymousClass23 = new Function2<Scope, DefinitionParameters, ContactsRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.23
                    @Override // kotlin.jvm.functions.Function2
                    public final ContactsRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new ContactsRepository();
                    }
                };
                DefinitionFactory definitionFactory23 = DefinitionFactory.INSTANCE;
                Kind kind23 = Kind.Single;
                BeanDefinition beanDefinition23 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(ContactsRepository.class));
                beanDefinition23.setDefinition(anonymousClass23);
                beanDefinition23.setKind(kind23);
                module.declareDefinition(beanDefinition23, new Options(false, false));
                AnonymousClass24 anonymousClass24 = new Function2<Scope, DefinitionParameters, MusicRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.24
                    @Override // kotlin.jvm.functions.Function2
                    public final MusicRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new MusicRepository();
                    }
                };
                DefinitionFactory definitionFactory24 = DefinitionFactory.INSTANCE;
                Kind kind24 = Kind.Single;
                BeanDefinition beanDefinition24 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(MusicRepository.class));
                beanDefinition24.setDefinition(anonymousClass24);
                beanDefinition24.setKind(kind24);
                module.declareDefinition(beanDefinition24, new Options(false, false));
                AnonymousClass25 anonymousClass25 = new Function2<Scope, DefinitionParameters, EbookRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.25
                    @Override // kotlin.jvm.functions.Function2
                    public final EbookRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new EbookRepository();
                    }
                };
                DefinitionFactory definitionFactory25 = DefinitionFactory.INSTANCE;
                Kind kind25 = Kind.Single;
                BeanDefinition beanDefinition25 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(EbookRepository.class));
                beanDefinition25.setDefinition(anonymousClass25);
                beanDefinition25.setKind(kind25);
                module.declareDefinition(beanDefinition25, new Options(false, false));
                AnonymousClass26 anonymousClass26 = new Function2<Scope, DefinitionParameters, BloodSugarRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.26
                    @Override // kotlin.jvm.functions.Function2
                    public final BloodSugarRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new BloodSugarRepository();
                    }
                };
                DefinitionFactory definitionFactory26 = DefinitionFactory.INSTANCE;
                Kind kind26 = Kind.Single;
                BeanDefinition beanDefinition26 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(BloodSugarRepository.class));
                beanDefinition26.setDefinition(anonymousClass26);
                beanDefinition26.setKind(kind26);
                module.declareDefinition(beanDefinition26, new Options(false, false));
                AnonymousClass27 anonymousClass27 = new Function2<Scope, DefinitionParameters, PressureRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.27
                    @Override // kotlin.jvm.functions.Function2
                    public final PressureRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new PressureRepository();
                    }
                };
                DefinitionFactory definitionFactory27 = DefinitionFactory.INSTANCE;
                Kind kind27 = Kind.Single;
                BeanDefinition beanDefinition27 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(PressureRepository.class));
                beanDefinition27.setDefinition(anonymousClass27);
                beanDefinition27.setKind(kind27);
                module.declareDefinition(beanDefinition27, new Options(false, false));
                AnonymousClass28 anonymousClass28 = new Function2<Scope, DefinitionParameters, HRVRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.28
                    @Override // kotlin.jvm.functions.Function2
                    public final HRVRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new HRVRepository();
                    }
                };
                DefinitionFactory definitionFactory28 = DefinitionFactory.INSTANCE;
                Kind kind28 = Kind.Single;
                BeanDefinition beanDefinition28 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(HRVRepository.class));
                beanDefinition28.setDefinition(anonymousClass28);
                beanDefinition28.setKind(kind28);
                module.declareDefinition(beanDefinition28, new Options(false, false));
                AnonymousClass29 anonymousClass29 = new Function2<Scope, DefinitionParameters, WebsocketRepository>() { // from class: com.qcwireless.qcwatch.base.di.KoinModuleKt$repositoryModule$1.29
                    @Override // kotlin.jvm.functions.Function2
                    public final WebsocketRepository invoke(Scope single, DefinitionParameters it) {
                        Intrinsics.checkNotNullParameter(single, "$this$single");
                        Intrinsics.checkNotNullParameter(it, "it");
                        return new WebsocketRepository();
                    }
                };
                DefinitionFactory definitionFactory29 = DefinitionFactory.INSTANCE;
                Kind kind29 = Kind.Single;
                BeanDefinition beanDefinition29 = new BeanDefinition(null, null, Reflection.getOrCreateKotlinClass(WebsocketRepository.class));
                beanDefinition29.setDefinition(anonymousClass29);
                beanDefinition29.setKind(kind29);
                module.declareDefinition(beanDefinition29, new Options(false, false));
            }
        }, 3, null);
        repositoryModule = moduleModule$default2;
        appModule = CollectionsKt.listOf((Object[]) new Module[]{moduleModule$default, moduleModule$default2});
    }

    public static final Module getViewModelModule() {
        return viewModelModule;
    }

    public static final Module getRepositoryModule() {
        return repositoryModule;
    }

    public static final List<Module> getAppModule() {
        return appModule;
    }
}
