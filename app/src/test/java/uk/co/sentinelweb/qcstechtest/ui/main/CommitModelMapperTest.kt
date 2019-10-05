package uk.co.sentinelweb.qcstechtest.ui.main

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import uk.co.sentinelweb.qcstechtest.domain.Commit
import java.time.ZonedDateTime

class CommitModelMapperTest {

    private val sut = CommitModelMapper()

    @Before
    fun setUp() {
    }

    @Test
    fun map() {
        val date = ZonedDateTime.now()
        val element = Commit(
            "sha_1",
            "message1",
            date,
            Commit.Author("name1", "d1@d.com", "http://image.com/url")
        )

        val actual = sut.map(element)

        assertEquals("sha_1", actual.sha)
        assertEquals("message1", actual.message)
        assertEquals(date.toString(), actual.dateString)
        assertEquals("name1", actual.name)
        assertEquals("http://image.com/url", actual.imageUrl)
    }
}