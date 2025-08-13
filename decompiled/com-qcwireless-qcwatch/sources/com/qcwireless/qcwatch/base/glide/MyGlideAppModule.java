package com.qcwireless.qcwatch.base.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/* loaded from: classes3.dex */
public class MyGlideAppModule extends AppGlideModule {
    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(Context context, Glide glide, Registry registry) {
        glide.getRegistry().replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient.Builder().connectTimeout(120L, TimeUnit.SECONDS).readTimeout(120L, TimeUnit.SECONDS).writeTimeout(120L, TimeUnit.SECONDS).build()));
    }

    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        long j = 104857600;
        builder.setMemoryCache(new LruResourceCache(j));
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, j));
    }
}
