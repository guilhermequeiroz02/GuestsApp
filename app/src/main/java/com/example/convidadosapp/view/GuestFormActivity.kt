package com.example.convidadosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidadosapp.viewmodel.GuestFormViewModel
import com.example.convidadosapp.R
import com.example.convidadosapp.databinding.ActivityGuestFormBinding
import com.example.convidadosapp.service.constants.GuestConstants


class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private lateinit var binding: ActivityGuestFormBinding
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)


        setListeners()
        observe()
        loadData()

        val findPresence = findViewById<RadioButton>(R.id.radio_presence)
        findPresence.isChecked = true

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            val findName = findViewById<EditText>(R.id.edit_name)
            val findPresence = findViewById<RadioButton>(R.id.radio_presence)

            val name = findName.text.toString()
            val presence = findPresence.isChecked

            mViewModel.save(mGuestId, name, presence)
        }
    }

    private fun observe() {

        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        val findName = findViewById<EditText>(R.id.edit_name)
        val findRadio = findViewById<RadioButton>(R.id.radio_presence)
        val findRadioAbsent = findViewById<RadioButton>(R.id.radio_absent)

        mViewModel.guest.observe(this, Observer {
            findName.setText(it.name)
            if (it.presence) {
                findRadio.isChecked = true
            } else {
                findRadioAbsent.isChecked = true
            }
        })

    }

    private fun setListeners() {

        val findButton = findViewById<Button>(R.id.button_save)

        val click = findButton.setOnClickListener(this)

        return click
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }
    }

}