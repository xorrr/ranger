package xorrr.github.io;

import java.net.UnknownHostException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import xorrr.github.io.db.DatastoreFacade;
import xorrr.github.io.db.MediaDatastore;
import xorrr.github.io.db.MediaMongoDatastore;
import xorrr.github.io.db.UserDatastore;
import xorrr.github.io.db.UserMongoDatastore;
import xorrr.github.io.rest.SparkFacade;
import xorrr.github.io.rest.routes.media.GETmediaByIdRoute;
import xorrr.github.io.rest.routes.media.GETmediaRoute;
import xorrr.github.io.rest.routes.media.POSTmediaRoute;
import xorrr.github.io.rest.routes.media.PUTmediaRoute;
import xorrr.github.io.rest.routes.user.POSTuserRoute;
import xorrr.github.io.rest.transformation.Transformator;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);

        UserDatastore uds = new UserMongoDatastore();
        MediaDatastore mds = new MediaMongoDatastore();
        DatastoreFacade facade = new DatastoreFacade(uds, mds);
        Transformator transformator = new Transformator();

        SparkFacade rest = new SparkFacade();
        rest.setPort(1337);
        rest.setPostMediaRoute(new POSTmediaRoute(facade, transformator));
        rest.setGetMediaByIdRoute(new GETmediaByIdRoute(facade, transformator));
        rest.setPutRangeToMediaRoute(new PUTmediaRoute(facade,
                transformator));
        rest.setPostUserRoute(new POSTuserRoute(facade, transformator));
        rest.setGetMediaRoute(new GETmediaRoute(facade, transformator));
        rest.setWildcardRoutes();
    }
}
