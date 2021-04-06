package com.ifce.dailypoint.entities;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkedHour {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
	private Long id;

    private int day;

    private int month;

    private int year;

    @Column(name="worked_hours_morning")
    private LocalDateTime workedHoursMorning;

    @Column(name="worked_hours_afternoon")
    private LocalDateTime workedHoursAfternoon;

    @Column(name="worked_hours_day")
    private double workedHoursDay;

    @ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
}
