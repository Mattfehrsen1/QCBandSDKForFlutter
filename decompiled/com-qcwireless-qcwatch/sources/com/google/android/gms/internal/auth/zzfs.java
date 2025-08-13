package com.google.android.gms.internal.auth;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfs {
    static String zza(zzfq zzfqVar, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzfqVar, sb, 0);
        return sb.toString();
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzgn.zza(zzeb.zzl((String) obj)));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzeb) {
            sb.append(": \"");
            sb.append(zzgn.zza((zzeb) obj));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzeq) {
            sb.append(" {");
            zzd((zzeq) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj.toString());
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        zzb(sb, i4, "key", entry.getKey());
        zzb(sb, i4, "value", entry.getValue());
        sb.append("\n");
        while (i2 < i) {
            sb.append(' ');
            i2++;
        }
        sb.append("}");
    }

    private static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }

    private static void zzd(zzfq zzfqVar, StringBuilder sb, int i) throws SecurityException {
        boolean zEquals;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzfqVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List")) {
                String strValueOf = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf2 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 4));
                String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                Method method2 = (Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzc(strConcat), zzeq.zzf(method2, zzfqVar, new Object[0]));
                }
            }
            if (strSubstring.endsWith("Map") && !strSubstring.equals("Map")) {
                String strValueOf3 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf4 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 3));
                String strConcat2 = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
                Method method3 = (Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(strConcat2), zzeq.zzf(method3, zzfqVar, new Object[0]));
                }
            }
            String strValueOf5 = String.valueOf(strSubstring);
            if (((Method) map2.get(strValueOf5.length() != 0 ? "set".concat(strValueOf5) : new String("set"))) != null) {
                if (strSubstring.endsWith("Bytes")) {
                    String strValueOf6 = String.valueOf(strSubstring.substring(0, strSubstring.length() - 5));
                    if (!map.containsKey(strValueOf6.length() != 0 ? "get".concat(strValueOf6) : new String("get"))) {
                    }
                }
                String strValueOf7 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf8 = String.valueOf(strSubstring.substring(1));
                String strConcat3 = strValueOf8.length() != 0 ? strValueOf7.concat(strValueOf8) : new String(strValueOf7);
                String strValueOf9 = String.valueOf(strSubstring);
                Method method4 = (Method) map.get(strValueOf9.length() != 0 ? "get".concat(strValueOf9) : new String("get"));
                String strValueOf10 = String.valueOf(strSubstring);
                Method method5 = (Method) map.get(strValueOf10.length() != 0 ? "has".concat(strValueOf10) : new String("has"));
                if (method4 != null) {
                    Object objZzf = zzeq.zzf(method4, zzfqVar, new Object[0]);
                    if (method5 == null) {
                        if (objZzf instanceof Boolean) {
                            if (((Boolean) objZzf).booleanValue()) {
                                zzb(sb, i, zzc(strConcat3), objZzf);
                            }
                        } else if (objZzf instanceof Integer) {
                            if (((Integer) objZzf).intValue() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzf);
                            }
                        } else if (objZzf instanceof Float) {
                            if (((Float) objZzf).floatValue() != 0.0f) {
                                zzb(sb, i, zzc(strConcat3), objZzf);
                            }
                        } else if (!(objZzf instanceof Double)) {
                            if (objZzf instanceof String) {
                                zEquals = objZzf.equals("");
                            } else if (objZzf instanceof zzeb) {
                                zEquals = objZzf.equals(zzeb.zzb);
                            } else if (objZzf instanceof zzfq) {
                                if (objZzf != ((zzfq) objZzf).zzh()) {
                                    zzb(sb, i, zzc(strConcat3), objZzf);
                                }
                            } else if (!(objZzf instanceof Enum) || ((Enum) objZzf).ordinal() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzf);
                            }
                            if (!zEquals) {
                                zzb(sb, i, zzc(strConcat3), objZzf);
                            }
                        } else if (((Double) objZzf).doubleValue() != 0.0d) {
                            zzb(sb, i, zzc(strConcat3), objZzf);
                        }
                    } else if (((Boolean) zzeq.zzf(method5, zzfqVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzc(strConcat3), objZzf);
                    }
                }
            }
        }
        if (zzfqVar instanceof zzep) {
            zzel zzelVar = ((zzep) zzfqVar).zzb;
            throw null;
        }
        zzgq zzgqVar = ((zzeq) zzfqVar).zzc;
        if (zzgqVar != null) {
            zzgqVar.zze(sb, i);
        }
    }
}
