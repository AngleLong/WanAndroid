package com.angle.lib_home.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angle.lib_home.R
import com.angle.lib_home.databinding.FooterItemBinding
import com.angle.lib_home.databinding.HeaderItemBinding

class FooterAdapter(val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {
    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        val binding = holder.binding as FooterItemBinding
        when (loadState) {
            is LoadState.Error -> {

                Log.e("出现的错误", "onBindViewHolder:${loadState.toString()} ")

                binding.progressBar.visibility = View.GONE
                binding.retryButton.visibility = View.VISIBLE
                binding.retryButton.text = "Load Failed, Tap Retry"
                binding.retryButton.setOnClickListener {
                    retry()
                }
            }
            is LoadState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.retryButton.visibility = View.VISIBLE
                binding.retryButton.text = "Loading"
            }
            is LoadState.NotLoading -> {
                binding.progressBar.visibility = View.GONE
                binding.retryButton.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false)
        return ViewHolder(DataBindingUtil.bind(view)!!)
    }
}

class HeaderAdapter(val retry: () -> Unit) : LoadStateAdapter<HeaderAdapter.ViewHolder>() {
    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        val binding = holder.binding as HeaderItemBinding
        when (loadState) {
            is LoadState.Error -> {
                binding.retryButton.text = "Load Failed, Tap Retry"
                binding.retryButton.setOnClickListener {
                    retry()
                }
            }
            is LoadState.Loading -> {
                binding.retryButton.text = "Loading"
            }
            is LoadState.NotLoading -> {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
        return ViewHolder(DataBindingUtil.bind(view)!!)
    }
}