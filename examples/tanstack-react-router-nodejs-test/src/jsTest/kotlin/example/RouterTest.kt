package example

import example.testsupport.DataTestId
import example.zegreatrob.jsmints.TestingLibraryReact.act
import example.zegreatrob.jsmints.TestingLibraryReact.fireEvent
import example.zegreatrob.jsmints.TestingLibraryReact.screen
import kotlinx.coroutines.test.runTest
import react.create
import react.dom.client.createRoot
import web.dom.document
import web.html.HTMLDivElement
import kotlin.test.Test
import kotlin.test.assertNotNull

class RouterTest {
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage() = runTest {
        // before each test
        val container = document.createElement("div") as HTMLDivElement
        document.body.appendChild(container)


        // given
        val root = createRoot(container)

        // when
        act {
            root.render(
                App.create()
            )
        }

        // then
        val indexContainer = screen.findByTestId(DataTestId.INDEX_CONTAINER)
        assertNotNull(indexContainer, "index page")

        val topicsLink = screen.findByTestId(DataTestId.INDEX_LINK_TOPICS)
        assertNotNull(topicsLink, "link to topics on index page")

        // when
        fireEvent.click(topicsLink)

        // then
        val topicsContainer = screen.findByTestId(DataTestId.TOPICS_CONTAINER)
        assertNotNull(topicsContainer, "topics page")

        // after each test
        act { root.unmount() }
        container.remove()
    }
}
