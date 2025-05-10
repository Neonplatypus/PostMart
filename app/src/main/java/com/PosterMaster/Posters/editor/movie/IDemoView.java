package com.PosterMaster.Posters.editor.movie;

import android.content.Context;

import com.hw.photomovie.render.GLTextureView;

/**
 * Created by huangwei on 2018/9/9.
 */
public interface IDemoView {
    public GLTextureView getGLView();
    Context getActivity();
}
