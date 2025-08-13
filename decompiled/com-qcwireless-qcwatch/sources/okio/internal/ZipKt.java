package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.ZipFileSystem;

/* compiled from: zip.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\"\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002\u001a\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u001b\u001a.\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#0\"H\u0000\u001a\f\u0010$\u001a\u00020\u0015*\u00020%H\u0000\u001a\f\u0010&\u001a\u00020'*\u00020%H\u0002\u001a.\u0010(\u001a\u00020)*\u00020%2\u0006\u0010*\u001a\u00020\u00012\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020)0,H\u0002\u001a\u0014\u0010-\u001a\u00020.*\u00020%2\u0006\u0010/\u001a\u00020.H\u0000\u001a\u0018\u00100\u001a\u0004\u0018\u00010.*\u00020%2\b\u0010/\u001a\u0004\u0018\u00010.H\u0002\u001a\u0014\u00101\u001a\u00020'*\u00020%2\u0006\u00102\u001a\u00020'H\u0002\u001a\f\u00103\u001a\u00020)*\u00020%H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00064"}, d2 = {"BIT_FLAG_ENCRYPTED", "", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "hex", "", "getHex", "(I)Ljava/lang/String;", "buildIndex", "", "Lokio/Path;", "Lokio/internal/ZipEntry;", "entries", "", "dosDateTimeToEpochMillis", "date", "time", "(II)Ljava/lang/Long;", "openZip", "Lokio/ZipFileSystem;", "zipPath", "fileSystem", "Lokio/FileSystem;", "predicate", "Lkotlin/Function1;", "", "readEntry", "Lokio/BufferedSource;", "readEocdRecord", "Lokio/internal/EocdRecord;", "readExtra", "", "extraSize", "block", "Lkotlin/Function2;", "readLocalHeader", "Lokio/FileMetadata;", "basicMetadata", "readOrSkipLocalHeader", "readZip64EocdRecord", "regularRecord", "skipLocalHeader", "okio"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class ZipKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = new Function1<ZipEntry, Boolean>() { // from class: okio.internal.ZipKt.openZip.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ZipEntry it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return openZip(path, fileSystem, function1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x009a, code lost:
    
        r6 = readEocdRecord(r15);
        r12 = r15.readUtf8(r6.getCommentByteCount());
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a7, code lost:
    
        r15.close();
        r9 = r9 - 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b0, code lost:
    
        if (r9 <= r7) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00b2, code lost:
    
        r9 = okio.Okio.buffer(r5.source(r9));
        r10 = (java.lang.Throwable) null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00bf, code lost:
    
        r10 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c9, code lost:
    
        if (r10.readIntLe() != okio.internal.ZipKt.ZIP64_LOCATOR_SIGNATURE) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00cb, code lost:
    
        r13 = r10.readIntLe();
        r14 = r10.readLongLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d8, code lost:
    
        if (r10.readIntLe() != 1) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00da, code lost:
    
        if (r13 != 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00dc, code lost:
    
        r7 = okio.Okio.buffer(r5.source(r14));
        r8 = (java.lang.Throwable) null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00e9, code lost:
    
        r8 = r7;
        r10 = r8.readIntLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00f3, code lost:
    
        if (r10 != okio.internal.ZipKt.ZIP64_EOCD_RECORD_SIGNATURE) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00f5, code lost:
    
        r6 = readZip64EocdRecord(r8, r6);
        r8 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00fb, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r7, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0123, code lost:
    
        throw new java.io.IOException("bad zip: expected " + getHex(okio.internal.ZipKt.ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0124, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0134, code lost:
    
        throw new java.io.IOException("unsupported zip: spanned");
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0135, code lost:
    
        r7 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0137, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r9, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x013b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0144, code lost:
    
        r7 = new java.util.ArrayList();
        r5 = okio.Okio.buffer(r5.source(r6.getCentralDirectoryOffset()));
        r8 = (java.lang.Throwable) null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x015c, code lost:
    
        r8 = r5;
        r9 = r6.getEntryCount();
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0167, code lost:
    
        if (0 >= r9) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0169, code lost:
    
        r16 = r16 + 1;
        r11 = readEntry(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x017b, code lost:
    
        if (r11.getOffset() >= r6.getCentralDirectoryOffset()) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0187, code lost:
    
        if (r22.invoke(r11).booleanValue() == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0189, code lost:
    
        r7.add(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0191, code lost:
    
        if (r16 < r9) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x019b, code lost:
    
        throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x019c, code lost:
    
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x019e, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r5, null);
        r5 = new okio.ZipFileSystem(r20, r21, buildIndex(r7), r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01aa, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r3, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01ad, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01ae, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01b0, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01b1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01b6, code lost:
    
        throw r0;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final okio.ZipFileSystem openZip(okio.Path r20, okio.FileSystem r21, kotlin.jvm.functions.Function1<? super okio.internal.ZipEntry, java.lang.Boolean> r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 509
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ZipKt.openZip(okio.Path, okio.FileSystem, kotlin.jvm.functions.Function1):okio.ZipFileSystem");
    }

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ZipEntry zipEntry : CollectionsKt.sortedWith(list, new Comparator() { // from class: okio.internal.ZipKt$buildIndex$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((ZipEntry) t).getCanonicalPath(), ((ZipEntry) t2).getCanonicalPath());
            }
        })) {
            if (((ZipEntry) linkedHashMap.put(zipEntry.getCanonicalPath(), zipEntry)) == null) {
                while (true) {
                    Path pathParent = zipEntry.getCanonicalPath().parent();
                    if (pathParent != null) {
                        ZipEntry zipEntry2 = (ZipEntry) linkedHashMap.get(pathParent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(pathParent, true, null, 0L, 0L, 0L, 0, null, 0L, TypedValues.PositionType.TYPE_CURVE_FIT, null);
                        linkedHashMap.put(pathParent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public static final ZipEntry readEntry(final BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        int intLe = bufferedSource.readIntLe();
        if (intLe != CENTRAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(4L);
        int shortLe = bufferedSource.readShortLe() & 65535;
        if ((shortLe & 1) != 0) {
            throw new IOException(Intrinsics.stringPlus("unsupported zip: general purpose bit flag=", getHex(shortLe)));
        }
        int shortLe2 = bufferedSource.readShortLe() & 65535;
        Long lDosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & 65535, bufferedSource.readShortLe() & 65535);
        long intLe2 = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref.LongRef longRef2 = new Ref.LongRef();
        longRef2.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        int shortLe3 = bufferedSource.readShortLe() & 65535;
        int shortLe4 = bufferedSource.readShortLe() & 65535;
        int shortLe5 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(8L);
        final Ref.LongRef longRef3 = new Ref.LongRef();
        longRef3.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        String utf8 = bufferedSource.readUtf8(shortLe3);
        if (StringsKt.contains$default((CharSequence) utf8, (char) 0, false, 2, (Object) null)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        long j = longRef2.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? 8 + 0 : 0L;
        if (longRef.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        if (longRef3.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        final long j2 = j;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        readExtra(bufferedSource, shortLe4, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipKt.readEntry.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) throws IOException {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, long j3) throws IOException {
                if (i == 1) {
                    if (booleanRef.element) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    booleanRef.element = true;
                    if (j3 < j2) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    Ref.LongRef longRef4 = longRef2;
                    longRef4.element = longRef4.element == ZipKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : longRef2.element;
                    Ref.LongRef longRef5 = longRef;
                    longRef5.element = longRef5.element == ZipKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : 0L;
                    Ref.LongRef longRef6 = longRef3;
                    longRef6.element = longRef6.element == ZipKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : 0L;
                }
            }
        });
        if (j2 > 0 && !booleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        return new ZipEntry(Path.Companion.get$default(Path.INSTANCE, "/", false, 1, (Object) null).resolve(utf8), StringsKt.endsWith$default(utf8, "/", false, 2, (Object) null), bufferedSource.readUtf8(shortLe5), intLe2, longRef.element, longRef2.element, shortLe2, lDosDateTimeToEpochMillis, longRef3.element);
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) throws IOException {
        int shortLe = bufferedSource.readShortLe() & 65535;
        int shortLe2 = bufferedSource.readShortLe() & 65535;
        long shortLe3 = bufferedSource.readShortLe() & 65535;
        if (shortLe3 != (bufferedSource.readShortLe() & 65535) || shortLe != 0 || shortLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(4L);
        return new EocdRecord(shortLe3, MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE & bufferedSource.readIntLe(), bufferedSource.readShortLe() & 65535);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) throws IOException {
        bufferedSource.skip(12L);
        int intLe = bufferedSource.readIntLe();
        int intLe2 = bufferedSource.readIntLe();
        long longLe = bufferedSource.readLongLe();
        if (longLe != bufferedSource.readLongLe() || intLe != 0 || intLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(8L);
        return new EocdRecord(longLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
    }

    private static final void readExtra(BufferedSource bufferedSource, int i, Function2<? super Integer, ? super Long, Unit> function2) throws IOException {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int shortLe = bufferedSource.readShortLe() & 65535;
            long shortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            long j2 = j - 4;
            if (j2 < shortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(shortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(shortLe), Long.valueOf(shortLe2));
            long size2 = (bufferedSource.getBuffer().size() + shortLe2) - size;
            if (size2 < 0) {
                throw new IOException(Intrinsics.stringPlus("unsupported zip: too many bytes processed for ", Integer.valueOf(shortLe)));
            }
            if (size2 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j = j2 - shortLe2;
        }
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, null);
    }

    public static final FileMetadata readLocalHeader(BufferedSource bufferedSource, FileMetadata basicMetadata) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
        FileMetadata orSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, basicMetadata);
        Intrinsics.checkNotNull(orSkipLocalHeader);
        return orSkipLocalHeader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FileMetadata readOrSkipLocalHeader(final BufferedSource bufferedSource, FileMetadata fileMetadata) throws IOException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = fileMetadata == null ? 0 : fileMetadata.getLastModifiedAtMillis();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        int intLe = bufferedSource.readIntLe();
        if (intLe != LOCAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(2L);
        int shortLe = bufferedSource.readShortLe() & 65535;
        if ((shortLe & 1) != 0) {
            throw new IOException(Intrinsics.stringPlus("unsupported zip: general purpose bit flag=", getHex(shortLe)));
        }
        bufferedSource.skip(18L);
        long shortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        int shortLe3 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(shortLe2);
        if (fileMetadata == null) {
            bufferedSource.skip(shortLe3);
            return null;
        }
        readExtra(bufferedSource, shortLe3, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipKt.readOrSkipLocalHeader.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) throws IOException {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r0v13, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r0v17, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r10v3, types: [T, java.lang.Long] */
            public final void invoke(int i, long j) throws IOException {
                if (i == ZipKt.HEADER_ID_EXTENDED_TIMESTAMP) {
                    if (j < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    int i2 = bufferedSource.readByte() & 255;
                    boolean z = (i2 & 1) == 1;
                    boolean z2 = (i2 & 2) == 2;
                    boolean z3 = (i2 & 4) == 4;
                    BufferedSource bufferedSource2 = bufferedSource;
                    long j2 = z ? 5L : 1L;
                    if (z2) {
                        j2 += 4;
                    }
                    if (z3) {
                        j2 += 4;
                    }
                    if (j < j2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        objectRef.element = Long.valueOf(bufferedSource2.readIntLe() * 1000);
                    }
                    if (z2) {
                        objectRef2.element = Long.valueOf(bufferedSource.readIntLe() * 1000);
                    }
                    if (z3) {
                        objectRef3.element = Long.valueOf(bufferedSource.readIntLe() * 1000);
                    }
                }
            }
        });
        return new FileMetadata(fileMetadata.getIsRegularFile(), fileMetadata.getIsDirectory(), null, fileMetadata.getSize(), (Long) objectRef3.element, (Long) objectRef.element, (Long) objectRef2.element, null, 128, null);
    }

    private static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        gregorianCalendar.set(((i >> 9) & 127) + 1980, ((i >> 5) & 15) - 1, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    private static final String getHex(int i) {
        String string = Integer.toString(i, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return Intrinsics.stringPlus("0x", string);
    }
}
