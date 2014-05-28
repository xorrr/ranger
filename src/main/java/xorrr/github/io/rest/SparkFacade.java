package xorrr.github.io.rest;

import spark.Spark;
import xorrr.github.io.rest.routes.GETmediaByIdRoute;
import xorrr.github.io.rest.routes.POSTmediaRoute;
import xorrr.github.io.rest.routes.PUTmediaRoute;

public class SparkFacade {

    public void setPort(int port) {
        Spark.setPort(port);
    }

    public void setGetMediaByIdRoute(GETmediaByIdRoute getMediaById) {
        Spark.get(MappedRoutes.MEDIA_BY_ID, getMediaById);
    }

    public void setPostMediaRoute(POSTmediaRoute postMedia) {
        Spark.post(MappedRoutes.MEDIA, postMedia);
    }

    public void setPutRangeToMediaRoute(PUTmediaRoute putRangeToMedia) {
        Spark.put(MappedRoutes.MEDIA_BY_ID, putRangeToMedia);
    }

    public void setWildcardRoutes() {
        Spark.get("*", (request, response) -> {
            return "404";
        });
    }

}
