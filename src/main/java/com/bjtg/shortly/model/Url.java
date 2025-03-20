package com.bjtg.shortly.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "short_code", nullable = false, length = 150)
    private String shortCode;

    @Column(name = "hit_count")
    private Integer hitCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createdAt;

    public void setId(Long idUrl) {
        this.id = idUrl;
    }

    public Long getId() {
        return id;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;

    }

    public Integer getHitCount() {
        return hitCount;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
