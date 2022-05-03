package com.chase.interview.project.recylerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import boldFirstWord
import com.chase.interview.project.R
import com.chase.interview.project.base.BaseViewHolder
import com.chase.interview.project.models.SchoolDirectoryObj
import com.google.android.material.button.MaterialButton
import getFirstWord

class SchoolDirAdapter constructor(val listener: SchoolDirItemActionListener): RecyclerView.Adapter<BaseViewHolder<*>>(), Filterable {
    private val dataList: MutableList<SchoolDirectoryObj> = mutableListOf()
    private var filteredData: MutableList<SchoolDirectoryObj> = this.dataList
    fun setData(list: MutableList<SchoolDirectoryObj>?) {
        list?.let {
            this.dataList.clear()
            this.dataList.addAll(list)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.school_directory_list_item_view, parent, false)
        val holder = PreviewLinkViewHolder(view)
        holder.learnMoreBt?.setOnClickListener {
            val schoolDirectoryObj: SchoolDirectoryObj = holder.itemView.tag as SchoolDirectoryObj
            this.listener.onLearnMoreClicked(schoolDirectoryObj)
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as PreviewLinkViewHolder).bindView(this.dataList[position])
    }

    override fun getItemCount(): Int {
        return this.dataList.size
    }
    inner class PreviewLinkViewHolder(private val view: View): BaseViewHolder<SchoolDirectoryObj>(view) {
        private var schoolNameView: AppCompatTextView?= null
        private var addressView: AppCompatTextView?= null
        private var phoneNumView: AppCompatTextView?= null
        private var faxNumView: AppCompatTextView?= null
        private var emailView: AppCompatTextView?= null
        var  learnMoreBt: MaterialButton?= null
        init {
            this.schoolNameView = this.view.findViewById(R.id.school_dir_item_school_name_view_id)
            this.addressView = this.view.findViewById(R.id.school_dir_item_address_view_id)
            this.phoneNumView = this.view.findViewById(R.id.school_dir_item_phoneNum_view_id)
            this.faxNumView = this.view.findViewById(R.id.school_dir_item_fax_view_id)
            this.emailView = this.view.findViewById(R.id.school_dir_item_email_view_id)
            this.learnMoreBt = this.view.findViewById(R.id.school_dir_item_learnMore_bt_id)
        }
        override fun bindView(item: SchoolDirectoryObj) {
            itemView.tag = item
            val address = "${item.primaryAddressLine1}, ${item.city}, ${item.stateCode}, ${item.postcode}"
            this.schoolNameView?.text = item.schoolName
            this.addressView?.text = address
            this.phoneNumView?.visibility = if (item.phoneNumber?.isNotEmpty()!!) View.VISIBLE else View.GONE
            this.faxNumView?.visibility = if (item.faxNumber?.isNotEmpty()!!) View.VISIBLE else View.GONE
            this.emailView?.visibility = if (item.schoolEmail?.isNotEmpty()!!) View.VISIBLE else View.GONE
            val phoneNumber = "${view.context.resources.getString(R.string.phone_number)}: ${item.phoneNumber}"
            val faxNumber = "${view.context.resources.getString(R.string.fax)}: ${item.faxNumber}"
            val email = "${view.context.resources.getString(R.string.email)}: ${item.schoolEmail}"
            this.phoneNumView?.let {
                boldFirstWord(getFirstWord(phoneNumber).length, phoneNumber, it,true)
            }
            this.faxNumView?.let {
                boldFirstWord(getFirstWord(faxNumber).length, faxNumber, it,true)
            }
            this.emailView?.let {
                boldFirstWord(getFirstWord(email).length, email, it,true)
            }
        }
    }
    interface SchoolDirItemActionListener {
        fun onLearnMoreClicked(schoolDirectoryObj: SchoolDirectoryObj)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraints: CharSequence?): FilterResults {
                val resultList: MutableList<SchoolDirectoryObj> = mutableListOf()
                filteredData = if (!(constraints == null || constraints.isEmpty())) {
                    val queries: String = constraints.toString()
                    for (schoolDirObj in dataList) {
                        if (schoolDirObj.schoolName?.lowercase()?.contains(queries.lowercase())!! ||
                                schoolDirObj.ellPrograms?.lowercase()?.contains(queries.lowercase())!! ||
                                schoolDirObj.diplomaendorsements?.lowercase()?.contains(queries.lowercase())!!) {
                            resultList.add(schoolDirObj)
                        }
                    }
                    resultList
                } else {
                    dataList
                }
                val filters = FilterResults()
                filters.values = filteredData
                filters.count = filteredData.size
                return filters
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraints: CharSequence?, results: FilterResults?) {
                if (results?.values != null) {
                    val newData = results.values as ArrayList<*>
                    if (newData.size > 0) {
                        filteredData = newData as MutableList<SchoolDirectoryObj>
                        notifyItemRangeRemoved(0, filteredData.size)
                        notifyItemRangeInserted(0, newData.size)
                    }
                }
            }
        }
    }
}