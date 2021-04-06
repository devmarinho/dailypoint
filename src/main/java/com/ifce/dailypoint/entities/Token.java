package com.ifce.dailypoint.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
public class Token {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Getter @Setter
	@Column(name = "id_token", length = 150000)
	private String idToken;

	@Getter @Setter
	@Column(name = "refresh_token", length = 15000)
	private String refreshToken;

	@Getter @Setter
	@Column(name = "access_token", length = 15000)
	private String accessToken;

	@Getter @Setter
	@Column(name = "token_type", length = 15000)
	private String tokenType;

	@Getter @Setter
	private LocalDateTime expiresIn;

	@Getter @Setter
	@Column(name = "cognito_user_id", length = 15000)
	private String cognitoUserId;

    @Getter @Setter
	@Column(name = "username")
	private String username;

	@Getter
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Getter
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
