    package com.betrybe.trybnb.ui.views.fragments

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import androidx.core.view.isNotEmpty
    import androidx.fragment.app.Fragment
    import com.betrybe.trybnb.R
    import com.google.android.material.button.MaterialButton
    import com.google.android.material.textfield.TextInputLayout

    class FragmentCreateReservation : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_create_reservation, container, false)

            val firstName: TextInputLayout = view.findViewById(R.id.first_name_create_reservation)
            val lastName: TextInputLayout = view.findViewById(R.id.last_name_create_reservation)
            val checkin: TextInputLayout = view.findViewById(R.id.checkin_create_reservation)
            val checkout: TextInputLayout = view.findViewById(R.id.checkout_create_reservation)
            val specialNeeds: TextInputLayout =
                view.findViewById(R.id.additional_needs_create_reservation)
            val totalPrice: TextInputLayout = view.findViewById(R.id.total_price_create_reservation)
            val buttonCreateReservation: MaterialButton =
                view.findViewById(R.id.create_reservation_button)


            buttonCreateReservation.setOnClickListener {
                val fieldsToValidate = listOf(
                    Pair(firstName, "O campo Nome é obrigatório"),
                    Pair(lastName, "O campo Sobrenome é obrigatório"),
                    Pair(checkin, "O campo Checkin é obrigatório"),
                    Pair(checkout, "O campo Checkout é obrigatório"),
                    Pair(specialNeeds, "O campo Necessidades Adicionais é obrigatório"),
                    Pair(totalPrice, "O campo Preço Total é obrigatório")
                )

                for ((field, message) in fieldsToValidate) {
                    val text = field.editText?.text.toString()
                    if (text.isEmpty()) {
                        field.error = message
                    } else {
                        field.error = null
                    }
                }

            }
            return view
        }
    }