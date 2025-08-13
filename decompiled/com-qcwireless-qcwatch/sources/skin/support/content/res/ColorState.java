package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import skin.support.exception.SkinCompatException;
import skin.support.utils.Slog;

/* loaded from: classes5.dex */
public final class ColorState {
    private static final String TAG = "ColorState";
    String colorAccelerated;
    String colorActivated;
    String colorChecked;
    String colorDefault;
    String colorDragCanAccept;
    String colorDragHovered;
    String colorEnabled;
    String colorFocused;
    String colorHovered;
    String colorName;
    String colorPressed;
    String colorSelected;
    String colorWindowFocused;
    boolean onlyDefaultColor;

    ColorState(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.colorWindowFocused = str;
        this.colorSelected = str2;
        this.colorFocused = str3;
        this.colorEnabled = str4;
        this.colorPressed = str5;
        this.colorChecked = str6;
        this.colorActivated = str7;
        this.colorAccelerated = str8;
        this.colorHovered = str9;
        this.colorDragCanAccept = str10;
        this.colorDragHovered = str11;
        this.colorDefault = str12;
        boolean z = TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4) && TextUtils.isEmpty(str5) && TextUtils.isEmpty(str6) && TextUtils.isEmpty(str7) && TextUtils.isEmpty(str8) && TextUtils.isEmpty(str9) && TextUtils.isEmpty(str10) && TextUtils.isEmpty(str11);
        this.onlyDefaultColor = z;
        if (z && !str12.startsWith("#")) {
            throw new SkinCompatException("Default color cannot be a reference, when only default color is available!");
        }
    }

    ColorState(String str, String str2) {
        this.colorName = str;
        this.colorDefault = str2;
        this.onlyDefaultColor = true;
        if (!str2.startsWith("#")) {
            throw new SkinCompatException("Default color cannot be a reference, when only default color is available!");
        }
    }

    public boolean isOnlyDefaultColor() {
        return this.onlyDefaultColor;
    }

    public String getColorName() {
        return this.colorName;
    }

    public String getColorWindowFocused() {
        return this.colorWindowFocused;
    }

    public String getColorSelected() {
        return this.colorSelected;
    }

    public String getColorFocused() {
        return this.colorFocused;
    }

    public String getColorEnabled() {
        return this.colorEnabled;
    }

    public String getColorPressed() {
        return this.colorPressed;
    }

    public String getColorChecked() {
        return this.colorChecked;
    }

    public String getColorActivated() {
        return this.colorActivated;
    }

    public String getColorAccelerated() {
        return this.colorAccelerated;
    }

    public String getColorHovered() {
        return this.colorHovered;
    }

    public String getColorDragCanAccept() {
        return this.colorDragCanAccept;
    }

    public String getColorDragHovered() {
        return this.colorDragHovered;
    }

    public String getColorDefault() {
        return this.colorDefault;
    }

    ColorStateList parse() {
        if (this.onlyDefaultColor) {
            return ColorStateList.valueOf(Color.parseColor(this.colorDefault));
        }
        return parseAll();
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.res.ColorStateList parseAll() {
        /*
            Method dump skipped, instructions count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.content.res.ColorState.parseAll():android.content.res.ColorStateList");
    }

    private String getColorStr(String str) {
        if (str.startsWith("#")) {
            return str;
        }
        ColorState colorState = SkinCompatUserThemeManager.get().getColorState(str);
        if (colorState == null) {
            return null;
        }
        if (colorState.isOnlyDefaultColor()) {
            return colorState.colorDefault;
        }
        if (!Slog.DEBUG) {
            return null;
        }
        Slog.i(TAG, str + " cannot reference " + colorState.colorName);
        return null;
    }

    static boolean checkColorValid(String str, String str2) {
        boolean z = !TextUtils.isEmpty(str2) && (!str2.startsWith("#") || str2.length() == 7 || str2.length() == 9);
        if (Slog.DEBUG && !z) {
            Slog.i(TAG, "Invalid color -> " + str + ": " + str2);
        }
        return z;
    }

    static JSONObject toJSONObject(ColorState colorState) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (colorState.onlyDefaultColor) {
            jSONObject.putOpt("colorName", colorState.colorName).putOpt("colorDefault", colorState.colorDefault).putOpt("onlyDefaultColor", Boolean.valueOf(colorState.onlyDefaultColor));
        } else {
            jSONObject.putOpt("colorName", colorState.colorName).putOpt("colorWindowFocused", colorState.colorWindowFocused).putOpt("colorSelected", colorState.colorSelected).putOpt("colorFocused", colorState.colorFocused).putOpt("colorEnabled", colorState.colorEnabled).putOpt("colorPressed", colorState.colorPressed).putOpt("colorChecked", colorState.colorChecked).putOpt("colorActivated", colorState.colorActivated).putOpt("colorAccelerated", colorState.colorAccelerated).putOpt("colorHovered", colorState.colorHovered).putOpt("colorDragCanAccept", colorState.colorDragCanAccept).putOpt("colorDragHovered", colorState.colorDragHovered).putOpt("colorDefault", colorState.colorDefault).putOpt("onlyDefaultColor", Boolean.valueOf(colorState.onlyDefaultColor));
        }
        return jSONObject;
    }

    static ColorState fromJSONObject(JSONObject jSONObject) {
        if (!jSONObject.has("colorName") || !jSONObject.has("colorDefault") || !jSONObject.has("onlyDefaultColor")) {
            return null;
        }
        try {
            boolean z = jSONObject.getBoolean("onlyDefaultColor");
            String string = jSONObject.getString("colorName");
            String string2 = jSONObject.getString("colorDefault");
            if (z) {
                return new ColorState(string, string2);
            }
            ColorBuilder colorBuilder = new ColorBuilder();
            colorBuilder.setColorDefault(string2);
            if (jSONObject.has("colorWindowFocused")) {
                colorBuilder.setColorWindowFocused(jSONObject.getString("colorWindowFocused"));
            }
            if (jSONObject.has("colorSelected")) {
                colorBuilder.setColorSelected(jSONObject.getString("colorSelected"));
            }
            if (jSONObject.has("colorFocused")) {
                colorBuilder.setColorFocused(jSONObject.getString("colorFocused"));
            }
            if (jSONObject.has("colorEnabled")) {
                colorBuilder.setColorEnabled(jSONObject.getString("colorEnabled"));
            }
            if (jSONObject.has("colorPressed")) {
                colorBuilder.setColorPressed(jSONObject.getString("colorPressed"));
            }
            if (jSONObject.has("colorChecked")) {
                colorBuilder.setColorChecked(jSONObject.getString("colorChecked"));
            }
            if (jSONObject.has("colorActivated")) {
                colorBuilder.setColorActivated(jSONObject.getString("colorActivated"));
            }
            if (jSONObject.has("colorAccelerated")) {
                colorBuilder.setColorAccelerated(jSONObject.getString("colorAccelerated"));
            }
            if (jSONObject.has("colorHovered")) {
                colorBuilder.setColorHovered(jSONObject.getString("colorHovered"));
            }
            if (jSONObject.has("colorDragCanAccept")) {
                colorBuilder.setColorDragCanAccept(jSONObject.getString("colorDragCanAccept"));
            }
            if (jSONObject.has("colorDragHovered")) {
                colorBuilder.setColorDragHovered(jSONObject.getString("colorDragHovered"));
            }
            ColorState colorStateBuild = colorBuilder.build();
            colorStateBuild.colorName = string;
            return colorStateBuild;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class ColorBuilder {
        String colorAccelerated;
        String colorActivated;
        String colorChecked;
        String colorDefault;
        String colorDragCanAccept;
        String colorDragHovered;
        String colorEnabled;
        String colorFocused;
        String colorHovered;
        String colorPressed;
        String colorSelected;
        String colorWindowFocused;

        public ColorBuilder() {
        }

        public ColorBuilder(ColorState colorState) {
            this.colorWindowFocused = colorState.colorWindowFocused;
            this.colorSelected = colorState.colorSelected;
            this.colorFocused = colorState.colorFocused;
            this.colorEnabled = colorState.colorEnabled;
            this.colorPressed = colorState.colorPressed;
            this.colorChecked = colorState.colorChecked;
            this.colorActivated = colorState.colorActivated;
            this.colorAccelerated = colorState.colorAccelerated;
            this.colorHovered = colorState.colorHovered;
            this.colorDragCanAccept = colorState.colorDragCanAccept;
            this.colorDragHovered = colorState.colorDragHovered;
            this.colorDefault = colorState.colorDefault;
        }

        public ColorBuilder setColorWindowFocused(String str) {
            if (ColorState.checkColorValid("colorWindowFocused", str)) {
                this.colorWindowFocused = str;
            }
            return this;
        }

        public ColorBuilder setColorWindowFocused(Context context, int i) {
            this.colorWindowFocused = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorSelected(String str) {
            if (ColorState.checkColorValid("colorSelected", str)) {
                this.colorSelected = str;
            }
            return this;
        }

        public ColorBuilder setColorSelected(Context context, int i) {
            this.colorSelected = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorFocused(String str) {
            if (ColorState.checkColorValid("colorFocused", str)) {
                this.colorFocused = str;
            }
            return this;
        }

        public ColorBuilder setColorFocused(Context context, int i) {
            this.colorFocused = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorEnabled(String str) {
            if (ColorState.checkColorValid("colorEnabled", str)) {
                this.colorEnabled = str;
            }
            return this;
        }

        public ColorBuilder setColorEnabled(Context context, int i) {
            this.colorEnabled = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorChecked(String str) {
            if (ColorState.checkColorValid("colorChecked", str)) {
                this.colorChecked = str;
            }
            return this;
        }

        public ColorBuilder setColorChecked(Context context, int i) {
            this.colorChecked = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorPressed(String str) {
            if (ColorState.checkColorValid("colorPressed", str)) {
                this.colorPressed = str;
            }
            return this;
        }

        public ColorBuilder setColorPressed(Context context, int i) {
            this.colorPressed = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorActivated(String str) {
            if (ColorState.checkColorValid("colorActivated", str)) {
                this.colorActivated = str;
            }
            return this;
        }

        public ColorBuilder setColorActivated(Context context, int i) {
            this.colorActivated = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorAccelerated(String str) {
            if (ColorState.checkColorValid("colorAccelerated", str)) {
                this.colorAccelerated = str;
            }
            return this;
        }

        public ColorBuilder setColorAccelerated(Context context, int i) {
            this.colorAccelerated = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorHovered(String str) {
            if (ColorState.checkColorValid("colorHovered", str)) {
                this.colorHovered = str;
            }
            return this;
        }

        public ColorBuilder setColorHovered(Context context, int i) {
            this.colorHovered = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorDragCanAccept(String str) {
            if (ColorState.checkColorValid("colorDragCanAccept", str)) {
                this.colorDragCanAccept = str;
            }
            return this;
        }

        public ColorBuilder setColorDragCanAccept(Context context, int i) {
            this.colorDragCanAccept = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorDragHovered(String str) {
            if (ColorState.checkColorValid("colorDragHovered", str)) {
                this.colorDragHovered = str;
            }
            return this;
        }

        public ColorBuilder setColorDragHovered(Context context, int i) {
            this.colorDragHovered = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorBuilder setColorDefault(String str) {
            if (ColorState.checkColorValid("colorDefault", str)) {
                this.colorDefault = str;
            }
            return this;
        }

        public ColorBuilder setColorDefault(Context context, int i) {
            this.colorDefault = context.getResources().getResourceEntryName(i);
            return this;
        }

        public ColorState build() {
            if (TextUtils.isEmpty(this.colorDefault)) {
                throw new SkinCompatException("Default color can not empty!");
            }
            return new ColorState(this.colorWindowFocused, this.colorSelected, this.colorFocused, this.colorEnabled, this.colorPressed, this.colorChecked, this.colorActivated, this.colorAccelerated, this.colorHovered, this.colorDragCanAccept, this.colorDragHovered, this.colorDefault);
        }
    }
}
