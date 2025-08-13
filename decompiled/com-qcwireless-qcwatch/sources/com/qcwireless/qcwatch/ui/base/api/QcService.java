package com.qcwireless.qcwatch.ui.base.api;

import com.qcwireless.qcwatch.ui.base.bean.request.app.AppVersionRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.device.DeviceFeaturesListRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.device.DeviceMissingFileRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.device.LastOtaRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.BloodOxygenRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.BloodPressureRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.BpDownRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.CommitSleepNewProtocolParam;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.HealthyDataDownRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.HeartRateIntervalRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.SleepDetailRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.Spo2DownRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.SportDetailRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.StepDetailRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.TemperatureDownloadRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.healthy.TemperatureRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.user.FindPwdRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.user.GoalSettingRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.user.LoginRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.user.ResetPwdRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.user.UserProfileRequest;
import com.qcwireless.qcwatch.ui.base.bean.request.weather.WeatherRequest;
import com.qcwireless.qcwatch.ui.base.bean.response.cs.SupportCsResp;
import com.qcwireless.qcwatch.ui.base.bean.response.cs.WsChatMessage;
import com.qcwireless.qcwatch.ui.base.bean.response.device.AndroidMessagePush;
import com.qcwireless.qcwatch.ui.base.bean.response.device.CustomDialResp;
import com.qcwireless.qcwatch.ui.base.bean.response.device.DeviceMissFileResp;
import com.qcwireless.qcwatch.ui.base.bean.response.device.DevicePictureResp;
import com.qcwireless.qcwatch.ui.base.bean.response.device.FirmwareOtaResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.BpDownResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.HeartRateResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.SleepDetailNewProtocolResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.SleepDetailResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.Spo2DownResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.SportDetailResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.StepDetailResp;
import com.qcwireless.qcwatch.ui.base.bean.response.healthy.TemperatureDownResp;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.UserLoginResp;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.UserProfileResp;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.feedback.FeedbackResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.GenerateOrderNumberResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceIndex;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceOrderGenerateParams;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceOrderParams;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceOrderResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceRanking;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchFaceResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchThemeResp;
import com.qcwireless.qcwatch.ui.base.bean.response.watchface.WatchWallpaperResp;
import com.qcwireless.qcwatch.ui.base.bean.response.weather.WeatherResp;
import com.qcwireless.qcwatch.ui.mine.ai.bean.AiChatBean;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* compiled from: QcService.kt */
@Metadata(d1 = {"\u0000´\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 Ã\u00012\u00020\u0001:\u0002Ã\u0001J1\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\f\u001a\u00020\rH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u0012H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J+\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00152\b\b\u0001\u0010\u0016\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\u00102\b\b\u0001\u0010\u0014\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001b\u0010\u001a\u001a\u00020\u00102\b\b\u0001\u0010\u001b\u001a\u00020\u001cH§@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ'\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\b0\u00032\b\b\u0001\u0010 \u001a\u00020!H§@ø\u0001\u0000¢\u0006\u0002\u0010\"J'\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\b0\u00032\b\b\u0001\u0010 \u001a\u00020%H§@ø\u0001\u0000¢\u0006\u0002\u0010&J'\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\b0\u00032\b\b\u0001\u0010 \u001a\u00020!H§@ø\u0001\u0000¢\u0006\u0002\u0010\"J'\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\b0\u00032\b\b\u0001\u0010 \u001a\u00020)H§@ø\u0001\u0000¢\u0006\u0002\u0010*J'\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\b0\u00032\b\b\u0001\u0010 \u001a\u00020)H§@ø\u0001\u0000¢\u0006\u0002\u0010*J'\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\b0\u00032\b\b\u0001\u0010 \u001a\u00020)H§@ø\u0001\u0000¢\u0006\u0002\u0010*J'\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\b0\u00032\b\b\u0001\u0010 \u001a\u00020)H§@ø\u0001\u0000¢\u0006\u0002\u0010*J'\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\b0\u00032\b\b\u0001\u0010 \u001a\u00020)H§@ø\u0001\u0000¢\u0006\u0002\u0010*J'\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\b0\u00032\b\b\u0001\u0010 \u001a\u000205H§@ø\u0001\u0000¢\u0006\u0002\u00106J'\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\b0\u00032\b\b\u0001\u00109\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019JI\u0010:\u001a\u00020\u00102\b\b\u0001\u0010;\u001a\u00020\u000b2\b\b\u0001\u0010<\u001a\u00020\u000b2\b\b\u0001\u0010=\u001a\u00020\u00062\b\b\u0001\u0010>\u001a\u00020\u00062\u000e\b\u0001\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\bH§@ø\u0001\u0000¢\u0006\u0002\u0010AJ!\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\u00032\b\b\u0001\u0010\u0011\u001a\u00020DH§@ø\u0001\u0000¢\u0006\u0002\u0010EJ!\u0010F\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010G\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J1\u0010H\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020J0Ij\b\u0012\u0004\u0012\u00020J`K0\u00032\b\b\u0001\u0010L\u001a\u00020MH§@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u001d\u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0\b0\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010QJ!\u0010R\u001a\b\u0012\u0004\u0012\u00020S0\u00032\b\b\u0001\u0010G\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010T\u001a\b\u0012\u0004\u0012\u00020S0\u00032\b\b\u0001\u0010G\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010U\u001a\b\u0012\u0004\u0012\u00020V0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0015H§@ø\u0001\u0000¢\u0006\u0002\u0010WJ!\u0010X\u001a\b\u0012\u0004\u0012\u00020Y0\u00032\b\b\u0001\u0010Z\u001a\u00020[H§@ø\u0001\u0000¢\u0006\u0002\u0010\\J!\u0010]\u001a\b\u0012\u0004\u0012\u00020Y0\u00032\b\b\u0001\u0010Z\u001a\u00020[H§@ø\u0001\u0000¢\u0006\u0002\u0010\\J!\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010_\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010`\u001a\b\u0012\u0004\u0012\u00020a0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0015H§@ø\u0001\u0000¢\u0006\u0002\u0010WJ\u001b\u0010b\u001a\u00020\u00102\b\b\u0001\u0010c\u001a\u00020dH§@ø\u0001\u0000¢\u0006\u0002\u0010eJ'\u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0\b0\u00032\b\b\u0001\u0010G\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010h\u001a\b\u0012\u0004\u0012\u00020i0\u00032\b\b\u0001\u0010G\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010j\u001a\b\u0012\u0004\u0012\u00020i0\u00032\b\b\u0001\u0010G\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010G\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001b\u0010l\u001a\u00020\u00102\b\b\u0001\u0010m\u001a\u00020VH§@ø\u0001\u0000¢\u0006\u0002\u0010nJ\u001b\u0010o\u001a\u00020\u00102\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010p\u001a\b\u0012\u0004\u0012\u00020q0\u00032\b\b\u0001\u0010p\u001a\u00020rH§@ø\u0001\u0000¢\u0006\u0002\u0010sJ'\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020u0\b0\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J+\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00152\b\b\u0001\u0010w\u001a\u00020@H§@ø\u0001\u0000¢\u0006\u0002\u0010xJ1\u0010y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u00062\b\b\u0001\u0010{\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010|J\u001b\u0010}\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020~H§@ø\u0001\u0000¢\u0006\u0002\u0010\u007fJ\"\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020q0\u00032\b\b\u0001\u0010p\u001a\u00020rH§@ø\u0001\u0000¢\u0006\u0002\u0010sJ\u001f\u0010\u0081\u0001\u001a\u00020\u00102\n\b\u0001\u0010\u0081\u0001\u001a\u00030\u0082\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010\u0083\u0001J#\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\t\b\u0001\u0010\u0085\u0001\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001f\u0010\u0086\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0087\u00010\b0\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010QJ\"\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001f\u0010\u0089\u0001\u001a\u00020\u00102\n\b\u0001\u0010\u008a\u0001\u001a\u00030\u008b\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010\u008c\u0001J\u001f\u0010\u008d\u0001\u001a\u00020\u00102\n\b\u0001\u0010\u008e\u0001\u001a\u00030\u008f\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010\u0090\u0001J\u001f\u0010\u0091\u0001\u001a\u00020\u00102\n\b\u0001\u0010\u0092\u0001\u001a\u00030\u0093\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010\u0094\u0001J\u001f\u0010\u0095\u0001\u001a\u00020\u00102\n\b\u0001\u0010\u0096\u0001\u001a\u00030\u0097\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J\u001f\u0010\u0099\u0001\u001a\u00020\u00102\n\b\u0001\u0010\u0096\u0001\u001a\u00030\u009a\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010\u009b\u0001J\u001f\u0010\u009c\u0001\u001a\u00020\u00102\n\b\u0001\u0010\u009d\u0001\u001a\u00030\u009e\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010\u009f\u0001J\u001f\u0010 \u0001\u001a\u00020\u00102\n\b\u0001\u0010¡\u0001\u001a\u00030¢\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010£\u0001J\u001f\u0010¤\u0001\u001a\u00020\u00102\n\b\u0001\u0010¥\u0001\u001a\u00030¦\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010§\u0001J\u001f\u0010¨\u0001\u001a\u00020\u00102\n\b\u0001\u0010©\u0001\u001a\u00030ª\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010«\u0001J3\u0010¬\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u00062\b\b\u0001\u0010;\u001a\u00020\u000bH§@ø\u0001\u0000¢\u0006\u0003\u0010\u00ad\u0001J3\u0010®\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u00062\b\b\u0001\u0010;\u001a\u00020\u000bH§@ø\u0001\u0000¢\u0006\u0003\u0010\u00ad\u0001J)\u0010¯\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030°\u00010\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J)\u0010±\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030°\u00010\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J3\u0010²\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030³\u00010\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010|J\u001e\u0010´\u0001\u001a\u00020\u00102\t\b\u0001\u0010\u0011\u001a\u00030µ\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010¶\u0001J)\u0010·\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030¸\u00010\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J4\u0010¹\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030º\u00010\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u00062\b\b\u0001\u0010;\u001a\u00020\u000bH§@ø\u0001\u0000¢\u0006\u0003\u0010\u00ad\u0001J)\u0010»\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030º\u00010\b0\u00032\b\b\u0001\u0010z\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J-\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010z\u001a\u00020\u00062\t\b\u0001\u0010½\u0001\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010|J,\u0010¾\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030¿\u00010\b0\u00032\n\b\u0001\u0010À\u0001\u001a\u00030Á\u0001H§@ø\u0001\u0000¢\u0006\u0003\u0010Â\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Ä\u0001"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/api/QcService;", "", "aiChatGPT", "Lcom/qcwireless/qcwatch/ui/base/api/QcResponse;", "Lcom/qcwireless/qcwatch/ui/mine/ai/bean/AiChatBean;", "uid", "", "messages", "", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "appLastVersion", "", "appUpgrade", "Lcom/qcwireless/qcwatch/ui/base/bean/request/app/AppVersionRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/app/AppVersionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectionData", "Lcom/qcwireless/qcwatch/ui/base/api/QcNoDataResponse;", "request", "Lcom/qcwireless/qcwatch/ui/base/bean/request/collection/CollectionRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/collection/CollectionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contactId", "", "hdVersion", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deviceFeaturesList", "featureList", "Lcom/qcwireless/qcwatch/ui/base/bean/request/device/DeviceFeaturesListRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/device/DeviceFeaturesListRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downBo2", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/Spo2DownResp;", "healthy", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/Spo2DownRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/Spo2DownRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downBp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/BpDownResp;", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BpDownRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BpDownRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downHeartRateDetail", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp;", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/HealthyDataDownRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/HealthyDataDownRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downSleepDetail", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailResp;", "downSleepDetailV1", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailNewProtocolResp;", "downSportDetail", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SportDetailResp;", "downStepDetail", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/StepDetailResp;", "downTemperature", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/TemperatureDownResp;", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/TemperatureDownloadRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/TemperatureDownloadRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "feedback", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/feedback/FeedbackResp;", "language", "feedbackSubmit", "typeId", "feedbackId", "email", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "files", "Lokhttp3/MultipartBody$Part;", "(IILjava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateOrder", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/GenerateOrderNumberResp;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrderGenerateParams;", "(Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrderGenerateParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceConfig", "version", "getDeviceFileList", "Ljava/util/ArrayList;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DeviceMissFileResp;", "Lkotlin/collections/ArrayList;", "fileName", "Lcom/qcwireless/qcwatch/ui/base/bean/request/device/DeviceMissingFileRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/device/DeviceMissingFileRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeviceMessagePush", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/AndroidMessagePush;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDevicePicture", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;", "getDevicePictureChina", "getGoal", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/GoalSettingRequest;", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastOta", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;", "otaRequest", "Lcom/qcwireless/qcwatch/ui/base/bean/request/device/LastOtaRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/device/LastOtaRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastOtaChina", "getToken", "key", "getUserProfile", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserProfileResp;", "getVerifyCode", "findPwdRequest", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/FindPwdRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/user/FindPwdRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWatchFace", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceResp;", "getWatchFaceDialParameters", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp;", "getWatchFaceDialParametersChina", "getWatchFaceVersion", "goalUpdate", "goalSetting", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/user/GoalSettingRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logOff", "login", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/LoginRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/user/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "messageList", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/WsChatMessage;", "protoImage", "file", "(JLokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryByNamesChina", "hdName", "names", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rankingUpdate", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceRanking;", "(Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceRanking;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "restPwd", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/ResetPwdRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/user/ResetPwdRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanConfig", "app", "showSupportCs", "Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/SupportCsResp;", "unread", "upBloodOxygen", "spo2", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodOxygenRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodOxygenRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upBloodPressure", "bp", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodPressureRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodPressureRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upIntervalHeart", "heartRate", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/HeartRateIntervalRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/HeartRateIntervalRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upSleepDetail", "sleepDetail", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SleepDetailRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SleepDetailRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upSleepDetailV1", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/CommitSleepNewProtocolParam;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/CommitSleepNewProtocolParam;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upSportDetail", "sportDetail", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SportDetailRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SportDetailRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upStepDetail", "stepDetail", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/StepDetailRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/StepDetailRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upTemperature", "temperature", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/TemperatureRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/TemperatureRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProfile", "userProfile", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/UserProfileRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/user/UserProfileRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "watchFaceByType", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "watchFaceByTypeChina", "watchFaceIndex", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceIndex;", "watchFaceIndexChina", "watchFacePays", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrderResp;", "watchFacePaysUpdate", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrderParams;", "(Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrderParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "watchThemeList", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchThemeResp;", "watchWallpaperByType", "Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;", "watchWallpaperList", "watchfaceDownloadCount", "name", "weatherInfo", "Lcom/qcwireless/qcwatch/ui/base/bean/response/weather/WeatherResp;", "weather", "Lcom/qcwireless/qcwatch/ui/base/bean/request/weather/WeatherRequest;", "(Lcom/qcwireless/qcwatch/ui/base/bean/request/weather/WeatherRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcService {
    public static final String APP_NAME = "QWatchPro";
    public static final String Android_Token_Key = "qcwx_android";
    public static final String BASE_URL = "https://api1.qcwxkjvip.com/qcwx/";
    public static final String BASE_URL_BACKUP = "https://china.qcwxwire.com/qcwx/";
    public static final String BASE_URL_CHINA = "https://china.qcwxwire.com/qcwx/";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @POST("ai/chat/{uid}")
    Object aiChatGPT(@Path("uid") String str, @Body List<AiChatBean> list, Continuation<? super QcResponse<AiChatBean>> continuation);

    @POST("app-update/appLastVersion")
    Object appLastVersion(@Body AppVersionRequest appVersionRequest, Continuation<? super QcResponse<Integer>> continuation);

    @POST("collection/system/info")
    Object collectionData(@Body CollectionRequest collectionRequest, Continuation<? super QcNoDataResponse> continuation);

    @GET("ws/contact/queryId")
    Object contactId(@Query("uid") long j, @Query("hdVersion") String str, Continuation<? super QcResponse<String>> continuation);

    @GET("ws/contact/delete")
    Object deleteContact(@Query("contactId") String str, Continuation<? super QcNoDataResponse> continuation);

    @POST("device/features/list")
    Object deviceFeaturesList(@Body DeviceFeaturesListRequest deviceFeaturesListRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("spo2/sync-from-id/v1")
    Object downBo2(@Body Spo2DownRequest spo2DownRequest, Continuation<? super QcResponse<? extends List<Spo2DownResp>>> continuation);

    @POST("blood-pressure/sync-from-id")
    Object downBp(@Body BpDownRequest bpDownRequest, Continuation<? super QcResponse<? extends List<BpDownResp>>> continuation);

    @POST("spo2/sync-from-id")
    Object downBp(@Body Spo2DownRequest spo2DownRequest, Continuation<? super QcResponse<? extends List<Spo2DownResp>>> continuation);

    @POST("heart-rate-interval/sync-from-time")
    Object downHeartRateDetail(@Body HealthyDataDownRequest healthyDataDownRequest, Continuation<? super QcResponse<? extends List<HeartRateResp>>> continuation);

    @POST("sleep/sync-from-time")
    Object downSleepDetail(@Body HealthyDataDownRequest healthyDataDownRequest, Continuation<? super QcResponse<? extends List<SleepDetailResp>>> continuation);

    @POST("sleep/sync-from-time/v1")
    Object downSleepDetailV1(@Body HealthyDataDownRequest healthyDataDownRequest, Continuation<? super QcResponse<? extends List<? extends SleepDetailNewProtocolResp>>> continuation);

    @POST("sport/sync-from-id")
    Object downSportDetail(@Body HealthyDataDownRequest healthyDataDownRequest, Continuation<? super QcResponse<? extends List<SportDetailResp>>> continuation);

    @POST("step/sync-from-time")
    Object downStepDetail(@Body HealthyDataDownRequest healthyDataDownRequest, Continuation<? super QcResponse<? extends List<StepDetailResp>>> continuation);

    @POST("temperature/sync-from-id")
    Object downTemperature(@Body TemperatureDownloadRequest temperatureDownloadRequest, Continuation<? super QcResponse<? extends List<TemperatureDownResp>>> continuation);

    @GET("customer/feedback")
    Object feedback(@Query("language") String str, Continuation<? super QcResponse<? extends List<FeedbackResp>>> continuation);

    @POST("customer/submit")
    @Multipart
    Object feedbackSubmit(@Query("typeId") int i, @Query("feedbackId") int i2, @Query("email") String str, @Query(Constant.MODIFY_ACTIVITY_INTENT_CONTENT) String str2, @Part List<MultipartBody.Part> list, Continuation<? super QcNoDataResponse> continuation);

    @POST("watchface/pay/generateOrder")
    Object generateOrder(@Body WatchFaceOrderGenerateParams watchFaceOrderGenerateParams, Continuation<? super QcResponse<GenerateOrderNumberResp>> continuation);

    @GET("device/config")
    Object getDeviceConfig(@Query("hardwareVersion") String str, Continuation<? super QcResponse<Integer>> continuation);

    @POST("device-file/find-list")
    Object getDeviceFileList(@Body DeviceMissingFileRequest deviceMissingFileRequest, Continuation<? super QcResponse<? extends ArrayList<DeviceMissFileResp>>> continuation);

    @GET("device/messagePush")
    Object getDeviceMessagePush(Continuation<? super QcResponse<? extends List<AndroidMessagePush>>> continuation);

    @GET("device/effectPicture")
    Object getDevicePicture(@Query("hardwareVersion") String str, Continuation<? super QcResponse<DevicePictureResp>> continuation);

    @GET("device/effectPicture/china")
    Object getDevicePictureChina(@Query("hardwareVersion") String str, Continuation<? super QcResponse<DevicePictureResp>> continuation);

    @GET("goals/my")
    Object getGoal(@Query("uid") long j, Continuation<? super QcResponse<GoalSettingRequest>> continuation);

    @POST("app-update/last-ota")
    Object getLastOta(@Body LastOtaRequest lastOtaRequest, Continuation<? super QcResponse<FirmwareOtaResp>> continuation);

    @POST("app-update/last-ota/china")
    Object getLastOtaChina(@Body LastOtaRequest lastOtaRequest, Continuation<? super QcResponse<FirmwareOtaResp>> continuation);

    @GET("token/getToken")
    Object getToken(@Query("key") String str, Continuation<? super QcResponse<String>> continuation);

    @GET("users/info")
    Object getUserProfile(@Query("uid") long j, Continuation<? super QcResponse<UserProfileResp>> continuation);

    @POST("users/reset-password-email")
    Object getVerifyCode(@Body FindPwdRequest findPwdRequest, Continuation<? super QcNoDataResponse> continuation);

    @GET("device-file/list-watch-face")
    Object getWatchFace(@Query("hardwareVersion") String str, Continuation<? super QcResponse<? extends List<WatchFaceResp>>> continuation);

    @GET("device/dialParameters")
    Object getWatchFaceDialParameters(@Query("hardwareVersion") String str, Continuation<? super QcResponse<CustomDialResp>> continuation);

    @GET("device/dialParameters/china")
    Object getWatchFaceDialParametersChina(@Query("hardwareVersion") String str, Continuation<? super QcResponse<CustomDialResp>> continuation);

    @GET("device-file/watchFaceVersion")
    Object getWatchFaceVersion(@Query("hardwareVersion") String str, Continuation<? super QcResponse<String>> continuation);

    @POST("goals/update-goals")
    Object goalUpdate(@Body GoalSettingRequest goalSettingRequest, Continuation<? super QcNoDataResponse> continuation);

    @GET("users/login/logoff")
    Object logOff(@Query("uid") String str, Continuation<? super QcNoDataResponse> continuation);

    @POST("users/login/v1")
    Object login(@Body LoginRequest loginRequest, Continuation<? super QcResponse<UserLoginResp>> continuation);

    @GET("ws/contact/messageList")
    Object messageList(@Query("contactId") String str, Continuation<? super QcResponse<? extends List<? extends WsChatMessage>>> continuation);

    @POST("users/image/upload")
    @Multipart
    Object protoImage(@Query("uid") long j, @Part MultipartBody.Part part, Continuation<? super QcResponse<String>> continuation);

    @GET("watchface/query/names/china")
    Object queryByNamesChina(@Query("hdName") String str, @Query("names") String str2, Continuation<? super QcResponse<? extends List<WatchFaceResp>>> continuation);

    @POST("ranking/download/update")
    Object rankingUpdate(@Body WatchFaceRanking watchFaceRanking, Continuation<? super QcNoDataResponse> continuation);

    @POST("users/register/v1")
    Object register(@Body LoginRequest loginRequest, Continuation<? super QcResponse<UserLoginResp>> continuation);

    @POST("users/reset-password")
    Object restPwd(@Body ResetPwdRequest resetPwdRequest, Continuation<? super QcNoDataResponse> continuation);

    @GET("device/scanConfig")
    Object scanConfig(@Query("app") String str, Continuation<? super QcResponse<String>> continuation);

    @GET("external/device/show/chat/withName")
    Object showSupportCs(Continuation<? super QcResponse<? extends List<SupportCsResp>>> continuation);

    @GET("ws/contact/unread")
    Object unread(@Query("contactId") String str, Continuation<? super QcResponse<Integer>> continuation);

    @POST("spo2/commit-list")
    Object upBloodOxygen(@Body BloodOxygenRequest bloodOxygenRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("blood-pressure/commit-list")
    Object upBloodPressure(@Body BloodPressureRequest bloodPressureRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("heart-rate-interval/commit")
    Object upIntervalHeart(@Body HeartRateIntervalRequest heartRateIntervalRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("sleep/commit")
    Object upSleepDetail(@Body SleepDetailRequest sleepDetailRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("sleep/commit/v1")
    Object upSleepDetailV1(@Body CommitSleepNewProtocolParam commitSleepNewProtocolParam, Continuation<? super QcNoDataResponse> continuation);

    @POST("sport/commit")
    Object upSportDetail(@Body SportDetailRequest sportDetailRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("step/commit")
    Object upStepDetail(@Body StepDetailRequest stepDetailRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("temperature/commit")
    Object upTemperature(@Body TemperatureRequest temperatureRequest, Continuation<? super QcNoDataResponse> continuation);

    @POST("users/update")
    Object updateProfile(@Body UserProfileRequest userProfileRequest, Continuation<? super QcNoDataResponse> continuation);

    @GET("watchface/download/type")
    Object watchFaceByType(@Query("hdName") String str, @Query("typeId") int i, Continuation<? super QcResponse<? extends List<WatchFaceResp>>> continuation);

    @GET("watchface/download/type/china/android")
    Object watchFaceByTypeChina(@Query("hdName") String str, @Query("typeId") int i, Continuation<? super QcResponse<? extends List<WatchFaceResp>>> continuation);

    @GET("watchface/index")
    Object watchFaceIndex(@Query("hdName") String str, Continuation<? super QcResponse<? extends List<WatchFaceIndex>>> continuation);

    @GET("watchface/index/china/android")
    Object watchFaceIndexChina(@Query("hdName") String str, Continuation<? super QcResponse<? extends List<WatchFaceIndex>>> continuation);

    @GET("watchface/pay/myAll")
    Object watchFacePays(@Query("hdName") String str, @Query("uid") String str2, Continuation<? super QcResponse<? extends List<WatchFaceOrderResp>>> continuation);

    @POST("watchface/pay/update")
    Object watchFacePaysUpdate(@Body WatchFaceOrderParams watchFaceOrderParams, Continuation<? super QcNoDataResponse> continuation);

    @GET("watchface/theme/list")
    Object watchThemeList(@Query("hdName") String str, Continuation<? super QcResponse<? extends List<WatchThemeResp>>> continuation);

    @GET("watchface/wallpaper/download/type")
    Object watchWallpaperByType(@Query("hdName") String str, @Query("typeId") int i, Continuation<? super QcResponse<? extends List<WatchWallpaperResp>>> continuation);

    @GET("watchface/wallpaper/list")
    Object watchWallpaperList(@Query("hdName") String str, Continuation<? super QcResponse<? extends List<WatchWallpaperResp>>> continuation);

    @GET("ranking/download/count")
    Object watchfaceDownloadCount(@Query("hdName") String str, @Query("name") String str2, Continuation<? super QcResponse<Integer>> continuation);

    @POST("weather-com/five-days-forecastAndroid")
    Object weatherInfo(@Body WeatherRequest weatherRequest, Continuation<? super QcResponse<? extends List<WeatherResp>>> continuation);

    /* compiled from: QcService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/api/QcService$Companion;", "", "()V", "APP_NAME", "", "Android_Token_Key", "BASE_URL", "BASE_URL_BACKUP", "BASE_URL_CHINA", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String APP_NAME = "QWatchPro";
        public static final String Android_Token_Key = "qcwx_android";
        public static final String BASE_URL = "https://api1.qcwxkjvip.com/qcwx/";
        public static final String BASE_URL_BACKUP = "https://china.qcwxwire.com/qcwx/";
        public static final String BASE_URL_CHINA = "https://china.qcwxwire.com/qcwx/";

        private Companion() {
        }
    }
}
