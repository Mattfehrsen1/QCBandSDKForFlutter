package com.baidu.bdhttpdns;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class BDHttpDnsResult {
    private ResolveType a;
    private final ResolveStatus b;
    private ArrayList<String> c;
    private ArrayList<String> d;

    public enum ResolveStatus {
        BDHttpDnsResolveOK,
        BDHttpDnsInputError,
        BDHttpDnsResolveErrorCacheMiss,
        BDHttpDnsResolveErrorDnsResolve
    }

    public enum ResolveType {
        RESOLVE_NONE,
        RESOLVE_NONEED,
        RESOLVE_FROM_HTTPDNS_CACHE,
        RESOLVE_FROM_HTTPDNS_EXPIRED_CACHE,
        RESOLVE_FROM_DNS_CACHE,
        RESOLVE_FROM_DNS
    }

    public BDHttpDnsResult(ResolveStatus resolveStatus) {
        this.a = ResolveType.RESOLVE_NONE;
        this.b = resolveStatus;
    }

    public BDHttpDnsResult(ResolveType resolveType, ResolveStatus resolveStatus, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        this.a = ResolveType.RESOLVE_NONE;
        this.a = resolveType;
        this.b = resolveStatus;
        this.c = arrayList;
        this.d = arrayList2;
    }

    public ArrayList<String> getIpv4List() {
        return this.c;
    }

    public ArrayList<String> getIpv6List() {
        return this.d;
    }

    public ResolveStatus getResolveStatus() {
        return this.b;
    }

    public ResolveType getResolveType() {
        return this.a;
    }
}
