// Generated code from Butter Knife. Do not modify!
package com.example.rartamonov.translater.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.rartamonov.translater.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TabHistoryFavourite_ViewBinding implements Unbinder {
  private TabHistoryFavourite target;

  private View view2131558539;

  private View view2131558540;

  private TextWatcher view2131558540TextWatcher;

  @UiThread
  public TabHistoryFavourite_ViewBinding(final TabHistoryFavourite target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.delete, "field 'deleteHistory' and method 'onClickDeleteHistory'");
    target.deleteHistory = Utils.castView(view, R.id.delete, "field 'deleteHistory'", ImageButton.class);
    view2131558539 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickDeleteHistory();
      }
    });
    view = Utils.findRequiredView(source, R.id.findHistory, "field 'findHistory' and method 'onTextChangedFindHistory'");
    target.findHistory = Utils.castView(view, R.id.findHistory, "field 'findHistory'", EditText.class);
    view2131558540 = view;
    view2131558540TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onTextChangedFindHistory();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131558540TextWatcher);
    target.tabLayoutFav = Utils.findRequiredViewAsType(source, R.id.tabLayout_fav, "field 'tabLayoutFav'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TabHistoryFavourite target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.deleteHistory = null;
    target.findHistory = null;
    target.tabLayoutFav = null;

    view2131558539.setOnClickListener(null);
    view2131558539 = null;
    ((TextView) view2131558540).removeTextChangedListener(view2131558540TextWatcher);
    view2131558540TextWatcher = null;
    view2131558540 = null;
  }
}
