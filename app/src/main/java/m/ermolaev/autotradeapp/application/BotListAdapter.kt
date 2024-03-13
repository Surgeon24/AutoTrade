package m.ermolaev.autotradeapp.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.socket.SendMessage
import org.json.JSONArray
import org.json.JSONObject


class BotListAdapter(private val dataList: ArrayList<Bot>, private val activity: ApplicationActivity) : RecyclerView.Adapter<BotListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textSymbol: TextView = itemView.findViewById(R.id.textTitle)
        val textStrategy: TextView = itemView.findViewById(R.id.textDescription)
        val buttonStop: Button = itemView.findViewById(R.id.stopButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_bot_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.textSymbol.text = currentItem.symbol
        holder.textStrategy.text = currentItem.strategy
        holder.buttonStop.setOnClickListener {
            val bot = activity.findStrategy(currentItem.id)
            ApplicationActivity.activeStrategyList.remove(bot)

            val argumentsArray = JSONArray()
            argumentsArray.put(currentItem.id)
            val pairJson = JSONObject()
            pairJson.put("method", "stopStrategy")
            pairJson.put("arguments",argumentsArray)
            SendMessage().execute(pairJson.toString())

        }
    }

    override fun getItemCount() = dataList.size
}

