package m.ermolaev.autotradeapp.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import m.ermolaev.autotradeapp.R

class StrategyListAdapter(private val dataList: ArrayList<StrategyData>) : RecyclerView.Adapter<StrategyListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val buttonUse: Button = itemView.findViewById(R.id.useButton)
        val buttonMore: Button = itemView.findViewById(R.id.moreButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_strategy_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.textTitle.text = currentItem.title
        holder.textDescription.text = currentItem.description
        holder.buttonUse.setOnClickListener {
            // Действие по нажатию на кнопку "Use"
        }
        holder.buttonMore.setOnClickListener {
            // Действие по нажатию на кнопку "More"
        }
    }

    override fun getItemCount() = dataList.size
}

class StrategyData(val title: String, val description: String)

