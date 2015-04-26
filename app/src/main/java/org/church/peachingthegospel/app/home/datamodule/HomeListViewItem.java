package org.church.peachingthegospel.app.home.datamodule;

/**
 * Created by abraham.chen on 2015/4/4.
 */
public class HomeListViewItem {
    public HomeListViewItem(int imageUrl,String title)
    {
        this.setImageUrl(imageUrl);
        this.setTitle(title);
    }
    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    int imageUrl;
    String title;

}
