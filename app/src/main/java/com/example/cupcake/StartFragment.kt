package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentStartBinding
import com.example.cupcake.model.OrderViewModel

class StartFragment : Fragment() {

    /*
     * Binding object instance corresponding to the fragment_start.xml layout
     * This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
     * when the view hierarchy is attached to the fragment.
     */
    private var binding: FragmentStartBinding? = null

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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    /*
     * the third fun of Fragment -> onViewCreated()
     * call the functions to display on UI
     * Update the UI
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.startFragment = this
    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen.
     */
    fun orderCupcake(quantity: Int) {
        // Update the view model with the quantity
        sharedViewModel.setQuantity(quantity)

        // If no flavor is set in the view model yet, select vanilla as default flavor
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }
        // Navigate to the next destination to select the flavor of the cupcakes
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    /**
     * DestroyView
     */
   override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}