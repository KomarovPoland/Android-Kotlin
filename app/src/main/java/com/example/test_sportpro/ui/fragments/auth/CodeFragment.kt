package com.example.test_sportpro.ui.fragments.auth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.test_sportpro.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_code.view.*

class CodeFragment : Fragment(), View.OnClickListener {

    private val args: CodeFragmentArgs by navArgs()
    lateinit var navController: NavController
    lateinit var auth: FirebaseAuth
    lateinit var numberForTextView: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_code, container, false)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        view.backButton.setOnClickListener { activity?.onBackPressed() }

        val myNumber = args.code
        numberForTextView = args.numberForTextView.toString()

        Log.d("TAG", myNumber.toString())
        view.textView4.text = "На номер ${numberForTextView}\nотправлен код подтвержения"
        Toast.makeText(activity, args.statuss, Toast.LENGTH_LONG).show()

        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.buttonCode).setOnClickListener(this)

        val user = navController.previousBackStackEntry?.arguments?.getSerializable("user")
        navController.currentBackStackEntry?.arguments?.putSerializable("user", user)

        auth = FirebaseAuth.getInstance()

        val verify = view.findViewById<Button>(R.id.buttonCode)
        val otpGiven = view.findViewById<EditText>(R.id.editTextPersonalCode)

//        On click listener for the verify button

        verify.setOnClickListener {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().windowToken, 0)

            val otp = otpGiven.text.toString().trim()
            if (otp.isNotEmpty()) {
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        myNumber.toString(), otp
                )
                signInWithPhoneAuthCredential(credential)
                Log.d("TAG", "credential:$credential")

            } else {
                Toast.makeText(activity, "Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.buttonCode -> {
                if (args.statuss == "1") {
//                    val action =
//                            CodeFragmentDirections.actionCodeFragmentToRegisterFragment(numberForTextView)
//                    navController.navigate(action)
                    navController.navigate(R.id.action_codeFragment_to_mainProfileFragment)

                } else
                    navController.navigate(R.id.action_codeFragment_to_judgeFragment)
            }

        }
    }

    //Do your own basic conditions to check whether the OTP is entered or not. If entered, pass the
    // credentials to the signInWithPhoneAuthCredential method

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        activity?.let {
            auth.signInWithCredential(credential)
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {

                            if (args.statuss == "1") {
//                                val action = CodeFragmentDirections.actionCodeFragmentToRegisterFragment(numberForTextView)
//                                navController.navigate(action)
                                navController.navigate(R.id.action_codeFragment_to_mainProfileFragment)

                                Log.d("TAG", "SignInwithPhoneAuth")
                            } else {
                                navController.navigate(R.id.action_codeFragment_to_judgeFragment)
                            }


//                        startActivity(Intent(applicationContext, PersonalDataActivity::class.java))
//                        finish()
                        } else {
// Sign in failed, display a message and update the UI
                            if (task.exception is FirebaseAuthInvalidCredentialsException) {
// The verification code entered was invalid
                                Toast.makeText(activity, "Invalid OTP", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
        }
    }

    //if (task.isSuccessful) login the user. Intent to home activity else toast the error message


}