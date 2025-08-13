package com.baidu.location.h.b;

import com.baidu.location.h.s;
import java.io.File;

/* loaded from: classes.dex */
public class a {

    /* renamed from: com.baidu.location.h.b.a$a, reason: collision with other inner class name */
    public static class C0023a {
        public static final String a = s.g() + File.separator + "locModel" + File.separator + "gps_checker" + File.separator + "GPS_Checker_Model.m";
        public static final String b;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append(s.g());
            sb.append(File.separator);
            sb.append("locModel");
            sb.append(File.separator);
            sb.append("gps_checker");
            sb.append(File.separator);
            b = sb.toString();
        }
    }

    public static class b {
        public static final String a;
        private static final String b;

        static {
            String str = "indoor" + File.separator + "poiData";
            b = str;
            a = s.g() + File.separator + "locModel" + File.separator + str + File.separator;
        }
    }

    public static class c {
        public static final String a = s.g() + File.separator + "locModel" + File.separator + "vdrModel" + File.separator + "mobilenet_opt.nb";
        public static final String b;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append(s.g());
            sb.append(File.separator);
            sb.append("locModel");
            sb.append(File.separator);
            sb.append("vdrModel");
            sb.append(File.separator);
            b = sb.toString();
        }
    }

    public static class d {
        public static final String a;
        private static final String b;

        static {
            String str = "outdoor" + File.separator + "poiData";
            b = str;
            a = s.g() + File.separator + "locModel" + File.separator + str + File.separator;
        }
    }

    public static class e {
        public static final String a = s.g() + File.separator + "locModel" + File.separator + "subway" + File.separator + "State_Recognition_Model_Static.m";
        public static final String b;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append(s.g());
            sb.append(File.separator);
            sb.append("locModel");
            sb.append(File.separator);
            sb.append("subway");
            sb.append(File.separator);
            b = sb.toString();
        }
    }

    public static class f {
        public static final String a = s.g() + File.separator + "locModel" + File.separator + "traffic" + File.separator + "Traffic_Recognition_Model.m";
        public static final String b;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append(s.g());
            sb.append(File.separator);
            sb.append("locModel");
            sb.append(File.separator);
            sb.append("traffic");
            sb.append(File.separator);
            b = sb.toString();
        }
    }
}
