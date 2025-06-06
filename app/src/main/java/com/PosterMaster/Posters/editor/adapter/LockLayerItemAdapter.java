package com.PosterMaster.Posters.editor.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.util.Pair;

import com.PosterMaster.Posters.R;
import com.PosterMaster.Posters.editor.View.AutoResizeTextView;
import com.PosterMaster.Posters.editor.View.StickerView;
import com.PosterMaster.Posters.editor.View.text.AutofitTextRel;
import com.PosterMaster.Posters.editor.utility.ImageUtils;
import com.woxthebox.draglistview.DragItemAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LockLayerItemAdapter extends DragItemAdapter<Pair<Long, View>, LockLayerItemAdapter.ViewHolder> {
    private final int mLayoutId;
    Activity activity;
    private boolean mDragOnLongPress;
    private int mGrabHandleId;

    public LockLayerItemAdapter(Activity activity2, ArrayList<Pair<Long, View>> arrayList, int i, int i2, boolean z) {
        this.mLayoutId = i;
        this.mGrabHandleId = i2;
        this.activity = activity2;
        this.mDragOnLongPress = z;
        setItemList(arrayList);

//        SharedUtils.init(activity);
    }

    @NotNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.mLayoutId, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        final View view = this.mItemList.get(i).second;


        try {
            if (view instanceof StickerView) {


                View childAt = ((StickerView) view).getChildAt(1);
                Bitmap createBitmap = Bitmap.createBitmap(childAt.getWidth(), childAt.getHeight(), Bitmap.Config.ARGB_8888);
                childAt.draw(new Canvas(createBitmap));
                float[] fArr = new float[9];
                ((ImageView) childAt).getImageMatrix().getValues(fArr);
                float f = fArr[0];
                float f2 = fArr[4];
                Drawable drawable = ((ImageView) childAt).getDrawable();
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                int round = Math.round(((float) intrinsicWidth) * f);
                int round2 = Math.round(((float) intrinsicHeight) * f2);
                viewHolder.mImage.setImageBitmap(Bitmap.createBitmap(createBitmap, (createBitmap.getWidth() - round) / 2, (createBitmap.getHeight() - round2) / 2, round, round2));
                viewHolder.mImage.setRotationY(childAt.getRotationY());
                viewHolder.mImage.setTag(this.mItemList.get(i));
                viewHolder.mImage.setAlpha(1.0f);
                viewHolder.textView.setText(" ");
            }
            if (view instanceof AutofitTextRel) {

                viewHolder.textView.setText(((AutoResizeTextView) ((AutofitTextRel) view).getChildAt(2)).getText());
                viewHolder.textView.setTypeface(((AutoResizeTextView) ((AutofitTextRel) view).getChildAt(2)).getTypeface());
                viewHolder.textView.setTextColor(((AutoResizeTextView) ((AutofitTextRel) view).getChildAt(2)).getTextColors());
                viewHolder.textView.setGravity(17);
                viewHolder.textView.setMinTextSize(12.0f);

                if (((AutofitTextRel) view).getTextInfo().getBG_COLOR() != 0) {

                    Bitmap createBitmap2 = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888);
                    viewHolder.mImage.setImageBitmap(createBitmap2);
                    viewHolder.mImage.setAlpha(((float) ((AutofitTextRel) view).getTextInfo().getBG_ALPHA()) / 255.0f);

                } else if (((AutofitTextRel) view).getTextInfo().getBG_DRAWABLE().equals("0")) {
                    viewHolder.mImage.setAlpha(1.0f);
                } else {
                    viewHolder.mImage.setImageBitmap(ImageUtils.getTiledBitmap(this.activity, this.activity.getResources().getIdentifier(((AutofitTextRel) view).getTextInfo().getBG_DRAWABLE(), "drawable", this.activity.getPackageName()), 150, 150));
                    viewHolder.mImage.setAlpha(((float) ((AutofitTextRel) view).getTextInfo().getBG_ALPHA()) / 255.0f);
                }


            }
        } catch (Exception e) {
            Log.d("LockLay_____",e.getMessage());
        }


        if (view instanceof StickerView) {
            if (((StickerView) view).isMultiTouchEnabled) {
                viewHolder.imgLock.setImageResource(R.drawable.ic_unlock);
            } else {
                viewHolder.imgLock.setImageResource(R.drawable.ic_lock);
            }

            if (((StickerView) view).getVisibility() == View.VISIBLE) {
                viewHolder.img_visible.setImageResource(R.drawable.ic_eye);
            } else {
                viewHolder.img_visible.setImageResource(R.drawable.eye_close);
            }
        }
        if (view instanceof AutofitTextRel) {
            if (((AutofitTextRel) view).isMultiTouchEnabled) {
                viewHolder.imgLock.setImageResource(R.drawable.ic_unlock);
            } else {
                viewHolder.imgLock.setImageResource(R.drawable.ic_lock);
            }
            if (((AutofitTextRel) view).getVisibility() == View.VISIBLE) {
                viewHolder.img_visible.setImageResource(R.drawable.ic_eye);
            } else {
                viewHolder.img_visible.setImageResource(R.drawable.eye_close);
            }
        }

        viewHolder.img_visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                if (view instanceof StickerView) {
                    if (((StickerView) view).getVisibility() == View.VISIBLE) {
                        ((StickerView) view).setVisibility(View.GONE);
                        viewHolder.img_visible.setImageResource(R.drawable.eye_close);
                    } else {
                        viewHolder.img_visible.setImageResource(R.drawable.ic_eye);
                        ((StickerView) view).setVisibility(View.VISIBLE);
                    }
                }
                if (view instanceof AutofitTextRel) {
                    if (((AutofitTextRel) view).getVisibility() == View.VISIBLE) {
                        ((AutofitTextRel) view).setVisibility(View.GONE);
                        viewHolder.img_visible.setImageResource(R.drawable.eye_close);
                    } else {
                        ((AutofitTextRel) view).setVisibility(View.VISIBLE);
                        viewHolder.img_visible.setImageResource(R.drawable.ic_eye);
                    }
                }
            }
        });

        viewHolder.imgLock.setOnClickListener(view1 -> {

            if (view instanceof StickerView) {

                if (((StickerView) view).isMultiTouchEnabled) {
                    ((StickerView) view).isMultiTouchEnabled = ((StickerView) view).setDefaultTouchListener(false);
                    viewHolder.imgLock.setImageResource(R.drawable.ic_lock);
                } else {

                    ((StickerView) view).isMultiTouchEnabled = ((StickerView) view).setDefaultTouchListener(true);
                    viewHolder.imgLock.setImageResource(R.drawable.ic_unlock);
                }
            }
            if (view instanceof AutofitTextRel) {

                if (((AutofitTextRel) view).isMultiTouchEnabled) {
                    ((AutofitTextRel) view).isMultiTouchEnabled = ((AutofitTextRel) view).setDefaultTouchListener(false);
                    viewHolder.imgLock.setImageResource(R.drawable.ic_lock);
                    return;
                }
                ((AutofitTextRel) view).isMultiTouchEnabled = ((AutofitTextRel) view).setDefaultTouchListener(true);
                viewHolder.imgLock.setImageResource(R.drawable.ic_unlock);
            }
        });
    }

    public long getUniqueItemId(int i) {
        return this.mItemList.get(i).first;
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {
        ImageView imgLock;
        ImageView img_visible;
        ImageView mImage;
//        TextView mText;
        AutoResizeTextView textView;

        ViewHolder(View view) {
            super(view, LockLayerItemAdapter.this.mGrabHandleId, LockLayerItemAdapter.this.mDragOnLongPress);
//            this.mText = view.findViewById(R.id.text);
            this.mImage = view.findViewById(R.id.image1);
            this.imgLock = view.findViewById(R.id.img_lock);
            this.img_visible = view.findViewById(R.id.img_visible);
            this.textView = view.findViewById(R.id.auto_fit_edit_text);
        }

        @Override
        public void onItemClicked(View view) {

        }

        @Override
        public boolean onItemLongClicked(View view) {
            return true;
        }
    }
}
