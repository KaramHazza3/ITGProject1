package com.itg.project1.s3objectsview.Service;

import java.io.File;
import java.util.List;

public interface IS3Services {
     List<String> getAllImagesFromS3Bucket();
     void uploadLogFile();
}
