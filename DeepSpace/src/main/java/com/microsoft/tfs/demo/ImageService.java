package com.microsoft.tfs.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;

@Path("/images")
public class ImageService {

    private final static List<Image> planetaries;

    static {
        planetaries = new ArrayList<Image>(); 

        //http://www.solarsystemscope.com/nexus/resources/planet_images/
        //Content distributed under CC Attribution license
        //http://creativecommons.org/licenses/by/3.0/
        
        Image mercury = new Image();
        mercury.setSrc("./webapp/images/mercury.png");
        mercury.setWidth(1950);
        mercury.setHeight(1950);
        planetaries.add(mercury);
        
        Image venus = new Image();
        venus.setSrc("./images/venus.png");
        venus.setWidth(547);
        venus.setHeight(550);
        planetaries.add(venus);
        
        // Uncomment the following section of code so Earth appears
        // and remember to update the unit tests in "ImageServiceTest.java"
        
        Image earth = new Image();
        earth.setSrc("./images/earth.png");
        earth.setWidth(512);
        earth.setHeight(512);
        planetaries.add(earth);
        
        Image moon = new Image();
        moon.setSrc("./images/moon.png");
        moon.setWidth(308);
        moon.setHeight(360);
        planetaries.add(moon);
        
        Image mars = new Image();
        mars.setSrc("./images/mars.png");
        mars.setWidth(600);
        mars.setHeight(599);
        planetaries.add(mars);
        
        Image jupiter = new Image();
        jupiter.setSrc("./images/jupiter.png");
        jupiter.setWidth(512);
        jupiter.setHeight(512);
        planetaries.add(jupiter);
        
        Image saturn = new Image();
        saturn.setSrc("./images/saturn.png");
        saturn.setWidth(1206);
        saturn.setHeight(690);
        planetaries.add(saturn);
                
        Image uranus = new Image();
        uranus.setSrc("./images/uranus.png");
        uranus.setWidth(1720);
        uranus.setHeight(1720);
        planetaries.add(uranus);
        
        Image neptune = new Image();
        neptune.setSrc("./images/neptune.png");
        neptune.setWidth(598);
        neptune.setHeight(600);
        planetaries.add(neptune);
        
        Image pluto = new Image();
        pluto.setSrc("./images/pluto.png");
        pluto.setWidth(225);
        pluto.setHeight(225);
        planetaries.add(pluto);
        
        Image meteorite = new Image();
        meteorite.setSrc("./images/meteorite.png");
        meteorite.setWidth(600);
        meteorite.setHeight(600);
        planetaries.add(meteorite);
              
        Image sun = new Image();
        sun.setSrc("./images/sun.png");
        sun.setWidth(660);
        sun.setHeight(660);
        planetaries.add(sun);        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Image> getImages() {
        return planetaries;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public Image getByName(@PathParam("name") final String name) {
        final String imageSrc = String.format("/%s.png", name.toLowerCase());
        for (Image i : planetaries) {
            if (i.getSrc().endsWith(imageSrc)) {
                return i;
            }
        }

        return null;
    }
}
