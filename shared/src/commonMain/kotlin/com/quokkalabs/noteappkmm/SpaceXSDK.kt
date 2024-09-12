package com.quokkalabs.noteappkmm

import com.quokkalabs.noteappkmm.cache.Database
import com.quokkalabs.noteappkmm.cache.DatabaseDriverFactory
import com.quokkalabs.noteappkmm.network.SpaceXApi
import com.quokkalabs.noteappkmm.model.RocketLaunch

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory, val api: SpaceXApi) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearAndCreateLaunches(it)
            }
        }
    }
}
