package com.ifce.dailypoint.entities;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Enterprise")
public class Enterprise {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
	private Long id;

    private String name;

    private String address;

    private String cnpj;

    private String email;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enterprise",  orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Employee> employees;

    @CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
