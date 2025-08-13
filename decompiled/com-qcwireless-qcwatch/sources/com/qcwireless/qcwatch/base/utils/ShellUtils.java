package com.qcwireless.qcwatch.base.utils;

import java.util.List;

/* loaded from: classes3.dex */
public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";
    private static final String TAG = "ShellUtils";

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String command, boolean isRoot) {
        return execCommand(new String[]{command}, isRoot, true);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, true);
    }

    public static CommandResult execCommand(String[] commands, boolean isRoot) {
        return execCommand(commands, isRoot, true);
    }

    public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(new String[]{command}, isRoot, isNeedResultMsg);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, isNeedResultMsg);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:(4:168|7|(1:9)(1:10)|11)|(7:176|12|155|13|(3:15|(2:17|180)(2:18|179)|19)|178|20)|(19:172|22|165|23|164|24|174|25|(3:170|26|(1:28)(1:181))|(2:29|(1:31)(0))|64|(1:66)|(1:68)|(1:73)|125|(1:127)(1:128)|(1:131)|132|133)(1:63)|151|64|(0)|(0)|(0)|125|(0)(0)|(0)|132|133) */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f9, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00fa, code lost:
    
        r2.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0143 A[Catch: IOException -> 0x013f, TryCatch #8 {IOException -> 0x013f, blocks: (B:96:0x013b, B:100:0x0143, B:102:0x0148), top: B:153:0x013b }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0148 A[Catch: IOException -> 0x013f, TRY_LEAVE, TryCatch #8 {IOException -> 0x013f, blocks: (B:96:0x013b, B:100:0x0143, B:102:0x0148), top: B:153:0x013b }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0167 A[Catch: IOException -> 0x0163, TryCatch #20 {IOException -> 0x0163, blocks: (B:113:0x015f, B:117:0x0167, B:119:0x016c), top: B:160:0x015f }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016c A[Catch: IOException -> 0x0163, TRY_LEAVE, TryCatch #20 {IOException -> 0x0163, blocks: (B:113:0x015f, B:117:0x0167, B:119:0x016c), top: B:160:0x015f }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0175 A[PHI: r1 r2 r3 r4 r5 r9 r10
      0x0175: PHI (r1v10 java.io.DataOutputStream) = (r1v7 java.io.DataOutputStream), (r1v11 java.io.DataOutputStream) binds: [B:105:0x014f, B:122:0x0173] A[DONT_GENERATE, DONT_INLINE]
      0x0175: PHI (r2v5 int) = (r2v3 int), (r2v6 int) binds: [B:105:0x014f, B:122:0x0173] A[DONT_GENERATE, DONT_INLINE]
      0x0175: PHI (r3v11 java.io.DataOutputStream) = (r3v9 java.io.DataOutputStream), (r3v12 java.io.DataOutputStream) binds: [B:105:0x014f, B:122:0x0173] A[DONT_GENERATE, DONT_INLINE]
      0x0175: PHI (r4v10 java.io.BufferedReader) = (r4v8 java.io.BufferedReader), (r4v11 java.io.BufferedReader) binds: [B:105:0x014f, B:122:0x0173] A[DONT_GENERATE, DONT_INLINE]
      0x0175: PHI (r5v7 ??) = (r5v5 ??), (r5v8 ??) binds: [B:105:0x014f, B:122:0x0173] A[DONT_GENERATE, DONT_INLINE]
      0x0175: PHI (r9v13 java.lang.StringBuilder) = (r9v11 java.lang.StringBuilder), (r9v14 java.lang.StringBuilder) binds: [B:105:0x014f, B:122:0x0173] A[DONT_GENERATE, DONT_INLINE]
      0x0175: PHI (r10v11 java.lang.Process) = (r10v9 java.lang.Process), (r10v12 java.lang.Process) binds: [B:105:0x014f, B:122:0x0173] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x019f A[Catch: IOException -> 0x019b, TryCatch #25 {IOException -> 0x019b, blocks: (B:137:0x0197, B:141:0x019f, B:143:0x01a4), top: B:162:0x0197 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01a4 A[Catch: IOException -> 0x019b, TRY_LEAVE, TryCatch #25 {IOException -> 0x019b, blocks: (B:137:0x0197, B:141:0x019f, B:143:0x01a4), top: B:162:0x0197 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x013b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x015f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0197 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:182:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f0 A[Catch: IOException -> 0x00f9, TryCatch #0 {IOException -> 0x00f9, blocks: (B:64:0x00eb, B:66:0x00f0, B:68:0x00f5), top: B:151:0x00eb }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f5 A[Catch: IOException -> 0x00f9, TRY_LEAVE, TryCatch #0 {IOException -> 0x00f9, blocks: (B:64:0x00eb, B:66:0x00f0, B:68:0x00f5), top: B:151:0x00eb }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ff  */
    /* JADX WARN: Type inference failed for: r10v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v13, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r10v24, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r10v25 */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v44 */
    /* JADX WARN: Type inference failed for: r4v45 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.qcwireless.qcwatch.base.utils.ShellUtils.CommandResult execCommand(java.lang.String[] r8, boolean r9, boolean r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.base.utils.ShellUtils.execCommand(java.lang.String[], boolean, boolean):com.qcwireless.qcwatch.base.utils.ShellUtils$CommandResult");
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int result) {
            this.result = result;
        }

        public CommandResult(int result, String successMsg, String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}
