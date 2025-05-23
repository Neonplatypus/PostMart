package com.PosterMaster.Posters.editor.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;

public class AutoFitEditText extends androidx.appcompat.widget.AppCompatEditText {

    private final RectF _availableSpaceRect;
    private final SparseIntArray _textCachedSizes;
    public float _spacingAdd;
    public float _spacingMult;
    public int _widthLimit;
    public TextPaint paint;
    private boolean _enableSizeCache;
    private boolean _initiallized;
    private int _maxLines;
    private float _maxTextSize;
    private Float _minTextSize;
    private SizeTester _sizeTester;

    public AutoFitEditText(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public AutoFitEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoFitEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this._availableSpaceRect = new RectF();
        this._textCachedSizes = new SparseIntArray();
        this._spacingMult = 1.0f;
        this._spacingAdd = 0.0f;
        this._enableSizeCache = true;
        this._initiallized = false;
        try {
            this._minTextSize = Float.valueOf(TypedValue.applyDimension(2, 12.0f, getResources().getDisplayMetrics()));
            this._maxTextSize = getTextSize();
            if (this._maxLines == 0) {
                this._maxLines = -1;
            }
            this._sizeTester = new TextSize();
            this._initiallized = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTypeface(Typeface typeface) {
        if (this.paint == null) {
            this.paint = new TextPaint(getPaint());
        }
        this.paint.setTypeface(typeface);
        super.setTypeface(typeface);
    }

    @Override
    public void setTextSize(float f) {
        this._maxTextSize = f;
        this._textCachedSizes.clear();
        adjustTextSize();
    }

    @Override
    public int getMaxLines() {
        return this._maxLines;
    }

    @Override
    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this._maxLines = i;
        reAdjust();
    }

    @Override
    public void setSingleLine() {
        super.setSingleLine();
        this._maxLines = 1;
        reAdjust();
    }

    @Override
    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
        if (z) {
            this._maxLines = 1;
        } else {
            this._maxLines = -1;
        }
        reAdjust();
    }

    @Override
    public void setLines(int i) {
        super.setLines(i);
        this._maxLines = i;
        reAdjust();
    }

    @Override
    public void setTextSize(int i, float f) {
        Resources resources;
        Context context = getContext();
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        this._maxTextSize = TypedValue.applyDimension(i, f, resources.getDisplayMetrics());
        this._textCachedSizes.clear();
        adjustTextSize();
    }

    @Override
    public void setLineSpacing(float f, float f2) {
        super.setLineSpacing(f, f2);
        this._spacingMult = f2;
        this._spacingAdd = f;
    }

    public void setMinTextSize(Float f) {
        this._minTextSize = f;
        reAdjust();
    }

    public Float get_minTextSize() {
        return this._minTextSize;
    }

    private void reAdjust() {
        adjustTextSize();
    }

    private void adjustTextSize() {
        try {
            if (this._initiallized) {
                int round = Math.round(this._minTextSize.floatValue());
                int measuredHeight = (getMeasuredHeight() - getCompoundPaddingBottom()) - getCompoundPaddingTop();
                this._widthLimit = (getMeasuredWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
                if (this._widthLimit > 0) {
                    this._availableSpaceRect.right = (float) this._widthLimit;
                    this._availableSpaceRect.bottom = (float) measuredHeight;
                    super.setTextSize(0, (float) efficientTextSizeSearch(round, (int) this._maxTextSize, this._sizeTester, this._availableSpaceRect));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEnableSizeCache(boolean z) {
        this._enableSizeCache = z;
        this._textCachedSizes.clear();
        adjustTextSize();
    }

    private int efficientTextSizeSearch(int i, int i2, SizeTester sizeTester, RectF rectF) {
        int i3;
        if (!this._enableSizeCache) {
            return binarySearch(i, i2, sizeTester, rectF);
        }
        String obj = getText().toString();
        if (obj == null) {
            i3 = 0;
        } else {
            i3 = obj.length();
        }
        int i4 = this._textCachedSizes.get(i3);
        if (i4 != 0) {
            return i4;
        }
        int binarySearch = binarySearch(i, i2, sizeTester, rectF);
        this._textCachedSizes.put(i3, binarySearch);
        return binarySearch;
    }

    private int binarySearch(int i, int i2, SizeTester sizeTester, RectF rectF) {
        int i3 = i2 - 1;
        int i4 = i;
        while (i <= i3) {
            int i5 = (i + i3) >>> 1;
            try {
                int onTestSize = sizeTester.onTestSize(i5, rectF);
                if (onTestSize < 0) {
                    i4 = i;
                    i = i5 + 1;
                } else if (onTestSize <= 0) {
                    return i5;
                } else {
                    i3 = i5 - 1;
                    i4 = i3;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i4;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        reAdjust();
    }

    @Override
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this._textCachedSizes.clear();
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            reAdjust();
        }
    }

    private interface SizeTester {
        int onTestSize(int i, RectF rectF);
    }

    class TextSize implements SizeTester {
        final RectF textRect = new RectF();

        TextSize() {
        }

        @TargetApi(16)
        public int onTestSize(int i, RectF rectF) {
            AutoFitEditText.this.paint.setTextSize((float) i);
            String obj = AutoFitEditText.this.getText().toString();
            if (AutoFitEditText.this.getMaxLines() == 1) {
                this.textRect.bottom = AutoFitEditText.this.paint.getFontSpacing();
                this.textRect.right = AutoFitEditText.this.paint.measureText(obj);
            } else {
                StaticLayout staticLayout = new StaticLayout(obj, AutoFitEditText.this.paint, AutoFitEditText.this._widthLimit, Layout.Alignment.ALIGN_NORMAL, AutoFitEditText.this._spacingMult, AutoFitEditText.this._spacingAdd, true);
                if (AutoFitEditText.this.getMaxLines() != -1 && staticLayout.getLineCount() > AutoFitEditText.this.getMaxLines()) {
                    return 1;
                }
                this.textRect.bottom = (float) staticLayout.getHeight();
                int i2 = -1;
                for (int i3 = 0; i3 < staticLayout.getLineCount(); i3++) {
                    if (((float) i2) < staticLayout.getLineWidth(i3)) {
                        i2 = (int) staticLayout.getLineWidth(i3);
                    }
                }
                this.textRect.right = (float) i2;
            }
            this.textRect.offsetTo(0.0f, 0.0f);
            return rectF.contains(this.textRect) ? -1 : 1;
        }
    }
}
