package com.staunch.tech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_assets")
public class Asset {
    @Id
    @Column(name ="asset_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="name")
    private String name;
    @Column(name ="description")
    private String description;
    @Column(name ="price")
    private float price;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "facility_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location location;
  
    
    
    
    @Column(name ="created_by")
    private String createdBy;
    @Column(name ="created_time")
    private long createdTime;
    @Column(name ="updated_by")
    private String updatedBy;
    @Column(name ="updated_time")
    private long updatedTime;

    @Transient
    private boolean expand;
}