package com.example.tuesday.fragment

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.Data.EventData
import com.example.tuesday.Object.EventDataHolder
import com.example.tuesday.R
import com.example.tuesday.adapter.UserEventAdapter
import com.example.tuesday.databinding.FragmentUserEventBinding
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class UserEventFragment : Fragment() {

    private lateinit var userEventAdapter: UserEventAdapter
    private lateinit var binding: FragmentUserEventBinding
    private var todayBtn: Boolean = true
    private var weekBtn: Boolean = false
    private var monthBtn: Boolean = false

    private var currentMonth: Int? = null
    private var currentDay: Int? = null

    private lateinit var credential: GoogleAccountCredential
    private lateinit var calendarService: Calendar
    private lateinit var jsonFactory: JsonFactory
    private lateinit var transport: com.google.api.client.http.HttpTransport

    private val btnState: String = "today"
    private val SCOPES = listOf(CalendarScopes.CALENDAR_READONLY)
    private val REQUEST_AUTHORIZATION = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserEventBinding.inflate(inflater, container, false)
        val calendar = java.util.Calendar.getInstance()
        currentMonth = calendar.get(java.util.Calendar.MONTH) + 1
        currentDay = calendar.get(java.util.Calendar.DAY_OF_MONTH)

        initRecyclerView()
        btnColorChange()
        setBtnColor()
        getSelectedDate()
        return binding.root
    }

    private fun getSelectedDate() {
        binding.calendarCv.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "${year}-${month + 1}-${dayOfMonth}"
            currentMonth = month + 1
            currentDay = dayOfMonth
            userEventAdapter.setDate(currentMonth!!, currentDay!!)
            // 여기서 month랑 date만 보내기
        }
    }

    private fun setBtnColor() {
        // today
        if (todayBtn) {
            // 참이면 이렇게 거짓이면 다르게
            binding.todayBtn.setBackgroundColor(Color.parseColor("#475E3E"))
            binding.todayTv.setTextColor((Color.parseColor("#FFFFFF")))
        } else {
            binding.todayBtn.setBackgroundColor(Color.parseColor("#F0F4EF"))
            binding.todayTv.setTextColor((Color.parseColor("#475E3E")))
        }
        // week
        if (weekBtn) {
            // 참이면 이렇게 거짓이면 다르게
            binding.weekBtn.setBackgroundColor(Color.parseColor("#475E3E"))
            binding.weekTv.setTextColor((Color.parseColor("#FFFFFF")))
        } else {
            binding.weekBtn.setBackgroundColor(Color.parseColor("#F0F4EF"))
            binding.weekTv.setTextColor((Color.parseColor("#475E3E")))
        }

        // month
        if (monthBtn) {
            // 참이면 이렇게 거짓이면 다르게
            binding.monthBtn.setBackgroundColor(Color.parseColor("#475E3E"))
            binding.monthTv.setTextColor((Color.parseColor("#FFFFFF")))
        } else {
            binding.monthBtn.setBackgroundColor(Color.parseColor("#F0F4EF"))
            binding.monthTv.setTextColor((Color.parseColor("#475E3E")))
        }
    }

    private fun btnColorChange() {
        binding.todayBtn.setOnClickListener {
            if (!todayBtn) {
                todayBtn = true
                weekBtn = false
                monthBtn = false
                setBtnColor()
                userEventAdapter.setState("today") // 버튼 상태에 따라서 btnState 업데이트
            }
        }
        binding.weekBtn.setOnClickListener {
            if (!weekBtn) {
                todayBtn = false
                weekBtn = true
                monthBtn = false
                setBtnColor()
                userEventAdapter.setState("week") // 버튼 상태에 따라서 btnState 업데이트
            }
        }
        binding.monthBtn.setOnClickListener {
            if (!monthBtn) {
                todayBtn = false
                weekBtn = false
                monthBtn = true
                setBtnColor()
                userEventAdapter.setState("month") // 버튼 상태에 따라서 btnState 업데이트
            }
        }
    }

    private fun initRecyclerView() {
        // itemList 초기화
        EventDataHolder.itemList = ArrayList()

        userEventAdapter = UserEventAdapter(requireContext(), EventDataHolder.itemList, btnState, currentMonth!!, currentDay!!)
        binding.eventRv.adapter = userEventAdapter
        binding.eventRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        Log.d("itemList", EventDataHolder.itemList.toString())
        binding.eventRv.addItemDecoration(SpaceItemDecoration(25))

        userEventAdapter.setOnItemClickListener(object : UserEventAdapter.OnItemClickListener {
            override fun onItemClick(eventName: String) {
                Log.d("thisclicked", eventName)
             //TODO:실행만 넣기



            }
        })
    }

    private fun initData() {
        //TODO: 나중에 비동기로 값 받아오기
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Google Calendar API 초기화
        try {
            transport = com.google.api.client.http.javanet.NetHttpTransport()
            jsonFactory = JacksonFactory.getDefaultInstance()
            credential = GoogleAccountCredential.usingOAuth2(requireContext(), SCOPES)

            // login_page에서 전달한 이메일을 받아 설정
            val email = arguments?.getString("email")

            credential.selectedAccountName = email
            Log.d("GoogleAccountCredential", "Selected account: ${credential.selectedAccountName}")

            calendarService = Calendar.Builder(
                transport,
                jsonFactory,
                credential
            )
                .setApplicationName(getString(R.string.app_name))
                .build()

            // 사용자가 속한 모든 캘린더의 이벤트 가져오기
            loadEventsFromAllCalendars()

        } catch (e: Exception) {
            Log.d("fail", "fail")
            e.printStackTrace()
        }
    }

    private fun loadEventsFromAllCalendars() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // 사용자의 모든 캘린더 목록 가져오기
                val calendarList = calendarService.calendarList().list().execute().items
                // 각 캘린더의 이벤트 가져오기
                val allEvents = mutableListOf<Event>()
                for (calendar in calendarList) {
                    val events: Events = calendarService.events().list(calendar.id)
                        .setMaxResults(10)
                        .setTimeMin(com.google.api.client.util.DateTime(System.currentTimeMillis()))
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute()
                    allEvents.addAll(events.items)
                }

                // 가져온 이벤트들을 출력하거나 처리하기
                for (event in allEvents) {
                    val start = event.start?.dateTime ?: event.start?.date
                    val end = event.end?.dateTime ?: event.end?.date
                    EventDataHolder.itemList.add(EventData(event.summary, start.toString(), end.toString()))
                    Log.d("Event summary", "${event.summary}")
                    Log.d("Event start", "$start")
                    Log.d("Event end", "$end")
                }

                // UI 업데이트는 메인 스레드에서 처리해야 함
                withContext(Dispatchers.Main) {
                    userEventAdapter.notifyDataSetChanged()
                }
            } catch (e: UserRecoverableAuthIOException) {
                // 사용자 인증 문제 처리
                Log.d("err", "UserRecoverableAuthIOException 발생")
                startActivityForResult(e.intent, REQUEST_AUTHORIZATION)
                e.printStackTrace()
            } catch (e: IOException) {
                Log.d("err", "IOException 발생")
                e.printStackTrace()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_AUTHORIZATION) {
            if (resultCode == android.app.Activity.RESULT_OK) {
                // 사용자가 권한을 승인했으므로 다시 시도
                loadEventsFromAllCalendars()
            } else {
                // 사용자가 권한을 승인하지 않음
                Log.d("err", "사용자가 승인하지 않음")
            }
        }
    }

    class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = space
        }
    }
}
