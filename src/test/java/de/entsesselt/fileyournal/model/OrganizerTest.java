package de.entsesselt.fileyournal.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizerTest {

    Page p = new Page("fullpage","ColumnSystem.png","", "", "");
    Organizer org;

    @Test
    void noThing(){
        this.org = (Organizer.getInstance("test"));
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        assertEquals(3, org.fetchPageParent().getContentSize());
    }

    @Test
    void testAddPage() {
        this.org = (Organizer.getInstance("test"));
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        assertEquals(7,org.fetchPageParent().getContentSize());
    }

    @Test
    void addModifiedContent() {
        this.org = (Organizer.getInstance("test"));
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        org.addModifiedContent(0,p.pageCreator());
       /* org.addModifiedContent(1,p.pageCreator());*/
        assertEquals("3",org.fetchPageParent().getContentSize());
    }

    @Test
    void insertContent() {
        this.org = (Organizer.getInstance("test"));
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        org.insertContent(1,p.pageCreator());
        assertEquals(4,org.fetchPageParent().getContentSize());
    }

    @Test
    void deletePage() {
        this.org = (Organizer.getInstance("test"));
        org.readFO("/Users/nicolegrieve/Desktop/testing.fo");
        org.addPage(p.pageCreator());
        org.addPage(p.pageCreator());
        org.addModifiedContent(0,p.pageCreator());
        org.deletePage(0);
        assertEquals(4,org.fetchPageParent().getContentSize());
    }
}