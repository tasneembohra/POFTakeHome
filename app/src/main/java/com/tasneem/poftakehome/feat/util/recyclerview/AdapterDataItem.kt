package com.tasneem.poftakehome.feat.util.recyclerview


class AdapterDataItem(var layoutId: Int, variableId: Int, model: Any) {
    var idModelPairs: MutableList<Pair<Int, Any>>? = null

    init {
        this.idModelPairs = ArrayList()
        this.idModelPairs!!.add(Pair(variableId, model))
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as AdapterDataItem?

        if (layoutId != that!!.layoutId) return false
        return if (idModelPairs != null) idModelPairs == that.idModelPairs else that.idModelPairs == null

    }

    override fun hashCode(): Int {
        var result = layoutId
        result = 31 * result + if (idModelPairs != null) idModelPairs!!.hashCode() else 0
        return result
    }
}