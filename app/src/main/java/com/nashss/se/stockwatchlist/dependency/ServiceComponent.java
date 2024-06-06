package com.nashss.se.stockwatchlist.dependency;


import com.nashss.se.stockwatchlist.activity.*;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateWatchListActivity provideCreateWatchListActivity();

    DeleteWatchListActivity provideDeleteWatchListActivity();

    SearchStockInfoActivity provideSearchStockInfoActivity();

    GetWatchListActivity provideGetWatchListActivity();

    AddStockIntoWatchListActivity provideAddStockIntoWatchListActivity();

    RemoveStockFromWatchListActivity provideRemoveStockFromWatchListActivity();


}
