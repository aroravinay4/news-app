package com.indigo.newsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.indigo.newsapp.R;
import com.indigo.newsapp.adapter.NewsListAdapter;
import com.indigo.newsapp.databinding.FragmentNewListBinding;
import com.indigo.newsapp.model.Article;
import com.indigo.newsapp.viewmodel.NewsListViewModel;

public class FragmentNewsList extends Fragment {

    public static final String TAG = FragmentNewsList.class.getSimpleName();
    public static final String CATEGORY = "category";
    private String selectedCategory;
    FragmentNewListBinding fragmentNewListBinding;

    private NewsListViewModel newsListViewModel;

    public FragmentNewsList() {
        // Required empty public constructor
    }

    public static FragmentNewsList newInstance(String category) {
        FragmentNewsList fragment = new FragmentNewsList();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedCategory = getArguments().getString(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentNewListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_list, container, false);
        newsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);

        fragmentNewListBinding.setModel(newsListViewModel);

        fragmentNewListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsListViewModel.fetchList(selectedCategory);

        setupListUpdate();
        return fragmentNewListBinding.getRoot();
    }


    private void setupListUpdate() {

        final NewsListAdapter adapter = new NewsListAdapter();

        newsListViewModel.itemPagedList.observe(this, new Observer<PagedList<Article>>() {
            @Override
            public void onChanged(@Nullable PagedList<Article> items) {
                adapter.submitList(items);
            }
        });

        fragmentNewListBinding.recyclerView.setAdapter(adapter);

    }


}
