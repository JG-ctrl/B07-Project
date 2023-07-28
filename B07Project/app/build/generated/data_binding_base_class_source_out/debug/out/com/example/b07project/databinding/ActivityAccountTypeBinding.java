// Generated by view binder compiler. Do not edit!
package com.example.b07project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.b07project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAccountTypeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonCreateOwner;

  @NonNull
  public final Button buttonCreateShopper;

  @NonNull
  public final EditText editTextPasswordSignup;

  @NonNull
  public final EditText editTextUsernameSignup;

  @NonNull
  public final ConstraintLayout textView2;

  @NonNull
  public final TextView textViewPasswordSignup;

  @NonNull
  public final TextView textViewUsernameSignup;

  @NonNull
  public final TextView textViewWelcomeMsg;

  private ActivityAccountTypeBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonCreateOwner, @NonNull Button buttonCreateShopper,
      @NonNull EditText editTextPasswordSignup, @NonNull EditText editTextUsernameSignup,
      @NonNull ConstraintLayout textView2, @NonNull TextView textViewPasswordSignup,
      @NonNull TextView textViewUsernameSignup, @NonNull TextView textViewWelcomeMsg) {
    this.rootView = rootView;
    this.buttonCreateOwner = buttonCreateOwner;
    this.buttonCreateShopper = buttonCreateShopper;
    this.editTextPasswordSignup = editTextPasswordSignup;
    this.editTextUsernameSignup = editTextUsernameSignup;
    this.textView2 = textView2;
    this.textViewPasswordSignup = textViewPasswordSignup;
    this.textViewUsernameSignup = textViewUsernameSignup;
    this.textViewWelcomeMsg = textViewWelcomeMsg;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAccountTypeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAccountTypeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_account_type, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAccountTypeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonCreateOwner;
      Button buttonCreateOwner = ViewBindings.findChildViewById(rootView, id);
      if (buttonCreateOwner == null) {
        break missingId;
      }

      id = R.id.buttonCreateShopper;
      Button buttonCreateShopper = ViewBindings.findChildViewById(rootView, id);
      if (buttonCreateShopper == null) {
        break missingId;
      }

      id = R.id.editTextPasswordSignup;
      EditText editTextPasswordSignup = ViewBindings.findChildViewById(rootView, id);
      if (editTextPasswordSignup == null) {
        break missingId;
      }

      id = R.id.editTextUsernameSignup;
      EditText editTextUsernameSignup = ViewBindings.findChildViewById(rootView, id);
      if (editTextUsernameSignup == null) {
        break missingId;
      }

      ConstraintLayout textView2 = (ConstraintLayout) rootView;

      id = R.id.textViewPasswordSignup;
      TextView textViewPasswordSignup = ViewBindings.findChildViewById(rootView, id);
      if (textViewPasswordSignup == null) {
        break missingId;
      }

      id = R.id.textViewUsernameSignup;
      TextView textViewUsernameSignup = ViewBindings.findChildViewById(rootView, id);
      if (textViewUsernameSignup == null) {
        break missingId;
      }

      id = R.id.textViewWelcomeMsg;
      TextView textViewWelcomeMsg = ViewBindings.findChildViewById(rootView, id);
      if (textViewWelcomeMsg == null) {
        break missingId;
      }

      return new ActivityAccountTypeBinding((ConstraintLayout) rootView, buttonCreateOwner,
          buttonCreateShopper, editTextPasswordSignup, editTextUsernameSignup, textView2,
          textViewPasswordSignup, textViewUsernameSignup, textViewWelcomeMsg);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
