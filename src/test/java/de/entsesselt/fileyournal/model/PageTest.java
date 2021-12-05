package de.entsesselt.fileyournal.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("PageTest with five test cases ")
class PageTest {

    Page fullpage = new Page("fullpage","ColumnSystem.png","", "", "");
    Page halfpage = new Page("half","ColumnSystem.png","Dots.png", "", "");
    Page quadpage = new Page("quad","ColumnSystem.png","Ikigai.png", "Dots.png", "Blanko.png");
    Page halfquadpage = new Page ("halfQuad","ColumnSystem.png","Blanko.png", "Dots.png", "");
    Page quadhalfpage = new Page("quadHalf","ColumnSystem.png","Blanko.png", "Dots.png", "");
    Page overload = new Page("quad","ColumnSystem.png","Ikigai.png", "Dots.png", "Blanko.png");



    @AfterEach
    void deletePageCounter() {
        Page.setCurrentPageNumber(0);
    }

    @Nested
    @DisplayName("fullpage test")
    class PageTestFullpage{

        @Test
        public void testPage(){
            assertEquals("ColumnSystem.png", fullpage.getContent1());
        }

        @Test
        public void testPageCreator(){
            assertNotNull(fullpage.pageCreator());
            assertEquals(1, Page.getCurrentPageNumber());
        }
    }

    @Nested
    @DisplayName("halfpage test")
    class PageTestHalfpage{

        @Test
        public void testPage(){
            assertEquals("ColumnSystem.png", halfpage.getContent1());
            assertEquals("Dots.png", halfpage.getContent2());
        }

        @Test
        public void testPageCreator(){
            assertNotNull(halfpage.pageCreator());
            assertEquals(1, Page.getCurrentPageNumber());
        }
    }

    @Nested
    @DisplayName("quadpage test")
    class PageTestQuadpage{

        @Test
        public void testPage(){
            assertEquals("ColumnSystem.png", quadpage.getContent1());
            assertEquals("Ikigai.png", quadpage.getContent2());
            assertEquals("Dots.png", quadpage.getContent3());
            assertEquals("Blanko.png", quadpage.getContent4());
        }

        @Test
        public void testPageCreator(){
            assertNotNull(quadpage.pageCreator());
            assertEquals(1, Page.getCurrentPageNumber());
        }
    }

    @Nested
    @DisplayName("quadHalfpage test")
    class PageTestQuadHalfpage{

        @Test
        public void testPage(){
            assertEquals("ColumnSystem.png", quadhalfpage.getContent1());
            assertEquals("Blanko.png", quadhalfpage.getContent2());
            assertEquals("Dots.png", quadhalfpage.getContent3());
        }

        @Test
        public void testPageCreator(){
            assertNotNull(quadhalfpage.pageCreator());
            assertEquals(1, Page.getCurrentPageNumber());
        }
    }

    @Nested
    @DisplayName("halfQuadpage test")
    class PageTestHalfQuadpage{

        @Test
        public void testPage(){
            assertEquals("ColumnSystem.png", halfquadpage.getContent1());
            assertEquals("Blanko.png", halfquadpage.getContent2());
            assertEquals("Dots.png", halfquadpage.getContent3());
        }

        @Test
        public void testPageCreator(){
            assertNotNull(halfquadpage.pageCreator());
            assertEquals(1, Page.getCurrentPageNumber());
        }
    }

    @Nested
    @DisplayName("Overload test")
    class PageTestOverload{

        @Test
        public void testPage(){
            assertEquals("ColumnSystem.png", overload.getContent1());
            assertEquals("Blanko.png", quadpage.getContent4());
        }

        @Test
        public void testPageCreator(){
            assertNotNull(overload.pageCreator());
            assertEquals(1, Page.getCurrentPageNumber());
        }
    }
/*

    @Test
    void page(){
        assertEquals("ColumnSystem.png", fullpage.getContent1());
        assertEquals("Dots.png", halfpage.getContent2());
        assertEquals("Blanko.png", quadpage.getContent4());
        assertEquals("Dots.png", halfquadpage.getContent3());
        assertEquals("", quadhalfpage.getContent4());
    }

    @Test
    void pageCreator(){
        assertNotNull(fullpage.pageCreator());
        assertEquals(1, Page.getCurrentPageNumber());
    }
*/

}