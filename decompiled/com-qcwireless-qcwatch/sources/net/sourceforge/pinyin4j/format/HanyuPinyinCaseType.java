package net.sourceforge.pinyin4j.format;

/* loaded from: classes2.dex */
public class HanyuPinyinCaseType {
    protected String name;
    public static final HanyuPinyinCaseType UPPERCASE = new HanyuPinyinCaseType("UPPERCASE");
    public static final HanyuPinyinCaseType LOWERCASE = new HanyuPinyinCaseType("LOWERCASE");

    public String getName() {
        return this.name;
    }

    protected void setName(String str) {
        this.name = str;
    }

    protected HanyuPinyinCaseType(String str) {
        setName(str);
    }
}
