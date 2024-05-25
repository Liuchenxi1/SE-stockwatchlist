package com.nashss.se.stockwatchlist.dependency;


import com.nashss.se.stockwatchlist.activity.CreateWatchListActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateWatchListActivity provideCreateWatchListActivity();


}
