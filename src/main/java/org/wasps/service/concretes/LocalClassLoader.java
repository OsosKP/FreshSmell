package org.wasps.service.concretes;

import org.wasps.model.SourceFile;
import org.wasps.service.abstracts.IClassLoader;

public class LocalClassLoader extends ClassLoaderBase implements IClassLoader {

    @Override
    public SourceFile loadClass(Class objectClass) {
        SourceFile sourceFile = _mapper.map(objectClass);
        addSourceFileToList(sourceFile);
        return sourceFile;
    }
}
