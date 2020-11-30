package com.microsoft.tfs.demo;

import com.microsoft.applicationinsights.TelemetryClient;
//import com.microsoft.applicationinsights.internal.schemav2.MetricData;
import com.microsoft.applicationinsights.telemetry.RequestTelemetry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Path("/images")
public class ImageService {

    private final static List<Image> PLANETARY;
    private final static TelemetryClient TELEMETRY_CLIENT = new TelemetryClient();
    static {
        Date begin = new Date();
        PLANETARY = new ArrayList<Image>();

        //http://www.solarsystemscope.com/nexus/resources/planet_images/
        //Content distributed under CC Attribution license
        //http://creativecommons.org/licenses/by/3.0/

        Image sun = new Image();
        sun.setSrc("./images/sun.png");
        sun.setWidth(660);
        sun.setHeight(660);
        PLANETARY.add(sun);

        // Uncomment the following section of code so Earth appears
        // and remember to update the unit tests in "ImageServiceTest.java"

        final Image earth = new Image();
        earth.setSrc("./images/earth.png");
        earth.setWidth(512);
        earth.setHeight(512);
        PLANETARY.add(earth);


        final Image jupiter = new Image();
        jupiter.setSrc("./images/jupiter.png");
        jupiter.setWidth(512);
        jupiter.setHeight(512);
        PLANETARY.add(jupiter);

        final Image saturn = new Image();
        saturn.setSrc("./images/saturn.png");
        saturn.setWidth(1206);
        saturn.setHeight(690);
        PLANETARY.add(saturn);

        final Date now = new Date();
        final double duration = now.getTime() - begin.getTime();
        TELEMETRY_CLIENT.trackMetric("imageService_initialization", duration);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Image> getImages() {
        final Date begin = new Date();

        for (final Image i : PLANETARY) {
            if (!new File(i.getSrc()).exists()) {
                TELEMETRY_CLIENT.trackException(new RuntimeException("Did not find any images for " + i.getSrc()));
            }
        }

        final Date now = new Date();
        final long duration = now.getTime() - begin.getTime();
        final RequestTelemetry telemetryData = new RequestTelemetry("/images", new Date(), duration, "200", true);
        TELEMETRY_CLIENT.trackRequest(telemetryData);

        return PLANETARY;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public Image getByName(@PathParam("name") final String name) {
        final Date begin = new Date();

        final String imageSrc = String.format("/%s.png", name.toLowerCase());
        for (final Image i : PLANETARY) {
            if (i.getSrc().endsWith(imageSrc)) {
                final Date now = new Date();
                final long duration = now.getTime() - begin.getTime();
                final RequestTelemetry telemetryData = new RequestTelemetry("/images/{name}", new Date(), duration, "200", true);
                TELEMETRY_CLIENT.trackRequest(telemetryData);
                return i;
            }
        }
        TELEMETRY_CLIENT.trackException(new RuntimeException("Did not find any images matching the name."));
        return null;
    }
}
