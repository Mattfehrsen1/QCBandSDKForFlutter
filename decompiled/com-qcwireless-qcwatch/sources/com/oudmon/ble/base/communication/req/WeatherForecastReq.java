package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.utils.DataParseUtils;

/* loaded from: classes3.dex */
public class WeatherForecastReq extends MixtureReq {
    private WeatherForecastReq() {
        super(Constants.CMD_SEND_WEATHER_FORECAST);
    }

    public static WeatherForecastReq getWriteInstance(WeatherForecastBuilder weatherForecastBuilder) {
        return new WeatherForecastReq() { // from class: com.oudmon.ble.base.communication.req.WeatherForecastReq.1
            {
                super();
                this.subData = new byte[10];
                this.subData[0] = (byte) this.val$builder.index;
                System.arraycopy(DataParseUtils.intToByteArray((int) this.val$builder.timeStamp), 0, this.subData, 1, 4);
                this.subData[5] = (byte) this.val$builder.weatherType;
                this.subData[6] = (byte) this.val$builder.minDegree;
                this.subData[7] = (byte) this.val$builder.maxDegree;
                this.subData[8] = (byte) this.val$builder.humidity;
                this.subData[9] = (byte) (this.val$builder.takeUmbrella ? 1 : 2);
            }
        };
    }

    public static class WeatherForecastBuilder {
        private int humidity;
        private int index;
        private int maxDegree;
        private int minDegree;
        private boolean takeUmbrella;
        private long timeStamp;
        private int weatherType;

        public WeatherForecastBuilder setIndex(int i) {
            this.index = i;
            return this;
        }

        public WeatherForecastBuilder setTimeStamp(long j) {
            this.timeStamp = j;
            return this;
        }

        public WeatherForecastBuilder setWeatherType(int i) {
            this.weatherType = i;
            return this;
        }

        public WeatherForecastBuilder setMinDegree(int i) {
            this.minDegree = i;
            return this;
        }

        public WeatherForecastBuilder setMaxDegree(int i) {
            this.maxDegree = i;
            return this;
        }

        public WeatherForecastBuilder setHumidity(int i) {
            this.humidity = i;
            return this;
        }

        public WeatherForecastBuilder setTakeUmbrella(boolean z) {
            this.takeUmbrella = z;
            return this;
        }

        public String toString() {
            return "WeatherForecastBuilder{index=" + this.index + ", timeStamp=" + this.timeStamp + ", weatherType=" + this.weatherType + ", minDegree=" + this.minDegree + ", maxDegree=" + this.maxDegree + ", humidity=" + this.humidity + ", takeUmbrella=" + this.takeUmbrella + '}';
        }
    }
}
