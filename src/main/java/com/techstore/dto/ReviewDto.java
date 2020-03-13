package com.techstore.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement
public class ReviewDto {
    public int id;
    public String userName;
    public LocalDateTime creationDate;
    public int rating;
    public String comment;

    public int getId() { return id; }
    public String getUserName() { return userName; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}
