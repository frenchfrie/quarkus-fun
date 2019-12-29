package org.frenchfrie;

import io.quarkus.scheduler.Scheduled;
import java.time.Instant;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class DataRandomCreator {

  private static final Logger log = LoggerFactory.getLogger(DataRandomCreator.class);

  @Scheduled(every = "10S")
  @Transactional
  public void createData() {
    Data entity = new Data();
    entity.name = org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5, 20);
    entity.creation = Instant.now();
    entity.modification = Instant.now();
    Data.persist(entity);
    log.info("Created new entity {}.", entity);
  }

}
