package com.tasneem.poftakehome.feat.util.recyclerview

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*
import kotlin.reflect.KClass


/**
 * @author tasneem
 */
open class EasyRecyclerAdapter(private val data: ObservableList<Any>) : RecyclerView.Adapter<EasyRecyclerAdapter.BindingViewHolder>() {
    private val modelLayoutMap = HashMap<Class<*>, OnMap<Any>>()
    var onRecycleListener: OnBindingRecycledListener? = null
    init {
        data.addOnListChangedCallback(ObservableListCallback())
    }

    override fun onViewAttachedToWindow(holder: BindingViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (onRecycleListener != null) {
            onRecycleListener?.onAttached(holder.binding)
        }
    }

    override fun onViewDetachedFromWindow(holder: BindingViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (onRecycleListener != null) {
            onRecycleListener?.onDetached(holder.binding)
        }
    }

    fun addMapping(modelClass: Class<*>, mapFunc: OnMap<Any>): EasyRecyclerAdapter {
        if (modelLayoutMap.keys.contains(modelClass))
            throw IllegalStateException("This model already has a mapping function!")
        modelLayoutMap[modelClass] = mapFunc
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val dataItem = map(position)
        onBindViewHolderInternal(holder, dataItem)
    }


    protected fun onBindViewHolderInternal(holder: BindingViewHolder, dataItem: AdapterDataItem) {
        for ((first, second) in dataItem.idModelPairs!!) {
            holder.bind(first, second)
        }
        holder.binding.executePendingBindings()
    }

    open protected fun map(position: Int): AdapterDataItem {
        val o = data[position]
        Log.d("EasyRecyclerAdapter", "map : "+ o::class.java.canonicalName)
        modelLayoutMap.keys
                .filter {
                    Log.d("EasyRecyclerAdapter", "map : "+ (o::class.java == it))
                    o::class.java == it
                }
                .mapNotNull {
                    Log.d("EasyRecyclerAdapter", "map 2 : "+ it.canonicalName)
                    modelLayoutMap[it]
                }
                .map {
                    Log.d("EasyRecyclerAdapter", "map 3 : "+ it)
                    it.invoke(o)
                    //it(o)
                }
                .forEach { return AdapterDataItem(it.layoutId, it.bindingVariableId, o) }

        throw IllegalStateException("Unknown object type. ${o.javaClass.canonicalName} Please map it to a layout first")
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return map(position).layoutId
    }


    private inner class ObservableListCallback : ObservableList.OnListChangedCallback<ObservableList<Any>>() {

        override fun onChanged(sender: ObservableList<Any>) {
            notifyDataSetChanged()
        }

        override fun onItemRangeChanged(sender: ObservableList<Any>, positionStart: Int, itemCount: Int) {
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableList<Any>, positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableList<Any>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            notifyDataSetChanged() // not sure how to notify adapter of this event
        }

        override fun onItemRangeRemoved(sender: ObservableList<Any>, positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
        }
    }


    inner class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(varId: Int, obj: Any) {
            this.binding.setVariable(varId, obj)
        }
    }


}
typealias OnMap<T> = (model: T) -> MapData
class MapData(val layoutId: Int, val bindingVariableId: Int)