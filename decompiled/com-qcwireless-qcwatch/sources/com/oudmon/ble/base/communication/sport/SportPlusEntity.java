package com.oudmon.ble.base.communication.sport;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SportPlusEntity {
    public float mCalories;
    public int mDistance;
    public int mDownhill;
    public int mDuration;
    public int mElevation;
    public List<SportLocation> mLocations = new ArrayList();
    public int mRateAvg;
    public int mRateMax;
    public int mRateMin;
    public int mSpeedAvg;
    public int mSpeedMax;
    public int mSportCount;
    public int mSportType;
    public int mStartTime;
    public int mStepRate;
    public int mUphill;
    public int steps;

    public String toString() {
        return "SportPlusEntity{mSportType=" + this.mSportType + ", mStartTime=" + this.mStartTime + ", mDuration=" + this.mDuration + ", mDistance=" + this.mDistance + ", mCalories=" + this.mCalories + ", mSpeedAvg=" + this.mSpeedAvg + ", mSpeedMax=" + this.mSpeedMax + ", mRateAvg=" + this.mRateAvg + ", mRateMin=" + this.mRateMin + ", mRateMax=" + this.mRateMax + ", mElevation=" + this.mElevation + ", mUphill=" + this.mUphill + ", mDownhill=" + this.mDownhill + ", mStepRate=" + this.mStepRate + ", mSportCount=" + this.mSportCount + ", mLocations=" + this.mLocations + ", steps=" + this.steps + '}';
    }
}
