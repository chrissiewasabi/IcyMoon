package xyz.megundo.icymoon.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.main_fragment.*
import xyz.megundo.icymoon.R
import xyz.megundo.icymoon.data.Application
import xyz.megundo.icymoon.data.Information

class MainFragment : Fragment() {
    private val TAG = "MainFragment"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        btn_submit.setOnClickListener {
            val payload = getDataFromViews()
            Log.d(TAG, "data $payload")
            viewModel.getResponseFromServer(payload).observe(this, Observer { response ->
                if (response == null || response.getError() != null) {
                    showMessage(getString(R.string.error_message))
                } else {
                    Log.d(TAG, "resp ${response.getInformation()}")
                    showMessage(getString(R.string.success_message))
                }

            })

        }
    }

    private fun showMessage(message: String) {
        MaterialAlertDialogBuilder(
            context
        )
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null).show()

    }

    private fun getDataFromViews(): Information {
        val fName = txtFname.text.toString()
        val lName = txtLname.text.toString()
        val phone = txtPhone.text.toString()
        val email = txtEmail.text.toString()
        val idNum = txtId.text.toString()
        val description = txtDesc.text.toString()
        val amount = ((txtAmount.text.toString())).toDouble()

        val data = Application(
            email = email,
            first_name = fName,
            id_number = idNum,
            item_description = description,
            item_value = amount,
            last_name = lName,
            phone_number = phone
        )

        return Information(data)


    }


}
