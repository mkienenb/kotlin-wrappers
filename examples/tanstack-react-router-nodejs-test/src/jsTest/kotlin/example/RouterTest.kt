package example

import example.testsupport.DataTestId
import js.promise.await
import kotlinx.coroutines.test.runTest
import react.create
import testing.library.dom.screen
import testing.library.react.cleanup
import testing.library.react.render
import testing.library.user.event.setup.UserEvent
import testing.library.user.event.setup.userEvent
import kotlin.test.Test
import kotlin.test.assertNotNull

// Still getting this error:
//  Exception during run: Error: Package subpath './dist/esm/setup/index' is not defined by "exports"
//  in .../kotlin-wrappers/examples/build/js/node_modules/@testing-library/user-event/package.json
//  imported from .../kotlin-wrappers/examples/build/js/packages/tanstack-react-router-nodejs-test-test/kotlin/tanstack-react-router-nodejs-test-test/example/RouterTest.mjs

val JS_UNDEFINED = js("undefined")

class RouterTest {
    @Test
    fun shouldClickTopicsLinkAndNavigateToTopicsPage() = runTest {
        // before each test

        // given

        // when
        val result = render(App.create())

        // then
        val indexContainer : web.dom.Element = screen.findByTestId(DataTestId.INDEX_CONTAINER)
        assertNotNull(indexContainer, "index page")

        val topicsLink : web.dom.Element = screen.findByTestId(DataTestId.INDEX_LINK_TOPICS)
        assertNotNull(topicsLink, "link to topics on index page")

        // when
        val user : UserEvent = userEvent.setup(JS_UNDEFINED)
        user.click(topicsLink, JS_UNDEFINED).await()
//        fireEvent.invoke(topicsLink, Event.CLICK)

        // then
        val topicsContainer : web.dom.Element = screen.findByTestId(DataTestId.TOPICS_CONTAINER)
        assertNotNull(topicsContainer, "topics page")

        // after each test
        cleanup()
    }
}
