package com.example.personsfinder.adapter.persistence.connector.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, name = "person_reference")
    private Long reference;
    
    @Column(nullable = false)
    private Double latitude;
    
    @Column(nullable = false)
    private Double longitude;
    // owning side of one-to-one to PersonEntity
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_reference", referencedColumnName = "id", insertable = false, updatable = false)
    private PersonEntity person;
    
    public LocationEntity(Long reference, Double latitude, Double longitude) {
        this.reference = reference;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
