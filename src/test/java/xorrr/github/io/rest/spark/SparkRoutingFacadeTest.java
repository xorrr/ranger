package xorrr.github.io.rest.spark;

import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import spark.Spark;
import xorrr.github.io.rest.routes.MappedRoutes;
import xorrr.github.io.rest.routes.media.GETmediaByIdRoute;
import xorrr.github.io.rest.routes.media.GETmediaRoute;
import xorrr.github.io.rest.routes.media.POSTmediaRoute;
import xorrr.github.io.rest.routes.media.PUTmediaRoute;
import xorrr.github.io.rest.routes.range.GETrangeRoute;
import xorrr.github.io.rest.routes.range.POSTrangeRoute;
import xorrr.github.io.rest.routes.range.PutRangeRoute;
import xorrr.github.io.rest.routes.user.POSTuserRoute;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Spark.class })
public class SparkRoutingFacadeTest {

    @Mock
    GETmediaByIdRoute getMediaById;
    @Mock
    POSTmediaRoute postMedia;
    @Mock
    PUTmediaRoute putRangeToMedia;
    @Mock
    POSTuserRoute postUser;
    @Mock
    GETmediaRoute getMedia;
    @Mock
    GETrangeRoute getRange;
    @Mock
    POSTrangeRoute postRange;
    @Mock
    PutRangeRoute putRange;

    private SparkRoutingFacade facade;

    @Before
    public void setUp() {
        facade = new SparkRoutingFacade();

        PowerMockito.mockStatic(Spark.class);
    }

    @Test
    public void canSetGetMediaByIdRoute() {
        facade.setGetMediaByIdRoute(getMediaById);
        verifyStatic();
        Spark.get(MappedRoutes.MEDIA_BY_ID, getMediaById);
    }

    @Test
    public void canSetPostMediaRoute() {
        facade.setPostMediaRoute(postMedia);
        verifyStatic();
        Spark.post(MappedRoutes.MEDIA, postMedia);
    }

    @Test
    public void canSetPutRangeToMediaRoute() {
        facade.setPutRangeToMediaRoute(putRangeToMedia);
        verifyStatic();
        Spark.put(MappedRoutes.MEDIA_BY_ID, putRangeToMedia);
    }

    @Test
    public void canSetPostUserRoute() {
        facade.setPostUserRoute(postUser);
        verifyStatic();
        Spark.post(MappedRoutes.USERS, postUser);
    }

    @Test
    public void canSetGetMediaRoute() {
        facade.setGetMediaRoute(getMedia);
        verifyStatic();
        Spark.get(MappedRoutes.MEDIA, getMedia);
    }

    @Test
    public void canSetGetRangeRoute() {
        facade.setGetRangeRoute(getRange);
        verifyStatic();
        Spark.get(MappedRoutes.RANGE, getRange);
    }

    @Test
    public void canSetPostRangeRoute() {
        facade.setPostRangeRoute(postRange);
        verifyStatic();
        Spark.post(MappedRoutes.RANGE, postRange);
    }

    @Test
    public void canSetPutRangeRoute() {
        facade.setPutRangeRoute(putRange);
        verifyStatic();
        Spark.put(MappedRoutes.RANGE, putRange);
    }
}
