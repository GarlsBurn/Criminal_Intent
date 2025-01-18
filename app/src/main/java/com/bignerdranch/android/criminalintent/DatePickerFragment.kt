package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Date

private const val ARD_DATE = "date"

class DatePickerFragment: DialogFragment() {

    val dateListener = DatePickerDialog.OnDateSetListener{
        _: DatePicker, year: Int, month: Int, day: Int ->

        val resultDate: Date = GregorianCalendar(year, month, day).time

        targetFragment?.let {
            fragment -> (fragment as Callbacks).onDateSelected(resultDate)
        }
    }

    interface  Callbacks {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val calendar = Calendar.getInstance()
        val date = arguments?.getSerializable(ARD_DATE) as Date
        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object{
        fun newInstance(date: Date): DatePickerFragment{
            val args = Bundle().apply {
                putSerializable(ARD_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }

        }
    }

}