package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.news

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.FragmentHomeBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import javax.inject.Inject


class NewsRoomFragment : BaseFragment<FragmentHomeBinding, NewsRoomViewModel>(), NewsRoomNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var newsRoomViewModel:NewsRoomViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_news_room

    override val viewModel: NewsRoomViewModel
        get() {
            if(!::newsRoomViewModel.isInitialized){
                newsRoomViewModel = ViewModelProviders.of(this, factory).get(NewsRoomViewModel::class.java)
            }
            return newsRoomViewModel
        }

    override fun goBack() {
        baseActivity?.onFragmentDetached(TAG)
    }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsRoomViewModel.setNavigator(this)
    }

    companion object {
        val TAG = NewsRoomFragment::class.java.simpleName

        fun newInstance(): NewsRoomFragment {
            val args = Bundle()
            val fragment = NewsRoomFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun handleError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
