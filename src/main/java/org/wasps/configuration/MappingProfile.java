package org.wasps.configuration;

import org.wasps.model.MethodModel;
import org.wasps.model.SourceFile;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MappingProfile {
    public MethodModel mapMethod(Method methodIn) {
        MethodModel methodOut = new MethodModel();
        methodOut.setName(methodIn.getName());
        methodOut.setParameterCount(methodIn.getParameterCount());

        return methodOut;
    }

    public SourceFile map(Class classIn) {
        SourceFile classOut = new SourceFile();
        classOut.setName(classIn.getName());
        classOut.setMethods(mapMethods(classIn.getMethods()));
        // TODO: Add the rest of the mappings
        /*
            Anything available through reflection should be added as above
            Anything we get from source code most go through ISourceCodeParserService
         */

        return classOut;
    }

    public List<MethodModel> mapMethods(Method[] methodsIn) {
        ArrayList<MethodModel> methodsOut = new ArrayList<>();
        for (Method m : methodsIn) {
            methodsOut.add(mapMethod(m));
        }
        return methodsOut;
    }
}
