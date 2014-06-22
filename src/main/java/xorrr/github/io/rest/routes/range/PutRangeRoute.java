package xorrr.github.io.rest.routes.range;

import spark.Request;
import spark.Response;
import spark.Route;
import xorrr.github.io.db.DatastoreFacade;
import xorrr.github.io.model.Range;
import xorrr.github.io.rest.MappedRoutesParams;
import xorrr.github.io.rest.RestHelperFacade;
import xorrr.github.io.rest.RouteQueryParams;
import xorrr.github.io.rest.transformation.Transformator;
import xorrr.github.io.utils.logging.PutRangeRouteLogger;

import com.google.inject.Inject;

public class PutRangeRoute implements Route {

    private DatastoreFacade ds;
    private Transformator transformator;
    private RestHelperFacade restHelper;
    private PutRangeRouteLogger logger;

    @Inject
    public PutRangeRoute(DatastoreFacade dsFacade, Transformator trans,
            RestHelperFacade h, PutRangeRouteLogger l) {
        ds = dsFacade;
        transformator = trans;
        restHelper = h;
        logger = l;
    }

    @Override
    public String handle(Request req, Response resp) {
        String rangeId = "";

        if (noJsonPayload(req)) {
            logger.logNoRangePayloadProvided();
            restHelper.stopRequest(404, "todo");
        } else {
            rangeId = modifyRange(req);
        }

        return rangeId;
    }

    private String modifyRange(Request req) {
        String mediaId = req.params(MappedRoutesParams.ID);
        String userId = checkUserIdProvided(req);

        String rangeId = storeModifiedRange(req, mediaId, userId);
        return rangeId;
    }

    private boolean noJsonPayload(Request req) {
        return req.contentLength() < 1;
    }

    private String storeModifiedRange(Request req, String mediaId, String userId) {
        Range r = transformator.toRangePojo(req.body());
        String rangeId = ds.modifyRange(r, mediaId, userId);
        logger.logRangeModified(rangeId);
        return rangeId;
    }

    private String checkUserIdProvided(Request req) {
        String userId = req.queryParams(RouteQueryParams.USER_ID);
        if (userId == null) {
            logger.logNoUserIdProvided();
            restHelper.stopRequest(404, "todo");
        }
        return userId;
    }

}
