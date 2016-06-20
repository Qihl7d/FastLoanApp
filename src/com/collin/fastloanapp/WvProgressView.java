package com.collin.fastloanapp;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * 简单的缓冲进度条
 */
@SuppressLint("NewApi")
public class WvProgressView extends View {
	public WvProgressView(Context context) {
		super(context);
	}

	public WvProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WvProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private final int MAX_PROGRESS = 3000;
	private int currentProgress = 0;
	private ValueAnimator anim;
	private Paint paint = new Paint();
	private Rect mRect = new Rect();

	{
		paint.setColor(ResourceTool.getColor(R.color.ask_for_normal));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		float percent = (float) currentProgress / (float) MAX_PROGRESS;
		mRect.set(0, 0, (int) (getWidth() * percent), getHeight());
		canvas.drawRect(mRect, paint);
	}

	/**
	 * 开始滚动
	 */
	public void start() {
		stop(false);
		paint.setAlpha(255);
		anim = ValueAnimator.ofInt(0, (int) (MAX_PROGRESS * 0.87)).setDuration(4000);
		anim.setInterpolator(new DecelerateInterpolator());
		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			public void onAnimationUpdate(ValueAnimator animation) {
				currentProgress = (Integer) animation.getAnimatedValue();
				invalidate();
			}
		});
		anim.start();
	}

	/**
	 * 停止滚动
	 */
	public void stop(boolean doAnim) {
		if (anim != null) {
			anim.cancel();
			anim = null;
		}
		if (!doAnim) {
			currentProgress = 0;
			invalidate();
			return;
		}
		final ValueAnimator stopAnim = ValueAnimator.ofInt(currentProgress, MAX_PROGRESS).setDuration(300);
		stopAnim.setInterpolator(new LinearInterpolator());
		final int startStopValue = currentProgress;
		stopAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			public void onAnimationUpdate(ValueAnimator animation) {
				if (anim != null) {
					stopAnim.cancel();
					return;
				}
				currentProgress = (Integer) animation.getAnimatedValue();
				invalidate();
				paint.setAlpha(255 - ((int) ((float) (currentProgress - startStopValue)
						/ (float) (MAX_PROGRESS - startStopValue) * 255)));
			}
		});
		stopAnim.addListener(new Animator.AnimatorListener() {
			public void onAnimationStart(Animator animation) {
				
			}

			public void onAnimationEnd(Animator animation) {
				if (anim != null)
					return;
				currentProgress = 0;
				invalidate();
			}

			public void onAnimationCancel(Animator animation) {
				
			}

			public void onAnimationRepeat(Animator animation) {

			}
		});
		stopAnim.start();
	}

}
