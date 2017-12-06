package com.cricbuzz.medicbuddy.di;


import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    /*UserInfoViewModel userInfoViewModel();

    UserDetailViewModel userDetailViewModel();*/
}
