package com.example.myapplication.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStatusBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_status.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatusFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentStatusBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_status,container,false)
        binding.show = this
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun showSimpleSnackBar(view: View) {
            Snackbar.make(statusFragment,"Done! ",Snackbar.LENGTH_LONG).show()
    }

    fun showActionSnackBar(view: View) {
      val  a :Snackbar =  Snackbar.make(statusFragment,"Loading .. ",Snackbar.LENGTH_LONG)
        a.setAction("Undo"){
             Snackbar.make(statusFragment,"Done! ",Snackbar.LENGTH_LONG).show()
        }
        a.show()
    }

    fun fab(view: View) {
        val  a :Snackbar =  Snackbar.make(statusFragment,"Loading .. ",Snackbar.LENGTH_LONG)
        a.setBackgroundTint(Color.YELLOW)
        a.setAction("Undo"){
            Snackbar.make(statusFragment,"Done! ",Snackbar.LENGTH_LONG).show()
        }
        a.show()
    }
}