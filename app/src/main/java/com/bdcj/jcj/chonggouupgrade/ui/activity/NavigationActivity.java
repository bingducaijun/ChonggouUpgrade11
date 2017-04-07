package com.bdcj.jcj.chonggouupgrade.ui.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.bdcj.jcj.chonggouupgrade.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class NavigationActivity extends AppIntro
{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		addSlide(AppIntroFragment.newInstance("Welcome!", "This is a demo of the AppIntro library, with permissions being requested on a slide..", R.drawable.img_welcome_one, Color.parseColor("#1976D2")));
		addSlide(AppIntroFragment.newInstance("Permission Request", "In order to access your camera, you must give permissions.", R.drawable.img_welcome_two, Color.parseColor("#1976D2")));
		addSlide(AppIntroFragment.newInstance("Simple, yet Customizable", "The library offers a lot of customization, while keeping it simple for those that like simple.", R.drawable.img_welcome_three, Color.parseColor("#1976D2")));
		//addSlide(AppIntroFragment.newInstance("Explore", "Feel free to explore the rest of the library demo!", R.drawable.ic_slide4, Color.parseColor("#1976D2")));

		// Here we load a string array with a camera permission, and tell the
		// library to request permissions on slide 2
		askForPermissions(new String[] { Manifest.permission.CAMERA }, 2);
	}

	@Override
	public void onSkipPressed(Fragment currentFragment)
	{
		super.onSkipPressed(currentFragment);
		finish();
	}

	@Override
	public void onDonePressed(Fragment currentFragment)
	{
		super.onDonePressed(currentFragment);
		finish();
	}
}
