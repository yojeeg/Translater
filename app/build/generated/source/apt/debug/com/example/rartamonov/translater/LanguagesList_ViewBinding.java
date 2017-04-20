// Generated code from Butter Knife. Do not modify!
package com.example.rartamonov.translater;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LanguagesList_ViewBinding implements Unbinder {
  private LanguagesList target;

  private View view2131558559;

  @UiThread
  public LanguagesList_ViewBinding(LanguagesList target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LanguagesList_ViewBinding(final LanguagesList target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.close_activity, "field 'closeActivity' and method 'onClickCloseActivity'");
    target.closeActivity = Utils.castView(view, R.id.close_activity, "field 'closeActivity'", ImageButton.class);
    view2131558559 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickCloseActivity(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LanguagesList target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.closeActivity = null;

    view2131558559.setOnClickListener(null);
    view2131558559 = null;
  }
}
