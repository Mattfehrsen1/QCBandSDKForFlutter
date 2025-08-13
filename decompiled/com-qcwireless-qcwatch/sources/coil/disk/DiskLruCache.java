package coil.disk;

import coil.util.FileSystems;
import com.androidnetworking.common.ANConstants;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okio.BufferedSink;
import okio.FileSystem;
import okio.ForwardingFileSystem;
import okio.Okio;
import okio.Path;
import okio.Sink;

/* compiled from: DiskLruCache.kt */
@Metadata(d1 = {"\u0000u\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014*\u0001\u0014\b\u0000\u0018\u0000 C2\u00060\u0001j\u0002`\u00022\u00020\u0003:\u0004CDEFB5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020'H\u0016J\u001c\u0010)\u001a\u00020'2\n\u0010*\u001a\u00060+R\u00020\u00002\u0006\u0010,\u001a\u00020\u0013H\u0002J\b\u0010-\u001a\u00020'H\u0002J\u0014\u0010.\u001a\b\u0018\u00010+R\u00020\u00002\u0006\u0010/\u001a\u00020\u001fJ\u0006\u00100\u001a\u00020'J\b\u00101\u001a\u00020'H\u0016J\u0017\u00102\u001a\b\u0018\u000103R\u00020\u00002\u0006\u0010/\u001a\u00020\u001fH\u0086\u0002J\u0006\u00104\u001a\u00020'J\b\u00105\u001a\u00020\u0013H\u0002J\b\u00106\u001a\u00020'H\u0002J\b\u00107\u001a\u00020\u001cH\u0002J\b\u00108\u001a\u00020'H\u0002J\b\u00109\u001a\u00020'H\u0002J\u0010\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u00020\u001fH\u0002J\u000e\u0010<\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u001fJ\u0014\u0010=\u001a\u00020\u00132\n\u0010>\u001a\u00060 R\u00020\u0000H\u0002J\b\u0010?\u001a\u00020\u0013H\u0002J\u0006\u0010%\u001a\u00020\u000bJ\b\u0010@\u001a\u00020'H\u0002J\u0010\u0010A\u001a\u00020'2\u0006\u0010/\u001a\u00020\u001fH\u0002J\b\u0010B\u001a\u00020'H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u001d\u001a&\u0012\u0004\u0012\u00020\u001f\u0012\b\u0012\u00060 R\u00020\u00000\u001ej\u0012\u0012\u0004\u0012\u00020\u001f\u0012\b\u0012\u00060 R\u00020\u0000`!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcoil/disk/DiskLruCache;", "Ljava/io/Closeable;", "Lokio/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokio/FileSystem;", "directory", "Lokio/Path;", "cleanupDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "maxSize", "", "appVersion", "", "valueCount", "(Lokio/FileSystem;Lokio/Path;Lkotlinx/coroutines/CoroutineDispatcher;JII)V", "cleanupScope", "Lkotlinx/coroutines/CoroutineScope;", "closed", "", "coil/disk/DiskLruCache$fileSystem$1", "Lcoil/disk/DiskLruCache$fileSystem$1;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lcoil/disk/DiskLruCache$Entry;", "Lkotlin/collections/LinkedHashMap;", "mostRecentRebuildFailed", "mostRecentTrimFailed", "operationsSinceRewrite", "size", "checkNotClosed", "", "close", "completeEdit", "editor", "Lcoil/disk/DiskLruCache$Editor;", ANConstants.SUCCESS, "delete", "edit", "key", "evictAll", "flush", "get", "Lcoil/disk/DiskLruCache$Snapshot;", "initialize", "journalRewriteRequired", "launchCleanup", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "remove", "removeEntry", "entry", "removeOldestEntry", "trimToSize", "validateKey", "writeJournal", "Companion", "Editor", "Entry", "Snapshot", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DiskLruCache implements Closeable, Flushable {
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TMP = "journal.tmp";
    public static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    public static final String VERSION = "1";
    private final int appVersion;
    private final CoroutineScope cleanupScope;
    private boolean closed;
    private final Path directory;
    private final DiskLruCache$fileSystem$1 fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final Path journalFile;
    private final Path journalFileBackup;
    private final Path journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries;
    private final long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private int operationsSinceRewrite;
    private long size;
    private final int valueCount;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");

    /* JADX WARN: Type inference failed for: r6v11, types: [coil.disk.DiskLruCache$fileSystem$1] */
    public DiskLruCache(final FileSystem fileSystem, Path path, CoroutineDispatcher coroutineDispatcher, long j, int i, int i2) {
        this.directory = path;
        this.maxSize = j;
        this.appVersion = i;
        this.valueCount = i2;
        if (!(j > 0)) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        if (!(i2 > 0)) {
            throw new IllegalArgumentException("valueCount <= 0".toString());
        }
        this.journalFile = path.resolve(JOURNAL_FILE);
        this.journalFileTmp = path.resolve(JOURNAL_FILE_TMP);
        this.journalFileBackup = path.resolve(JOURNAL_FILE_BACKUP);
        this.lruEntries = new LinkedHashMap<>(0, 0.75f, true);
        this.cleanupScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(coroutineDispatcher.limitedParallelism(1)));
        this.fileSystem = new ForwardingFileSystem() { // from class: coil.disk.DiskLruCache$fileSystem$1
            {
                super(this.$fileSystem);
            }

            @Override // okio.ForwardingFileSystem, okio.FileSystem
            public Sink sink(Path file, boolean mustCreate) {
                Path pathParent = file.parent();
                if (pathParent != null) {
                    createDirectories(pathParent);
                }
                return super.sink(file, mustCreate);
            }
        };
    }

    public final synchronized void initialize() {
        if (this.initialized) {
            return;
        }
        delete(this.journalFileTmp);
        if (exists(this.journalFileBackup)) {
            if (exists(this.journalFile)) {
                delete(this.journalFileBackup);
            } else {
                atomicMove(this.journalFileBackup, this.journalFile);
            }
        }
        if (exists(this.journalFile)) {
            try {
                readJournal();
                processJournal();
                this.initialized = true;
                return;
            } catch (IOException unused) {
                try {
                    delete();
                    this.closed = false;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
        }
        writeJournal();
        this.initialized = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void readJournal() throws java.lang.Throwable {
        /*
            r12 = this;
            java.lang.String r0 = ", "
            coil.disk.DiskLruCache$fileSystem$1 r1 = r12.fileSystem
            okio.FileSystem r1 = (okio.FileSystem) r1
            okio.Path r2 = r12.journalFile
            okio.Source r1 = r1.source(r2)
            okio.BufferedSource r1 = okio.Okio.buffer(r1)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = 0
            r3 = r1
            okio.BufferedSource r3 = (okio.BufferedSource) r3     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r4 = r3.readUtf8LineStrict()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r5 = r3.readUtf8LineStrict()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r6 = r3.readUtf8LineStrict()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r7 = r3.readUtf8LineStrict()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r8 = r3.readUtf8LineStrict()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r9 = "libcore.io.DiskLruCache"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r4)     // Catch: java.lang.Throwable -> Lbd
            if (r9 == 0) goto L88
            java.lang.String r9 = "1"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r5)     // Catch: java.lang.Throwable -> Lbd
            if (r9 == 0) goto L88
            int r9 = r12.appVersion     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> Lbd
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r6)     // Catch: java.lang.Throwable -> Lbd
            if (r9 == 0) goto L88
            int r9 = r12.valueCount     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> Lbd
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r7)     // Catch: java.lang.Throwable -> Lbd
            if (r9 == 0) goto L88
            r9 = r8
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch: java.lang.Throwable -> Lbd
            int r9 = r9.length()     // Catch: java.lang.Throwable -> Lbd
            r10 = 0
            if (r9 <= 0) goto L5f
            r9 = 1
            goto L60
        L5f:
            r9 = 0
        L60:
            if (r9 != 0) goto L88
        L62:
            java.lang.String r0 = r3.readUtf8LineStrict()     // Catch: java.io.EOFException -> L6c java.lang.Throwable -> Lbd
            r12.readJournalLine(r0)     // Catch: java.io.EOFException -> L6c java.lang.Throwable -> Lbd
            int r10 = r10 + 1
            goto L62
        L6c:
            java.util.LinkedHashMap<java.lang.String, coil.disk.DiskLruCache$Entry> r0 = r12.lruEntries     // Catch: java.lang.Throwable -> Lbd
            int r0 = r0.size()     // Catch: java.lang.Throwable -> Lbd
            int r10 = r10 - r0
            r12.operationsSinceRewrite = r10     // Catch: java.lang.Throwable -> Lbd
            boolean r0 = r3.exhausted()     // Catch: java.lang.Throwable -> Lbd
            if (r0 != 0) goto L7f
            r12.writeJournal()     // Catch: java.lang.Throwable -> Lbd
            goto L85
        L7f:
            okio.BufferedSink r0 = r12.newJournalWriter()     // Catch: java.lang.Throwable -> Lbd
            r12.journalWriter = r0     // Catch: java.lang.Throwable -> Lbd
        L85:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lbd
            goto Lc1
        L88:
            java.io.IOException r3 = new java.io.IOException     // Catch: java.lang.Throwable -> Lbd
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd
            r9.<init>()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r10 = "unexpected journal header: ["
            r9.append(r10)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r4)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r0)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r5)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r0)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r6)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r0)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r7)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r0)     // Catch: java.lang.Throwable -> Lbd
            r9.append(r8)     // Catch: java.lang.Throwable -> Lbd
            r0 = 93
            r9.append(r0)     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r0 = r9.toString()     // Catch: java.lang.Throwable -> Lbd
            r3.<init>(r0)     // Catch: java.lang.Throwable -> Lbd
            throw r3     // Catch: java.lang.Throwable -> Lbd
        Lbd:
            r0 = move-exception
            r11 = r2
            r2 = r0
            r0 = r11
        Lc1:
            if (r1 != 0) goto Lc4
            goto Ld0
        Lc4:
            r1.close()     // Catch: java.lang.Throwable -> Lc8
            goto Ld0
        Lc8:
            r1 = move-exception
            if (r2 != 0) goto Lcd
            r2 = r1
            goto Ld0
        Lcd:
            kotlin.ExceptionsKt.addSuppressed(r2, r1)
        Ld0:
            if (r2 != 0) goto Ld6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            return
        Ld6:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.disk.DiskLruCache.readJournal():void");
    }

    private final BufferedSink newJournalWriter() {
        return Okio.buffer(new FaultHidingSink(appendingSink(this.journalFile), new Function1<IOException, Unit>() { // from class: coil.disk.DiskLruCache$newJournalWriter$faultHidingSink$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IOException iOException) {
                invoke2(iOException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IOException iOException) {
                this.this$0.hasJournalErrors = true;
            }
        }));
    }

    private final void readJournalLine(String line) throws IOException {
        String strSubstring;
        String str = line;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, ' ', 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            throw new IOException(Intrinsics.stringPlus("unexpected journal line: ", line));
        }
        int i = iIndexOf$default + 1;
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, ' ', i, false, 4, (Object) null);
        if (iIndexOf$default2 == -1) {
            strSubstring = line.substring(i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
            if (iIndexOf$default == 6 && StringsKt.startsWith$default(line, REMOVE, false, 2, (Object) null)) {
                this.lruEntries.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = line.substring(i, iIndexOf$default2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        LinkedHashMap<String, Entry> linkedHashMap = this.lruEntries;
        Entry entry = linkedHashMap.get(strSubstring);
        if (entry == null) {
            entry = new Entry(strSubstring);
            linkedHashMap.put(strSubstring, entry);
        }
        Entry entry2 = entry;
        if (iIndexOf$default2 != -1 && iIndexOf$default == 5 && StringsKt.startsWith$default(line, CLEAN, false, 2, (Object) null)) {
            String strSubstring2 = line.substring(iIndexOf$default2 + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
            List<String> listSplit$default = StringsKt.split$default((CharSequence) strSubstring2, new char[]{' '}, false, 0, 6, (Object) null);
            entry2.setReadable(true);
            entry2.setCurrentEditor(null);
            entry2.setLengths(listSplit$default);
            return;
        }
        if (iIndexOf$default2 == -1 && iIndexOf$default == 5 && StringsKt.startsWith$default(line, DIRTY, false, 2, (Object) null)) {
            entry2.setCurrentEditor(new Editor(entry2));
        } else if (iIndexOf$default2 != -1 || iIndexOf$default != 4 || !StringsKt.startsWith$default(line, READ, false, 2, (Object) null)) {
            throw new IOException(Intrinsics.stringPlus("unexpected journal line: ", line));
        }
    }

    private final void processJournal() {
        Iterator<Entry> it = this.lruEntries.values().iterator();
        long j = 0;
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.getCurrentEditor() == null) {
                int i2 = this.valueCount;
                while (i < i2) {
                    j += next.getLengths()[i];
                    i++;
                }
            } else {
                next.setCurrentEditor(null);
                int i3 = this.valueCount;
                while (i < i3) {
                    delete(next.getCleanFiles().get(i));
                    delete(next.getDirtyFiles().get(i));
                    i++;
                }
                it.remove();
            }
        }
        this.size = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void writeJournal() {
        Unit unit;
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        BufferedSink bufferedSinkBuffer = Okio.buffer(sink(this.journalFileTmp, false));
        Throwable th = null;
        try {
            BufferedSink bufferedSink2 = bufferedSinkBuffer;
            bufferedSink2.writeUtf8(MAGIC).writeByte(10);
            bufferedSink2.writeUtf8("1").writeByte(10);
            bufferedSink2.writeDecimalLong(this.appVersion).writeByte(10);
            bufferedSink2.writeDecimalLong(this.valueCount).writeByte(10);
            bufferedSink2.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.getCurrentEditor() != null) {
                    bufferedSink2.writeUtf8(DIRTY);
                    bufferedSink2.writeByte(32);
                    bufferedSink2.writeUtf8(entry.getKey());
                    bufferedSink2.writeByte(10);
                } else {
                    bufferedSink2.writeUtf8(CLEAN);
                    bufferedSink2.writeByte(32);
                    bufferedSink2.writeUtf8(entry.getKey());
                    entry.writeLengths(bufferedSink2);
                    bufferedSink2.writeByte(10);
                }
            }
            unit = Unit.INSTANCE;
        } catch (Throwable th2) {
            unit = null;
            th = th2;
        }
        if (bufferedSinkBuffer != null) {
            try {
                bufferedSinkBuffer.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                } else {
                    ExceptionsKt.addSuppressed(th, th3);
                }
            }
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(unit);
        if (exists(this.journalFile)) {
            atomicMove(this.journalFile, this.journalFileBackup);
            atomicMove(this.journalFileTmp, this.journalFile);
            delete(this.journalFileBackup);
        } else {
            atomicMove(this.journalFileTmp, this.journalFile);
        }
        this.journalWriter = newJournalWriter();
        this.operationsSinceRewrite = 0;
        this.hasJournalErrors = false;
        this.mostRecentRebuildFailed = false;
    }

    public final synchronized Snapshot get(String key) {
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        Snapshot snapshot = entry == null ? null : entry.snapshot();
        if (snapshot == null) {
            return null;
        }
        this.operationsSinceRewrite++;
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        bufferedSink.writeUtf8(READ);
        bufferedSink.writeByte(32);
        bufferedSink.writeUtf8(key);
        bufferedSink.writeByte(10);
        if (journalRewriteRequired()) {
            launchCleanup();
        }
        return snapshot;
    }

    public final synchronized Editor edit(String key) {
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if ((entry == null ? null : entry.getCurrentEditor()) != null) {
            return null;
        }
        if (entry != null && entry.getLockingSnapshotCount() != 0) {
            return null;
        }
        if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.writeUtf8(DIRTY);
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(key);
            bufferedSink.writeByte(10);
            bufferedSink.flush();
            if (this.hasJournalErrors) {
                return null;
            }
            if (entry == null) {
                entry = new Entry(key);
                this.lruEntries.put(key, entry);
            }
            Editor editor = new Editor(entry);
            entry.setCurrentEditor(editor);
            return editor;
        }
        launchCleanup();
        return null;
    }

    public final synchronized long size() {
        initialize();
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void completeEdit(Editor editor, boolean success) {
        Entry entry = editor.getEntry();
        if (!Intrinsics.areEqual(entry.getCurrentEditor(), editor)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        int i = 0;
        if (success && !entry.getZombie()) {
            int i2 = this.valueCount;
            int i3 = 0;
            while (i3 < i2) {
                int i4 = i3 + 1;
                if (editor.getWritten()[i3] && !exists(entry.getDirtyFiles().get(i3))) {
                    editor.abort();
                    return;
                }
                i3 = i4;
            }
            int i5 = this.valueCount;
            while (i < i5) {
                int i6 = i + 1;
                Path path = entry.getDirtyFiles().get(i);
                Path path2 = entry.getCleanFiles().get(i);
                if (exists(path)) {
                    atomicMove(path, path2);
                } else {
                    FileSystems.createFile(this.fileSystem, entry.getCleanFiles().get(i));
                }
                long j = entry.getLengths()[i];
                Long size = metadata(path2).getSize();
                long jLongValue = size == null ? 0L : size.longValue();
                entry.getLengths()[i] = jLongValue;
                this.size = (this.size - j) + jLongValue;
                i = i6;
            }
        } else {
            int i7 = this.valueCount;
            while (i < i7) {
                delete(entry.getDirtyFiles().get(i));
                i++;
            }
        }
        entry.setCurrentEditor(null);
        if (entry.getZombie()) {
            removeEntry(entry);
            return;
        }
        this.operationsSinceRewrite++;
        BufferedSink bufferedSink = this.journalWriter;
        Intrinsics.checkNotNull(bufferedSink);
        if (success || entry.getReadable()) {
            entry.setReadable(true);
            bufferedSink.writeUtf8(CLEAN);
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(entry.getKey());
            entry.writeLengths(bufferedSink);
            bufferedSink.writeByte(10);
        } else {
            this.lruEntries.remove(entry.getKey());
            bufferedSink.writeUtf8(REMOVE);
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(entry.getKey());
            bufferedSink.writeByte(10);
        }
        bufferedSink.flush();
        if (this.size > this.maxSize || journalRewriteRequired()) {
            launchCleanup();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean journalRewriteRequired() {
        return this.operationsSinceRewrite >= 2000;
    }

    public final synchronized boolean remove(String key) {
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if (entry == null) {
            return false;
        }
        boolean zRemoveEntry = removeEntry(entry);
        if (zRemoveEntry && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return zRemoveEntry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeEntry(Entry entry) throws IOException {
        BufferedSink bufferedSink;
        if (entry.getLockingSnapshotCount() > 0 && (bufferedSink = this.journalWriter) != null) {
            bufferedSink.writeUtf8(DIRTY);
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(entry.getKey());
            bufferedSink.writeByte(10);
            bufferedSink.flush();
        }
        if (entry.getLockingSnapshotCount() > 0 || entry.getCurrentEditor() != null) {
            entry.setZombie(true);
            return true;
        }
        Editor currentEditor = entry.getCurrentEditor();
        if (currentEditor != null) {
            currentEditor.detach();
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            delete(entry.getCleanFiles().get(i2));
            this.size -= entry.getLengths()[i2];
            entry.getLengths()[i2] = 0;
        }
        this.operationsSinceRewrite++;
        BufferedSink bufferedSink2 = this.journalWriter;
        if (bufferedSink2 != null) {
            bufferedSink2.writeUtf8(REMOVE);
            bufferedSink2.writeByte(32);
            bufferedSink2.writeUtf8(entry.getKey());
            bufferedSink2.writeByte(10);
        }
        this.lruEntries.remove(entry.getKey());
        if (journalRewriteRequired()) {
            launchCleanup();
        }
        return true;
    }

    private final void checkNotClosed() {
        if (!(!this.closed)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        Editor currentEditor;
        if (this.initialized && !this.closed) {
            int i = 0;
            Object[] array = this.lruEntries.values().toArray(new Entry[0]);
            if (array != null) {
                Entry[] entryArr = (Entry[]) array;
                int length = entryArr.length;
                while (i < length) {
                    Entry entry = entryArr[i];
                    i++;
                    if (entry.getCurrentEditor() != null && (currentEditor = entry.getCurrentEditor()) != null) {
                        currentEditor.detach();
                    }
                }
                trimToSize();
                CoroutineScopeKt.cancel$default(this.cleanupScope, null, 1, null);
                BufferedSink bufferedSink = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink);
                bufferedSink.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        this.closed = true;
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trimToSize() {
        while (this.size > this.maxSize) {
            if (!removeOldestEntry()) {
                return;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final boolean removeOldestEntry() throws IOException {
        for (Entry entry : this.lruEntries.values()) {
            if (!entry.getZombie()) {
                removeEntry(entry);
                return true;
            }
        }
        return false;
    }

    private final void delete() throws IOException {
        close();
        FileSystems.deleteContents(this.fileSystem, this.directory);
    }

    public final synchronized void evictAll() {
        initialize();
        Object[] array = this.lruEntries.values().toArray(new Entry[0]);
        if (array != null) {
            Entry[] entryArr = (Entry[]) array;
            int length = entryArr.length;
            int i = 0;
            while (i < length) {
                Entry entry = entryArr[i];
                i++;
                removeEntry(entry);
            }
            this.mostRecentTrimFailed = false;
        } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "coil.disk.DiskLruCache$launchCleanup$1", f = "DiskLruCache.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: coil.disk.DiskLruCache$launchCleanup$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DiskLruCache.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                DiskLruCache diskLruCache = DiskLruCache.this;
                synchronized (diskLruCache) {
                    if (diskLruCache.initialized && !diskLruCache.closed) {
                        try {
                            diskLruCache.trimToSize();
                        } catch (IOException unused) {
                            diskLruCache.mostRecentTrimFailed = true;
                        }
                        try {
                            if (diskLruCache.journalRewriteRequired()) {
                                diskLruCache.writeJournal();
                            }
                        } catch (IOException unused2) {
                            diskLruCache.mostRecentRebuildFailed = true;
                            diskLruCache.journalWriter = Okio.buffer(Okio.blackhole());
                        }
                        Unit unit = Unit.INSTANCE;
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void launchCleanup() {
        BuildersKt__Builders_commonKt.launch$default(this.cleanupScope, null, null, new AnonymousClass1(null), 3, null);
    }

    private final void validateKey(String key) {
        if (LEGAL_KEY_PATTERN.matches(key)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + key + Typography.quote).toString());
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\n\u0010\u0003\u001a\u00060\u0004R\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\f\u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u0005J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0003\u001a\u00060\u0004R\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcoil/disk/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "Lokio/Closeable;", "entry", "Lcoil/disk/DiskLruCache$Entry;", "Lcoil/disk/DiskLruCache;", "(Lcoil/disk/DiskLruCache;Lcoil/disk/DiskLruCache$Entry;)V", "closed", "", "getEntry", "()Lcoil/disk/DiskLruCache$Entry;", "close", "", "closeAndEdit", "Lcoil/disk/DiskLruCache$Editor;", "file", "Lokio/Path;", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Snapshot implements Closeable {
        private boolean closed;
        private final Entry entry;

        public Snapshot(Entry entry) {
            this.entry = entry;
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final Path file(int index) {
            if (!(!this.closed)) {
                throw new IllegalStateException("snapshot is closed".toString());
            }
            return this.entry.getCleanFiles().get(index);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                getEntry().setLockingSnapshotCount(r1.getLockingSnapshotCount() - 1);
                if (getEntry().getLockingSnapshotCount() == 0 && getEntry().getZombie()) {
                    diskLruCache.removeEntry(getEntry());
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        public final Editor closeAndEdit() {
            Editor editorEdit;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                close();
                editorEdit = diskLruCache.edit(getEntry().getKey());
            }
            return editorEdit;
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\f\u0010\u0011\u001a\b\u0018\u00010\u0012R\u00020\u0004J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0007H\u0002J\u0006\u0010\u0015\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcoil/disk/DiskLruCache$Editor;", "", "entry", "Lcoil/disk/DiskLruCache$Entry;", "Lcoil/disk/DiskLruCache;", "(Lcoil/disk/DiskLruCache;Lcoil/disk/DiskLruCache$Entry;)V", "closed", "", "getEntry", "()Lcoil/disk/DiskLruCache$Entry;", "written", "", "getWritten", "()[Z", "abort", "", "commit", "commitAndGet", "Lcoil/disk/DiskLruCache$Snapshot;", "complete", ANConstants.SUCCESS, "detach", "file", "Lokio/Path;", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Editor {
        private boolean closed;
        private final Entry entry;
        private final boolean[] written;

        public Editor(Entry entry) {
            this.entry = entry;
            this.written = new boolean[DiskLruCache.this.valueCount];
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final boolean[] getWritten() {
            return this.written;
        }

        public final Path file(int index) {
            Path path;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!(!this.closed)) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                getWritten()[index] = true;
                Path path2 = getEntry().getDirtyFiles().get(index);
                FileSystems.createFile(diskLruCache.fileSystem, path2);
                path = path2;
            }
            return path;
        }

        public final void detach() {
            if (Intrinsics.areEqual(this.entry.getCurrentEditor(), this)) {
                this.entry.setZombie(true);
            }
        }

        public final void commit() {
            complete(true);
        }

        public final Snapshot commitAndGet() {
            Snapshot snapshot;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                commit();
                snapshot = diskLruCache.get(getEntry().getKey());
            }
            return snapshot;
        }

        public final void abort() {
            complete(false);
        }

        private final void complete(boolean success) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!(!this.closed)) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                if (Intrinsics.areEqual(getEntry().getCurrentEditor(), this)) {
                    diskLruCache.completeEdit(this, success);
                }
                this.closed = true;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030,J\f\u0010-\u001a\b\u0018\u00010.R\u00020\rJ\u000e\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201R!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0018\u00010\fR\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%¨\u00062"}, d2 = {"Lcoil/disk/DiskLruCache$Entry;", "", "key", "", "(Lcoil/disk/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "Ljava/util/ArrayList;", "Lokio/Path;", "Lkotlin/collections/ArrayList;", "getCleanFiles", "()Ljava/util/ArrayList;", "currentEditor", "Lcoil/disk/DiskLruCache$Editor;", "Lcoil/disk/DiskLruCache;", "getCurrentEditor", "()Lcoil/disk/DiskLruCache$Editor;", "setCurrentEditor", "(Lcoil/disk/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles", "getKey", "()Ljava/lang/String;", "lengths", "", "getLengths", "()[J", "lockingSnapshotCount", "", "getLockingSnapshotCount", "()I", "setLockingSnapshotCount", "(I)V", "readable", "", "getReadable", "()Z", "setReadable", "(Z)V", "zombie", "getZombie", "setZombie", "setLengths", "", "strings", "", "snapshot", "Lcoil/disk/DiskLruCache$Snapshot;", "writeLengths", "writer", "Lokio/BufferedSink;", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Entry {
        private final ArrayList<Path> cleanFiles;
        private Editor currentEditor;
        private final ArrayList<Path> dirtyFiles;
        private final String key;
        private final long[] lengths;
        private int lockingSnapshotCount;
        private boolean readable;
        private boolean zombie;

        public Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            this.dirtyFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            int i = DiskLruCache.this.valueCount;
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(i2);
                this.cleanFiles.add(DiskLruCache.this.directory.resolve(sb.toString()));
                sb.append(".tmp");
                this.dirtyFiles.add(DiskLruCache.this.directory.resolve(sb.toString()));
                sb.setLength(length);
            }
        }

        public final String getKey() {
            return this.key;
        }

        public final long[] getLengths() {
            return this.lengths;
        }

        public final ArrayList<Path> getCleanFiles() {
            return this.cleanFiles;
        }

        public final ArrayList<Path> getDirtyFiles() {
            return this.dirtyFiles;
        }

        public final boolean getReadable() {
            return this.readable;
        }

        public final void setReadable(boolean z) {
            this.readable = z;
        }

        public final boolean getZombie() {
            return this.zombie;
        }

        public final void setZombie(boolean z) {
            this.zombie = z;
        }

        public final Editor getCurrentEditor() {
            return this.currentEditor;
        }

        public final void setCurrentEditor(Editor editor) {
            this.currentEditor = editor;
        }

        public final int getLockingSnapshotCount() {
            return this.lockingSnapshotCount;
        }

        public final void setLockingSnapshotCount(int i) {
            this.lockingSnapshotCount = i;
        }

        public final void setLengths(List<String> strings) throws IOException {
            if (strings.size() != DiskLruCache.this.valueCount) {
                throw new IOException(Intrinsics.stringPlus("unexpected journal line: ", strings));
            }
            int i = 0;
            try {
                int size = strings.size();
                while (i < size) {
                    int i2 = i + 1;
                    this.lengths[i] = Long.parseLong(strings.get(i));
                    i = i2;
                }
            } catch (NumberFormatException unused) {
                throw new IOException(Intrinsics.stringPlus("unexpected journal line: ", strings));
            }
        }

        public final void writeLengths(BufferedSink writer) throws IOException {
            long[] jArr = this.lengths;
            int length = jArr.length;
            int i = 0;
            while (i < length) {
                long j = jArr[i];
                i++;
                writer.writeByte(32).writeDecimalLong(j);
            }
        }

        public final Snapshot snapshot() {
            if (!this.readable || this.currentEditor != null || this.zombie) {
                return null;
            }
            ArrayList<Path> arrayList = this.cleanFiles;
            DiskLruCache diskLruCache = DiskLruCache.this;
            int i = 0;
            int size = arrayList.size();
            while (i < size) {
                int i2 = i + 1;
                if (!diskLruCache.fileSystem.exists(arrayList.get(i))) {
                    try {
                        diskLruCache.removeEntry(this);
                    } catch (IOException unused) {
                    }
                    return null;
                }
                i = i2;
            }
            this.lockingSnapshotCount++;
            return DiskLruCache.this.new Snapshot(this);
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u0016\u0010\n\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0002¨\u0006\u0014"}, d2 = {"Lcoil/disk/DiskLruCache$Companion;", "", "()V", DiskLruCache.CLEAN, "", DiskLruCache.DIRTY, "JOURNAL_FILE", "getJOURNAL_FILE$coil_base_release$annotations", "JOURNAL_FILE_BACKUP", "getJOURNAL_FILE_BACKUP$coil_base_release$annotations", "JOURNAL_FILE_TMP", "getJOURNAL_FILE_TMP$coil_base_release$annotations", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "getMAGIC$coil_base_release$annotations", DiskLruCache.READ, DiskLruCache.REMOVE, "VERSION", "getVERSION$coil_base_release$annotations", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getJOURNAL_FILE$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_BACKUP$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_TMP$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getMAGIC$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getVERSION$coil_base_release$annotations() {
        }

        private Companion() {
        }
    }
}
