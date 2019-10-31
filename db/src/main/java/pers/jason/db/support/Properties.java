package pers.jason.db.support;

import javax.inject.Singleton;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/31 15:57
 */
public class Properties {

  private final Map<String, String> _current;

  private Properties _parent;

  private String source = null;

  public Properties() {
    this(null);
  }

  public Properties(final Properties parent) {
    this._current = new HashMap<>();
    this._parent = parent;
  }

  public Properties(final Properties parent, final File file) throws IOException {
    this(parent);

    if (file.exists()) {
      setSource(file.getPath());

      final InputStream input = new BufferedInputStream(new FileInputStream(file));
      try {
        loadFrom(input);
      } catch (final IOException e) {
        throw e;
      } finally {
        input.close();
      }
    }
  }

  public Properties(final Properties parent, final String filepath) throws IOException {
    this(parent, new File(filepath));
  }

  public Properties(final Properties parent, final Map<String, String>... props) {
    this(parent);
    for (int i = props.length - 1; i >= 0; i--) {
      this.putAll(props[i]);
    }
  }

  public String get(final Object key) {
    if (this._current.containsKey(key)) {
      return this._current.get(key);
    } else if (this._parent != null) {
      return this._parent.get(key);
    } else {
      return null;
    }
  }

  public String put(final String key, final String value) {
    return this._current.put(key, value);
  }

  public void putAll(final Map<? extends String, ? extends String> m) {
    if (m == null) {
      return;
    }

    for (final Map.Entry<? extends String, ? extends String> entry : m.entrySet()) {
      this.put(entry.getKey(), entry.getValue());
    }
  }

  /**
   * Put the given Properties into the Props. This method performs any variable substitution in the
   * value replacing any occurrence of ${name} with the value of get("name"). get() is called first
   * on the Props and next on the Properties object.
   *
   * @param properties The properties to put
   * @throws IllegalArgumentException If the variable given for substitution is not a valid key in
   * this Props.
   */
  public void put(final java.util.Properties properties) {
    for (final String propName : properties.stringPropertyNames()) {
      this._current.put(propName, properties.getProperty(propName));
    }
  }

  private void loadFrom(final InputStream inputStream) throws IOException {
    final java.util.Properties properties = new java.util.Properties();
    properties.load(inputStream);
    this.put(properties);
  }

  public void setSource(String source) {
    this.source = source;
  }
}
