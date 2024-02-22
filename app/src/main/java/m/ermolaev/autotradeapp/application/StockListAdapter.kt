package m.ermolaev.autotradeapp.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import m.ermolaev.autotradeapp.R

class StockListAdapter(private val dataList: List<StockData>) : RecyclerView.Adapter<StockListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val textPrice: TextView = itemView.findViewById(R.id.textPrice)
        val buttonTrade: Button = itemView.findViewById(R.id.tradeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_stock_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.textTitle.text = currentItem.title
        holder.textDescription.text = currentItem.description
        holder.textPrice.text = currentItem.price
        holder.buttonTrade.setOnClickListener {
            // Действие по нажатию на кнопку "Use"
        }
    }

    override fun getItemCount() = dataList.size
}

class StockData(val title: String, val description: String, val price: String)

