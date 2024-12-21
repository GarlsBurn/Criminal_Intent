package com.bignerdranch.android.criminalintent

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class Crime(val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: String = SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.ENGLISH).format(Date()),
                 var isSolved: Boolean = false)