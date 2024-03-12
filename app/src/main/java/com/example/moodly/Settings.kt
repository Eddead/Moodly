package com.example.moodly

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ToggleButton
import android.widget.TimePicker
import androidx.cardview.widget.CardView
import android.widget.TextView
import androidx.core.content.ContextCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class Settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val textViewRemind: TextView? = view.findViewById(R.id.textViewReminder) as? TextView
        val toggleButton: ToggleButton? = view.findViewById(R.id.toggleButton2) as? ToggleButton
        val timePicker: TimePicker? = view.findViewById(R.id.timePicker) as? TimePicker
        val cardSettings: CardView? = view.findViewById(R.id.cardSettings) as? CardView
        val buttonSetTime: Button? = view.findViewById(R.id.buttonSetTime)

        toggleButton?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Toggle is ON, show TimePicker
                textViewRemind!!.setText("Turn Reminder Off?")
                timePicker!!.visibility = View.VISIBLE
                val params = cardSettings!!.layoutParams
                params.height = resources.getDimensionPixelSize(R.dimen.card_height_whenSpinner)
                cardSettings.layoutParams = params
                buttonSetTime!!.visibility = View.VISIBLE

                buttonSetTime.setOnHoverListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_HOVER_ENTER -> {
                            // Change button color on hover enter
                            buttonSetTime.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue_hovered))
                        }
                        MotionEvent.ACTION_HOVER_EXIT -> {
                            // Revert button color on hover exit
                            buttonSetTime.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue_normal))
                        }
                    }
                    true
                }

                buttonSetTime.setOnClickListener {
                }
            } else {
                // Toggle is OFF, hide TimePicker
                timePicker!!.visibility = View.GONE
                textViewRemind!!.setText("Turn Reminder On?")
                val params = cardSettings!!.layoutParams
                params.height = resources.getDimensionPixelSize(R.dimen.card_height)
                cardSettings.layoutParams = params
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}