package example

import example.testsupport.DataTestId
import kotlinx.coroutines.test.runTest
import react.FC
import react.create
import react.use.useConstant
import tanstack.history.CreateMemoryHistoryOpts
import tanstack.history.createMemoryHistory
import tanstack.react.router.Router
import tanstack.react.router.RouterOptions
import tanstack.react.router.RouterProvider
import tanstack.react.router.createRouter
import testing.library.dom.EventType
import testing.library.dom.click
import testing.library.dom.fireEvent
import testing.library.dom.screen
import testing.library.react.cleanup
import testing.library.react.render
import web.html.HTMLElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RouterTest {
    private fun createTestAppRouter(): Router {
        val history = createMemoryHistory(
            CreateMemoryHistoryOpts(
                initialEntries = arrayOf("/"),
                initialIndex = 0
            ))

        return createRouter(
            RouterOptions(
                routeTree = buildRouteTree(),
                history = history
            )
        )
    }

    val TestableApp = FC {
        val appRouter = useConstant(::createTestAppRouter)

        RouterProvider {
            router = appRouter
        }
    }


    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage() = runTest {
        // before each test

        // given

        // when
        render(TestableApp.create())

        // then
        val indexContainer = screen.findByTestId<HTMLElement>(DataTestId.INDEX_CONTAINER)
        assertNotNull(indexContainer, "index page")

        val topicsLink = screen.findByTestId<HTMLElement>(DataTestId.INDEX_LINK_TOPICS)
        assertNotNull(topicsLink, "link to topics on index page")

        // when
// TODO: replace with UE user.click(topicsLink)
        fireEvent[EventType.click]?.invoke(topicsLink, undefined)

        // then
        val topicsContainer = screen.findByTestId<HTMLElement>(DataTestId.TOPICS_CONTAINER)
        assertNotNull(topicsContainer, "topics page")

        val componentTopicLink = screen.findByTestId<HTMLElement>(DataTestId.TOPIC_LINK_COMPONENTS)
// TODO: enable when supported
//        val componentTopicLink: HTMLElement =
//            within(topicsContainer).findByTestId<HTMLElement>(DataTestId.TOPIC_LINK_COMPONENTS)

// TODO: replace with UE user.click(componentTopicLink)
        fireEvent[EventType.click]?.invoke(componentTopicLink, undefined)

        val topicContainer = screen.findByTestId<HTMLElement>(DataTestId.TOPIC_CONTAINER)
        assertEquals("Requested topic ID: components", topicContainer.textContent)

        // after each test
        cleanup()
    }

    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage1() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage2() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage3() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage4() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage5() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage6() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage7() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage8() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage9() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage10() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage11() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage12() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage13() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage14() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage15() = shouldClickTopicsLinkAndNavigateToTopicsPage()
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage16() = shouldClickTopicsLinkAndNavigateToTopicsPage()
}
