package com.androidshowtime.trivia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.androidshowtime.trivia.databinding.FragmentTitleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TitleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.i("MainActivity", "onCreate() called()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentTitleBinding.inflate(inflater)

        /*  val binding: FragmentTitleBinding = DataBindingUtil.inflate(
              inflater, R.layout
                  .fragment_title, container, false
          )*/

        binding.playButton.setOnClickListener {

            this.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
            /* v ->
             v.findNavController()
                 .navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())*/

        }

        binding.rulesButton.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_titleFragment_to_rulesFragment)
        }


        binding.aboutButton.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_titleFragment_to_aboutFragment)
        }

        setHasOptionsMenu(true)

        Log.i("MainActivity", "onCreateView() called()")

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super
            .onOptionsItemSelected(item)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

        Log.i("MainActivity", "onAttach() called()")
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TitleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TitleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("MainActivity", "onActivityCreated() called")
    }


    override fun onStart() {
        super.onStart()

        Log.i("MainActivity", "onStart() called")


    }

    override fun onResume() {
        super.onResume()

        Log.i("MainActivity", "onResume() called")
    }


    override fun onPause() {
        super.onPause()

        Log.i("MainActivity", "onPause() called")
    }


    override fun onStop() {
        super.onStop()

        Log.i("MainActivity", "onStop() called")
    }


    override fun onDestroyView() {
        super.onDestroyView()

        Log.i("MainActivity", "onDestroyView() called")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        Log.i("MainActivity", "onDetach() called()")

    }


}
