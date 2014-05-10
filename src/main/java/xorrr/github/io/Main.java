package xorrr.github.io;

import java.net.UnknownHostException;

import xorrr.github.io.db.DatastoreFacade;
import xorrr.github.io.db.MediaDatastore;
import xorrr.github.io.db.MediaMongoDatastore;
import xorrr.github.io.db.UserDatastore;
import xorrr.github.io.db.UserMongoDatastore;
import xorrr.github.io.rest.SparkFacade;
import xorrr.github.io.rest.routes.GetMediaByIdRoute;
import xorrr.github.io.rest.routes.PostMediaRoute;
import xorrr.github.io.rest.routes.PutRangeToMediaRoute;
import xorrr.github.io.rest.transformation.Transformator;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        UserDatastore uds = new UserMongoDatastore();
        MediaDatastore mds = new MediaMongoDatastore();
        DatastoreFacade facade = new DatastoreFacade(uds, mds);
        Transformator transformator = new Transformator();

        SparkFacade rest = new SparkFacade();
        rest.setPort(1337);
        rest.setPostMediaRoute(new PostMediaRoute(facade, transformator));
        rest.setGetMediaByIdRoute(new GetMediaByIdRoute(facade, transformator));
        rest.setPutRangeToMediaRoute(new PutRangeToMediaRoute(facade,
                transformator));
    }
}
