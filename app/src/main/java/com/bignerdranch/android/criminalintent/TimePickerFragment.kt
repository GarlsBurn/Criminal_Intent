package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import java.util.Date


private const val ARG_TIME = "time"

class TimePickerFragment: DialogFragment() {

    interface Callbacks{
        fun onTimeSelected(time: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dateListener = TimePickerDialog.OnTimeSetListener{
            _, hour, minute ->

            val resultTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
            }.time

            targetFragment?.let { fragment -> (fragment as Callbacks).onTimeSelected(resultTime) }
        }

        val time = arguments?.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = time
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
                requireContext(),
            dateListener,
            hour,
            minute,
            DateFormat.is24HourFormat(requireContext())
                )
    }

    companion object{
        fun newInstance(time: Date): TimePickerFragment{
            val args = Bundle().apply {
                putSerializable(ARG_TIME, time)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}