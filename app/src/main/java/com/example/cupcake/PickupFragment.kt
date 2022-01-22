package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentPickupBinding
import com.example.cupcake.databinding.FragmentSummaryBinding
import com.example.cupcake.model.OrderViewModel


class PickupFragment : Fragment() {

    /*
     * Binding object that access to the views in the fragment_pickup.xml layout
     */
    private var binding: FragmentPickupBinding? = null

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
        binding = FragmentPickupBinding.inflate(inflater, container, false)
        return binding?.root
        /* WE CAN DO ALSO LIKE THIS:
         * val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
         * binding = fragmentBinding
         * return fragmentBinding.root
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
            pickupFragment = this@PickupFragment
        }
    }

    /*
     * Navigate to the next screen to see the order summary.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    /*
     * add cancelOrder to move to start fragment
     * the call is will be on button cancel -> fragment_pickup.xml
     */
    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
    }

    /*
     * DestroyView
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}