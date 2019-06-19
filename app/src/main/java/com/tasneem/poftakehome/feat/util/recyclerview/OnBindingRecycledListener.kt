package com.tasneem.poftakehome.feat.util.recyclerview

import android.databinding.ViewDataBinding

/**
 * OnBindingRecycledListener
 * Description
 * @author tasneem
 */
interface OnBindingRecycledListener {
    fun onAttached(binding: ViewDataBinding?)
    fun onDetached(binding: ViewDataBinding?)
}