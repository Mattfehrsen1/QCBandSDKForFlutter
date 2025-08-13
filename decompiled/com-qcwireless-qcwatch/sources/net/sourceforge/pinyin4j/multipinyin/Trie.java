package net.sourceforge.pinyin4j.multipinyin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

/* loaded from: classes2.dex */
public class Trie {
    private Trie nextTire;
    private String pinyin;
    private Hashtable<String, Trie> values = new Hashtable<>();

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }

    public Trie getNextTire() {
        return this.nextTire;
    }

    public void setNextTire(Trie trie) {
        this.nextTire = trie;
    }

    public synchronized void load(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            try {
                inputStreamReader = new InputStreamReader(inputStream);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line != null) {
                                String[] strArrSplit = line.split(" ");
                                if (strArrSplit.length == 2) {
                                    Trie trie = new Trie();
                                    trie.pinyin = strArrSplit[1];
                                    put(strArrSplit[0], trie);
                                }
                            } else {
                                inputStreamReader.close();
                                bufferedReader.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        } catch (Throwable th5) {
            inputStreamReader = null;
            th = th5;
            bufferedReader = null;
        }
    }

    public synchronized void loadMultiPinyin(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            try {
                inputStreamReader = new InputStreamReader(inputStream);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line != null) {
                                String[] strArrSplit = line.split(" ");
                                if (strArrSplit.length == 2) {
                                    int i = 0;
                                    String str = strArrSplit[0];
                                    String str2 = strArrSplit[1];
                                    char[] charArray = str.toCharArray();
                                    Trie trie = this;
                                    while (true) {
                                        if (i >= charArray.length) {
                                            break;
                                        }
                                        String upperCase = Integer.toHexString(charArray[i]).toUpperCase();
                                        Trie trie2 = trie.get(upperCase);
                                        if (trie2 == null) {
                                            trie.put(upperCase, new Trie());
                                            trie2 = trie.get(upperCase);
                                        }
                                        Trie nextTire = trie2.getNextTire();
                                        if (charArray.length - 1 == i) {
                                            trie2.pinyin = str2;
                                            break;
                                        }
                                        if (nextTire != null) {
                                            trie = nextTire;
                                        } else if (charArray.length - 1 != i) {
                                            trie = new Trie();
                                            trie2.setNextTire(trie);
                                            trie.put(Integer.toHexString(charArray[i + 1]).toUpperCase(), new Trie());
                                        }
                                        i++;
                                    }
                                }
                            } else {
                                inputStreamReader.close();
                                bufferedReader.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        } catch (Throwable th5) {
            inputStreamReader = null;
            th = th5;
            bufferedReader = null;
        }
    }

    public void loadMultiPinyinExtend() throws IOException {
        String str = MultiPinyinConfig.multiPinyinPath;
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                loadMultiPinyin(new FileInputStream(file));
            }
        }
    }

    public Trie get(String str) {
        return this.values.get(str);
    }

    public void put(String str, Trie trie) {
        this.values.put(str, trie);
    }
}
