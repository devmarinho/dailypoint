package com.ifce.dailypoint.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
   
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
	private Long id;

    private String name;

    private String address;

    private String cpf;

	@ManyToOne
	@JoinColumn(name="enterprise_id")
	private Enterprise enterprise;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee",  orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TimePoint> timePoints;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee",  orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkedHour> workedHours;

    @CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;


}
