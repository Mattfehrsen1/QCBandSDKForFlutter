package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Element;
import com.hp.hpl.sparta.ParseException;

/* loaded from: classes2.dex */
class PinyinRomanizationTranslator {
    PinyinRomanizationTranslator() {
    }

    static String convertRomanizationSystem(String str, PinyinRomanizationType pinyinRomanizationType, PinyinRomanizationType pinyinRomanizationType2) {
        String strExtractPinyinString = TextHelper.extractPinyinString(str);
        String strExtractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element elementXpathSelectElement = PinyinRomanizationResource.getInstance().getPinyinMappingDoc().xpathSelectElement("//" + pinyinRomanizationType.getTagName() + "[text()='" + strExtractPinyinString + "']");
            if (elementXpathSelectElement == null) {
                return null;
            }
            return elementXpathSelectElement.xpathSelectString("../" + pinyinRomanizationType2.getTagName() + "/text()") + strExtractToneNumber;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
