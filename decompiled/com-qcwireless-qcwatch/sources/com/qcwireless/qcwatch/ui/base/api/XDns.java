package com.qcwireless.qcwatch.ui.base.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import okhttp3.Dns;

/* loaded from: classes3.dex */
public class XDns implements Dns {
    private long timeout;

    public XDns(long timeout) {
        this.timeout = timeout;
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(final String hostname) throws UnknownHostException {
        try {
            FutureTask futureTask = new FutureTask(new Callable<List<InetAddress>>() { // from class: com.qcwireless.qcwatch.ui.base.api.XDns.1
                @Override // java.util.concurrent.Callable
                public List<InetAddress> call() throws Exception {
                    return Arrays.asList(InetAddress.getAllByName(hostname));
                }
            });
            new Thread(futureTask).start();
            return (List) futureTask.get(this.timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }
}
