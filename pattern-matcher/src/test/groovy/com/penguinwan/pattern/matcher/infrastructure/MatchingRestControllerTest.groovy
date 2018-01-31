package com.penguinwan.pattern.matcher.infrastructure

import com.penguinwan.pattern.matcher.application.MatcherNotFoundException
import com.penguinwan.pattern.matcher.application.MatchingApplicationService
import com.penguinwan.pattern.matcher.application.SetupMatcherCommand
import com.penguinwan.pattern.matcher.domain.model.Consequent
import org.glassfish.hk2.api.Factory
import org.glassfish.hk2.utilities.binding.AbstractBinder
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import javax.ws.rs.client.Entity
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import static org.mockito.Matchers.*
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
class MatchingRestControllerTest extends JerseyTest {

    @Mock
    MatchingApplicationService matchingApplicationService
    MyAppServiceFactory factory

    class MyBinder extends AbstractBinder {
        MyAppServiceFactory factory

        @Override
        protected void configure() {
            bindFactory(factory).to(MatchingApplicationService)
        }
    }

    class MyAppServiceFactory implements Factory {
        MatchingApplicationService appService

        @Override
        Object provide() {
            return appService
        }

        @Override
        void dispose(Object o) {
        }
    }

    @Override
    protected Application configure() {
        factory = new MyAppServiceFactory()
        MyBinder binder = new MyBinder(factory: factory)
        return new ResourceConfig().register(binder).register(MatchingRestController.class)
    }

    @Before
    void setup() {
        factory.appService = matchingApplicationService
    }

    @Test
    void "200 result when matcher is valid"() {
        when(matchingApplicationService.match(anyLong(), anyVararg())).thenReturn(new Consequent('susi', 'lily'))

        Response response = target("matcher/12").
                request().
                post(Entity.entity(
                        Collections.emptyMap(),
                        MediaType.APPLICATION_JSON))

        JSONObject jsonObject = new JSONObject(response.readEntity(String.class))
        assert jsonObject['subject'] == 'susi'
        assert jsonObject['value'] == 'lily'
    }

    @Test
    void "404 when matcher id is invalid"() {
        when(matchingApplicationService.match(eq(Long.valueOf(99)), anyVararg())).thenThrow(new MatcherNotFoundException())

        Response response = target("matcher/99").
                request().
                post(
                        Entity.entity(new HashMap(),
                                MediaType.APPLICATION_JSON))

        assert response.status == 404

    }

    @Test
    void "200 when setup valid matcher"() {
        when(matchingApplicationService.setup(isNotNull(SetupMatcherCommand.class))).thenReturn(Long.valueOf(100))

        Response response = target("matcher").request().put(Entity.entity(new SetupMatcherCommand(), MediaType.APPLICATION_JSON))

        assert response.status == 200
        assert response.readEntity(Long.class) == 100
    }
}
