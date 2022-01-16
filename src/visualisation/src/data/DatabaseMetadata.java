package data;

import java.net.MalformedURLException;
import java.net.URL;

public class DatabaseMetadata {
  private final String dbAddress;

  public DatabaseMetadata() {
    this.dbAddress = "http://10.230.128.212:5000";
  }

  public URL getUrl() throws MalformedURLException {
    return new URL(this.dbAddress);
  }
}
