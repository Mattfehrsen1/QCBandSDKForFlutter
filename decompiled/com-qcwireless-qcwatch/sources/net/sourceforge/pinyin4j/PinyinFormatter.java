package net.sourceforge.pinyin4j;

import kotlin.text.Typography;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* loaded from: classes2.dex */
class PinyinFormatter {
    PinyinFormatter() {
    }

    static String formatHanyuPinyin(String str, HanyuPinyinOutputFormat hanyuPinyinOutputFormat) throws BadHanyuPinyinOutputFormatCombination {
        if (HanyuPinyinToneType.WITH_TONE_MARK == hanyuPinyinOutputFormat.getToneType() && (HanyuPinyinVCharType.WITH_V == hanyuPinyinOutputFormat.getVCharType() || HanyuPinyinVCharType.WITH_U_AND_COLON == hanyuPinyinOutputFormat.getVCharType())) {
            throw new BadHanyuPinyinOutputFormatCombination("tone marks cannot be added to v or u:");
        }
        if (HanyuPinyinToneType.WITHOUT_TONE == hanyuPinyinOutputFormat.getToneType()) {
            str = str.replaceAll("[1-5]", "");
        } else if (HanyuPinyinToneType.WITH_TONE_MARK == hanyuPinyinOutputFormat.getToneType()) {
            str = convertToneNumber2ToneMark(str.replaceAll("u:", "v"));
        }
        if (HanyuPinyinVCharType.WITH_V == hanyuPinyinOutputFormat.getVCharType()) {
            str = str.replaceAll("u:", "v");
        } else if (HanyuPinyinVCharType.WITH_U_UNICODE == hanyuPinyinOutputFormat.getVCharType()) {
            str = str.replaceAll("u:", "ü");
        }
        return HanyuPinyinCaseType.UPPERCASE == hanyuPinyinOutputFormat.getCaseType() ? str.toUpperCase() : str;
    }

    private static String convertToneNumber2ToneMark(String str) {
        String lowerCase = str.toLowerCase();
        if (!lowerCase.matches("[a-z]*[1-5]?")) {
            return lowerCase;
        }
        if (lowerCase.matches("[a-z]*[1-5]")) {
            int numericValue = Character.getNumericValue(lowerCase.charAt(lowerCase.length() - 1));
            char cCharAt = 'a';
            int iIndexOf = lowerCase.indexOf(97);
            int iIndexOf2 = lowerCase.indexOf(101);
            int iIndexOf3 = lowerCase.indexOf("ou");
            if (-1 == iIndexOf) {
                if (-1 == iIndexOf2) {
                    if (-1 != iIndexOf3) {
                        cCharAt = "ou".charAt(0);
                        iIndexOf = iIndexOf3;
                    } else {
                        iIndexOf = lowerCase.length() - 1;
                        while (true) {
                            if (iIndexOf < 0) {
                                cCharAt = Typography.dollar;
                                iIndexOf = -1;
                                break;
                            }
                            if (String.valueOf(lowerCase.charAt(iIndexOf)).matches("[aeiouv]")) {
                                cCharAt = lowerCase.charAt(iIndexOf);
                                break;
                            }
                            iIndexOf--;
                        }
                    }
                } else {
                    iIndexOf = iIndexOf2;
                    cCharAt = 'e';
                }
            }
            if ('$' == cCharAt || -1 == iIndexOf) {
                return lowerCase;
            }
            return lowerCase.substring(0, iIndexOf).replaceAll("v", "ü") + "āáăàaēéĕèeīíĭìiōóŏòoūúŭùuǖǘǚǜü".charAt(("aeiouv".indexOf(cCharAt) * 5) + (numericValue - 1)) + lowerCase.substring(iIndexOf + 1, lowerCase.length() - 1).replaceAll("v", "ü");
        }
        return lowerCase.replaceAll("v", "ü");
    }
}
