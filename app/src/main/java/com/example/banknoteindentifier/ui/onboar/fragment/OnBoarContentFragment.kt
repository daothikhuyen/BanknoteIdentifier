package com.example.banknoteindentifier.ui.onboar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banknoteindentifier.data.domain.entities.OnboardingOption
import com.example.banknoteindentifier.databinding.FragmentOnBoarContentBinding
import com.example.banknoteindentifier.ui.onboar.OnBoarViewModel
import com.example.banknoteindentifier.ui.onboar.data.getDataOnBoarFirst
import com.example.banknoteindentifier.ui.onboar.data.getDataOnBoarThree
import com.example.banknoteindentifier.ui.onboar.data.getDataOnBoarTwo
import com.example.banknoteindentifier.ui.onboar.fragment.adapter.OnBoardContentAdapter

class OnBoarContentFragment : Fragment() {
    private var _binding: FragmentOnBoarContentBinding? = null
    private val binding get() = _binding!!

    private val adapter: OnBoardContentAdapter by lazy {
        OnBoardContentAdapter(
            onClickOption = ::onClickOption
        )
    }
    private val viewModel: OnBoarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoarContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPage()
    }

    fun setUpViewPage() {
        val type = arguments?.getInt(ARG_TYPE) ?: 0
        binding.rvItemOnBoar.adapter = adapter
        binding.rvItemOnBoar.layoutManager = LinearLayoutManager(requireContext())
        viewModel.option.asLiveData().observe(viewLifecycleOwner) {
            adapter.submitList(it.getValue(type))
        }

        val pageData = when (type) {
            0 -> getDataOnBoarFirst()
            1 -> getDataOnBoarTwo()
            2 -> getDataOnBoarThree()
            else -> getDataOnBoarFirst()
        }
        binding.tvTitle.text = pageData.title
        viewModel.setUpData(type, pageData)
        viewModel.option.asLiveData().observe(viewLifecycleOwner) {
            adapter.submitList(it.getValue(type))
        }
    }

    fun onClickOption( item : OnboardingOption){
        viewModel.onClickOption(arguments?.getInt(ARG_TYPE) ?: 0, item)
    }

    companion object {
        private const val ARG_TYPE = "onboarding"

        fun newInstance(type: Int): OnBoarContentFragment {
            val fragment = OnBoarContentFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }
}