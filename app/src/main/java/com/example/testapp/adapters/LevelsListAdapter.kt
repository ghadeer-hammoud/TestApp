package com.example.testapp.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.models.Level

class LevelsRecyclerAdapter(private var context: Context,
                            private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<LevelsRecyclerAdapter.LevelViewHolder>() {

    private var dataSet = ArrayList<Level>()

    inner class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val tv_order: TextView = view.findViewById(R.id.tv_order)
        private val tv_title: TextView = view.findViewById(R.id.tv_title)
        private val tv_description: TextView = view.findViewById(R.id.tv_description)
        private val iv_locked: ImageView = view.findViewById(R.id.iv_locked)
        private val iv_confirmed: ImageView = view.findViewById(R.id.iv_confirmed)


        fun bind(level: Level){
            tv_order.text = level.order.toString()
            tv_title.text = level.title
            tv_description.text = level.description

            if(level.isLocked){
                itemView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.gray_light))
                tv_order.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.gray_light))
                tv_order.setTextColor(ContextCompat.getColor(context, R.color.black))
                iv_locked.setImageResource(R.drawable.ic_lock_closed_24)
                iv_locked.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
            else{
                itemView.setOnClickListener(this)
                iv_locked.setImageResource(R.drawable.ic_lock_open_24)
                iv_locked.setColorFilter(ContextCompat.getColor(context, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
            }

            if(level.isConfirmed){
                iv_confirmed.setColorFilter(ContextCompat.getColor(context, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
            else{
                iv_confirmed.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        }

        override fun onClick(p0: View?) {
            val level = dataSet[adapterPosition]
            onItemClickListener.onItemClick(level.id!!)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_level, viewGroup, false)

        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: LevelViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    public fun setData(levelsList: List<Level>){
        this.dataSet = ArrayList(levelsList)
        notifyDataSetChanged()

    }
    override fun getItemCount() = dataSet.size

    interface OnItemClickListener {
        fun onItemClick(levelId: Int)
    }

}



