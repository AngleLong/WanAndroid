package com.angle.lib_home.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.angle.lib_home.R
import com.angle.lib_home.databinding.ItemHomeMyBinding

class HomeItemAdapter(val context: Context) :
    PagingDataAdapter<Data, BaseViewHolder<ItemHomeMyBinding>>(COMPARATOR) {

    companion object {
        //因为Paging 3在内部会使用DiffUtil来管理数据变化，所以这个COMPARATOR是必须的
        private val COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ItemHomeMyBinding> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_my, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemHomeMyBinding>, position: Int) {

        holder.binding?.let {
            val data = getItem(position)

            //设置标题
            it.titleTv.text = data?.title

            //设置作者
            if (data?.author?.isNotEmpty() == true) {
                it.authorTv.text =
                    String.format(context.resources.getString(R.string.home_item_author),
                        data.author)
            }

            //设置
            it.niceDateTv.text = data?.niceDate
        }

    }
}

class BaseViewHolder<DB : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: DB? = DataBindingUtil.bind(itemView)
}