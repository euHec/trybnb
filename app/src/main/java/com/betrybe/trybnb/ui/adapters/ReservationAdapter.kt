package com.betrybe.trybnb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.Booking

class ReservationAdapter(private val items: List<Booking?>) : Adapter<ReservationAdapter.ItemReservationViewHolder>() {

    class ItemReservationViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.name_item_reservation)
        val checkin: TextView = view.findViewById(R.id.checkin_item_reservation)
        val checkout: TextView = view.findViewById(R.id.checkout_item_reservation)
        val additionalNeeds: TextView = view.findViewById(R.id.additional_needs_item_reservation)
        val totalPrice: TextView = view.findViewById(R.id.total_price_item_reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemReservationViewHolder {
        return ItemReservationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_reservation, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemReservationViewHolder, position: Int) {
        val booking = items[position]
        holder.name.text = buildString {
            booking?.let {
                append(it.firstname)
                append(" ")
                append(it.lastname)
            }
        }
        holder.checkin.text = booking?.bookingdates?.checkin
        holder.checkout.text = booking?.bookingdates?.checkout
        holder.additionalNeeds.text = booking?.additionalneeds
        holder.totalPrice.text = booking?.totalprice.toString()
    }
}