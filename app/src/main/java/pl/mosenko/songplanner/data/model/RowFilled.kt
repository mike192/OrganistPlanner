package pl.mosenko.songplanner.data.model

data class RowFilled(
    var rowId: Long,
    var partOfMassName: String,
    var songName: String,
    var versesNumbers: String?,
    var songbookName: String?,
    var numberInSongbook: String?,
    var slideNumber: String?,
    var ordinal: Long
)