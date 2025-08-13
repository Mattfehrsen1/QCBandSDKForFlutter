package com.oudmon.ble.base.communication.bigData;

import java.util.List;

/* loaded from: classes3.dex */
public class CustomWatchFaceEntity {
    List<CustomElement> data;

    public List<CustomElement> getData() {
        return this.data;
    }

    public void setData(List<CustomElement> list) {
        this.data = list;
    }

    public static class CustomElement {
        int b;
        int g;
        int r;
        int type;
        int x;
        int y;

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public int getX() {
            return this.x;
        }

        public void setX(int i) {
            this.x = i;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int i) {
            this.y = i;
        }

        public int getR() {
            return this.r;
        }

        public void setR(int i) {
            this.r = i;
        }

        public int getG() {
            return this.g;
        }

        public void setG(int i) {
            this.g = i;
        }

        public int getB() {
            return this.b;
        }

        public void setB(int i) {
            this.b = i;
        }
    }
}
