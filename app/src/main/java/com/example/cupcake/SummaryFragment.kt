package com.example.cupcake

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentSummaryBinding
import com.example.cupcake.model.OrderViewModel


class SummaryFragment : Fragment() {

    /*
     * Binding object that access to the views in the fragment_summary.xml layout
     */
    private var binding: FragmentSummaryBinding? = null


    /*
    * create a reference object with OrderViewModel type
    * the reference object from activityViewModels
     */
    private val sharedViewModel: OrderViewModel by activityViewModels()


    /*
     * the first fun of Fragment -> onCreate()
     * Inflate the layout XML file and return a binding object instance
     */
    // is by Default is create it

    /*
     * the second fun of Fragment -> onCreateView()
     * Inflate the layout XML file and return a binding object instance
     */
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSummaryBinding.inflate(inflater, container, false)
       return binding?.root
        /* WE CAN DO ALSO LIKE THIS:
         * val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
         * binding = fragmentBinding
         *  return fragmentBinding.root
         */
    }

    /*
     * the third fun of Fragment -> onViewCreated()
     * call the functions to display on UI
     * Update the UI
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    /**
     * ORDER IS SEND IT !!
     */
    fun sendOrder() {
        //Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()

        // Construct the order summary text with information from the view model
        val numberOfCupcakes = sharedViewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes), // plurals if it 1,1
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
            sharedViewModel.price.value.toString()
        )

        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // Check if there's an app that can handle this intent before launching it
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            startActivity(intent)
        }
    }

    /*
     * add cancelOrder to move to start fragment
     * the call is will be on button cancel -> fragment_summary.xml
     */
    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }

    /*
    * DestroyView
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}