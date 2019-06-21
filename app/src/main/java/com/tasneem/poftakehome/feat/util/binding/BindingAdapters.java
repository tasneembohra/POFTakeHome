package com.tasneem.poftakehome.feat.util.binding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class BindingAdapters {

    @BindingAdapter({"bind:firstText", "bind:secondText", "bind:position"})
    public static void setTextWithAnimation(final TextView textView, final String firstText, final String secondText, final int position) {
        if ((position+1) % 2 == 0) return;
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (textView.getText().toString().equalsIgnoreCase(firstText)) {
                    textView.setText(secondText);
                } else {
                    textView.setText(firstText);
                }
            }
        });
        textView.startAnimation(anim);
    }

    @BindingAdapter("bind:src")
    public static void setImageViewSrc(ImageView imageView, String url) {
        if (url == null || TextUtils.isEmpty(url)) return;
                Picasso.with(imageView.getContext())
                        .load(url)
                        .transform(new RoundedCornersTransformation(15, 5))
                        .into(imageView);
    }
}
