package com.tasneem.poftakehome.feat.ui.main.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tasneem.poftakehome.BR;
import com.tasneem.poftakehome.R;
import com.tasneem.poftakehome.databinding.MainFragmentBinding;
import com.tasneem.poftakehome.feat.ui.main.viewmodel.CommitItemViewModel;
import com.tasneem.poftakehome.feat.ui.main.viewmodel.MainViewModel;
import com.tasneem.poftakehome.feat.util.recyclerview.EasyRecyclerAdapter;
import com.tasneem.poftakehome.feat.util.recyclerview.MapData;

import kotlin.jvm.functions.Function1;

public class MainFragment extends Fragment {

    @NonNull
    private MainViewModel mViewModel;

    @NonNull
    private MainFragmentBinding mBinding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        if (getActivity() == null) return null;
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mBinding.setFragment(this);
        mBinding.setViewModel(mViewModel);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.loadLD.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                // Do Nothing
            }
        });
        mViewModel.selectedItem.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer position) {
                if (position == null ) return;
                DetailsDialogFragment.show(getFragmentManager(), position);
                mViewModel.selectedItem.setValue(null);
            }
        });
        mViewModel.loadData();
    }

    public EasyRecyclerAdapter getAdapter() {
        EasyRecyclerAdapter adapter = new EasyRecyclerAdapter(mViewModel.list);
        adapter.addMapping(CommitItemViewModel.class, new Function1<Object, MapData>() {
            @Override
            public MapData invoke(Object o) {
                return new MapData(R.layout.layout_item_commit, BR.viewModel);
            }
        });
        return adapter;
    }

    public LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

}
