package com.microsoft.tfs.demo;

import com.microsoft.tfs.demo.ImageService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

//import static org.junit.Assert.*;
// Listed separately
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;


public class ImageServiceTest {

    private ImageService underTest;

    @Before
    public void setUp() {
        this.underTest = new ImageService();
    }

    @Test
    public void testGetImages() {
        List<Image> images = this.underTest.getImages();
        assertNotNull(images);

        // Only have one of the two lines below uncommented one at a time
        //assertEquals(3, images.size());   // Leave this line in for no Earth
        assertEquals(4, images.size()); // Uncomment this line for Earth
    }

    @Test
    public void testGetImagesSun() {
        List<Image> images = this.underTest.getImages();
        Image sun = getByName(images, "sun.png");
        assertEquals(660, sun.getWidth());
        assertEquals(660, sun.getHeight());
    }

    @Test
    public void testGetImagesJupiter() {
        List<Image> images = this.underTest.getImages();
        Image jupiter = getByName(images, "jupiter.png");
        assertEquals(512, jupiter.getWidth());
        assertEquals(512, jupiter.getHeight());
    }

    @Test
    public void testGetImagesSaturn() {
        List<Image> images = this.underTest.getImages();
        Image saturn = getByName(images, "saturn.png");
        assertEquals(1206, saturn.getWidth());
        assertEquals(690, saturn.getHeight());
    }

    @Test
    public void testGetImagesEarth() {
        // Uncomment the following test when Earth is added

        List<Image> images = this.underTest.getImages();
        Image earth = getByName(images, "earth.png");
        assertEquals(512, earth.getWidth());
        assertEquals(512, earth.getHeight());

    }

    @Test
    public void getGetByNameSun() {
        Image sun = this.underTest.getByName("sun");
        assertEquals(660, sun.getWidth());
        assertEquals(660, sun.getHeight());
    }

    @Test
    public void getGetByNameJupiter() {
        Image jupiter = this.underTest.getByName("jupiter");
        assertEquals(512, jupiter.getWidth());
        assertEquals(512, jupiter.getHeight());
    }

    @Test
    public void getGetByNameSaturn() {
        Image saturn = this.underTest.getByName("saturn");
        assertEquals(1206, saturn.getWidth());
        assertEquals(690, saturn.getHeight());
    }

    @Test
    public void getGetByNameEarth() {
        // Uncomment the following test when Earth is added
        /*
        Image earth = this.underTest.getByName("earth");
        assertEquals(512, earth.getWidth());
        assertEquals(512, earth.getHeight());
        */
    }

    @Test
    public void getGetByNameNonExistent() {
        Image nonExistent = this.underTest.getByName("nonexistent");
        assertNull(nonExistent);
    }

    private Image getByName(List<Image> images, String name) {
        if (images != null) {
            for (Image i : images) {
                if (i.getSrc().endsWith(name)) {
                    return i;
                }
            }
        }
        return null;
    }
}
