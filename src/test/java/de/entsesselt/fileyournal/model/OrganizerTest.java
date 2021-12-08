package de.entsesselt.fileyournal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizerTest {

    Page p = new Page("fullpage","ColumnSystem.png","", "", "");
    Page h = new Page("half", "Blanko.png", "Dots.png", "", "");
    Organizer org;

    @BeforeEach
    void setBack(){
        Page.setCurrentPageNumber(0);
    }

    @Test
    void testDeletePage(){
        org = null;
        this.org = (Organizer.getInstance());
        org.readFO("/Users/nicolegrieve/Desktop/testing_Del2.fo");
        assertEquals("flow", org.fetchPageParent().getName());

        org.deletePage(1);
        org.writeFO("/Users/nicolegrieve/Desktop/testing_Del3.fo");
        assertEquals(2,(org.fetchPageParent().getChildren().size()));
    }
    @Test
    void testAddPage() {
        org = null;
        Page.setCurrentPageNumber(0);
        this.org = (Organizer.getInstance());
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        assertEquals("flow", org.fetchPageParent().getName());
        assertEquals(4,(org.fetchPageParent().getChildren().size()));
        org.writeFO("/Users/nicolegrieve/Desktop/testing_Add.fo");
        Page.setCurrentPageNumber(0);
    }

    @Test
    void addModifiedContent() {
        org = null;
        Page.setCurrentPageNumber(0);
        this.org = (Organizer.getInstance());
        org.readFO("/Users/nicolegrieve/Desktop/testing_Mod.fo");
        org.addModifiedContent(1,h.pageCreator());
        assertEquals(2,(org.fetchPageParent().getChildren().size()));
        org.writeFO("/Users/nicolegrieve/Desktop/testing_Mod2.fo");
        Page.setCurrentPageNumber(0);
    }

    @Test
    void insertContent() {
        org = null;
        Page.setCurrentPageNumber(0);
        this.org = (Organizer.getInstance());
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.insertContent(1,h.pageCreator());
        assertEquals(5,(org.fetchPageParent().getChildren().size()));
        org.writeFO("/Users/nicolegrieve/Desktop/testing_Ins.fo");
        Page.setCurrentPageNumber(0);
    }

    /*@Test
    void deletePage() {
        org = null;
        Page.setCurrentPageNumber(0);
        this.org = (Organizer.getInstance("test"));
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addModifiedContent(1,h.pageCreator());
        assertEquals(4,(org.fetchPageParent().getChildren().size()));
        org.writeFO("/Users/nicolegrieve/Desktop/testing_Ins.fo");
        Page.setCurrentPageNumber(0);
    }*/

}