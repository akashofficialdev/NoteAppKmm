package com.quokkalabs.noteappkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform