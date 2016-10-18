package pr03_DependencyInversionSkeleton.contracts;

import java.io.IOException;

public interface Reader {

    String read() throws IOException;
}
