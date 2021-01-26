/*
 * Copyright (c) 2019 Hemanth Savarala.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by
 *  the Free Software Foundation either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package code.name.monkey.retromusic.model

import code.name.monkey.retromusic.util.MusicUtil
import code.name.monkey.retromusic.util.PreferenceUtil
import java.util.*

data class Artist(
    val id: Long,
    val albums: List<Album>
) {

    val name: String
        get() {
            val name = safeGetFirstAlbum().safeGetFirstSong().albumArtist
            if (PreferenceUtil.albumArtistsOnly && MusicUtil.isVariousArtists(name)) {
                return VARIOUS_ARTISTS_DISPLAY_NAME
            }
            return if (MusicUtil.isArtistNameUnknown(name)) {
                UNKNOWN_ARTIST_DISPLAY_NAME
            } else safeGetFirstAlbum().safeGetFirstSong().artistName
            return "oke"
        }

    val songCount: Int
        get() {
            var songCount = 0
            for (album in albums) {
                songCount += album.songCount
            }
            return songCount
        }

    val albumCount: Int
        get() = albums.size

    val songs: List<Song>
        get() = albums.flatMap { it.songs }

    fun safeGetFirstAlbum(): Album {
        return albums.firstOrNull() ?: Album.empty
    }

    companion object {
        const val UNKNOWN_ARTIST_DISPLAY_NAME = "Unknown Artist"
        const val VARIOUS_ARTISTS_DISPLAY_NAME = "Various Artists"
        const val VARIOUS_ARTISTS_ID : Long = -2

        @JvmStatic
        val empty = Artist(-1, emptyList())
    }
}
