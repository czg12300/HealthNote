package com.jake.health.ui.widgt.materialdesign.pullrefresh;

import android.content.Context;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * @author amyu
 */
class AnimationImageView extends ImageView {

  /**
   * AnimationのStartとEnd時にListenerにアレする
   */
  private Animation.AnimationListener mListener;

  /**
   * コンストラクタ
   * {@inheritDoc}
   */
  public AnimationImageView(Context context) {
    super(context);
  }

  /**
   * {@link AnimationImageView#mListener} のセット
   *
   * @param listener {@link android.view.animation.Animation.AnimationListener}
   */
  public void setAnimationListener(Animation.AnimationListener listener) {
    mListener = listener;
  }

  /**
   * ViewのAnimationのStart時にセットされたListenerの {@link android.view.animation.Animation.AnimationListener#onAnimationStart(Animation)}
   * を呼ぶ
   */
  @Override public void onAnimationStart() {
    super.onAnimationStart();
    if (mListener != null) {
      mListener.onAnimationStart(getAnimation());
    }
  }

  /**
   * ViewのAnimationのEnd時にセットされたListenerの {@link android.view.animation.Animation.AnimationListener#onAnimationEnd(Animation)}
   * (Animation)} を呼ぶ
   */
  @Override public void onAnimationEnd() {
    super.onAnimationEnd();
    if (mListener != null) {
      mListener.onAnimationEnd(getAnimation());
    }
  }
}