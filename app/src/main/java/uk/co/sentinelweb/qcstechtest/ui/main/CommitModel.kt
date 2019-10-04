package uk.co.sentinelweb.qcstechtest.ui.main


data class CommitModel(
    val sha: String,
    val message: String,
    val dateString: String,
    val name: String,
    val imageUrl: String
)