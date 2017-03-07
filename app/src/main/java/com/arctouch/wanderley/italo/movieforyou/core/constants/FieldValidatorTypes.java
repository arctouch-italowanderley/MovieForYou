package com.arctouch.wanderley.italo.movieforyou.core.constants;

import android.content.Context;
import android.content.res.Resources;

import com.arctouch.wanderley.italo.movieforyou.R;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italo on 09/02/17.
 */

public enum FieldValidatorTypes {
    NONE,
    NUMBER("^[0-9]+$", R.string.text_field_error_only_numbers),
    NAME("\\p{L}+( +[\\p{L}\\.']+)+", R.string.text_field_error_name),
    EMAIL("(?i)^[_a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$", R.string.text_field_error_invalid_email),
    REQUIRED("^(?=\\s*\\S).*$", R.string.text_field_error_mandatory);

    private Pattern mRegexPattern;
    private int mMessageId;
    private String[] mExtraContent;

    FieldValidatorTypes() {
        this(null, 0);
    }

    FieldValidatorTypes(final String regex, final int message) {
        mMessageId = message;
        if (regex != null) mRegexPattern = Pattern.compile(regex);
    }

    FieldValidatorTypes(final String regex, final int message, String... extraContent) {
        mExtraContent = extraContent;
        mMessageId = message;
        if (regex != null) mRegexPattern = Pattern.compile(regex);
    }

    public boolean isValid(final String value) {
        if (value == null) return false;
        return mRegexPattern.matcher(value).matches();
    }

    public String getErrorMessage(final Context context) {
        try {
            String msg = context.getResources().getString(mMessageId);
            if (mExtraContent != null && mExtraContent.length > 0) {
                msg = MessageFormat.format(msg, (Object[]) mExtraContent);
            }
            return msg;
        } catch (Resources.NotFoundException e) {
            printExceptionMessage(e, "FieldValidatorTypes");
        }
        return "";
    }
}
