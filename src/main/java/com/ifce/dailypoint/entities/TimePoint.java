package com.ifce.dailypoint.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ifce.dailypoint.enums.TipoPontoEnum;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TimePoint {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
	private Long id;

    private int day;
    
    private int year;

    @Column(name="point_hour")
    private LocalDateTime pointHour;

    @Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TipoPontoEnum type;

    @ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

}
