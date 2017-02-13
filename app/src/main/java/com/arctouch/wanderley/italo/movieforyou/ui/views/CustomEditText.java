package com.arctouch.wanderley.italo.movieforyou.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.EditText;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.core.constants.FieldValidatorTypes;
import com.arctouch.wanderley.italo.movieforyou.core.utils.FontCache;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by italo on 09/02/17.
 */

public class CustomEditText extends EditText {
    private List<FieldValidatorTypes> mValidators = new ArrayList<>();

    public CustomEditText(Context context) {
        super(context);
        applyCustomFont(context);
        prepareValidators(context, null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
        prepareValidators(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
        prepareValidators(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
        prepareValidators(context, attrs);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/WorkSans-Regular.ttf", context);
        setTypeface(customFont);
        setTextSize(getContext().getResources().getDimension(R.dimen.text_small_size));
    }

    private void prepareValidators(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        final String typedArrayValidator = typedArray.getString(R.styleable.CustomEditText_validator);
        if (typedArrayValidator != null && !typedArrayValidator.isEmpty()) {
            addValidators(typedArrayValidator.split("\\|"));
        }
    }

    private void addValidators(final String[] validatorReferences) {
        for (String validatorReference : validatorReferences) {
            final FieldValidatorTypes validator = FieldValidatorTypes.valueOf(validatorReference);
            mValidators.add(validator);
        }
    }

    private List<FieldValidatorTypes> getValidators() {
        return mValidators;
    }

    public String getValue() {
        String rawContent = super.getText().toString();
        return rawContent.replaceAll("\\s{2,}", " ").trim();
    }

    public boolean validate() {
        if (getValidators() == null || getValidators().isEmpty()) {
            return true;
        }
        setError(null);

        CharSequence text = getValue();
        boolean isValid = true;
        TextInputLayout til = getTextInputLayout();

        for (FieldValidatorTypes validator : mValidators) {
            isValid = validator.isValid(text.toString());
            if (!isValid) {
                String errorMsg = validator.getErrorMessage(getContext());
                if (til != null) {
                    til.setError(errorMsg);
                }
                break;
            }
        }

        if (isValid && til != null) {
            til.setError(null);
        }

        return isValid;
    }

    private TextInputLayout getTextInputLayout() {
        ViewParent parent = getParent();
        TextInputLayout needed = null;
        do {
            if (parent instanceof TextInputLayout) {
                needed = (TextInputLayout) parent;
                break;
            }
            parent = parent.getParent();
        } while (parent != null);
        return needed;
    }
}
