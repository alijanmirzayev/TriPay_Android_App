package com.alijan.tripay.ui.onboarding

import com.alijan.tripay.R
import com.alijan.tripay.data.model.Onboarding
import com.alijan.tripay.databinding.FragmentOnboardingBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.OnboardingAdapter

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    private val onboardingAdapter = OnboardingAdapter()
    private val onboardingItems = arrayListOf(
        Onboarding(
            "Ödənişlərinizi \nmobil bankçılıq ilə \nidarə edin",
            "Pulunuzu mobil cihazdan təhlükəsiz idarə etməyin rahat yolu.",
            R.drawable.img_onboarding_1
        ),
        Onboarding(
            "Pul köçürmələrini \n0% komissiya \nilə edin",
            "Artıq pul köçürmələri daha asan və komissiyasız indi Tripay’da!",
            R.drawable.img_onboarding_2
        )
    )

    override fun layoutInflater(): FragmentOnboardingBinding =
        FragmentOnboardingBinding.inflate(layoutInflater)

    override fun setupUI() {
        setupViewPager()
        buttonClickListener()
    }

    private fun setupViewPager() {
        with(binding) {
            viewPager2Onboarding.adapter = onboardingAdapter
            viewPager2Onboarding.setUserInputEnabled(false)
            dotsIndicatorOnboarding.attachTo(viewPager2Onboarding)
            onboardingAdapter.updateList(onboardingItems)
        }
    }

    private fun buttonClickListener() {
        with(binding) {
            buttonOnboarding.setOnClickListener {
                if (viewPager2Onboarding.currentItem == 0) {
                    viewPager2Onboarding.setCurrentItem(1, true)
                    buttonOnboarding.text = "Daxil ol"
                } else {
                    // navigate olacaq
                }
            }
        }
    }

}