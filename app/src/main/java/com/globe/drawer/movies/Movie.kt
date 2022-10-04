package com.globe.movies

data class Movie(val title:String, val date: Int, val poster: Int, val rating: Float, val plot:String, val cast:String)
    :java.io.Serializable {

}