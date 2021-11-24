package de.entsesselt.fileyournal.model;

public class Data {

    String currentTemplate;
    String content1;
    String content2;
    String content3;
    String content4;
    int pageIndex;
    int maxIndex;

    public Data (String currentTemplate, String content1, String content2, String content3, String content4, int pageIndex, int maxIndex){
        this.currentTemplate = currentTemplate;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.content4 = content4;
        this.pageIndex = pageIndex;
        this.maxIndex = maxIndex;
    }

    public String getCurrentTemplate() {
        return currentTemplate;
    }

    public void setCurrentTemplate(String currentTemplate) {
        this.currentTemplate = currentTemplate;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getContent4() {
        return content4;
    }

    public void setContent4(String content4) {
        this.content4 = content4;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }
}
