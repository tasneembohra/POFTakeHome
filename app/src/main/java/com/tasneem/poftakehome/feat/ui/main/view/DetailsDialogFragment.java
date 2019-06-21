package com.tasneem.poftakehome.feat.ui.main.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tasneem.poftakehome.R;
import com.tasneem.poftakehome.databinding.LayoutBottomSheetBinding;
import com.tasneem.poftakehome.feat.ui.main.viewmodel.CommitItemViewModel;
import com.tasneem.poftakehome.feat.ui.main.viewmodel.MainViewModel;

import org.jetbrains.annotations.Nullable;

public class DetailsDialogFragment extends BottomSheetDialogFragment {
    private final static String EXTRA_ID = "position";
    private CommitItemViewModel vm;

    public static void show(FragmentManager fragmentManager, int position) {
        DetailsDialogFragment fragment = new DetailsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, position);
        fragment.setArguments(bundle);
        fragment.show(fragmentManager, "DetailsDialogFragment");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (getActivity() == null || getArguments() == null) return null;
        LayoutBottomSheetBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_bottom_sheet, container, false);
        MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        int position = getArguments().getInt(EXTRA_ID);
        vm = viewModel.getSelectedItemViewModel(position);
        if (vm == null) return null;
        binding.setFragment(this);
        binding.setViewModel(vm);
        return binding.getRoot();

    }

    public void close() {
        if (getActivity() != null && getActivity().getSupportFragmentManager() != null) {
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("DetailsDialogFragment");
            if (fragment != null)
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void onImageClick() {
        if (getActivity() == null) return;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(vm.webUrl));
        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
