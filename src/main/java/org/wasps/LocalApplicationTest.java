package org.wasps;

import org.wasps.data.TestClassBad;
import org.wasps.data.TestClassGood;
import org.wasps.model.MethodModel;
import org.wasps.model.SourceFile;
import org.wasps.service.concretes.LocalClassLoader;

import java.util.ArrayList;
import java.util.List;

// This class is to test any functionality locally
// This should be run as a separate, regular Application run configuration
public class LocalApplicationTest {
    public static void main(String[] args) {
        LocalClassLoader loader = new LocalClassLoader();

        SourceFile source1 = loader.loadClass(TestClassGood.class);
        SourceFile source2 = loader.loadClass(TestClassBad.class);
        loader.writeSourceFilesToJson();
        List<SourceFile> fromJson = loader.getSourceFilesFromJson();
        List<List<MethodModel>> methods = new ArrayList<>();
        fromJson.forEach(file -> methods.add(file.getMethods()));
        methods.get(0).forEach(method -> System.out.println(method.getName()));
    }
}
