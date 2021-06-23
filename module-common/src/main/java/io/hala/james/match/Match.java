package io.hala.james.match;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
@Entity
public class Match {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String teamName;

  @Column
  private LocalDate matchDate;

  @Column
  @ColumnDefault("BEFORE")
  private String status;

}
