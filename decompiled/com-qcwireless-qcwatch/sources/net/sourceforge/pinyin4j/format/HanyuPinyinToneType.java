package net.sourceforge.pinyin4j.format;

/* loaded from: classes2.dex */
public class HanyuPinyinToneType {
    protected String name;
    public static final HanyuPinyinToneType WITH_TONE_NUMBER = new HanyuPinyinToneType("WITH_TONE_NUMBER");
    public static final HanyuPinyinToneType WITHOUT_TONE = new HanyuPinyinToneType("WITHOUT_TONE");
    public static final HanyuPinyinToneType WITH_TONE_MARK = new HanyuPinyinToneType("WITH_TONE_MARK");

    public String getName() {
        return this.name;
    }

    protected void setName(String str) {
        this.name = str;
    }

    protected HanyuPinyinToneType(String str) {
        setName(str);
    }
}
