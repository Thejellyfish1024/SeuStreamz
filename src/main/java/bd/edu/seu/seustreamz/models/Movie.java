package bd.edu.seu.seustreamz.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Movie {
    private int id;
    private String title;
    private String description;
    private String genre;
    private String videoLink;
    private String thumbnailLink;
    private List<String> viewers;
    private List<String> likedBy;
    private LocalDateTime createdAt;

    // Constructors
    public Movie() {}

    public Movie( String title, String description, String genre, String videoLink, String thumbnailLink) {
        this.id = generateId();
        this.title = title;
        this.videoLink = videoLink;
        this.thumbnailLink = thumbnailLink;
        this.description = description;
        this.genre = genre;
        this.viewers = new ArrayList<>();
        this.likedBy = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Movie(int id, String title, String description, String genre, String videoLink, String thumbnailLink, List<String> viewers, List<String> likedBy, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.videoLink = videoLink;
        this.thumbnailLink = thumbnailLink;
        this.description = description;
        this.genre = genre;
        this.viewers = viewers;
        this.likedBy = likedBy;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getViewers() {
        return viewers;
    }

    public void setViewers(List<String> viewers) {
        this.viewers = viewers;
    }

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    int generateId() {
        long timestamp = System.currentTimeMillis();
        return (int) (timestamp % Integer.MAX_VALUE);
    }

    public int getLikesCount() {
        return likedBy.size();
    }
    public int getViewsCount(){
        return viewers.size();
    }

}
