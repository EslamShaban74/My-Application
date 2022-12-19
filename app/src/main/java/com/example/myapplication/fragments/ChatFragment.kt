package com.example.myapplication.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.*
import com.example.myapplication.databinding.FragmentChatBinding
import kotlinx.android.synthetic.main.fragment_chat.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        helloTV.animate()
//            .alpha(0.5f)
//            .scaleXBy(0.5f)
//            .scaleYBy(0.5f)
//            .rotation(360f)
//            .translationY(20f)
//            .duration=2000
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
        val binding : FragmentChatBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chat,container,false)
        binding.listen = this // your fragment
        return  binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun press(view: View) {
        if (view == helloTV) {
            Toast.makeText(activity, "Hello text view", Toast.LENGTH_LONG).show()
        } else {
            startActivity(Intent(activity, CalculateAge::class.java))
        }
    }

    fun openWebsiteInWebView(view: View) {
        // open in web view
        val site = webSiteEditText.text.toString()

        /// to move to another activity you must use category that you added in manifest

        val intent =Intent("com.eslam.web")
        intent.addCategory("DATA")
        intent.putExtra("site",site)
        startActivity(intent)

        /// move to screen and return with data using override method onActivityResult
        //  startActivityForResult(intent,100)
    }

    fun openWebSiteInBrowser(view: View) {
        // open in the browser
        val site = webSiteEditText.text.toString()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://$site")
        startActivity(intent)
    }

    fun goToCameraScreen(view: View) {
        val  intent = Intent(requireActivity(), CameraActivity::class.java)
        startActivity(intent)
    }

    fun rateApp(view: View) {
        try {

            val uri = Uri.parse("market://details?id ${requireActivity().packageName}")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            startActivity(intent)
        }catch (e:Exception){

            val uri = Uri.parse("http://play.google.com/store/apps/details?id ${requireActivity().packageName}")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            startActivity(intent)
        }

    }

    fun login(view: View) {
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
    }

    fun listView(view: View) {
        val intent = Intent(requireActivity(),ListView::class.java)
        startActivity(intent)
    }

    fun gridView(view: View) {
        val intent = Intent(requireActivity(), GridActivity::class.java)
        startActivity(intent)
    }
    fun spinner(view: View) {
        val intent = Intent(requireActivity(),SpinnerActivity::class.java)
        startActivity(intent)
    }


}