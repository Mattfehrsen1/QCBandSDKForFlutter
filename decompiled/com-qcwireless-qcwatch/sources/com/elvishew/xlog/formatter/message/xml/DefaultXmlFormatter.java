package com.elvishew.xlog.formatter.message.xml;

import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.SystemCompat;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/* loaded from: classes2.dex */
public class DefaultXmlFormatter implements XmlFormatter {
    private static final int XML_INDENT = 4;

    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(String str) throws TransformerException, IllegalArgumentException {
        if (str == null || str.trim().length() == 0) {
            Platform.get().warn("XML empty.");
            return "";
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            transformerNewTransformer.setOutputProperty("indent", "yes");
            transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(4));
            transformerNewTransformer.transform(streamSource, streamResult);
            return streamResult.getWriter().toString().replaceFirst(">", ">" + SystemCompat.lineSeparator);
        } catch (Exception e) {
            Platform.get().warn(e.getMessage());
            return str;
        }
    }
}
