package org.frenchfrie;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.Instant;
import javax.persistence.Entity;

@Entity
public class Data extends PanacheEntity {

  public String name;
  public Instant creation;
  public Instant modification;


}
