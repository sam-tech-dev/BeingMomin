package com.beingmomin.mominapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivityMainBinding
import com.beingmomin.mominapp.databinding.NavHeaderMainBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import com.beingmomin.mominapp.ui.main.fragments.addPerson.AddPersonFragment
import com.beingmomin.mominapp.ui.main.fragments.home.HomeFragment
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator, HasSupportFragmentInjector, NavigationView.OnNavigationItemSelectedListener {


    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel

    lateinit var mActivityMainBinding: ActivityMainBinding

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() {
            if (!::mainViewModel.isInitialized) {
                mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
            }
            return mainViewModel
        }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        mActivityMainBinding = getViewDataBinding()

        setUp()
    }


    fun setUp() {
        setSupportActionBar(tb_main)

        val mDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawer_main,
                tb_main,
                R.string.open_drawer,
                R.string.close_drawer) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                hideKeyboard()
            }
        }
        drawer_main.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        setupNavMenu()

        navigation_view_main.setCheckedItem(R.id.nav_item_home)
        onNavigationItemSelected(navigation_view_main.getMenu().getItem(0))

    }

    private fun setupNavMenu() {
        val navHeaderMainBinding: NavHeaderMainBinding = DataBindingUtil.inflate(layoutInflater, R.layout.nav_header_main, navigation_view_main, false)
        navigation_view_main.addHeaderView(navHeaderMainBinding.getRoot())
        navHeaderMainBinding.setViewModel(mainViewModel)
        navigation_view_main.setNavigationItemSelectedListener(this)
    }

    private fun showHomeFragment() {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(HomeFragment.TAG)
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.fl_main, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit()
    }

    private fun showAddPersonFragment() {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(AddPersonFragment.TAG)
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.fl_main,AddPersonFragment.newInstance(), AddPersonFragment.TAG)
                .commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_main.closeDrawer(GravityCompat.START)
        when (item.getItemId()) {
            R.id.nav_item_home -> {
                showHomeFragment()
                return true
            }
            R.id.nav_item_add_person -> {
                showAddPersonFragment()
                return true
            }

            else -> {
                return false
            }
        }
    }


}