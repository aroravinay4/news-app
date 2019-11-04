package com.indigo.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.indigo.newsapp.R;
import com.indigo.newsapp.model.Article;
import com.indigo.newsapp.viewmodel.NewsListViewModel;

import java.util.List;

public class NewsListAdapter extends PagedListAdapter<Article, NewsListAdapter.GenericViewHolder> {

    public NewsListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<Article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Article>() {
                @Override
                public boolean areItemsTheSame(Article article, Article newItem) {
                    return article.getTitle() == newItem.getTitle();
                }

                @Override
                public boolean areContentsTheSame(Article oldItem, Article newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle());
                }
            };

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list_layout, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {


        holder.bind(getItem(position));
    }


    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Article article) {
            binding.setVariable(BR.article, article);
            binding.executePendingBindings();
        }

    }
}
