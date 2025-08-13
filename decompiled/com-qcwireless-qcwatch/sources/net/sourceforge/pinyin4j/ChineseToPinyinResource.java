package net.sourceforge.pinyin4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.sourceforge.pinyin4j.multipinyin.Trie;

/* loaded from: classes2.dex */
class ChineseToPinyinResource {
    private Trie unicodeToHanyuPinyinTable;

    private void setUnicodeToHanyuPinyinTable(Trie trie) {
        this.unicodeToHanyuPinyinTable = trie;
    }

    Trie getUnicodeToHanyuPinyinTable() {
        return this.unicodeToHanyuPinyinTable;
    }

    private ChineseToPinyinResource() {
        this.unicodeToHanyuPinyinTable = null;
        initializeResource();
    }

    private void initializeResource() {
        try {
            setUnicodeToHanyuPinyinTable(new Trie());
            getUnicodeToHanyuPinyinTable().load(ResourceHelper.getResourceInputStream("/pinyindb/unicode_to_hanyu_pinyin.txt"));
            getUnicodeToHanyuPinyinTable().loadMultiPinyin(ResourceHelper.getResourceInputStream("/pinyindb/multi_pinyin.txt"));
            getUnicodeToHanyuPinyinTable().loadMultiPinyinExtend();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    Trie getHanyuPinyinTrie(char c) {
        return getUnicodeToHanyuPinyinTable().get(Integer.toHexString(c).toUpperCase());
    }

    String[] getHanyuPinyinStringArray(char c) {
        return parsePinyinString(getHanyuPinyinRecordFromChar(c));
    }

    String[] parsePinyinString(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")).split(",");
    }

    private boolean isValidRecord(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    private String getHanyuPinyinRecordFromChar(char c) {
        Trie trie = getUnicodeToHanyuPinyinTable().get(Integer.toHexString(c).toUpperCase());
        String pinyin = trie != null ? trie.getPinyin() : null;
        if (isValidRecord(pinyin)) {
            return pinyin;
        }
        return null;
    }

    static ChineseToPinyinResource getInstance() {
        return ChineseToPinyinResourceHolder.theInstance;
    }

    private static class ChineseToPinyinResourceHolder {
        static final ChineseToPinyinResource theInstance = new ChineseToPinyinResource();

        private ChineseToPinyinResourceHolder() {
        }
    }

    class Field {
        static final String COMMA = ",";
        static final String LEFT_BRACKET = "(";
        static final String RIGHT_BRACKET = ")";

        Field() {
        }
    }
}
