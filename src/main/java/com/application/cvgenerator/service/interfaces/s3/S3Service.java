package com.application.cvgenerator.service.interfaces.s3;

import java.io.IOException;
import java.util.Map;

public interface S3Service {
    String upload(Map<String, byte[]> file) throws IOException;
    void delete(String filename);
}
