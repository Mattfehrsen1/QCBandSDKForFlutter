package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Element;
import com.hp.hpl.sparta.ParseException;

/* loaded from: classes2.dex */
class GwoyeuRomatzyhTranslator {
    private static String[] tones = {"_I", "_II", "_III", "_IV", "_V"};

    GwoyeuRomatzyhTranslator() {
    }

    static String convertHanyuPinyinToGwoyeuRomatzyh(String str) {
        String strExtractPinyinString = TextHelper.extractPinyinString(str);
        String strExtractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element elementXpathSelectElement = GwoyeuRomatzyhResource.getInstance().getPinyinToGwoyeuMappingDoc().xpathSelectElement("//" + PinyinRomanizationType.HANYU_PINYIN.getTagName() + "[text()='" + strExtractPinyinString + "']");
            if (elementXpathSelectElement == null) {
                return null;
            }
            return elementXpathSelectElement.xpathSelectString("../" + PinyinRomanizationType.GWOYEU_ROMATZYH.getTagName() + tones[Integer.parseInt(strExtractToneNumber) - 1] + "/text()");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
