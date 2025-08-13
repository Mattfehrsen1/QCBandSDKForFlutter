package coil.disk;

import coil.disk.DiskCache;
import coil.disk.DiskLruCache;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import okio.ByteString;
import okio.FileSystem;
import okio.Path;

/* compiled from: RealDiskCache.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000  2\u00020\u0001:\u0003 !\"B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH\u0096\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\f\u0010\u001f\u001a\u00020\u001a*\u00020\u001aH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006#"}, d2 = {"Lcoil/disk/RealDiskCache;", "Lcoil/disk/DiskCache;", "maxSize", "", "directory", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "cleanupDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(JLokio/Path;Lokio/FileSystem;Lkotlinx/coroutines/CoroutineDispatcher;)V", "cache", "Lcoil/disk/DiskLruCache;", "getDirectory", "()Lokio/Path;", "getFileSystem", "()Lokio/FileSystem;", "getMaxSize", "()J", "size", "getSize", "clear", "", "edit", "Lcoil/disk/DiskCache$Editor;", "key", "", "get", "Lcoil/disk/DiskCache$Snapshot;", "remove", "", "hash", "Companion", "RealEditor", "RealSnapshot", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RealDiskCache implements DiskCache {
    private static final int ENTRY_DATA = 1;
    private static final int ENTRY_METADATA = 0;
    private final DiskLruCache cache;
    private final Path directory;
    private final FileSystem fileSystem;
    private final long maxSize;

    public RealDiskCache(long j, Path path, FileSystem fileSystem, CoroutineDispatcher coroutineDispatcher) {
        this.maxSize = j;
        this.directory = path;
        this.fileSystem = fileSystem;
        this.cache = new DiskLruCache(getFileSystem(), getDirectory(), coroutineDispatcher, getMaxSize(), 1, 2);
    }

    @Override // coil.disk.DiskCache
    public long getMaxSize() {
        return this.maxSize;
    }

    @Override // coil.disk.DiskCache
    public Path getDirectory() {
        return this.directory;
    }

    @Override // coil.disk.DiskCache
    public FileSystem getFileSystem() {
        return this.fileSystem;
    }

    @Override // coil.disk.DiskCache
    public long getSize() {
        return this.cache.size();
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Snapshot get(String key) {
        DiskLruCache.Snapshot snapshot = this.cache.get(hash(key));
        return snapshot == null ? null : new RealSnapshot(snapshot);
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Editor edit(String key) {
        DiskLruCache.Editor editorEdit = this.cache.edit(hash(key));
        return editorEdit == null ? null : new RealEditor(editorEdit);
    }

    @Override // coil.disk.DiskCache
    public boolean remove(String key) {
        return this.cache.remove(hash(key));
    }

    @Override // coil.disk.DiskCache
    public void clear() {
        this.cache.evictAll();
    }

    private final String hash(String str) {
        return ByteString.INSTANCE.encodeUtf8(str).sha256().hex();
    }

    /* compiled from: RealDiskCache.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0012\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcoil/disk/RealDiskCache$RealSnapshot;", "Lcoil/disk/DiskCache$Snapshot;", "snapshot", "Lcoil/disk/DiskLruCache$Snapshot;", "Lcoil/disk/DiskLruCache;", "(Lcoil/disk/DiskLruCache$Snapshot;)V", "data", "Lokio/Path;", "getData", "()Lokio/Path;", "metadata", "getMetadata", "close", "", "closeAndEdit", "Lcoil/disk/RealDiskCache$RealEditor;", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class RealSnapshot implements DiskCache.Snapshot {
        private final DiskLruCache.Snapshot snapshot;

        public RealSnapshot(DiskLruCache.Snapshot snapshot) {
            this.snapshot = snapshot;
        }

        @Override // coil.disk.DiskCache.Snapshot
        public Path getMetadata() {
            return this.snapshot.file(0);
        }

        @Override // coil.disk.DiskCache.Snapshot
        public Path getData() {
            return this.snapshot.file(1);
        }

        @Override // coil.disk.DiskCache.Snapshot, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.snapshot.close();
        }

        @Override // coil.disk.DiskCache.Snapshot
        public RealEditor closeAndEdit() {
            DiskLruCache.Editor editorCloseAndEdit = this.snapshot.closeAndEdit();
            if (editorCloseAndEdit == null) {
                return null;
            }
            return new RealEditor(editorCloseAndEdit);
        }
    }

    /* compiled from: RealDiskCache.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u0011"}, d2 = {"Lcoil/disk/RealDiskCache$RealEditor;", "Lcoil/disk/DiskCache$Editor;", "editor", "Lcoil/disk/DiskLruCache$Editor;", "Lcoil/disk/DiskLruCache;", "(Lcoil/disk/DiskLruCache$Editor;)V", "data", "Lokio/Path;", "getData", "()Lokio/Path;", "metadata", "getMetadata", "abort", "", "commit", "commitAndGet", "Lcoil/disk/RealDiskCache$RealSnapshot;", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class RealEditor implements DiskCache.Editor {
        private final DiskLruCache.Editor editor;

        public RealEditor(DiskLruCache.Editor editor) {
            this.editor = editor;
        }

        @Override // coil.disk.DiskCache.Editor
        public Path getMetadata() {
            return this.editor.file(0);
        }

        @Override // coil.disk.DiskCache.Editor
        public Path getData() {
            return this.editor.file(1);
        }

        @Override // coil.disk.DiskCache.Editor
        public void commit() {
            this.editor.commit();
        }

        @Override // coil.disk.DiskCache.Editor
        public RealSnapshot commitAndGet() {
            DiskLruCache.Snapshot snapshotCommitAndGet = this.editor.commitAndGet();
            if (snapshotCommitAndGet == null) {
                return null;
            }
            return new RealSnapshot(snapshotCommitAndGet);
        }

        @Override // coil.disk.DiskCache.Editor
        public void abort() {
            this.editor.abort();
        }
    }
}
