package com.baidu.bdhttpdns;

import android.util.LruCache;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class h {
    private final String a;
    private final LruCache<String, a> b = new LruCache<>(((int) Runtime.getRuntime().maxMemory()) / 16);
    private boolean c;

    static class a {
        private ArrayList<String> a;
        private ArrayList<String> b;
        private long c;
        private long d;

        a() {
        }

        public void a(long j) {
            this.c = j;
        }

        public void a(ArrayList<String> arrayList) {
            this.a = arrayList;
        }

        public boolean a() {
            return e() + this.c < System.currentTimeMillis() / 1000;
        }

        public ArrayList<String> b() {
            return this.a;
        }

        public void b(long j) {
            this.d = j;
        }

        public void b(ArrayList<String> arrayList) {
            this.b = arrayList;
        }

        public ArrayList<String> c() {
            return this.b;
        }

        public long d() {
            return this.c;
        }

        public long e() {
            return this.d;
        }
    }

    h(String str, boolean z) {
        this.c = false;
        this.a = str;
        this.c = z;
    }

    a a(String str) {
        a aVar = this.b.get(str);
        if (aVar == null || !aVar.a() || !this.c) {
            return aVar;
        }
        this.b.remove(str);
        l.a("Remove expired entry from %s cache while reading, host(%s)", this.a, str);
        return null;
    }

    void a() {
        this.b.evictAll();
        l.a("Clear %s cache", this.a);
    }

    void a(String str, a aVar) {
        ArrayList<String> arrayListB = aVar.b();
        ArrayList<String> arrayListC = aVar.c();
        if ((arrayListB == null || arrayListB.isEmpty()) && (arrayListC == null || arrayListC.isEmpty())) {
            return;
        }
        this.b.put(str, aVar);
        Object[] objArr = new Object[5];
        objArr[0] = this.a;
        objArr[1] = str;
        objArr[2] = arrayListB != null ? arrayListB.toString() : null;
        objArr[3] = arrayListC != null ? arrayListC.toString() : null;
        objArr[4] = Long.valueOf(aVar.d());
        l.a("Set entry to %s cache, host(%s), ipv4List(%s), ipv6List(%s), ttl(%d)", objArr);
    }

    void a(boolean z) {
        this.c = z;
    }

    ArrayList<String> b() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = this.b.snapshot().keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    void b(String str) {
        a aVarA = a(str);
        if (aVarA == null || !aVarA.a()) {
            return;
        }
        this.b.remove(str);
        l.a("Remove expired entry from %s cache, host(%s)", this.a, str);
    }
}
