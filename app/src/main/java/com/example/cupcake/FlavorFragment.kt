package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentFlavorBinding
import com.example.cupcake.databinding.FragmentSummaryBinding
import com.example.cupcake.model.OrderViewModel


class FlavorFragment : Fragment() {

    /*
     * Binding object that access to the views in the fragment_flavor.xml layout
     */
    private var binding: FragmentFlavorBinding? = null


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

        binding = FragmentFlavorBinding.inflate(inflater, container, false)
        return binding?.root
        /* WE CAN DO ALSO LIKE THIS:
         * val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
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
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            flavorFragment = this@FlavorFragment
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    /*
    * add cancelOrder to move to start fragment
    * the call is will be on button cancel -> fragment_flavor.xml
     */
    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }

    /**
     * DestroyView
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}