package com.appbin.fininfocomassignment.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class UserInfo (
    @PrimaryKey
    var id: Int = 0,
    @Required
    var name: String? = null,
    @Required
    var age: String? =null,
    @Required
    var city: String? = null,
) :RealmObject()