package org.teknichrono.mgp.model.output;

import org.teknichrono.mgp.api.model.result.Classification;
import org.teknichrono.mgp.api.model.result.Record;
import org.teknichrono.mgp.api.model.result.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionResultOutput extends SessionOutput {

  public List<RecordOutput> records = new ArrayList<>();
  public List<SessionClassificationOutput> classifications = new ArrayList<>();

  public SessionResultOutput() {
    super(true);
  }

  public static SessionResultOutput from(Session s, Classification c, List<SessionClassificationOutput> results) {
    SessionResultOutput session = new SessionResultOutput();
    session.fillFromSession(s);
    session.fillFromClassification(c);
    session.classifications = results;

    return session;
  }

  private void fillFromClassification(Classification c) {
    if (c.records != null && !c.records.isEmpty()) {
      for (Record record : c.records) {
        records.add(RecordOutput.from(record));
      }
    }
  }

}
