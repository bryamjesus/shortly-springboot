package com.bjtg.shortly.model.url;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false, columnDefinition = "varchar")
    private String originalUrl;

    @Column(name = "short_code", nullable = false, length = 8, unique = true)
    private String shortCode;

    @Column(name = "hit_count")
    private Integer hitCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date modifiedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.hitCount = 1;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = new Date();
    }

    public Url() {
    }

    Url(UrlBuilder builder) {
        this.id = builder.getId();
        this.originalUrl = builder.getOriginalUrl();
        this.shortCode = builder.getShortCode();
        this.hitCount = builder.getHitCount();
        this.createdAt = builder.getCreatedAt();
        this.modifiedAt = builder.getModifiedAt();
    }

    public static UrlBuilder builder(String originalUrl, String shortCode) {
        return new UrlBuilder(originalUrl, shortCode);
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void increaseHitCount() {
        this.hitCount += 1;
    }
}
