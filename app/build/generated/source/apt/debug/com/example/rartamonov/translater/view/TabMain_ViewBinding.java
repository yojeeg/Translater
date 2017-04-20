// Generated code from Butter Knife. Do not modify!
package com.example.rartamonov.translater.view;

import android.annotation.SuppressLint;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.rartamonov.translater.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TabMain_ViewBinding implements Unbinder {
  private TabMain target;

  private View view2131558542;

  private View view2131558544;

  private View view2131558543;

  private View view2131558548;

  private View view2131558546;

  private View view2131558545;

  private TextWatcher view2131558545TextWatcher;

  @UiThread
  @SuppressLint("ClickableViewAccessibility")
  public TabMain_ViewBinding(final TabMain target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.buttonFrom, "field 'buttonFrom' and method 'onClickButtonFrom'");
    target.buttonFrom = Utils.castView(view, R.id.buttonFrom, "field 'buttonFrom'", Button.class);
    view2131558542 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickButtonFrom();
      }
    });
    view = Utils.findRequiredView(source, R.id.buttonTo, "field 'buttonTo' and method 'onClickButtonTo'");
    target.buttonTo = Utils.castView(view, R.id.buttonTo, "field 'buttonTo'", Button.class);
    view2131558544 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickButtonTo();
      }
    });
    view = Utils.findRequiredView(source, R.id.switchLanguage, "field 'buttonSwitch' and method 'onClickSwitchShowDict'");
    target.buttonSwitch = Utils.castView(view, R.id.switchLanguage, "field 'buttonSwitch'", ImageButton.class);
    view2131558543 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSwitchShowDict();
      }
    });
    view = Utils.findRequiredView(source, R.id.addToFav, "field 'addToFav' and method 'onClickAddToFav'");
    target.addToFav = Utils.castView(view, R.id.addToFav, "field 'addToFav'", ToggleButton.class);
    view2131558548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickAddToFav();
      }
    });
    view = Utils.findRequiredView(source, R.id.textView, "field 'textView' and method 'onTouchTextView'");
    target.textView = Utils.castView(view, R.id.textView, "field 'textView'", TextView.class);
    view2131558546 = view;
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onTouchTextView();
      }
    });
    view = Utils.findRequiredView(source, R.id.editText2, "field 'editText', method 'onFocusChange', and method 'onTextChangedEditText'");
    target.editText = Utils.castView(view, R.id.editText2, "field 'editText'", EditText.class);
    view2131558545 = view;
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View p0, boolean p1) {
        target.onFocusChange(p1);
      }
    });
    view2131558545TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onTextChangedEditText();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131558545TextWatcher);
  }

  @Override
  @CallSuper
  public void unbind() {
    TabMain target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.buttonFrom = null;
    target.buttonTo = null;
    target.buttonSwitch = null;
    target.addToFav = null;
    target.textView = null;
    target.editText = null;

    view2131558542.setOnClickListener(null);
    view2131558542 = null;
    view2131558544.setOnClickListener(null);
    view2131558544 = null;
    view2131558543.setOnClickListener(null);
    view2131558543 = null;
    view2131558548.setOnClickListener(null);
    view2131558548 = null;
    view2131558546.setOnTouchListener(null);
    view2131558546 = null;
    view2131558545.setOnFocusChangeListener(null);
    ((TextView) view2131558545).removeTextChangedListener(view2131558545TextWatcher);
    view2131558545TextWatcher = null;
    view2131558545 = null;
  }
}
