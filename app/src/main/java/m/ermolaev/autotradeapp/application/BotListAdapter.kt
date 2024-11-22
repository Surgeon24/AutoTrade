package m.ermolaev.autotradeapp.application

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import m.ermolaev.autotradeapp.R
import m.ermolaev.autotradeapp.socket.SendMessage
import m.ermolaev.autotradeapp.socket.WebSocketManagerSingleton
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import kotlin.reflect.typeOf


class BotListAdapter(private val dataList: ArrayList<Bot>, private val activity: ApplicationActivity) : RecyclerView.Adapter<BotListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textSymbol: TextView = itemView.findViewById(R.id.textTitle)
        val textStrategy: TextView = itemView.findViewById(R.id.textDescription)
        val botId: TextView = itemView.findViewById(R.id.botId)
        val buttonStop: Button = itemView.findViewById(R.id.stopButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_bot_layout, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.botId.text = "#" + currentItem.id.toString()
        holder.textSymbol.text = currentItem.symbol
        holder.textStrategy.text = currentItem.strategy
        holder.buttonStop.setOnClickListener {
            val bot = activity.findStrategy(currentItem.id)
            Log.d("DEL", "Bot: $bot");
            ApplicationActivity.activeStrategyList.remove(bot)

            val pairJson = JSONObject()
            pairJson.put("method", "stopStrategy")
            val argumentsArray = JSONArray()
            argumentsArray.put(currentItem.id)
            pairJson.put("arguments",argumentsArray)
            WebSocketManagerSingleton.webSocketManager.sendMessage(pairJson.toString())
            Log.d("JSON", pairJson.toString())

            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataList.size)
        }
    }

    override fun getItemCount() = dataList.size
}

