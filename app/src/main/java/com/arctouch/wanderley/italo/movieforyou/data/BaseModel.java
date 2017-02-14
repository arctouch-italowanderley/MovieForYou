package com.arctouch.wanderley.italo.movieforyou.data;

import java.io.Serializable;
import java.lang.reflect.Method;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italowanderley on 13/02/17.
 */

public abstract class BaseModel implements Serializable {
    private final String TAG = BaseModel.class.getSimpleName();

    @Override
    public String toString() {
        StringBuilder toStr = new StringBuilder().append("{ ");
        for (Method method : getClass().getMethods()) {
            if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                printAttribute(toStr, 3, method);
            } else if (method.getName().startsWith("is")) {
                printAttribute(toStr, 2, method);
            }
        }
        toStr.append("}");

        return toStr.toString();
    }

    private void printAttribute(StringBuilder toStr, int startIndex, Method method) {
        char c[] = method.getName().substring(startIndex).toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        try {
            toStr.append(new String(c) + ": " + method.invoke(this) + "; ");
        } catch (Throwable t) {
            printExceptionMessage(t, TAG);
        }
    }
}

