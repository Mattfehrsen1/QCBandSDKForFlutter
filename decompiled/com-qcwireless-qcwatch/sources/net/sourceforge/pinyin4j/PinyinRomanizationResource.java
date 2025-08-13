package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Document;
import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes2.dex */
class PinyinRomanizationResource {
    private Document pinyinMappingDoc;

    private void setPinyinMappingDoc(Document document) {
        this.pinyinMappingDoc = document;
    }

    Document getPinyinMappingDoc() {
        return this.pinyinMappingDoc;
    }

    private PinyinRomanizationResource() {
        initializeResource();
    }

    private void initializeResource() {
        try {
            setPinyinMappingDoc(Parser.parse("", ResourceHelper.getResourceInputStream("/pinyindb/pinyin_mapping.xml")));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    static PinyinRomanizationResource getInstance() {
        return PinyinRomanizationSystemResourceHolder.theInstance;
    }

    private static class PinyinRomanizationSystemResourceHolder {
        static final PinyinRomanizationResource theInstance = new PinyinRomanizationResource();

        private PinyinRomanizationSystemResourceHolder() {
        }
    }
}
