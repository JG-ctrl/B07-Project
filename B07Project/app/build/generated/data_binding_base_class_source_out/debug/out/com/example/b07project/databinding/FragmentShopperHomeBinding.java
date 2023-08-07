// Generated by view binder compiler. Do not edit!
package com.example.b07project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.b07project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentShopperHomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RecyclerView recyclerViewShopperHome;

  @NonNull
  public final TextView shoppingCart;

  private FragmentShopperHomeBinding(@NonNull RelativeLayout rootView,
      @NonNull RecyclerView recyclerViewShopperHome, @NonNull TextView shoppingCart) {
    this.rootView = rootView;
    this.recyclerViewShopperHome = recyclerViewShopperHome;
    this.shoppingCart = shoppingCart;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentShopperHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentShopperHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_shopper_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentShopperHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recycler_view_shopper_home;
      RecyclerView recyclerViewShopperHome = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewShopperHome == null) {
        break missingId;
      }

      id = R.id.shopping_cart;
      TextView shoppingCart = ViewBindings.findChildViewById(rootView, id);
      if (shoppingCart == null) {
        break missingId;
      }

      return new FragmentShopperHomeBinding((RelativeLayout) rootView, recyclerViewShopperHome,
          shoppingCart);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
