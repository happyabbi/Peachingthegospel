package org.church.peachingthegospel.app.model;

/**
 * Created by abraham.chen on 2015/4/18.
 */
public class ListImageModel1 {
    String imageUrl;
    String title;

    public ListImageModel1(String imageUrl, String title){
        setImageUrl(imageUrl);
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
