package com.baidu.location.indoor.mapversion.b;

import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes.dex */
class b implements FilenameFilter {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    b(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.d(this.a));
        sb.append("_");
        return str.startsWith(sb.toString());
    }
}
