package com.bjtg.shortly.model.url;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false)
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

    protected Url() {
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void increaseHitCount() {
        this.hitCount += 1;
    }
}
