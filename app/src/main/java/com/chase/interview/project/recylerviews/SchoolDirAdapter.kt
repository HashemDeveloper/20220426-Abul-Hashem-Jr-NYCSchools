package com.chase.interview.project.recylerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import boldFirstWord
import com.chase.interview.project.R
import com.chase.interview.project.base.BaseViewHolder
import com.chase.interview.project.models.SchoolDirectoryObj
import getFirstWord

class SchoolDirAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val dataList: MutableList<SchoolDirectoryObj> = mutableListOf()

    fun setData(list: MutableList<SchoolDirectoryObj>?) {
        list?.let {
            this.dataList.clear()
            this.dataList.addAll(list)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.school_directory_list_item_view, parent, false)
        return PreviewLinkViewHolder(view)
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
        init {
            this.schoolNameView = this.view.findViewById(R.id.school_dir_item_school_name_view_id)
            this.addressView = this.view.findViewById(R.id.school_dir_item_address_view_id)
            this.phoneNumView = this.view.findViewById(R.id.school_dir_item_phoneNum_view_id)
            this.faxNumView = this.view.findViewById(R.id.school_dir_item_fax_view_id)
            this.emailView = this.view.findViewById(R.id.school_dir_item_email_view_id)
        }
        override fun bindView(item: SchoolDirectoryObj) {
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
                boldFirstWord(getFirstWord(phoneNumber).length, phoneNumber, it)
            }
            this.faxNumView?.let {
                boldFirstWord(getFirstWord(faxNumber).length, faxNumber, it)
            }
            this.emailView?.let {
                boldFirstWord(getFirstWord(email).length, email, it)
            }
        }
    }
}