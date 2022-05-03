package com.chase.interview.project.recylerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import boldFirstWord
import com.chase.interview.project.R
import com.chase.interview.project.base.BaseViewHolder
import com.chase.interview.project.models.SatScoreDataObj
import com.chase.interview.project.models.SchoolDirectoryObj
import getFirstWord
import java.lang.IllegalArgumentException

class SchoolDetailsAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val data: MutableList<Any> = mutableListOf()
    private var isLoading: Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            HEADER_VIEW -> {
                val headerView: View = LayoutInflater.from(parent.context).inflate(R.layout.school_details_page_header_layout, parent, false)
                HeaderViewHolder(headerView)
            }
            SAT_SCORE_VIEW -> {
                val satScoreView: View = LayoutInflater.from(parent.context).inflate(R.layout.school_details_page_satscore_layout, parent, false)
                SATScoreViewHolder(satScoreView)
            }
            else -> throw IllegalArgumentException("Unsupported view")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item: Any = this.data[position]
        when (holder) {
            is HeaderViewHolder -> holder.bindView(item as SchoolDirectoryObj)
            is SATScoreViewHolder -> holder.bindView(item as SatScoreDataObj)
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (this.data[position]) {
            is SchoolDirectoryObj -> HEADER_VIEW
            is SatScoreDataObj -> SAT_SCORE_VIEW
            else -> throw IllegalArgumentException("Invalid index position $position")
        }
    }
    fun setData(list: MutableList<Any>) {
        this.data.clear()
        this.data.addAll(list)
        notifyDataSetChanged()
    }
    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }
    inner class HeaderViewHolder(val view: View): BaseViewHolder<SchoolDirectoryObj>(view) {
        private var schoolNameView: AppCompatTextView?= null
        private var overviewView: AppCompatTextView?= null
        init {
            this.schoolNameView = this.view.findViewById(R.id.fragment_school_dir_details_titleview_id)
           this.overviewView = this.view.findViewById(R.id.fragment_school_dir_overview_id)
        }
        override fun bindView(item: SchoolDirectoryObj) {
            this.schoolNameView?.let {
                boldFirstWord(getFirstWord(item.schoolName!!).length, item.schoolName!!, it, false)
            }
            overviewView?.text = item.overviewParagraph
        }
    }
    inner class SATScoreViewHolder(val view: View): BaseViewHolder<SatScoreDataObj>(view) {
        private var mathScoreView: AppCompatTextView?= null
        private var readingScoreView: AppCompatTextView?= null
        private var writingScoreView: AppCompatTextView?= null

        init {
            this.mathScoreView = this.view.findViewById(R.id.fragment_school_dir_details_math_score_view_id)
            this.readingScoreView = this.view.findViewById(R.id.fragment_school_dir_details_reading_score_view_id)
            this.writingScoreView = this.view.findViewById(R.id.fragment_school_dir_details_writing_score_view_id)
        }
        override fun bindView(item: SatScoreDataObj) {
            val mathScore = "Math: ${item.satMathAvgScore}"
            val readingScore = "Reading: ${item.criticalReadingAvgScore}"
            val writingScore = "Writing: ${item.satWritingAvgScore}"
            this.mathScoreView?.let {
                boldFirstWord(getFirstWord(mathScore).length, mathScore, it, false)
            }
            this.readingScoreView?.let {
                boldFirstWord(getFirstWord(readingScore).length, readingScore, it, false)
            }
            this.writingScoreView?.let {
                boldFirstWord(getFirstWord(writingScore).length, writingScore, it, false)
            }
        }
    }
    companion object {
        private const val HEADER_VIEW = 0
        private const val SAT_SCORE_VIEW = 1
    }
}