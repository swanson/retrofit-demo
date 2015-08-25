package com.swanson.octodroid.test;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.res.Fs;
import org.robolectric.res.FsFile;

import java.io.File;

public class CustomTestRunner extends RobolectricTestRunner {

    public static File testDirLocation;

    public CustomTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    private static final String MANIFEST_FILE_NAME = "AndroidManifest.xml";

    private static final File ROOT_ANDROID_PROJECT =
            new File(MANIFEST_FILE_NAME).exists() ? new File(".") : new File("../Octodroid");

    @Override
    protected AndroidManifest createAppManifest(FsFile manifestFile, FsFile resDir, FsFile assetsDir) {
        return new AndroidManifest(resourceFile(MANIFEST_FILE_NAME), resourceFile("res"),
                resourceFile("assets"));
    }

    public static FsFile resourceFile(String... pathParts) {
        return resourcesBaseDir().join(pathParts);
    }

    public static FsFile resourcesBaseDir() {
        return Fs.newFile(resourcesBaseDirFile());
    }

    private static File resourcesBaseDirFile() {
        if (testDirLocation == null) {
            if (hasTestManifest(ROOT_ANDROID_PROJECT))
                return testDirLocation = ROOT_ANDROID_PROJECT;

            throw new RuntimeException("can't find your TestAndroidManifest.xml in "
                    + ROOT_ANDROID_PROJECT.getAbsolutePath());
        } else {
            return testDirLocation;
        }
    }

    private static boolean hasTestManifest(File testDir) {
        return new File(testDir, MANIFEST_FILE_NAME).isFile();
    }
}
