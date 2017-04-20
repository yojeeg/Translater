// Generated code from Butter Knife. Do not modify!
package com.example.rartamonov.translater.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.rartamonov.translater.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TabTools_ViewBinding implements Unbinder {
  private TabTools target;

  private View view2131558551;

  @UiThread
  public TabTools_ViewBinding(final TabTools target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.switch_show_dict, "field 'switchShowDict' and method 'onClickSwitchShowDict'");
    target.switchShowDict = Utils.castView(view, R.id.switch_show_dict, "field 'switchShowDict'", SwitchCompat.class);
    view2131558551 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSwitchShowDict();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    TabTools target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.switchShowDict = null;

    view2131558551.setOnClickListener(null);
    view2131558551 = null;
  }
}
