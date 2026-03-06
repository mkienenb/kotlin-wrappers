package example

import example.testsupport.DataTestId
import kotlinx.coroutines.test.runTest
import react.create
import testing.library.dom.EventType
import testing.library.dom.click
import testing.library.dom.fireEvent
import testing.library.dom.screen
import testing.library.react.cleanup
import testing.library.react.render
import kotlin.test.Test
import kotlin.test.assertNotNull

class RouterTest {
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage() = runTest {
        // before each test

        // given

        // when
        render(App.create())

        // then
        val indexContainer : web.dom.Element = screen.findByTestId(DataTestId.INDEX_CONTAINER)
        assertNotNull(indexContainer, "index page")

        val topicsLink : web.dom.Element = screen.findByTestId(DataTestId.INDEX_LINK_TOPICS)
        assertNotNull(topicsLink, "link to topics on index page")

        // when
        fireEvent[EventType.click]?.invoke(topicsLink, js("undefined"))

        // then
        val topicsContainer : web.dom.Element = screen.findByTestId(DataTestId.TOPICS_CONTAINER)
        assertNotNull(topicsContainer, "topics page")

        // after each test
        cleanup()
    }
}
