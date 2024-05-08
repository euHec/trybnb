package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.ApiClientService
import com.betrybe.trybnb.ui.adapters.ReservationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentReservation : Fragment() {
    private val serviceAPI = ApiClientService.instance
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)

        val recyclerView: RecyclerView by lazy {
            view.findViewById(R.id.reservation_recycler_view)
        }

        CoroutineScope(IO).launch {
            try {
                ApiIdlingResource.increment()
                val responseBookingID = serviceAPI.getBookings()
                val body = responseBookingID.body()
                val bookingsID = body?.subList(0,5)
                if (responseBookingID.isSuccessful && bookingsID != null) {
                    val responseBooking = bookingsID.map {
                        serviceAPI.getBookingID(it.bookingid.toString()).body()
                    }
                    val bookingFilter = responseBooking.filterNotNull()
                    withContext(Main) {
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        recyclerView.adapter = ReservationAdapter(bookingFilter)
                    }
                }
                ApiIdlingResource.decrement()
            } catch(e: Exception) {
                ApiIdlingResource.decrement()
                Log.e("RETURN-APP", e.message.toString())
            }
        }

        return view
    }
}