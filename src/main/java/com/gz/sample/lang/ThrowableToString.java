package com.gz.sample.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception to String
 *
 * @author Liguiqing
 * @since V1.0
 */
public class ThrowableToString {
    private final static  Logger log = LoggerFactory.getLogger(ThrowableToString.class);

    private ThrowableToString() {
        throw new AssertionError("No com.gz.jiebao.lang.ThrowableToString instances for you!");
    }

    public static String toString(Throwable t) {
        if (t == null) {
            return "Throwable is null";
        }
        var swriter = new StringWriter();
        var pwriter = new PrintWriter(swriter);
        t.printStackTrace(pwriter);
        var s = swriter.toString();
        Closer.close(pwriter);
        return s;
    }

    public static String toHtml(Throwable t) {
        var msg = toString(t);
        return "<div class='exception'><pre>".concat(msg.replaceAll("\n\t", "<br/>")).concat("</pre></div>");
    }

    public static String cleanExceptionString(Throwable t) {
        var msg = t.getMessage();
        if (msg != null && msg.contains("Exception")) {
            return msg.substring(msg.indexOf("Exception:") + "Exception:".length());
        } else {
            return msg;
        }
    }

    public static String toString(Exception t) {
        if (t == null)
            return "Exception is null";

        return toString(t.getCause() == null ? t : t.getCause());
    }

    public static void logWarn(Exception t) {
        log.warn(toString(t));
    }
}
