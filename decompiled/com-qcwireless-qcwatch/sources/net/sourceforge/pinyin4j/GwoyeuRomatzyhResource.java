package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Document;
import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes2.dex */
class GwoyeuRomatzyhResource {
    private Document pinyinToGwoyeuMappingDoc;

    private void setPinyinToGwoyeuMappingDoc(Document document) {
        this.pinyinToGwoyeuMappingDoc = document;
    }

    Document getPinyinToGwoyeuMappingDoc() {
        return this.pinyinToGwoyeuMappingDoc;
    }

    private GwoyeuRomatzyhResource() {
        initializeResource();
    }

    private void initializeResource() {
        try {
            setPinyinToGwoyeuMappingDoc(Parser.parse("", ResourceHelper.getResourceInputStream("/pinyindb/pinyin_gwoyeu_mapping.xml")));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    static GwoyeuRomatzyhResource getInstance() {
        return GwoyeuRomatzyhSystemResourceHolder.theInstance;
    }

    private static class GwoyeuRomatzyhSystemResourceHolder {
        static final GwoyeuRomatzyhResource theInstance = new GwoyeuRomatzyhResource();

        private GwoyeuRomatzyhSystemResourceHolder() {
        }
    }
}
