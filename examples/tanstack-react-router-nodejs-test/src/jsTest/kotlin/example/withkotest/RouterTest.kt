package example.withkotest

import example.TestApp
import example.TopicServiceImpl
import example.di.Di
import example.di.DiConstants.SCOPE
import example.di.DiConstants.TOPIC_SERVICE
import example.testsupport.*
import io.kotest.core.spec.style.ShouldSpec
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import react.ReactNode
import react.create
import testing.library.dom.screen
import testing.library.dom.within
import testing.library.react.cleanup
import testing.library.react.render
import testing.library.user.event.userEvent
import kotlin.test.assertNotNull

// Run tests with the Default coroutine dispatcher
// Test needs no knowledge of how coroutines are used
class RouterTestWithKotest : ShouldSpec() {
    object JsTestScope : CoroutineScope {
        override val coroutineContext =
            SupervisorJob() + Dispatchers.Default
    }

    init {

        // Use dependency injection to inject this test CoroutineScope into your production code instead of MainScope
        fun createTestApp(testScope: CoroutineScope): ReactNode {
            val testDI = object : Di {
                override fun get(key: String) = when (key) {
                    SCOPE -> testScope
                    TOPIC_SERVICE -> TopicServiceImpl()
                    else -> throw Exception("Unknown Di key '$key'")
                }
            }

            val testApp = TestApp.create {
                di = testDI
            }
            return testApp
        }

        afterTest {
            cleanup()
        }

        suspend fun doTest() {
            val testApp = createTestApp(JsTestScope)

            // given
            val user = userEvent.setup()

            // when
            render(testApp)

            // then
            screen.findByTestId(INDEX_CONTAINER_ID)

            val topicsLink = screen.queryByTestId(INDEX_LINK_TOPICS_ID)
            assertNotNull(topicsLink, "link to topics on index page")

            // when
            user.click(topicsLink)

            // then
            val topicsContainer = screen.findByTestId(TOPICS_CONTAINER_ID)

            val componentTopicLink =
                within(topicsContainer).queryByTestId(TOPIC_LINK_COMPONENTS_ID)
            assertNotNull(componentTopicLink, "link to component topic")

            user.click(componentTopicLink)

            screen.findByTestId(TOPIC_CONTAINER_ID)
        }

        should("should click topics link and navigate to topics page") {
            doTest()
        }

        should("should click topics link and navigate to topics page 01") { doTest() }
        should("should click topics link and navigate to topics page 02") { doTest() }
        should("should click topics link and navigate to topics page 03") { doTest() }
        should("should click topics link and navigate to topics page 04") { doTest() }
        should("should click topics link and navigate to topics page 05") { doTest() }
        should("should click topics link and navigate to topics page 06") { doTest() }
        should("should click topics link and navigate to topics page 07") { doTest() }
        should("should click topics link and navigate to topics page 08") { doTest() }
        should("should click topics link and navigate to topics page 09") { doTest() }
        should("should click topics link and navigate to topics page 10") { doTest() }
        should("should click topics link and navigate to topics page 11") { doTest() }
        should("should click topics link and navigate to topics page 12") { doTest() }
        should("should click topics link and navigate to topics page 13") { doTest() }
        should("should click topics link and navigate to topics page 14") { doTest() }
    }
}
